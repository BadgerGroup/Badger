package seniorproject.badger;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

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

                    switch (httpMethod) {
                        case "POST":
                            conn.setDoOutput(true);
                            conn.setDoInput(true);
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
                        Log.d("Database", "Retrieved: " + sb.toString());
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
        } catch (InterruptedException | ExecutionException | JSONException e) {
            Log.e("Database", Log.getStackTraceString(e));
        }
        return json;
    }

    public JSONObject makeGetRequest(final String endpoint, final String query) {
        return makeRequest("GET", endpoint, query, null);
    }

    public JSONObject makePostRequest(String endpoint, JSONObject json) {
        return makeRequest("POST", endpoint, null, json);
    }

    public User login(String username, String password) throws IllegalArgumentException {
        JSONObject userLogin = new JSONObject();
        User result;
        try {
            userLogin.put("username", username);
            userLogin.put("password", password);

            JSONObject response = makePostRequest("/login", userLogin);
            if (!response.isNull("error")) { // check for presence of error
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
            Log.e("Database", Log.getStackTraceString(je));
        }
        return result;
    }

    public void setTrophyCase(List<Badge> trophyCase)
    {

    }

    /**
     * Adds the user with id `friendID` to the given user's friends list. The API will automatically
     * to the same thing in the reverse direction - the user with id `userID` will be added as a
     * friend of the other user.
     * @param userID
     * @param friendID
     */
    public boolean addFriend(String userID, String friendID) throws UserNotFoundException
    {
        JSONObject request = new JSONObject();

        try {
            request.put("user_id", userID);
            request.put("friend_id", friendID);

            JSONObject response = makePostRequest("/addFriend", request);

            if (!response.isNull("error")) {
                throw new UserNotFoundException("User and/or friend not found.");
            }
            else return response.getString("response").equalsIgnoreCase("success");
        } catch (JSONException e) {
            Log.e("Database", Log.getStackTraceString(e));
            return false;
        }
    }

    public void createGroup(String groupname, String description, String admin_id)
    {

    }

    public Badge getBadge(String badgeID) throws BadgeNotFoundException
    {
        JSONObject response = makeGetRequest("/readBadge",  "id=" + badgeID);
        if (!response.isNull("error")) {
            throw new BadgeNotFoundException(("No badge found with that id."));
        }

        return new Badge(response);
    }

    public void createBadge(String name, String img, String description, String authorID)
    {

    }

    public Group getGroup(String groupID)
    {
        return null;
    }

    public void addUserToGroup(String userID, String groupID)
    {

    }

    public User getUser(String key, String value) throws UserNotFoundException{
        JSONObject response = makeGetRequest("/readUser", key + "=" + value);
        if (!response.isNull("error")) {
            throw new UserNotFoundException(("No user found with that " + key + "."));
        }

        return new User(response);
    }

    public User getUser(int userID) throws UserNotFoundException
    {
        return getUser("id", String.valueOf(userID));
    }

    public User getUser(String username) throws UserNotFoundException {
        return getUser("username", username);
    }

}