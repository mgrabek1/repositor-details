package com.github.mapper;

import com.github.dto.GithubRepositoryResponseDto;
import com.github.dto.RepositoryDetailsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface GithubMapper {

    @Mapping(source = "stargazersCount", target = "stars")
    RepositoryDetailsDto toRepositoryDetails(GithubRepositoryResponseDto githubRepositoryResponseDto);
}
