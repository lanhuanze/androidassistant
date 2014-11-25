package com.iassistant.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.iassistant.android.R;
import com.iassistant.android.asynctask.CallbackAsyncTask;
import com.iassistant.android.asynctask.PutAsyncTask;
import com.iassistant.android.entities.RegisterEntity;
import com.iassistant.android.entities.RegisterResult;
import com.iassistant.android.http.RegisterResultResponseHandler;
import com.iassistant.android.http.Result;
import com.iassistant.android.prefs.Prefs;
import com.iassistant.android.util.FieldChecker;
import com.iassistant.android.util.GsonHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * Created by lan on 11/24/14.
 */
@Slf4j
public class RegisterActivity extends BaseActivity implements View.OnClickListener, CallbackAsyncTask.Callback<RegisterResult>{
    private EditText email;
    private EditText password;
    private EditText phoneNumber;
    private Spinner securityQuestion;
    private EditText securityAnswer;
    private Button register;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_register);

        email = byId(R.id.register_email_edittext);
        password = byId(R.id.register_password_textedit);
        phoneNumber = byId(R.id.register_phonenumber_textedit);
        securityQuestion = byId(R.id.register_security_question_spinner);
        securityAnswer = byId(R.id.register_security_answer_textedit);
        register = byId(R.id.register_action_button);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(R.id.register_action_button == id) {
            if(!FieldChecker.isEmail(email.getText())) {
                email.setError(get(R.string.error_email_address));
            }else if(!StringUtils.isEmpty(phoneNumber.getText()) && !FieldChecker.isPhoneNumber(phoneNumber.getText())) {
                phoneNumber.setError(get(R.string.error_phone_number));
            }else if(StringUtils.isEmpty(password.getText())) {
                password.setError(get(R.string.error_empty_field));
            }else if(StringUtils.isEmpty(securityAnswer.getText())) {
                securityAnswer.setError(get(R.string.error_empty_field));
            }else {
                RegisterEntity re = new RegisterEntity();
                re.setEmail(email.getText().toString());
                if(!StringUtils.isEmpty(phoneNumber.getText())) {
                    re.setPhoneNumber(phoneNumber.getText().toString());
                }
                re.setPassword(password.getText().toString());
                re.setSecurityAnswer(securityAnswer.getText().toString());
                re.setSecurityQuestionIndex(securityQuestion.getSelectedItemPosition());

                CallbackAsyncTask asyncTask = new PutAsyncTask("/account/v1/register", this, Arrays.asList(re), new RegisterResultResponseHandler());
                asyncTask.execute();

            }
        }
    }

    @Override
    public void onResult(Result<RegisterResult> result) {
        log.info("Register result:" + GsonHelper.toJson(result));
        if(result.getCount() <= 0) {
            Toast.makeText(this, "Register error.", Toast.LENGTH_LONG).show();
        }else {
            Intent intent = new Intent();
            intent.setClass(this, DashboardActivity.class);
            intent.putExtra("json", GsonHelper.toJson(result));
            startActivity(intent);

            Prefs prefs = Prefs.getInstance();
            prefs.setLogined(true);
            RegisterResult rr = result.getObjects().get(0);
            prefs.setAccountId(rr.getAccountId());
            prefs.setDeviceId(rr.getDeviceId());
        }

    }
}