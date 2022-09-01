package interviews.wio;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestParkingService {

    @Test(description = "Check Total Nr Of Cars")
    public void checkNrOfTotalCarsIsCorrect() {
        CarParking parkedCars = new CarParking();

        parkedCars.enterCar(new Car("GL12ABC", "John", 2));
        parkedCars.enterCar(new Car("GL13ALL", "John", 2));
        parkedCars.enterCar(new Car("GL15AGG", "John", 2));
        parkedCars.enterCar(new Car("GL144BAC", "John", 2));
        parkedCars.enterCar(new Car("GL162APC", "John", 2));

        Assert.assertEquals(parkedCars.getNrOfCars(), 5, "Total number of cars is not matching");
    }

    @Test(description = "Check if entering a new car in the parking changes the total nr of cars")
    public void checkEnterCarFunctionality() {
        CarParking parkedCars = new CarParking();

        parkedCars.enterCar(new Car("GL12ABC", "John", 2));
        parkedCars.enterCar(new Car("GL13ALL", "John", 2));
        int nrOfCars = parkedCars.getNrOfCars();
        Assert.assertTrue(nrOfCars == 2, "Nr of total is not matching");

        parkedCars.enterCar(new Car("GL162APC", "John", 2));
        Assert.assertTrue(nrOfCars + 1 == parkedCars.getNrOfCars(), "Nr of total is not matching");
    }

    @Test(description = "Check if exiting an existing car in the parking changes the total nr of cars")
    public void checkExitCarFunctionality() {
        CarParking parkedCars = new CarParking();

        parkedCars.enterCar(new Car("GL12ABC", "John", 2));
        parkedCars.enterCar(new Car("GL13ALL", "John", 2));
        int nrOfCars = parkedCars.getNrOfCars();
        Assert.assertTrue(nrOfCars == 2, "Nr of total is not matching");

        Car deleteObj = parkedCars.getCar(0);
        parkedCars.exitCar(deleteObj);
        Assert.assertTrue(nrOfCars - 1 == parkedCars.getNrOfCars(), "Nr of total is not matching");
    }

    @Test(description = "Check if new car fields are correct")
    public void checkIfEnteredCarsFieldsAreCorrect() {
        CarParking parkedCars = new CarParking();
        String licenseNumber = "GL12ABC";
        String name = "John";
        int nrOfHours = 2;

        parkedCars.enterCar(new Car(licenseNumber, name, nrOfHours));
        Assert.assertEquals(licenseNumber, parkedCars.getCar(0).getLicenceNumber(), "License number is not matching");
        Assert.assertEquals(name, parkedCars.getCar(0).getDriverName(), "Driver name is not matching");
        Assert.assertEquals(nrOfHours, parkedCars.getCar(0).getNrOfHours(), "Nr of hours is not matching");
    }
}
