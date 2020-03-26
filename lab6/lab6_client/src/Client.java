import java.io.IOException;
import java.net.Socket;
import java.io.*;

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
    private PrintWriter out;
    private String name;

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
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            // smth starting
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
        @Override
        public void run() {
            try {
                System.out.println("Enter your nickname:");
                name = reader.readLine();
                out.write(name + "\n");
            } catch (IOException e) {
                System.out.println("Hey dude nice nick \n(It caused exception)");
            }
            while (true) {
                String userWord;
                try {
                    userWord = reader.readLine(); // сообщения с консоли
                    if (userWord.equals("stop")) {
                        out.write("stop" + "\n");
                        System.out.println("Program stopped");
                        break; // выходим из цикла если пришло "stop"
                    } else {
                        out.write(userWord + "\n"); // отправляем на сервер
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




