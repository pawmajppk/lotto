package pl.fissst.lbd.lotto.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.fissst.lbd.lotto.dto.DrawSummaryDto;
import pl.fissst.lbd.lotto.mapper.DrawMapper;
import pl.fissst.lbd.lotto.model.Bet;
import pl.fissst.lbd.lotto.model.Draw;
import pl.fissst.lbd.lotto.repository.BetRepository;
import pl.fissst.lbd.lotto.repository.DrawRepository;
import pl.fissst.lbd.lotto.utils.LottoUtils;

import java.time.OffsetDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DrawService {

    private final BetRepository betRepository;
    private final DrawRepository drawRepository;
    private final DrawMapper drawMapper;

    @Transactional
    public DrawSummaryDto runDraw() {
        Set<Bet> openBets = betRepository.findByDrawIsNull();

        if (openBets.isEmpty()) {
            throw new RuntimeException("We don't have bets for new draw");
        }

        Draw draw = new Draw();
        draw.setCreationDate(OffsetDateTime.now());
        draw.setBets(openBets);
        draw.setNumbers(calcNumbers());

        draw.getBets().forEach(b -> b.setDraw(draw));

        Draw saved = drawRepository.save(draw);

        return getDrawSummary(saved);
    }

    private DrawSummaryDto getDrawSummary(Draw draw) {
        Map<Integer, Integer> hits = getNumbersOfHits(draw);

        return DrawSummaryDto.builder()
                .draw(drawMapper.mapEntityToDto(draw))
                .numberOf0Hits(hits.get(0))
                .numberOf1Hits(hits.get(1))
                .numberOf2Hits(hits.get(2))
                .numberOf3Hits(hits.get(3))
                .numberOf4Hits(hits.get(4))
                .numberOf5Hits(hits.get(5))
                .numberOf6Hits(hits.get(6))
                .build();
    }

    private Integer[] calcNumbers() {
        Set<Integer> result = new HashSet<>();
        Random random = new Random();

        do {
            result.add(random.ints(1, 49).findFirst().getAsInt());
        } while (result.size() != 6);

        return result.toArray(Integer[]::new);
    }

    private Map<Integer, Integer> getNumbersOfHits(Draw draw) {
        Map<Integer, Integer> results = new HashMap() {{
            put(0,0);
            put(1,0);
            put(2,0);
            put(3,0);
            put(4,0);
            put(5,0);
            put(6,0);
        }};

        draw.getBets().forEach(b -> b.getCoupons().forEach(c -> {
            Integer hit = LottoUtils.calcHits(draw.getNumbers(), c.getNumbers());
            results.replace(hit, results.get(hit) + 1);
        }));

        return results;
    }
}