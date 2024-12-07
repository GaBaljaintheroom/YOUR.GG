package geng.your.gg.api.fixture;


import geng.your.gg.api.dto.SummonerInfoDto;
import geng.your.gg.infrastructure.riot.dto.match.DetailChallengesDto;
import geng.your.gg.infrastructure.riot.dto.match.DetailInfoDto;
import geng.your.gg.infrastructure.riot.dto.match.DetailMatchDto;
import geng.your.gg.infrastructure.riot.dto.match.DetailParticipantDto;
import geng.your.gg.infrastructure.riot.dto.match.MatchIdsDto;
import geng.your.gg.infrastructure.riot.dto.match.SimpleInfoDto;
import geng.your.gg.infrastructure.riot.dto.match.SimpleMatchDto;
import geng.your.gg.infrastructure.riot.dto.match.SimpleParticipantDto;
import geng.your.gg.infrastructure.riot.dto.user.AccountDto;
import java.util.List;
import java.util.stream.IntStream;

public class ResponseDtoFixture {

    public static AccountDto accountDto() {
        return new AccountDto("testPuuid", "testGameName", "testTagLine");
    }

    public static SummonerInfoDto summonerInfoDto() {
        return SummonerInfoDto.builder()
            .gameName("testGameName")
            .tagLine("testTagLine")
            .profileImage("testProfileImage")
            .summonerLevel(999)
            .build();
    }

    public static MatchIdsDto matchIdsDto(int size) {
        return MatchIdsDto.from(
            IntStream.range(0, size)
                .mapToObj(i -> i + "matchId")
                .toList()
        );
    }

    public static DetailMatchDto detailMatchDto(int size) {
        return new DetailMatchDto(
            new DetailInfoDto(detailParticipantDtoList(size))
        );
    }

    public static SimpleMatchDto simpleMatchDto(int size) {
        return new SimpleMatchDto(
            new SimpleInfoDto(
                "GameComplete",
                1000,
                "CLASSIC",
                1000,
                simpleParticipantDtoList(size))
        );
    }

    private static List<SimpleParticipantDto> simpleParticipantDtoList(int size) {
        return IntStream.range(0, size)
            .mapToObj(i -> simpleParticipantDto())
            .toList();
    }

    private static SimpleParticipantDto simpleParticipantDto() {
        return new SimpleParticipantDto(
            "puuid",
            true,
            3,
            3,
            3,
            "championName"
        );
    }

    private static List<DetailParticipantDto> detailParticipantDtoList(int size) {
        return IntStream.range(0, size)
            .mapToObj(i -> detailParticipantDto())
            .toList();
    }

    private static DetailParticipantDto detailParticipantDto() {
        return new DetailParticipantDto(
            "puuid",
            true,
            "championName",
            999,
            10000,
            100,
            10,
            0,
            10,
            3,
            3,
            3,
            detailChallengesDto()
        );
    }

    private static DetailChallengesDto detailChallengesDto() {
        return new DetailChallengesDto(0, 3);
    }
}
