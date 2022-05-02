package optidata.aushakou.ventortechgifsaver.ui.gifsfragment;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import optidata.aushakou.ventortechgifsaver.db.dao.FavoriteGifsDao;
import optidata.aushakou.ventortechgifsaver.db.entity.FavoriteGifsEntity;
import optidata.aushakou.ventortechgifsaver.db.repository.DbRepository;
import optidata.aushakou.ventortechgifsaver.model.GifsDataModel;
import optidata.aushakou.ventortechgifsaver.model.OriginalModel;
import optidata.aushakou.ventortechgifsaver.rest.servicerepository.GiphyRepository;
import optidata.aushakou.ventortechgifsaver.rest.service.IGiphyService;

@HiltViewModel
public class HomeViewModel extends ViewModel {

    MutableLiveData<List<GifsDataModel>> gifsList;
    MutableLiveData<List<OriginalModel>> favouriteGifsList;
    MutableLiveData<FavoriteGifsEntity> favoriteGifsEntity;
    MutableLiveData<Boolean> favouriteGifStatus;
    MutableLiveData<Boolean> progressStatus;

    @Inject
    IGiphyService giphyService;
    @Inject
    FavoriteGifsDao favoriteGifsDao;

    @Inject
    public HomeViewModel() {
        this.gifsList = new MutableLiveData();
        this.favouriteGifsList = new MutableLiveData();
        this.progressStatus = new MutableLiveData();
        this.favoriteGifsEntity = new MutableLiveData();
        this.favouriteGifStatus = new MutableLiveData();
    }

    public void getGifsData() {
        progressStatus.setValue(true);
        GiphyRepository giphyRepository = new GiphyRepository(giphyService);
        giphyRepository.getGifsData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> gifsList.setValue(result.getType()),
                        error -> Log.e("TAG", "getPokemons: " + error.getMessage()));
        progressStatus.setValue(false);
    }

    public void searchGif(String searchField) {
        progressStatus.setValue(true);
        GiphyRepository giphyRepository = new GiphyRepository(giphyService);
        giphyRepository.searchTheGif(searchField)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> gifsList.setValue(result.getType()),
                        error -> Log.e("TAG", "getPokemons: " + error.getMessage()));
        progressStatus.setValue(false);
    }

    public void insertFavouriteGif(FavoriteGifsEntity favoriteGifsEntity) {
        DbRepository dbRepository = new DbRepository(favoriteGifsDao);
        dbRepository.insertFavouriteGif(favoriteGifsEntity);
    }

    public void removeFromFavouriteGif(FavoriteGifsEntity favoriteGifsEntity) {
        DbRepository dbRepository = new DbRepository(favoriteGifsDao);
        dbRepository.removeFromFavouriteGif(favoriteGifsEntity);
    }

    public void getFavouriteGifs() {
        DbRepository dbRepository = new DbRepository(favoriteGifsDao);
        favouriteGifsList.setValue(dbRepository.getFavouriteGifs());
    }
}