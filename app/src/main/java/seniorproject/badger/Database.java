package seniorproject.badger;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Library class for communicating with BadgerAPI.
 */

public class Database {

    private static final String API_URL = "http://sample-env.e3rxnzanmm.us-west-2.elasticbeanstalk.com";

    public static void main(String[] args) {
        Database db = new Database();
        db.createUser("testLibraryUser", "pw", "testLibUser@example.com");
    }

    public Database()
    {

    }

    public JSONObject makeRequest(final String httpMethod, final String endpoint, final String query, final JSONObject request) {
        AsyncTask<String, Void, String> task = new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                StringBuilder sb = new StringBuilder();
                try {
                    HttpURLConnection conn;
                    String strUrl = API_URL + endpoint;
                    if (query != null) {
                        strUrl += "?" + query;
                    }
                    URL url = new URL(strUrl);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    switch (httpMethod) {
                        case "POST":
                            conn.setRequestMethod("POST");
                            conn.setRequestProperty("Content-Type", "application/json");
                            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                            wr.write(request.toString());
                            wr.flush(); break;
                        case "GET":
                            break;
                        default:
                            Exception e = new IllegalArgumentException("Invalid HTTP method given to makeRequest.");
                            Log.e("Database", e.toString());
                            throw e;
                    }

                    int HttpResult = conn.getResponseCode();
                    if (HttpResult == HttpURLConnection.HTTP_OK) {
                        BufferedReader br = new BufferedReader(
                                new InputStreamReader(conn.getInputStream(), "utf-8"));
                        String line;
                        while ((line = br.readLine()) != null) {
                            sb.append(line + "\n");
                        }
                        br.close();
                        Log.d("Database", "" + sb.toString());
                    } else {
                        Log.d("Database", "HTTP Result was NOT OK");
                        Log.d("Database", "HTTP RESPONSE: " + HttpResult);
                        Log.d("Database", conn.getResponseMessage());
                    }
                } catch (Exception e) {
                    Log.d("Database", "Error opening connection");
                    e.printStackTrace();
                    Log.e("Database", e.toString());
                }
                return sb.toString();
            }
        };
        JSONObject json = null;
        try {
            json = new JSONObject(task.execute("").get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException je) {
            je.printStackTrace();
        }
        return json;
    }

    public JSONObject makeGetRequest(final String endpoint, final String query) {
        return makeRequest("GET", endpoint, query, null);
    }

    public JSONObject makePostRequest(String endpoint, JSONObject json) {
        return makeRequest("POST", endpoint, null, json);
    }

    public User login(String username, String password) {
        JSONObject userLogin = new JSONObject();
        User result;
        try {
            userLogin.put("username", username);
            userLogin.put("password", password);

            JSONObject response = makePostRequest("/login", userLogin);
            if (!response.isNull("error")) {
                Log.e("Database", response.getString("error"));
                throw new IllegalArgumentException(response.getString("error"));
            }
            result = new User(response);
        }
        catch (JSONException je) {
            result = null;
        }
        return result;
    }

    public User createUser(String username, String password, String email) {
        JSONObject user = new JSONObject();
        User result;
        try {
            user.put("username", username);
            user.put("password", password);
            user.put("password_confirmation", password);
            user.put("email", email);

            JSONObject response = makePostRequest("/createUser", user);
            if (!response.isNull("error")) {
                Log.e("Database", response.getString("error"));
                throw new IllegalArgumentException(response.getString("error"));
            }

            result = new User(response.getString("id"), response.getString("username"), response.getString("email"));
        }
        catch (JSONException je) {
            result = null;
            Log.e("Database", je.getMessage());
            je.printStackTrace();
        }
        return result;
    }

    public void setTrophyCase(List<Badge> trophyCase)
    {

    }

    public void addFriend(String email)
    {

    }

    public void createGroup(String groupname, String description, String admin_id)
    {
        URL url = null;
        HttpURLConnection conn = null;
        try {

            url = new URL("http://sample-env.e3rxnzanmm.us-west-2.elasticbeanstalk.com/createGroup");
            conn = (HttpURLConnection) url.openConnection();
        } catch (Exception e) {
            System.out.println("Error opening connection");
        }

        try {
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);


            String params = "groupName=" + groupname + "&groupDescription="
                    + description + "&admin_id=" + admin_id;

            DataOutputStream output = new DataOutputStream(conn.getOutputStream());
            output.writeBytes(params);
            output.flush();
            output.close();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println("Error creating group");
        }
    }

    public Badge getBadge(String badgeID)
    {
        Badge badge = null;
        return badge;
    }

    public void createBadge(String name, String img, String description, String authorID)
    {
        URL url = null;
        HttpURLConnection conn = null;
        try {

            url = new URL("http://sample-env.e3rxnzanmm.us-west-2.elasticbeanstalk.com/createBadge");
            conn = (HttpURLConnection) url.openConnection();
        } catch (Exception e) {
            System.out.println("Error opening connection");
            e.printStackTrace();
        }

        try {
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);


            String params = "badge_name=" + name + "&image_url=" + img +
                    "&badge_description=" + description + "&author_id=" + authorID;

            DataOutputStream output = new DataOutputStream(conn.getOutputStream());
            output.writeBytes(params);
            output.flush();
            output.close();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println("Error creating badge");
            e.printStackTrace();
        }
    }

    public Group getGroup(String groupID)
    {
        Group group = null;

        URL url = null;
        HttpURLConnection conn = null;
        User user = null;
        try
        {
            url = new URL("http://sample-env.e3rxnzanmm.us-west-2.elasticbeanstalk.com" +
                    "/readGroup?id=" + groupID);
            conn = (HttpURLConnection) url.openConnection();
        }
        catch(Exception e)
        {
            System.out.println("Error opening connection");
        }
        try {
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            System.out.println(response.toString());

        }
        catch(Exception e)
        {
            System.out.println("Error reading group");
        }


        return group;
    }

    public void addUserToGroup(String userID, String groupID)
    {
        URL url = null;
        HttpURLConnection conn = null;
        try {

            url = new URL("http://sample-env.e3rxnzanmm.us-west-2.elasticbeanstalk.com/" +
                    "addUserToGroup");
            conn = (HttpURLConnection) url.openConnection();
        } catch (Exception e) {
            System.out.println("Error opening connection");
        }

        try {
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);


            String params = "user_id=" + userID + "&group_id=" + groupID;

            DataOutputStream output = new DataOutputStream(conn.getOutputStream());
            output.writeBytes(params);
            output.flush();
            output.close();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println("Error adding user to group");
        }
    }

    public User getUser(int userID)
    {
        return null;
    }

    public User getUser(String username) {
        return new User(makeGetRequest("/readUser", "username=" + username));
    }

    public List<Badge> getTrophyCase(String userID)
    {
        List<Badge> badges = null;
        return badges;
    }

    public List<Badge> getSentBadges(String userID)
    {
        List<Badge> badges = null;
        return badges;
    }

    public List<Badge> getReceivedBadges(String userID)
    {
        List<Badge> badges = null;
        return badges;
    }
}