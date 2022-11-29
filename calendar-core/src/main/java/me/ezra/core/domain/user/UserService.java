package me.ezra.core.domain.user;

import lombok.RequiredArgsConstructor;
import me.ezra.core.global.exception.CalendarException;
import me.ezra.core.global.exception.ErrorCode;
import me.ezra.core.global.util.Encryptor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final Encryptor bcryptEncryptor;
    @Transactional
    public User create(UserCreateReq request) {
        userRepository.findByEmail(request.email())
                .ifPresent(user -> {
                    throw new CalendarException(ErrorCode.ALREADY_EXISTS_USER);
                });
        return userRepository.save(
                User.builder()
                        .name(request.name())
                        .password(bcryptEncryptor.encrypt(request.password()))
                        .email(request.email())
                        .birthday(request.birthday())
                        .build()
        );
    }

    public Optional<User> findPwMatchUser(String email, String password) {
        return userRepository.findByEmail(email)
                .map(user -> user.isMatch(password, bcryptEncryptor) ? user : null);
    }

    public User findByUserIdOrThrow(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new CalendarException(ErrorCode.USER_NOT_FOUND));
    }

}
