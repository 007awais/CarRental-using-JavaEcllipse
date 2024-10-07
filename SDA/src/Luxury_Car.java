
public class Luxury_Car extends Car {
	
	 // Inheritance
    final String type = "Luxury";
    private final int luxury_tax = 10000;
    Luxury_Car()
    {
        super();
        setFeatures("Luxurious");
        setSuitedFor("Long Distance");
        setBase_rent(35000);
        setInsurance(0);
        setDamageCost(45000);

    }
    
    // Polymorphism
    @Override
    void RentCalculation(int distance_travelled)
    {
        this.setRentFee(distance_travelled + this.getBase_rent() + luxury_tax);
    }

}
