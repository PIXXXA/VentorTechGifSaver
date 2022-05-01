package optidata.aushakou.ventortechgifsaver.ui.gifsfragment;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import optidata.aushakou.ventortechgifsaver.db.entity.FavoriteGifsEntity;
import optidata.aushakou.ventortechgifsaver.db.repository.DbRepository;
import optidata.aushakou.ventortechgifsaver.model.GifsDataModel;
import optidata.aushakou.ventortechgifsaver.model.OriginalModel;
import optidata.aushakou.ventortechgifsaver.rest.servicerepository.GiphyRepository;
import optidata.aushakou.ventortechgifsaver.rest.service.IGiphyService;
import optidata.aushakou.ventortechgifsaver.ui.gifsfragment.adapter.IFavouriteGifsListener;

@HiltViewModel
public class HomeViewModel extends ViewModel implements IFavouriteGifsListener {

    MutableLiveData<List<GifsDataModel>> gifsList;
    MutableLiveData<List<OriginalModel>> favouriteGifs;

    @Inject
    IGiphyService giphyService;
    DbRepository dbRepository;

    public HomeViewModel() {
    }

    @Inject
    public HomeViewModel(DbRepository dbRepository) {
        this.dbRepository = dbRepository;
        this.gifsList = new MutableLiveData();
    }

    public void getGifsData() {
        GiphyRepository giphyRepository = new GiphyRepository(giphyService);
        giphyRepository.getGifsData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> gifsList.setValue(result.getType()),
                        error -> Log.e("TAG", "getPokemons: " + error.getMessage()));
    }

    public void searchGif(String searchField) {
        GiphyRepository giphyRepository = new GiphyRepository(giphyService);
        giphyRepository.searchTheGif(searchField)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> gifsList.setValue(result.getType()),
                        error -> Log.e("TAG", "getPokemons: " + error.getMessage()));
    }

    public void insertFavouriteGif(FavoriteGifsEntity favoriteGifsEntity) {
        dbRepository.insertFavouriteGif(favoriteGifsEntity);
    }

    public void getFavouriteGifs() {
        favouriteGifs.setValue(dbRepository.getFavouriteGifs());
    }

    @Override
    public void addFavouriteGif(FavoriteGifsEntity favoriteGifsEntity) {
        insertFavouriteGif(favoriteGifsEntity);
    }
}