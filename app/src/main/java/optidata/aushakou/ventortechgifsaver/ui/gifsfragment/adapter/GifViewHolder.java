package optidata.aushakou.ventortechgifsaver.ui.gifsfragment.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import optidata.aushakou.ventortechgifsaver.R;
import optidata.aushakou.ventortechgifsaver.model.GifsDataModel;
import optidata.aushakou.ventortechgifsaver.model.OriginalModel;

public class GifViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    CardView cardView;

    public GifViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.gif_image_view);
        cardView = itemView.findViewById(R.id.gif_card_view);
    }

    public void bind(List<GifsDataModel> gifsDataModels, int position) {
        OriginalModel gifInfo = gifsDataModels.get(position).getImages().getOriginal();

        cardView.setMinimumWidth(Integer.parseInt(gifInfo.width));
        cardView.setMinimumHeight(Integer.parseInt(gifInfo.height));

//        imageView.setMaxWidth();
//        imageView.setMaxHeight(Integer.parseInt(gifInfo.height));

        Glide.with(itemView.getContext())
                .asGif()
                .load(gifInfo.getUrl())
                .into(imageView);
    }
}