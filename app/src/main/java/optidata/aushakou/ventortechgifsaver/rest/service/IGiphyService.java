package optidata.aushakou.ventortechgifsaver.rest.service;

import io.reactivex.rxjava3.core.Observable;
import optidata.aushakou.ventortechgifsaver.model.GifObjectModel;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IGiphyService {

    @GET("gifs/trending")
    Observable<GifObjectModel> getGifsList(@Query("api_key") String apiKey);

    @GET("gifs/search")
    Observable<GifObjectModel> searchTheGif(@Query("api_key") String apiKey, @Query("q") String searchString);
}
