package com.github.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@EqualsAndHashCode
@RequiredArgsConstructor(onConstructor_ = {@JsonCreator})
public class GithubRepositoryResponseDto {

    @JsonProperty("full_name")
    private final String fullName;

    @JsonProperty("description")
    private final String description;

    @JsonProperty("clone_url")
    private final String cloneUrl;

    @JsonProperty("stargazers_count")
    private final Integer stargazersCount;

    @JsonProperty("created_at")
    private final String createdAt;
}

