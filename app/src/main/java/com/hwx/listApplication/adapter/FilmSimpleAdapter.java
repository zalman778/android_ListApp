package com.hwx.listApplication.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hwx.listApplication.R;
import com.hwx.listApplication.databinding.ActivityFilmSimpleBinding;
import com.hwx.listApplication.model.FilmSimple;
import com.hwx.listApplication.viewModel.FilmSimpleViewModel;

import java.util.ArrayList;
import java.util.List;

public class FilmSimpleAdapter extends RecyclerView.Adapter<FilmSimpleAdapter.FilmSimpleViewHolder> {

    private List<FilmSimple> filmSimpleList;

    public void setFilmSimpleList(List<FilmSimple> filmSimpleList) {
        this.filmSimpleList = filmSimpleList;
        notifyDataSetChanged();
    }

    public FilmSimpleAdapter() {
        this.filmSimpleList = new ArrayList<>();
    }

    @NonNull
    @Override
    public FilmSimpleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ActivityFilmSimpleBinding filmSimpleObjectBinding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()), R.layout.activity_film_simple,viewGroup, false
        );
        return new FilmSimpleViewHolder(filmSimpleObjectBinding);

    }

    @Override
    public int getItemCount() {
        return filmSimpleList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull FilmSimpleViewHolder filmSimpleViewHolder, int i) {
        filmSimpleViewHolder.bindFilmSimple(filmSimpleList.get(i));
    }

    public static class FilmSimpleViewHolder extends RecyclerView.ViewHolder {

        ActivityFilmSimpleBinding filmSimpleObjectBinding;

        public FilmSimpleViewHolder(@NonNull ActivityFilmSimpleBinding filmSimpleObjectBinding) {
            super(filmSimpleObjectBinding.filmSimpleObject);
            this.filmSimpleObjectBinding = filmSimpleObjectBinding;
        }

        void bindFilmSimple(FilmSimple filmSimple) {
            if (filmSimpleObjectBinding.getFilmSimpleViewModel() == null) {
                filmSimpleObjectBinding.setFilmSimpleViewModel(new FilmSimpleViewModel(filmSimple, itemView.getContext()));
            } else {
                filmSimpleObjectBinding.getFilmSimpleViewModel().setFilmSimple(filmSimple);
            }
        }
    }
}
