import com.google.gson.annotations.SerializedName;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ConfigSystem obj = new ConfigSystem();
        
        while (true) {
            System.out.println("1.Configuration");
            System.out.println("2.Simulation");
            System.out.println("3.Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    obj.ticketConfigSetup();
                    break;

                case 2:
                    System.out.println("simulation running..");
                    obj.readFile();
                    TicketPool ticketPool = new TicketPool(obj);

                    Vendor[] vendors = new Vendor[10];
                    Thread[] vendorThreads = new Thread[vendors.length];
                    for (int i = 0; i < vendors.length; i++) {
                        vendors[i] = new Vendor(obj.getTotalTickets(), obj.getTicketReleaseRate(), ticketPool);
                        vendorThreads[i] = new Thread(vendors[i], "Vendor ID-" + i);
                        vendorThreads[i].start();
                    }

                    Customer[] customers = new Customer[10];
                    Thread[] customerThreads = new Thread[customers.length];
                    for (int i = 0; i < customers.length; i++) {
                        customers[i] = new Customer(obj.getCustomerRetrievalRate(), obj.getTotalTickets(), ticketPool);
                        customerThreads[i] = new Thread(customers[i], "Customer ID-" + i);
                        customerThreads[i].start();
                    }
                    for (Thread vendorThread : vendorThreads) {
                        try {
                            vendorThread.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    // Wait for customer threads to finish
                    for (Thread customerThread : customerThreads) {
                        try {
                            customerThread.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("simulation completed.");
                    break;

                case 3:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. please try again.");
                    break;
            }

        }


    }
}