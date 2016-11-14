package seniorproject.badger;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class User {

    private String userName;
    private String emailAddress;

    private String id;
    private String[] groupIds;
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

            JSONArray badgeArray = json.getJSONArray("badge_ids");
            badgeIds = new String[badgeArray.length()];
            for (int i = 0; i < badgeArray.length(); i++) {
                badgeIds[i] = badgeArray.getString(i);
            }

            JSONArray trophyCase = json.getJSONArray("trophy_case");
            receivedBadges = new String[trophyCase.length()];
            for (int i = 0; i < trophyCase.length(); i++) {
                receivedBadges[i] = trophyCase.getString(i);
            }
        } catch (JSONException e) {
            Log.e("Database", e.toString());
        }
    }

    public User() {

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
}
