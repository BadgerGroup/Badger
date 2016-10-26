package seniorproject.badger;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Profile extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button friends = (Button) findViewById(R.id.friendsButton);
        friends.setOnClickListener(this);

        Button groups = (Button) findViewById(R.id.groupsButton);
        groups.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        switch(v.getId()) {
            case R.id.friendsButton:
                startActivity(new Intent(this, FriendsList.class));
                break;

            case R.id.groupsButton:
                startActivity(new Intent(this, GroupList.class));
                break;
        }
    }
}
