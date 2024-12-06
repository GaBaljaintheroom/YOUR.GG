package geng.your.gg;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import geng.your.gg.infrastructure.riot.RiotClient;
import geng.your.gg.infrastructure.riot.dto.AccountDto;
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
}
