package seniorproject.badger;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Kevin on 11/19/2016.
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
                db.createBadge(badgeNameText.getText().toString(), url, badgeDescriptionText.getText().toString(),
                                currentUser.getId(), null);
                BadgerApp app = (BadgerApp) getApplication();
                try {
                    app.setCurrentUser(db.getUser(Integer.valueOf(currentUser.getId())));
                } catch (UserNotFoundException e) {
                    Log.e("Database", Log.getStackTraceString(e));
                    return;
                }

                startActivity(new Intent(CreateBadge.this, Profile.class));
            }
        });

    }

}