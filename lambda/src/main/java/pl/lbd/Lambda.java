package pl.lbd;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.event.S3EventNotification;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.util.IOUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
//import org.example.damagereport.model.BetApi;
//import org.example.damagereport.model.CouponApi;
import org.example.damagereport.model.BetApi;
import pl.lbd.mapper.BetApiMapper;
import pl.lbd.utils.StringLinesPareser;

import java.io.InputStream;


public class Lambda implements RequestHandler<S3EventNotification,String> {
    private final ObjectMapper mapper= new ObjectMapper();

    @Override
    public String handleRequest(S3EventNotification s3EventNotification, Context context) {
        // Pull the event records and get the object content type
        String bucket = s3EventNotification.getRecords().get(0).getS3().getBucket().getName();
        String key = s3EventNotification.getRecords().get(0).getS3().getObject().getKey();
        S3Object obj = AWSClient.getAmazonS3Client().getObject(new GetObjectRequest(bucket, key));
        boolean properFile=false;
        try (InputStream stream = obj.getObjectContent()) {
            String s= IOUtils.toString(stream);
            System.out.println(s);
            try {
                BetApi api= BetApiMapper.mapToBetApi(StringLinesPareser.getLines(s));
                sendMessage(mapper.writeValueAsString(api));
                sendToBucket(key,true,bucket);
            }
            catch (Exception e)
            {
                sendToBucket(key,false,bucket);
            }

        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
        return null;
    }

    public void sendToBucket(String key,boolean success_fail,String bucket)
    {
        AmazonS3 s3=AWSClient.getAmazonS3Client();
        if(success_fail)s3.copyObject(bucket,key,
                bucket, key.substring(0,key.length()-4)+"/success");
            else s3.copyObject("lotto.kupon",key,
                "lotto.kupon",key.substring(0,key.length()-4)+"/fail");
    }
    private void sendMessage(String json)
    {
         AmazonSQS sqs = AWSClient.getAmazonSQSClient();
        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl(sqs.getQueueUrl("lotto.bet").getQueueUrl())
                .withMessageBody(json);
        sqs.sendMessage(send_msg_request);
    }

}
