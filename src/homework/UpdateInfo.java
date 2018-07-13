package homework;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateInfo {
	public static void updateInfoHuman() throws SQLException {

		Scanner scan = new Scanner(System.in);

		System.out.println("Enter new first name of human:");
		String userInputFirstName = scan.nextLine();
		System.out.println("Enter new last name of human:");
		String userInputLastName = scan.nextLine();
		System.out.println("Enter new hobby of human:");
		String userInputHobby = scan.nextLine();
		System.out.println("Enter new age of human:");
		int userInputAge;
		while (!scan.hasNextInt()) {
			System.out.println("Incorrect input... try again");
			scan.nextLine();
		}
		userInputAge = scan.nextInt();
		System.out.println("Enter people id, which need change:");
		Show.showAllPeople();
		int peopleID;
		while (!scan.hasNextInt()) {
			System.out.println("Incorrect input... try again");
			scan.nextLine();
		}
		peopleID = scan.nextInt();
		// String query1 = "update people set first_name = `"+ userInputFirstName + "`,
		// last_name = `"+ userInputLastName
		// + "`, hobby = `"+ userInputHobby + "`, age = `"+ userInputAge + "`, where id
		// = " + peopleID + ";";

		String query = "update people set first_name = ?, last_name = ?, hobby = ?, age = ? where id = ?;";

		PreparedStatement ps = Main.conn.prepareStatement(query);

		ps.setString(1, userInputFirstName);
		ps.setString(2, userInputLastName);
		ps.setString(3, userInputHobby);
		ps.setInt(4, userInputAge);
		ps.setInt(5, peopleID);
		ps.executeUpdate();
		ps.close();
	}
}
