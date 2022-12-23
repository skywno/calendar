package me.ezra.api;

import lombok.RequiredArgsConstructor;
import me.ezra.core.domain.user.UserCreateReq;
import me.ezra.core.domain.user.UserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class ApplicationSupport implements ApplicationRunner {

    private final UserService userService;

    @Override
    public void run(ApplicationArguments args) {
        UserCreateReq req1 = new UserCreateReq("gyeha", "skywno@gmail.com", "test1234", LocalDate.now());
        UserCreateReq req2 = new UserCreateReq("gyeha", "skywno@naver.com", "test1234", LocalDate.now());
        UserCreateReq req3 = new UserCreateReq("gyeha", "gyeha.lim@aalto.fi", "test1234", LocalDate.now());

        userService.create(req1);
        userService.create(req2);
        userService.create(req3);
    }
}
