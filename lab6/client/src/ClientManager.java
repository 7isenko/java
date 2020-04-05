import lab5.Command;
import lab5.lab3.Human;
import org.json.JSONObject;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.TreeSet;

public class ClientManager {
    private final ObjectOutputStream out;

    public ClientManager(ObjectOutputStream out) {
        this.out = out;
    }

    public void sendCommand(String cmd) {
        Command command;
        String[] buff;
        buff = cmd.split(" ", 2);
        String head = buff[0];
        if (head.equals("import") || head.equalsIgnoreCase("doImport")) {
            if (buff.length == 1) {
                System.out.println("use \"import /any_path/any_file.json\"");
                return;
            } else command = new Command("import", doImport(buff[1]));
            if (command.getBody() != null) send(command);
            return;
        }
        if (buff.length == 1) {
            command = new Command(head);
            send(command);
            return;
        }
        if (buff.length == 2) {
            command = new Command(head, JSONReader.parseHuman(new JSONObject(buff[1])));
            send(command);
            return;
        }
        System.out.println("Please write it correctly");
    }

    private void send(Command command) {
        try {
            out.writeObject(command);
            out.flush();
        } catch (IOException e) {
            // ignore
        }
    }

    private TreeSet<Human> doImport(String path) {
        if (!path.substring(path.lastIndexOf('.') + 1).equals("json")) {
            System.out.println("It is not .json format file");
            return null;
        } else
            try {
                JSONReader reader = new JSONReader();
                reader.setFileAddress(path);
                return reader.getHumanCollection();
            } catch (NullPointerException e) {
                System.out.println("Wrong file address: " + path);
                return null;
            }
    }

}