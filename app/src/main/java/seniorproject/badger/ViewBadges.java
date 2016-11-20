package seniorproject.badger;

import android.os.Bundle;
import android.util.Log;
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

        User currentUser = BadgerApp.getCurrentUser();
        String[] badgeIds = currentUser.getBadgeIds();
        Badge[] badges = new Badge[badgeIds.length];
        Database db = new Database();
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
            scrollLayout.addView(imageButton);
        }
    }
}
