package com.github.controller;

import com.github.dto.GithubRepositoryResponseDto;
import com.github.remote.GithubClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GithubRepositoryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GithubClient githubClient;

    @Test
    public void getRepositoryDetailsTest() throws Exception {
        String owner = "owner";
        String repoName = "repo";
        GithubRepositoryResponseDto githubRepositoryResponseDto = GithubRepositoryResponseDto.builder()
                .fullName("testFullName")
                .description("testDescription")
                .stargazersCount(100)
                .cloneUrl("xedsa")
                .createdAt("2022-04-20T18:50:12Z")
                .build();

        when(githubClient.getRepositoryDetails(owner, repoName)).thenReturn(ResponseEntity.ok(githubRepositoryResponseDto));

        mockMvc.perform(get("/repositories/{owner}/{repository-name}", owner, repoName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value(githubRepositoryResponseDto.getFullName()))
                .andExpect(jsonPath("$.description").value(githubRepositoryResponseDto.getDescription()))
                .andExpect(jsonPath("$.cloneUrl").value(githubRepositoryResponseDto.getCloneUrl()))
                .andExpect(jsonPath("$.stars").value(githubRepositoryResponseDto.getStargazersCount()))
                .andExpect(jsonPath("$.createdAt").value(githubRepositoryResponseDto.getCreatedAt()));
    }
}
