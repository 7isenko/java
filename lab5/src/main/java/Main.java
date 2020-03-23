import lab3.Item;
import lab3.Skill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        CollectionManager collection = new CollectionManager();
        collection.setOnLoad();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String command = "";
        String[] buff;
        showMenu();
        try {
            while (!command.equalsIgnoreCase("stop") && !command.equalsIgnoreCase("exit") && !command.equalsIgnoreCase("close")) {
                buff = in.readLine().split(" ", 2);
                command = buff[0];
                if (buff.length == 1) buff = new String[]{buff[0], "{}"};
                if (command.equals("load")) {
                    collection.load();
                } else if (command.equals("save")) {
                    collection.save();
                } else if (command.equals("info")) {
                    collection.info();
                } else if (command.equals("add")) {
                    collection.add(buff[1]);
                } else if (command.equals("import") || command.equalsIgnoreCase("doImport")) {
                    collection.doImport(buff[1]);
                } else if (command.equals("remove")) {
                    collection.remove(buff[1]);
                } else if (command.equals("show")) {
                    collection.show();
                } else if (command.equals("clear")) {
                    collection.clear();
                }
                else if (command.equalsIgnoreCase("stop") || command.equalsIgnoreCase("exit") || command.equalsIgnoreCase("close")) {
                    System.out.println("Program stopped");
                } else System.out.println("I don't know a command \"" + command + "\"");
            }
        } catch (IOException e) {
            System.out.println("There was a problem caused by your input");
        }
        collection.save();
    }

    public static void showMenu() {
        System.out.println("Enter show, load, info, save, add {element}, import {path}, remove {element}, clear or \"stop\" to exit \nelement - json, example: {\"name\":\"Bill\",\"age\":\"42\",\"skills\":[\"fun\"],\"carry\":[\"stick\"]} \npath - string, example: ./collections/students.json");
        showAbleEnums();
        System.out.println("Name and age are required");
    }

    public static void showAbleEnums(){
        System.out.println("List of able skills: " + Arrays.toString(Skill.values()));
        System.out.println("List of able items: " + Arrays.toString(Item.values()));
    }
}