package com.github.service.impl;

import com.github.dto.RepositoryDetailsDto;
import com.github.exception.GeneralRepositoryWebException;
import com.github.mapper.GithubMapper;
import com.github.remote.GithubClient;
import com.github.service.GithubRepoService;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GithubRepoServiceImpl implements GithubRepoService {

    private final GithubClient githubClient;
    private final GithubMapper githubMapper;

    @Override
    public RepositoryDetailsDto fetchRepoDetails(String owner, String repositoryName) {
        log.info("Process of fetching repository details for owner '{}' and repository name '{}' started", owner, repositoryName);
        if (StringUtils.isBlank(owner) || StringUtils.isBlank(repositoryName)) {
            throw new GeneralRepositoryWebException("Invalid parameters. Owner or Repository Name can not be empty.", HttpStatus.BAD_REQUEST);
        }
        var githubRepositoryResponse = githubClient.getRepositoryDetails(owner, repositoryName);
        if (githubRepositoryResponse == null || githubRepositoryResponse.getBody() == null) {
            throw new GeneralRepositoryWebException(String.format("Repository '%s' not found for owner '%s", owner, repositoryName), HttpStatus.NOT_FOUND);
        }
        log.info("Process of fetching repository details for owner '{}' and repository name '{}' finished", owner, repositoryName);
        return githubMapper.toRepositoryDetails(githubRepositoryResponse.getBody());
    }

}
