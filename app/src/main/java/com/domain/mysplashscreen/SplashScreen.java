package com.domain.mysplashscreen;

import android.app.Activity;
import android.app.SharedElementCallback;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Map;
import java.util.Set;

public class SplashScreen extends Activity {
    private int countItem = 1;
    private long ms = 0;
    private long splashTime = 2000;
    private boolean splashActive = true;
    private boolean paused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int max = sharedPreferences.getInt("count", 0);
        if (max == 2) {
            Toast.makeText(SplashScreen.this, "Холодный запуск №3 } ", Toast.LENGTH_SHORT).show();
        }

        sharedPreferences.edit().putInt("count", max + countItem).apply();


        Thread myThread = new Thread() {
            public void run() {
                try {
                    while (splashActive && ms < splashTime) {
                        if(!paused)
                            ms = ms + 100;
                        sleep(100);
                    }
                } catch (Exception e) {}
                finally {
 //
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        myThread.start();
    }
}
