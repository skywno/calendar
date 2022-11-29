package me.ezra.api.domain.login.api;

import lombok.RequiredArgsConstructor;
import me.ezra.api.domain.login.application.LoginService;
import me.ezra.api.domain.login.dto.LoginReq;
import me.ezra.api.domain.login.dto.SignUpReq;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/api/sign-up")
    public ResponseEntity<Void> login(
            @Valid @RequestBody SignUpReq signUpReq,
            HttpSession httpSession
    ) {
        loginService.signUp(signUpReq, httpSession);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/login")
    public ResponseEntity<Void> login(
            @Valid @RequestBody LoginReq loginReq,
            HttpSession httpSession
    ) {
        loginService.login(loginReq, httpSession);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/logout")
    public ResponseEntity<Void> logout(HttpSession httpSession) {
        loginService.logout(httpSession);
        return ResponseEntity.ok().build();
    }
}
