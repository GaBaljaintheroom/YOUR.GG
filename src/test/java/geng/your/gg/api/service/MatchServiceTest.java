package geng.your.gg.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import geng.your.gg.api.dto.DetailMatchResponseDto;
import geng.your.gg.api.dto.SimpleMatchResponseDto;
import geng.your.gg.api.fixture.ResponseDtoFixture;
import geng.your.gg.api.manager.ExternalApiManager;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MatchServiceTest {

    private final ExternalApiManager externalApiManager = mock(ExternalApiManager.class);
    private final MatchService matchService = new MatchService(externalApiManager);

    @Test
    @DisplayName("매치 정보를 간단 조회한다")
    void getSimpleMatchInfo() {
        //given
        String puuid = "puuid";
        given(externalApiManager.getMatchIds(0, 20, puuid))
            .willReturn(ResponseDtoFixture.matchIdsDto(20));

        given(externalApiManager.getSimpleMatch(anyString()))
            .willReturn(ResponseDtoFixture.simpleMatchDto(10));

        //when
        List<SimpleMatchResponseDto> responses = matchService.getSimpleMatchInfo(0, 20, puuid);

        //then
        assertThat(responses).hasSize(20);
    }

    @Test
    @DisplayName("매치 정보 간단 조회 시 소환사의 아이디가 일치하지 않으면 예외가 발생한다.")
    void getSimpleMatchInfoThrowNoSuchElementException() {
        //given
        String puuid = "errorPuuid";

        given(externalApiManager.getMatchIds(0, 20, puuid))
            .willReturn(ResponseDtoFixture.matchIdsDto(20));

        given(externalApiManager.getSimpleMatch(anyString()))
            .willReturn(ResponseDtoFixture.simpleMatchDto(10));

        //when & then
        assertThatThrownBy(() -> matchService.getSimpleMatchInfo(0, 20, puuid))
            .isInstanceOf(NoSuchElementException.class);
    }


    @Test
    @DisplayName("매치 정보를 상세 조회한다")
    void getDetailMatchInfo() {
        //given
        String matchId = "testMatchId";
        given(externalApiManager.getDetailMatch(matchId))
            .willReturn(ResponseDtoFixture.detailMatchDto(10));

        //when
        DetailMatchResponseDto response = matchService.getDetailMatchInfoByMatchId(matchId);

        //then
        assertSoftly(softly -> {
            softly.assertThat(response).isNotNull();
            softly.assertThat(response.info()).isNotNull();
            softly.assertThat(response.info().participants()).hasSize(10);
            softly.assertThat(response.info().participants().get(0).challenges()).isNotNull();
        });
    }

}
