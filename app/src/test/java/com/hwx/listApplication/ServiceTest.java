package com.hwx.listApplication;


import com.hwx.listApplication.model.ObjectListResponse;
import com.hwx.listApplication.service.ApiFactory;
import com.hwx.listApplication.service.FilmService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.reactivex.subscribers.TestSubscriber;


public class ServiceTest {


    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void checkFilmsListJson() {

        FilmService filmService = ApiFactory.create();

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

        FilmService filmService = ApiFactory.create();

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
