package com.github;

import com.github.dto.GithubRepositoryResponseDto;
import com.github.dto.RepositoryDetailsDto;

public final class TestDataFactory {

    private TestDataFactory(){}

    public static GithubRepositoryResponseDto createGithubRepositoryResponseDto(String fullName, String description, String cloneUrl, Integer stargazersCount, String createdAt) {
        return GithubRepositoryResponseDto.builder()
                .fullName(fullName)
                .description(description)
                .cloneUrl(cloneUrl)
                .stargazersCount(stargazersCount)
                .createdAt(createdAt)
                .build();
    }

    public static RepositoryDetailsDto createRepositoryDetails(String fullName, String description, String cloneUrl, Integer stars, String createdAt) {
        return RepositoryDetailsDto.builder()
                .fullName(fullName)
                .description(description)
                .cloneUrl(cloneUrl)
                .stars(stars)
                .createdAt(createdAt)
                .build();
    }
}
