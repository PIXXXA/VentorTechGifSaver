package optidata.aushakou.ventortechgifsaver.rest.servicerepository;

import io.reactivex.rxjava3.core.Observable;
import optidata.aushakou.ventortechgifsaver.BuildConfig;
import optidata.aushakou.ventortechgifsaver.model.GifObjectModel;
import optidata.aushakou.ventortechgifsaver.rest.service.IGiphyService;

public class GiphyRepository {

    private IGiphyService giphyService;

    public GiphyRepository(IGiphyService giphyService) {
        this.giphyService = giphyService;
    }

    public Observable<GifObjectModel> getGifsData(){
        return giphyService.getGifsList(BuildConfig.API_KEY);
    }

    public Observable<GifObjectModel> searchTheGif(String searchField){
        return giphyService.searchTheGif(BuildConfig.API_KEY, searchField);
    }
}
