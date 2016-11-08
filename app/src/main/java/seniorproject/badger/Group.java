package seniorproject.badger;


import java.util.Arrays;

public class Group {

    private String groupID;
    private String groupName;
    private String []members;
    private int numberOfMembers;
    private String description;

    public Group(){
        members = new String[20];
        numberOfMembers = 0;

    }

    public Group(String gID, String gName){
        groupID = gID;
        groupName = gName;
        members = new String[20];
        numberOfMembers = 0;
    }

    public void addMember(String userID){
        if(numberOfMembers == members.length){
            resize();
        }
        members[numberOfMembers] = userID;
        numberOfMembers++;
    }

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

    /** @return a copy of the array */
    public String[] getArray() {
        return Arrays.copyOf(members, members.length);
    }

    public String getGroupID() {
        return groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public int getNumberOfMembers() {
        return numberOfMembers;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setNumberOfMembers(int numberOfMembers) {
        this.numberOfMembers = numberOfMembers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
