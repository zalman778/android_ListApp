package com.hwx.listApplication;


import android.content.Context;

import com.hwx.listApplication.model.ObjectListResponse;
import com.hwx.listApplication.service.FilmService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.reactivex.subscribers.TestSubscriber;

import static org.mockito.Mockito.when;


public class ServiceTest {

    @Mock
    Context context;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void checkFilmsListJson() {
        when(context.getApplicationContext()).thenReturn(new AppController());
        AppController appController = AppController.create(context);
        FilmService filmService = appController.getFilmService();

        TestSubscriber<ObjectListResponse> testSubscriber = new TestSubscriber<>();

        Observable<ObjectListResponse> observable = filmService
                .fetchFilmsList(Configuration.getBaseUrlList(1));


        observable.toFlowable(BackpressureStrategy.DROP).subscribe(testSubscriber);

        testSubscriber.awaitTerminalEvent();

        //checking
        testSubscriber.assertComplete();
        testSubscriber.assertNoErrors();
        Assert.assertTrue("Has values in response", !testSubscriber.values().isEmpty());
    }

    @Test
    public void checkFilmDetailJson() {
        when(context.getApplicationContext()).thenReturn(new AppController());
        AppController appController = AppController.create(context);
        FilmService filmService = appController.getFilmService();

        TestSubscriber<ObjectListResponse> testSubscriber = new TestSubscriber<>();

        Observable<ObjectListResponse> observable = filmService
                .fetchFilmsList(Configuration.getMovieFullInfoUrl(166428L));


        observable.toFlowable(BackpressureStrategy.DROP).subscribe(testSubscriber);

        testSubscriber.awaitTerminalEvent();

        //checking
        testSubscriber.assertComplete();
        testSubscriber.assertNoErrors();
        Assert.assertTrue("Has values in response", !testSubscriber.values().isEmpty());
    }


}
