package seniorproject.badger;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


import java.util.ArrayList;

import static android.R.interpolator.linear;

public class FriendsList extends AppCompatActivity implements View.OnClickListener {


    @SuppressWarnings("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        createButtons();




          //tabs for all and close friends- most likely to be deleted
//        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
//        tabHost.setup();
//
//        TabSpec closeTab = tabHost.newTabSpec("Close");
//        TabSpec allTab = tabHost.newTabSpec("All");
//
//        closeTab.setIndicator("Close").setContent(R.id.Close);
//
//        allTab.setIndicator("All").setContent(R.id.All);
//
//        tabHost.addTab(closeTab);
//        tabHost.addTab(allTab);
//
//        tabHost.setCurrentTab(0);
    }

    private void createButtons() {
        int size = FriendSearch.allFriends.size();
        for (int i = 0; i <= size - 1; i++) {
            LinearLayout layout = (LinearLayout) findViewById(R.id.content_friends_list);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);

            layout.setOrientation(LinearLayout.VERTICAL);

            Button btn = new Button(this);

            for (int j = 0; j <= size - 1; j++) {
                String name = FriendSearch.allFriends.get(j).getUserName();
                btn.setText(name);

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //goes to this friend's profile page
                        FriendSearch.setIsFriend(true);
                        startActivity(new Intent(FriendsList.this, Profile.class));
                    }
                });
                layout.addView(btn);
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
