package com.skybox.seven.senpai;


import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class SenpaiApplication extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return null;
    }
}
