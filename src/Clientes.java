
import java.io.*;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Clientes {

    private static ArrayList<Cliente> Clientes = new ArrayList<>();

    public Clientes(){
    }

    private Cliente GetCliente(int Indice) {
        for (Cliente cliente : Clientes) {
            if (cliente.getIndice() == Indice) {
                return cliente;
            }
        }
        return null;
    }

    public void MostrarClientes(){
        System.out.println("----------Lista de Clientes---------");
        //System.out.println("Lista com " + this.Clientes.size() + " clientes.\n");

        for (Cliente cliente : this.Clientes){
            if (cliente.getEstadoCliente() == true){
            System.out.println("Numero Cliente......" + cliente.getIndice());
            System.out.println("Nome ..............." + cliente.getNome());
            System.out.println("Telefone............" + cliente.getTelefone());
            System.out.println("Credito Maximo......" + cliente.getCredMax()+"\n");
            }
        }
    }

    public void AdicionarCliente(String Nome, int Telefone, double CredMax){
        Cliente cliente = new Cliente();

        cliente.setIndice(Clientes.size() +1);
        cliente.setEstadoCliente(true);
        cliente.setNome(Nome);
        cliente.setTelefone(Telefone);
        cliente.setCredMax(CredMax);

        //String FicheiroMovimentosCliente = Integer.toString(cliente.getIndice()) + ".csv";
        //cliente.setFicheiroMovimentos(FicheiroMovimentosCliente);

        this.Clientes.add(cliente);

        CriarFicheiroMovimentos();

        //if (EscreveFicheiroClientes() == false){
            //System.out.println("Erro na adiçao de movimentos ao ficheiro do cliente "+cliente.getIndice());
        //}
    }

    public void RemoverCliente(int Indice) {
        Cliente cliente = GetCliente(Indice);

        if (cliente == null){
            System.out.println("Cliente "+ Indice + " nao existe.");
            return;
        }
        cliente.setEstadoCliente(false);

        if (EscreveFicheiroClientes() == false){
            System.out.println("Erro na eliminaçao do cliente " + Indice);
        }
        else{
            System.out.println("Cliente "+Indice+ " eliminado.");
        }
    }

    public void MudaNome(int Indice, String Nome){

        Cliente cliente = GetCliente(Indice);

        if(cliente == null){
            System.out.println("Cliente inexistente");
            return;
        }

        else if (cliente.getEstadoCliente() == false) {
            System.out.println("Cliente inativo");
            return;
        }

        cliente.setNome(Nome);

        if (EscreveFicheiroClientes() == false){
            System.out.println("Erro na alteraçao do Nome do cliente.");
        }

        else {
            System.out.println("Cliente atualizado com sucesso.");
        }
    }

    public void MudaTelefone(int Indice, int Telefone){

        Cliente cliente = GetCliente(Indice);

        if(cliente == null){
            System.out.println("Cliente inexistente");
            return;
        }

        else if (cliente.getEstadoCliente() == false) {
            System.out.println("Cliente inativo");
            return;
        }

        cliente.setTelefone(Telefone);

        if (EscreveFicheiroClientes() == false){
            System.out.println("Erro na alteraçao do Telefone do cliente.");
        }

        else {
            System.out.println("Cliente atualizado com sucesso.");
        }

    }

    public void MudaCredMax(int Indice, double CredMax){

        Cliente cliente = GetCliente(Indice);

        if(cliente == null){
            System.out.println("Cliente inexistente");
            return;
        }

        else if (cliente.getEstadoCliente() == false) {
            System.out.println("Cliente inativo");
            return;
        }

        cliente.setCredMax(CredMax);

        if (EscreveFicheiroClientes() == false){
            System.out.println("Erro na alteraçao do Credito do cliente.");
        }

        else {
            System.out.println("Cliente atualizado com sucesso.");
        }

    }

    public void ConsultarMovimentos(int Indice){
        Cliente cliente = GetCliente(Indice);

        System.out.println("Credito Maximo: " + cliente.getCredMax());
        System.out.println("Credito Disponivel: " + (cliente.getCredMax() )); //credmax - soma dos movimentos
        System.out.println("Movimentos: \n");
        LerFicheiroCliente();
    }

    // funcao que faz a leitura do ficheiro clientes.csv
    public boolean LerFile() {
        String Line , IndiceStr, Nome, EstadoClienteStr ,TelefoneStr ,CredMaxStr;

        try {
            File file = new File("clientes.csv.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((Line = bufferedReader.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(Line, ";");
                IndiceStr = st.nextToken();
                EstadoClienteStr = st.nextToken();
                Nome = st.nextToken();
                TelefoneStr = st.nextToken();
                CredMaxStr = st.nextToken();

                try {
                    int Indice = Integer.parseInt(IndiceStr);
                    boolean EstadoCliente = Boolean.parseBoolean(EstadoClienteStr);
                    int Telefone = Integer.parseInt(TelefoneStr);
                    double CredMax = Double.parseDouble(CredMaxStr);


                    Cliente cliente = new Cliente();

                    cliente.setIndice(Clientes.size() +1);
                    cliente.setEstadoCliente(EstadoCliente);
                    cliente.setNome(Nome);
                    cliente.setTelefone(Telefone);
                    cliente.setCredMax(CredMax);

                    this.Clientes.add(cliente);

                }
                catch (NumberFormatException e) {
                    System.out.println("Indice, telefone ou credito com formato errado no ficheiro");
                }
            }

            bufferedReader.close();
            fileReader.close();
        }
        catch(IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public void LerFicheiroCliente() {
        Cliente cliente = new Cliente();
        String Line , movimentosStr; //IndiceStr, CredMaxStr, CredDispStr;

        try {
            File file = new File(Integer.toString(cliente.setIndice(Clientes.size())) + ".csv");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((Line = bufferedReader.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(Line);
                movimentosStr = st.nextToken();
                //IndiceStr = st.nextToken();
                //CredMaxStr = st.nextToken();
                //CredDispStr = st.nextToken();

                try {
                    double movimentos = Double.parseDouble(movimentosStr);
                    //int Indice = Integer.parseInt(IndiceStr);
                    //double CredMax = Double.parseDouble(CredMaxStr);
                    //double CredDisp = Double.parseDouble(CredDispStr);

                    //Cliente cliente = new Cliente();

                    //cliente.setCredMax(CredMax);
                    //cliente.setCredDisp(CredDisp);

                    //this.Clientes.add(cliente);

                }
                catch (NumberFormatException e) {
                    System.out.println("Movimento com formato errado.");
                }
            }

            bufferedReader.close();
            fileReader.close();
        }
        catch(IOException e) {
            e.printStackTrace();

        }

    }

    //funcao que escreve no ficheiro clientes.csv
    public boolean EscreverFile() {

        try {
            File file = new File("clientes.csv.txt");

            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);
            // conteudo que quero escrever no ficheiro
            //pra cada cliente, escrever indice,nome,estado,tlm, credito
            this.Clientes.forEach(cliente -> {
                printWriter.println(cliente.getIndice() + ";" + cliente.getEstadoCliente() + ";" + cliente.getNome() + ";" + cliente.getTelefone() + ";" + cliente.getCredMax());
            });

            printWriter.close();
            bufferedWriter.close();
            fileWriter.close();
        }

        catch (IOException e) {
            e.printStackTrace();

            return false;
        }
        return true;
    }

    //formatar esta funcao
   public boolean EscreveFicheiroClientes(){
        try {
            Cliente cliente = new Cliente();
            File file = new File(Integer.toString(cliente.setIndice(Clientes.size())) + ".csv");

            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            // conteudo que quero escrever no ficheiro



            printWriter.close();
            bufferedWriter.close();
            fileWriter.close();
        }

        catch (IOException e) {
            e.printStackTrace();

            return false;
        }
        return true;
    }

    //escreve so na primeira linha do ficheiro
    public boolean AdicionarCompra(int Indice){
        Cliente cliente = GetCliente(Indice);

        Scanner input = new Scanner(System.in);

        try {
            Cliente clientes = new Cliente();

            File file = new File(Integer.toString(clientes.setIndice(Clientes.size())) + ".csv");

            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            // conteudo que quero escrever no ficheiro
            System.out.println("Qual o valor da compra? ");
            double compra =  input.nextDouble();

            printWriter.println("-" + compra);

            printWriter.close();
            bufferedWriter.close();
                fileWriter.close();
        }

        catch (IOException e) {
        e.printStackTrace();

        return false;
    }
        return true;
    }

    //escreve so na primeira linha do ficheiro
    public boolean AdicionarPagamento(int Indice){
        Cliente cliente = GetCliente(Indice);

        Scanner input = new Scanner(System.in);

        try {
            Cliente clientes = new Cliente();

            File file = new File(Integer.toString(clientes.setIndice(Clientes.size())) + ".csv");

            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            // conteudo que quero escrever no ficheiro
            System.out.println("Qual o valor do pagamento? ");
            double pagamento =  input.nextDouble();

            printWriter.println(pagamento);

            printWriter.close();
            bufferedWriter.close();
            fileWriter.close();
        }

        catch (IOException e) {
            e.printStackTrace();

            return false;
        }
        return true;
    }

    public boolean CriarFicheiroMovimentos(){
        try{
            Cliente cliente = new Cliente();
            File file = new File(Integer.toString(cliente.setIndice(Clientes.size())) + ".csv");

            if (file.createNewFile()){
                System.out.println("Ficheiro de movimentos criado.");
            }
            else{
                System.out.println("Ficheiro ja existe.");
            }
        }
        catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

