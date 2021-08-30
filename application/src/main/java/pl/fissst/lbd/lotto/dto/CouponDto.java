package pl.fissst.lbd.lotto.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
public class CouponDto {

    private Long id;
    private OffsetDateTime creationDate;
    private Set<Integer> numbers;
    private Integer numberOfHits;
}