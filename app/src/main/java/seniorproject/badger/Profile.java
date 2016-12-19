package seniorproject.badger;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
import java.util.ArrayList;
import java.util.List;

import android.graphics.BitmapFactory;

import com.squareup.picasso.Picasso;


public class Profile extends AppCompatActivity implements View.OnClickListener {

    private TextView levelText, recentBadgeAuthor, recentDescription, newBadgeText;
    private TextView badgeCountEarned, badgeCountGiven;
    private Toolbar toolbar;
    private ImageView viewRecent;
    private User user;
    private User friend;
    private Button badgesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        badgeCountEarned = (TextView) findViewById(R.id.badgeCountEarned);
        badgeCountGiven = (TextView) findViewById(R.id.badgeCountGiven);
        levelText = (TextView) findViewById(R.id.levelTextView);

        badgesButton = (Button) findViewById(R.id.badgesButton);
        badgesButton.setOnClickListener(this);
        recentBadgeAuthor =(TextView) findViewById(R.id.textView7);
        recentDescription = (TextView) findViewById(R.id.textView8);

        newBadgeText = (TextView) findViewById(R.id.textView5);

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
        if (FriendSearch.isFriend()) { // friends profile
            Button giveBadges = (Button) findViewById(R.id.editOrGiveButton);
            giveBadges.setOnClickListener(this);

            Button friends = (Button) findViewById(R.id.groupsButton);
            friends.setVisibility(View.GONE);

            int level = ((friend.getBadgeIds().length) + (friend.getReceivedBadges().length)) / 3;
            levelText.setText("Level: " + String.valueOf(level));

            toolbar.setTitle(friend.getUserName() + "'s Profile");


            badgeCountEarned.setText("" + friend.getReceivedBadges().length);
            badgeCountGiven.setText("" + friend.getBadgeIds().length);
        }
        else
        { // user profile
            List<String> arr = user.getNewBadgeIds();

            if(arr.size() > 0){
                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
                dlgAlert.setTitle("You have earned a new Badge!");
                dlgAlert.setMessage("Go see it in Your Badges");
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();

                dlgAlert.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //dismiss the dialog
                            }
                        });
                user.dismissNewBadges();
            }


            badgesButton.setText("Your Badges");

            badgeCountEarned.setText("" + user.getReceivedBadges().length);
            badgeCountGiven.setText("" + user.getBadgeIds().length);

            Button giveBadges = (Button) findViewById(R.id.editOrGiveButton);
            giveBadges.setVisibility(View.GONE);

            Button friends = (Button) findViewById(R.id.groupsButton);
            friends.setOnClickListener(this);

            int level = ((user.getBadgeIds().length) + (user.getReceivedBadges().length)) / 3;
            levelText.setText("Level: " + String.valueOf(level));

            toolbar.setTitle(user.getUserName() + "'s Profile");

        }

        toolbar.setTitleTextColor(Color.BLACK);
        toolbar.showOverflowMenu();
        setSupportActionBar(toolbar);

    }

    /**
     * displays most recent earned badge
     */
    public void showRecent() {
        String badgeIds[];
        if (!FriendSearch.isFriend()) {
            User currentUser = BadgerApp.getCurrentUser();
            badgeIds = currentUser.getReceivedBadges();
        } else {
            User friendUser = BadgerApp.getFriendUser();
            badgeIds = friendUser.getReceivedBadges();
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
        final String author;
        if(recent != 0) {
            final String authorID = badges[recent - 1].getAuthorID();
            final String description = badges[recent - 1].getDescription();
            final Badge currentBadge = badges[recent - 1];
            final String badgeName = badges[recent - 1].getBadgeName();

            try {
                author = db.getUser(Integer.valueOf(authorID)).getUserName();

                Picasso.with(this).load(currentBadge.getImageURL()).into(viewRecent);
                recentBadgeAuthor.setText("From: " + author);
                recentDescription.setText(description);
                newBadgeText.setText("Check Out Your Newest Badge!");


            } catch (UserNotFoundException e) {


                e.printStackTrace();

            }
        }
        else{

            recentBadgeAuthor.setText("");
            recentDescription.setText("No Badges!  Go Earn One!");
            newBadgeText.setText("");
            //from.setText("You Have No Badges!");
            viewRecent.setImageResource(R.drawable.nobadges);
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

            case R.id.groupsOption:
                startActivity(new Intent(this, GroupList.class));
                break;

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
                startActivity(new Intent(this, CreateBadge.class));
                break;
        }
    }
}
