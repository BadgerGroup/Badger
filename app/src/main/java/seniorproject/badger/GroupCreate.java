package seniorproject.badger;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

        gnText = (EditText) findViewById(R.id.groupNameText);
        gdText = (EditText) findViewById(R.id.groupDescriptionText);
        createGroupBtn = (Button) findViewById(R.id.createGroupButton);
        createGroupBtn.setOnClickListener(this);

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
        }
    }
}
