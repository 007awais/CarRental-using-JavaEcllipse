import java.util.Scanner;
import java.util.List;
import java.util.NoSuchElementException;
import java.sql.Connection;
import java.util.ArrayList;
//import java.sql.Connection;          // To handle database connections
//import java.sql.DriverManager;       // For managing the connection driver
//import java.sql.PreparedStatement;   // For executing SQL queries with parameters (used in insert, update, delete)
//import java.sql.ResultSet;           // To handle the result of SQL SELECT queries
//import java.sql.SQLException;        // To catch SQL-related exceptions
import java.util.InputMismatchException;


public class main 
{
    static Scanner sc = new Scanner(System.in);

    // Utility Functions
    static void Print(String str)
    {
        System.out.println(str);
    }
    public static void printRenterDetails(List<Renter> renters) {
        // Method implementation here
    }

    public static void printCarDetails(List<Car> cars) {
        // Method implementation here
    }


    // Main Function
    public static void main(String[] args) 
    {
        List<Car> carList = new ArrayList<>();
        List<Renter> renterList = new ArrayList<>();
        while (true) 
        {
            

         	
            
            Print("Choose User Mode:");
            Print("1. Car Management");
            Print("2. Renter Management");
            Print("3. Rent Transactions");
            Print("4. Exit\n");
            int input = sc.nextInt();
            if(input == 1)
            {
                Print("Choose Car Management Option:");
                Print("1. Add Car");
                Print("2. Display Available Car");
                Print("3. Delete Car");
                Print("4. Exit\n");
                int input1 = sc.nextInt();
                if(input1 == 1)
                {
                    Print("Choose Car Type:");
                    Print("1. Compact Car");
                    Print("2. Luxury Car");
                    Print("3. SUV Car\n");
                    int input2 = sc.nextInt();
                    if(input2 == 1)
                    {
                    	
                    	Scanner scanner = new Scanner(System.in);
                         
                        System.out.println("Enter Car Name: ");
                        String carName = scanner.nextLine();

                        System.out.println("Enter Car Type: ");
                        String carType = scanner.nextLine();

                        System.out.println("Enter Price Per Day: ");
                        double pricePerDay = scanner.nextDouble();

                        System.out.println("Is the car available? (true/false): ");
                        boolean availability = scanner.nextBoolean();

                        // Call the method to add the car
                        Car.addCar(carName, carType, pricePerDay, availability);

                        // Close the scanner
                        scanner.close();
                    }
                    else if(input2 == 2)
                    {
                    	Scanner scanner = new Scanner(System.in);
                        
                        System.out.println("Enter Car Name: ");
                        String carName = scanner.nextLine();

                        System.out.println("Enter Car Type: ");
                        String carType = scanner.nextLine();

                        System.out.println("Enter Price Per Day: ");
                        double pricePerDay = scanner.nextDouble();

                        System.out.println("Is the car available? (true/false): ");
                        boolean availability = scanner.nextBoolean();

                        // Call the method to add the car
                        Car.addCar(carName, carType, pricePerDay, availability);

                        // Close the scanner
                        scanner.close();
                    	
                    }
                    
                    
                    else if(input2 == 3)
                    {
                    	Scanner scanner = new Scanner(System.in);
                        
                        System.out.println("Enter Car Name: ");
                        String carName = scanner.nextLine();

                        System.out.println("Enter Car Type: ");
                        String carType = scanner.nextLine();

                        System.out.println("Enter Price Per Day: ");
                        double pricePerDay = scanner.nextDouble();

                        System.out.println("Is the car available? (true/false): ");
                        boolean availability = scanner.nextBoolean();

                        // Call the method to add the car
                        Car.addCar(carName, carType, pricePerDay, availability);

                        // Close the scanner
                        scanner.close();

                    }
                    
                }
                else if(input1 == 2)
                {
                	List<Car> carList1 = Car.fetchAllCarsFromDB();

                    // Display all cars
                    if (!carList1.isEmpty()) {
                        Car.displayAllCars(carList1);
                    } else {
                        System.out.println("No cars found in the database.");
                    }
                }
                else if(input1 == 3)
                {
                	Scanner scanner = new Scanner(System.in); // Initialize the Scanner

                   
                        System.out.println("Enter the CarID to delete: ");
                        int carID = scanner.nextInt(); // Take input from the user

                        // Call the deleteCar function from CarDatabase class
                        Car.deleteCar(carID);
                  
                
                }
                else if(input1 == 4)
                {
                    System.exit(0);
                }
            }

            else if(input == 2)
            {
                Print("Choose Renter Management Option:");
                Print("1. Add Renter");
                Print("2. Display Renter");
                Print("3. Delete Renter");
                Print("4. Exit");
                int input1 = sc.nextInt();
                if(input1 == 1)
                {
                    Print("Choose Car Type:");
                    Print("1. Regular Renter");
                    Print("2. Frequent Renter");
                    Print("3. Corporate Renter");
                    int input2 = sc.nextInt();
                    if(input2 == 1)
                    {
                    	Renter.addRenterToDB();  
                        
                    }
                    else if(input2 == 2)
                    {
                    	 Frequent_Renter f = new Frequent_Renter();
                         renterList.add(f);
                    }
                    else if(input2 == 3)
                    {
                        Corporate_Renter c = new Corporate_Renter();
                        renterList.add(c);

                    }
                

                }
                else if(input1 == 2)
                {
                	  List<Renter> renterList1 = Renter.fetchAllRentersFromDB();

                      // Display all renters
                      if (!renterList1.isEmpty()) {
                          Renter.displayAllRenters(renterList1);
                      } else {
                          System.out.println("No renters found in the database.");
                      }
                  }
                
                else if(input1 == 3)
                {
                	 Scanner scanner = new Scanner(System.in);
                     System.out.println("Enter Renter ID to delete: ");
                     int renterId = scanner.nextInt();
                     Renter.deleteRenter(renterId);

                }
                else if(input1 == 4)
                {
                    System.exit(0);
                }
            }

            else if(input == 3)
            {
                Print("Choose Rent Transaction Option:");
                Print("1. Rent a car to a renter.");
                Print("2. Calculate and display the total rental cost.");
                Print("3. Add insurance to a rented car.");
                Print("4. Calculate and display the damage cost.");
                Print("5. View all rent transactions.");
                Print("6. Exit");
                int input1 = sc.nextInt();
                if(input1 == 1)
                {
                    printRenterDetails(renterList);
                    Print("Enter Renter ID:");
                    int id = sc.nextInt();
                    int flag = 0;
                    for(int i = 0; i < renterList.size(); i++)
                    {
                        if(renterList.get(i).getRenterID() == id)
                        {
                            printCarDetails(carList);
                            Print("Enter Car ID:");
                            int id1 = sc.nextInt();
                            for(int j = 0; j < carList.size(); j++)
                            {
                                if(carList.get(j).getCarID() == id1)
                                {
                                        renterList.get(i).setRentedCars(carList.get(j));
                                        System.out.println(renterList.get(i).getRentedCars());
                                        carList.get(j).setRentalStatus("Rented");
                                    Print("Car Rented Successfully!");
                                    flag = 1;
                                }
                            }
                        }
                    }
                    if(flag == 0)
                        Print("Renter Not found!");
                }
                else if(input1 == 2)
                {
                    printCarDetails(carList);
                    Print("Enter Car ID:");
                    int id = sc.nextInt();
                    for(int i = 0; i < carList.size(); i++)
                    {
                        if(carList.get(i).getCarID() == id)
                        {
                            double total = 0;
                            if (carList.get(i).getRentalStatus() == "Rented")
                            {
                                Print("Enter Distance Travelled:");
                                int distance = sc.nextInt();
                                carList.get(i).RentCalculation(distance);

                                total = carList.get(i).getRentFee();
                                Print("Total Rent " + total);
                            }
                            else
                                Print("No Rented Cars");
                        }
                    }
                }
                else if(input1 == 3)
                {
                	Scanner scanner = new Scanner(System.in);
                    System.out.println("Enter Renter ID: ");
                    int renterId = scanner.nextInt();

                    // Display insurance options
                    Renter.displayInsuranceOptions();

                    System.out.println("Enter Insurance ID to select: ");
                    int insuranceId = scanner.nextInt();

                    // Assume method to rent a car takes insuranceId
                    Renter.rentCar(renterId, insuranceId);
                }
                else if(input1 == 4)
                {
                    //Renter.displayTotalDamageCost(input1)
                }
                else if(input1 == 5)
                {
                   Renter.displayRentTransactions();
                }
                else if(input1==6)
                {
                	System.exit(0);
                }
            }
            else if(input == 4)
            {
                System.exit(0);
            }
        }
           
    }
}
   

    
    