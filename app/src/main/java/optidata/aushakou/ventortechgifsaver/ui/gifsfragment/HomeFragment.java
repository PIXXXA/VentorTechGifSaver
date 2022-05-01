package optidata.aushakou.ventortechgifsaver.ui.gifsfragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import dagger.hilt.android.AndroidEntryPoint;
import optidata.aushakou.ventortechgifsaver.R;
import optidata.aushakou.ventortechgifsaver.ui.gifsfragment.adapter.GifsRecyclerViewAdapter;

@AndroidEntryPoint
public class HomeFragment extends Fragment {
    private GifsRecyclerViewAdapter gifsRecyclerViewAdapter;
    public HomeViewModel homeViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gifs_list_fragment, container, false);
        setInitMethods(view);
        return view;
    }

    private void setInitMethods(View view) {
        initViewModel();
        initToolbar(view);
        initRecyclerView(view);
    }

    private void initToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.gif_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        searchListener(toolbar);
        favouriteListener(toolbar);
    }

    private void searchListener(Toolbar toolbar) {
        EditText searchField = toolbar.findViewById(R.id.search_field);

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

    private void favouriteListener(Toolbar toolbar) {
        ImageView favouriteButton = toolbar.findViewById(R.id.favorite_button);

        favouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeViewModel.getFavouriteGifs();
            }
        });
    }

    private void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.gifs_recycler_view);
        gifsRecyclerViewAdapter = new GifsRecyclerViewAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(gifsRecyclerViewAdapter);
    }

    private void initViewModel() {
        getGifsListObserver();
        homeViewModel.getGifsData();
    }

    private void getGifsListObserver() {
        homeViewModel.gifsList.observe(getViewLifecycleOwner(), gifsListModels -> {
            if (gifsListModels != null) {
                gifsRecyclerViewAdapter.setGifsListModels(gifsListModels);
                gifsRecyclerViewAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getContext(), getString(R.string.error_message), Toast.LENGTH_LONG).show();
            }
        });
    }
}