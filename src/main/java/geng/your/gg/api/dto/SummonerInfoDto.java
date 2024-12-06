package geng.your.gg.api.dto;

import lombok.Builder;

@Builder
public record SummonerInfoDto(
    String name,
    String profileImage,
    long summonerLevel
) {

}
