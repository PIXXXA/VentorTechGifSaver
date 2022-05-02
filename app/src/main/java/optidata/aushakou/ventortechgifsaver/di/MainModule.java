package optidata.aushakou.ventortechgifsaver.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import optidata.aushakou.ventortechgifsaver.rest.service.IGiphyService;
import retrofit2.Retrofit;

@Module
@InstallIn(SingletonComponent.class)
public class MainModule {

    @Provides
    @Singleton
    public Context provideContext(@ApplicationContext Context applicationContext) {
        return applicationContext;
    }
}
