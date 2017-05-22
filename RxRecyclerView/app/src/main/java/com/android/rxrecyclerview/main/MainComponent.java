package com.android.rxrecyclerview.main;

import android.app.Activity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = MainPresenterModule.class)
public interface MainComponent {
    void inject(Activity activity);
    MainPresenter mainPresenter();
}
