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

import java.util.ArrayList;


public class FriendSearch extends AppCompatActivity implements View.OnClickListener {




    private static boolean isFriend;
    private static EditText friendName;
    public static ArrayList<User> allFriends = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_search);

        isFriend = false;

        friendName = (EditText) findViewById(R.id.friendText);

        Button seacrhButton = (Button) findViewById(R.id.searchButton);
        seacrhButton.setOnClickListener(this);

    }

    public static String getFriendName(){
        String fName = friendName.getText().toString();
        return fName;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.searchButton:
                String fName = getFriendName();
                int size = LoginCredentials.allUsers.size();
                ArrayList<String> names = new ArrayList<String>(size);
                for(int i = 0; i <= size -1 ; i++){
                    names.add(LoginCredentials.allUsers.get(i).getUserName());

                }
                boolean found = names.contains(fName);
                if (found){
                    isFriend = true;
                    User friend = new User();
                    friend.setUserName(fName);
                    allFriends.add(friend);


                    //for now if true goes back to FriendList page
                    startActivity(new Intent(this, Profile.class));
                    break;
                }
                else {
                    //for now if false goes to Profile page
                    startActivity(new Intent(this, Profile.class));
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

    public static void setFriendName(EditText fName) {
        friendName = fName;
    }

    public static ArrayList<User> getAllFriends() {
        return allFriends;
    }

}




