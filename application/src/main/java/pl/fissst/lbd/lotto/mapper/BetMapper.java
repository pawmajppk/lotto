package pl.fissst.lbd.lotto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.fissst.lbd.lotto.dto.BetDto;
import pl.fissst.lbd.lotto.model.Bet;
import pl.fissst.lbd.lotto.model.BetModelApi;

import java.time.OffsetDateTime;

@Mapper(componentModel = "spring", uses = {CouponMapper.class, DrawMapper.class})
public interface BetMapper {

    BetDto mapModelApiToDto(BetModelApi betModelApi);

    @Mapping(target = "dayOfDraw", source = "draw.creationDate")
    BetModelApi mapDtoToModelApi(BetDto betDto);

    BetDto mapEntityToDto(Bet bet);

    Bet mapDtoToEntity(BetDto betDto);

    default Bet mapDtoToNewEntity(BetDto betDto) {
        Bet bet = mapDtoToEntity(betDto);

        bet.setId(null);
        bet.setCreationDate(OffsetDateTime.now());
        bet.setDraw(null);

        bet.getCoupons().forEach(c -> {
            c.setId(null);
            c.setCreationDate(OffsetDateTime.now());
            c.setBet(bet);
        });

        return bet;
    }
}