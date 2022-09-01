package interviews.wio;

public class CarService {
    /*
    You need to implement parking service, car can enter the parking, car can
    exit the parking. Also we want to know how many cars are currently inside
    the parking.
     */

    public static void main(String[] args) {
        CarParking parkedCars = new CarParking();

        parkedCars.enterCar(new Car("GL12ABC", "John", 2));
        parkedCars.enterCar(new Car("GL13ALL", "John", 2));
        parkedCars.enterCar(new Car("GL15AGG", "John", 2));
        parkedCars.enterCar(new Car("GL144BAC", "John", 2));
        parkedCars.enterCar(new Car("GL162APC", "John", 2));

        System.out.println(parkedCars.getNrOfCars());
    }
}
