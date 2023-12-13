import java.util.ArrayList;
import java.util.List;

public class DivisaoEConquista {

    public static void executarDivisaoEConquista (int qtdCaminhoes) {
        int numConjuntos = 10;
        int numRotas = 22;

        long tempoTotal = 0;
        long menorTempo = Long.MAX_VALUE;
        long maiorTempo = Long.MIN_VALUE;
        long somaAmplitudes = 0;

        for (int i = 0; i < numConjuntos; i++) {
            long tempoInicial = System.nanoTime();

            List<int[]> conjuntoDeRotas = GeradorDeProblemas.geracaoDeRotas(numRotas, numConjuntos, 1);
            int[] rotas = conjuntoDeRotas.get(0);

            List<List<Integer>> distribuicao = DivisaoEConquista.distribuirRotas(rotas, qtdCaminhoes);

            for (int j = 0; j < distribuicao.size(); j++) {
                System.out.println("Caminhão " + (j + 1) + ": rotas " + distribuicao.get(j) +
                        " - total " + DivisaoEConquista.calcularQuilometragem(distribuicao.get(j)) + "km");
            }

            long tempoFinal = System.nanoTime();
            long tempoConjunto = tempoFinal - tempoInicial;

            System.out.println("Tempo do conjunto " + (i + 1) + ": " + tempoConjunto/1000000. + " milissegundos");

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
        System.out.println("Média de Tempo Final: " + ((tempoTotal / numConjuntos)/1000000.) + " milissegundos");
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

    public static List<List<Integer>> distribuirRotas(int[] rotas, int numCaminhoes) {
        List<List<Integer>> distribuicao = new ArrayList<>();

        for (int i = 0; i < numCaminhoes; i++) {
            distribuicao.add(new ArrayList<>());
        }

        distribuirRotasDivisaoConquista(rotas, 0, rotas.length - 1, distribuicao);

        return distribuicao;
    }

    private static void distribuirRotasDivisaoConquista(int[] rotas, int inicio, int fim, List<List<Integer>> distribuicao) {
        if (inicio > fim) {
            return;
        }

        int meio = (inicio + fim) / 2;

        distribuirRotasDivisaoConquista(rotas, inicio, meio - 1, distribuicao);
        distribuirRotasDivisaoConquista(rotas, meio + 1, fim, distribuicao);

        int menorIndex = encontrarMenorCaminhao(distribuicao);
        distribuicao.get(menorIndex).add(rotas[meio]);
    }

    private static int encontrarMenorCaminhao(List<List<Integer>> distribuicao) {
        int menorIndex = 0;
        int menorQuilometragem = calcularQuilometragem(distribuicao.get(0));

        for (int i = 1; i < distribuicao.size(); i++) {
            int quilometragemAtual = calcularQuilometragem(distribuicao.get(i));
            if (quilometragemAtual < menorQuilometragem) {
                menorQuilometragem = quilometragemAtual;
                menorIndex = i;
            }
        }

        return menorIndex;
    }

    private static int calcularQuilometragem(List<Integer> rotas) {
        int quilometragem = 0;
        for (int rota : rotas) {
            quilometragem += rota;
        }
        return quilometragem;
    }

    public static void executarDivisaoEConquistaApresentacao (List<int[]> conjuntoDeTeste, int qtdCaminhoes) {
        int numConjuntos = 2;

        long tempoTotal = 0;
        long menorTempo = Long.MAX_VALUE;
        long maiorTempo = Long.MIN_VALUE;
        long somaAmplitudes = 0;

        for (int i = 0; i < numConjuntos; i++) {
            long tempoInicial = System.nanoTime();

            int[] rotas = conjuntoDeTeste.get(0);

            List<List<Integer>> distribuicao = DivisaoEConquista.distribuirRotas(rotas, qtdCaminhoes);

            for (int j = 0; j < distribuicao.size(); j++) {
                System.out.println("Caminhão " + (j + 1) + ": rotas " + distribuicao.get(j) +
                        " - total " + DivisaoEConquista.calcularQuilometragem(distribuicao.get(j)) + "km");
            }

            long tempoFinal = System.nanoTime();
            long tempoConjunto = tempoFinal - tempoInicial;

            System.out.println("Tempo do conjunto " + (i + 1) + ": " + tempoConjunto/1000000. + " milissegundos");

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
        System.out.println("Média de Tempo Final: " + ((tempoTotal / numConjuntos)/1000000.) + " milissegundos");
    }

}