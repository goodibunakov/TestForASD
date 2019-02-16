package ru.goodibunakov.testforasd.api;


import retrofit2.Call;
import retrofit2.http.GET;
import ru.goodibunakov.testforasd.model.Rss;

public interface ApiService {

    @GET("rss/news")
    Call<Rss> getNewsItems();
}
