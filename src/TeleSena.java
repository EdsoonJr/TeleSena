public class TeleSena {

    public static final double VALOR_VENDA = 10.0;

    // Cada TeleSena possui duas cartelas de 25 números
    private int[] cartela1 = new int[25];
    private int[] cartela2 = new int[25];

    public TeleSena() {
        gerarNumeros(cartela1);
        gerarNumeros(cartela2);
    }

    private void gerarNumeros(int[] cartela) {
        int count = 0;

        while (count < 25) {
            int numgerado = (int) (Math.random() * 60 + 1); // gera número de 1 a 60
            boolean repetido = false;

            // Verifica se o número já existe na cartela
            for (int i = 0; i < count; i++) {
                if (cartela[i] == numgerado) {
                    repetido = true;
                    break;
                }
            }

            // Se não é repetido, adiciona à cartela
            if (!repetido) {
                cartela[count] = numgerado;
                count++;
            }
        }
    }

    // GETTERS
    public int[] getCartela1() {
        return cartela1;
    }

    public int[] getCartela2() {
        return cartela2;
    }

    // Verifica se uma das cartelas acertou todos os números sorteados
    public boolean acertouTodos(int[] numerosSorteados, int quantidade) {

        // Verifica a cartela 1
        boolean verifcart1 = true;
        for (int n : cartela1) {
            boolean achou = false;
            for (int i = 0; i < quantidade; i++) {
                if (numerosSorteados[i] == n) {
                    achou = true;
                    break;
                }
            }
            if (!achou) {
                verifcart1 = false;
                break;
            }
        }

        // Verifica a cartela 2
        boolean verifcart2 = true;
        for (int n : cartela2) {
            boolean achou = false;
            for (int i = 0; i < quantidade; i++) {
                if (numerosSorteados[i] == n) {
                    achou = true;
                    break;
                }
            }
            if (!achou) {
                verifcart2 = false;
                break;
            }
        }

        return (verifcart1 || verifcart2);
    }
}
