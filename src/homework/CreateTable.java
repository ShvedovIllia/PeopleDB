package homework;

import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
	public static void createTable() throws SQLException {
		System.out.println("You want to create table!");

		// CREATE PEOPLE

		String dropPeople = "drop table if exists people";
		String createPeople = "create table people(" + "id int primary key auto_increment,"
				+ "first_name varchar(50) not null," + "last_name varchar(50) not null," + "hobby varchar(50) not null,"
				+ "age int not null," + "city_id int not null);";
		Statement stat = Main.conn.createStatement();
		stat.execute(dropPeople);
		stat.execute(createPeople);
		System.out.println("Table 'people' created");

		// CREATE CITY

		String dropCity = "drop table if exists city";
		String createCity = "create table city(" + "id int primary key auto_increment," + "name varchar(50) not null,"
				+ "country_id int not null);";
		stat.execute(dropCity);
		stat.execute(createCity);
		System.out.println("Table 'city' created");

		// CREATE COUNTRY

		String dropCountry = "drop table if exists country";
		String createCountry = "create table country(" + "id int primary key auto_increment,"
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

}
