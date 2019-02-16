package ru.goodibunakov.testforasd.api;

import retrofit2.Retrofit;

public class ServiceGenerator {

    public static ApiService getJsonService() {
        Retrofit retrofit = XmlService.builder.build();
        return retrofit.create(ApiService.class);
    }
}
