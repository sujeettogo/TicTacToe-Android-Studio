package com.example.sujeet.tictac;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class Choosing extends AppCompatActivity {
    Random r=new Random();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose);
        Button b1,b2;
        TextView chooser,choose;
        b1=findViewById(R.id.xbutton);
        b2=findViewById(R.id.obutton);
        chooser=findViewById(R.id.chooser);
        choose=findViewById(R.id.choose);
      //  choose.setBackgroundColor(Color.WHITE);

       // chooser.setBackgroundColor(Color.WHITE);
        final String p1,p2;
        p1=getIntent().getStringExtra("Player1");
        p2=getIntent().getStringExtra("Player2");
        final int x=r.nextInt(2);
        if(x==1)
            chooser.setText(p1);
        else
            chooser.setText(p2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(x==1)
                {
                    Intent i1=new Intent(Choosing.this,MainTic.class);
                    i1.putExtra("Player_selects_X",p1);
                    i1.putExtra("Player_selects_O",p2);
                    i1.putExtra("ref",x);
                    startActivity(i1);
                    finish();
                }
                else {
                    Intent i2=new Intent(Choosing.this,MainTic.class);
                    i2.putExtra("Player_selects_X",p2);
                    i2.putExtra("Player_selects_O",p1);
                    i2.putExtra("ref",x);
                    startActivity(i2);
                    finish();
                }

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(x==1)
                {
                    Intent i1=new Intent(Choosing.this,MainTic.class);
                    i1.putExtra("Player_selects_O",p1);
                    i1.putExtra("Player_selects_X",p2);
                    i1.putExtra("ref",x);
                    startActivity(i1);
                    finish();
                }
                else
                {
                    Intent i2=new Intent(Choosing.this,MainTic.class);
                    i2.putExtra("Player_selects_O",p2);
                    i2.putExtra("Player_selects_X",p1);
                    i2.putExtra("ref",x);
                    startActivity(i2);
                    finish();
                }
            }
        });
    }

}
