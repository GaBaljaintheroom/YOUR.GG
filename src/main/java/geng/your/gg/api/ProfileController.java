package geng.your.gg.api;

import geng.your.gg.api.dto.SummonerInfoDto;
import geng.your.gg.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ko/kr/profile")
public class ProfileController {

    private final UserService userService;

    @GetMapping
    public String profile() {
        return "profile";
    }

    @GetMapping("/search/match/{gameName}/{tageLine}")
    public String searchMatch(
        @PathVariable("gameName") String gameName,
        @PathVariable("tageLine") String tageLine,
        Model model
    ) {
        SummonerInfoDto summonerInfo = userService.getSummonerInfo(gameName, tageLine);

        model.addAttribute("summonerInfo", summonerInfo);

        return "profile";
    }

}
