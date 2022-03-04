package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreate {
    @NotEmpty
    private String username;
    @NotEmpty
    private char[] password;
    @Builder.Default
    private boolean active = true;
}

