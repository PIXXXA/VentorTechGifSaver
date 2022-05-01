package optidata.aushakou.ventortechgifsaver.ui.gifsfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
        RecyclerView recyclerView = view.findViewById(R.id.gifs_recycler_view);
        initRecyclerView(recyclerView);
        initViewModel();
        return view;
    }

    private void initRecyclerView(RecyclerView recyclerView) {
        gifsRecyclerViewAdapter = new GifsRecyclerViewAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(gifsRecyclerViewAdapter);
    }

    private void initViewModel() {
        homeViewModel.getLiveData().observe(getViewLifecycleOwner(), gifsListModels -> {
            if (gifsListModels != null) {
                gifsRecyclerViewAdapter.setGifsListModels(gifsListModels);
                gifsRecyclerViewAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getContext(), getString(R.string.error_message), Toast.LENGTH_LONG).show();
            }
        });

        homeViewModel.getGifsData();
    }
}