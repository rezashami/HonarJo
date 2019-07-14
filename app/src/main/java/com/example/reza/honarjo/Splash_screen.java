package com.example.reza.honarjo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reza.honarjo.Controller.api.API;
import com.example.reza.honarjo.Controller.api.appClient;
import com.example.reza.honarjo.Controller.prefrence.PreferenceManager;
import com.example.reza.honarjo.Model.LoginInformation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Splash_screen extends AppCompatActivity {
    PreferenceManager preferenceManager;
    private API APIInterface;
    EditText user_name, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        preferenceManager =new PreferenceManager(getApplicationContext());
        if (preferenceManager.getToken() != null)
        {
            lunchMain();
        }
        else{
            APIInterface = appClient.getInstance().create(API.class);
            Button loginBtn = findViewById(R.id.login_btn);
            user_name = findViewById(R.id.username);
            password = findViewById(R.id.password);
            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String username = user_name.getText().toString();
                    String pass = password.getText().toString();
                    if (TextUtils.isEmpty(username))
                    {
                        Toast.makeText(getApplicationContext(),"لطفا اسم خود را درست وارد کنید.",Toast.LENGTH_LONG).show();
                        user_name.requestFocus();
                    }
                    else if(TextUtils.isEmpty(pass))
                    {
                        Toast.makeText(getApplicationContext(),"لطفا رمز عبور خود را درست وارد کنید.",Toast.LENGTH_LONG).show();
                        password.requestFocus();
                    }
                    else{
                        LoginInformation loginInformation = new LoginInformation(username,pass);
                        Call<String> call = APIInterface.login(loginInformation);
                        call.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                if(response.code() == 200)
                                {
                                    preferenceManager.addToken(response.body());
                                    Toast.makeText(getApplicationContext(),"خوش آمدید",Toast.LENGTH_LONG).show();
                                    lunchMain();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"ظاهرا مشکلی پیش آمده است",Toast.LENGTH_LONG).show();
                                }

                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                t.printStackTrace();
                                Toast.makeText(getApplicationContext(),"خظا در ارتباط!",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
            });
        }

    }
    private void lunchMain(){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
