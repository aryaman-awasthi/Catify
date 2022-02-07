package com.example.catify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Hello extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        Thread thread = new Thread(){
            public void run(){
                try{
                    sleep(6700);
                }catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    Intent intent = new Intent(Hello.this, Home.class);
                    Hello.this.finish();
                    startActivity(intent);
                }
            }
        };
        thread.start();
    }
}