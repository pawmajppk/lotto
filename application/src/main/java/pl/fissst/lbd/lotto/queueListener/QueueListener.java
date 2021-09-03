package pl.fissst.lbd.lotto.queueListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;
import pl.fissst.lbd.lotto.dto.BetDto;
import pl.fissst.lbd.lotto.mapper.BetMapper;
import pl.fissst.lbd.lotto.model.BetModelApi;
import pl.fissst.lbd.lotto.service.BetService;

@Component
public class QueueListener {

    @Autowired
    BetMapper betMapper;
    @Autowired
    BetService betService;

    @SqsListener(value = "lotto.bet", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void listen(BetModelApi betModelApi) {

        System.out.println("ESsa");
        BetDto betDto = betMapper.mapModelApiToDto(betModelApi);

        BetDto resultDto = betService.create(betDto);
        System.out.println(betDto);
        BetModelApi result = betMapper.mapDtoToModelApi(resultDto);
    }

}
