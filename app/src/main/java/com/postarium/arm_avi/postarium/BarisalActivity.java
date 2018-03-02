package com.postarium.arm_avi.postarium;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BarisalActivity extends AppCompatActivity {


    ImageView searchBtn, reloadBtn;
    EditText searchBar;
    ListView listView;
    ArrayList<Subjects> SubjectList = new ArrayList<Subjects>();
    String HttpURL = "http://www.armapprise.com/barisal.php";
    ListViewAdapter listViewAdapter;
    ProgressBar progressBar ;

//    String dummyOne = "Area Name : ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mymensingh);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        searchBtn = (ImageView) findViewById(R.id.searchBtn);
        reloadBtn = (ImageView) findViewById(R.id.reloadBtn);
        searchBar = (EditText) findViewById(R.id.searchBar);
        listView = (ListView) findViewById(R.id.listView1);


        progressBar = (ProgressBar)findViewById(R.id.progressbar);

        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Subjects ListViewClickData = (Subjects) parent.getItemAtPosition(position);

//                Toast.makeText(BarisalActivity.this, ListViewClickData.getSubName(), Toast.LENGTH_SHORT).show();
                Intent barisalDispIntent = new Intent(BarisalActivity.this,DataDisp.class);
                barisalDispIntent.putExtra("key_barisal_11",ListViewClickData.getSubName());
                barisalDispIntent.putExtra("key_barisal_12",ListViewClickData.getSubFullForm());
                barisalDispIntent.putExtra("key_barisal_13",ListViewClickData.getSubPhone());
                startActivity(barisalDispIntent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);
            }
        });

        searchBar.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence stringVar, int start, int before, int count) {

                listViewAdapter.getFilter().filter(stringVar.toString());
            }
        });

        new BarisalActivity.ParseJSonDataClass(this).execute();


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchBar.setVisibility(View.VISIBLE);
            }
        });

        reloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new BarisalActivity.ParseJSonDataClass(BarisalActivity.this).execute();
            }
        });

        networkStateCheck();


    }

    private class ParseJSonDataClass extends AsyncTask<Void, Void, Void> {
        public Context context;
        String FinalJSonResult;

        public ParseJSonDataClass(Context context) {

            this.context = context;
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            HttpParseClass httpParseClass = new HttpParseClass(HttpURL);

            try {
                httpParseClass.ExecutePostRequest();

                if (httpParseClass.getResponseCode() == 200) {

                    FinalJSonResult = httpParseClass.getResponse();

                    if (FinalJSonResult != null) {

                        JSONArray jsonArray = null;
                        try {

                            jsonArray = new JSONArray(FinalJSonResult);

                            JSONObject jsonObject;

                            Subjects subjects;

                            SubjectList = new ArrayList<Subjects>();

                            for (int i = 0; i < jsonArray.length(); i++) {

                                jsonObject = jsonArray.getJSONObject(i);

                                String tempName = jsonObject.getString("districtname").toString();

                                String tempFullForm = jsonObject.getString("postcode").toString();

                                String tempPhone = jsonObject.getString("thanaphone").toString();

                                subjects = new Subjects(tempName, tempFullForm, tempPhone);

                                SubjectList.add(subjects);
                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                } else {

                    Toast.makeText(context, httpParseClass.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)

        {
            progressBar.setVisibility(View.INVISIBLE);
            listViewAdapter = new ListViewAdapter(BarisalActivity.this, R.layout.listview_items_layout, SubjectList);
            listView.setAdapter(listViewAdapter);
        }
    }

    public void networkStateCheck(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo wifi = cm
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        android.net.NetworkInfo datac = cm
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifi != null & datac != null)
                && (wifi.isConnected() | datac.isConnected())) {
        }else{

            Context context = getApplicationContext();
            AlertDialog.Builder builder = new AlertDialog.Builder(BarisalActivity.this);
            builder.setTitle("Confirm");
            builder.setMessage("There is No internet Connection ! are You sure to connect internet ?");

            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    // Do nothing but close the dialog
                    final Intent intent = new Intent(Intent.ACTION_MAIN, null);
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    final ComponentName cn = new ComponentName("com.android.settings", "com.android.settings.wifi.WifiSettings");
                    intent.setComponent(cn);
                    intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });

            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    // Do nothing
                    dialog.dismiss();
                }
            });

            AlertDialog alert = builder.create();
            alert.show();
        }
    }

}
