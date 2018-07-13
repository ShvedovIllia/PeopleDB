package homework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

	static Connection conn;
	static boolean program = true;

	public static void main(String[] args) throws SQLException {

		String dbUrl = "jdbc:mysql://localhost:3306/people?useSSL=false";
		String username = "root";
		String password = "1234";

		conn = DriverManager.getConnection(dbUrl, username, password);
		Scanner scan = new Scanner(System.in);
		while (program) {
			Interfaces.showInterface();

			System.out.println("Make your choice:");

			int userChoice = 0;

			while (!scan.hasNextInt()) {

				System.out.println("Incorrect input... try again");
				scan.nextLine();
			}

			userChoice = scan.nextInt();
			switch (userChoice) {
			case 1:
				CreateTable.createTable();
				break;
			case 2:
				Add.addCountry();
				break;
			case 3:
				Add.addCity();
				break;
			case 4:
				Add.addHuman();
				break;
			case 5:
				Show.showAllPeople();
				break;
			case 6:
				Show.showCity();
				break;
			case 7:
				Show.showCountry();
				break;
			case 8:
				Show.showPeopleFromCity();
				break;
			case 9:
				Show.showCitiesFromCountry();
				break;
			case 10:
				Show.showHumanById();
				break;
			case 11:
				InsertTable.insertTable();
				break;
			case 12:
				UpdateInfo.updateInfoHuman();
				break;
			case 13:
				endOfProgramm();
				break;
			default:
				System.out.println("Incorrect input! \n");
			}
		}
		System.out.println("Thanks for watching =)");
		scan.close();
	}

	public static void endOfProgramm() {
		program = false;
	}

}