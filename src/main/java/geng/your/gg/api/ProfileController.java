package geng.your.gg.api;

import geng.your.gg.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ko/kr")
public class ProfileController {

    private final UserService userService;

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }

}
