import java.util.Scanner;
import java.net.Socket;


public class AtendeCliente extends Thread {
    
    Socket client;

    AtendeCliente(Socket serverSocket){
        this.client = serverSocket;

    }

    @Override
    public void run() {
            try {
                Scanner entrada = new Scanner(this.client.getInputStream());
                while (entrada.hasNextLine()) {
                    System.out.println(entrada.nextLine());
                }            
            } catch (Exception e) {
                System.out.println("Nao foi possivel ler mensagens: " + e.getMessage());
            }
        super.run();
    }
}
