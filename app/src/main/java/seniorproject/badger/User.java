package seniorproject.badger;


public class User {

    private String userName;
    private String emailAddress;
    private String password;

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

    public User() {

    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setEmailAddress(String email) {
        this.emailAddress = email;
    }

    public void setPassword(String pw) {
        this.password = pw;
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
