package me.ezra.api.domain.login;

import lombok.RequiredArgsConstructor;
import me.ezra.api.domain.login.dto.LoginReq;
import me.ezra.api.domain.login.dto.SignUpReq;
import me.ezra.core.domain.user.User;
import me.ezra.core.domain.user.UserService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserService userService;

    public final static String LOGIN_SESSION_KEY = "USER_ID";

    public void signUp(SignUpReq signUpReq, HttpSession session) {
        final User user = userService.create(signUpReq.toUserCreateReq());
        session.setAttribute(LOGIN_SESSION_KEY, user.getId());
    }

    public void login(LoginReq loginReq, HttpSession session) {
        final Long userId = (Long) session.getAttribute(LOGIN_SESSION_KEY);
        if (userId != null) {
            return;
        }

        final Optional<User> user = userService.findPwMatchUser(loginReq.email(), loginReq.password());
        if (user.isPresent()) {
            session.setAttribute(LOGIN_SESSION_KEY, user.get().getId());
        } else {
            throw new RuntimeException("Password or email not match");
        }
    }

    public void logout(HttpSession session) {
        /*
        세션 제거
         */
        session.removeAttribute(LOGIN_SESSION_KEY);
    }
}
