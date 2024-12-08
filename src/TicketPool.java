import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private int maxCapacity;
    private Queue<Ticket> ticketQueue;


    public TicketPool(ConfigSystem config) {
        this.maxCapacity = config.getMaxTicketCapacity();
        this.ticketQueue = new LinkedList<>();
    }

    public synchronized void addTicket(Ticket ticket) {
        while (ticketQueue.size() >= maxCapacity) {
            try{
                wait();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.ticketQueue.add(ticket);
        notifyAll();
        System.out.println("Ticket added by - " + Thread.currentThread().getName() + " - current size is - " + ticketQueue.size());
    }

    public synchronized Ticket buyTicket() {
        while (ticketQueue.isEmpty()) {
            try {
                wait();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Ticket ticket = ticketQueue.poll();
        notifyAll();
        System.out.println("Ticket bought by - " + Thread.currentThread().getName() + " - current size is - " + ticketQueue.size() + " - Ticket is - " + ticket);
        return ticket;

    }
}
