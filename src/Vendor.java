public class Vendor implements Runnable {
    private int totalTickets;
    private int ticketReleaseRate;
    private TicketPool ticketPool;
    ConfigSystem obj = new ConfigSystem();

    public Vendor(int totalTickets, int ticketReleaseRate, TicketPool ticketPool) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        for (int i = 0; i < totalTickets; i++) {
            Ticket ticket = new Ticket(i, "test1", "10/12/2024", "10.30pm","colombo", 2000 );
            ticketPool.addTicket(ticket);
            try{
                Thread.sleep(ticketReleaseRate*2000);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " has finished adding tickets.");
    }
}
