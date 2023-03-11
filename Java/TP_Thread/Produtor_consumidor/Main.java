package TP_Thread.Produtor_consumidor;

public class Main {
    
    public static void main(String[] args) {
            
        PilhaSincronizada pilha = new PilhaSincronizada();
        Producer mms = new Producer(pilha);
        Consumer Guilherme = new Consumer(pilha);
        
        mms.start();
        Guilherme.start();
    }
}