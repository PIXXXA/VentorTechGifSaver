package optidata.aushakou.ventortechgifsaver.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import optidata.aushakou.ventortechgifsaver.db.entity.FavoriteGifsEntity;

@Dao
public interface FavoriteGifsDao {

    @Query("SELECT * FROM favourite_table")
    LiveData<List<FavoriteGifsEntity>> getFavouriteGifs();

    @Insert
    void addFavouriteGif(FavoriteGifsEntity favoriteGifsEntity);

}
