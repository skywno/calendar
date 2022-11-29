package me.ezra.api.domain.login.dto;

import javax.validation.constraints.NotBlank;

public record LoginReq(
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}
