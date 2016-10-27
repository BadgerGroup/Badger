package seniorproject.badger;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

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

}
