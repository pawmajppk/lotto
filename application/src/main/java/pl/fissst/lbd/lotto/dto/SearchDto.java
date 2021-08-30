package pl.fissst.lbd.lotto.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchDto {

    private String sort;
    private String order;
    private Long limit;
    private Integer page;
}