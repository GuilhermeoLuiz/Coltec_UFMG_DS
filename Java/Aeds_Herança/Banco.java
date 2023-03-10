class Conta
{
    //Atributos
    protected double saldo;

    //getters e setters
    public double getSaldo() {
        return saldo;
    }

    //Metodos
    public void atualiza(double taxa)
    {
        this.saldo += this.saldo * taxa;
    }

    public void deposita(double valor)
    {
        this.saldo += valor;
    }
    public void saldo()
    {
        System.out.println(this.saldo);
    }
}

class ContaCorrente extends Conta
{
    
    //Metodos
    public void atualiza(double taxa)
    {
        this.saldo += this.saldo * taxa * 2;
    }

    public void deposita(double valor)
    {
        this.saldo += valor - 0.10;
    }
}

class ContaPoupanca extends Conta
{
    //Metodos
    public void atualiza(double taxa)
    {
        this.saldo += this.saldo * taxa * 3;
    }
}

class AtualizadorDeContas
{
    //Atributos
    private double saldoTotal = 0;
    private double selic;

    //getters e setters
    public double getSaldoTotal() {
        return saldoTotal;
    }

    //Metodos
    public void AtualizaTaxa(double selic)
    {
        this.selic = selic;
    }

    public void roda(Conta c)
    {
        saldoTotal = c.getSaldo();
        System.out.println(saldoTotal);
        c.atualiza(this.selic);
        System.out.println(c.getSaldo());
    }

}

class Banco
{
    //Atributos
    private Conta[] acontas;
	private static int nContas;


    //metodos 
    public void adicionaConta(Conta c)
    {
        this.acontas[Banco.nContas] = c;
		Banco.nContas++;
    }
    public Conta pegaConta(int n)
    {
        return acontas[n];
    }
    public int totalContas()
    {
        return Banco.nContas;
    }

}

class TestaConta
{
    public static void main(String[] args) 
    {
        Conta[] lista = new Conta[10];
        Banco bank = new Banco();
        AtualizadorDeContas update = new AtualizadorDeContas();
        update.AtualizaTaxa(0.01);


        for(int i = 0; i < 10; i++)
        {
            if((i % 2) == 0)
            {
                lista[i] = new ContaPoupanca();

            }
            else 
            {
                lista[i] = new ContaCorrente();
            }
        }

        for(int i = 0; i < 10; i++)
        {
            bank.adicionaConta(lista[i]);
        }

        for(int i = 0; i < 10; i++)
        {
            update.roda(lista[i]);
        }

        

    }
}
