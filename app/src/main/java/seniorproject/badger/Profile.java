package seniorproject.badger;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Profile extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button groups = (Button) findViewById(R.id.groupsButton);
        groups.setOnClickListener(this);

        Button badges = (Button) findViewById(R.id.badgesButton);
        badges.setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById (R.id.toolbar);
        toolbar.setTitle("(User)'s Profile");
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
                startActivity(new Intent(this, FriendsList.class));
                break;

            case R.id.groupsOption:
                startActivity(new Intent(this, GroupList.class));
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
                startActivity(new Intent(this, GroupList.class));
                break;

            case R.id.badgesButton:
                startActivity(new Intent(this, BadgeList.class));
                break;
        }
    }
}
