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

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class MainActivity extends AppCompatActivity implements Observer {

    private ActivityMainBinding activityMainBinding;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();

        mainViewModel.setResourceProvider(ListApplication.get(getApplicationContext()).getResourceProvider());
        activityMainBinding.listFilms.setLayoutManager(new LinearLayoutManager(this));

        //rx
        PublishSubject<FilmDetail> publishSubject = PublishSubject.create();
        publishSubject.subscribeActual(this);

        FilmSimpleAdapter filmSimpleAdapter = new FilmSimpleAdapter(publishSubject, this);
        activityMainBinding.listFilms.setAdapter(filmSimpleAdapter);


        mainViewModel.getSubjFilmQuery().subscribeActual(this);
        activityMainBinding.executePendingBindings();

    }



    private void initDataBinding() {
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
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
        //отписка не срабатывает почему-то
        mainViewModel.getSubjFilmQuery().unsubscribeOn(Schedulers.io());

    }

    @Override
    protected void onResume() {
        super.onResume();
        mainViewModel.onResume();
    }

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onNext(Object o) {
        //обрабатываем события он момента завершения загрузки данных и от выбора фильма из списка
        //надо исправить, почему-то после повотора старая активити получает событие, а у неё уже пустой список.
        if (o instanceof List) {
            if (activityMainBinding != null) {
                FilmSimpleAdapter filmSimpleAdapter = (FilmSimpleAdapter) activityMainBinding.listFilms.getAdapter();
                filmSimpleAdapter.setFilmSimpleList((List<FilmSimple>) o);
            }
        }

        if (o instanceof FilmDetail) {

            startActivity(FilmDetailActivity.fillDetail(MainActivity.this, (FilmDetail) o));
        }

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }



}
