package name;

// represents an entry in a list
public class Entry {
    private int number;
    private String item;
    private String status;

    // MODIFIES: this
    // EFFECTS: creates an entry with number of 0 and takes a string as a parameter which becomes the item
    public Entry(String item) {
        number = 0;
        this.item = item;
        status = "not done";
    }

    // MODIFIES: this
    // EFFECTS: sets the number of the entry to an int number
    public void setNumber(int number) {
        this.number = number;
    }

    // MODIFIES: this
    // EFFECTS: sets the item of the entry to the given string
    public void setItem(String item) {
        this.item = item;
    }

    // MODIFIES: this
    // EFFECTS: sets the status of the entry to the given string
    public void setStatus(String status) {
        this.status = status;
    }

   // EFFECTS: returns the number of the entry it is called on
    public int getNumber() {
        return number;
    }

   // EFFECTS: returns the item of the entry it is called on
    public String getItem() {
        return item;
    }

    // EFFECTS: returns the status of the entry it is called on
    public String getStatus() {
        return status;
    }

    // REQUIRES: the entry is in the todo list
    // EFFECTS: returns the number and item of the entry
    public String todoGetEntry() {
        return number + " " + item + " " + status + " (to do)";
    }

    // REQUIRES: the entry is in the crossedOff list
    // EFFECTS: returns the number and item of the entry
    public String crossedOffGetEntry() {
        return number + " " + item + " " + status + " (crossed off)";
    }
}