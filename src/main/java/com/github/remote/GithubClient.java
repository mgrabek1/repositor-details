package com.github.remote;

import com.github.dto.GithubRepositoryResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "githubApiClient", url = "https://api.github.com/")
public interface GithubClient {

    @RequestMapping(method = RequestMethod.GET, value = "/repos/{owner}/{repositoryName}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<GithubRepositoryResponseDto> getRepositoryDetails(@PathVariable("owner") String owner, @PathVariable("repositoryName") String repositoryName);
}
