public class ControleTeleSena {

    private Pessoa[] pessoas = new Pessoa[20];
    private int teleSenasVendidas = 0;
    private int[] numerosSorteados = new int[60];
    private int qtdSorteados = 0;
    private Pessoa[] ganhadores = new Pessoa[20];
    private int qtdGanhadores = 0;

    private String[] nomes = {
            "Ana", "Bruno", "Carlos", "Daniel", "Eduarda",
            "Fernanda", "Gabriel", "Helena", "Igor", "João",
            "Karen", "Luis", "Mariana", "Neto", "Olivia",
            "Paulo", "Quezia", "Rafaela", "Samuel", "Tatiane"
    };

    public void iniciarVendas() {// sortea qtd de telesenas compradas

        for (int i = 0; i < pessoas.length; i++) {
            int qtdComprada = (int) (Math.random() * 15 + 1);

            if (teleSenasVendidas + qtdComprada > 300) {// garante 300 apenas
                qtdComprada = 300 - teleSenasVendidas;
                if (qtdComprada <= 0)
                    break;
            }

            pessoas[i] = new Pessoa(nomes[i], qtdComprada);// cria pessoa e inseri qtd de cartelascompradas
            teleSenasVendidas += qtdComprada;
        }
    }

    private void sortearNumero() {
        while (true) {
            int numeros = (int) (Math.random() * 60 + 1);// sortea numeros de 1 a 60
            boolean repetido = false;

            for (int i = 0; i < qtdSorteados; i++) {// verifica se ja foi sorteado
                if (numerosSorteados[i] == numeros) {
                    repetido = true;
                    break;
                }
            }

            if (!repetido) {// se nao foi repetido inseri
                numerosSorteados[qtdSorteados] = numeros;
                qtdSorteados++;
                break;
            }
        }
    }

    public void realizarSorteio() {

        for (int i = 0; i < 25; i++) {// sortea 25 numeros
            sortearNumero();
        }

        boolean temGanhador = verificarGanhadores();// verifica se ganho

        while (!temGanhador) {// se não continua
            sortearNumero();
            temGanhador = verificarGanhadores();
        }
    }

    private boolean verificarGanhadores() {

        for (Pessoa p : pessoas) {// pecorre todas as pessoas
            if (p == null)
                continue;

            for (TeleSena t : p.getTeleSenas()) {// pecorre todas as telesenas da pessoa
                if (t.acertouTodos(numerosSorteados, qtdSorteados)) {// verifica se acertou todos numeros

                    ganhadores[qtdGanhadores] = p;
                    qtdGanhadores++;
                    break;
                }
            }
        }

        return qtdGanhadores > 0;
    }

    public void mostrarResultado() {

        final String RESET = "\u001B[0m";
        final String BOLD = "\u001B[1m";
        final String YELLOW = "\u001B[33m";
        final String CYAN = "\u001B[36m";
        final String GREEN = "\u001B[32m";
        final String WHITE = "\u001B[37m";

        System.out.println("\n" + CYAN + "========================================" + RESET);
        System.out.println("    " + BOLD + YELLOW + "RESULTADO FINAL DA TELE SENA!" + RESET);
        System.out.println(CYAN + "========================================\n" + RESET);

        System.out.println(BOLD + "Números Sorteados (" + qtdSorteados + "):" + RESET);

        for (int i = 0; i < qtdSorteados; i++) {
            System.out.printf(YELLOW + "%02d " + RESET, numerosSorteados[i]);

            if ((i + 1) % 10 == 0) {
                System.out.println();
            }
        }

        System.out.println("\n" + CYAN + "----------------------------------------" + RESET);
        System.out.println(BOLD + "Tele Senas Vendidas: " + RESET + teleSenasVendidas);
        System.out.println(BOLD + "Quantidade de Ganhadores: " + RESET + qtdGanhadores);
        System.out.println(CYAN + "----------------------------------------\n" + RESET);

        double totalVendas = teleSenasVendidas * TeleSena.VALOR_VENDA;
        double premioTotal = totalVendas * 0.8;
        double premioPorGanhador = premioTotal / qtdGanhadores;

        System.out.println(BOLD + GREEN + "GANHADORES:" + RESET);

        for (int i = 0; i < qtdGanhadores; i++) {
            ganhadores[i].adicionarPremio(premioPorGanhador);
            System.out.printf(" - " + WHITE + "%s" + RESET + " | Prêmio: " + GREEN + "R$ %.2f" + RESET + "%n",
                    ganhadores[i].getNome(),
                    premioPorGanhador);
        }

        System.out.println("\n" + CYAN + "----------------------------------------" + RESET);
        System.out.printf(BOLD + "Total Arrecadado: " + RESET + "R$ %.2f%n", totalVendas);
        System.out.printf(BOLD + "Total Pago em Prêmios: " + RESET + "R$ %.2f%n", premioTotal);
        System.out.printf(BOLD + "Lucro Obtido: " + RESET + "R$ %.2f%n", (totalVendas - premioTotal));
        System.out.println(CYAN + "========================================\n" + RESET);
    }

}
