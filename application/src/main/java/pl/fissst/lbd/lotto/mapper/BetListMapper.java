package pl.fissst.lbd.lotto.mapper;

import org.mapstruct.Mapper;
import pl.fissst.lbd.lotto.dto.BetDto;
import pl.fissst.lbd.lotto.dto.FindResultDto;
import pl.fissst.lbd.lotto.model.BetListModelApi;

@Mapper(componentModel = "spring", uses = {BetMapper.class})
public interface BetListMapper {

    BetListModelApi mapDtoToModelApi(FindResultDto<BetDto> findResultDto);
}