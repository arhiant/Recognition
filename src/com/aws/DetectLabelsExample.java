package com.aws;

/**
 * Created by Anna on 11.12.2016.
 */

import com.amazonaws.services.rekognition.AmazonRekognitionClient;
import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.rekognition.model.AmazonRekognitionException;
import com.amazonaws.services.rekognition.model.DetectLabelsRequest;
import com.amazonaws.services.rekognition.model.DetectLabelsResult;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.S3Object;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DetectLabelsExample {
    public static void detectLabels(String[] args) throws Exception {

        AWSCredentials credentials;
        try {
            credentials = new ProfileCredentialsProvider().getCredentials();
        } catch (Exception e) {
            throw new AmazonClientException("Cannot load the credentials from the credential profiles file. "
                    + "Please make sure that your credentials file is at the correct "
                    + "location (/Users/<userid>/.aws/credentials), and is in a valid format.", e);
        }

        DetectLabelsRequest request = new DetectLabelsRequest()
                .withImage(new Image()
                        .withS3Object(new S3Object()
                                .withName("IMG_3149.JPG")
                                .withBucket("arhiant-bucket")))
                .withMaxLabels(10)
                .withMinConfidence(77F);

        AmazonRekognitionClient rekognitionClient = new AmazonRekognitionClient(credentials)
                .withEndpoint("https://rekognition.eu-west-1.amazonaws.com");
        rekognitionClient.setSignerRegionOverride("eu-west-1");
        try {
            DetectLabelsResult result = rekognitionClient.detectLabels(request);
            ObjectMapper objectMapper = new ObjectMapper();
            System.out.println("Result = " + objectMapper.writeValueAsString(result));
        } catch (AmazonRekognitionException e) {
            e.printStackTrace();
        }

    }
}

