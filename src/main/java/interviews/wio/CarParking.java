package interviews.wio;

import java.util.ArrayList;

public class CarParking {
    ArrayList<Car> parkingCarsStored = new ArrayList<>();

    public CarParking() {
        this.parkingCarsStored = parkingCarsStored;
    }

    public void enterCar(Car temp) {
        this.parkingCarsStored.add(temp);
    }

    public void exitCar(Car temp) {
        this.parkingCarsStored.remove(temp);
    }

    public int getNrOfCars() {
        return this.parkingCarsStored.size();
    }

    public Car getCar(int index) {
        return this.parkingCarsStored.get(index);
    }
}
