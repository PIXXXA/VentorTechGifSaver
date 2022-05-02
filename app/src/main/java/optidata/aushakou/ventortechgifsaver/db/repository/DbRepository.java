package optidata.aushakou.ventortechgifsaver.db.repository;

import java.util.List;

import javax.inject.Inject;

import optidata.aushakou.ventortechgifsaver.db.dao.FavoriteGifsDao;
import optidata.aushakou.ventortechgifsaver.db.entity.FavoriteGifsEntity;
import optidata.aushakou.ventortechgifsaver.model.OriginalModel;

public class DbRepository {

    private FavoriteGifsDao favoriteGifsDao;

    @Inject
    public DbRepository(FavoriteGifsDao favoriteGifsDao) {
        this.favoriteGifsDao = favoriteGifsDao;
    }

    public void insertFavouriteGif(FavoriteGifsEntity favoriteGifsEntity) {
        favoriteGifsDao.addFavouriteGif(favoriteGifsEntity);
    }

    public void removeFromFavouriteGif(String url) {
        favoriteGifsDao.removeFromFavouriteGif(url);
    }

    public List<OriginalModel> getFavouriteGifs() {
        return favoriteGifsDao.getFavouriteGifs();
    }
}
