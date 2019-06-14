package com.hwx.listApplication.viewModel;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;
import android.view.View;

import com.hwx.listApplication.Configuration;
import com.hwx.listApplication.ResourceProvider;
import com.hwx.listApplication.model.FilmDetail;
import com.hwx.listApplication.model.FilmSimple;
import com.hwx.listApplication.model.ObjectListResponse;
import com.hwx.listApplication.service.ApiFactory;
import com.hwx.listApplication.service.FilmService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {


    private MutableLiveData<Boolean> lvIsFilmListLoading = new MutableLiveData<>();
    private MutableLiveData<Integer> lvObjectsRecyclerVisibility = new MutableLiveData<>();

    private ResourceProvider mResourceProvider;
    private FilmService filmService = ApiFactory.create();

    private List<FilmSimple> filmSimpleList = new ArrayList<>();

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private PublishSubject<List<FilmSimple>> psFilmSimpleList = PublishSubject.create();
    private PublishSubject<Long> psFilmSelected = PublishSubject.create();
    private PublishSubject<FilmDetail> psFilmSelectedWithData = PublishSubject.create();

    public void setResourceProvider(ResourceProvider mResourceProvider) {
        this.mResourceProvider = mResourceProvider;
    }

    public MainViewModel() {
        lvObjectsRecyclerVisibility.setValue(View.GONE);

        compositeDisposable.add(
            psFilmSelected
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long filmId) throws Exception {
                            requestDataByFilmId(filmId);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Log.e("AVX", "err", throwable);
                        }
                    })
        );

        //load list
        onClick();
    }

    private void requestDataByFilmId(Long filmId) {
        filmService
            .fetchFilmDetail(Configuration.getMovieFullInfoUrl(filmId))
            .enqueue(new Callback<FilmDetail>() {
                @Override
                public void onResponse(Call<FilmDetail> call, Response<FilmDetail> response) {
                     psFilmSelectedWithData.onNext(response.body());
                }

                @Override
                public void onFailure(Call<FilmDetail> call, Throwable t) {
                    Log.e("AVX", "err", t);
                }
            });
    }

    public MutableLiveData<Boolean> getLvIsFilmListLoading() {
        return lvIsFilmListLoading;
    }

    public MutableLiveData<Integer> getLvObjectsRecyclerVisibility() {
        return lvObjectsRecyclerVisibility;
    }

    public List<FilmSimple> getFilmSimpleList() {
        return filmSimpleList;
    }

    public PublishSubject<List<FilmSimple>> getPsFilmSimpleList() {
        return psFilmSimpleList;
    }

    public PublishSubject<Long> getPsFilmSelected() {
        return psFilmSelected;
    }

    public PublishSubject<FilmDetail> getPsFilmSelectedWithData() {
        return psFilmSelectedWithData;
    }

    public void onClick() {
        reloadData();
    }

    public void reloadData() {
        initializeViews();
        fetchObjectsList();
    }

    private void initializeViews() {
        lvIsFilmListLoading.setValue(false);
        lvObjectsRecyclerVisibility.setValue(View.GONE);
    }

    private void fetchObjectsList() {

        lvIsFilmListLoading.setValue(true);

        compositeDisposable.add(
            filmService
                .fetchFilmsList(Configuration.getBaseUrlList(1))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ObjectListResponse>() {
                    @Override
                    public void accept(ObjectListResponse objectListResponse) throws Exception {
                        updateFilmsSimpleDataList(objectListResponse.getFilmSimpleList());
                        lvObjectsRecyclerVisibility.setValue(View.VISIBLE);
                        lvIsFilmListLoading.setValue(false);
                    }

                }, new Consumer<Throwable>() {
                @Override public void accept(Throwable throwable) throws Exception {
                        Log.w("AVX", "err", throwable);
                        lvObjectsRecyclerVisibility.setValue(View.GONE);
                    }
                })
        );
    }

    private void updateFilmsSimpleDataList(List<FilmSimple> pFilmSimpleList) {
        filmSimpleList.addAll(pFilmSimpleList);
        psFilmSimpleList.onNext(filmSimpleList);
    }


    public void onResume() {
        if (filmSimpleList.size() > 0)
            psFilmSimpleList.onNext(filmSimpleList);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }


}
