package com.example.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.widget.Toast.makeText(getApplicationContext(), "On Create", Toast.LENGTH_SHORT).show();
        Log.d("lifecycle", "On Create");
    }

    @Override
    protected void onStart() {
        super.onStart();
        android.widget.Toast.makeText(getApplicationContext(), "On Start", Toast.LENGTH_SHORT).show();
        Log.d("lifecycle", "On Start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        android.widget.Toast.makeText(getApplicationContext(), "On Resume", Toast.LENGTH_SHORT).show();
        Log.d("lifecycle", "On Resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        android.widget.Toast.makeText(getApplicationContext(), "On Pause", Toast.LENGTH_SHORT).show();
        Log.d("lifecycle", "On Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        android.widget.Toast.makeText(getApplicationContext(), "On Stop", Toast.LENGTH_SHORT).show();
        Log.d("lifecycle", "On Stop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        android.widget.Toast.makeText(getApplicationContext(), "On Restart", Toast.LENGTH_SHORT).show();
        Log.d("lifecycle", "On Restart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        android.widget.Toast.makeText(getApplicationContext(), "On Destroy", Toast.LENGTH_SHORT).show();
        Log.d("lifecycle", "On Destroy");
    }
}