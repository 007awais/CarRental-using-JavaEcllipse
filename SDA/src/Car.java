import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class Car {

	 private int carID;
	    private String brand;
	    private String model;
	    private int year;
	    private String rentalStatus;
	    private int rentFee;
	    private String features; 
	    private String suited_for;
	    private float insurance;
	    private float damage_cost;
	    private int base_rent;

	    
	    // Constructor
	    public Car() 
	    {
	        this.carID = 0;
	        this.brand = null;
	        this.model = null;
	        this.year = 0;
	        this.rentalStatus = null;
	        this.rentFee = 0;
	        this.features = null;
	        this.suited_for = null;
	        this.insurance = 0.0f;
	        this.damage_cost = 0.0f;
	        this.base_rent = 0;
	    }
	    
	    
	    
	    public int getCarID() 
	    {
	        return carID;
	    }

	    public void setCarID(int carID) 
	    {
	        this.carID = carID;
	    }

	    public String getBrand() {
	        return brand;
	    }

	    public void setBrand(String brand) 
	    {
	        this.brand = brand;
	    }

	    public String getModel() {
	        return model;
	    }

	    public void setModel(String model) 
	    {
	        this.model = model;
	    }

	    public int getYear() {
	        return year;
	    }

	    public void setYear(int year) 
	    {
	        this.year = year;
	    }

	    public String getRentalStatus() 
	    {
	        return rentalStatus;
	    }

	    public void setRentalStatus(String rentalStatus) 
	    {
	        this.rentalStatus = rentalStatus;
	    }

	    public int getRentFee() {
	        return rentFee;
	    }

	    public void setRentFee(int rentFee) 
	    {
	        this.rentFee = rentFee;
	    }

	    public String getFeatures() 
	    {
	        return features;
	    }
	    
	    public void setFeatures(String features) 
	    {
	        
	        this.features = features;
	    }
	    
	    public String getSuitedFor() 
	    {
	        return suited_for;
	    }
	    
	    public void setSuitedFor(String suited_for) 
	    {
	        this.suited_for = suited_for;
	    }
	    
	    public float getInsurance() 
	    {
	        return insurance;
	    }
	    
	    public void setInsurance(float insurance) 
	    {
	        this.insurance = insurance;
	    }

	    public float getDamageCost() 
	    {
	        return damage_cost;
	    }
	    
	    public void setDamageCost(float damage_cost) 
	    {
	        this.insurance = damage_cost;
	    }

	    public int getBase_rent() 
	    {
	        return base_rent;
	    }

	    public void setBase_rent(int base_rent) 
	    {
	        this.base_rent = base_rent;
	    }
	    
	    
	    void RentCalculation(int distance_travelled)
	    {
	        this.setRentFee(distance_travelled + this.getBase_rent());
	    }


	    // To print the details of the car
	    public void printCarDetails() {
	        // %-10d for integers (left-aligned, 10 characters wide)
	        // %-15s for strings (left-aligned, 15 characters wide)
	        System.out.printf("%-10d | %-30s | %-30s | %-30s | %-10d\n", 
	                          carID, 
	                          brand, 
	                          model, 
	                          rentalStatus, 
	                          rentFee);
	    }

	    
	    
	    
	    public static void addCar(String carName, String carType, double pricePerDay, boolean availability) {
	        String insertCarSQL = "INSERT INTO Cars (CarName, CarType, PricePerDay, Availability) VALUES (?, ?, ?, ?)";
	        Connection conn = DatabaseConnection.getConnection();
	        try (PreparedStatement pstmt = conn.prepareStatement(insertCarSQL)) {
	            pstmt.setString(1, carName);
	            pstmt.setString(2, carType);
	            pstmt.setDouble(3, pricePerDay);
	            pstmt.setBoolean(4, availability);

	            int rowsAffected = pstmt.executeUpdate();
	            if (rowsAffected > 0) {
	                System.out.println("Car added successfully.");
	            } else {
	                System.out.println("Failed to add the car.");
	            }
	        } catch (SQLException e) {
	            System.out.println("Error occurred while adding the car: " + e.getMessage());
	        }
	    }
	                          
	    
	    public static List<Car> fetchAllCarsFromDB() {
	        List<Car> carList = new ArrayList<>();
	        Connection conn = DatabaseConnection.getConnection(); // Assuming you have DatabaseConnection.java
	        
	        String query = "SELECT CarID, CarName, CarType, PricePerDay, Availability FROM Cars";
	        
	        try {
	            PreparedStatement pstmt = conn.prepareStatement(query);
	            ResultSet rs = pstmt.executeQuery();

	            while (rs.next()) {
	                // Assuming you use the Car class to store the results
	                Car car = new Compact_Car();  // If Car is abstract, use a subclass like CompactCar or LuxuryCar

	                car.setCarID(rs.getInt("CarID"));
	                car.setBrand(rs.getString("CarName"));
	                car.setModel(rs.getString("CarType"));
	                car.setRentFee(rs.getInt("PricePerDay"));
	                car.setRentalStatus(rs.getString("Availability").equals("1") ? "Available" : "Unavailable");

	                carList.add(car); // Add the car to the list
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
	        return carList;
	    }

	    // Method to display all cars
	    public static void displayAllCars(List<Car> carList) {
	        System.out.println("+-------+-------------------------------------------------------------------------+----------+-------------+--------------+");
	        System.out.println("| CarID | CarName                                  | CarType  | PricePerDay | Availability |");
	        System.out.println("+-------+-------------------------------------------------------------------------+----------+-------------+--------------+");

	        for (Car car : carList) {
	            car.printCarDetails(); // Call the method to print car details
	        }

	        System.out.println("+-------+-----------------------+----------+-------------+-----------------------------------------------------------------+");
	    }
	    
	    
	    
	    
	    public static void deleteCar(int carID) {
	        String deleteTransactions = "DELETE FROM RentTransactions WHERE CarID = ?";
	        String deleteCar = "DELETE FROM Cars WHERE CarID = ?";
	        Connection conn = DatabaseConnection.getConnection();
	        try {
	            // Delete from RentTransactions first
	            try (PreparedStatement pstmt1 = conn.prepareStatement(deleteTransactions)) {
	                pstmt1.setInt(1, carID);
	                pstmt1.executeUpdate();
	            }

	            // Now delete the car from Cars table
	            try (PreparedStatement pstmt2 = conn.prepareStatement(deleteCar)) {
	                pstmt2.setInt(1, carID);
	                int rowsAffected = pstmt2.executeUpdate();

	                if (rowsAffected > 0) {
	                    System.out.println("Car with ID " + carID + " and related transactions were deleted successfully.");
	                } else {
	                    System.out.println("No car found with ID " + carID);
	                }
	            }
	        } catch (SQLException e) {
	            System.out.println("Error occurred while deleting the car: " + e.getMessage());
	        }
	    }
	}
	    
	    
	    
	    
	    

