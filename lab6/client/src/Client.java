import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

//TODO: перенести парсинг команд на клиентскую сторону, стоит прийти к ObjectOutputStream/ObjectInputStream, не зря же сериализация существует
public class Client {
    public static int PORT = 24220;
    public static String HOST = "localhost";

    public static void main(String[] args) {
        new ClientConnector(HOST, PORT);
    }
}

class ClientConnector {
    private Socket socket;
    private BufferedReader reader; // Listen to user
    private BufferedReader in; // Listen to server
    private ObjectOutputStream out;

    public ClientConnector(String HOST, int PORT) {
        while (true) {
            try {
                Thread.sleep(1000);
                this.socket = new Socket(HOST, PORT);
                System.out.println("Connected to a server");
                break;
            } catch (IOException | InterruptedException e) {
                System.out.println("Waiting for host");
            }
        }

        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new ObjectOutputStream((socket.getOutputStream()));
            new CommandWriter().start();
            new CommandReader().start();
        } catch (IOException e) {
            ClientConnector.this.stop();
        } catch (NullPointerException e) {
            System.out.println("Socket lost or not found");
            e.printStackTrace();
        }
    }


    class CommandWriter extends Thread {
        ClientManager cm = new ClientManager(out);
        @Override
        public void run() {
            while (true) {
                String userWord;
                try {
                    userWord = reader.readLine(); // сообщения с консоли
                    if (userWord.equals("stop")) {
                        System.out.println("Program stopped");
                        ClientConnector.this.stop();
                        break;
                    } else {
                        cm.sendCommand(userWord);
                    }
                    out.flush();
                } catch (IOException e) {
                    ClientConnector.this.stop();
                }

            }
        }
    }

    class CommandReader extends Thread {
        @Override
        public void run() {
            String str;
            try {
                while (true) {
                    str = in.readLine(); // сообщения с сервера
                    if (str.equals("stop")) {
                        ClientConnector.this.stop();
                        break;
                    }
                    System.out.println(str);
                }
            } catch (IOException e) {
                ClientConnector.this.stop();
            }
        }
    }

    private void stop() {
        try {
            if (!socket.isClosed()) {
                System.out.println("Client closed");
                socket.close();
                reader.close();
                in.close();
                out.close();
            }
        } catch (IOException ignored) {
        } // yes ignore
    }
}




