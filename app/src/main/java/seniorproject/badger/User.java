package seniorproject.badger;


public class User {

    private String userName;
    //private String emailAddress;
    private String password;

    public User(String name, String pw){
        userName = name;
        //emailAddress = email;
        password = pw;
    }

    public User() {

    }

    //public String getEmailAddress() {
    //    return emailAddress;
    //}

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    //public void setEmailAddress(String email) {
      //  this.emailAddress = email;
    //}

    public void setPassword(String pw) {
        this.password = pw;
    }

    public void setUserName(String name) {
        this.userName = name;
    }
}
