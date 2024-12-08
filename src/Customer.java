public class Customer implements Runnable {
    private int customerRetrievalRate;
    private int quantity;
    private TicketPool ticketPool;

    public Customer(int customerRetrievalRate, int quantity, TicketPool ticketPool) {
        this.customerRetrievalRate = customerRetrievalRate;
        this.quantity = quantity;
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        for (int i = 0; i < quantity; i++) {
            Ticket ticket = ticketPool.buyTicket();
            try {
                Thread.sleep(customerRetrievalRate * 2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
