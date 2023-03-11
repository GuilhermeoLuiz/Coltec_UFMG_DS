package TP_Thread.Corrida_dos_Sapos;

public class Sapo extends Thread {

    //Atributos objeto sapo
    private String nome;
    private int distanciaPercorrida = 0, distanciaTotal, distanciaPulo;
    private static boolean fim = false;


    //setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDistanciaPercorrida(int distanciaPercorrida) {
        this.distanciaPercorrida = distanciaPercorrida;
    }

    public void setDistanciaPulo(int distanciaPulo) {
        this.distanciaPulo = distanciaPulo;
    }

    public void setDistanciaTotal(int distanciaTotal) {
        this.distanciaTotal = distanciaTotal;
    }

    //funcao corrida dos sapos
    @Override
    public void run() {
        while ((this.distanciaPercorrida < this.distanciaTotal) && (!fim)) {
            this.distanciaPercorrida += this.distanciaPulo;
            System.out.println("O sapo " + this.nome + " caminhou " + this.distanciaPercorrida + " metros.");

            if (this.distanciaPercorrida >= distanciaTotal) {
                fim = true;
                System.out.println("Fim da corrida!");
                System.out.println("O sapo " + this.nome + " venceu!");
            }
        }

    }
}