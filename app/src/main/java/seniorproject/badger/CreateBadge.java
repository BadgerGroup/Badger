package seniorproject.badger;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Kevin on 11/19/2016.
 *
 * Activity for creating new badges.
 */

public class CreateBadge extends BadgeScreen {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }


    public void addListenerOnButton() {
        super.addListenerOnButton();
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText badgeNameText = (EditText) findViewById(R.id.badgeNameEditText);
                EditText badgeDescriptionText = (EditText) findViewById(R.id.badgeDescriptionEditText);

                if (badgeNameText == null || badgeDescriptionText == null) {
                    Log.e("Database", "Badge name/descr is null.");
                    return;
                }

                String url = "https://s3-us-west-2.amazonaws.com/badge-bucket/Badge" + CreateBadge.this.selected
                        + ".jpg";
                User currentUser = BadgerApp.getCurrentUser();
                Database db = new Database();
                Badge badge = db.createBadge(badgeNameText.getText().toString(), url, badgeDescriptionText.getText().toString(),
                                currentUser.getId(), null);
                BadgerApp app = (BadgerApp) getApplication();
                try {
                    app.setCurrentUser(db.getUser(Integer.valueOf(currentUser.getId())));
                } catch (UserNotFoundException e) {
                    Log.e("Database", Log.getStackTraceString(e));
                    return;
                }

                if (FriendSearch.isFriend()) {
                    User friend = BadgerApp.getFriendUser();
                    try {
                        db.awardBadge(badge, friend);
                    }
                    catch (IllegalArgumentException e) {
                        Toast.makeText(getApplicationContext(), "Invalid name or description.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    try {
                        app.setFriendUser(db.getUser(friend.getUserName()));
                    } catch (UserNotFoundException e) {
                        Log.e("Database", Log.getStackTraceString(e));
                    }

                }

                startActivity(new Intent(CreateBadge.this, Profile.class));
            }
        });

    }

}