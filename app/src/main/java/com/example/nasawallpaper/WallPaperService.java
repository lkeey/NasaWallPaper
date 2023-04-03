package com.example.nasawallpaper;

import android.app.Service;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class WallPaperService extends Service {

    private static final String TAG = "WallPaperService";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        // сообщение о создании службы
        Toast.makeText(this, "Service created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // сообщение о запуске службы
        Toast.makeText(this, "Service started", Toast.LENGTH_SHORT).show();

        WallManager wallpaper = new WallManager();
        Bitmap bitmapWallpaper = null;

        try {
            bitmapWallpaper = wallpaper.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {

            WallpaperManager.getInstance(this).setBitmap(bitmapWallpaper);

        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "EXCEPTION", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        stopSelf();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        //сообщение об остановке службы
        Toast.makeText(this, "Service stopped", Toast.LENGTH_SHORT).show();
    }

}
