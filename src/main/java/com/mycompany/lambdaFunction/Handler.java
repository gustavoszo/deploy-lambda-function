package com.mycompany.lambdaFunction;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

public class Handler implements RequestHandler<S3Event, String> {

    private final S3Client s3 = S3Client.create();

    @Override
    public String handleRequest(S3Event s3Event, Context context) {
        S3EventNotification.S3EventNotificationRecord record = s3Event.getRecords().get(0);
        String bucketName = record.getS3().getBucket().getName();
        String objectKey = record.getS3().getObject().getKey();

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(objectKey)
                .build();

        GetObjectResponse object = s3.getObject(getObjectRequest).response();

        int mb = 1024 * 1024;
        if (object.contentLength() > mb) { // Se o tamanho do objeto de upload for maior que 1mb
            System.out.println("Objeto muito grande");
            return "Objeto muito grande";
        }

        System.out.println("Objeto de tamanho ok");
        return "Objeto de tamanho ok";
    }
}
