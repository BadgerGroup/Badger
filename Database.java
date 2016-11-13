package seniorproject.badger;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;


public class Database {


	public Database()
	{

	}

	public void createUser(String username, String password, String email) {
		URL url = null;
		HttpURLConnection conn = null;
		try {

			url = new URL("http://sample-env.e3rxnzanmm.us-west-2.elasticbeanstalk.com/createUser");
			conn = (HttpURLConnection) url.openConnection();
		} catch (Exception e) {
			System.out.println("Error opening connection");
		}

		try {
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);


			String params = "username=" + username + "&password=" + password + "&email=" + email;

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
			System.out.println("Error creating user");
		}
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

			url = new URL("http://sample-env.e3rxnzanmm.us-west-2.elasticbeanstalk.com/createUser");
			conn = (HttpURLConnection) url.openConnection();
		} catch (Exception e) {
			System.out.println("Error opening connection");
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
			System.out.println("Error creating user");
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

	public User getUser(String userID)
	{
		URL url = null;
		HttpURLConnection conn = null;
		User user = null;
		try
		{

			url = new URL("http://sample-env.e3rxnzanmm.us-west-2.elasticbeanstalk.com" +
					"/readUser?id=" + userID);
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
			System.out.println("Error reading user");
		}

		return user;
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
