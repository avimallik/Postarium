package com.postarium.arm_avi.postarium;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class PanelActivity extends AppCompatActivity {

    GridView gridView;
    private GridViewAdapter mAdapter;
    private ArrayList<String> listDivision;
    private ArrayList<Integer> listFlag;

    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        gridView = (GridView) findViewById(R.id.gridView);

        prepareList();

        mAdapter = new GridViewAdapter(this,listDivision,listFlag);

        gridView.setAdapter(mAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
               if(position == 0){
                   Intent sylhetIntent = new Intent(PanelActivity.this,SylhetActivity.class);
                   startActivity(sylhetIntent);
                   overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);
               }else if(position == 1){
                   Intent dhakaIntent = new Intent(PanelActivity.this,DhakaActivity.class);
                   startActivity(dhakaIntent);
                   overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);
               }else if(position == 2){
                   Intent chittagongIntent = new Intent(PanelActivity.this,ChittagongActivity.class);
                   startActivity(chittagongIntent);
                   overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);
               }else if(position == 3){
                   Intent khulnaIntent = new Intent(PanelActivity.this,KhulnaActivity.class);
                   startActivity(khulnaIntent);
                   overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);
               }else if(position == 4){
                   Intent rajshahiIntent = new Intent(PanelActivity.this,RajshahiActivity.class);
                   startActivity(rajshahiIntent);
                   overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);
               }else if(position == 5){
                   Intent barisalIntent = new Intent(PanelActivity.this,BarisalActivity.class);
                   startActivity(barisalIntent);
                   overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);
               }else if(position == 6){
                   Intent rangpurIntent = new Intent(PanelActivity.this,RangpurActivity.class);
                   startActivity(rangpurIntent);
                   overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);
               }else if(position == 7){
                   Intent mymensinghIntent = new Intent(PanelActivity.this,MymensinghActivity.class);
                   startActivity(mymensinghIntent);
                   overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);
               }
            }
        });

        checkAndRequestPermissions();


    }

    public void prepareList()
    {
        Resources res = getResources();
        // String[]menu_grid = res.getStringArray(R.array.menu_grid);

        listDivision = new ArrayList<String> ();

        listDivision.add(getString(R.string.sylhet_title));
        listDivision.add(getString(R.string.dhaka_title));
        listDivision.add(getString(R.string.chittagong_title));
        listDivision.add(getString(R.string.khulna_title));
        listDivision.add(getString(R.string.rajshahi_title));
        listDivision.add(getString(R.string.barisal_title));
        listDivision.add(getString(R.string.rangpur_title));
        listDivision.add(getString(R.string.mymensingh_title));

        listFlag = new ArrayList<Integer>();
        listFlag.add(R.drawable.sylhetphoto);
        listFlag.add(R.drawable.dhakaphoto);
        listFlag.add(R.drawable.chittagongphoto);
        listFlag.add(R.drawable.khulnaphoto);
        listFlag.add(R.drawable.rajshahiphoto);
        listFlag.add(R.drawable.barisalphoto);
        listFlag.add(R.drawable.rangpurphoto);
        listFlag.add(R.drawable.mymensinghphoto);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_panel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Intent aboutIntent = new Intent(PanelActivity.this,AboutActivity.class);
            startActivity(aboutIntent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private  boolean checkAndRequestPermissions() {
        int phoneCall = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        List<String> listPermissionsNeeded = new ArrayList<>();

        if (phoneCall != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CALL_PHONE);
        }
        if (!listPermissionsNeeded.isEmpty())
        {
            ActivityCompat.requestPermissions(this,listPermissionsNeeded.toArray
                    (new String[listPermissionsNeeded.size()]),REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;

    }
}
