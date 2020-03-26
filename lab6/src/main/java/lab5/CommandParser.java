package lab5;

import lab5.lab3.Item;
import lab5.lab3.Place;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Arrays;

public class CommandParser {
    private static CollectionManager collection = new CollectionManager();
    private BufferedWriter out;

    public CommandParser(BufferedWriter out, String name) {
        this.out = out;
        collection.setName(name);
        showMenu();
        collection.setOnLoad();
    }

    public void parse(String cmd) {
        String command;
        String answer;
        String[] buff;
        buff = cmd.split(" ", 2);
        command = buff[0];
        if (buff.length == 1) buff = new String[]{buff[0], "{}"};
        if (command.equals("load")) {
            answer = collection.load();
        } else if (command.equals("save")) {
            answer = collection.save();
        } else if (command.equals("info")) {
            answer = collection.info();
        } else if (command.equals("add")) {
            answer = collection.add(buff[1]);
        } else if (command.equals("import") || command.equalsIgnoreCase("doImport")) {
            answer = collection.doImport(buff[1]);
        } else if (command.equals("remove")) {
            answer = collection.remove(buff[1]);
        } else if (command.equals("show")) {
            answer = collection.show();
        } else if (command.equals("clear")) {
            answer = collection.clear();
        } else answer = "I don't know a command \"" + command + "\"";
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