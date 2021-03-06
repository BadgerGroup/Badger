package seniorproject.badger;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateAccount extends AppCompatActivity implements View.OnClickListener {

    EditText uText;
    EditText pwText;
    EditText mailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Create Account");
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        uText = (EditText) findViewById(R.id.usernameText);
        pwText = (EditText) findViewById(R.id.passwordText);
        mailText = (EditText) findViewById(R.id.emailText);

        Button signUpButton = (Button) findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(this);


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
        switch(v.getId()) {
            case R.id.signUpButton:
                String u = uText.getText().toString();
                String email = mailText.getText().toString();
                String pw = pwText.getText().toString();

                Database db = new Database();
                try {
                    User user = db.createUser(u, pw, email);
                    if (user == null) {
                        Log.e("Database", "User is null after creation.");
                    }
                    BadgerApp app = (BadgerApp) getApplication();
                    app.setCurrentUser(user);

                    startActivity(new Intent(this, Profile.class));
                }
                catch (IllegalArgumentException e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
        }
    }
}
