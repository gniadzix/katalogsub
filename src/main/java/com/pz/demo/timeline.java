package com.pz.demo;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yusuke Yamamoto - yusuke at mac.com
 * @since Twitter4J 2.1.7
 */
public class timeline {
    /**
     * Usage: java twitter4j.examples.timeline.GetUserTimeline
     *
     * @param args String[]
     */
    public static void main(String[] args) {

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey("fn6CqDgi6hRokpZPjw5NdkaSp");
        cb.setOAuthConsumerSecret("0vqpuy8gcIT666TKaf7sfsxctqG9LI0mCwfeJaxhS2KdLU9cVO");
        cb.setOAuthAccessToken("965953184035475457-T8Iw3aowky2FXRKKelNZ6AThcoXb1Am");
        cb.setOAuthAccessTokenSecret("RTxDK57ZTNXpfcOIIeCtSAQkJqFT6e3j3ge3JpELXMP3c");
        Twitter twitter = new TwitterFactory(cb.build()).getInstance();



        List<Status> statusList = null;

        try {
            statusList = twitter.getUserTimeline("@WWE");
        } catch (TwitterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (Status status : statusList) {
            System.out.println(status.toString());
        }
    }}