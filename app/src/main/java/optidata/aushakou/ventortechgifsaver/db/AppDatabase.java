package optidata.aushakou.ventortechgifsaver.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import optidata.aushakou.ventortechgifsaver.db.dao.FavoriteGifsDao;
import optidata.aushakou.ventortechgifsaver.db.entity.FavoriteGifsEntity;

@Database(entities = {FavoriteGifsEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FavoriteGifsDao favoriteGifsDao();
}