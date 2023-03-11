public class Trabalhador extends Thread {
    private String produto;
    private int tempo, i;
    private static boolean running = true;
    

    //Atributos
    public Trabalhador(String produto, int tempo) {
        this.produto = produto;
        this.tempo = tempo;
    }

    //metodos
    public void parar(){
        running = false;
    }

    public void run() {
        while (running) {
            i++;
            System.out.println(" Terminei " + produto + " " + i);
            try {
                sleep((long) (Math.random() * tempo)); 
                if (Math.random() > 0.9) {
                    this.parar();
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            
        }
    }
}