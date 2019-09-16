package ui;

public class ListEntry {
    private static int number;
    private static String item;
    private static boolean status;

    public ListEntry() {
        number = 0;
        item = "";
        status = false;
    }

    public static void increaseNumber() {
        number = number + 1;
    }

    public static void setItem(String newItem) {

        item = newItem;
    }

    public static void setStatus(Boolean done) {
        status = done;
    }

    public String toString() {
        return number + " " + item + " " + status;
    }
}
