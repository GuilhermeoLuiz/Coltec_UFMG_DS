import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class BlockingChatClientApp implements Runnable {
   
    public static String SERVER_ADDRESS;
    private Scanner scanner;
    private ClientSocket clientSocket;
    

    public static void main(String[] args) {
        BlockingChatClientApp client = new BlockingChatClientApp();
        try {
            client.start();
        } catch (IOException e) {
            System.out.println("Erro ao conectar ao servidor: " + e.getMessage());
        }
    }

    public BlockingChatClientApp(){
        scanner = new Scanner(System.in);
    }

    private void start() throws IOException {
        System.out.println("Digite o endereco ip:");
        SERVER_ADDRESS = scanner.nextLine();
        Socket socket = new Socket(SERVER_ADDRESS, BlockingChatServerApp.PORT);
        clientSocket = new ClientSocket(socket);
        System.out.println(
            "Cliente conectado ao servidor no endereço " + SERVER_ADDRESS +
            " e porta " + BlockingChatServerApp.PORT);

        login();

        new Thread(this).start();
        messageLoop();
    }

    private void login() {
        System.out.print("Digite seu nome: ");
        String login = scanner.nextLine();
        System.out.println("Digite sair para encerrar sua conexao no chat!!");
        clientSocket.setLogin(login);
        clientSocket.sendMsg(login);
    }

    private void messageLoop() {
        String msg;
        do {
            System.out.print("Digite a msg: ");
            msg = scanner.nextLine();
            clientSocket.sendMsg(msg);
        } while(!"sair".equalsIgnoreCase(msg));
        clientSocket.close();
    }

    @Override
    public void run() {
        String msg;
        while((msg = clientSocket.getMessage())!=null) {
            System.out.println(msg);
        }
    }
}