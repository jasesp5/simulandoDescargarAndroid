package com.example.app_hilos_newhandler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button button;

    ProgressDialog miProgressDialog;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lanzarPogressDialog();
            }
        });
    }

    public void lanzarPogressDialog(){
        miProgressDialog = new ProgressDialog(MainActivity.this);
        miProgressDialog.setTitle("CUADRO DE PROGRESO ");
        miProgressDialog.setMessage("Almacenamiento información");
        miProgressDialog.setProgressStyle(miProgressDialog.STYLE_HORIZONTAL);
        miProgressDialog.setProgress(0);
        miProgressDialog.setMax(100);
        miProgressDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (miProgressDialog.getProgress()<= miProgressDialog.getMax()){
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                miProgressDialog.incrementProgressBy(10);
                            }
                        });
                        if(miProgressDialog.getProgress() == miProgressDialog.getMax()){
                            miProgressDialog.dismiss();
                        }
                        Thread.sleep(2000);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

    }
}