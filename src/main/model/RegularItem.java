package model;

// represents a regular item in a list
public class RegularItem extends Item {
    public RegularItem() {
        super();
    }

    public RegularItem(String task, int y, int m, int d) {
        super(task,y,m,d);
    }

    // EFFECTS: returns a regular item to be printed
//    public String printRegularItem(String print) {
//        if (print.equals("")) {
//            print = print + todoGetItem();
//        } else {
//            print = print + "\n" + todoGetItem();
//        }
//        return print;
//    }

    // EFFECTS: returns given string with nothing added to the end
//    public String printRegularItem(int number) {
//        return printItemHelper(number,"");
//    }
}
