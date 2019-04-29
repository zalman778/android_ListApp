package com.hwx.listApplication.viewModel;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.view.View;

import com.hwx.listApplication.Configuration;
import com.hwx.listApplication.model.FilmSimple;
import com.hwx.listApplication.model.ObjectListResponse;
import com.hwx.listApplication.service.ApiFactory;
import com.hwx.listApplication.service.FilmService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class MainViewModel extends ViewModel {



    public MutableLiveData<Integer> progressBar;
    public MutableLiveData<Integer> statusLabelVisibility;
    public MutableLiveData<Integer> objectsRecyclerVisibility;
    public MutableLiveData<String> statusLabelText;

    private List<FilmSimple> filmSimpleList;

    private CompositeDisposable compositeDisposable;

    private PublishSubject subjFilmQuery;  //Observable для запроса списка фильма

    public MainViewModel() {
        subjFilmQuery = PublishSubject.create();

        filmSimpleList = new ArrayList<>();

        progressBar =  new MutableLiveData<>();
        progressBar.setValue(View.GONE);

        statusLabelVisibility = new MutableLiveData<>();
        statusLabelVisibility.setValue(View.VISIBLE);

        objectsRecyclerVisibility = new MutableLiveData<>();
        objectsRecyclerVisibility.setValue(View.GONE);

        compositeDisposable = new CompositeDisposable();

        statusLabelText = new MutableLiveData<>();
        statusLabelText.setValue("Нажмите на кнопку, чтобы получить данные");
    }

    public List<FilmSimple> getFilmSimpleList() {
        return filmSimpleList;
    }

    public PublishSubject getSubjFilmQuery() {
        return subjFilmQuery;
    }

    public void setSubjFilmQuery(PublishSubject subjFilmQuery) {
        this.subjFilmQuery = subjFilmQuery;
    }

    public void onClick(View view) {
        reloadData();
    }

    public void reloadData() {
        initializeViews();
        fetchObjectsList();
    }

    private void initializeViews() {
        statusLabelVisibility.setValue(View.GONE);
        objectsRecyclerVisibility.setValue(View.GONE);
        progressBar.setValue(View.VISIBLE);
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
                        progressBar.setValue(View.GONE);
                        statusLabelVisibility.setValue(View.GONE);
                        objectsRecyclerVisibility.setValue(View.VISIBLE);
                    }

                }, new Consumer<Throwable>() {
                @Override public void accept(Throwable throwable) throws Exception {
                    statusLabelText.setValue("Ошибка получения данных!");
                    progressBar.setValue(View.GONE);
                    statusLabelVisibility.setValue(View.VISIBLE);
                    objectsRecyclerVisibility.setValue(View.GONE);
                }
            });
        compositeDisposable.add(disposable);
    }

    private void updateFilmsSimpleDataList(List<FilmSimple> pFilmSimpleList) {
        filmSimpleList.addAll(pFilmSimpleList);
        subjFilmQuery.onNext(filmSimpleList);

    }

    public void reset() {
        //unSubscribeFromObservable();
        //compositeDisposable = null;
    }

    private void unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }


    public void onResume() {
        if (filmSimpleList.size() > 0)
            subjFilmQuery.onNext(filmSimpleList);
    }
}
