package optidata.aushakou.ventortechgifsaver.ui.gifsfragment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import optidata.aushakou.ventortechgifsaver.model.gifsmodel.GifsDataModel;
import optidata.aushakou.ventortechgifsaver.rest.servicerepository.GiphyRepository;
import optidata.aushakou.ventortechgifsaver.rest.service.IGiphyService;

@HiltViewModel
public class HomeViewModel extends ViewModel {

    MutableLiveData<List<GifsDataModel>> gifsListModel;

    @Inject
    IGiphyService giphyService;

    @Inject
    public HomeViewModel() {
        this.gifsListModel = new MutableLiveData();
    }

    public MutableLiveData<List<GifsDataModel>> getLiveData(){
        return gifsListModel;
    }

    void getGifsData() {
        GiphyRepository giphyRepository = new GiphyRepository(giphyService);
        giphyRepository.getGifsData(gifsListModel);
    }
}