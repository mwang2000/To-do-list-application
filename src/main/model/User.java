package model;

import network.Network;
import observer.Observer;
import org.json.JSONObject;

public class User implements Observer {
    public TodoList todo;

    public void addTodo(TodoList todoList) {
        if (todo != todoList) {
            this.todo = todoList;
            todoList.addUser(this);
        }
    }

    @Override
    public void update() {
        Network.printWebPage();
    }

    public static String parseString(String sb) {
        JSONObject obj = new JSONObject(sb);
        String temperature = "temperature " + obj.getJSONObject("main").getInt("temp") + "K";
        String humidity = "humidity " + obj.getJSONObject("main").getInt("humidity") + "%";
        String minTemp = "low of " + obj.getJSONObject("main").getInt("temp_min") + "K";
        String maxTemp = "high of " + obj.getJSONObject("main").getInt("temp_max") + "K";
        return temperature + "\n" + humidity + "\n" + minTemp + "\n" + maxTemp;
    }
}
