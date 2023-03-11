package TP_Thread.Corrida_dos_Sapos;

import java.util.LinkedList;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        
        Scanner ss = new Scanner(System.in);
        int nSapos;

        LinkedList<Sapo> sapos = new LinkedList<Sapo>();

        System.out.println("Seja bem vindo a corrida de sapos");
        System.out.println("Digite a quantidade de sapos na corrida:");
        nSapos = ss.nextInt();

        for(int i = 0; i < nSapos; i++){
            Sapo s = new Sapo();
            System.out.println("Digite o nome do sapo " + (i+1) + ": ");
            s.setNome(ss.next());
            System.out.println("Qual a distancia esse sapo deve correr: ");
            s.setDistanciaTotal(ss.nextInt());
            System.out.println("Qual o tamanho do pulo desse sapo: ");
            s.setDistanciaPulo(ss.nextInt());
            sapos.add(s);
        }

        for(Sapo s: sapos)
        {
            s.start();
        }

        ss.close();
    }
}