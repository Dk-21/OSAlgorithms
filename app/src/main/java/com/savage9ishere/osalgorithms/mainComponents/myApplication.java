package com.savage9ishere.osalgorithms.mainComponents;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.core.os.HandlerCompat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class myApplication extends Application {
     private ExecutorService executorService = Executors.newFixedThreadPool(4);
     private Handler mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());

     public ExecutorService getExecutorService() {
          return executorService;
     }
     public Handler getMainThreadHandler() {
          return mainThreadHandler;
     }
}
