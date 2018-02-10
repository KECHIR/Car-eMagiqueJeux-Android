package com.example.cash.sumgame;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Random;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);




    }



    public void newgame (View v){
        Intent intenetnewgame = new Intent(  this, Level.class);

        startActivity(intenetnewgame);
    }


    public void AboutGame (View v){
        Intent intenetAboutgame = new Intent(Intent.ACTION_VIEW, Uri.parse("https://fr.wikipedia.org/wiki/Carr%C3%A9_magique_(math%C3%A9matiques)"));
        startActivity(intenetAboutgame);
    }



    public void Exitgame (View v){
        this.finish();

    }
}
