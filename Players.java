package com.example.sujeet.tictac;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Players extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_name);
        final EditText e1,e2;
        Button done;
        done=findViewById(R.id.done);
        e1=findViewById(R.id.e1);
        e2=findViewById(R.id.e2);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(e1.getText().toString().equals(""))
                {
                    e1.setText("Player 1");
                }
                if(e2.getText().toString().equals(""))
                {
                    e2.setText("Player 2");
                }
                Intent intent=new Intent(Players.this,Choosing.class);
                intent.putExtra("Player1",e1.getText().toString());
                intent.putExtra("Player2",e2.getText().toString());
                startActivity(intent);
                finish();

            }
        });

    }
}
