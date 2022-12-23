package me.ezra.api.domain.email;

import lombok.Builder;
import me.ezra.core.global.util.Period;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvitationDetailsForEmail {
    private final String invitationUpdateUrl = "http://localhost:8080/events" +
            "/invitations/";
    public static final String MAIL_TIME_FORMAT = "yyyy년 MM월 dd일(E) a hh시 mm분";

    private final Long invitationId;
    private final String toEmail;
    private final List<String> participantEmails;
    private final String title;
    private final Period period;

    @Builder
    public InvitationDetailsForEmail(
            Long invitationId,
            String toEmail,
            List<String> participantEmails,
            String title,
            Period period
    ) {
        this.invitationId = invitationId;
        this.toEmail = toEmail;
        this.participantEmails = participantEmails;
        this.title = title;
        this.period = period;
    }

    public String getSubject() {
        return new StringBuilder()
                .append("[초대장] ")
                .append(title)
                .append(" - ")
                .append(period.toString())
                .append("(")
                .append(toEmail)
                .append(")")
                .toString();
    }

    public String getToEmail() {
        return this.toEmail;
    }

    public Map<String, Object> getProps() {
        final Map<String, Object> props = new HashMap<>();
        props.put("title", title);
        props.put("calendar", toEmail);
        props.put("period", period.toString());
        props.put("participants", participantEmails);
        props.put("acceptUrl", invitationUpdateUrl + invitationId + "?type=ACCEPTED");
        props.put("rejectUrl", invitationUpdateUrl + invitationId + "?type=REJECTED");

        return props;
    }
}
