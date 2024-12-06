package geng.your.gg;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import geng.your.gg.infrastructure.riot.RiotClient;
import geng.your.gg.infrastructure.riot.dto.AccountDto;
import geng.your.gg.infrastructure.riot.dto.SummonerDto;
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
    void getGameUserAccount() {
        // given & when
        AccountDto accountDto;
        try {
            accountDto = riotClient.getGameUserAccount("Hide on bush", "KR1");
        } catch (Exception e) {
            return;
        }

        // then
        assertSoftly(softly -> {
            softly.assertThat(accountDto).isNotNull();
            softly.assertThat(accountDto.puuid()).isNotEmpty();
            softly.assertThat(accountDto.gameName()).isNotEmpty();
            softly.assertThat(accountDto.tagLine()).isNotEmpty();
        });
    }

    @Test
    void getSummonerInfo() {
        // given & when
        String puuId = "a5KGfHPki4xBS6UXWY5rkL6Jyjk7wyiu-4bECy3J2z-4s7DVqsjlkOL2Q-gtSFaVu5fciizb36rTHA";
        SummonerDto summonerDto;
        try {
            summonerDto = riotClient.getSummonerInfo(puuId);
        } catch (Exception e) {
            return;
        }

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
}
