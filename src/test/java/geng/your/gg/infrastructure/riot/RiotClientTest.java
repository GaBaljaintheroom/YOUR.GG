package geng.your.gg.infrastructure.riot;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import geng.your.gg.infrastructure.riot.dto.match.DetailMatchDto;
import geng.your.gg.infrastructure.riot.dto.match.MatchIdsDto;
import geng.your.gg.infrastructure.riot.dto.match.SimpleMatchDto;
import geng.your.gg.infrastructure.riot.dto.user.AccountDto;
import geng.your.gg.infrastructure.riot.dto.user.SummonerDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(value = "test")
class RiotClientTest {

    @Autowired
    private RiotClient riotClient;

    @Test
    @DisplayName("소환사의 계정 정보를 가져온다")
    void getGameUserAccount() {
        // given & when
        AccountDto accountDto = riotClient.getGameUserAccount("Hide on bush", "KR1");

        // then
        assertSoftly(softly -> {
            softly.assertThat(accountDto).isNotNull();
            softly.assertThat(accountDto.puuid()).isNotEmpty();
            softly.assertThat(accountDto.gameName()).isNotEmpty();
            softly.assertThat(accountDto.tagLine()).isNotEmpty();
        });
    }

    @Test
    @DisplayName("소환사의 정보를 가져온다")
    void getSummonerInfo() {
        // given & when
        String puuId = "a5KGfHPki4xBS6UXWY5rkL6Jyjk7wyiu-4bECy3J2z-4s7DVqsjlkOL2Q-gtSFaVu5fciizb36rTHA";
        SummonerDto summonerDto = riotClient.getSummoner(puuId);

        // then
        assertSoftly(softly -> {
            softly.assertThat(summonerDto).isNotNull();
            softly.assertThat(summonerDto.accountId()).isNotEmpty();
            softly.assertThat(summonerDto.profileIconId()).isNotNull();
            softly.assertThat(summonerDto.revisionDate()).isNotNull();
            softly.assertThat(summonerDto.id()).isNotEmpty();
            softly.assertThat(summonerDto.puuid()).isNotEmpty();
            softly.assertThat(summonerDto.summonerLevel()).isNotNull();
        });
    }

    @Test
    @DisplayName("소환사 아이디로 최근 match id 리스트를 가져온다.")
    void getMatchIds() {
        //given & when
        String puuId = "a5KGfHPki4xBS6UXWY5rkL6Jyjk7wyiu-4bECy3J2z-4s7DVqsjlkOL2Q-gtSFaVu5fciizb36rTHA";
        MatchIdsDto matchIdsDto = riotClient.getMatchIds(0, 20, puuId);

        //then
        assertSoftly(
            softly -> {
                softly.assertThat(matchIdsDto).isNotNull();
                softly.assertThat(matchIdsDto.matchIds()).isNotEmpty();
            }
        );
    }

    @Test
    @DisplayName("match id로 소환사의 협곡 매치의 간단한 정보를 가져온다")
    void getSimpleMatch() {
        //given & when
        String matchId = "KR_7277245614";
        SimpleMatchDto matchDto = riotClient.getSimpleMatch(matchId);

        //then
        assertSoftly(
            softly -> {
                softly.assertThat(matchDto).isNotNull();
                softly.assertThat(matchDto.info()).isNotNull();
                softly.assertThat(matchDto.info().gameDuration()).isNotNull();
                softly.assertThat(matchDto.info().gameMode()).isNotNull();
                softly.assertThat(matchDto.info().gameStartTimestamp()).isNotNull();
                softly.assertThat(matchDto.info().participants()).isNotEmpty();
            }
        );
    }

    @Test
    @DisplayName("match id로 소환사의 협곡 매치의 상세한 정보를 가져온다")
    void getDetailMatch() {
        //given & when
        String matchId = "KR_7277245614";
        DetailMatchDto matchDto = riotClient.getDetailMatch(matchId);

        //then
        assertSoftly(
            softly -> {
                softly.assertThat(matchDto).isNotNull();
                softly.assertThat(matchDto.info()).isNotNull();
                softly.assertThat(matchDto.info().participants()).isNotEmpty();
                softly.assertThat(matchDto.info().participants().get(0).challenges()).isNotNull();
            }
        );
    }
}
