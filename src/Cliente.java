public class Cliente {

    private int Indice;
    private boolean EstadoCliente;
    private String Nome;
    private int Telefone;
    private double CredMax;

    private double CredDisp;
    private String FicheiroMovimentos;


    public Cliente(){
    }

    public int setIndice(int Indice){
        this.Indice = Indice;
        return Indice;
    }

    public void setEstadoCliente(boolean EstadoCliente){
        this.EstadoCliente = EstadoCliente;
    }
    public void setNome(String Nome){
        this.Nome = Nome;
    }

    public void setTelefone(int Telefone){
        this.Telefone = Telefone;
    }

    public void setCredMax(double CredMax){
        this.CredMax = CredMax;
    }
    public void setCredDisp(double CredDisp){
        this.CredDisp = CredDisp;
    }

    public void setFicheiroMovimentos(String FicheiroMovimentos){
        this.FicheiroMovimentos = FicheiroMovimentos;
    }
    public int getIndice(){
        return this.Indice;
    }

    public boolean getEstadoCliente(){
        return this.EstadoCliente;
    }
    public  String getNome(){
        return this.Nome;
    }

    public int getTelefone(){
        return this.Telefone;
    }

    public double getCredMax(){
        return this.CredMax;
    }

    public double getCredDisp(){
        return this.CredDisp;
    }

}
