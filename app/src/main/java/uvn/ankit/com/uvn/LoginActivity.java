package uvn.ankit.com.uvn;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText pass;

    //firebase auth object
    private FirebaseAuth firebaseAuth;

    //progress dialog
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


            TextView s1 = (TextView) findViewById(R.id.Forget);
            firebaseAuth = FirebaseAuth.getInstance();

            s1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email1 = email.getText().toString().trim();
                    Intent i = new Intent(getApplicationContext(), Forget_Activity.class);
                    i.putExtra("email" , email1);
                    startActivity(i);
                }
            });
            //if the objects getcurrentuser method is not null
            //means user is already logged in
            if(firebaseAuth.getCurrentUser() != null){
                //close this activity
                finish();
                //opening profile activity
                startActivity(new Intent(getApplicationContext(), Main2Activity.class));
            }

            //initializing views
            email = (EditText) findViewById(R.id.email);
            pass = (EditText) findViewById(R.id.pass);
            Button b1 = (Button) findViewById(R.id.Log_in);
            Button sign_up = (Button) findViewById(R.id.sign_up);

            sign_up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), Sign_up.class);
                    startActivity(i);
                }
            });
            progressDialog = new ProgressDialog(this);

            //attaching click listener
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email1 = email.getText().toString().trim();
                    String password  = pass.getText().toString().trim();


                    //checking if email and passwords are empty
                    if(TextUtils.isEmpty(email1)){
                        Toast.makeText(getApplicationContext(),"Please enter email",Toast.LENGTH_LONG).show();
                        return;
                    }

                    if(TextUtils.isEmpty(password)){
                        Toast.makeText(getApplicationContext(),"Please enter password",Toast.LENGTH_LONG).show();
                        return;
                    }

                    //if the email and password are not empty
                    //displaying a progress dialog

                    progressDialog.setMessage("Registering Please Wait...");
                    progressDialog.show();

                    //logging in the user

                    firebaseAuth.signInWithEmailAndPassword(email1, password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressDialog.dismiss();
                                    //if the task is successfull
                                    if(task.isSuccessful()){
                                        //start the profile activity
                                        finish();
                                        startActivity(new Intent(getApplicationContext(), Main2Activity.class));
                                    }
                                }
                            });


                }
            });

        }
    }
