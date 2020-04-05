import lab5.Command;
import lab5.SHA1Encoder;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {

    public static final int PORT = 24220;
    public static LinkedList<ServerCommand> serverList = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        DBRepository db = new DBRepository();
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started");
            while (true) {
                Socket socket = serverSocket.accept();
                try {
                    serverList.add(new ServerCommand(socket));
                } catch (IOException e) {
                    serverSocket.close();
                }
            }
        }

    }

    //TODO: Операции обработки объектов коллекции должны быть реализованы с помощью Stream API с использованием лямбда-выражений

    //TODO: сделать автопоиск людей по текущему нику.
    public static class ServerCommand extends Thread {
        private Socket socket;
        private ObjectInputStream in;
        private BufferedWriter out;

        public ServerCommand(Socket socket) throws IOException {
            this.socket = socket;
            in = new ObjectInputStream(socket.getInputStream());
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            start(); // вызываем run()
        }

        @Override
        public void run() {
            DBRepository db = new DBRepository();
            try {
                String email;
                send("Print your email:");
                email = ((Command) in.readObject()).getName();

                if (db.login(email, "empty")) {
                    send("You were registered and logged in. Password has been sent to your email");
                } else {
                    send("Print your password:");
                    String password = ((Command) in.readObject()).getName();
                    if (db.login(email, SHA1Encoder.encryptPassword(password))) {
                        send("Successful login");
                    } else {
                        send("Bad password. One more try. Print your password:");
                        password = ((Command) in.readObject()).getName();
                        if (db.login(email, SHA1Encoder.encryptPassword(password))) {
                            send("Successful login");
                        } else {
                            send("Wrong password");
                            send("stop");
                            down();
                        }
                    }
                }
                CommandParser parser = new CommandParser(out, email);
                //TODO: переписать низ под комманды
                while (true) {
                    Command cmd = (Command) in.readObject();
                    if (cmd.getName().equalsIgnoreCase("stop")) {
                        this.down();
                        break;
                    }
                    parser.parse(cmd);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        private void send(String message) {
            try {
                out.write(message + "\n");
                out.flush();
            } catch (IOException e) {
                // ignore
            }
        }

        private void down() {
            try {
                if (!socket.isClosed()) {
                    socket.close();
                    in.close();
                    out.close();
                    for (ServerCommand vr : Server.serverList) {
                        if (vr.equals(this)) vr.interrupt();
                        Server.serverList.remove(this);
                    }
                }
            } catch (IOException ignored) {
            }
        }
    }
}
