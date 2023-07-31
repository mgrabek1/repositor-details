package com.github.service;

import com.github.dto.RepositoryDetailsDto;

public interface GithubRepoService {

    RepositoryDetailsDto fetchRepoDetails(String owner, String repositoryName);
}
