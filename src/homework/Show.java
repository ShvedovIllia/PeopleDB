package homework;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Show {

	public static void showAllPeople() throws SQLException {
		System.out.println("All people");
		String query = "select p.* from people p;";
		PreparedStatement ps = Main.conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();

		List<String> people = new ArrayList<>();

		while (rs.next()) {
			people.add("id:" + rs.getInt("id") + "\t" + "First name: " + rs.getString("first_name") + "\t"
					+ "Last name: " + rs.getString("last_name") + "\t" + "Hobbies: " + rs.getString("hobby") + "\t"
					+ "Age: " + rs.getInt("age"));
		}
		people.forEach(System.out::println);

		rs.close();
		ps.close();
	}

	public static void showCity() throws SQLException {
		System.out.println("all cities!");
		String query = "select c.* from city c;";
		PreparedStatement ps = Main.conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();

		List<String> cities = new ArrayList<>();

		while (rs.next()) {
			cities.add("id:" + rs.getInt("id") + "\t" + rs.getString("name"));
		}
		cities.forEach(System.out::println);

		rs.close();
		ps.close();
	}

	public static void showCountry() throws SQLException {
		System.out.println("show all countries!");
		String query = "select c.* from country c;";
		PreparedStatement ps = Main.conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();

		List<String> countries = new ArrayList<>();

		while (rs.next()) {
			countries.add("id:" + rs.getInt("id") + "\t" + rs.getString("name"));
		}
		countries.forEach(System.out::println);

		rs.close();
		ps.close();
	}

	public static void showPeopleFromCity() throws SQLException {
		System.out.println("show people from city!");
		System.out.println("Select id:");
		showCity();
		Scanner scan = new Scanner(System.in);
		int userInput;
		while (!scan.hasNextInt()) {
			System.out.println("Incorrect input... try again");
			scan.nextLine();
		}
		userInput = scan.nextInt();
		String query = "select * from people where city_id = ?";

		PreparedStatement ps = Main.conn.prepareStatement(query);
		ps.setInt(1, userInput);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			System.out.println("id:" + rs.getInt("id") + "\t" + "First name: " + rs.getString("first_name") + "\t"
					+ "Last name: " + rs.getString("last_name") + "\t" + "Hobbies: " + rs.getString("hobby") + "\t"
					+ "Age: " + rs.getInt("age"));
		}
		rs.close();
		ps.close();
	}

	public static void showCitiesFromCountry() throws SQLException {
		System.out.println("show cities from country!");
		System.out.println("Select id:");
		showCountry();
		Scanner scan = new Scanner(System.in);
		int userInput;
		while (!scan.hasNextInt()) {
			System.out.println("Incorrect input... try again");
			scan.nextLine();
		}
		userInput = scan.nextInt();
		String query = "select * from city where country_id = ?";

		PreparedStatement ps = Main.conn.prepareStatement(query);
		ps.setInt(1, userInput);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			System.out.println("id:" + rs.getInt("id") + "\t" + rs.getString("name"));
		}
		rs.close();
		ps.close();
	}

	public static void showHumanById() throws SQLException {
		System.out.println("You want to show human by id!");
		System.out.println("Select id:");
		showAllPeople();
		Scanner scan = new Scanner(System.in);
		int userInput = scan.nextInt();
		String query = "select * from people where id = ?";

		PreparedStatement ps = Main.conn.prepareStatement(query);
		ps.setInt(1, userInput);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			System.out.println("id:" + rs.getInt("id") + "\t" + "First name: " + rs.getString("first_name") + "\t"
					+ "Last name: " + rs.getString("last_name") + "\t" + "Hobbies: " + rs.getString("hobby") + "\t"
					+ "Age: " + rs.getInt("age"));
		}
		rs.close();
		ps.close();
	}

}
