import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

//TODO: перенести парсинг команд на клиентскую сторону, стоит прийти к ObjectOutputStream/ObjectInputStream, не зря же сериализация существует
//TODO: Команда import должна использовать файл из файловой системы клиента (содержимое файла передается на сервер), load и save - сервера.
public class Client {
    public static int PORT = 10357;
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
        try {
            this.socket = new Socket(HOST, PORT);
        } catch (IOException e) {
            System.out.println("OMG, A PROBLEM");
            e.printStackTrace();
        }

        try {
            assert socket != null;
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
                        break; // выходим из цикла если пришло "stop"
                    } else {
                        cm.sendCommand(userWord);
                    }
                    out.flush(); // чистим
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
                    str = in.readLine(); // ждем сообщения с сервера
                    if (str.equals("stop")) {
                        ClientConnector.this.stop();
                        break; // выходим из цикла если пришло "stop"
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
                in.close();
                out.close();
            }
        } catch (IOException ignored) {
        } // yes ignore
    }
}




