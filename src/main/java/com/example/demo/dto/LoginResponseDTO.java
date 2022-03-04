package com.example.demo.dto;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@Builder
@RequiredArgsConstructor
public class LoginResponseDTO {

    String sessionToken;
}
