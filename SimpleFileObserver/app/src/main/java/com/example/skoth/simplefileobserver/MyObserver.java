package com.example.skoth.simplefileobserver;

import android.os.Environment;
import android.os.FileObserver;
import android.support.annotation.Nullable;

import java.io.File;

/**
 * Created by skoth on 17-04-2020.
 */

public class MyObserver extends FileObserver {


    public MyObserver(String path , int mask) {
        super(path, mask);

    }

    @Override
    public void startWatching() {
        super.startWatching();
    }

    @Override
    public void stopWatching() {
        super.stopWatching();
    }

    @Override
    public void onEvent(int event, @Nullable String path) {

    }
}
