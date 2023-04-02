import java.util.Scanner;
import java.net.Socket;
import java.io.*;

public class ChatClient{
    //Atributos
    private Scanner scanner = new Scanner(System.in);;
    private Socket clientSocket;
    //private static String SERVER_ADDRESS = "127.0.0.1";
    private static int PORT = 12345;

    //main
    public static void main(String[] args) {
        ChatClient client = new ChatClient();
        client.start();
        System.out.println("Cliente finalizado.");
    }

    //metodos
    public void start(){
        
        System.out.println("Digite o endereco ip: ");
            String SERVER_ADDRESS = scanner.nextLine();

        try {
            clientSocket = new Socket(SERVER_ADDRESS, PORT);
            System.out.println("Cliente conectado ao servidor em " + SERVER_ADDRESS + " na porta " + PORT);
            messageLoop();
        } catch (Exception e) {
            System.out.println("Nao foi possivel incializar cliente de ip " + SERVER_ADDRESS + ": " + e.getMessage() );
        }
    }
    
    private void messageLoop(){
        System.out.println("Digite seu nome: ");
        String login = scanner.nextLine();
        String msg;

        System.out.println("Chat aberto! ('chatfim') finaliza chat");
        try {
            PrintStream saida = new PrintStream(clientSocket.getOutputStream());
            saida.println(login + " entrou no chat");    
            do {
                System.out.println("Digite uma mensagem: ");
                msg = scanner.nextLine();
                saida.println(login + " diz -> " + msg);
            } while (!msg.equalsIgnoreCase("chatfim"));
            saida.println(login + " saiu do chat");
            saida.close();
        } catch (Exception e) {
            System.out.println("Erro ao gerar chat: " + e.getMessage());
        }
    }
}