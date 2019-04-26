package com.hwx.listApplication.viewModel;


import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;

import com.hwx.listApplication.Configuration;
import com.hwx.listApplication.model.FilmSimple;
import com.hwx.listApplication.model.ObjectListResponse;
import com.hwx.listApplication.service.ApiFactory;
import com.hwx.listApplication.service.FilmService;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends Observable {

    public ObservableInt progressBar;
    public ObservableInt statusLabelVisibility;
    public ObservableInt objectsRecyclerVisibility;
    public ObservableField<String> statusLabelText;

    public List<FilmSimple> getFilmSimpleList() {
        return filmSimpleList;
    }

    private List<FilmSimple> filmSimpleList;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MainViewModel() {
        this.filmSimpleList = new ArrayList<>();
        progressBar = new ObservableInt(View.GONE);
        statusLabelVisibility = new ObservableInt(View.VISIBLE);
        objectsRecyclerVisibility = new ObservableInt(View.GONE);
        statusLabelText = new ObservableField<>("Нажмите на кнопку, чтобы получить данные");
    }

    public void onClick(View view) {
        reloadData();
    }

    public void reloadData() {
        initializeViews();
        fetchObjectsList();
    }

    private void initializeViews() {
        statusLabelVisibility.set(View.GONE);
        objectsRecyclerVisibility.set(View.GONE);
        progressBar.set(View.VISIBLE);
    }

    private void fetchObjectsList() {
        FilmService filmService = ApiFactory.create();



        Disposable disposable = filmService
                .fetchFilmsList(Configuration.getBaseUrlList(1))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ObjectListResponse>() {
                    @Override
                    public void accept(ObjectListResponse objectListResponse) throws Exception {
                        updateFilmsSimpleDataList(objectListResponse.getFilmSimpleList());
                        progressBar.set(View.GONE);
                        statusLabelVisibility.set(View.GONE);
                        objectsRecyclerVisibility.set(View.VISIBLE);
                    }

                }, new Consumer<Throwable>() {
                @Override public void accept(Throwable throwable) throws Exception {
                    statusLabelText.set("Ошибка получения данных!");
                    progressBar.set(View.GONE);
                    statusLabelVisibility.set(View.VISIBLE);
                    objectsRecyclerVisibility.set(View.GONE);
                }
            });
        compositeDisposable.add(disposable);
    }

    private void updateFilmsSimpleDataList(List<FilmSimple> filmSimpleList) {
        this.filmSimpleList.addAll(filmSimpleList);
        setChanged();
        notifyObservers();
    }

    public void reset() {
        unSubscribeFromObservable();
        compositeDisposable = null;
    }

    private void unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }


}
