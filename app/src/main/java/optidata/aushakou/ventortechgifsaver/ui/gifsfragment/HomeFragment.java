package optidata.aushakou.ventortechgifsaver.ui.gifsfragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import optidata.aushakou.ventortechgifsaver.R;
import optidata.aushakou.ventortechgifsaver.model.GifsDataModel;
import optidata.aushakou.ventortechgifsaver.model.OriginalModel;
import optidata.aushakou.ventortechgifsaver.ui.gifsfragment.adapter.GifsRecyclerViewAdapter;

@AndroidEntryPoint
public class HomeFragment extends Fragment {

    private GifsRecyclerViewAdapter gifsRecyclerViewAdapter;
    public HomeViewModel homeViewModel;

    public Toolbar toolbar;
    public EditText searchField;
    public ToggleButton favouriteButton;
    public RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gifs_list_fragment, container, false);
        initView(view);
        setInitMethods();
        return view;
    }

    private void initView(View view) {
        toolbar = view.findViewById(R.id.gif_toolbar);
        searchField = toolbar.findViewById(R.id.search_field);
        favouriteButton = toolbar.findViewById(R.id.favorite_button);
        recyclerView = view.findViewById(R.id.gifs_recycler_view);
    }

    private void setInitMethods() {
        initViewModel();
        initToolbar();
        initRecyclerView();
    }

    private void initViewModel() {
        homeViewModel.gifsList.observe(getViewLifecycleOwner(), gifsList -> {
            if (gifsList != null) {
                setRecyclerViewAdapter(separateGifsRequestModel(gifsList), homeViewModel);
            } else {
                Toast.makeText(getContext(), getString(R.string.error_message), Toast.LENGTH_LONG).show();
            }
        });

        homeViewModel.favouriteGifsList.observe(getViewLifecycleOwner(), favouriteList -> {
            setRecyclerViewAdapter(favouriteList, homeViewModel);
        });

        homeViewModel.getGifsData();
    }

    private List<OriginalModel> separateGifsRequestModel(List<GifsDataModel> gifsList) {
        List<OriginalModel> originalModelList = new ArrayList<OriginalModel>();
        for (int i = 0; i < gifsList.size(); i++) {
            OriginalModel originalModel = new OriginalModel(gifsList.get(i).getImages().getOriginal().getUrl());
            originalModelList.add(i, originalModel);
        }
        return originalModelList;
    }

    private void setRecyclerViewAdapter(List<OriginalModel> gifsDataModels, HomeViewModel homeViewModel) {
        gifsRecyclerViewAdapter.setGifsListModels(gifsDataModels, homeViewModel);
        gifsRecyclerViewAdapter.notifyDataSetChanged();
    }

    private void initToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        searchListener();
        favouriteListener();
    }

    private void initRecyclerView() {
        gifsRecyclerViewAdapter = new GifsRecyclerViewAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(gifsRecyclerViewAdapter);
    }

    private void searchListener() {
        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (searchField.getText().toString().equals(getString(R.string.empty_string))) {
                    homeViewModel.getGifsData();
                } else {
                    homeViewModel.searchGif(searchField.getText().toString());
                }
            }
        });
    }

    private void favouriteListener() {
        favouriteButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    homeViewModel.getFavouriteGifs();
                } else {
                    homeViewModel.getGifsData();
                }
            }
        });
    }
}