package ru.goodibunakov.testforasd.api;

import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

class XmlService {

    private static OkHttpClient generateHttpClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        return httpClient.build();
    }

    static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl("https://lenta.ru/")
                    .addConverterFactory(SimpleXmlConverterFactory.createNonStrict(
                            new Persister(new AnnotationStrategy())))
                    .client(generateHttpClient());
}
