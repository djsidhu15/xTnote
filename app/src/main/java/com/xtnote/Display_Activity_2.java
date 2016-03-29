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
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Display_Activity_2 extends AppCompatActivity {
    EditText et1;
    TextView tv1;
    Button b1;
    String appID="sidX349notE",appKey="#$Enter14",itr="value",text="";

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

                content=et1.getText().toString();


                class LoginAsync extends AsyncTask<String, Void, String> {

                    private Dialog loadingDialog;

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        loadingDialog = ProgressDialog.show(Display_Activity_2.this, "Please wait", "Saving...");
                    }

                    @Override
                    protected String doInBackground(String... params) {

                        String lnk = params[0];
                        String pass = params[1];
                        String data = params[2];
                        String appID = params[3];
                        String appKey = params[4];

                        try {

                            String link = "http://xtnote.com/xt_api.php";
                            String postData = "lnk=" + URLEncoder.encode(lnk, "UTF-8") + "&pass=" + URLEncoder.encode(pass, "UTF-8") + "&data=" + URLEncoder.encode(data, "UTF-8") + "&appID=" + URLEncoder.encode(appID, "UTF-8") + "&appKey=" + URLEncoder.encode(appKey, "UTF-8");

                            URL url = new URL(link);
                            HttpURLConnection con = (HttpURLConnection) url.openConnection();

                            con.setRequestMethod("POST");
                            con.setDoOutput(true);

                            DataOutputStream contentWriter = new DataOutputStream(con.getOutputStream());
                            contentWriter.writeBytes(postData);

                            contentWriter.flush();
                            contentWriter.close();

                            BufferedReader contentReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                            /*itr=contentReader.readLine();
                            itr=contentReader.readLine();*/
                            while(true) {
                                itr=contentReader.readLine();
                                if(itr==null)
                                    break;
                                text +="\n";
                                text +=itr;
                            }

                            return text;


                        } catch (IOException e) {
                            return "a";
                        }
                    }


                    @Override
                    protected void onPostExecute(String result) {

                        loadingDialog.dismiss();

                        String[] lines = result.split(System.getProperty("line.separator"));
                        String status_code=lines[1];

                        switch(status_code){

                            case "00":

                                Toast.makeText(Display_Activity_2.this,"Note is empty",Toast.LENGTH_LONG).show();
                                break;

                            case "10":

                                Toast.makeText(Display_Activity_2.this,"Password didn't match",Toast.LENGTH_LONG).show();
                                break;

                            case "11":

                                //Toast.makeText(Display_Activity_2.this,"Saved",Toast.LENGTH_LONG).show();


                                class LoginAsync2 extends AsyncTask<String, Void, String> {

                                    private Dialog loadingDialog;

                                    @Override
                                    protected void onPreExecute() {
                                        super.onPreExecute();
                                        loadingDialog = ProgressDialog.show(Display_Activity_2.this, "Please wait", "Saving...");
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
                                        String status_code=lines[1];
                                        content="";
                                        for(int i=2; i<=lines.length-1;i++)
                                            content=content+lines[i];





                                        switch (status_code)
                                        {


                                            case "10":
                                                Intent intent2=new Intent(Display_Activity_2.this, Wrong_password_Activity.class);
                                                intent2.putExtra("kw",keyword);
                                                intent2.putExtra("pw", password);
                                                Toast.makeText(Display_Activity_2.this,"Password is changed.. Re-enter the password",Toast.LENGTH_LONG).show();
                                                startActivity(intent2);
                                                break;

                                            case "11":
                                                Intent intent3=new Intent(Display_Activity_2.this, Display_Activity.class);
                                                intent3.putExtra("con",content);
                                                intent3.putExtra("kw", keyword);
                                                intent3.putExtra("pw", password);
                                                Toast.makeText(Display_Activity_2.this,"Successfully Saved",Toast.LENGTH_LONG).show();
                                                startActivity(intent3);
                                                break;

                                            case "101":
                                                Intent intent4=new Intent(Display_Activity_2.this, Read_Only_Activity.class);
                                                intent4.putExtra("con",content);
                                                intent4.putExtra("kw",keyword);
                                                intent4.putExtra("pw",password);
                                                startActivity(intent4);

                                        }

                                    }
                                }
                                new LoginAsync2().execute(keyword,password,content,appID,appKey);

                                break;

                        }

                    }
                }

                new LoginAsync().execute(keyword,password,content,appID,appKey);
            }

        });
    }

}
