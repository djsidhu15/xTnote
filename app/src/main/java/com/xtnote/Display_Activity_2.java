package com.xtnote;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Display_Activity_2 extends AppCompatActivity {
    EditText et1;
    TextView tv1;
    Button b1;

    String content,keyword,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display__activity_2);
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
        et1=(EditText)findViewById(R.id.et1);
        b1=(Button)findViewById(R.id.b1);

        Bundle bundle=getIntent().getExtras();
        content=bundle.getString("con");
        keyword=bundle.getString("kw");
        password=bundle.getString("pw");

        String note_name="www.xtnote.com/"+keyword;
        tv1.setText(note_name);
        et1.setText(content);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Display_Activity_2.this,"Function not yet assigned",Toast.LENGTH_LONG).show();
            }
        });
    }

}
