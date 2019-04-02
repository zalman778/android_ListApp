package com.hwx.listApplication.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.hwx.listApplication.R;
import com.hwx.listApplication.adapter.FilmSimpleAdapter;
import com.hwx.listApplication.databinding.ActivityMainBinding;
import com.hwx.listApplication.viewModel.MainViewModel;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {

    private ActivityMainBinding activityMainBinding;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();

        activityMainBinding.listFilms.setLayoutManager(new LinearLayoutManager(this));
        activityMainBinding.listFilms.setAdapter(new FilmSimpleAdapter());

        mainViewModel.addObserver(this);

    }



    private void initDataBinding() {
        mainViewModel = new MainViewModel(this);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setMainViewModel(mainViewModel);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof MainViewModel) {
            FilmSimpleAdapter filmSimpleAdapter = (FilmSimpleAdapter) activityMainBinding.listFilms.getAdapter();
            MainViewModel mainViewModel = (MainViewModel) o;
            filmSimpleAdapter.setFilmSimpleList(mainViewModel.getFilmSimpleList());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainViewModel.reset();
        activityMainBinding.listFilms.setAdapter(null);
        activityMainBinding = null;
    }
}
