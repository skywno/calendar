package me.ezra.api.domain.schedule.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.time.LocalDate;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ActiveProfiles("test")
class ScheduleControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp(
            WebApplicationContext webApplicationContext,
            @Autowired RestDocumentationContextProvider restDocumentation
    ) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 필터 추가
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    @DisplayName("[GET] Schedule by day")
    void test1() throws Exception {
//        // Given
//        Long userId = 1L;
//        LocalDate date = LocalDate.of(2022, 11, 25);
//
//        // When & Then
//        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/schedules/day")
//                        .queryParam("date", date.toString())
//                        .queryParam("userId", String.valueOf(userId))
//                ).andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$", hasSize(3)))
//                .andExpect(content().string(containsString("여행1")))
//                .andExpect(content().string(containsString("운동1")))
//                .andExpect(content().string(containsString("알람1")))
//                .andDo(document("get-schedule-by-day",
//                        RequestDocumentation.requestParameters(
//                                parameterWithName("userId").ignored(),
//                                parameterWithName("date").description("date")
//                        ),
//                        PayloadDocumentation.responseBody()
//                ));
    }
}