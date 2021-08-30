package pl.fissst.lbd.lotto.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
public class BetDto {

    private Long id;
    private OffsetDateTime creationDate;
    private DrawDto draw;
    private Set<CouponDto> coupons;
    private Long payment;
}