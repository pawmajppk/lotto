package pl.fissst.lbd.lotto.component;

import com.amazonaws.services.s3.event.S3EventNotification;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.config.annotation.NotificationMessage;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import pl.fissst.lbd.lotto.dto.BetDto;
import pl.fissst.lbd.lotto.mapper.BetMapper;
import pl.fissst.lbd.lotto.model.BetModelApi;
import pl.fissst.lbd.lotto.service.BetService;

import javax.annotation.PostConstruct;

@Component
public class QueueListener {
    private final QueueMessagingTemplate queueMessagingTemplate;
    private final NotificationMessagingTemplate notificationMessagingTemplate;

    @Autowired
    AmazonSQSAsync amazonSQSAsync;

    @Autowired
    BetMapper betMapper;
    @Autowired
    BetService betService;


    public QueueListener(QueueMessagingTemplate queueMessagingTemplate,
                         NotificationMessagingTemplate notificationMessagingTemplate) {
        this.queueMessagingTemplate = queueMessagingTemplate;
        this.notificationMessagingTemplate = notificationMessagingTemplate;
    }

    @SqsListener(value = "kolejka", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)

    public void listen(BetModelApi betModelApi) {

        BetDto betDto = betMapper.mapModelApiToDto(betModelApi);

        BetDto resultDto = betService.create(betDto);
        System.out.println(betDto);
        BetModelApi result = betMapper.mapDtoToModelApi(resultDto);
    }
    @PostConstruct void  createQue()
    {

        amazonSQSAsync.createQueueAsync("kolejka");
    }
}
