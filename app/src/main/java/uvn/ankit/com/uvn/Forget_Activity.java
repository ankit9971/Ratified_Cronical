package uvn.ankit.com.uvn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class Forget_Activity extends AppCompatActivity {

    EditText email;
    Button reset;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_);

        email = (EditText) findViewById(R.id.email);
        String s = getIntent().getStringExtra("email");
        email.setText(s);
        reset = (Button) findViewById(R.id.reset);
        firebaseAuth = FirebaseAuth.getInstance();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.sendPasswordResetEmail(email.getText().toString());
                finish();
            }
        });
    }
}
