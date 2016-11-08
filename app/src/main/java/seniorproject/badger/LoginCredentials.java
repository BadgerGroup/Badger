package seniorproject.badger;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginCredentials extends AppCompatActivity implements View.OnClickListener {

    EditText userText;
    EditText passwordText;
    EditText emailText;
    public static User newUser = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_credentials);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Sign In");
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView newUserText = (TextView) findViewById(R.id.newUserText);
        newUserText.setOnClickListener(this);

        userText = (EditText) findViewById(R.id.userText);
        passwordText = (EditText) findViewById(R.id.passwordText);
        emailText = (EditText) findViewById(R.id.emailText);

        Button signInButton = (Button) findViewById(R.id.signInButton);
        signInButton.setOnClickListener(this);
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpTo(this, getIntent());
                return true;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.newUserText:
                startActivity(new Intent(this, CreateAccount.class));
                break;

            case R.id.signInButton:
                String username = userText.getText().toString();
                String password = passwordText.getText().toString();

                //creates User with sign in info
                newUser = new User();
                newUser.setUserName(username);

                //TODO: check for valid username and password in database
                //Right now, immediately go to profile
                startActivity(new Intent(this, Profile.class));
                break;
        }
    }
}
