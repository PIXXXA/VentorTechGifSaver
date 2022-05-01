package optidata.aushakou.ventortechgifsaver.ui.gifsfragment;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import optidata.aushakou.ventortechgifsaver.model.GifObjectModel;
import optidata.aushakou.ventortechgifsaver.model.GifsDataModel;
import optidata.aushakou.ventortechgifsaver.rest.servicerepository.GiphyRepository;
import optidata.aushakou.ventortechgifsaver.rest.service.IGiphyService;

@HiltViewModel
public class HomeViewModel extends ViewModel {

    MutableLiveData<List<GifsDataModel>> gifsListModel;

    @Inject
    IGiphyService giphyService;

    @Inject
    public HomeViewModel() {
        this.gifsListModel = new MutableLiveData();
    }

    public MutableLiveData<List<GifsDataModel>> getGifsListData() {
        return gifsListModel;
    }

    public void getGifsData(){
        GiphyRepository giphyRepository = new GiphyRepository(giphyService);
        giphyRepository.getGifsData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> gifsListModel.setValue(result.getType()),
                        error-> Log.e("TAG", "getPokemons: " + error.getMessage() ));
    }

    public void searchGif(String searchField){
        GiphyRepository giphyRepository = new GiphyRepository(giphyService);
        giphyRepository.searchTheGif(searchField)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> gifsListModel.setValue(result.getType()),
                        error-> Log.e("TAG", "getPokemons: " + error.getMessage() ));
    }
}