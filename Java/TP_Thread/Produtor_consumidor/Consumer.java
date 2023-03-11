package TP_Thread.Produtor_consumidor;

public class Consumer extends Thread {
    PilhaSincronizada pilha;

    public Consumer(PilhaSincronizada pilha) {
        this.pilha = pilha;
    }

    public void run() {
        int colorIdx;
        for (int i = 0; i < 20; i++) {
            colorIdx = pilha.pop();
            System.out.println("Usado: " + colorIdx);
            try {
                Thread.sleep((int) (Math.random() * 500));
            } catch (InterruptedException e) {
            }
        }
    }
}
