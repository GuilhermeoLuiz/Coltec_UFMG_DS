package TP_Thread.Produtor_consumidor;

public class PilhaSincronizada {
    private int index = 0;
    private int[] buffer = new int[10];

    public synchronized int pop () {
        while(index == 0) {
            try {this.wait();
            } catch(InterruptedException e) {}
        }
        this.notify(); // So e feita ao fim do bloco
        index--;
        return buffer[index];
     }

    public synchronized void push(int i) {
        while (index == buffer.length ) {
            try {this.wait();
            } catch ( InterruptedException e) {}
        }
        this.notify () ;
        buffer[index] = i ;
        index++;
    }
}