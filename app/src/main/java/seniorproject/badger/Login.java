package seniorproject.badger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Login extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button logIn = (Button) findViewById(R.id.logInButton);
        logIn.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        switch(v.getId()) {
            case R.id.logInButton:
                startActivity(new Intent(this, LoginCredentialsActivity.class));
        }
    }
}
