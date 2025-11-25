public class Pessoa {

    private String nome;
    private TeleSena[] teleSenas;
    private int quantidadeComprada;
    private double premioRecebido;

    public Pessoa(String nome, int qtd) {
        this.nome = nome;
        this.quantidadeComprada = qtd;
        teleSenas = new TeleSena[qtd];

        for (int i = 0; i < qtd; i++) {
            teleSenas[i] = new TeleSena();
        }
    }

    public String getNome() {
        return nome;
    }

    public TeleSena[] getTeleSenas() {
        return teleSenas;
    }

    public double getPremioRecebido() {
        return premioRecebido;
    }

    public void adicionarPremio(double valor) {
        this.premioRecebido += valor;
    }

    public int getQuantidadeComprada() {
        return quantidadeComprada;
    }
}
