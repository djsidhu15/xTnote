package com.xtnote;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Display_Activity extends AppCompatActivity {
    TextView tv1,tv2;
    Button b1;
    String content,keyword,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        tv1=(TextView)findViewById(R.id.tv1);
        tv2=(TextView)findViewById(R.id.tv2);
        b1=(Button)findViewById(R.id.b1);

        Bundle bundle=getIntent().getExtras();
        content=bundle.getString("con");
        keyword=bundle.getString("kw");
        password=bundle.getString("pw");

        tv1.setText("www.xtnote.com/"+keyword);
        tv2.setText(content);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Display_Activity.this, Display_Activity_2.class);
                intent.putExtra("con",content);
                intent.putExtra("kw", keyword);
                intent.putExtra("pw", password);
                startActivity(intent);
            }
        });
    }

}
