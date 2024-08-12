/**
 * Represents a ticket with information about the seat, price, and person.
 */
public class Ticket {

    private int row;
    private int seat;
    private double price;
    private Person person;

    /**
     * Constructs a ticket with the specified row, seat number, price, and person.
     *
     * @param row the row letter of the seat
     * @param seat the seat number
     * @param price  the price of the ticket
     * @param person the person associated with the ticket
     */
    public Ticket(int row, int seat, double price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    /**
     * Retrieves the row number of the ticket.
     *
     * @return the row number of the ticket
     */
    public int getRow() {
        return row;
    }

    /**
     * Sets the row number of the ticket.
     *
     * @param row the new row number of the ticket
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Retrieves the seat number of the ticket.
     *
     * @return the seat number of the ticket
     */
    public int getSeat() {
        return seat;
    }

    /**
     * Sets the seat number of the ticket.
     *
     * @param seat the new seat number of the ticket
     */
    public void setSeat(int seat) {
        this.seat = seat;
    }

    /**
     * Retrieves the price of the ticket.
     *
     * @return the price of the ticket
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the ticket.
     *
     * @param price the new price of the ticket
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Retrieves the customer associated with the ticket.
     *
     * @return the customer associated with the ticket
     */
    public Person getPerson() {
        return person;
    }

    /**
     * Sets the customer associated with the ticket.
     *
     * @param person the new customer associated with the ticket
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * Prints information about the ticket including row, seat, price, and customer information of the person.
     */
    public void ticketInfo() {
        System.out.println("Ticket Information:");
        System.out.println("\tRow: " + getRow());
        System.out.println("\tSeat: " + getSeat());
        System.out.println("\tPrice: £" + getPrice());
        person.personalInfo();
        System.out.println("-".repeat(50));
    }
}
