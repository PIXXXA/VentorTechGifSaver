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

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import optidata.aushakou.ventortechgifsaver.R;
import optidata.aushakou.ventortechgifsaver.db.entity.FavoriteGifsEntity;
import optidata.aushakou.ventortechgifsaver.model.OriginalModel;
import optidata.aushakou.ventortechgifsaver.ui.gifsfragment.adapter.GifsRecyclerViewAdapter;
import optidata.aushakou.ventortechgifsaver.ui.gifsfragment.adapter.IFavouriteGifsListener;

@AndroidEntryPoint
public class HomeFragment extends Fragment {
    private GifsRecyclerViewAdapter gifsRecyclerViewAdapter;
    public HomeViewModel homeViewModel;

    public Toolbar toolbar;
    public EditText searchField;
    public ToggleButton favouriteButton;
    public RecyclerView recyclerView;
    public ProgressBar homeProgressBar;

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
        homeProgressBar = view.findViewById(R.id.home_progress_bar);
    }

    private void setInitMethods() {
        initViewModel();
        initToolbar();
        initRecyclerView();
    }

    private void initToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        searchListener();
        favouriteListener();
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
                if (searchField.getText().toString().equals("")) {
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
                    homeViewModel.getGifsData();
                } else {
                    homeViewModel.getFavouriteGifs();
                }
            }
        });
    }

    private void initRecyclerView() {
        gifsRecyclerViewAdapter = new GifsRecyclerViewAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(gifsRecyclerViewAdapter);
    }

    private void initViewModel() {
        homeViewModel.gifsList.observe(getViewLifecycleOwner(), gifsList -> {
            if (gifsList != null) {
                List<OriginalModel> originalModel = new ArrayList<OriginalModel>();
                for (int i = 0; i < gifsList.size(); i++) {
                    OriginalModel model = new OriginalModel(gifsList.get(i).getImages().getOriginal().getUrl());
                    originalModel.add(i, model);
                }
                gifsRecyclerViewAdapter.setGifsListModels(originalModel, homeViewModel);
                gifsRecyclerViewAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getContext(), getString(R.string.error_message), Toast.LENGTH_LONG).show();
            }
        });

        homeViewModel.favouriteGifsList.observe(getViewLifecycleOwner(), favouriteList -> {
            gifsRecyclerViewAdapter.setGifsListModels(favouriteList, homeViewModel);
            gifsRecyclerViewAdapter.notifyDataSetChanged();
        });

        homeViewModel.progressStatus.observe(getViewLifecycleOwner(), status -> {
            if (status) {
                homeProgressBar.setVisibility(View.VISIBLE);
            } else {
                homeProgressBar.setVisibility(View.GONE);
            }
        });

        homeViewModel.favoriteGifsEntity.observe(getViewLifecycleOwner(), favouriteGif -> {
            if (homeViewModel.favouriteGifStatus.getValue()) {
                homeViewModel.insertFavouriteGif(favouriteGif);
            } else {
                homeViewModel.removeFromFavouriteGif(favouriteGif);
            }
        });

        homeViewModel.getGifsData();
    }
}