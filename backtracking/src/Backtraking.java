import java.util.ArrayList;
import java.util.List;

class Backtraking {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 10; i++) {
            List<int[]> conjuntoDeTeste = GeradorDeProblemas.geracaoDeRotas(26, 1, 1);
            System.out.println("------- Conjunto de teste " + i + " -------");
            for (int[] rotas : conjuntoDeTeste) {
                int meta = 0;
                for (int rota : rotas) {
                    meta += rota;
                }
                List<List<Number>> caminhoes = backtracking(rotas, new int[3], new ArrayList<>(), meta / 3, 0);
                for (List<Number> caminhao : caminhoes) {
                    System.out.println(caminhao);
                }
            }
        }

        long endTime = System.currentTimeMillis();

        long duration = endTime - startTime;
        double durationInSeconds = duration / 1000.0;
        System.out.println("Tempo de execução: " + durationInSeconds + " segundos");
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

            if (Math.abs(kilometragemCaminhao - meta) > 10) {
                return false;
            }
        }

        return true;
    }

}
