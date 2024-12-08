import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ConfigSystem {

    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    public ConfigSystem() {
    }

    public ConfigSystem(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    @Override
    public String toString() {
        return "configSystem{" +
                "totalTickets=" + totalTickets +
                ", ticketReleaseRate=" + ticketReleaseRate +
                ", customerRetrievalRate=" + customerRetrievalRate +
                ", maxTicketCapacity=" + maxTicketCapacity +
                '}';
    }

    public void ticketConfigSetup() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Total number of tickets");
        setTotalTickets(scanner.nextInt());

        System.out.print("Enter ticket release rate");
        setTicketReleaseRate(scanner.nextInt());

        System.out.print("Enter customer retrieval rate");
        setCustomerRetrievalRate(scanner.nextInt());

        System.out.print("Enter max ticket capacity");
        setMaxTicketCapacity(scanner.nextInt());

        ConfigSystem writeConfig = new ConfigSystem(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter fileWriter = new FileWriter("ticketSystemConfig.json")) {
            gson.toJson(writeConfig, fileWriter);
            System.out.println("Ticket System config successfully saved to");
        }
        catch (IOException e){
            System.out.println("Error while saving ticket system config" + e.getMessage());
        }
    }



    public void readFile() {
        Gson gson = new Gson();
        ConfigSystem fileData;
        try (FileReader fileReader = new FileReader("ticketSystemConfig.json")) {
             fileData = gson.fromJson(fileReader, ConfigSystem.class);

             if (fileData != null) {
                 totalTickets = fileData.getTotalTickets();
                 ticketReleaseRate = fileData.getTicketReleaseRate();
                 customerRetrievalRate = fileData.getCustomerRetrievalRate();
                 maxTicketCapacity = fileData.getMaxTicketCapacity();
                 System.out.println("Successfully read config file");
             }
             else {
                 System.out.println("Error while reading ticket system config");
             }

        }
        catch (IOException e){
            System.out.println("Error while reading ticket system config" + e.getMessage());
        }

    }


}
