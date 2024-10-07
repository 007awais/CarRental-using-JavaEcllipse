import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;       // For managing the connection driver
import java.sql.PreparedStatement;   // For executing SQL queries with parameters (used in insert, update, delete)
import java.sql.ResultSet;           // To handle the result of SQL SELECT queries
import java.sql.SQLException;        // To catch SQL-related exceptions
import java.util.Scanner;


public abstract class Renter 
{
    // Abstract class Renter
    private int renterID;
    private String name;
    private String email;
    // Composition
    private List<Car> rentedCars;
    private double totalRentalFee;
    private String phoneNumber;
    private String address;
    private String features;

    // Constructor
    public Renter() 
    {
        this.rentedCars = new ArrayList<>();
    }
    // Getters and Setters
    public int getRenterID() 
    {
        return renterID;
    }


    public void setRenterID(int renterID) 
    {
        this.renterID = renterID;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getEmail() 
    {
        return email;
    }

    public void setContact(String email) 
    {
        this.email = email;
    }

    public List<Car> getRentedCars() 
    {
        return rentedCars;
    }

    public void setRentedCars(Car rentedCar) 
    {
        if (this.rentedCars == null) 
        {
            this.rentedCars = new ArrayList<>();
        }
        
        this.rentedCars.add(rentedCar);
    }

    public double getTotalRentalFee() 
    {
        return totalRentalFee;
    }

    public void setTotalRentalFee(double totalRentalFee) 
    {
        this.totalRentalFee = totalRentalFee;
    }

    public String getPhoneNumber() 
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) 
    {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() 
    {
        return address;
    }

    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getFeatures() 
    {
        return features;
    }
    
    public void setFeatures(String features) 
    {
        this.features = features;
    }

    // Method
    public void printRenterDetails() {
        // Print the table header once
        System.out.printf("%-10s | %-20s | %-30s |\n", "RenterID", "Name", "Email");
        System.out.println("-------------------------------------------------------------");

        // Print the renter's details
        System.out.printf("%-10d | %-20s | %-30s |\n", 
                            renterID, 
                            name, 
                            email);
    }

      
      public static List<Renter> fetchAllRentersFromDB() {
          List<Renter> renterList = new ArrayList<>();
          Connection conn = DatabaseConnection.getConnection();
          String query = "SELECT * FROM Renters";
          
          try {
              PreparedStatement pstmt = conn.prepareStatement(query);
              ResultSet rs = pstmt.executeQuery();
              
              while (rs.next()) {
                  // Here, you may create a concrete subclass of Renter (Regular_Renter, Frequent_Renter, etc.)
                  // Assuming you use Renter directly for now:
                  Renter renter = new Regular_Renter(); // Assuming Regular_Renter is a subclass
                  
                  renter.setRenterID(rs.getInt("RenterID"));
                  renter.setName(rs.getString("RenterName"));
                  renter.setContact(rs.getString("ContactInfo"));
               

                  // Add the renter to the list
                  renterList.add(renter);
              }
          } catch (SQLException e) {
              e.printStackTrace();
          } finally {
              try {
                  if (conn != null) {
                      conn.close();
                  }
              } catch (SQLException e) {
                  e.printStackTrace();
              }
          }
          return renterList;
      }

      // Method to display all renters
      public static void displayAllRenters(List<Renter> renterList) {
          System.out.println("Renter ID | Name | Email");
          for (Renter renter : renterList) {
              renter.printRenterDetails(); // Use the print method from Renter class
          }
      }
      
      
      public static void addRenterToDB() {
          Scanner sc = new Scanner(System.in);

          // Get renter details from the user
          System.out.print("Enter Renter Name: ");
          String name = sc.nextLine();

          System.out.print("Enter Renter Email: ");
          String contactInfo = sc.nextLine();

          Connection conn = null;
          PreparedStatement pstmt = null;
          ResultSet rs = null;

          try {
              // Establish a connection to the database
              conn = DatabaseConnection.getConnection();

              // Check if renter already exists
              String checkQuery = "SELECT COUNT(*) FROM Renters WHERE ContactInfo = ?";
              pstmt = conn.prepareStatement(checkQuery);
              pstmt.setString(1, contactInfo);
              rs = pstmt.executeQuery();

              if (rs.next() && rs.getInt(1) > 0) {
                  System.out.println("A renter with this email already exists.");
              } else {
                  // Prepare the insert statement
                  String insertQuery = "INSERT INTO Renters (RenterName, ContactInfo) VALUES (?, ?)";
                  pstmt = conn.prepareStatement(insertQuery);
                  pstmt.setString(1, name);
                  pstmt.setString(2, contactInfo);

                  // Execute the insert statement
                  int rowsInserted = pstmt.executeUpdate();
                  if (rowsInserted > 0) {
                      System.out.println("A new renter was inserted successfully!");
                  } else {
                      System.out.println("Failed to insert renter.");
                  }
              }
          } catch (SQLException e) {
              e.printStackTrace();
          } finally {
              try {
                  if (rs != null) rs.close();
                  if (pstmt != null) pstmt.close();
                  if (conn != null) conn.close();
              } catch (SQLException ex) {
                  ex.printStackTrace();
              }
          }

          sc.close();
      }
      
      
      
      public static void displayRentTransactions() {
          Connection conn = null;
          PreparedStatement pstmt = null;
          ResultSet rs = null;

          try {
              // Establish a connection to the database
              conn = DatabaseConnection.getConnection();

              // Prepare the SQL query
              String query = "SELECT rt.TransactionID, rt.CarID, rt.RenterID, r.RenterName, rt.RentDate, rt.ReturnDate, rt.TotalAmount " +
                             "FROM RentTransactions rt " +
                             "JOIN Renters r ON rt.RenterID = r.RenterID";

              pstmt = conn.prepareStatement(query);

              // Execute the query and receive the result set
              rs = pstmt.executeQuery();

              // Process the result set
              while (rs.next()) {
                  int transactionId = rs.getInt("TransactionID");
                  int carId = rs.getInt("CarID");
                  int renterId = rs.getInt("RenterID");
                  String renterName = rs.getString("RenterName");
                  java.sql.Date rentDate = rs.getDate("RentDate");
                  java.sql.Date returnDate = rs.getDate("ReturnDate");
                  double totalAmount = rs.getDouble("TotalAmount");

                  System.out.println("Transaction ID: " + transactionId +
                                     ", Car ID: " + carId +
                                     ", Renter ID: " + renterId +
                                     ", Renter Name: " + renterName +
                                     ", Rent Date: " + rentDate +
                                     ", Return Date: " + returnDate +
                                     ", Total Amount: $" + totalAmount);
              }
          } catch (SQLException e) {
              e.printStackTrace();
          } finally {
              // Close all connections
              try {
                  if (rs != null) rs.close();
                  if (pstmt != null) pstmt.close();
                  if (conn != null) conn.close();
              } catch (SQLException ex) {
                  ex.printStackTrace();
              }
          }
      }
      public void displayTotalDamageCost(int transactionId) {
          Connection conn = null;
          PreparedStatement pstmt = null;
          ResultSet rs = null;

          try {
              conn = DatabaseConnection.getConnection();
              String query = "SELECT SUM(Cost) as TotalDamage FROM DamageCosts WHERE TransactionID = ?";

              pstmt = conn.prepareStatement(query);
              pstmt.setInt(1, transactionId);
              rs = pstmt.executeQuery();

              if (rs.next()) {
                  double totalDamage = rs.getDouble("TotalDamage");
                  System.out.println("Total Damage Cost for Transaction ID " + transactionId + ": $" + totalDamage);
              } else {
                  System.out.println("No damage costs found for Transaction ID " + transactionId);
              }
          } catch (SQLException e) {
              e.printStackTrace();
          } finally {
              try {
                  if (rs != null) rs.close();
                  if (pstmt != null) pstmt.close();
                  if (conn != null) conn.close();
              } catch (SQLException ex) {
                  ex.printStackTrace();
              }
          }
      }

      private static void deleteRelatedTransactions(int renterId) throws SQLException {
    	Connection  conn = DatabaseConnection.getConnection();
          String deleteTransactionsSQL = "DELETE FROM RentTransactions WHERE RenterID = ?";
          try (PreparedStatement pstmt = conn.prepareStatement(deleteTransactionsSQL)) {
              pstmt.setInt(1, renterId);
              pstmt.executeUpdate();
          }
      }
      
      
      public static void deleteRenter(int renterId) {
          try {
              // First, delete any related transactions
              deleteRelatedTransactions(renterId);
             Connection conn = DatabaseConnection.getConnection();
              // Now, delete the renter
              String deleteRenterSQL = "DELETE FROM Renters WHERE RenterID = ?";
              try (PreparedStatement pstmt = conn.prepareStatement(deleteRenterSQL)) {
                  pstmt.setInt(1, renterId);

                  int rowsAffected = pstmt.executeUpdate();
                  if (rowsAffected > 0) {
                      System.out.println("Renter deleted successfully with RenterID: " + renterId);
                  } else {
                      System.out.println("No renter found with RenterID: " + renterId);
                  }
              }
          } catch (SQLException e) {
              System.out.println("Error occurred while deleting the renter: " + e.getMessage());
          }
      }
      
      public static void displayInsuranceOptions() {
          String query = "SELECT * FROM Insurance";
          Connection conn = DatabaseConnection.getConnection();
        		  try (PreparedStatement pstmt = conn.prepareStatement(query);
               ResultSet rs = pstmt.executeQuery()) {

              System.out.println("Available Insurance Options:");
              while (rs.next()) {
                  System.out.println("ID: " + rs.getInt("InsuranceID") + ", Name: " + rs.getString("InsuranceName") + ", Cost: $" + rs.getDouble("Cost"));
              }
          } catch (SQLException e) {
              System.out.println("Error fetching insurance options: " + e.getMessage());
          }
      }
      public static void rentCar(int renterId, int insuranceId) {
    	    // Implementation of renting a car, including insuranceId
    	    String rentCarSQL = "INSERT INTO RentTransactions (CarID, RenterID, RentDate, ReturnDate, TotalAmount, InsuranceID) VALUES (?, ?, ?, ?, ?, ?)";
    	    // Set parameters, including insuranceId
    	}

      
       
      }
  

      
    