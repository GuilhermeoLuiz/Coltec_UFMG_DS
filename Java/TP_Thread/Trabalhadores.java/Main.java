public class Main{
    public static void main(String[] args) {

        //cria objetos trabalhadores
        Trabalhador severino = new Trabalhador (" sapato ", 100) ;
        Trabalhador raimundo = new Trabalhador (" bota ", 500) ;
        
        //inicia corrida
        severino.start () ;
        raimundo.start () ;
    }
}