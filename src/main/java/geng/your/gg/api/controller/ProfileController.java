package geng.your.gg.api.controller;

import geng.your.gg.api.dto.DetailMatchResponseDto;
import geng.your.gg.api.dto.SimpleMatchResponseDto;
import geng.your.gg.api.dto.SummonerInfoDto;
import geng.your.gg.api.service.MatchService;
import geng.your.gg.api.service.UserService;
import java.util.List;
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
    private final MatchService matchService;

    @GetMapping
    public String profile() {
        return "profile";
    }

    @GetMapping("/search/match/{gameName}/{tagLine}")
    public String searchMatch(
        @PathVariable("gameName") String gameName,
        @PathVariable("tagLine") String tagLine,
        Model model
    ) {
        SummonerInfoDto summonerInfo = userService.getSummonerInfo(gameName, tagLine);
        List<SimpleMatchResponseDto> simpleMatchInfo = matchService.getSimpleMatchInfo(
            0, 20, summonerInfo.puuid());

        model.addAttribute("summonerInfo", summonerInfo);
        model.addAttribute("simpleMatchInfo", simpleMatchInfo);

        return "profile";
    }

    @GetMapping("/search/match/detail/{matchId}")
    public String searchMatchDetailFragment(
        @PathVariable("matchId") String matchId,
        Model model
    ) {
        DetailMatchResponseDto detailMatchInfo = matchService.getDetailMatchInfoByMatchId(matchId);

        model.addAttribute("detailMatchInfo", detailMatchInfo);

        return "fragments/detail-match-info :: detailMatchInfoFragment";
    }
}
