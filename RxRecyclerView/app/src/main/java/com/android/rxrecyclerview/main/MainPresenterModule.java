package com.android.rxrecyclerview.main;


import dagger.Module;
import dagger.Provides;


@Module
public class MainPresenterModule {
    private final MainContract.View mView;

    public MainPresenterModule(MainContract.View mView) {
        this.mView = mView;
    }
    @Provides
    MainContract.View provideMainContractView(){
        return mView;
    }

}
