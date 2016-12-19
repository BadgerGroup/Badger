package seniorproject.badger;

import android.app.Application;

/**
 * Created by Kevin on 11/14/2016.
 */
public class BadgerApp extends Application {
    static private User currentUser;
    static private User friendUser;


    /**
     * returns the selected friend
     * @return friendUser
     */
    static public User getFriendUser() {
        return friendUser;
    }


    /**
     * sets the current friend as the friend user
     * @param friendUser
     */
    public void setFriendUser(User friendUser) {
        this.friendUser = friendUser;
    }


    /**
     * sets the user as the current user
     * @param user
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }


    /**
     * gets the current user
     * @return currentUser
     */
    static public User getCurrentUser() {
        return currentUser;
    }
}
