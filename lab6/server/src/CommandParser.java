import lab5.Command;
import lab5.lab3.Human;
import lab5.lab3.Item;
import lab5.lab3.Place;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Arrays;

public class CommandParser {
    private CollectionManager collection;
    private BufferedWriter out;
    private String email;
    public CommandParser(BufferedWriter out, String email) {
        this.out = out;
        this.email = email;
        collection = new CollectionManager(email);
        showMenu();
    }

    public void parse(Command cmd) {
        String command = cmd.getName();
        Human body = (Human) cmd.getBody();
        String answer;
        switch (command) {
            case "load":
                answer = collection.load();
                break;
            case "save":
                answer = collection.save();
                break;
            case "info":
                answer = collection.info();
                break;
            case "add":
                answer = collection.add(body);
                break;
            case "remove":
                answer = collection.remove(body);
                break;
            case "show":
                answer = collection.show();
                break;
            case "clear":
                answer = collection.clear();
                break;
            default:
                answer = "I don't know a command \"" + command + "\"";
                break;
        }
        send(answer);
    }

    private void send(String message) {
        try {
            out.write(message + "\n");
            out.flush();
        } catch (IOException e) {
            // ignore
        }
    }

    public void showMenu() {
        send("Enter show, load, info, save, add {element}, import {path}, remove {element}, clear or \"stop\" to exit \nelement - json, example: {\"name\":\"Bill\",\"age\":\"42\",\"skills\":[\"fun\"],\"carry\":[\"stick\"]} \npath - string, example: ./collections/students.json");
        send("List of able places: " + Arrays.toString(Place.values()));
        send("List of able items: " + Arrays.toString(Item.values()));
        send("Name and age are required");
    }

}