package labs.lab3_bus;

public class Buses {
    public static void main(String[] args) {
        Bus bus1 = new Bus(54, 400);
        Bus bus2 = new Bus(45, 500);

        bus1.setOccupied(25);
        bus2.setOccupied(30);

        if (bus1.calculateTotalPrice() > 11000) {
            System.out.println("Bus 1 is vigodno");
        } else {
            System.out.println("Bus 1 is not vigodno");
        }

        if (bus2.calculateTotalPrice() > 11000) {
            System.out.println("Bus 2 is vigodno");
        } else {
            System.out.println("Bus 2 is not vigodno");
        }
    }
}
