package com.jspider.cardeko.jdbc.CarOpreation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.jspider.cardeko.jdbc.entity.Car;

public class CarOpreation {
	private static Connection connection;
	private static PreparedStatement preparedStatement;
	private static ResultSet resultSet;
	private static String queryAdd; 
	private static String queryDelete;
	private static String queryFetchByID;
	private static String queryFetchByName;
	private static String queryAllFetch;
	private static String queryupdate;


	public static void addCar(Scanner scanner)  {
		try {
		System.out.println("how many car you want add");
		int count = scanner.nextInt();
		for (int i = 1; i <= count; i++) {
			System.out.println("Enter car id");
			int id = scanner.nextInt();
			scanner.nextLine();
			System.out.println("Enter car name");
			String name = scanner.nextLine();
			System.out.println("Enter car price");
			String price = scanner.nextLine();
			System.out.println("Enter car color");
			String color = scanner.nextLine();
			System.out.println("Enter car model");
			String model = scanner.nextLine();
			System.out.println("Enter car engine");
			String engine = scanner.nextLine();
			
			try {

			} catch (Exception e) {
				// TODO: handle exception
			}
			openConnection();
			queryAdd = "INSERT INTO cardeko VALUES (?, ?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(queryAdd);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, price);
			preparedStatement.setString(4, color);
			preparedStatement.setString(5, model);
			preparedStatement.setString(6, engine);
			preparedStatement.addBatch();
			System.out.println("Car added");
		}
		int[] NumberOfcar = preparedStatement.executeBatch();
		System.out.println("Number Of car added is " + NumberOfcar.length);
	}
		catch (Exception e) {
			// TODO: handle exception+
			e.printStackTrace();
		}
		finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public void findCarById(Scanner scanner) {
		System.out.println("Enter car id");
		int id = scanner.nextInt();

		try {
			openConnection();
			queryFetchByID = "SELECT * FROM cardeko WHERE id = ?";
			preparedStatement = connection.prepareStatement(queryFetchByID);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				System.out.println(resultSet.getInt(1));
				System.out.println(resultSet.getString(2));
				System.out.println(resultSet.getString(3));
				System.out.println(resultSet.getString(4));
				System.out.println(resultSet.getString(5));
				System.out.println(resultSet.getString(6));
			} else {

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void findAllCars() {
		try {
			openConnection();
			queryAllFetch = "SELECT * FROM cardeko";
			preparedStatement = connection.prepareStatement(queryAllFetch);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				System.out.println(resultSet.getInt(1));
				System.out.println(resultSet.getString(2));
				System.out.println(resultSet.getString(3));
				System.out.println(resultSet.getString(4));
				System.out.println(resultSet.getString(5));
				System.out.println(resultSet.getString(6));
				System.out.println();
				System.out.println();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void findCarByname(Scanner scanner) {
		System.out.println("Enter car name");
		scanner.nextLine();
		String name = scanner.nextLine();

		try {
			openConnection();
			queryFetchByName= "SELECT * FROM cardeko WHERE name = ?";
			preparedStatement = connection.prepareStatement(queryFetchByName);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				System.out.println(resultSet.getInt(1));
				System.out.println(resultSet.getString(2));
				System.out.println(resultSet.getString(3));
				System.out.println(resultSet.getString(4));
				System.out.println(resultSet.getString(5));
				System.out.println(resultSet.getString(6));
			} else {

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void deleteCar(Scanner scanner) {
		System.out.println("Enter car id");
		int id = scanner.nextInt();

		try {
			openConnection();
			queryDelete = "DELETE FROM cardeko WHERE id = ?";
			preparedStatement = connection.prepareStatement(queryDelete);
			preparedStatement.setInt(1, id);
			int res = preparedStatement.executeUpdate();
			if (res == 1) {
				System.out.println("car is deleted");
			} else {

				System.out.println("car not found !!!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void updateCar(Scanner scanner) {

		System.out.println("Enter car id");
		int id = scanner.nextInt();
		scanner.nextLine();
		
		System.out.println("Enter car price");
		String price = scanner.nextLine();
		try {
			openConnection();
			queryupdate = "UPDATE cardeko SET price = ? WHERE id = ?";
			preparedStatement = connection.prepareStatement(queryupdate);
			preparedStatement.setString(1, price);
			preparedStatement.setInt(2, id);
			int res = preparedStatement.executeUpdate();
			if (res == 1) {
				System.out.println("Price of car is updated");
			} else {
				System.out.println("car not found");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void openConnection() throws SQLException {
		connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1523:XE", "system", "root");
	}

	public static void closeConnection() throws SQLException {
		if (resultSet != null) {
			resultSet.close();
		}
		if (preparedStatement != null) {
			preparedStatement.close();
		}
		if (connection != null) {
			connection.close();
		}
	}
}
