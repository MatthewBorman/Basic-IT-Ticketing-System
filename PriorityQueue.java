import java.util.ArrayList;
import java.util.List;

public class PriorityQueue {
    private final List<Ticket> tickets = new ArrayList<>();

     /***
      Inserts a ticket into the queue while maintaining sorted order based on priority.
     */
    public void insert(Ticket ticket) {
        int i = 0;
        while (i < tickets.size() && ticket.getPriority() >= tickets.get(i).getPriority()) {
            i++;
        }
        tickets.add(i, ticket);
    }
        /***
      Removes and returns the ticket with the highest priority (front of the list).
     */
    public Ticket remove() {
        if (!isEmpty()) {
            return tickets.remove(0);
        }
        return null;
    }
    /***
          Checks if the priority queue is empty.
         */
    public boolean isEmpty() {
        return tickets.isEmpty();
    }
    /***
      Returns a shallow copy of all tickets in the queue.
     */
    public List<Ticket> getAllTickets() {
        return new ArrayList<>(tickets);
    }
    /***
      Finds and returns a ticket by its unique ID.
     */
    public Ticket findById(int id) {
        for (Ticket ticket : tickets) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }
        return null;
    }
    
}

