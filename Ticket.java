import java.util.List;

public class Ticket {
    private static int counter = 1;

    private final int id;
    private final String creator;
    private String owner;
    private int priority;
    private final String description;
    private final List<String> tags;
    private String status;
    
    /***
      Constructor initializes ticket fields and auto-generates a unique ID.
     */
    public Ticket(String creator, String owner, int priority, String description, List<String> tags) {
        this.id = counter++;
        this.creator = creator;
        this.owner = owner;
        this.priority = priority;
        this.description = description;
        this.tags = tags;
        this.status = "Open";
    }

    //  getters and setters
    public int getId() {
        return id;
    }

    public int getPriority() {
        return priority;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreator() {
        return creator;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    public void setOwner(String owner) {
        this.owner = owner;
    }
    

    public String getOwner() {
        return owner;
    }

    public String getDescription() {
        return description;
    }

    /***
      Returns a comma-separated string of all tags.
     */
    public String getTagsAsString() {
        return String.join(",", tags);
    }

    public List<String> getTags() {
        return tags;
    }
    

    /***
      Returns a formatted string with all ticket details.
     */
    @Override
    public String toString() {
        return "Ticket ID: " + id +
                "\nCreator: " + creator +
                "\nOwner: " + owner +
                "\nPriority: " + priority +
                "\nStatus: " + status + 
                "\nDescription: " + description +
                "\nTags: " + getTagsAsString() +
                "\n----------------------------------";
    }
}
