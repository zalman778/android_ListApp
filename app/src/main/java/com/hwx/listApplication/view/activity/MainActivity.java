package com.hwx.listApplication.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.hwx.listApplication.R;
import com.hwx.listApplication.adapter.FilmSimpleAdapter;
import com.hwx.listApplication.databinding.ActivityMainBinding;
import com.hwx.listApplication.model.FilmDetail;
import com.hwx.listApplication.model.FilmSimple;
import com.hwx.listApplication.viewModel.MainViewModel;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

public class MainActivity extends AppCompatActivity implements Observer {

    private ActivityMainBinding activityMainBinding;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();

        activityMainBinding.listFilms.setLayoutManager(new LinearLayoutManager(this));
        PublishSubject<FilmDetail> publishSubject = PublishSubject.create();

        publishSubject.subscribeActual(new io.reactivex.Observer<FilmDetail>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(FilmDetail filmDetail) {
               startActivity(FilmDetailActivity.fillDetail(MainActivity.this, filmDetail));
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        FilmSimpleAdapter filmSimpleAdapter = new FilmSimpleAdapter(publishSubject, this);
        activityMainBinding.listFilms.setAdapter(filmSimpleAdapter);

        mainViewModel.getPublishSubject().subscribeActual(this);

    }



    private void initDataBinding() {
        mainViewModel = new MainViewModel();
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setLifecycleOwner(this);
        activityMainBinding.setMainViewModel(mainViewModel);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainViewModel.reset();
        activityMainBinding.listFilms.setAdapter(null);
        activityMainBinding = null;
    }

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onNext(Object o) {
        FilmSimpleAdapter filmSimpleAdapter = (FilmSimpleAdapter) activityMainBinding.listFilms.getAdapter();
        filmSimpleAdapter.setFilmSimpleList((List<FilmSimple>)o);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
