package me.ezra.api.domain;

import me.ezra.core.domain.schedule.dto.RequestReplyType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static me.ezra.api.domain.login.application.LoginService.LOGIN_SESSION_KEY;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(
            Model model,
            HttpSession httpSession,
            @RequestParam(required = false) String redirect
    ) {
        model.addAttribute("isSignIn",
                httpSession.getAttribute(LOGIN_SESSION_KEY) != null);
        model.addAttribute("redirect", redirect);
        return "index";
    }

    @GetMapping("/events/invitations/{invitationId}")
    public String updateInvitation(
            @PathVariable Long invitationId,
            HttpSession httpSession,
            @Valid @RequestParam RequestReplyType type,
            Model model
    ) {
        model.addAttribute("isSignIn", httpSession.getAttribute(LOGIN_SESSION_KEY) != null);
        model.addAttribute("updateType", type.name());
        model.addAttribute("invitationId", invitationId);
        model.addAttribute("path", "/events/invitations/" + invitationId + "?type=" + type.name());
        return "update-event";

    }

}
