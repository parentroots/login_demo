package com.example.loginui;

import static android.view.View.GONE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextInputEditText loginEmail,loginPassword,signupEmail,signupPassword;
    AppCompatButton loginSubmitButton,signupSubmitButton;
    TextView signupText,titleText,userAlertTex;
    LinearLayout signinLayout,signupLayout;
    CardView cardView;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



      variableFinder();
      signupValidation();
      loginValidation();



      Animation right_slide= AnimationUtils.loadAnimation(MainActivity.this,R.anim.right_slide);
      Animation left_slide=AnimationUtils.loadAnimation(MainActivity.this,R.anim.left_slide);



      signupText.setOnClickListener(v -> {

          String currentText=signupText.getText().toString();

          if (currentText.equals("Signup")){

              cardView.startAnimation(right_slide);
              signinLayout.setVisibility(GONE);
              signupLayout.setVisibility(View.VISIBLE);
              signupText.setText("Login");
              titleText.setText("SIGN UP");
              userAlertTex.setText("Already have an account ?");


          } else if (currentText.equals("Login")) {

              cardView.startAnimation(left_slide);
              signupLayout.setVisibility(GONE);
              signinLayout.setVisibility(View.VISIBLE);
              signupText.setText("Signup");
             titleText.setText("LOGIN");
             userAlertTex.setText("Don't have any account ?");

          }


      });





    }


    public void variableFinder(){
        loginEmail=findViewById(R.id.loginEmail);
        loginPassword=findViewById(R.id.loginPassword);

        loginSubmitButton=findViewById(R.id.loginSubmitButton);
        signupSubmitButton=findViewById(R.id.signupSubmitButton);

        signupEmail=findViewById(R.id.signupEmail);
        signupPassword=findViewById(R.id.signupPassword);

        signupText=findViewById(R.id.signUpText);
        signinLayout=findViewById(R.id.singinLayout);
        signupLayout=findViewById(R.id.signupLayout);
        cardView=findViewById(R.id.mainCardView);
        titleText=findViewById(R.id.titileText);
        userAlertTex=findViewById(R.id.userAlertText);
    }







    public void signupValidation(){


        signupSubmitButton.setOnClickListener(v ->{

           String SignupEmail = signupEmail.getText().toString().trim();
           String SignupPassword=signupPassword.getText().toString().trim();

            if (SignupEmail.isEmpty()) {
                signupEmail.setError("Enter Valid Email");

            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(SignupEmail).matches()) {
                signupEmail.setError("Enter a valid email address");

            } else if (SignupPassword.isEmpty()) {
                signupPassword.setError("Password Required");

            } else if (SignupPassword.length()<4) {
               signupPassword.setError("Password Must required 4 charecter");

            }else {

                SharedPreferences sharedPreferences=getSharedPreferences("loginPref",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("loginEmail",SignupEmail);
                editor.putString("loginPassword",SignupPassword);
                editor.apply();



            }

        });



    }
    
    
    
    public void loginValidation(){
        
        loginSubmitButton.setOnClickListener(v -> {
            
            String LoginEmail=loginEmail.getText().toString().trim();
            String LoginPassword=loginPassword.getText().toString().trim();
            if (LoginEmail.isEmpty()){
                
                loginEmail.setError("Enter Email");
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(LoginEmail).matches())
            {
                loginEmail.setError("Enter a valid Email");

            } else if (LoginPassword.isEmpty()) {
                loginPassword.setError("Enter Password");
                
            } else{

                SharedPreferences sharedPreferences=getSharedPreferences("loginPref",MODE_PRIVATE);

                String Semail=sharedPreferences.getString("loginEmail",null);
                String Spassword=sharedPreferences.getString("loginPassword",null);

                if (LoginEmail.equals(Semail)&& LoginPassword.equals(Spassword)){

                    Toast.makeText(this, "Login Successfull", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(MainActivity.this, HomePage.class);

                    startActivity(intent);

                }else {
                    Toast.makeText(this, "NOT MATCH  Enter Valid Info", Toast.LENGTH_SHORT).show();

                }

            }


        });
        
    }



}