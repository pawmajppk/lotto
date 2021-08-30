package pl.fissst.lbd.lotto.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DrawSummaryDto {

    private DrawDto draw;
    private Integer numberOf0Hits;
    private Integer numberOf1Hits;
    private Integer numberOf2Hits;
    private Integer numberOf3Hits;
    private Integer numberOf4Hits;
    private Integer numberOf5Hits;
    private Integer numberOf6Hits;
}