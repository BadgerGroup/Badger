package seniorproject.badger;

import android.app.Application;

/**
 * Created by Kevin on 11/14/2016.
 */
public class BadgerApp extends Application {
    static private User currentUser;
    static private User friendUser;

    static public User getFriendUser() {
        return friendUser;
    }

    public void setFriendUser(User friendUser) {
        this.friendUser = friendUser;
    }


    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    static public User getCurrentUser() {
        return currentUser;
    }
}
