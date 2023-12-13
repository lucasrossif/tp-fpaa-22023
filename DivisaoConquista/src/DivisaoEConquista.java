import java.util.ArrayList;
import java.util.List;

public class DivisaoEConquista {

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
}
