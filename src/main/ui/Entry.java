package ui;

public class Entry {
    private int number;
    private String title;

    public Entry(String title) {
        number = 0;
        this.title = title;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String todoGetItem() {
        return number + " " + title + " to do";
    }

    public String crossedOffGetItem() {
        return number + " " + title + " crossed off";
    }
}