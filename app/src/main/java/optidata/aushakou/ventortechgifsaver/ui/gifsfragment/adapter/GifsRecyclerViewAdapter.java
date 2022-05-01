package optidata.aushakou.ventortechgifsaver.ui.gifsfragment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import optidata.aushakou.ventortechgifsaver.R;
import optidata.aushakou.ventortechgifsaver.model.gifsmodel.GifsDataModel;

public class GifsRecyclerViewAdapter extends RecyclerView.Adapter<GifViewHolder> {

    private List<GifsDataModel> gifsDataModels;

    public void setGifsListModels(List<GifsDataModel> gifsDataModels) {
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
        holder.bind(gifsDataModels, position);
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
