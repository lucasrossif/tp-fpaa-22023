import java.util.List;

public class TesteDivEConq {

    public static void main(String[] args) {
        int numConjuntos = 10;
        int numCaminhoes = 3;
        int numRotas = 25;

        long tempoInicial = System.nanoTime();

        for (int i = 0; i < numConjuntos; i++) {
            List<int[]> conjuntoDeRotas = GeradorDeProblemas.geracaoDeRotas(numRotas, 1, 0.5);
            int[] rotas = conjuntoDeRotas.get(0);

            List<List<Integer>> distribuicao = DivisaoEConquista.distribuirRotas(rotas, numCaminhoes);

            for (int j = 0; j < distribuicao.size(); j++) {
                System.out.println("Caminhão " + (j + 1) + ": rotas " + distribuicao.get(j) +
                        " - total " + DivisaoEConquista.calcularQuilometragem(distribuicao.get(j)) + "km");
            }

            System.out.println("---------------");
        }

        long tempoFinal = System.nanoTime();
        long tempoTotal = tempoFinal - tempoInicial;

        System.out.println("Tempo total de execução: " + tempoTotal + " nanossegundos");
    }
}
