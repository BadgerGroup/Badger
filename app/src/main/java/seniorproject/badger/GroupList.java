package seniorproject.badger;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class GroupList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Groups");
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);

        toolbar.showOverflowMenu();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_group_list, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId()) {
            case R.id.myProfileOption:
                startActivity(new Intent(this, Profile.class));
                break;

            case R.id.createGroupOption:
                Toast toast = Toast.makeText(this, "Group creation coming soon...", Toast.LENGTH_SHORT);
                toast.show();
                break;

            case android.R.id.home:
                NavUtils.navigateUpTo(this, getIntent());
                return true;
        }
        return true;
    }

}
