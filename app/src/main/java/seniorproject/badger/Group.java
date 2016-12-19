package seniorproject.badger;


import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;

public class Group {

    private String groupID;
    private String groupName;
    private String[] members;
    private String adminID;
    private int numberOfMembers;
    private String description;

    public Group(){
        members = new String[20];
        numberOfMembers = 0;

    }

    public Group(JSONObject json)
    {
        try {
            groupID = json.getString("id");
            groupName = json.getString("group_name");
            adminID = json.getString("admin_id");
            description = json.getString("group_description");
            members = toArray( json.getJSONArray("user_ids") );
            numberOfMembers = members.length;

        } catch (JSONException e) {
            Log.e("Database", e.toString());
        } catch (NullPointerException npe) {
            Log.e("Database", npe.toString());
        }
    }

    public Group(String gID, String gName){
        groupID = gID;
        groupName = gName;
        members = new String[20];
        numberOfMembers = 0;
    }

    /**
     * addes member to group
     * @param userID
     */
    public void addMember(String userID){
        if(numberOfMembers == members.length){
            resize();
        }
        members[numberOfMembers] = userID;
        numberOfMembers++;
    }


    /**
     * removes member from a group
     * @param index
     */
    public void removeMember(int index){
        for (int pos = index + 1; pos < numberOfMembers; pos++)
        {
            members[pos-1] = members[pos];
        }
        members[numberOfMembers - 1] = null;
        numberOfMembers--;
    }

    /**
     * a method to resize the array
     */
    private void resize(){
        //allocate memory for bigger array
        String[] temp = new String[2 * numberOfMembers];
        //copy over all items from old array to new array
        for(int i = 0; i< numberOfMembers; i++)
        {
            temp[i] = members[i];
        }
        //assign ref to new array
        members = temp;
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

    /** @return a copy of the array */
    public String[] getArray() {
        return Arrays.copyOf(members, members.length);
    }

    /**
     * returns the id of the group
     * @return String
     */
    public String getGroupID() {
        return groupID;
    }

    /**
     * returns the name of the group
     * @return
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * returns number of members in a group
     * @return int
     */
    public int getNumberOfMembers() {
        return numberOfMembers;
    }

    /**
     * sets the id of the grouo
     * @param groupID
     */
    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    /**
     * sets the name of the group
     * @param groupName
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * sets the number of members in a group
     * @param numberOfMembers
     */
    public void setNumberOfMembers(int numberOfMembers) {
        this.numberOfMembers = numberOfMembers;
    }

    /**
     * returns the group description
     * @return String
     */
    public String getDescription() {
        return description;
    }


    /**
     * sets the group description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * returns the admin ID
     * @return  String
     */
    public String getAdminID() { return adminID;}

    /**
     * returns list of memebers in a group
     * @return String array
     */
    public String[] getMembers() { return members; }
}
