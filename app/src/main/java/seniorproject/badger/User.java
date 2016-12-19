package seniorproject.badger;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String userName;
    private String emailAddress;
    private String id;
    private String[] groupIds;
    private String[] friendIds;
    private String[] ownedGroups;
    private String[] badgeIds;
    private String[] receivedBadges;

    public User(String id, String name, String email){
        this.id = id;
        userName = name;
        emailAddress = email;
    }

    public User(JSONObject json) {
        try {
            id = json.getString("id");
            userName = json.getString("username");
            emailAddress = json.getString("email");

            this.friendIds = toArray( json.getJSONArray("friend_ids") );
            this.badgeIds = toArray( json.getJSONArray("badge_ids") );
            this.receivedBadges = toArray( json.getJSONArray("trophy_case") );
            this.groupIds = toArray(json.getJSONArray("group_ids"));
        } catch (JSONException e) {
            Log.e("Database", e.toString());
        } catch (NullPointerException npe) {
            Log.e("Database", npe.toString());
        }
    }

    /**
     * changes JSON array to array
     * @param json
     * @return String array
     */
    private String[] toArray(JSONArray json) {
        String[] result = null;
        try {
            if (json == null) {
                throw new IllegalArgumentException("toArray() received null JSONArray.");
            }
            result = new String[json.length()];
            for (int i = 0; i < json.length(); i++) {
                result[i] = json.getString(i);
            }
        }
        catch (JSONException je) {
            Log.e("Database", Log.getStackTraceString(je));
        }
        return result;
    }

    public User() {

    }

    /**
     * Get badges that have not been dismissed yet with dismissNewBadges()
     * @return String List
     */
    public List<String> getNewBadgeIds() {
        ArrayList<String> result = new ArrayList<>();
        Database db = new Database();
        int i = 0;
        for(String badgeId : getReceivedBadges()) {
            try {
                Badge badge = db.getBadge(badgeId);
                if (badge.isNew()) {
                    result.add(badgeId);
                    i++;
                }
            } catch (BadgeNotFoundException e) {
                Log.e("Database", Log.getStackTraceString(e));
            }
        }
        return result;
    }

    /**
     * Clears the NewBadgeIds array.
     * @return false only if an error occurred.
     */
    public boolean dismissNewBadges() {
        Database db = new Database();
        for(String id : getNewBadgeIds()) {
            if (!db.dismissBadge(id)) {
                return false;
            }
        }
        return true;
    }

    /**
     * returns user's emmail address
     * @return String
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * returns username
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * sets the user's email address
     * @param email
     */
    public void setEmailAddress(String email) {
        this.emailAddress = email;
    }

    /**
     * sets the user's name
     * @param name
     */
    public void setUserName(String name) {
        this.userName = name;
    }

    /**
     * returns the user's group ids
     * @return
     */
    public String[] getGroupIds() {
        return groupIds;
    }

    /**
     * sets the group ids
     * @param groupIds
     */
    public void setGroupIds(String[] groupIds) {
        this.groupIds = groupIds;
    }

    /**
     * returns the user's groups
     * @return String array
     */
    public String[] getOwnedGroups() {
        return ownedGroups;
    }

    /**
     * sets all of the user's owned groups
     * @param ownedGroups
     */
    public void setOwnedGroups(String[] ownedGroups) {
        this.ownedGroups = ownedGroups;
    }

    /**
     * returns a user's badge ids
     * @return String array
     */
    public String[] getBadgeIds() {
        return badgeIds;
    }

    /**
     * sets the badge ids
     * @param badgeIds
     */
    public void setBadgeIds(String[] badgeIds) {
        this.badgeIds = badgeIds;
    }

    /**
     * returns the user's received badges
     * @return String array
     */
    public String[] getReceivedBadges() {
        return receivedBadges;
    }

    /**
     * sets a user's received badges
     * @param receivedBadges
     */
    public void setReceivedBadges(String[] receivedBadges) {
        this.receivedBadges = receivedBadges;
    }

    /**
     * returns all of the user's friend ids
     * @return String array
     */
    public String[] getFriendIds() {
        return friendIds;
    }

    /**
     * returns a user's id
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     * sets a user's id
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }
}
