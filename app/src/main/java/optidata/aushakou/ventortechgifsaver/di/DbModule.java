package optidata.aushakou.ventortechgifsaver.di;

import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import optidata.aushakou.ventortechgifsaver.db.AppDatabase;
import optidata.aushakou.ventortechgifsaver.db.dao.FavoriteGifsDao;
import optidata.aushakou.ventortechgifsaver.db.repository.DbRepository;

@Module
@InstallIn(SingletonComponent.class)
public class DbModule {

    @Singleton
    @Provides
    public AppDatabase provideRetrofitInstance(@ApplicationContext Context context
    ) {
        return Room.databaseBuilder(context, AppDatabase.class, DB_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Singleton
    @Provides
    public static FavoriteGifsDao provideFavoriteGifsDao(AppDatabase appDatabase) {
        return appDatabase.favoriteGifsDao();
    }

    public static final String DB_NAME = "Gifs Database";
}
