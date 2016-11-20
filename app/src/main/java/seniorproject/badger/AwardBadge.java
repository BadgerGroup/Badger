package seniorproject.badger;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Kevin on 11/19/2016.
 */

public class AwardBadge extends ViewBadges {

    public void onCreate(Bundle b) {
        super.onCreate(b);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
