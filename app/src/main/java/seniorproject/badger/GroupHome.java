package seniorproject.badger;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View;

import org.w3c.dom.Text;

public class GroupHome extends AppCompatActivity {

    TextView groupName;
    TextView description;
    String adminID;
    String groupID;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        groupName = (TextView) findViewById((R.id.groupHomeNameText));
        description = (TextView) findViewById((R.id.groupHomeDescText));

        groupName.setText(getIntent().getExtras().getString("groupName"));
        description.setText(getIntent().getExtras().getString("description"));
        adminID = getIntent().getExtras().getString("adminID");
        groupID = getIntent().getExtras().getString("groupID");
        createButtons();
    }

    private void createButtons() {
        Database db = new Database();
        Group group = db.getGroup(groupID);
        String[] members = group.getMembers();

        int size = members.length;
        LinearLayout layout = (LinearLayout) findViewById(R.id.group_member_list);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);

        layout.setOrientation(LinearLayout.VERTICAL);

        for (int j = 0; j <= size; j++) {
            Button btn = new Button(this);
            btn.setBackgroundColor(0xffff8800);
            btn.setWidth(1200);
            btn.getLayoutParams();
            params.setMargins(100, 35, 100, 35);
            btn.setLayoutParams(params);
            if(j == size)
            {
                if(BadgerApp.getCurrentUser().getId().equals(adminID))
                {
                    btn.setText("Add Friend to Group");
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent myIntent = new Intent(GroupHome.this, GroupAdd.class);
                            myIntent.putExtra("groupName", groupName.getText());
                            myIntent.putExtra("description", description.getText());
                            myIntent.putExtra("adminID", adminID);
                            myIntent.putExtra("groupID", groupID);
                            startActivity(myIntent);
                        }
                    });
                    layout.addView(btn);
                }
            }
            else {
                final String memberID = members[j];
                try {
                    final int memberIDInt = Integer.parseInt(memberID);
                    final User fUser = db.getUser(memberIDInt);

                    if(memberID.equals(adminID))
                        btn.setText(fUser.getUserName() + " (Admin)");
                    else
                        btn.setText(fUser.getUserName());


                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //goes to this friend's profile page
                            if (memberID.equals(BadgerApp.getCurrentUser().getId()))
                            {
                            } else {
                                FriendSearch.setIsFriend(true);
                            }

                            ((BadgerApp) getApplication()).setFriendUser(fUser);
                            String fName = fUser.getUserName();
                            FriendSearch.setFriendName(fName);
                            startActivity(new Intent(GroupHome.this, Profile.class));
                        }
                    });
                    layout.addView(btn);
                } catch (UserNotFoundException unfe) {
                    Log.e("Database", Log.getStackTraceString(unfe));
                }
            }

        }
    }



}
