package com.example.nasawallpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "NasaAPI";
    public static final String API_KEY = "C8X3Ne6xlKtjlfcPkk6q3YJvmYtE6lY8NIq4aAhF";
    public static ImageView imageLoaded;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Intent intent = new Intent(this, WallPaperService.class);
//        startService(intent);

        imageLoaded = findViewById(R.id.loadedImage);

        SiteDataApi api = new SiteDataApi();
        api.execute();

    }
}
