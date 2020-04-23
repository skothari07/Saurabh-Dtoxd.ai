package com.example.skoth.simplefileobserver;

import android.os.Environment;
import android.os.FileObserver;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by skoth on 17-04-2020.
 */

public class MyObserver extends FileObserver {

    List<SingleFileObserver> allObservers;
    String allPath;
    int allMask;

    public MyObserver(String path) {
        this(path, FileObserver.ALL_EVENTS);
    }

    public MyObserver(String path, int mask) {
        super(path, mask);
        allPath = path;
        allMask = mask;
    }


    @Override
    public void startWatching() {
        super.startWatching();
        if (allObservers != null) return;

        allObservers = new ArrayList<SingleFileObserver>();
        Stack<String> stack = new Stack<String>();
        stack.push(allPath);
        Log.i("STACK", String.valueOf(stack));

        while (!stack.isEmpty()) {
            String parent = stack.pop();
            allObservers.add(new SingleFileObserver(parent, allMask));
            File path = new File(parent);
            File[] files = path.listFiles();
            if (null == files) continue;
            for (File f : files) {
                if (f.isDirectory() && !f.getName().equals(".") && !f.getName().equals("..")) {
                    stack.push(f.getPath());
                }
            }
        }

        for (SingleFileObserver sfo : allObservers) {
            sfo.startWatching();
        }
    }

    @Override
    public void stopWatching() {
        super.stopWatching();
        if (allObservers == null) return;

        for (SingleFileObserver sfo : allObservers) {
            sfo.stopWatching();
        }
        allObservers.clear();
        allObservers = null;
    }

    @Override
    public void onEvent(int event, @Nullable String path) {
        switch(event){
            case FileObserver.CREATE:
                Log.d("RecursiveFileObserver", "CREATE:" + path);
                MainActivity.myArrayList.add("CREATE:" + path);
                break;
            case FileObserver.DELETE:
                Log.d("RecursiveFileObserver", "DELETE:" + path);
                MainActivity.myArrayList.add("DELETE:" + path);
                break;
            case FileObserver.DELETE_SELF:
                Log.d("RecursiveFileObserver", "DELETE_SELF:" + path);
                MainActivity.myArrayList.add("DELETE_SELF:" + path);
                break;
            case FileObserver.MOVED_FROM:
                Log.d("RecursiveFileObserver", "MOVED_FROM:" + path);
                MainActivity.myArrayList.add("MOVED_FROM:" + path);
                break;
            case FileObserver.MOVED_TO:
                Log.d("RecursiveFileObserver", "MOVED_TO:" + path);
                MainActivity.myArrayList.add("MOVED_TO:" + path);
                break;
            case FileObserver.MOVE_SELF:
                Log.d("RecursiveFileObserver", "MOVE_SELF:" + path);
                MainActivity.myArrayList.add("MOVED_SELF:" + path);
                break;
            default:
                // just ignore
                break;
        }
    }
    /**
     * Monitor single directory and dispatch all events to its parent, with full path.
     */
    class SingleFileObserver extends FileObserver {
        String allPath;

        public SingleFileObserver(String path) {
            this(path, ALL_EVENTS);
            allPath = path;
        }

        public SingleFileObserver(String path, int mask) {
            super(path, mask);
            allPath = path;
        }

        @Override
        public void onEvent(int event, String path) {
            String newPath = allPath + "/" + path;
            MyObserver.this.onEvent(event, newPath);
        }
    }


}
