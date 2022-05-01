package optidata.aushakou.ventortechgifsaver.rest.service;

import optidata.aushakou.ventortechgifsaver.model.GifObjectModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IGiphyService {

    @GET("gifs/trending")
    Call<GifObjectModel> getGifsList(@Query("api_key") String apiKey);
}
