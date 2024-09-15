package labs.lab3_bus;

public class Bus {
    private int seats;
    private int price;
    private int occupied;

    public Bus() {
        this.seats = 0;
        this.price = 0;
        this.occupied = 0;
    }

    public Bus(int seats, int price) {
        this.seats = seats;
        this.price = price;
        this.occupied = 0;
    }

    public Bus(Bus bus) {
        if (bus == null) {
            this.seats = 0;
            this.price = 0;
            this.occupied = 0;
            return;
        }
        this.seats = bus.seats;
        this.price = bus.price;
        this.occupied = bus.occupied;
    }

    public int calculateFreeSeats() {
        return seats - occupied;
    }

    public boolean isFull() {
        return occupied == seats;
    }

    public int calculateTotalPrice() {
        return price * occupied;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    /**
     * Set bus price
     * if occupied seats > seats then occupied seats = seats
     * 
     * @param price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    public void setOccupied(int occupied) {
        if (occupied > seats) {
            this.occupied = seats;
        }
        this.occupied = occupied;
    }

    public int getSeats() {
        return seats;
    }

    public int getPrice() {
        return price;
    }

    public int getOccupied() {
        return occupied;
    }
}