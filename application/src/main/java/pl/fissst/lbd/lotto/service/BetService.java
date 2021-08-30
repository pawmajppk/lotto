package pl.fissst.lbd.lotto.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.fissst.lbd.lotto.dto.BetDto;
import pl.fissst.lbd.lotto.dto.FindResultDto;
import pl.fissst.lbd.lotto.dto.SearchDto;
import pl.fissst.lbd.lotto.mapper.BetMapper;
import pl.fissst.lbd.lotto.model.Bet;
import pl.fissst.lbd.lotto.repository.BetRepository;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BetService {

    private final BetMapper betMapper;
    private final BetRepository betRepository;

    @Transactional
    public BetDto create(BetDto betDto) {
        Bet bet = betMapper.mapDtoToNewEntity(betDto);
        Bet result = betRepository.save(bet);
        return betMapper.mapEntityToDto(result);
    }

    @Transactional
    public FindResultDto<BetDto> find(SearchDto searchDto) {
        PageRequest pageRequest = PageRequest.of(searchDto.getPage(),
                searchDto.getLimit().intValue(),
                Sort.Direction.fromString(searchDto.getOrder()),
                searchDto.getSort());

        Page<Bet> page = betRepository.findAll(pageRequest);

        return FindResultDto.<BetDto>builder()
                .totalCount(page.getTotalElements())
                .count((long) page.getSize())
                .startElement(searchDto.getLimit() * searchDto.getPage())
                .results(page.getContent().stream()
                        .map(betMapper::mapEntityToDto)
                        .collect(Collectors.toList()))
                .build();
    }
}