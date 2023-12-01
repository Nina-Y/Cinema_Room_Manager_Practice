package cinema;
import java.util.Scanner;

public class Cinema {
    static Scanner scanner = new Scanner(System.in);
    public static int rows = 0;
    public static int seats = 0;
    
    public static void main(String[] args) {
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = scanner.nextInt();
        System.out.println();
        String[][] cinema = new String[rows + 1][seats + 1];
        seats(cinema);      
        menu(cinema);
    }

    public static void menu(String[][] cinema) {
        while (true) {  
            System.out.println("""
                1. Show the seats
                2. Buy a ticket  
                3. Statistics              
                0. Exit""");
            int action = scanner.nextInt();
            System.out.println();
            if (action == 1) {
                printSeats(cinema);                
            } else if (action == 2) {
                buyTicket(cinema);
            } else if (action == 3) {
                statistics(cinema);
            } else if (action == 0) {
                break;
            }
        }  
    }
    
    public static void seats(String[][] cinema) {
        for (int i = 1; i < cinema.length; i++) {
            for (int j = 1; j < cinema[i].length; j++) {
                cinema[i][j] = "S";
            }
        }
    }
    
    public static void printSeats(String[][] cinema) {
        System.out.println("Cinema:");
        for (int i = 0; i < cinema.length; i++) {  
            for (int j = 0; j < cinema[i].length; j++) {
                if (i == 0 && j == 0) {
                    System.out.print(" " + " "); 
                } else if (i == 0 || j == 0) {
                    System.out.print(Math.abs(i-j) + " ");                            
                } else {
                    System.out.print(cinema[i][j] + " ");  
                }
            }
            System.out.println();
        } 
        System.out.println();
    }

    public static void buyTicket(String[][] cinema) {
        System.out.println("Enter a row number:");
        int x = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int y = scanner.nextInt();
        System.out.println();
        if (x < 1 || x > rows || y < 1 || y > seats) {
            System.out.println("Wrong input!");
            System.out.println();
            buyTicket(cinema);
        } else if (cinema[x][y] == "B") {
            System.out.println("That ticket has already been purchased!");
            System.out.println();
            buyTicket(cinema);
        } else {
            int totalSeats = rows * seats;
            int frontRows = rows / 2;
            int price = totalSeats < 60 ? 10 : x > frontRows ? 8 : 10;
            System.out.println("Ticket price: $" + price);
            System.out.println();
            cinema[x][y] = "B";
        }  
    }

    public static void statistics(String[][] cinema) {
        int frontRows = rows / 2;
        int backRows = rows - rows / 2;
        int totalSeats = rows * seats;
        int currentIncome = 0;
        int count = 0;
        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[i].length; j++) {
                if (cinema[i][j] == "B") {
                    count++; 
                    currentIncome += totalSeats < 60 ? 10 : i > frontRows ? 8 : 10;
                }
            } 
        }
        System.out.println("Number of purchased tickets: " + count);   
        double percentage = (double)count * 100 / totalSeats;
        System.out.printf("Percentage: %.2f%%%n", percentage);
        System.out.println("Current income: $" + currentIncome); 
        int totalIncome = totalSeats * 10;
        if (totalSeats > 60) {
            totalIncome = frontRows * seats * 10 + backRows * seats * 8;
        }
        System.out.println("Total income: $" + totalIncome);
        System.out.println();
    }
}
