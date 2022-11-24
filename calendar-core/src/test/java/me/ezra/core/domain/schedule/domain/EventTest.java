package me.ezra.core.domain.schedule.domain;

import me.ezra.core.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {
    final LocalDateTime startAt = LocalDateTime.of(2020, 7, 1, 0, 0, 0);
    final LocalDateTime endAt = LocalDateTime.of(2020, 7, 15, 0, 0, 00);
    final Event event = Schedule.event(
                    "title",
                    "desc",
                    startAt,
                    endAt,
                    User.builder()
                            .name("test boy")
                            .email("test@test.com")
                            .password("password")
                            .birthday(LocalDate.of(2021, 01, 01))
                            .build())
            .toEvent();

    @Test
    @DisplayName("전부 과거 --> False")
    void test() {
        final LocalDateTime testStartAt1 = LocalDateTime.of(2020, 6, 1, 0, 0, 0);
        final LocalDateTime testEndAt1 = LocalDateTime.of(2020, 6, 30, 0, 0, 00);

        assertFalse(event.isOverlapped(testStartAt1, testEndAt1));
    }

    @Test
    @DisplayName("end 겹친다 --> True")
    void test2() {
        final LocalDateTime testStartAt2 = LocalDateTime.of(2020, 6, 1, 0, 0, 0);
        final LocalDateTime testEndAt2 = LocalDateTime.of(2020, 7, 5, 0, 0, 0);

        assertTrue(event.isOverlapped(testStartAt2, testEndAt2));
    }

    @Test
    @DisplayName("start 가 겹친다 --> True")
    void test3() {
        final LocalDateTime testStartAt3 = LocalDateTime.of(2020, 7, 1, 0, 0, 0);
        final LocalDateTime testEndAt3 = LocalDateTime.of(2020, 8, 1, 0, 0, 10);

        assertTrue(event.isOverlapped(testStartAt3, testEndAt3));
    }
    @Test
    @DisplayName("전부 미래 --> False")
    void test4() {
        final LocalDateTime testStartAt4 = LocalDateTime.of(2020, 8, 1, 0, 0, 0);
        final LocalDateTime testEndAt4 = LocalDateTime.of(2020, 8, 1, 0, 0, 10);

        assertFalse(event.isOverlapped(testStartAt4, testEndAt4));
    }

    @Test
    @DisplayName("감싸짐 ---> True")
    void test5() {
        final LocalDateTime testStartAt5 = LocalDateTime.of(2020, 6, 1, 0, 0, 0);
        final LocalDateTime testEndAt5 = LocalDateTime.of(2020, 8, 1, 0, 0, 10);

        assertTrue(event.isOverlapped(testStartAt5, testEndAt5));
    }

    @Test
    @DisplayName("안에 포함됨 --> True")
    void test6() {
        final LocalDateTime testStartAt6 = LocalDateTime.of(2020, 7, 2, 0, 0, 0);
        final LocalDateTime testEndAt6 = LocalDateTime.of(2020, 7, 15, 0, 0, 10);

        assertTrue(event.isOverlapped(testStartAt6, testEndAt6));
    }

}