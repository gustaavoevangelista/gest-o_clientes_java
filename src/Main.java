import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int opcaoMenu;

        Clientes ListaClientes = new Clientes();

        if (ListaClientes.LerFile() == true) {
            System.out.println("Ficheiro de clientes lido.");
        }
        else {
            System.out.println("Erro ao ler o ficheiro de clientes!");
        }

        do {
            System.out.println("\n<<<<<<LISTA DE OPCOES>>>>>>\n");
            ListarMenu();
            System.out.print("Escolha a sua opcao: ");
            opcaoMenu = input.nextInt();

            while (opcaoMenu < 0 || opcaoMenu > 8){
                System.out.println("Opcao invalida, insira uma das opcoes validas...");
                System.out.print("Escolha a sua opcao: ");
                opcaoMenu = input.nextInt();
            }

            // colocar as opcoes aqui
            if(opcaoMenu == 1){
                ListaClientes.MostrarClientes();
            }

            else if(opcaoMenu == 2){
                try{
                    input.nextLine();

                    System.out.println(" > Introduza o nome do cliente: ");
                    String Nome = input.nextLine();

                    System.out.println(" > Introduza o telefone do cliente: ");
                    String TelefoneStr = input.nextLine();
                    int Telefone = Integer.parseInt(TelefoneStr);

                    System.out.println(" > Introduza o credito maximo do cliente: ");
                    String CredMaxStr = input.nextLine();
                    double CredMax = Double.parseDouble(CredMaxStr);

                    ListaClientes.AdicionarCliente(Nome, Telefone, CredMax);
                    ListaClientes.EscreverFile();

                    System.out.println("Cliente inserido!");

                    }
                    catch (NumberFormatException e) {;
                        System.out.println("O telefone ou o credito maximo introduzido tem um formato errado. Operacao cancelada.");
                    }
            }

            else if(opcaoMenu == 3){
                input.nextLine();

                System.out.print(" > Introduza o indice do cliente a remover na colecao: ");
                String IndiceStr = input.nextLine();

                try{
                    int Indice = Integer.parseInt(IndiceStr);
                    ListaClientes.RemoverCliente(Indice);
                    ListaClientes.EscreverFile();
                }
                catch(NumberFormatException e){
                    System.out.println("O indice do cliente tem formato errado. Operacao anulada.");
                }
            }

            else if(opcaoMenu == 4){
                input.nextLine();

                System.out.println("Introduza o indice do cliente a alterar: ");
                String IndiceStr = input.nextLine();

                try {
                    int Indice = Integer.parseInt(IndiceStr);

                    System.out.println("Campos disponiveis para alteraçao:");
                    System.out.println("    1- Nome");
                    System.out.println("    2- Telefone");
                    System.out.println("    3- Credito Maximo");
                    System.out.println("    4- Sair");
                    System.out.println("");
                    System.out.print("Qual campo pretende modificar? ");
                    String alteracaoStr = input.nextLine();

                    switch (alteracaoStr) {
                        case "1":
                            System.out.print("Novo nome: ");
                            String Nome = input.nextLine();
                            ListaClientes.MudaNome(Indice, Nome);
                            break;

                        case "2":
                            System.out.print("Novo telefone: ");
                            int Telefone = input.nextInt();
                            ListaClientes.MudaTelefone(Indice, Telefone);
                            break;

                        case "3":
                            System.out.print("Novo credito maximo: ");
                            double CredMax = input.nextDouble();
                            ListaClientes.MudaCredMax(Indice, CredMax);
                            break;

                        case "4":
                            break;
                    }
                }
                catch (NumberFormatException e){
                    System.out.println("Indice com formato errado. Operacao cancelada.");
                }
            }

            else if(opcaoMenu == 5){
                input.nextLine();

                Cliente cliente = new Cliente();

                System.out.println("Introduza o indice do cliente a verificar os movimentos e credito disponivel: ");
                String IndiceStr = input.nextLine();

                try{
                    int Indice = Integer.parseInt(IndiceStr);
                    ListaClientes.ConsultarMovimentos(Indice);
                }
                catch(NumberFormatException e){
                    System.out.println("Indice com formato errado. Operacao cancelada.");
                }
            }

            else if(opcaoMenu == 6){
                input.nextLine();

                Cliente cliente = new Cliente();

                System.out.println("Introduza o indice do cliente a adicionar uma compra: ");
                String IndiceStr = input.nextLine();


                try{
                    int Indice = Integer.parseInt(IndiceStr);
                    ListaClientes.AdicionarCompra(Indice);

                }
                catch(NumberFormatException e){
                    System.out.println("Indice com formato errado. Operacao cancelada.");
                }

            }

            else if(opcaoMenu == 7){
                input.nextLine();

                Cliente cliente = new Cliente();

                System.out.println("Introduza o indice do cliente a adicionar um pagamento: ");
                String IndiceStr = input.nextLine();

                try{
                    int Indice = Integer.parseInt(IndiceStr);
                    ListaClientes.AdicionarPagamento(Indice);

                }
                catch(NumberFormatException e){
                    System.out.println("Indice com formato errado. Operacao cancelada.");
                }

            }

            if(opcaoMenu == 8){
                ListaClientes.EscreverFile();
                System.out.println("Programa encerrado. Adeus!");
                break;
            }
        }
        while (opcaoMenu > 0  && opcaoMenu <= 8);

        //fazer um try e catch para a opcaoMenu
    }

    private static void ListarMenu() {
        System.out.println("1- Listar clientes ativos");
        System.out.println("2- Adicionar clientes");
        System.out.println("3- Eliminar um cliente"); //deixá-lo inativo
        System.out.println("4- Modificar dados de um cliente");
        System.out.println("5- Consultar movimentos de um cliente e verificar credito disponivel");
        System.out.println("6- Adicionar compra a um cliente");
        System.out.println("7- Adicionar pagamento a um cliente");
        System.out.println("8- Sair do programa");
    }

}