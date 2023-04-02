import java.net.ServerSocket;
import java.net.Socket;


public class ChatServer{


    // Atributos
    private int PORT = 12345;
    private static ServerSocket serverSocket;
    Socket clientSocket;

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
            clientConnectionLoop();
        } catch (Exception e) {
            System.out.println("Nao foi possivel inicializar o servidor na porta" + PORT + ": " + e.getMessage());
        }
    }

    private void clientConnectionLoop() {
        while (true) {
            try {
                clientSocket = serverSocket.accept();
                AtendeCliente ac = new AtendeCliente(clientSocket);
                //clientSocket = serverSocket.accept();
                ////////AtendeCliente ac = new AtendeCliente(serverSocket.accept());
                System.out.println("Cliente de endereco ip " + clientSocket.getRemoteSocketAddress() + " conectou.");
                ac.start();

            } catch (Exception e) {
                System.out.println("Nao foi possivel inicializar cliente: " + e.getMessage());
            }
        }
    }

    /*private void leMessage() {

        try {
            Scanner entrada = new Scanner(clientSocket.getInputStream());
            while (entrada.hasNextLine()) {
                System.out.println(entrada.nextLine());
            }            
        } catch (Exception e) {
            System.out.println("Nao foi possivel ler mensagens: " + e.getMessage());
        }

    }*/

}