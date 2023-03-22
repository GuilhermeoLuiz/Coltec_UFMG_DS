package TP_Sockets.Trabalho;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.PrintStream;
 import java.net.Socket;
 
 /**
  *
  * @author nicolle 
  */
 public class Cliente extends Thread{
 
     //Flag que indica quando se deve terminar a execução.
     public  static boolean done = false;
     public static void main(String[] args) {
         try{
             //Cria um socket, com o ip do servidor e porta
             Socket conexao = new Socket("127.0.0.1", 12345);
 
             //apos conectar, deve pegar os objetos que controlam o fluxo de comunicação
             PrintStream saida = new PrintStream(conexao.getOutputStream());
             //enviar o nome do usuário
             BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
             System.out.println("Entre com o seu apelido: ");
             String meuNome = teclado.readLine();
             saida.println(meuNome);
 
             //iniciar a tread de recepção de mensagens
             Thread t = new Cliente (conexao);
             t.start();
 
             //captura o que foi digitado e envia pro servidor
             String linha;
             while(true){
                 //captura o que foi digitado
                 System.out.println("> ");
                 linha = teclado.readLine();
                 //antes de enviar, verifica se a conexao n foi fechada
                 if(done){
                     break;
                 }
                 saida.println(linha);
             }
         }catch(IOException e){
             //caso ocorra exceção de I/O
             System.out.println("Exception do main");
             System.out.println("IOException: " + e);
         }
     }
     //recepção de mensagens do cliente
     private Socket conexao;
     //construtor que recebe o socket deste cliente
     public Cliente(Socket s){
         conexao = s;
     }
 
     //execução da thread
     public void run(){
         try{
             BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
             String linha;
             while(true){
                 //pega o que o servidor enviou
                 linha = entrada.readLine();
 
                 //verifica se a linha e valida
                 if(linha == null){
                     System.out.println("Conexão encerrada!");
                     break;
                 }
                 //caso a linha seja valida, imprimi-la
                 System.out.println();
                 System.out.println(linha);
                 System.out.println("...> ");
             }
         }catch(IOException e){
             //caso ocorra alguma exceção
             System.out.println("Exception do run");
             System.out.println("IOException> " + e);
         }
         //sinaliza para o main que a conexão encerrou
         done = true;
     }
 }