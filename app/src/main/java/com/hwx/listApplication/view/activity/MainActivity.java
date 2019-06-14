package com.hwx.listApplication.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.hwx.listApplication.ListApplication;
import com.hwx.listApplication.R;
import com.hwx.listApplication.adapter.FilmSimpleAdapter;
import com.hwx.listApplication.databinding.ActivityMainBinding;
import com.hwx.listApplication.model.FilmDetail;
import com.hwx.listApplication.model.FilmSimple;
import com.hwx.listApplication.viewModel.MainViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private MainViewModel mainViewModel;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private FilmSimpleAdapter filmSimpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDataBinding();

        initRecyclerViewAdapter();

        subscribePublishers();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainViewModel.onResume();
    }



    private void initDataBinding() {
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.setResourceProvider(ListApplication.get(getApplicationContext()).getResourceProvider());
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setLifecycleOwner(this);
        activityMainBinding.setMainViewModel(mainViewModel);
    }

    private void initRecyclerViewAdapter() {
        activityMainBinding.listFilms.setLayoutManager(new LinearLayoutManager(this));
        filmSimpleAdapter = new FilmSimpleAdapter(mainViewModel.getPsFilmSelected(), this);
        activityMainBinding.listFilms.setAdapter(filmSimpleAdapter);
    }

    private void subscribePublishers() {
        //слушаем события от вью модели
        compositeDisposable.add(
                mainViewModel.getPsFilmSelectedWithData()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<FilmDetail>() {
                            @Override
                            public void accept(FilmDetail filmDetail) throws Exception {
                                startActivity(FilmDetailActivity.fillDetail(MainActivity.this, filmDetail));
                            }
                        })
        );

        compositeDisposable.add(
                mainViewModel
                        .getPsFilmSimpleList()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<FilmSimple>>() {
                            @Override
                            public void accept(List<FilmSimple> filmList) throws Exception {
                                filmSimpleAdapter.setFilmSimpleList(filmList);
                            }
                        })
        );
    }






}
