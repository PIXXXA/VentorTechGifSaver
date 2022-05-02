package optidata.aushakou.ventortechgifsaver.ui.gifsfragment;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import optidata.aushakou.ventortechgifsaver.R;
import optidata.aushakou.ventortechgifsaver.db.entity.FavoriteGifsEntity;
import optidata.aushakou.ventortechgifsaver.db.repository.DbRepository;
import optidata.aushakou.ventortechgifsaver.model.GifsDataModel;
import optidata.aushakou.ventortechgifsaver.model.OriginalModel;
import optidata.aushakou.ventortechgifsaver.rest.servicerepository.GiphyRepository;
import optidata.aushakou.ventortechgifsaver.utils.Resources;

@HiltViewModel
public class HomeViewModel extends ViewModel {

    MutableLiveData<List<GifsDataModel>> gifsList;
    MutableLiveData<List<OriginalModel>> favouriteGifsList;

    GiphyRepository giphyRepository;
    DbRepository dbRepository;
    Resources resources;

    @Inject
    public HomeViewModel(GiphyRepository giphyRepository, DbRepository dbRepository, Resources resources) {
        this.resources = resources;
        this.giphyRepository = giphyRepository;
        this.dbRepository = dbRepository;
        this.gifsList = new MutableLiveData();
        this.favouriteGifsList = new MutableLiveData();
    }

    public void getGifsData() {
        giphyRepository.getGifsData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> gifsList.setValue(result.getType()),
                        error -> Log.e(resources.getStringById(R.string.error_tag), resources.getStringById(R.string.error_message) + error.getMessage()));
    }

    public void searchGif(String searchField) {
        giphyRepository.searchTheGif(searchField)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> gifsList.setValue(result.getType()),
                        error -> Log.e(resources.getStringById(R.string.error_tag), resources.getStringById(R.string.error_message) + error.getMessage()));
    }

    public void insertFavouriteGif(FavoriteGifsEntity favoriteGifsEntity) {
        dbRepository.insertFavouriteGif(favoriteGifsEntity);
    }

    public void removeFromFavouriteGif(String url) {
        dbRepository.removeFromFavouriteGif(url);
    }

    public void getFavouriteGifs() {
        favouriteGifsList.setValue(dbRepository.getFavouriteGifs());
    }
}