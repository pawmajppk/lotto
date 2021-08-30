package pl.fissst.lbd.lotto.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.fissst.lbd.lotto.dto.DrawSummaryDto;
import pl.fissst.lbd.lotto.mapper.DrawSummaryMapper;
import pl.fissst.lbd.lotto.model.DrawSummaryModelApi;
import pl.fissst.lbd.lotto.service.DrawService;

@RestController
@RequiredArgsConstructor
public class DrawApiController implements DrawApi {

    private final DrawService drawService;
    private final DrawSummaryMapper drawSummaryMapper;

    @Override
    public ResponseEntity<DrawSummaryModelApi> runDraw() {
        DrawSummaryDto resultDto = drawService.runDraw();
        DrawSummaryModelApi result = drawSummaryMapper.mapDtoToModelApi(resultDto);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}