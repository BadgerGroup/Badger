package seniorproject.badger;


import android.app.Application;
import android.app.LocalActivityManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class FriendSearch extends AppCompatActivity implements View.OnClickListener {

    private static boolean isFriend;
    EditText name;
    private static String friendName;
    //public static ArrayList<User> allFriends = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_search);

        isFriend = false;

        name = (EditText) findViewById(R.id.friendText);
        friendName = name.getText().toString();

        Button seacrhButton = (Button) findViewById(R.id.searchButton);
        seacrhButton.setOnClickListener(this);



    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.searchButton:
                //will need to change once we cna get friends info from database
                String fName = name.getText().toString();
                Database db = new Database();
                try {
                    User user = BadgerApp.getCurrentUser();

                    User friend = db.getUser(fName);
                    Log.d("Database", "Mactched user: " + friend.getUserName());
                    String uID = user.getId();
                    String fID = friend.getId();
                    if (!db.addFriend(uID, fID)) {
                        Toast.makeText(getApplicationContext(), "User is already added as friend.", Toast.LENGTH_LONG).show();
                        return;
                    }
                    Log.d("Database", uID);
                    user = db.getUser(Integer.valueOf(uID));
                    friend = db.getUser(fName);
                    ((BadgerApp) getApplication()).setCurrentUser(user);
                    ((BadgerApp) getApplication()).setFriendUser(friend);
                    startActivity(new Intent(this, FriendsList.class));

                }
                catch (UserNotFoundException unfe) {
                    //creates popup window
                    AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
                    dlgAlert.setMessage(unfe.getMessage());
                    dlgAlert.setTitle("Try Again");
                    dlgAlert.setPositiveButton("OK", null);
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();

                    dlgAlert.setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //dismiss the dialog
                                }
                            });
                }
                break;
        }
    }


    public static boolean isFriend() {
        return isFriend;
    }

    public static void setIsFriend(boolean friend) {
        isFriend = friend;
    }

    public static void setFriendName(String fName) {
        friendName = fName;
    }

    //public static ArrayList<User> getAllFriends() {
    //    return FriendsList.allFriends;
    //}

    public static String getFriendName(){
        return friendName;
    }

}




