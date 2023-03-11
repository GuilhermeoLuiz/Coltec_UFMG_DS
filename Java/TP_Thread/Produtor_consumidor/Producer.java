package TP_Thread.Produtor_consumidor;

public class Producer extends Thread {
    PilhaSincronizada pilha;

    public Producer(PilhaSincronizada pilha) {
        this.pilha = pilha;
    }

    public void run() {
        int colorIdx;
        
        for (int i = 0; i < 40; i++) {
            colorIdx = ((int)(Math.random() * 10));
            pilha.push(colorIdx);
            System.out.println("Criado: " + colorIdx);
            try {
                Thread.sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {
            }
        }
    }
}