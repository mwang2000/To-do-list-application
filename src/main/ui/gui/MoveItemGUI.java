package ui.gui;

import model.Item;
import model.TodoList;

import javax.swing.*;
import java.util.Map;

public class MoveItemGUI {
    String removing;

    public MoveItemGUI(TodoList crossedOff, Map<String, Item> map,TodoList todo) {
        removing = JOptionPane.showInputDialog("Enter the keyword of the item to be removed");
        crossedOff.moveItem(removing,map,todo);
    }
}
