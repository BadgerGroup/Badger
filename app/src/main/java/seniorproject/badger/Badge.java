package seniorproject.badger;



public class Badge  {

    private String badgeName;
    private String authorID;
    private String recipientID;
    private String imageURL;
    private String description;

    public Badge(){

    }

    public Badge(String name, String author, String recp, String url, String descr){
        badgeName = name;
        authorID = author;
        recipientID = recp;
        imageURL = url;
        description = descr;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public void setBadgeName(String badgeName) {
        this.badgeName = badgeName;
    }

    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    public String getRecipientID() {
        return recipientID;
    }

    public void setRecipientID(String recipientID) {
        this.recipientID = recipientID;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
