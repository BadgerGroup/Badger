package seniorproject.badger;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Button;

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
        createButtons();
    }

    @Override
    protected void onResume() {
        super.onResume();
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


            case android.R.id.home:
                NavUtils.navigateUpTo(this, getIntent());
                return true;
        }
        return true;
    }

    private void createButtons() {
        User cUser = BadgerApp.getCurrentUser();
        LinearLayout layout = (LinearLayout) findViewById(R.id.content_group_list);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);

        layout.setOrientation(LinearLayout.VERTICAL);
        String[] groupIds = cUser.getGroupIds();
        int size = groupIds.length;
        for (int j = 0; j <= size; j++) {
            Database db = new Database();
            Button btn = new Button(this);
            btn.setBackgroundColor(0xffff8800);
            btn.setWidth(1200);
            btn.getLayoutParams();
            params.setMargins(100, 35, 100, 35);
            btn.setLayoutParams(params);
            if(j == size)
            {
                btn.setText("Create Group");
                btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v)
                    {
                            startActivity(new Intent(GroupList.this, GroupCreate.class));
                    }
                });
            }
            else
            {

                String gID = groupIds[j];
                try {
                    final Group group = db.getGroup(gID);
                    String name = group.getGroupName();
                    btn.setText(name);

                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent myIntent = new Intent(GroupList.this, GroupHome.class);
                            myIntent.putExtra("groupName", group.getGroupName());
                            myIntent.putExtra("description", group.getDescription());
                            myIntent.putExtra("adminID", group.getAdminID());
                            myIntent.putExtra("groupID", group.getGroupID());
                            startActivity(myIntent);
                        }
                    });
                }
                catch (Exception unfe) {
                    Log.e("Database", Log.getStackTraceString(unfe));
                }

            }
            layout.addView(btn);
        }
    }

}
