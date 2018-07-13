package homework;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Add {
	public static void addCountry() throws SQLException {
		System.out.println("You want to add country!");
		String query = "insert into country (name) values (?);";
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter name of country:");
		String userInput = scan.nextLine();
		PreparedStatement ps = Main.conn.prepareStatement(query);

		ps.setString(1, userInput);

		System.out.println("Country '" + userInput + "' added!");

		ps.executeUpdate();
		ps.close();
	}

	public static void addCity() throws SQLException {
		System.out.println("You want to addCity!");
		String query = "insert into city (name, country_id) values (?,?);";
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter name of city:");
		String userInputCityName = scan.nextLine();
		System.out.println("Select country id: ");
		Show.showCountry();

		int userInputCountryID;
		while (!scan.hasNextInt()) {

			System.out.println("Incorrect input... try again");
			scan.nextLine();
		}
		userInputCountryID = scan.nextInt();
		PreparedStatement ps = Main.conn.prepareStatement(query);

		ps.setString(1, userInputCityName);
		ps.setInt(2, userInputCountryID);
		System.out.println("City '" + userInputCityName + "' added!");

		ps.executeUpdate();
		ps.close();
	}

	public static void addHuman() throws SQLException {
		System.out.println("You want to add Human!");
		String query = "insert into people (first_name, last_name, hobby, age, city_id) values (?,?,?,?,?);";
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter first name of human:");
		String userInputFirstName = scan.nextLine();
		System.out.println("Enter last name of human:");
		String userInputLastName = scan.nextLine();
		System.out.println("Enter hobbies of human:");
		String userInputHobby = scan.nextLine();
		System.out.println("Enter age of human:");
		int userInputAge;
		while (!scan.hasNextInt()) {
			System.out.println("Incorrect input... try again");
			scan.nextLine();
		}
		userInputAge = scan.nextInt();
		System.out.println("Select city_id of human:");
		Show.showCity();
		while (!scan.hasNextInt()) {
			System.out.println("Incorrect input... try again");
			scan.nextLine();
		}

		int userInputCityID;
		while (!scan.hasNextInt()) {

			System.out.println("Incorrect input... try again");
			scan.nextLine();
		}
		userInputCityID = scan.nextInt();

		PreparedStatement ps = Main.conn.prepareStatement(query);

		ps.setString(1, userInputFirstName);
		ps.setString(2, userInputLastName);
		ps.setString(3, userInputHobby);
		ps.setInt(4, userInputAge);
		ps.setInt(5, userInputCityID);

		System.out.println("People '" + userInputFirstName + " " + userInputLastName + ", " + userInputAge
				+ "years old, interested in " + userInputHobby + "' added!");

		ps.executeUpdate();
		ps.close();
	}

}
