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
     * @return
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setEmailAddress(String email) {
        this.emailAddress = email;
    }


    public void setUserName(String name) {
        this.userName = name;
    }

    public String[] getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(String[] groupIds) {
        this.groupIds = groupIds;
    }

    public String[] getOwnedGroups() {
        return ownedGroups;
    }

    public void setOwnedGroups(String[] ownedGroups) {
        this.ownedGroups = ownedGroups;
    }

    public String[] getBadgeIds() {
        return badgeIds;
    }

    public void setBadgeIds(String[] badgeIds) {
        this.badgeIds = badgeIds;
    }

    public String[] getReceivedBadges() {
        return receivedBadges;
    }

    public void setReceivedBadges(String[] receivedBadges) {
        this.receivedBadges = receivedBadges;
    }

    public String[] getFriendIds() {
        return friendIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
