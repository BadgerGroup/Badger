package seniorproject.badger;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Kevin on 11/19/2016.
 */

public class ViewBadges extends BadgeScreen {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        showBadges();
    }

    public void showBadges() {
        scrollLayout = (ViewGroup) findViewById(R.id.scrollLayout);
        scrollLayout.removeAllViewsInLayout();
        String badgeIds[];
        if (!FriendSearch.isFriend()) {
            User currentUser = BadgerApp.getCurrentUser();
            badgeIds = currentUser.getBadgeIds();
        }else{
            User friendUser = BadgerApp.getFriendUser();
            badgeIds = friendUser.getBadgeIds();
        }
        final Badge[] badges = new Badge[badgeIds.length];
        final Database db = new Database();
        for (int i = 0; i < badges.length; i++) {
            try {
                Badge badge = db.getBadge(badgeIds[i]);
                badges[i] = badge;
            }
            catch (BadgeNotFoundException e) {
                Log.e("Database", Log.getStackTraceString(e));
                return;
            }
        }
        for (int i = 0; i < badges.length; i++) {
            ImageButton imageButton = new ImageButton(this);
            imageButton.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Picasso.with(this).load(badges[i].getImageURL()).into(imageButton);
            final String authorID = badges[i].getAuthorID();
            final String description = badges[i].getDescription();
            final String author;
            final Badge currentBadge = badges[i];
            try {
                author = db.getUser(Integer.valueOf(authorID)).getUserName();

                imageButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Picasso.with(ViewBadges.this).load(currentBadge.getImageURL()).resize(1000,1000).into(image);

                        //creates popup window
                        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(ViewBadges.this);
                        dlgAlert.setMessage(description);
                        dlgAlert.setTitle("From: " + author);
                        dlgAlert.setPositiveButton("OK", null);
                        dlgAlert.setCancelable(true);
                        dlgAlert.create().show();
                        dlgAlert.setPositiveButton("Ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dismiss the dialog
                                    }
                                });
                    }

                });

            } catch (UserNotFoundException e) {
                e.printStackTrace();
            }


            scrollLayout.addView(imageButton);
        }
    }
}
