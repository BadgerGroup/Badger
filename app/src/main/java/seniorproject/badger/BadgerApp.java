package seniorproject.badger;

import android.app.Application;

/**
 * Created by Kevin on 11/14/2016.
 */
public class BadgerApp extends Application {
    private User currentUser;

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
