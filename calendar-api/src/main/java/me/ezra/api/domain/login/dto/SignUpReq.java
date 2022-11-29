package me.ezra.api.domain.login.dto;

import me.ezra.core.domain.user.UserCreateReq;

import javax.validation.constraints.*;
import java.time.LocalDate;


public record SignUpReq(
        @NotBlank String name,
        @Email String email,
        @NotBlank
        @Size(min = 6, message = "6자리 이상 입력해주세요.")
        String password,
        @NotNull LocalDate birthday
) {

    public UserCreateReq toUserCreateReq() {
        return new UserCreateReq(name, email, password, birthday);
    }
}
