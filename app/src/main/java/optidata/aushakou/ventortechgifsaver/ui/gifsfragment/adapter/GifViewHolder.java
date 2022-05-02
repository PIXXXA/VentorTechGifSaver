package optidata.aushakou.ventortechgifsaver.ui.gifsfragment.adapter;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import optidata.aushakou.ventortechgifsaver.R;
import optidata.aushakou.ventortechgifsaver.db.dao.FavoriteGifsDao;
import optidata.aushakou.ventortechgifsaver.db.entity.FavoriteGifsEntity;
import optidata.aushakou.ventortechgifsaver.db.repository.DbRepository;
import optidata.aushakou.ventortechgifsaver.model.GifsDataModel;
import optidata.aushakou.ventortechgifsaver.model.OriginalModel;
import optidata.aushakou.ventortechgifsaver.ui.gifsfragment.HomeFragment;
import optidata.aushakou.ventortechgifsaver.ui.gifsfragment.HomeViewModel;

public class GifViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    CardView cardView;
    ToggleButton toggleButton;

    public GifViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.gif_image_view);
        cardView = itemView.findViewById(R.id.gif_card_view);
        toggleButton = itemView.findViewById(R.id.favourite_gif_button);
    }

    public void bind(List<OriginalModel> gifsDataModels, int position, HomeViewModel homeViewModel) {
        String gifUrl = gifsDataModels.get(position).getUrl();

        Glide.with(itemView.getContext())
                .asGif()
                .load(gifUrl)
                .into(imageView);

        addToFavourite(gifUrl, homeViewModel);
    }

    public void addToFavourite(String url, HomeViewModel homeViewModel) {
        FavoriteGifsEntity favoriteGifsEntity = new FavoriteGifsEntity();
        favoriteGifsEntity.setUrl(url);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    homeViewModel.insertFavouriteGif(favoriteGifsEntity);
                } else {
                    homeViewModel.removeFromFavouriteGif(favoriteGifsEntity);
                }
            }
        });

    }
}