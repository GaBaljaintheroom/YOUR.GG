package geng.your.gg.api.dto;

import lombok.Builder;

@Builder
public record SummonerInfoDto(
    String gameName,
    String tagLine,
    String profileImage,
    long summonerLevel
) {

}
