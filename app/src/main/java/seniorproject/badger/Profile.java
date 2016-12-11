package seniorproject.badger;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView;
import java.io.InputStream;
import android.graphics.BitmapFactory;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.squareup.picasso.Picasso;


public class Profile extends AppCompatActivity implements View.OnClickListener {

    private TextView usernameText, recentBadgeAuthor, recentDescription;
    private TextView badgeCount;
    private Toolbar toolbar;
    private ImageView viewRecent;
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
        recentBadgeAuthor =(TextView) findViewById(R.id.textView7);
        recentDescription = (TextView) findViewById(R.id.textView8);


        toolbar = (Toolbar) findViewById(R.id.toolbar);

        viewRecent = (ImageView) findViewById(R.id.imageView4);
        showRecent();
    }


    public void onResume() {
        super.onResume();

        BadgerApp app = (BadgerApp) getApplication();
        user = app.getCurrentUser();
        friend = app.getFriendUser();

        //sets give badge button visible if this is friend's page
        if (FriendSearch.isFriend() == true) { // friends profile
            Button giveBadges = (Button) findViewById(R.id.editOrGiveButton);
            giveBadges.setOnClickListener(this);

            Button friends = (Button) findViewById(R.id.groupsButton);
            friends.setVisibility(View.GONE);

            usernameText.setText(friend.getUserName());
            toolbar.setTitle(friend.getUserName() + "'s Profile");

            badgeCount.setText("" + friend.getBadgeIds().length);
        } else { // user profile

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

    public void showRecent() {
        String badgeIds[];
        if (!FriendSearch.isFriend()) {
            User currentUser = BadgerApp.getCurrentUser();
            badgeIds = currentUser.getBadgeIds();
        } else {
            User friendUser = BadgerApp.getFriendUser();
            badgeIds = friendUser.getBadgeIds();
        }
        final Badge[] badges = new Badge[badgeIds.length];
        final Database db = new Database();
        for (int i = 0; i < badges.length; i++) {
            try {
                Badge badge = db.getBadge(badgeIds[i]);
                badges[i] = badge;
            } catch (BadgeNotFoundException e) {
                Log.e("Database", Log.getStackTraceString(e));
                return;
            }
        }
        int recent = badges.length;
        final String authorID = badges[recent-1].getAuthorID();
        final String description = badges[recent-1].getDescription();
        final String author;
        final Badge currentBadge = badges[recent-1];
        final String badgeName = badges[recent-1].getBadgeName();
        try {
            author = db.getUser(Integer.valueOf(authorID)).getUserName();

            Picasso.with(this).load(currentBadge.getImageURL()).into(viewRecent);
            recentBadgeAuthor.setText(author);
            recentDescription.setText(description);


        } catch (UserNotFoundException e) {


            e.printStackTrace();

        }
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
                Database db = new Database();
                for (String id : BadgerApp.getCurrentUser().getBadgeIds()) {
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
