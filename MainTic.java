package com.example.sujeet.tictac;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainTic extends AppCompatActivity {
    TextView player1,player2;
    String text1,text2;
    TextView x,o;
    int s1,s2;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    GridLayout grid;
    ConstraintLayout co;
    static int flag;
    int bcount;
    static int xscore=0;
    static int oscore=0;
    Random r=new Random();
    String pl1;
    String pl2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic);
        View v1, v2, v3, v4, v5, v6;
        Point size = new Point();
        player1=findViewById(R.id.player1);
        player2=findViewById(R.id.player2);
        co=findViewById(R.id.cl);
        final TextView turn;
        turn = findViewById(R.id.chance);

        x=findViewById(R.id.xscore);
        o=findViewById(R.id.oscore);
        final String a[][] = new String[5][5];
        int p,q;
        p= getIntent().getIntExtra("xscore", 0);
        q=getIntent().getIntExtra("oscore",0);
        x.setText(""+p);
        o.setText(""+q);
        Randchance(turn);
        int refx;
        refx=getIntent().getIntExtra("ref",0);
        pl1=getIntent().getStringExtra("Player_selects_X");
        pl2=getIntent().getStringExtra("Player_selects_O");
        int resume;
        resume=getIntent().getIntExtra("resume",0);


            player1.setText(pl1);
            player2.setText(pl2);
        if(resume==1)
        {
            flag=1;
            loaddata();
        }
        System.out.println("Flag"+flag);
        if(flag==1)
        {
            loaddata();
            xscore=s1;
            oscore=s2;
        }
        //int width=size.x;
        //int height=size.y;
        //String winner;
       // final int bcount=0;
        final Button bu[][] = new Button[5][5];

        v1 = findViewById(R.id.l1);
        v2 = findViewById(R.id.l2);
        v3 = findViewById(R.id.l3);
        v4 = findViewById(R.id.l4);
        v5 = findViewById(R.id.l5);
        v6 = findViewById(R.id.l6);
        grid = findViewById(R.id.grid);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        GridLayout.LayoutParams params;
        System.out.println("width" + width + "height" + height);

//        GridLayout.LayoutParams params=bu[i][j].getLayoutParams();
        //v1.setBottom(300);
        v1.setBackgroundColor(Color.BLACK);
        v2.setBackgroundColor(Color.BLACK);
        v3.setBackgroundColor(Color.BLACK);
        v4.setBackgroundColor(Color.BLACK);
        v5.setBackgroundColor(Color.BLACK);
        v6.setBackgroundColor(Color.BLACK);
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 3; ++j) {
                a[i][j]="-1";
                bu[i][j] = new Button(MainTic.this);
                //bu[i][j].setBackgroundColor(Color.LTGRAY);
                grid.addView(bu[i][j]);
                bu[i][j].setTextSize(40);
                params = (GridLayout.LayoutParams) bu[i][j].getLayoutParams();
                params.width = width / 3;
                params.height = 460; //height*7/30
                params.setGravity(Gravity.CENTER);
                bu[i][j].setLayoutParams(params);
            }
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 3; ++j) {
                final int finalI = i;
                final int finalJ = j;
                bu[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String winner;
                        if (bu[finalI][finalJ].getText().toString().equals("")) {
                            bcount++;
                            bu[finalI][finalJ].setText(turn.getText().toString());
                            if (turn.getText().toString().equals("X")) {
                                a[finalI][finalJ]=turn.getText().toString();
                                turn.setText("O");
                            }
                            else if (turn.getText().toString().equals("O")) {
                                a[finalI][finalJ]=turn.getText().toString();
                                turn.setText("X");
                            }

//                            for(int i=0;i<3;i++)
//                                for(int j=0;j<3;++j)
//                                    System.out.println("a["+i+"]["+j+"]:"+a[i][j]);
                           if(isComplete(a,bu).equals("X"))
                           {
                               for(int i=0;i<3;i++)
                                   for(int j=0;j<3;j++)
                                       bu[i][j].setEnabled(false);
                               xscore++;
                               Toast.makeText(MainTic.this,"X Wins",Toast.LENGTH_SHORT).show();
                               final Intent i1=new Intent(MainTic.this,MainTic.class);
                               i1.putExtra("xscore",xscore);
                               i1.putExtra("oscore",oscore);
                               i1.putExtra("Player_selects_X",pl1);
                               i1.putExtra("Player_selects_O",pl2);
                               co.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       startActivity(i1);
                                       finish();
                                   }
                               });
//                               startActivity(i1);
//                               finish();
                           }
                           else if(isComplete(a,bu).equals("O"))
                           {
                               for(int i=0;i<3;i++)
                                   for(int j=0;j<3;j++)
                                       bu[i][j].setEnabled(false);
                               oscore++;
                               Toast.makeText(MainTic.this,"O Wins",Toast.LENGTH_SHORT).show();
                               //turn.setText("O Wins");
                               final Intent i2=new Intent(MainTic.this,MainTic.class);
                               i2.putExtra("xscore",xscore);
                               i2.putExtra("oscore",oscore);
                               i2.putExtra("Player_selects_X",pl1);
                               i2.putExtra("Player_selects_O",pl2);
                               co.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       startActivity(i2);
                                       finish();
                                   }
                               });

                           }
                           else if(bcount==9)
                           {
                               Toast.makeText(MainTic.this,"Draw",Toast.LENGTH_SHORT).show();
                               final Intent i3=new Intent(MainTic.this,MainTic.class);
                               i3.putExtra("xscore",xscore);
                               i3.putExtra("oscore",oscore);
                               i3.putExtra("Player_selects_X",pl1);
                               i3.putExtra("Player_selects_O",pl2);
                               co.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       startActivity(i3);
                                       finish();
                                   }
                               });
                           }

                        }
                    }
                });
            }

    }


    public String isComplete(String a[][],Button bu[][]) {
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
            {
                if(i==0)
                {
                    if(a[i][j].equals(a[i+1][j])&&a[i][j].equals(a[i+2][j])&&!a[i][j].equals("-1"))//columns check
                    {
                        bu[i][j].setTextColor(Color.GREEN);
                        bu[i+1][j].setTextColor(Color.GREEN);
                        bu[i+2][j].setTextColor(Color.GREEN);
                        return a[i][j];
                    }
                }
                if(j==0)
                {
                    if(a[i][j].equals(a[i][j+1])&&a[i][j].equals(a[i][j+2])&&!a[i][j].equals("-1"))//rows check
                    {
                        bu[i][j].setTextColor(Color.GREEN);
                        bu[i][j+1].setTextColor(Color.GREEN);
                        bu[i][j+2].setTextColor(Color.GREEN);
                        return a[i][j];
                    }
                }
                if(i==0 && j==0)
                {
                    if(a[i][j].equals(a[i+1][j+1])&&a[i][j].equals(a[i+2][j+2])&&!a[i][j].equals("-1"))//rows check
                    {
                        bu[i][j].setTextColor(Color.GREEN);
                        bu[i+1][j+1].setTextColor(Color.GREEN);
                        bu[i+2][j+2].setTextColor(Color.GREEN);
                        return a[i][j];
                    }
                }
                if(i==2 && j==0)
                {
                    if(a[i][j].equals(a[i-1][j+1])&&a[i][j].equals(a[i-2][j+2])&&!a[i][j].equals("-1"))//rows check
                    {
                        bu[i][j].setTextColor(Color.GREEN);
                        bu[i-1][j+1].setTextColor(Color.GREEN);
                        bu[i-2][j+2].setTextColor(Color.GREEN);
                        return a[i][j];
                    }
                }
            }

    return "hi";
    }

    public void Randchance(TextView turn) {
        int x;
        x = r.nextInt(2);
        if (x == 1)
            turn.setText("O");
        else
            turn.setText("X");
    }

    @Override
    protected void onPause() {
        super.onPause();
        sharedPreferences=getSharedPreferences("save1",MODE_PRIVATE);
       editor=sharedPreferences.edit();
       editor.putString("p1",player1.getText().toString());
        editor.putString("p2",player2.getText().toString());
        editor.putInt("s1",xscore);
        editor.putInt("s2",oscore);
        editor.apply();
    }
    public void loaddata()
    {
        sharedPreferences=getSharedPreferences("save1",MODE_PRIVATE);
        text1=sharedPreferences.getString("p1","");
        text2=sharedPreferences.getString("p2","");
        s1=sharedPreferences.getInt("s1",0);
        s2=sharedPreferences.getInt("s2",0);
        player1.setText(text1);
        player2.setText(text2);
        x.setText(""+s1);
        o.setText(""+s2);
    }
}