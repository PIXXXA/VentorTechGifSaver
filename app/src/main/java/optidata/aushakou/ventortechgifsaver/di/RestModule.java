package optidata.aushakou.ventortechgifsaver.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import optidata.aushakou.ventortechgifsaver.BuildConfig;
import optidata.aushakou.ventortechgifsaver.rest.service.IGiphyService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class RestModule {

    @Singleton
    @Provides
    public static OkHttpClient provideInterceptorOkHttpClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        } else {
            httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.NONE);
        }
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    @Singleton
    @Provides
    public static IGiphyService provideGiphyService(Retrofit retrofit) {
        return retrofit.create(IGiphyService.class);
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofitInstance(OkHttpClient okHttpClient
    ) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static final String BASE_URL = "https://api.giphy.com/v1/";
}

