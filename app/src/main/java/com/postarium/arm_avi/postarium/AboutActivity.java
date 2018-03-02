package com.postarium.arm_avi.postarium;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class AboutActivity extends AppCompatActivity {

    ImageView facebookBtn, linkedinBtn, gitHubBtn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        facebookBtn = (ImageView) findViewById(R.id.facebookBtn);
        linkedinBtn = (ImageView) findViewById(R.id.linkedinBtn);
        gitHubBtn = (ImageView) findViewById(R.id.gitHubBtn);

        facebookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String facebooUrl = "https://www.facebook.com/prolog.fortron";
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                facebookIntent.setData(Uri.parse(facebooUrl));
                startActivity(facebookIntent);
            }
        });

        linkedinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String linkeinUrl = "https://www.linkedin.com/in/arunav-mallik-avi-31a34b152/";
                Intent linkedinIntent = new Intent(Intent.ACTION_VIEW);
                linkedinIntent.setData(Uri.parse(linkeinUrl));
                startActivity(linkedinIntent);
            }
        });

        gitHubBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gitHubUrl = "https://github.com/avimallik";
                Intent gitHubIntent = new Intent(Intent.ACTION_VIEW);
                gitHubIntent.setData(Uri.parse(gitHubUrl));
                startActivity(gitHubIntent);
            }
        });
    }

}
