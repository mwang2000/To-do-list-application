package ui;

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

    //public void setStatus(Entry entry, String status) {
      //  entry.status = status;
    //}

    public int getNumber() {
        return number;
    }

    public String getItem() {
        return item;
    }

    // REQUIRES: the entry is in the todo list
    // EFFECTS: returns the number and item of the entry
    public String todoGetEntry() {

        return number + " " + item + " to do";
    }

    // REQUIRES: the entry is in the crossedOff list
    // EFFECTS: returns the number and item of the entry
    public String crossedOffGetEntry() {

        return number + " " + item + " crossed off";
    }
}