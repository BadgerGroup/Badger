package seniorproject.badger;


import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Badge  {



    private String id;
    private String badgeName;
    private String authorID;
    private String recipientID;
    private String imageURL;
    private String description;
    private String isNew;

    public Badge(JSONObject json){
        try {
            id = json.getString("id");
            badgeName = json.getString("badge_name");
            authorID = json.getString("author_id");
            recipientID = json.getString("recipient_id");
            imageURL = json.getString("image_url");
            description = json.getString("badge_description");
            isNew = json.getString("is_new");
        } catch (JSONException e) {
            Log.e("Database", e.toString());
        } catch (NullPointerException npe) {
            Log.e("Database", npe.toString());
        }
    }

    public Badge(String name, String author, String recp, String url, String descr){
        badgeName = name;
        authorID = author;
        recipientID = recp;
        imageURL = url;
        description = descr;
    }

    /**
     * returns the name of the badge
     * @return String badgeName
     */
    public String getBadgeName() {
        return badgeName;
    }

    public void setBadgeName(String badgeName) {
        this.badgeName = badgeName;
    }

    /**
     * returns the creater of the badge
     * @return String authorID
     */
    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    /**
     * returns the ID of the user receiving the badge
     * @return String recipientID
     */
    public String getRecipientID() {
        return recipientID;
    }

    public void setRecipientID(String recipientID) {
        this.recipientID = recipientID;
    }

    /**
     * returns the URL for the badge image
     * @return imageURL
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * sets the url for the image
     * @param imageURL
     */
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    /**
     * returns the desription of the badge
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets the description of the badge
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * returns the id of the badge
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * sets the id of the badge
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * returns true id badge is new
     * @return true if badge is new, false otherwise
     */
    public boolean isNew() {
        return isNew.equals("true");
    }
}
