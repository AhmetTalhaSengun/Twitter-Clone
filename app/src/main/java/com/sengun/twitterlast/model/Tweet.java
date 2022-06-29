package com.sengun.twitterlast.model;

public class Tweet {

    public String email;
    public String tweet;
    public String downloadUrl;

    public Tweet(String email, String tweet, String downloadUrl){
        this.email =email;
        this.tweet =tweet;
        this.downloadUrl=downloadUrl;
    }
}
