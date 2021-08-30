package pl.fissst.lbd.lotto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.fissst.lbd.lotto.dto.DrawSummaryDto;
import pl.fissst.lbd.lotto.model.DrawSummaryModelApi;

@Mapper(componentModel = "spring")
public interface DrawSummaryMapper {

    @Mappings({
            @Mapping(target = "id", source = "draw.id"),
            @Mapping(target = "numbers", source = "draw.numbers"),
    })
    DrawSummaryModelApi mapDtoToModelApi(DrawSummaryDto drawSummaryDto);
}