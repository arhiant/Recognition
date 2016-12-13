package com.aws;

public class Main {
    public static void main(String[] args) {
        try{
            DetectLabelsExample.detectLabels(args);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
