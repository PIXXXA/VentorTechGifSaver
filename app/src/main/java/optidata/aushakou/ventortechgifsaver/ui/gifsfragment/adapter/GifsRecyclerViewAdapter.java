package optidata.aushakou.ventortechgifsaver.ui.gifsfragment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import optidata.aushakou.ventortechgifsaver.R;
import optidata.aushakou.ventortechgifsaver.model.OriginalModel;
import optidata.aushakou.ventortechgifsaver.ui.gifsfragment.HomeViewModel;

public class GifsRecyclerViewAdapter extends RecyclerView.Adapter<GifViewHolder> {

    private List<OriginalModel> gifsDataModels;
    private HomeViewModel homeViewModel;

    public void setGifsListModels(List<OriginalModel> gifsDataModels, HomeViewModel homeViewModel) {
        this.homeViewModel = homeViewModel;
        this.gifsDataModels = gifsDataModels;
    }

    @NonNull
    @Override
    public GifViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_element, parent, false);
        return new GifViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GifViewHolder holder, int position) {
        holder.bind(gifsDataModels, position, homeViewModel);
    }

    @Override
    public int getItemCount() {
        if (gifsDataModels == null) {
            return 0;
        } else {
            return gifsDataModels.size();
        }
    }

}
