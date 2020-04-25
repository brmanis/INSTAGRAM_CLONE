package com.example.instagram_clone;

import android.app.Application;

import com.parse.Parse;

public class APP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("Tk0K6bLXkGk3Xvo1ld8Xdbs9wmqZjHdaD0U3PTvZ")
                // if defined
                .clientKey("pqgeFxF44CqAlJs7o1O6z9aNwVWoqVV8r8ExgfqU")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
