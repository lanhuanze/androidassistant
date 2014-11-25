package com.iassistant.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.iassistant.android.R;
import com.iassistant.android.asynctask.AnonymousDataTask;
import com.iassistant.android.prefs.Prefs;

public class HelloAndroidActivity extends Activity implements View.OnClickListener{

    private TextView forgetPassword;
    private Button registerButton;
    private Button loginButton;

    /**
     * Called when the activity is first created.
     * @param savedInstanceState If the activity is being re-initialized after 
     * previously being shut down then this Bundle contains the data it most 
     * recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Prefs prefs = Prefs.getInstance();
        if(prefs.isLogined()) {
            Intent intent = new Intent();
            intent.setClass(this, DashboardActivity.class);
            intent.putExtra("json", "Already logged in.");
            startActivity(intent);
            finish();
        }

        setContentView(R.layout.activity_main);
        //AsyncTask task = new AnonymousDataTask(this);
        //task.execute(null);
        forgetPassword = (TextView)findViewById(R.id.forget_password_textview);
        forgetPassword.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        registerButton = (Button)findViewById(R.id.register_button);
        loginButton = (Button)findViewById(R.id.login_button);

        registerButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.main, menu);
	return true;
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(R.id.register_button == id) {
            Intent intent = new Intent();
            intent.setClass(this, RegisterActivity.class);
            startActivity(intent);
        }
    }
}

