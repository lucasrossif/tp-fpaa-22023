import java.util.List;

public class main {

    public static void main(String[] args) {
        int numConjuntos = 10;
        int numCaminhoes = 3;
        int numRotas = 25;

        long tempoTotal = 0;
        long menorTempo = Long.MAX_VALUE;
        long maiorTempo = Long.MIN_VALUE;
        long somaAmplitudes = 0;

        for (int i = 0; i < numConjuntos; i++) {
            long tempoInicial = System.nanoTime();

            List<int[]> conjuntoDeRotas = GeradorDeProblemas.geracaoDeRotas(numRotas, numConjuntos, 1);
            int[] rotas = conjuntoDeRotas.get(0);

            List<List<Integer>> distribuicao = DivisaoEConquista.distribuirRotas(rotas, numCaminhoes);

            for (int j = 0; j < distribuicao.size(); j++) {
                System.out.println("Caminhão " + (j + 1) + ": rotas " + distribuicao.get(j) +
                        " - total " + DivisaoEConquista.calcularQuilometragem(distribuicao.get(j)) + "km");
            }

            long tempoFinal = System.nanoTime();
            long tempoConjunto = tempoFinal - tempoInicial;

            System.out.println("Tempo do conjunto " + (i + 1) + ": " + tempoConjunto + " nanossegundos");

            // Calcular amplitude
            long amplitudeConjunto = calcularAmplitude(distribuicao);
            System.out.println("Amplitude do conjunto " + (i + 1) + ": " + amplitudeConjunto + " km");
            System.out.println("---------------");

            // Atualizar a amplitude geral
            somaAmplitudes += amplitudeConjunto;

            // Atualizar a amplitude
            if (tempoConjunto < menorTempo) {
                menorTempo = tempoConjunto;
            }
            if (tempoConjunto > maiorTempo) {
                maiorTempo = tempoConjunto;
            }

            // Somar o tempo total
            tempoTotal += tempoConjunto;
        }

        // Calcular a média da amplitude final
        long mediaAmplitudeFinal = somaAmplitudes / numConjuntos;

        System.out.println("Média de Amplitude Final: " + mediaAmplitudeFinal + " km");
        System.out.println("Média de Tempo Final: " + (tempoTotal / numConjuntos) + " nanossegundos");
    }

    private static long calcularAmplitude(List<List<Integer>> distribuicao) {
        long menorQuilometragem = Long.MAX_VALUE;
        long maiorQuilometragem = Long.MIN_VALUE;

        for (List<Integer> caminhao : distribuicao) {
            long quilometragemCaminhao = DivisaoEConquista.calcularQuilometragem(caminhao);

            if (quilometragemCaminhao < menorQuilometragem) {
                menorQuilometragem = quilometragemCaminhao;
            }

            if (quilometragemCaminhao > maiorQuilometragem) {
                maiorQuilometragem = quilometragemCaminhao;
            }
        }

        return maiorQuilometragem - menorQuilometragem;
    }
}
