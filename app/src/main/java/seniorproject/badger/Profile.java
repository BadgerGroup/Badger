package seniorproject.badger;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

    // for trophy case
//        ImageView imgClick1 = (ImageView)findViewById(R.id.badgeView1);
//        imgClick1.setOnClickListener(this);
//
//        ImageView imgClick2 = (ImageView)findViewById(R.id.badgeView2);
//        imgClick2.setOnClickListener(this);
//
//        ImageView imgClick3 = (ImageView)findViewById(R.id.badgeView3);
//        imgClick3.setOnClickListener(this);
//
//        ImageView imgClick4 = (ImageView)findViewById(R.id.badgeView4);
//        imgClick4.setOnClickListener(this);
//
//        ImageView imgClick5 = (ImageView)findViewById(R.id.badgeView5);
//        imgClick5.setOnClickListener(this);



        TextView usernameText = (TextView) findViewById(R.id.usernameTextView);

        Button badges = (Button) findViewById(R.id.badgesButton);
        badges.setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById (R.id.toolbar);

        BadgerApp app = (BadgerApp) getApplication();
        User user = app.getCurrentUser();
        User friend = app.getFriendUser();

        //sets give badge button visible if this is friend's page
        if (FriendSearch.isFriend() == true) {
            Button giveBadges = (Button) findViewById(R.id.editOrGiveButton);
            giveBadges.setOnClickListener(this);

            Button friends = (Button) findViewById(R.id.groupsButton);
            friends.setVisibility(View.GONE);

            usernameText.setText(friend.getUserName());
            toolbar.setTitle(friend.getUserName() + "'s Profile");
        }
        else{
            Button giveBadges = (Button) findViewById(R.id.editOrGiveButton);
            giveBadges.setVisibility(View.GONE);

            Button friends = (Button) findViewById(R.id.groupsButton);
            friends.setOnClickListener(this);

            usernameText.setText(user.getUserName());
            toolbar.setTitle(user.getUserName() + "'s Profile");
        }



//        BadgerApp app = (BadgerApp) getApplication();
//        User user = app.getCurrentUser();
//        User friend = app.getFriendUser();
//        if (user != null) {
//            usernameText.setText(user.getUserName());
//            toolbar.setTitle(user.getUserName() + "'s Profile");
//        }
//        else {
//            Log.e("Database", "User is null.");
//        }

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
                FriendSearch.setIsFriend(false);
                startActivity(new Intent(this, FriendsList.class));
                break;

//            case R.id.groupsOption:
//                startActivity(new Intent(this, GroupList.class));
//                break;

            case R.id.myProfileOption:
                FriendSearch.setIsFriend(false);
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
                startActivity(new Intent(this, FriendsList.class));
                break;

            case R.id.badgesButton:
                startActivity(new Intent(this, BadgeList.class));
                break;

            case R.id.editOrGiveButton:
                startActivity(new Intent(this, BadgeScreen.class));
                break;

            //For trophy case

//            case R.id.badgeView1:
//                startActivity(new Intent(this, Badge.class));
//                break;
//
//            case R.id.badgeView2:
//                startActivity(new Intent(this, Badge.class));
//                break;
//
//            case R.id.badgeView3:
//                startActivity(new Intent(this, Badge.class));
//                break;
//
//            case R.id.badgeView4:
//                startActivity(new Intent(this, Badge.class));
//                break;
//
//            case R.id.badgeView5:
//                startActivity(new Intent(this, Badge.class));
//                break;
        }
    }
}
