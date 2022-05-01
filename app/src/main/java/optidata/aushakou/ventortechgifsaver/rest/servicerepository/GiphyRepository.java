package optidata.aushakou.ventortechgifsaver.rest.servicerepository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import optidata.aushakou.ventortechgifsaver.BuildConfig;
import optidata.aushakou.ventortechgifsaver.model.GifObjectModel;
import optidata.aushakou.ventortechgifsaver.model.GifsDataModel;
import optidata.aushakou.ventortechgifsaver.rest.service.IGiphyService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GiphyRepository {

    private IGiphyService giphyService;

    public GiphyRepository(IGiphyService giphyService) {
        this.giphyService = giphyService;
    }

    public Observable<GifObjectModel> getGifsData(){
        return giphyService.getGifsList(BuildConfig.API_KEY);
    }

    public Observable<GifObjectModel> searchTheGif(String searchField){
        return giphyService.searchTheGif(BuildConfig.API_KEY, searchField);
    }
}
