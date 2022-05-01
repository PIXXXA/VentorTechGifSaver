package optidata.aushakou.ventortechgifsaver.db.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import optidata.aushakou.ventortechgifsaver.db.dao.FavoriteGifsDao;
import optidata.aushakou.ventortechgifsaver.db.entity.FavoriteGifsEntity;

public class DbRepository {

    private FavoriteGifsDao favoriteGifsDao;

    @Inject
    public DbRepository(FavoriteGifsDao favoriteGifsDao) {
        this.favoriteGifsDao = favoriteGifsDao;
    }

    public void insertPokemon(FavoriteGifsEntity favoriteGifsEntity){
        favoriteGifsDao.addFavouriteGif(favoriteGifsEntity);
    }

    public LiveData<List<FavoriteGifsEntity>> getFavoritePokemon(){
        return favoriteGifsDao.getFavouriteGifs();
    }
}
