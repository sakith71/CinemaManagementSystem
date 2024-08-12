import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * The CinemaManagement class of the Cinema Management application.
 * This class contains the main method which serves as the entry point for the application.
 * It initialises the seating arrangement and ticket array, and provides menu options to the user.
 */
public class CinemaManagementSystem {
    public static int[][] seats = new int[3][16];

    /**
     * Array storing ticket objects.
     * Each element represents a ticket purchased by a customer.
     */
    public static Ticket[] tickets = new Ticket[48];

    /**
     * Variables to store row number, seat number, and ticket index.
     */
    public static int rowNumber, seatNumber, ticketIndex = 0;

    /**
     * Variables to store  passenger name, surname, email, and ticket price.
     */
    public static String name, surname, email;
    public static double price;

    /**
     * The main method serves as the entry point for the Cinema Management application.
     * It displays the menu options to the user and processes their choices.
     * The menu options allow the user to buy tickets, cancel tickets, see the seating plan,
     * find the first available seat, print ticket information and total price, search for tickets,
     * sort tickets by price, and exit the application.
     *
     * @param args Command-line arguments (not used in this application)
     */
    public static void main(String[] args) {

        // Display welcome message and menu options
        System.out.println(" ");
        System.out.println("\tWelcome to The London Lumiere");
        boolean isValid = true;
        while (isValid) {
            System.out.println("-".repeat(50));
            System.out.println("Please select an option:");
            System.out.println("\t\t1) Buy a ticket");
            System.out.println("\t\t2) Cancel ticket");
            System.out.println("\t\t3) See seating plan");
            System.out.println("\t\t4) Find first seat available");
            System.out.println("\t\t5) Print ticket information and total price");
            System.out.println("\t\t6) Search ticket");
            System.out.println("\t\t7) Search ticket");
            System.out.println("\t\t0) Exit");
            System.out.println("-".repeat(50));
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Select option: ");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        buyTicket();
                        break;
                    case 2:
                        cancelTicket();
                        break;
                    case 3:
                        printSeatingArea();
                        break;
                    case 4:
                        findFirstAvailable();
                        break;
                    case 5:
                        printTicketInfo();
                        break;
                    case 6:
                        searchTicket();
                        break;
                    case 7:
                        sortTickets();
                        break;
                    case 0:
                        System.out.println();
                        isValid = false;
                        break;
                    default:
                        System.out.println("Please enter valid option.");
                        break;
                }
            } catch (InputMismatchException ex) {
                System.out.println("WARNING!..... Please enter a valid integer.");
            }
        }
    }

    /**
     * Prompts the user to select a seat by entering a row letter and seat number.
     * The method ensures the entered row and seat numbers are valid.
     * If the input is invalid, it prompts the user to enter the values again.
     */
    public static void selectSeat(){
        boolean isValid = true;
        while (isValid) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter a row letter: ");
                rowNumber = scanner.nextInt();
                if (rowNumber > 0 && rowNumber < 4) {
                    isValid = false;
                } else
                    System.out.println("Invalid row number. Please enter valid row number.");
            }catch (InputMismatchException ex) {
                System.out.println("WARNING!..... Please enter valid integer.");
            }
        }
        while (!isValid) {
            try {
                Scanner scanner1 = new Scanner(System.in);
                System.out.print("Enter seat number: ");
                seatNumber = scanner1.nextInt();
                if (seatNumber > 0 && seatNumber < 17) {
                    isValid = true;
                } else {
                    System.out.println("Invalid seat number. Please enter valid seat number.");
                }
            } catch (InputMismatchException ex) {
                System.out.println("WARNING!..... Please enter valid integer.");
            }
        }
    }

    /**
     * Allows the user to buy a ticket by selecting a seat and entering their details.
     * The method checks if the selected seat is already booked.
     * If the seat is available, it prompts the user to enter their first name, surname, and email.
     * It validates the entered details and calculates the ticket price.
     * A new ticket is created and added to the tickets array, and the seat is marked as sold.
     */
    public static void buyTicket() {
        selectSeat();

        if (seats[rowNumber - 1 ][seatNumber - 1 ] == 1) {
            System.out.println("This seat is already booked");
        } else {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter your first name: ");
            while (true) {
                name = scanner.nextLine().toUpperCase();
                if (!name.matches("[A-Z]*$")) {
                    System.out.println("Invalid name");
                    System.out.print("Enter your name again: ");
                } else break;
            }

            System.out.print("Enter your surname: ");
            while (true) {
                surname = scanner.nextLine().toUpperCase();
                if (!surname.matches("[A-Z]*$")) {
                    System.out.println("Invalid surname");
                    System.out.print("Enter your surname again: ");
                } else break;
            }

            while (true) {
                System.out.print("Enter your email: ");
                email = scanner.nextLine();
                if (email.matches("^[a-z0-9]+(?:\\.[a-z0-9]+)*@(?:[a-z]+\\.)+[a-z]{2,7}$")) {
                    break;
                } else
                    System.out.println("Invalid email. Please enter email again.");
            }


            Person person = new Person(name, surname, email);
            price = ticketPrice();

            Ticket ticket = new Ticket(rowNumber, seatNumber, price, person);
            tickets[ticketIndex] = ticket;
            ticketIndex++;

            seats[rowNumber-1][seatNumber-1] = 1;
            System.out.println("This seat booked successfully");

        }
    }

    /**
     * Cancels a ticket by selecting a seat and marking it as available.
     * The method checks if the selected seat is already available.
     * If the seat is booked, it removes the associated ticket from the tickets array and marks the seat as available.
     */
    public static void cancelTicket() {
        selectSeat();

        if (seats[rowNumber - 1][seatNumber - 1] == 0) {
            System.out.println("This seat is already available.");
        } else {
            for (int i = 0; i < ticketIndex; i++) {
                if (tickets[i] != null && tickets[i].getRow() == rowNumber && tickets[i].getSeat() == seatNumber) {
                    tickets[i] = null;

                    seats[rowNumber - 1][seatNumber - 1] = 0;
                    System.out.println("This seat canceled successfully.");
                }
            }
        }
    }
    /**
     * Finds and prints the first available seat in the seating area.
     * The method iterates through the seating arrangement and prints the row and seat number of the first available seat.
     */
    public static void findFirstAvailable() {
        for (int row = 0; row < seats.length; row++) {
            for (int col = 0; col < seats[row].length; col++) {
                if (seats[row][col] == 0) {
                    String rowNumber = (row == 0) ? "Row 1" : (row == 1) ? "Row 2" : "Row 3";
                    System.out.println("Available seat is: "+ rowNumber + " Seat number" +(col + 1));
                    return;
                }
            }
        }
    }
    /**
     * Determines the ticket price based on the selected row number.
     * The method returns a price based on the row:
     * - Row 1: £12
     * - Row 2: £10
     * - Row 3: £8
     *
     * @return the price of the ticket based on the row number
     */
    public static double ticketPrice() {
        if (rowNumber == 1) {
            return 12;
        } else if (rowNumber == 2) {
            return 10;
        } else {
            return 8;
        }
    }
    /**
     * Prints the seating area of the theatre.
     * The seating area displays the seat numbers and their availability status.
     * 'O' indicates an available seat and 'X' indicates a sold seat.
     * The method also prints the ticket price for each row.
     */
    public static void printSeatingArea() {
        System.out.print("\t");
        for (int i = 0; i < seats[0].length; i++) {
            if (i==8){
                System.out.print("\t");
            }
            System.out.print("\t" + (i + 1));
        }
        System.out.println();

        int n = 1;
        for (int[] seat : seats) {
            System.out.print(("Row " + n) + ": ");

            for (int col = 0; col < seat.length; col++) {
                if (col == 8) {
                    System.out.print("\t");
                }
                System.out.print(seat[col] == 0 ? "\tO" : "\tX");
            }
            System.out.println(n==1?"\t£12":n==2?"\t£10":"\t£8");
            n++;
        }
    }
    /**
     * Prints the information of all tickets sold during the session and calculates the total price.
     * If no tickets are sold, it prints a message indicating that no tickets were sold.
     * Otherwise, it prints the details of each sold ticket and the total price.
     */
    public static void printTicketInfo() {
        double totalTicket = 0;
        boolean isValid = true;
        System.out.println("Tickets sold during the session:");
        for (Ticket ticket : tickets) {
            if (ticket != null) {
                ticket.ticketInfo();
                totalTicket += ticket.getPrice();
                isValid = false;
            }
        }
        if (isValid) {
            System.out.println("No tickets sold during the session.");
        } else {
            System.out.println("Total price: £" + totalTicket);
        }
    }

    /**
     * Searches for a ticket based on the selected seat.
     * If the seat is found in the tickets array, it displays the ticket information.
     * If the seat is available (not found in the tickets array), it prints a message indicating that the seat is available.
     */
    public static void searchTicket() {
        selectSeat();

        boolean isValid = true;
        for (Ticket ticket : tickets) {
            if (ticket != null && ticket.getRow() == rowNumber && ticket.getSeat() == seatNumber) {
                isValid = false;
                ticket.ticketInfo();
            }
        }
        if (isValid) {
            System.out.println("This seat is available.");
        }
    }
    /**
     * Sorts the tickets array by ticket price in ascending order using bubble sort algorithm.
     * Then, it prints the sorted tickets by displaying their information.
     */
    public static void sortTickets(){
        for (int i = 0; i < ticketIndex - 1; i++) {
            for (int j = 0; j < ticketIndex - i - 1; j++) {
                if (tickets[j] != null && tickets[j + 1] != null && tickets[j].getPrice() > tickets[j + 1].getPrice()) {
                    Ticket temp = tickets[j];
                    tickets[j] = tickets[j + 1];
                    tickets[j + 1] = temp;
                }
            }
        }

        System.out.println("Sorted tickets by price:");
        for (Ticket ticket : tickets) {
            if (ticket != null) {
                ticket.ticketInfo();
            }
        }
    }
}