package pl.fissst.lbd.lotto.mapper;

import org.mapstruct.Mapper;
import pl.fissst.lbd.lotto.dto.DrawDto;
import pl.fissst.lbd.lotto.model.Draw;

@Mapper(componentModel = "spring")
public interface DrawMapper {

    DrawDto mapEntityToDto(Draw draw);

    Draw mapDtoToEntity(DrawDto drawDto);
}