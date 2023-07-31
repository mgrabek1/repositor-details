package com.github.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@EqualsAndHashCode
@RequiredArgsConstructor(onConstructor_ = {@JsonCreator})
public class RepositoryDetailsDto {
    private final String fullName;
    private final String description;
    private final String cloneUrl;
    private final Integer stars;
    private final String createdAt;

}

