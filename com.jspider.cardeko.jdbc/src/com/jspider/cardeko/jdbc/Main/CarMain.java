package com.jspider.cardeko.jdbc.Main;

import java.sql.SQLException;
import java.util.Scanner;

import com.jspider.cardeko.jdbc.CarOpreation.CarOpreation;

public class CarMain {

	private static CarOpreation carOperation = new CarOpreation();
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {

		boolean flag = true;
		while (flag) {
			System.out.println(
					"Enter 1 to add a car\nEnter 2 to search car by id\nEnter 3 to search car by name\nEnter 4 to fetch all cars\nEnter 5 to delete a car\nEnter 6 to update car price\nEnter 7 to exit");
			System.out.println("Enter your choice");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				CarOpreation.addCar(scanner);
				System.out.println();
				break;
			case 2:
				carOperation.findCarById(scanner);
				System.out.println();
				break;
			case 3:
				carOperation.findCarByname(scanner);
				System.out.println();
				break;
			case 4:
				carOperation.findAllCars();
				System.out.println();
				break;
			case 5:
				carOperation.deleteCar(scanner);
				System.out.println();
				break;
			case 6:
				carOperation.updateCar(scanner);
				System.out.println();
				break;
			case 7:
				flag = false;
				System.out.println("Thank You");
				break;
			default:
				System.out.println("Invalid choice");
				System.out.println();
			}
		}
		scanner.close();

	}
}
