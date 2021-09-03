package pl.lbd;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.internal.StaticCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.event.S3EventNotification;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.AmazonSQSException;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.util.IOUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
//import org.example.damagereport.model.BetApi;
//import org.example.damagereport.model.CouponApi;
import org.example.damagereport.model.BetApi;
import org.example.damagereport.model.CouponApi;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;


public class Lambda implements RequestHandler<S3EventNotification,String> {

    public final static String LOCALSTACK_HOSTNAME = System.getenv("LOCALSTACK_HOSTNAME");
    public final static String S3_ENDPOINT = String.format("http://%s:4566", LOCALSTACK_HOSTNAME);
    public final static String AWS_REGION = System.getenv("AWS_REGION");

    public final ObjectMapper mapper= new ObjectMapper();

    @Override
    public String handleRequest(S3EventNotification s3EventNotification, Context context) {
        // Pull the event records and get the object content type
        String bucket = s3EventNotification.getRecords().get(0).getS3().getBucket().getName();
        String key = s3EventNotification.getRecords().get(0).getS3().getObject().getKey();

        S3Object obj = prepareS3().getObject(new GetObjectRequest(bucket, key));
        boolean properFile=false;
        try (InputStream stream = obj.getObjectContent()) {
            String s= IOUtils.toString(stream);
            System.out.println(s);
            if(filter(s))
            {
                BetApi bet=init(s);
                System.out.println(bet);
                String json = mapper.writeValueAsString(bet);
               sendMessage(json);
            }
            sendToBucket(key,s,filter(s));

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }

    private AmazonS3 prepareS3() {
        BasicAWSCredentials credentials = new BasicAWSCredentials("temp", "temp");

        AwsClientBuilder.EndpointConfiguration config =
                new AwsClientBuilder.EndpointConfiguration(S3_ENDPOINT, AWS_REGION);

        AmazonS3ClientBuilder builder = AmazonS3ClientBuilder.standard();
        builder.withEndpointConfiguration(config);
        builder.withPathStyleAccessEnabled(true);
        builder.withCredentials(new AWSStaticCredentialsProvider(credentials));
        return builder.build();
    }
    public void sendToBucket(String key,String content,boolean success_fail)
    {
        AmazonS3 s3=AWSClient.getAmazonS3Client();
        if(success_fail)s3.copyObject("lbdbucket",key, "goods", key);
            else s3.copyObject("lbdbucket",key, "bads", key);
    }
    private void sendMessage(String json)
    {
        //
         AmazonSQS sqs = AWSClient.getAmazonSQSClient();

        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl(sqs.getQueueUrl("kolejka").getQueueUrl())
                .withMessageBody(json);
        sqs.sendMessage(send_msg_request);
        //sqs.sendMessage("http://localhost:4566/000000000000/kolejka",json);
    }
//    private void sendMessage(String json)
//    {
//        //
//        AmazonSQSAsync sqs = AWSClient.getAmazonSQSAsyncClient();
//
//       SendMessageRequest send_msg_request = new SendMessageRequest()
//               .withQueueUrl("http://localhost:4566/000000000000/kolejka")
//                .withMessageBody(json)
//                .withDelaySeconds(5);
//        sqs.sendMessage(send_msg_request);
//      //  sqs.sendMessage("http://localhost:4566/000000000000/kolejka",json);
//    }
    public boolean filter(String file)
    {
        List<String> lines=getLines(file);
        System.out.println("ILOSC LINI TO:  "+lines.size());
        if(lines.get(1).equals("zakłady:")) {
            for (String s:lines.subList(2,lines.size())) {
                Set<Integer> lst = Arrays.stream(s.trim().split(","))
                                .map(Integer::parseInt).filter(i->i>1&&i<49)
                                .collect(Collectors.toSet());
                if(lst.size()!=6)return false;
            }
        }
        else return false;
        return true;
    }

    public List<String> getLines(String s)
    {
        List<String> lista = new ArrayList<>();
        Scanner scanner = new Scanner(s);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            lista.add(line);
        }
        return lista;
    }

    BetApi init(String s)
    {
        BetApi api = new BetApi();
        List<CouponApi> coupons= getNestedList(s)
                .stream()
                .map(k->
                {
                    CouponApi couponApi=new CouponApi();
                    couponApi.numbers(k);
                    return couponApi;
                }).collect(Collectors.toList());
        api.setCoupons(coupons);
        return api;
    }
    Set<Set<Integer>> getNestedList(String s)
    {
        List<String> list = getLines(s);

        list=list.subList(2,list.size());
        Set<Set<Integer>> output =list.stream()
                .map(e-> Arrays.stream(e.split(","))
                        .map(Integer::new)
                        .collect(Collectors.toSet()))
                .collect(Collectors.toSet());
        return output;
    }
}
