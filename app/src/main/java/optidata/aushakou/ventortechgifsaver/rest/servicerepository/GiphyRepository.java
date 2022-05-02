package optidata.aushakou.ventortechgifsaver.rest.servicerepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import optidata.aushakou.ventortechgifsaver.BuildConfig;
import optidata.aushakou.ventortechgifsaver.R;
import optidata.aushakou.ventortechgifsaver.model.GifObjectModel;
import optidata.aushakou.ventortechgifsaver.rest.service.IGiphyService;
import optidata.aushakou.ventortechgifsaver.utils.Resources;

public class GiphyRepository {

    private IGiphyService giphyService;
    private Resources resources;

    @Inject
    public GiphyRepository(IGiphyService giphyService, Resources resources) {
        this.resources = resources;
        this.giphyService = giphyService;
    }

    public Observable<GifObjectModel> getGifsData() {
        return giphyService.getGifsList(resources.getStringById(R.string.api_key));
    }

    public Observable<GifObjectModel> searchTheGif(String searchField) {
        return giphyService.searchTheGif(resources.getStringById(R.string.api_key), searchField);
    }
}
