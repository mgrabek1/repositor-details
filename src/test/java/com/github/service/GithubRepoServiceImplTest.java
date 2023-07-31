package com.github.service;

import com.github.dto.GithubRepositoryResponseDto;
import com.github.dto.RepositoryDetailsDto;
import com.github.exception.GeneralRepositoryWebException;
import com.github.mapper.GithubMapper;
import com.github.remote.GithubClient;
import com.github.service.impl.GithubRepoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class GithubRepoServiceImplTest {

    private GithubClient githubClient;
    private GithubMapper mapper;
    private GithubRepoServiceImpl githubRepoService;

    @BeforeEach
    void setUp() {
        this.githubClient = Mockito.mock(GithubClient.class);
        this.mapper = Mappers.getMapper(GithubMapper.class);
        this.githubRepoService = new GithubRepoServiceImpl(githubClient, mapper);
    }

    @Test
    public void fetchRepoDetails_correctData_DetailsReturned() {
        String owner = "testOwner";
        String repositoryName = "reponame";
        GithubRepositoryResponseDto githubResponse = GithubRepositoryResponseDto.builder()
                .fullName("Repo Name")
                .description("Java")
                .stargazersCount(15)
                .build();
        when(githubClient.getRepositoryDetails(eq(owner), eq(repositoryName))).thenReturn(ResponseEntity.ok(githubResponse));
        RepositoryDetailsDto expectedDto = mapper.toRepositoryDetails(githubResponse);

        RepositoryDetailsDto actualDto = githubRepoService.fetchRepoDetails(owner, repositoryName);

        assertEquals(expectedDto, actualDto);
    }

    @Test
    public void fetchRepoDetails_RepositoryNotFound_ExceptionThrown() {
        String owner = "testOwner";
        String repositoryName = "repository-not-found";

        when(githubClient.getRepositoryDetails(eq(owner), eq(repositoryName))).thenReturn(null);

        var exception = assertThrows(GeneralRepositoryWebException.class, () -> githubRepoService.fetchRepoDetails(owner, repositoryName));
        assertEquals("Repository 'testOwner' not found for owner 'repository-not-found", exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }

    @ParameterizedTest
    @MethodSource
    public void fetchRepoDetails_WrongData_ExceptionThrown(String owner, String repoName) {
        var exception = assertThrows(GeneralRepositoryWebException.class, () -> githubRepoService.fetchRepoDetails(owner, repoName));

        assertEquals("Invalid parameters. Owner or Repository Name can not be empty.", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    private static Stream<Arguments> fetchRepoDetails_WrongData_ExceptionThrown(){
        return Stream.of(
                Arguments.of("", "x"),
                Arguments.of("x", ""),
                Arguments.of("", "")
        );
    }
}