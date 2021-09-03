package pl.lbd;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsAsyncClientBuilder;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sqs.*;

public class AWSClient {

    public final static String LOCALSTACK_HOSTNAME = System.getenv("LOCALSTACK_HOSTNAME");
    public final static String S3_ENDPOINT = String.format("http://%s:4566", LOCALSTACK_HOSTNAME);
    public final static String AWS_REGION = System.getenv("AWS_REGION");
    public static AmazonS3 getAmazonS3Client()
    {
        BasicAWSCredentials credentials = new BasicAWSCredentials("temp", "temp");

        AwsClientBuilder.EndpointConfiguration config =
                new AwsClientBuilder.EndpointConfiguration(S3_ENDPOINT, AWS_REGION);

        return AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(config)
                .withPathStyleAccessEnabled(true)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }
    public static AmazonSQS getAmazonSQSClient()
    {
        BasicAWSCredentials credentials = new BasicAWSCredentials("temp", "temp");

        AwsClientBuilder.EndpointConfiguration config =
                new AwsClientBuilder.EndpointConfiguration(S3_ENDPOINT, AWS_REGION);

        return AmazonSQSClientBuilder.standard()
                .withEndpointConfiguration(config)

                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }


    public static AmazonSQSAsync getAmazonSQSAsyncClient()
    {
        BasicAWSCredentials credentials = new BasicAWSCredentials("temp", "temp");

        AwsAsyncClientBuilder.EndpointConfiguration config =
                new AwsAsyncClientBuilder.EndpointConfiguration(S3_ENDPOINT, AWS_REGION);

        return AmazonSQSAsyncClientBuilder.standard()
                .withEndpointConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials)).build();

    }
}
