package seniorproject.badger;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;


public class FriendsList extends AppCompatActivity implements View.OnClickListener {
    public ArrayList<String> allFriends = new ArrayList<>();

    @SuppressWarnings("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        createButtons();
    }

    private void createButtons() {

        User cUser = BadgerApp.getCurrentUser();
        int size = cUser.getFriendIds().length;
        LinearLayout layout = (LinearLayout) findViewById(R.id.content_friends_list);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);

        layout.setOrientation(LinearLayout.VERTICAL);
        //creates buttons based on number of friends
        for (int j = 0; j <= size - 1; j++) {
            Database db = new Database();
            Button btn = new Button(this);
            btn.setBackgroundColor(0xffff8800);
            btn.setWidth(1200);
            btn.getLayoutParams();
            params.setMargins(100, 35, 100, 35);
            btn.setLayoutParams(params);
            String fID = cUser.getFriendIds()[j];
            final int friendID = Integer.parseInt(fID);
            try {
                String name = db.getUser(friendID).getUserName();
                btn.setText(name);

                final User fUser = db.getUser(friendID);

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //goes to this friend's profile page
                        FriendSearch.setIsFriend(true);

                        ((BadgerApp) getApplication()).setFriendUser(fUser);
                        String fName = fUser.getUserName();
                        //FriendSearch.setFriendName(fName);
                        startActivity(new Intent(FriendsList.this, Profile.class));
                    }
                });
                layout.addView(btn);
            }
            catch (UserNotFoundException unfe){
                Log.e("Database", Log.getStackTraceString(unfe));
            }


        }
    }



    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_friends_list, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId()){
            case R.id.addFriendOption:
                startActivity(new Intent(this, FriendSearch.class));
                break;

            case R.id.myProfileOption:
                FriendSearch.setIsFriend(false);
                startActivity(new Intent(this, Profile.class));
                break;

            case android.R.id.home:
                NavUtils.navigateUpTo(this, getIntent());
                return true;
        }

        return true;
    }


    @Override
    public void onClick(View v) {

    }
}
