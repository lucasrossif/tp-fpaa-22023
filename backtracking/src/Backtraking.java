import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Backtraking {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

//        List<int[]> conjuntoDeTeste = GeradorDeProblemas.geracaoDeRotas(10, 10, 1);
        List<int[]> conjuntoDeTeste = List.of(
                new int[]{40,36,38,29,32,28,31,35,31,30,32,30,29,39,35,38,39,35,32,38,32,33,29,33,29,39,28},
                new int[]{32,51,32,43,42,30,42,51,43,51,29,25,27,32,29,55,43,29,32,44,55,29,53,30,24,27});

        for(int i = 0; i < conjuntoDeTeste.size(); i++) {
            long startTime2 = System.currentTimeMillis();
            System.out.println("------------" + "Conjunto de teste " + (i + 1) + "------------" );

            int[] rotas = conjuntoDeTeste.get(i);
            int meta = 0;

            for (int rota : rotas) {
                meta += rota;
            }
            List<List<Number>> caminhoes = backtracking(rotas, new int[3], new ArrayList<>(), meta / 3, 0);

            int[] totalParaRotas = new int[3];

            for(int j = 0; j < caminhoes.size(); j++) {
                System.out.println("Caminhão " + (j + 1) + ": " + caminhoes.get(j));
                totalParaRotas[j] = caminhoes.get(j).stream().mapToInt(Number::intValue).sum();
            }

            System.out.println("Total para rotas: " + Arrays.toString(totalParaRotas));
            System.out.println("Desvio padrão: " + calcularDesvioPadrao(totalParaRotas));
            System.out.println("Amplitude: " + calcularAmplitude(totalParaRotas));


            long endTime2 = System.currentTimeMillis();

            long duration2 = endTime2 - startTime2;
            double durationInSeconds2 = duration2 / 1000.0;
            System.out.println("Tempo de execução atual: " + durationInSeconds2 + " segundos");

        }

        System.out.println("------------" + "Fim dos testes" + "------------");
        long endTime = System.currentTimeMillis();

        long duration = endTime - startTime;
        double durationInSeconds = duration / 1000.0;
        System.out.println("Tempo de execução geral: " + durationInSeconds + " segundos");
    }


    public static List<List<Number>> backtracking(int[] rotas, int[] melhorKilometragem, List<List<Number>> solucoes,
            int meta, int iteratorRotas) {
        if (iteratorRotas == rotas.length) {
            if (podarTrem(solucoes, meta)) {
                return new ArrayList<>(solucoes);
            }
            return new ArrayList<>();
        }

        for (int i = 0; i < 3; i++) {
            if (solucoes.size() <= i) {
                solucoes.add(new ArrayList<>());
            }

            solucoes.get(i).add(rotas[iteratorRotas]);
            melhorKilometragem[i] += rotas[iteratorRotas];

            List<List<Number>> recursao = backtracking(rotas, melhorKilometragem, solucoes, meta, iteratorRotas + 1);

            if (!recursao.isEmpty()) {
                return recursao;
            }

            solucoes.get(i).remove(solucoes.get(i).size() - 1);
            melhorKilometragem[i] -= rotas[iteratorRotas];
        }

        return new ArrayList<>();
    }

    public static boolean podarTrem(List<List<Number>> solucoes, int meta) {
        if (solucoes == null || solucoes.isEmpty()) {
            return false;
        }

        for (List<Number> caminhao : solucoes) {
            int kilometragemCaminhao = caminhao.stream().mapToInt(Number::intValue).sum();

            if (Math.abs(kilometragemCaminhao - meta) >= 1) {
                return false;
            }
        }

        return true;
    }

    public static double calcularDesvioPadrao(int[] array) {
        double soma = 0;
        for (int valor : array) {
            soma += valor;
        }
        double media = soma / array.length;

        double somaQuadradosDiferencas = 0;
        for (int valor : array) {
            double diferenca = valor - media;
            somaQuadradosDiferencas += diferenca * diferenca;
        }

        double variancia = somaQuadradosDiferencas / array.length;


        return Math.sqrt(variancia);
    }

    public static int calcularAmplitude(int[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("O array não pode estar vazio.");
        }

        int menorValor = array[0];
        int maiorValor = array[0];

        for (int valor : array) {
            if (valor < menorValor) {
                menorValor = valor;
            } else if (valor > maiorValor) {
                maiorValor = valor;
            }
        }

        return maiorValor - menorValor;
    }

}


