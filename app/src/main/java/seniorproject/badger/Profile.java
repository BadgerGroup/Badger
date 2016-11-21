package seniorproject.badger;

import android.content.Intent;
import android.graphics.Bitmap;
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
import java.io.InputStream;
import android.graphics.BitmapFactory;



public class Profile extends AppCompatActivity implements View.OnClickListener {

    private TextView usernameText;
    private TextView badgeCount;
    private Toolbar toolbar;
    private User user;
    private User friend;
    private Button badgesButton;

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




        badgeCount = (TextView) findViewById(R.id.badgeCount);
        usernameText = (TextView) findViewById(R.id.usernameTextView);

        badgesButton = (Button) findViewById(R.id.badgesButton);
        badgesButton.setOnClickListener(this);

        toolbar = (Toolbar) findViewById (R.id.toolbar);

    }

    public void onResume() {
        super.onResume();

        BadgerApp app = (BadgerApp) getApplication();
        user = app.getCurrentUser();
        friend = app.getFriendUser();

        //sets give badge button visible if this is friend's page
        if (FriendSearch.isFriend()) { // friends profile
            Button giveBadges = (Button) findViewById(R.id.editOrGiveButton);
            giveBadges.setOnClickListener(this);

            Button friends = (Button) findViewById(R.id.groupsButton);
            friends.setVisibility(View.GONE);

            usernameText.setText(friend.getUserName());
            toolbar.setTitle(friend.getUserName() + "'s Profile");

            badgeCount.setText("" + friend.getReceivedBadges().length);
        }
        else
        { // user profile

            badgesButton.setText("Your Badges");
            badgeCount.setText("" + user.getBadgeIds().length);

            Button giveBadges = (Button) findViewById(R.id.editOrGiveButton);
            giveBadges.setVisibility(View.GONE);

            Button friends = (Button) findViewById(R.id.groupsButton);
            friends.setOnClickListener(this);

            usernameText.setText(user.getUserName());
            toolbar.setTitle(user.getUserName() + "'s Profile");
        }

        toolbar.setTitleTextColor(Color.BLACK);
        toolbar.showOverflowMenu();
        setSupportActionBar(toolbar);

    }


    /**
     * Get the URL for a particular badge
     */
    private Bitmap getImageFromURL(String url)
    {
        Bitmap icon = null;

        try {
            InputStream in = new java.net.URL(url).openStream();
            icon = BitmapFactory.decodeStream(in);
        } catch(Exception e)
        {
            //Log.e("Error getting image", e.getMessage());
            //e.printStackTrace();
        }
        return icon;
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

            case R.id.createBadgeOption:
                startActivity(new Intent(this, CreateBadge.class));
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
                User owner;
                if (FriendSearch.isFriend()) {
                    owner = friend;
                }
                else {
                    owner = user;
                }
                Database db = new Database();
                for (String id : owner.getBadgeIds()) {
                    try {
                        Badge badge = db.getBadge(id);
                        Log.d("Database", "\n\tAuthor: " + badge.getAuthorID() + "\n\tName: " + badge.getBadgeName() + "\n\tDescr: " + badge.getDescription() + "\n\tURL: " + badge.getImageURL() + "\n\tRecip: " + badge.getRecipientID());
                    }
                    catch (BadgeNotFoundException e) {
                        Log.e("Database", Log.getStackTraceString(e));
                    }
                }
                startActivity(new Intent(this, ViewBadges.class));
                break;

            case R.id.editOrGiveButton:
                startActivity(new Intent(this, BadgeScreen.class));
                break;
        }
    }
}
