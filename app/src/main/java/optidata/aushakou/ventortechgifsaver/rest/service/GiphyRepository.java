package optidata.aushakou.ventortechgifsaver.rest.service;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import optidata.aushakou.ventortechgifsaver.BuildConfig;
import optidata.aushakou.ventortechgifsaver.model.GifObjectModel;
import optidata.aushakou.ventortechgifsaver.model.GifsDataModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GiphyRepository {

    private IGiphyService giphyService;

    public GiphyRepository(IGiphyService giphyService) {
        this.giphyService = giphyService;
    }

    public void getGifsData(MutableLiveData<List<GifsDataModel>> gifsListModel) {
        Call<GifObjectModel> call = giphyService.getGifsList(BuildConfig.API_KEY);
        call.enqueue(new Callback<GifObjectModel>() {
            @Override
            public void onResponse(Call<GifObjectModel> call, Response<GifObjectModel> response) {
                if (response.isSuccessful()){
                    gifsListModel.postValue(response.body().getType());
                } else {
                    gifsListModel.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<GifObjectModel> call, Throwable t) {
                Log.d("ERROR_MESSAGE", t.getMessage());
            }
        });
    }
}
