package seniorproject.badger;

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
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class GroupCreate extends AppCompatActivity implements View.OnClickListener {

    EditText gnText;
    EditText gdText;
    Button createGroupBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_create);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.showOverflowMenu();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        gnText = (EditText) findViewById(R.id.groupNameText);
        gdText = (EditText) findViewById(R.id.groupDescriptionText);
        createGroupBtn = (Button) findViewById(R.id.createGroupButton);
        createGroupBtn.setOnClickListener(this);

    }

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


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.createGroupButton:
                String name = gnText.getText().toString();
                String desc = gdText.getText().toString();
                User cUser = BadgerApp.getCurrentUser();
                Database db = new Database();
                try {
                    Group group = db.createGroup(name, desc, cUser.getId());
                    db.addUserToGroup(cUser.getId(), group.getGroupID());
                    ((BadgerApp) getApplication()).setCurrentUser(db.getUser(Integer.parseInt(cUser.getId())));

                    Intent myIntent = new Intent(GroupCreate.this, GroupHome.class);
                    myIntent.putExtra("groupName", group.getGroupName());
                    myIntent.putExtra("description", group.getDescription());
                    myIntent.putExtra("adminID", group.getAdminID());
                    myIntent.putExtra("groupID", group.getGroupID());
                    startActivity(myIntent);
                }
                catch (IllegalArgumentException e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
                catch(UserNotFoundException unfe){
                    Toast.makeText(this, unfe.getMessage(), Toast.LENGTH_LONG).show();
                }
       }
    }
}
