package com.xtnote;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class Read_Only_Activity extends AppCompatActivity {
    TextView tv1,tv2;
    String content,keyword,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read__only_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        tv1=(TextView)findViewById(R.id.tv1);
        tv2=(TextView)findViewById(R.id.tv2);

        Bundle bundle=getIntent().getExtras();
        content=bundle.getString("con");
        keyword=bundle.getString("kw");
        password=bundle.getString("pw");

        getSupportActionBar().setTitle("xTnote.com/" + keyword);
        tv1.setText("www.xtnote.com/"+keyword);
        tv2.setText(content);


    }

}
