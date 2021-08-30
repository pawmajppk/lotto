package pl.fissst.lbd.lotto.mapper;

import org.mapstruct.Mapper;
import pl.fissst.lbd.lotto.dto.CouponDto;
import pl.fissst.lbd.lotto.model.CouponModelApi;

@Mapper(componentModel = "spring")
public interface CouponMapper {

    CouponDto mapModelApiToDto(CouponModelApi couponModelApi);

    CouponModelApi mapDtoToModelApi(CouponDto couponDto);
}