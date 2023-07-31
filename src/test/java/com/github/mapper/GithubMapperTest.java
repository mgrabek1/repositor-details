package com.github.mapper;

import com.github.dto.GithubRepositoryResponseDto;
import com.github.dto.RepositoryDetailsDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

import java.util.stream.Stream;

import static com.github.TestDataFactory.createGithubRepositoryResponseDto;
import static com.github.TestDataFactory.createRepositoryDetails;

public class GithubMapperTest {

    GithubMapper githubMapper = Mappers.getMapper(GithubMapper.class);

    @ParameterizedTest
    @MethodSource
    public void githubMapperTest(GithubRepositoryResponseDto githubRepositoryResponseDto, RepositoryDetailsDto repositoryDetailsDto) {
        RepositoryDetailsDto result = githubMapper.toRepositoryDetails(githubRepositoryResponseDto);

        Assertions.assertEquals(repositoryDetailsDto.getDescription(), result.getDescription());
    }

    private static Stream<Arguments> githubMapperTest() {
        var githubRepositoryResponseData1 = createGithubRepositoryResponseDto("xc", "sdas", "sdasda", 1, "12-02-2023");
        var githubRepositoryResponseData2 = createGithubRepositoryResponseDto("xc", null, "sdasda", 1, "12-02-2023");
        var githubRepositoryResponseData3 = createGithubRepositoryResponseDto(null, "sdas", "sdasda", 1, "12-02-2023");
        var githubRepositoryResponseData4 = createGithubRepositoryResponseDto("xc", "sdas", null, 1, "12-02-2023");
        var githubRepositoryResponseData5 = createGithubRepositoryResponseDto("xc", "sdas", "sdasda", null, "12-02-2023");
        var githubRepositoryResponseData6 = createGithubRepositoryResponseDto("xc", "sdas", "sdasda", null, null);
        var repositoryDetailsData1 = createRepositoryDetails("xc", "sdas", "sdasda", 1, "12-02-2023");
        var repositoryDetailsData2 = createRepositoryDetails("xc", null, "sdasda", 1, "12-02-2023");
        var repositoryDetailsData3 = createRepositoryDetails(null, "sdas", "sdasda", 1, "12-02-2023");
        var repositoryDetailsData4 = createRepositoryDetails("xc", "sdas", null, 1, "12-02-2023");
        var repositoryDetailsData5 = createRepositoryDetails("xc", "sdas", "sdasda", null, "12-02-2023");
        var repositoryDetailsData6 = createRepositoryDetails("xc", "sdas", "sdasda", null, null);
        return Stream.of(
                Arguments.of(githubRepositoryResponseData1, repositoryDetailsData1),
                Arguments.of(githubRepositoryResponseData2, repositoryDetailsData2),
                Arguments.of(githubRepositoryResponseData3, repositoryDetailsData3),
                Arguments.of(githubRepositoryResponseData4, repositoryDetailsData4),
                Arguments.of(githubRepositoryResponseData5, repositoryDetailsData5),
                Arguments.of(githubRepositoryResponseData6, repositoryDetailsData6)
        );
    }

}
