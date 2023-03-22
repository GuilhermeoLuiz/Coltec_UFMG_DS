package TP_Sockets.Trabalho;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.PrintStream;
 import java.io.PrintWriter;
 import java.net.ServerSocket;
 import java.net.Socket;
 import java.util.Enumeration;
 import java.util.Vector;
 
 /**
  *
  * @author vvalt
  */
 public class Servidor extends Thread {
 
     /**
      * @param args the command line arguments
      */
     public static void main(String[] args) {
         clientes = new Vector();
         try {
             //criando o socket que escutara na porta 12345
             ServerSocket socket = new ServerSocket(12345);
 
             //loop principal
             while (true) {
                 //fica aguardando ate alguem se conectar no servidor
                 System.out.println("Chat aberto...");
                 Socket conexao = socket.accept();
                 System.out.println("Connectou!");
 
                 //cria um thread para receber essa conexão
                 Thread t = new Servidor (conexao);
                 t.start();
                 //volta no loop esperando mais alguem se conectar
             }
 
         } catch (IOException e) {
             //caso ocorro algum problema de I/O
             System.out.println("IOException: " + e);
         }
     }
 
     //parte que controla a conexão por meio de threads
     private static Vector clientes;
     //socket do cliente
     private Socket conexao;
     //nome do cliente
     private String meuNome;
 
 
     //contrutor que recebe o socket deste cliente
     public Servidor(Socket s) {
         conexao = s;
     }
 
     //execução da thread
     public void run() {
         try {
             //objetos que permitem controlar o fluxo de comunicação
             BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
             PrintStream saida = new PrintStream(conexao.getOutputStream());
 
             //primeiramente, espera-se pelo nome do cliente
             meuNome = entrada.readLine();
             //agora verifica se a string e valida
             if (meuNome == null) {
                 return;
             }
             //Quando o usuario e valido, adiciona ele ao vetor de clientes
             clientes.add(saida);
             //não a importancia de o cliente ser compartilhado entre varias threads
 
             //loop principal que espera as strings do cliente
             //ele e quebrado quando o cliente envia linha em branco(interrompida)
             String linha = entrada.readLine();
             while ((linha != null) && (!(linha.trim().equals("")))) {
                 //reenvia a linha para todos os clientes conectados
                 sendToAll(saida, " disse: ", linha);
 
                 //espera por uma nova linha
                 linha = entrada.readLine();
             }
             //quando o usuário envia linha em branco, fecha sua conexão
             sendToAll(saida, " saiu ", "do chat!");
             clientes.remove(saida);
             conexao.close();
         } catch (IOException e) {
             //caso ocorra alguma exeção de I/O
             System.out.println("IOException: " + e);
         }
     }
 
     //enviar uma mensagem para todos, menos para o próprio
     public void sendToAll(PrintStream saida, String acao, String linha) throws IOException {
         //define o caminho do arquivo que sera utilizado para leitura ou escrita
         File f = new File("C:\\Users\\nicolle\\msg.txt");
 
         //Cria uma instancia do arquivo para escrita
         FileWriter fw = new FileWriter(f, true);    //true pra não substituir tudo
 
         //Cria a possibilidade de escrever no arquivo
         PrintWriter pw = new PrintWriter(fw);
 
         Enumeration e = clientes.elements();
         while (e.hasMoreElements()) {
             //obtém o fluxo de saida de um dos clientes
             PrintStream chat = (PrintStream) e.nextElement();
             //envia para todos, menos para o próprio usuário
             if (chat != saida) {
                 //realmente escreve no arquivo
                 pw.println(meuNome + acao + linha);
 
                 //fecha a conexão do arquivo
                 fw.close();
                 chat.println(meuNome + acao + linha);
             }
         }
     }
 }