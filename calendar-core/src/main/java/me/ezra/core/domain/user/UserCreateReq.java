package me.ezra.core.domain.user;

import java.time.LocalDate;

public record UserCreateReq(
        String name,
        String email,
        String password,
        LocalDate birthday
) {
}
