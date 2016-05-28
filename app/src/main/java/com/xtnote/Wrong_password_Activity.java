package com.xtnote;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Wrong_password_Activity extends AppCompatActivity {

    EditText et1;
    TextView tv1;
    Button b1;

    String content="";

    String keyword,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong_password_);
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


        et1=(EditText)findViewById(R.id.et1);
        tv1=(TextView)findViewById(R.id.tv1);
        b1=(Button)findViewById(R.id.b1);


        Bundle bundle=getIntent().getExtras();
        keyword=bundle.getString("kw");
        password=bundle.getString("pw");
        tv1.setText("www.xtnote.com/"+keyword);
        getSupportActionBar().setTitle("xTnote.com/"+keyword);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                password=et1.getText().toString();

                class LoginAsync extends AsyncTask<String, Void, String> {

                    private Dialog loadingDialog;

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        loadingDialog = ProgressDialog.show(Wrong_password_Activity.this, "Please wait", "Signing in..");
                    }

                    @Override
                    protected String doInBackground(String... params) {

                        String usr = params[0];
                        String pass = params[1];

                        try {
                            String link="http://www.xtnote.com/xt_api.php?lnk=" + usr + "&pass=" + pass;
                            URL url= new URL(link);
                            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                            BufferedReader content = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                            String text = "";
                            String itr = "";
                            while(true) {
                                itr=content.readLine();
                                if(itr==null)
                                    break;
                                text +="\n";
                                text +=itr;
                            }
                            return text;
                        }
                        catch(Exception e) {
                            return null;
                        }

                    }

                    @Override
                    protected void onPostExecute(String result) {

                        /*Scanner scan = new Scanner(result);

                        String status_code = scan.nextLine();*/




                        /*String[] lines = result.split(System.getProperty("line.separator"));
                        status_code=lines[1];
                        for(int i=2; i<=lines.length-1;i++)
                        content=content+lines[i];*/








                        loadingDialog.dismiss();



                        String[] lines = result.split(System.getProperty("line.separator"));
                        int status_code=Integer.parseInt(lines[1]);

                        for(int i=2; i<=lines.length-1;i++)
                            content=content+lines[i];





                        switch (status_code)
                        {
                            case 00:
                                Intent intent=new Intent(Wrong_password_Activity.this, Display_Activity_2.class);
                                intent.putExtra("con",content);
                                intent.putExtra("kw",keyword);
                                intent.putExtra("pw", password);
                                Toast.makeText(Wrong_password_Activity.this, "Create a new note", Toast.LENGTH_LONG).show();
                                startActivity(intent);
                                break;

                            case 10:
                                et1.setText("");
                                Toast.makeText(Wrong_password_Activity.this,"Password didn't match. Try again.",Toast.LENGTH_SHORT).show();
                                break;

                            case 11:
                                Intent intent3=new Intent(Wrong_password_Activity.this, Display_Activity.class);
                                intent3.putExtra("con",content);
                                intent3.putExtra("kw", keyword);
                                intent3.putExtra("pw", password);
                                //Toast.makeText(MainActivity.this,"Password didn't match",Toast.LENGTH_LONG).show();
                                startActivity(intent3);
                                break;

                            case 101:
                                Intent intent4=new Intent(Wrong_password_Activity.this, Wrong_password_Activity.class);
                                intent4.putExtra("con",content);
                                intent4.putExtra("kw",keyword);
                                intent4.putExtra("pw",password);
                                //Toast.makeText(MainActivity.this,"Password didn't match",Toast.LENGTH_LONG).show();
                                startActivity(intent4);

                        }


                        /*if(status_code.equals("10")) {
                            //Intent intent =new Intent(MainActivity.this, Try_Again_Activity.class);
                            // tv_result.setText("Password doesn't match..");
                        }
                        else if(status_code.equals("11")) {
                            Intent intent=new Intent(MainActivity.this, Display_Activity.class);
                            // tv_result.setText("Username doesn't exist..");
                            intent.putExtra("con",content);
                            intent.putExtra("kw",keyword);
                            intent.putExtra("pw",password);
                            startActivity(intent);
                        }
                        else if(status_code.equals("00")) {
                            Intent intent=new Intent(MainActivity.this, Display_Activity.class);
                            intent.putExtra("res",result);
                            intent.putExtra("kw",keyword);
                            intent.putExtra("pw",password);
                            startActivity(intent);
                            //tv_result.setText("Something went wrong. Try again..");

                        }

                       /* else {
                            Intent intent = new Intent(MainActivity.this, newsfeed.class);
                            intent.putExtra("sid", result);
                            startActivity(intent);
                        }*/

                    }
                }

                new LoginAsync().execute(keyword,password);

            }

        });

    }

}
