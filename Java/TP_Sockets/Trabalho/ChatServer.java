import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class ChatServer{
    
    // Atributos
    private int PORT = 12345;
    private static ServerSocket serverSocket;
    private static List<Socket> clientSocket;

    // main
    public static void main(String[] args) {
        ChatServer server = new ChatServer();
        server.inicializa();
        System.out.println("Servidor finalizado");
    }

    // metodos
    public void inicializa() {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Servidor inicializado na porta: " + PORT);
            clientSocket = new ArrayList<>();
            clientConnectionLoop();
        } catch (Exception e) {
            System.out.println("Nao foi possivel inicializar o servidor na porta" + PORT + ": " + e.getMessage());
        }
    }

    private void clientConnectionLoop() {
        while (true) {
            try {
                Socket cliente = serverSocket.accept();
                clientSocket.add(cliente);
                AtendeCliente ac = new AtendeCliente(cliente);
                System.out.println("Cliente de endereco ip " + cliente.getRemoteSocketAddress() + " conectou.");
                ac.start();

            } catch (Exception e) {
                System.out.println("Nao foi possivel inicializar cliente: " + e.getMessage());
            }
        }
    }

    public static void enviaMenssagem(String msg, Socket cliente){
        for (Socket clientes : clientSocket) {
            try {
                if(clientes != cliente) new PrintStream(clientes.getOutputStream()).println(msg);
                else{}
            } catch (Exception e) {
                System.out.println("Erro ao enviar mensagem ao cliente");
            }
        }
    }
}
