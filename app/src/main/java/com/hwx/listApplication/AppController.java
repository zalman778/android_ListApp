package com.hwx.listApplication;

import android.app.Application;
import android.content.Context;

import com.hwx.listApplication.service.ApiFactory;
import com.hwx.listApplication.service.FilmService;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;



public class AppController extends Application {

    private FilmService filmService;
    private Scheduler scheduler;

    private static AppController get(Context context) {
        return (AppController) context.getApplicationContext();
    }

    public static AppController create(Context context) {
        return AppController.get(context);
    }

    public FilmService getFilmService() {
        if (filmService == null) {
            filmService = ApiFactory.create();
        }

        return filmService;
    }

    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }

        return scheduler;
    }

    public void setFilmService(FilmService filmService) {
        this.filmService = filmService;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

}
