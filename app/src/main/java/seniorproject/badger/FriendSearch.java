package seniorproject.badger;


import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;


public class FriendSearch extends AppCompatActivity implements View.OnClickListener {

    EditText friendName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_search);


        friendName = (EditText) findViewById(R.id.friendText);



        Button seacrhButton = (Button) findViewById(R.id.searchButton);
        seacrhButton.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.searchButton:
                String fName = friendName.getText().toString();
                System.out.print(fName);
                boolean found = LoginCredentials.allUsers.contains(fName);
                if (found){
                    //for now if true goes back to FrienList page
                    startActivity(new Intent(this, FriendsList.class));
                    break;
                }
                else {
                    //for now if false goes to Profile page
                    startActivity(new Intent(this, Profile.class));
                }
                break;


        }
    }

}




