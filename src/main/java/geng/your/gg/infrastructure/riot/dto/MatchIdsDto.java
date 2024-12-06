package geng.your.gg.infrastructure.riot.dto;

import java.util.List;

public record MatchIdsDto(
    List<String> matchIds
) {
    public static MatchIdsDto from(List<String> matchIds) {
        return new MatchIdsDto(matchIds);
    }

}
