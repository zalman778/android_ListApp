package com.hwx.listApplication.viewModel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;

import com.hwx.listApplication.AppController;
import com.hwx.listApplication.Configuration;
import com.hwx.listApplication.R;
import com.hwx.listApplication.model.FilmSimple;
import com.hwx.listApplication.model.ObjectListResponse;
import com.hwx.listApplication.service.FilmService;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainViewModel extends Observable {

    public ObservableInt progressBar;
    public ObservableInt statusLabelVisibility;
    public ObservableInt objectsRecyclerVisibility;
    public ObservableField<String> statusLabelText;

    public List<FilmSimple> getFilmSimpleList() {
        return filmSimpleList;
    }

    private List<FilmSimple> filmSimpleList;
    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MainViewModel(Context context) {
        this.context = context;
        this.filmSimpleList = new ArrayList<>();
        progressBar = new ObservableInt(View.GONE);
        statusLabelVisibility = new ObservableInt(View.VISIBLE);
        objectsRecyclerVisibility = new ObservableInt(View.GONE);
        statusLabelText = new ObservableField<>(context.getString(R.string.init_text));
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
        AppController appController = AppController.create(context);
        FilmService filmService = appController.getFilmService();



        Disposable disposable = filmService.fetchFilmsList(Configuration.getBaseUrlList(1))
                .subscribeOn(appController.subscribeScheduler())
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
                    statusLabelText.set(context.getString(R.string.update_error));
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
        context = null;
    }

    private void unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }


}
