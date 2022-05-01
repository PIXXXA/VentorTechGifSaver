package optidata.aushakou.ventortechgifsaver.ui.favoritefragment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import optidata.aushakou.ventortechgifsaver.R;
import optidata.aushakou.ventortechgifsaver.ui.gifsfragment.HomeViewModel;

public class FavoriteGifsFragment extends Fragment {

    public FavoriteGifsViewModel favoriteGifsViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.favorite_gifs_fragment, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        favoriteGifsViewModel = new ViewModelProvider(this).get(FavoriteGifsViewModel.class);
    }
}