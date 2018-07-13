package homework;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class InsertTable {
	public static void insertTable() throws SQLException {
		System.out.println("Insert table");
		Interfaces.showInsertIntefface();
		Scanner scan = new Scanner(System.in);

		int userChoice = scan.nextInt();
		Random rand = new Random();

		switch (userChoice) {
		case 1:
			for (int i = 0; i < 50; i++) {
				String query1 = "insert into people (first_name, last_name, hobby, age, city_id) values (?,?,?,?,?);";

				PreparedStatement ps1 = Main.conn.prepareStatement(query1);
				ps1.setString(1, "FirstName" + rand.nextInt(50));
				ps1.setString(2, "SecondName" + rand.nextInt(50));
				ps1.setString(3, "Hobby" + rand.nextInt(50));
				ps1.setInt(4, rand.nextInt(50));
				ps1.setInt(5, 1);
				ps1.executeUpdate();
				ps1.close();
			}
			break;

		case 2:
			for (int i = 0; i < 50; i++) {
				String query2 = "insert into city (name, country_id) values (?,?);";

				PreparedStatement ps2 = Main.conn.prepareStatement(query2);
				ps2.setString(1, "name" + rand.nextInt(50));
				ps2.setInt(2, 1);
				ps2.executeUpdate();
				ps2.close();
			}
			break;

		case 3:
			for (int i = 0; i < 50; i++) {
				String query3 = "insert into country (name) values (?);";

				PreparedStatement ps3 = Main.conn.prepareStatement(query3);
				ps3.setString(1, "country" + rand.nextInt(50));
				ps3.executeUpdate();
				ps3.close();
			}
			break;
		default:
			System.out.println("Incorrect input");

		}

	}
}
