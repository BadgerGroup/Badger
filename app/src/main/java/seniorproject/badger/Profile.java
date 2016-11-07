package seniorproject.badger;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ImageView;

public class Profile extends AppCompatActivity implements View.OnClickListener {

    //ImageView imgClick;

    //TODO: Be able to store the logged in user's credentials and the credentials of the user
    //TODO:   we are viewing. This second value will determine if the "Give Badges" button
    //TODO:   will be visible. This value will also change the name of the title bar, username, and trophy case.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ImageView imgClick1 = (ImageView)findViewById(R.id.badgeView1);
        imgClick1.setOnClickListener(this);

        ImageView imgClick2 = (ImageView)findViewById(R.id.badgeView2);
        imgClick2.setOnClickListener(this);

        ImageView imgClick3 = (ImageView)findViewById(R.id.badgeView3);
        imgClick3.setOnClickListener(this);

        ImageView imgClick4 = (ImageView)findViewById(R.id.badgeView4);
        imgClick4.setOnClickListener(this);

        ImageView imgClick5 = (ImageView)findViewById(R.id.badgeView5);
        imgClick5.setOnClickListener(this);

        Button groups = (Button) findViewById(R.id.groupsButton);
        groups.setOnClickListener(this);

        Button badges = (Button) findViewById(R.id.badgesButton);
        badges.setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById (R.id.toolbar);
        String userName = LoginCredentials.newUser.getUserName();
        toolbar.setTitle("" + userName  +"'s Profile");
        toolbar.setTitleTextColor(Color.BLACK);
        toolbar.showOverflowMenu();
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId()){
            case R.id.friendsOption:
                startActivity(new Intent(this, FriendsList.class));
                break;

            case R.id.groupsOption:
                startActivity(new Intent(this, GroupList.class));
                break;

            case R.id.myProfileOption:
                startActivity(new Intent(this, Profile.class));
                break;

            case R.id.settingsOption:
                Toast toast = Toast.makeText(this, "Settings option coming soon...", Toast.LENGTH_SHORT);
                toast.show();
                //startActivity(new Intent(this, Settings.class)); --> TODO: Create Settings class
                break;
        }
        return true;
    }

    public void onClick(View v)
    {
        switch(v.getId()) {
            case R.id.groupsButton:
                startActivity(new Intent(this, GroupList.class));
                break;

            case R.id.badgesButton:
                startActivity(new Intent(this, BadgeList.class));
                break;

            case R.id.badgeView1:
                startActivity(new Intent(this, Badge.class));
                break;

            case R.id.badgeView2:
                startActivity(new Intent(this, Badge.class));
                break;

            case R.id.badgeView3:
                startActivity(new Intent(this, Badge.class));
                break;

            case R.id.badgeView4:
                startActivity(new Intent(this, Badge.class));
                break;

            case R.id.badgeView5:
                startActivity(new Intent(this, Badge.class));
                break;
        }
    }
}
