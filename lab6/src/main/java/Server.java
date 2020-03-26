import lab5.CommandParser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {

    public static final int PORT = 10357;
    public static LinkedList<ServerCommand> serverList = new LinkedList<>();

    public static void main(String[] args) throws IOException {
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

    //TODO: запретить одинаковые ники
    //TODO: Операции обработки объектов коллекции должны быть реализованы с помощью Stream API с использованием лямбда-выражений

    public static class ServerCommand extends Thread {
        private Socket socket;
        private BufferedReader in;
        private BufferedWriter out;

        public ServerCommand(Socket socket) throws IOException {
            this.socket = socket;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            // чота написать?
            start(); // вызываем run()
        }

        @Override
        public void run() {
            String command;
            try {
                String name = in.readLine();
                CommandParser parser = new CommandParser(out, name);
                try {
                    while (true) {
                        command = in.readLine();
                        if (command.equalsIgnoreCase("stop")) {
                            this.down();
                            break;
                        }
                        System.out.println("User said " + command);
                        parser.parse(command);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                send("Naming problems");
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
