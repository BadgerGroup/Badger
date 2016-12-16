package seniorproject.badger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class GroupAdd extends AppCompatActivity {

    String groupName;
    String description;
    String adminID;
    String groupID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        groupName = getIntent().getExtras().getString("groupName");
        description = getIntent().getExtras().getString("description");
        adminID = getIntent().getExtras().getString("adminID");
        groupID = getIntent().getExtras().getString("groupID");

        createButtons();
    }

    private void createButtons() {
        //allFriends = (ArrayList<String>) Arrays.asList(User.getFriendIds());


        User cUser = BadgerApp.getCurrentUser();
        int size = cUser.getFriendIds().length;
        LinearLayout layout = (LinearLayout) findViewById(R.id.content_group_add);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);

        layout.setOrientation(LinearLayout.VERTICAL);

        for (int j = 0; j <= size - 1; j++) {
            final Database db = new Database();
            Button btn = new Button(this);
            btn.setBackgroundColor(0xffff8800);
            btn.setWidth(1200);
            btn.getLayoutParams();
            params.setMargins(100, 35, 100, 35);
            btn.setLayoutParams(params);
            final String fID = cUser.getFriendIds()[j];
            final int friendID = Integer.parseInt(fID);
            try {
                String name = db.getUser(friendID).getUserName();
                btn.setText(name);

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.addUserToGroup(fID, groupID);


                        Intent myIntent = new Intent(GroupAdd.this, GroupHome.class);
                        myIntent.putExtra("groupName", groupName);
                        myIntent.putExtra("description", description);
                        myIntent.putExtra("adminID", adminID);
                        myIntent.putExtra("groupID", groupID);
                        startActivity(myIntent);
                    }
                });
                layout.addView(btn);
            }
            catch (UserNotFoundException unfe){
                Log.e("Database", Log.getStackTraceString(unfe));
            }


        }
    }
}
