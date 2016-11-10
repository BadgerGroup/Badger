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
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

public class FriendsList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabSpec closeTab = tabHost.newTabSpec("Close");
        TabSpec allTab = tabHost.newTabSpec("All");

        closeTab.setIndicator("Close").setContent(R.id.Close);

        allTab.setIndicator("All").setContent(R.id.All);

        tabHost.addTab(closeTab);
        tabHost.addTab(allTab);

        tabHost.setCurrentTab(0);
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
                startActivity(new Intent(this, Profile.class));
                break;

            case android.R.id.home:
                NavUtils.navigateUpTo(this, getIntent());
                return true;
        }
        return true;
    }

}
