package me.ezra.api.domain.login.dto;

import me.ezra.core.domain.user.UserCreateReq;

import java.time.LocalDate;


public record SignUpReq(
        String name,
        String email,
        String password,
        LocalDate birthday
) {

    public UserCreateReq toUserCreateReq() {
        return new UserCreateReq(name, email, password, birthday);
    }
}
