package com.github.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Builder
@Getter
@EqualsAndHashCode
@RequiredArgsConstructor(onConstructor_ = {@JsonCreator})
public class MessageDto {
    private final HttpStatus httpStatus;
    private final String message;
}
