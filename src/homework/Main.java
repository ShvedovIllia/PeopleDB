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
		while(program) {
		showInterface();
		
		System.out.println("Make your choice:");
		
		int userChoice = 0;
		
		while(!scan.hasNextInt()) {
		
		System.out.println("Incorrect input... try again");
		scan.nextLine();
		}
		
		userChoice = scan.nextInt();
		switch(userChoice) {
		case 1: createTable();
				break;
		case 2: addCountry();
				break;
		case 3: addCity();
				break;
		case 4: addHuman();
				break;
		case 5: showAllPeople();
				break;
		case 6: showCity();
				break;
		case 7: showCountry();
				break;
		case 8: showPeopleFromCity();
				break;
		case 9: showCitiesFromCountry();
				break;
		case 10: showHumanById();
				break;
		case 11: insertTable();
				break;
		case 12: updateInfoHuman();
				break;
		case 13: endOfProgramm();
				break;
		default: System.out.println("Incorrect input! \n");
		}
		}
		System.out.println("Thanks for watching =)");
		scan.close();
	}	
	public static void createTable() throws SQLException{
		System.out.println("You want to create table!");
		
// CREATE PEOPLE
		
		String dropPeople = "drop table if exists people";
		String createPeople = "create table people("
				+ "id int primary key auto_increment,"
				+ "first_name varchar(50) not null,"
				+ "last_name varchar(50) not null,"
				+ "hobby varchar(50) not null,"
				+ "age int not null,"
				+ "city_id int not null);";
		Statement stat = conn.createStatement();
		stat.execute(dropPeople);
		stat.execute(createPeople);
		System.out.println("Table 'people' created");
		
// CREATE CITY
		
		String dropCity = "drop table if exists city";
		String createCity = "create table city("
				+ "id int primary key auto_increment,"
				+ "name varchar(50) not null,"
				+ "country_id int not null);";
		stat.execute(dropCity);
		stat.execute(createCity);
		System.out.println("Table 'city' created");
		
// CREATE COUNTRY		
		
		String dropCountry = "drop table if exists country";
		String createCountry = "create table country("
				+ "id int primary key auto_increment,"
				+ "name varchar(50) not null);";
		stat.execute(dropCountry);
		stat.execute(createCountry);
		System.out.println("Table 'country' created");
		
		String alterTableCity = "alter table city add foreign key (country_id) references country(id);";
		String alterTablePeople = "alter table people add foreign key (city_id) references city(id);";
		stat.execute(alterTableCity);
		stat.execute(alterTablePeople);

		
		stat.close();
	}
	public static void addCountry() throws SQLException{
		System.out.println("You want to add country!");		
		String query = "insert into country (name) values (?);";
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter name of country:");
		String userInput = scan.nextLine();
		PreparedStatement ps = conn.prepareStatement(query);
		
		ps.setString(1, userInput);
		
		System.out.println("Country '" + userInput + "' added!");
		
		ps.executeUpdate();
		ps.close();
	}
	public static void addCity() throws SQLException{
		System.out.println("You want to addCity!");		
		String query = "insert into city (name, country_id) values (?,?);";
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter name of city:");
		String userInputCityName = scan.nextLine();
		System.out.println("Select country id: ");
		showCountry();
		
		int userInputCountryID;
		while(!scan.hasNextInt()) {
			
			System.out.println("Incorrect input... try again");
			scan.nextLine();
			}
		userInputCountryID = scan.nextInt();
		PreparedStatement ps = conn.prepareStatement(query);
		
		ps.setString(1, userInputCityName);
		ps.setInt(2, userInputCountryID);
		System.out.println("City '" + userInputCityName + "' added!");
		
		ps.executeUpdate();
		ps.close();		
	}
	public static void addHuman() throws SQLException{
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
		while(!scan.hasNextInt()) {
			System.out.println("Incorrect input... try again");
			scan.nextLine();
		}
		userInputAge = scan.nextInt();
		System.out.println("Select city_id of human:");
		while(!scan.hasNextInt()) {
				System.out.println("Incorrect input... try again");
				scan.nextLine();
			}
		showCity();
		
		
		int userInputCityID;
		while(!scan.hasNextInt()) {
			
			System.out.println("Incorrect input... try again");
			scan.nextLine();
			}
		userInputCityID = scan.nextInt();
		
		PreparedStatement ps = conn.prepareStatement(query);
		
		ps.setString(1, userInputFirstName);
		ps.setString(2, userInputLastName);
		ps.setString(3, userInputHobby);
		ps.setInt(4, userInputAge);
		ps.setInt(5, userInputCityID);
		
		System.out.println("People '" + userInputFirstName + " " + userInputLastName + ", " 
							+ userInputAge + "years old, interested in " + userInputHobby + "' added!");
		
		ps.executeUpdate();
		ps.close();
	}
	public static void showAllPeople() throws SQLException{
		System.out.println("All people");
		String query = "select p.* from people p;";
		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		
		List <String> people = new ArrayList<>();
		
		while(rs.next()) {
			people.add("id:" + rs.getInt("id")+ "\t" + 
						"First name: " + rs.getString ("first_name") + "\t" + 
						"Last name: " + rs.getString("last_name")+ "\t" + 
						"Hobbies: " + rs.getString("hobby")+ "\t" + 
						"Age: " + rs.getInt("age"));
		}
		people.forEach(System.out::println);
		
		rs.close();
		ps.close();
	}
	public static void showCity() throws SQLException{
		System.out.println("all cities!");
		String query = "select c.* from city c;";
		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		
		List <String> cities = new ArrayList<>();
		
		while(rs.next()) {
			cities.add("id:" + rs.getInt("id")+ "\t" + 
						rs.getString ("name"));
		}
		cities.forEach(System.out::println);
		
		rs.close();
		ps.close();	
	}
	public static void showCountry() throws SQLException{
		System.out.println("show all countries!");
		String query = "select c.* from country c;";
		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		
		List <String> countries = new ArrayList<>();
		
		while(rs.next()) {
			countries.add("id:" + rs.getInt("id")+ "\t" + 
						rs.getString ("name"));
		}
		countries.forEach(System.out::println);
		
		rs.close();
		ps.close();	
	}
	public static void showPeopleFromCity() throws SQLException{
		System.out.println("show people from city!");
		System.out.println("Select id:");
		showCity();
		Scanner scan = new Scanner(System.in);
		int userInput;
		while(!scan.hasNextInt()) {
			System.out.println("Incorrect input... try again");
			scan.nextLine();
		}
		userInput = scan.nextInt();
		String query = "select * from people where city_id = ?";
		
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, userInput);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			System.out.println("id:" + rs.getInt("id")+ "\t" + 
					"First name: " + rs.getString ("first_name") + "\t" + 
					"Last name: " + rs.getString("last_name")+ "\t" + 
					"Hobbies: " + rs.getString("hobby")+ "\t" + 
					"Age: " + rs.getInt("age"));
		}
		rs.close();
		ps.close();
	}
	public static void showCitiesFromCountry() throws SQLException{
		System.out.println("show cities from country!");
		System.out.println("Select id:");
		showCountry();
		Scanner scan = new Scanner(System.in);
		int userInput;
		while(!scan.hasNextInt()) {
			System.out.println("Incorrect input... try again");
			scan.nextLine();
		}
		userInput = scan.nextInt();
		String query = "select * from city where country_id = ?";
		
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, userInput);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			System.out.println("id:" + rs.getInt("id")+ "\t" + 
					rs.getString ("name"));
		}
		rs.close();
		ps.close();
	}
	public static void showHumanById() throws SQLException{
		System.out.println("You want to show human by id!");
		System.out.println("Select id:");
		showAllPeople();
		Scanner scan = new Scanner(System.in);
		int userInput = scan.nextInt();
		String query = "select * from people where id = ?";
		
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, userInput);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			System.out.println("id:" + rs.getInt("id")+ "\t" + 
					"First name: " + rs.getString ("first_name") + "\t" + 
					"Last name: " + rs.getString("last_name")+ "\t" + 
					"Hobbies: " + rs.getString("hobby")+ "\t" + 
					"Age: " + rs.getInt("age"));
		}
		rs.close();
		ps.close();
	}
	public static void insertTable() throws SQLException{
		System.out.println("Insert table");
		showInsertIntefface();
		Scanner scan = new Scanner(System.in);

		int userChoice = scan.nextInt();
		Random rand = new Random();
		
		switch(userChoice) {
		case 1: for (int i=0; i<50; i++){
					String query1 = "insert into people (first_name, last_name, hobby, age, city_id) values (?,?,?,?,?);";
			
					PreparedStatement ps1 = conn.prepareStatement(query1);
					ps1.setString(1, "FirstName" + rand.nextInt(50));
					ps1.setString(2, "SecondName" + rand.nextInt(50));
					ps1.setString(3, "Hobby" + rand.nextInt(50));
					ps1.setInt(4, rand.nextInt(50));
					ps1.setInt(5, 1);
					ps1.executeUpdate();
					ps1.close();
				}	
				break;
				
		case 2: for (int i=0; i<50; i++){
				String query2 = "insert into city (name, country_id) values (?,?);";
		
				PreparedStatement ps2 = conn.prepareStatement(query2);
				ps2.setString(1, "name" + rand.nextInt(50));
				ps2.setInt(2, 1);
				ps2.executeUpdate();
				ps2.close();
				}
				break;
				
		case 3: for (int i=0; i<50; i++){
				String query3 = "insert into country (name) values (?);";
		
				PreparedStatement ps3 = conn.prepareStatement(query3);
				ps3.setString(1, "country" + rand.nextInt(50));
				ps3.executeUpdate();
				ps3.close();
			}
				break;
		default: System.out.println("Incorrect input");  
		
		}
		
	}
	public static void updateInfoHuman() throws SQLException{
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter new first name of human:");
		String userInputFirstName = scan.nextLine();
		System.out.println("Enter new last name of human:");
		String userInputLastName = scan.nextLine();
		System.out.println("Enter new hobby of human:");
		String userInputHobby = scan.nextLine();		
		System.out.println("Enter new age of human:");
		int userInputAge;
		while(!scan.hasNextInt()) {
			System.out.println("Incorrect input... try again");
			scan.nextLine();
		}
		userInputAge = scan.nextInt();
		System.out.println("Enter people id, which need change:");
		showAllPeople();
		int peopleID;
		while(!scan.hasNextInt()) {
			System.out.println("Incorrect input... try again");
			scan.nextLine();
		}
		peopleID = scan.nextInt();
//		String query1 = "update people set first_name = `"+ userInputFirstName + "`, last_name = `"+ userInputLastName 
//				+ "`, hobby = `"+ userInputHobby + "`, age = `"+ userInputAge + "`, where id = " + peopleID + ";";
		
		String query = "update people set first_name = ?, last_name = ?, hobby = ?, age = ? where id = ?;";
	
		PreparedStatement ps = conn.prepareStatement(query);
		
		ps.setString(1, userInputFirstName);
		ps.setString(2, userInputLastName);
		ps.setString(3, userInputHobby);
		ps.setInt(4, userInputAge);
		ps.setInt(5, peopleID);
		ps.executeUpdate();
		ps.close();	
	}
	public static void endOfProgramm(){	
		program = false;
	}
	public static void showInterface(){
		System.out.println("Interface");
		System.out.print("1: create table \t");
		System.out.println("2: add Country");
		System.out.print("3: add City \t\t");
		System.out.println("4: add Human");
		System.out.print("5: show all people \t");
		System.out.println("6: show all cities");
		System.out.print("7: show all countries \t");
		System.out.println("8: show People From City");
		System.out.print("9: Cities From Country \t");
		System.out.println("10: show human by id");
		System.out.print("11: insert Table \t");
		System.out.println("12: update Info Human");
		System.out.println("13: end of program \n");	
	}
	public static void showInsertIntefface() {
		System.out.println("Choose table, which need change:");
		System.out.print("1. People \t");
		System.out.print("2. City \t");
		System.out.println("3. Country");
	}
}