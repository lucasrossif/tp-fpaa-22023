import java.util.ArrayList;
import java.util.List;

class Backtraking {

    public static void executarSolucaoBacktracking (int qtdCaminhoes) {
        for (int i = 0; i < 10; i++) {
            List<int[]> conjuntoDeTeste = GeradorDeProblemas.geracaoDeRotas(10, 1, 1);
            System.out.println("------- Conjunto de teste " + i + " -------");
            for (int[] rotas : conjuntoDeTeste) {
                int meta = 0;
                for (int rota : rotas) {
                    meta += rota;
                }
                List<List<Number>> caminhoes = backtracking(rotas, new int[qtdCaminhoes], new ArrayList<>(), meta / qtdCaminhoes, 0);
                for (List<Number> caminhao : caminhoes) {
                    System.out.println(caminhao);
                }
            }
        }
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
            int kilometragemCaminhao = 0;
            for (Number rota : caminhao) {
                kilometragemCaminhao += rota.intValue();
            }
            if (Math.abs(kilometragemCaminhao - meta) > 1) {
                return false;
            }
        }

        return true;
    }

}