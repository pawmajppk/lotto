package pl.fissst.lbd.lotto.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.fissst.lbd.lotto.dto.BetDto;
import pl.fissst.lbd.lotto.dto.FindResultDto;
import pl.fissst.lbd.lotto.dto.SearchDto;
import pl.fissst.lbd.lotto.mapper.BetListMapper;
import pl.fissst.lbd.lotto.mapper.BetMapper;
import pl.fissst.lbd.lotto.model.BetListModelApi;
import pl.fissst.lbd.lotto.model.BetModelApi;
import pl.fissst.lbd.lotto.service.BetService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class BetApiController implements BetApi {

    private final BetMapper betMapper;
    private final BetListMapper betListMapper;
    private final BetService betService;

    @Override
    public ResponseEntity<BetModelApi> addBet(BetModelApi betModelApi) {
        BetDto betDto = betMapper.mapModelApiToDto(betModelApi);
        BetDto resultDto = betService.create(betDto);
        BetModelApi result = betMapper.mapDtoToModelApi(resultDto);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BetListModelApi> getBets(@Valid @RequestParam(required = false, defaultValue="id") String sort,
                                                   @Valid @RequestParam(required = false, defaultValue="desc") String order,
                                                   @Valid @RequestParam(required = false, defaultValue="50") Long limit,
                                                   @Valid @RequestParam(required = false, defaultValue="0") Integer page) {

        SearchDto searchDto = SearchDto.builder()
                .sort(sort)
                .order(order)
                .limit(limit)
                .page(page)
                .build();

        FindResultDto<BetDto> resultDto = betService.find(searchDto);
        BetListModelApi result = betListMapper.mapDtoToModelApi(resultDto);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}