package me.ezra.core.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ezra.core.domain.model.BaseEntity;
import me.ezra.core.global.util.Encryptor;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Table(name = "user")
public class User extends BaseEntity {

    @Column(name = "name", nullable = false)
    @Getter
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    @Getter
    private String email;

    @Column(name = "password", nullable = false)
    @Getter
    private String password;

    @Column(name = "birthday", nullable = true)
    @Getter
    private LocalDate birthday;

    @Builder
    public User(String name, String email, String password, LocalDate birthday) {
        Assert.hasText(name, "name cannot be null");
        Assert.hasText(email, "email cannot be null");
        Assert.hasText(password, "password cannot be null");

        this.name = name;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
    }

    public static User createUser(UserCreateReq request, Encryptor encryptor) {
        return User.builder()
                .name(request.name())
                .email(request.email())
                .password(encryptor.encrypt(request.password()))
                .birthday(request.birthday())
                .build();
    }

    public boolean isMatch(String password, Encryptor encryptor) {
        return encryptor.isMatch(password, this.password);
    }

}
