package uvn.ankit.com.uvn;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Sign_up extends AppCompatActivity {

    private EditText email;
    private EditText pass,Fname,Lname,Address,City,Pincode,Work,Wprofile;
    private ProgressDialog progressDialog;


    //defining firebaseauth object
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firebaseAuth = FirebaseAuth.getInstance();
        //initializing views
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.pass);
        Fname = (EditText) findViewById(R.id.Fname);
        Lname = (EditText) findViewById(R.id.Lname);
        Address = (EditText) findViewById(R.id.Address);
        City = (EditText) findViewById(R.id.City);
        Pincode = (EditText) findViewById(R.id.Pincode);
        Wprofile = (EditText) findViewById(R.id.Wprofile);
        TextView sign_up = (TextView) findViewById(R.id.sign_up);
        Button b1 = (Button) findViewById(R.id.b1);

        progressDialog = new ProgressDialog(this);

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Sign_up.this, LoginActivity.class);
                startActivity(i);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email1 = email.getText().toString().trim();
                String password  = pass.getText().toString().trim();

                //checking if email and passwords are empty
                if(TextUtils.isEmpty(email1)){
                    Toast.makeText(Sign_up.this,"Please enter email",Toast.LENGTH_LONG).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Sign_up.this,"Please enter password",Toast.LENGTH_LONG).show();
                    return;
                }

                //if the email and password are not empty
                //displaying a progress dialog

                progressDialog.setMessage("Registering Please Wait...");
                progressDialog.show();

                //creating a new user
                firebaseAuth.createUserWithEmailAndPassword(email1, password)
                        .addOnCompleteListener(Sign_up.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //checking if success
                                if(task.isSuccessful()){
                                    //display some message here
                                    Toast.makeText(Sign_up.this,"Successfully registered",Toast.LENGTH_LONG).show();
                                    progressDialog.dismiss();
                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    String username = email.getText().toString();
                                    String First_name = Fname.getText().toString();
                                    String Last_name = Lname.getText().toString();
                                    String address = Address.getText().toString();
                                    String city = City.getText().toString();
                                    String pincode = Pincode.getText().toString();
                                    String Work_profile = Wprofile.getText().toString();

                                    int i1 = username.indexOf('@');
                                    username = username.substring(0,i1);
                                    final DatabaseReference myRef = database.getReference(username);
                                    final DatabaseReference mchild1 = myRef.child("email");
                                    mchild1.setValue(username);
                                    final DatabaseReference mchild2 = myRef.child("email");
                                    mchild2.setValue(First_name);
                                    final DatabaseReference mchild3 = myRef.child("email");
                                    mchild3.setValue(Last_name);
                                    final DatabaseReference mchild4 = myRef.child("email");
                                    mchild4.setValue(address);
                                    final DatabaseReference mchild5 = myRef.child("email");
                                    mchild5.setValue(city);
                                    final DatabaseReference mchild6 = myRef.child("email");
                                    mchild3.setValue(pincode);
                                    final DatabaseReference mchild8 = myRef.child("email");
                                    mchild5.setValue(Work_profile);
                                    Intent i = new Intent(Sign_up.this, Main2Activity.class);
                                    startActivity(i);
                                }else{
                                    //display some message here
                                    Toast.makeText(Sign_up.this,"Registration Error",Toast.LENGTH_LONG).show();
                                    progressDialog.dismiss();
                                }

                            }
                        });
            }
        });
    }
}
