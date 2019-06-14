package com.hwx.listApplication.adapter;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hwx.listApplication.R;
import com.hwx.listApplication.databinding.ActivityFilmSimpleBinding;
import com.hwx.listApplication.model.FilmSimple;
import com.hwx.listApplication.viewModel.FilmSimpleViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.subjects.PublishSubject;

public class FilmSimpleAdapter extends RecyclerView.Adapter<FilmSimpleAdapter.FilmSimpleViewHolder> {

    private List<FilmSimple> filmSimpleList;
    private PublishSubject<Long> psFilmSelected;
    private LifecycleOwner lifecycleOwner;


    public void setFilmSimpleList(List<FilmSimple> filmSimpleList) {
        this.filmSimpleList = filmSimpleList;
        notifyDataSetChanged();
    }

    public FilmSimpleAdapter(
              PublishSubject<Long> psFilmSelected
            , LifecycleOwner lifecycleOwner
    ) {
        this.filmSimpleList = new ArrayList<>();
        this.psFilmSelected = psFilmSelected;
        this.lifecycleOwner = lifecycleOwner;
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
        filmSimpleViewHolder.bindFilmSimple(filmSimpleList.get(i), psFilmSelected, lifecycleOwner);
    }

    public static class FilmSimpleViewHolder extends RecyclerView.ViewHolder {

        ActivityFilmSimpleBinding filmSimpleObjectBinding;

        public FilmSimpleViewHolder(@NonNull ActivityFilmSimpleBinding filmSimpleObjectBinding) {
            super(filmSimpleObjectBinding.filmSimpleObject);
            this.filmSimpleObjectBinding = filmSimpleObjectBinding;
        }

        void bindFilmSimple(
                FilmSimple filmSimple
                , final PublishSubject<Long> publishSubject
                , LifecycleOwner lifecycleOwner
        ) {
            if (filmSimpleObjectBinding.getFilmSimpleViewModel() == null) {
                FilmSimpleViewModel filmSimpleViewModel = new FilmSimpleViewModel(filmSimple);

                //собираем события о выборе фильма
                filmSimpleViewModel
                        .getUiEventLiveData()
                        .observe(lifecycleOwner, new Observer<Long>() {
                    @Override
                    public void onChanged(@Nullable Long o) {
                        publishSubject.onNext(o);
                    }
                });

                filmSimpleObjectBinding.setFilmSimpleViewModel(filmSimpleViewModel);
            } else {
                filmSimpleObjectBinding.getFilmSimpleViewModel().setFilmSimple(filmSimple);
            }
        }
    }
}
