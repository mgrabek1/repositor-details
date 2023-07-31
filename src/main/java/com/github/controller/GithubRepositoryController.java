package com.github.controller;

import com.github.dto.RepositoryDetailsDto;
import com.github.service.GithubRepoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/repositories")
@RequiredArgsConstructor
public class GithubRepositoryController {

    private final GithubRepoService githubRepoService;

    @GetMapping("/{owner}/{repository-name}")
    public RepositoryDetailsDto getRepositoryDetails(@PathVariable("owner") String owner,
                                                     @PathVariable("repository-name") String repositoryName) {
        return githubRepoService.fetchRepoDetails(owner, repositoryName);
    }
}
