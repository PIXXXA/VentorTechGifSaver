package optidata.aushakou.ventortechgifsaver.db.dao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import optidata.aushakou.ventortechgifsaver.db.entity.FavoriteGifsEntity;
import optidata.aushakou.ventortechgifsaver.model.GifsDataModel;
import optidata.aushakou.ventortechgifsaver.model.OriginalModel;

@Dao
public interface FavoriteGifsDao {

    @Query("SELECT url FROM favourite_table")
    List<OriginalModel> getFavouriteGifs();

    @Insert
    void addFavouriteGif(FavoriteGifsEntity favoriteGifsEntity);

}
