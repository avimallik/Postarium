package com.postarium.arm_avi.postarium;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.empty;

public class DataDisp extends AppCompatActivity {

    TextView areaNameDisp;
    EditText areaNameTxt, postCodeTxt, phoneTxt, countryCodeTxt;

    private ClipboardManager myClipboard;
    private ClipData myClip;

    ImageView areaClipBrd, postClipBrd, thanaClipBrd, teleThana;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_disp);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Postarium Text Field
        areaNameTxt = (EditText) findViewById(R.id.areaNameTxt);
        postCodeTxt = (EditText) findViewById(R.id.postCodeTxt);
        phoneTxt = (EditText) findViewById(R.id.phoneTxt);
        countryCodeTxt = (EditText) findViewById(R.id.countryCodeTxt);

        //Postarium Clipboard & Telephone Bitton
        areaClipBrd = (ImageView) findViewById(R.id.areaClipBrd);
        postClipBrd = (ImageView) findViewById(R.id.postClipBrd);
        thanaClipBrd = (ImageView) findViewById(R.id.thanaClipBrd);
        teleThana = (ImageView) findViewById(R.id.teleThana);

        myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        areaClipBrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String areaNameClp;
                areaNameClp = areaNameTxt.getText().toString();

                myClip = ClipData.newPlainText("text_110", areaNameClp);
                myClipboard.setPrimaryClip(myClip);

                Snackbar snackbar = Snackbar.make(areaClipBrd,"Area Name is Copied",Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
        });

        postClipBrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String postClp;
                postClp = postCodeTxt.getText().toString();

                myClip = ClipData.newPlainText("text_111", postClp);
                myClipboard.setPrimaryClip(myClip);

                Snackbar snackbar = Snackbar.make(postClipBrd,"Post Code is Copied",Snackbar.LENGTH_SHORT);
                snackbar.show();

            }
        });

        thanaClipBrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String telephoneClp;
                telephoneClp = countryCodeTxt.getText().toString() + phoneTxt.getText().toString();

                myClip = ClipData.newPlainText("text_112", telephoneClp);
                myClipboard.setPrimaryClip(myClip);

                Snackbar snackbar = Snackbar.make(thanaClipBrd,"Thana Phone Number is Copied",Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
        });

        teleThana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(phoneTxt.getText().toString().matches(" ")){
                    Snackbar snackbar = Snackbar.make(teleThana,"Phone Number is Empty",Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }else {
                    String phoneNumber = countryCodeTxt.getText().toString()+phoneTxt.getText().toString();
                    callIntenStart("tel:"+phoneNumber);
                }


            }
        });

        if (getIntent().hasExtra("key_sylhet_11") && getIntent().hasExtra("key_sylhet_12") && getIntent().hasExtra("key_sylhet_13")) {
            areaNameTxt.setText(getIntent().getExtras().getString("key_sylhet_11"));
            postCodeTxt.setText(getIntent().getExtras().getString("key_sylhet_12"));
            phoneTxt.setText(getIntent().getExtras().getString("key_sylhet_13"));
        } else if (getIntent().hasExtra("key_barisal_11") && getIntent().hasExtra("key_barisal_12") && getIntent().hasExtra("key_barisal_13")) {
            areaNameTxt.setText(getIntent().getExtras().getString("key_barisal_11"));
            postCodeTxt.setText(getIntent().getExtras().getString("key_barisal_12"));
            phoneTxt.setText(getIntent().getExtras().getString("key_barisal_13"));
        } else if (getIntent().hasExtra("key_chittagong_11") && getIntent().hasExtra("key_chittagong_12") && getIntent().hasExtra("key_chittagong_13")) {
            areaNameTxt.setText(getIntent().getExtras().getString("key_chittagong_11"));
            postCodeTxt.setText(getIntent().getExtras().getString("key_chittagong_12"));
            phoneTxt.setText(getIntent().getExtras().getString("key_chittagong_13"));
        } else if (getIntent().hasExtra("key_rangpur_11") && getIntent().hasExtra("key_rangpur_12") && getIntent().hasExtra("key_rangpur_13")) {
            areaNameTxt.setText(getIntent().getExtras().getString("key_rangpur_11"));
            postCodeTxt.setText(getIntent().getExtras().getString("key_rangpur_12"));
            phoneTxt.setText(getIntent().getExtras().getString("key_rangpur_13"));
        } else if (getIntent().hasExtra("key_rajshahi_11") && getIntent().hasExtra("key_rajshahi_12") && getIntent().hasExtra("key_rajshahi_13")) {
            areaNameTxt.setText(getIntent().getExtras().getString("key_rajshahi_11"));
            postCodeTxt.setText(getIntent().getExtras().getString("key_rajshahi_12"));
            phoneTxt.setText(getIntent().getExtras().getString("key_rajshahi_13"));
        } else if (getIntent().hasExtra("key_mymensingh_11") && getIntent().hasExtra("key_mymensingh_12") && getIntent().hasExtra("key_mymensingh_13")) {
            areaNameTxt.setText(getIntent().getExtras().getString("key_mymensingh_11"));
            postCodeTxt.setText(getIntent().getExtras().getString("key_mymensingh_12"));
            phoneTxt.setText(getIntent().getExtras().getString("key_mymensingh_13"));
        } else if (getIntent().hasExtra("key_khulna_11") && getIntent().hasExtra("key_khulna_12") && getIntent().hasExtra("key_khulna_13")) {
            areaNameTxt.setText(getIntent().getExtras().getString("key_khulna_11"));
            postCodeTxt.setText(getIntent().getExtras().getString("key_khulna_12"));
            phoneTxt.setText(getIntent().getExtras().getString("key_khulna_13"));
        } else if (getIntent().hasExtra("key_dhaka_11") && getIntent().hasExtra("key_dhaka_12") && getIntent().hasExtra("key_dhaka_13")) {
            areaNameTxt.setText(getIntent().getExtras().getString("key_dhaka_11"));
            postCodeTxt.setText(getIntent().getExtras().getString("key_dhaka_12"));
            phoneTxt.setText(getIntent().getExtras().getString("key_dhaka_13"));
        }




    }

    // Function for Multiple Device permission in Runtime

    void callIntenStart(String number){
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse(number));
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(callIntent);
    }

}
