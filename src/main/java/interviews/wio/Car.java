package interviews.wio;

public class Car {
    private String licenceNumber;
    private String driverName;
    private int nrOfHours;

    public Car(String licenceNumber, String driverName, int nrOfHours) {
        this.licenceNumber = licenceNumber;
        this.driverName = driverName;
        this.nrOfHours = nrOfHours;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public int getNrOfHours() {
        return nrOfHours;
    }

    public void setNrOfHours(int nrOfHours) {
        this.nrOfHours = nrOfHours;
    }
}
