import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProgDinamica {
    public static void executarSolucaoProgDinamica (int qtdCaminhoes){
        List<int[]> rotasGeradas = GeradorDeProblemas.geracaoDeRotas(10, 1, 1);

        List<Integer> set = new ArrayList<>(Arrays.stream(rotasGeradas.get(0))
                .boxed()
                .toList());
        // Calcula o ideal para cada um, e arredonda, evitando decimais
        int sum = (int) Math.round(set.stream().mapToDouble(a -> a).sum()/qtdCaminhoes);

        System.out.println("Initial: " + set);

        for (int i = 0; i < qtdCaminhoes; i++) {
            System.out.println("Caminhão " + (i+1));
            boolean[][] subsetSum = GFG.subsetSum(set, sum);
            Rota rota = GFG.getResults(set, subsetSum);
            System.out.println(rota.rotas());
            System.out.println(rota.total());

            // Garantimos que removemos apenas 1
            for (int r: rota.rotas()) {
                set.remove(set.indexOf(r));
            }

            // Garante margem de erro que não foi suprida
            sum += sum - rota.total();
            System.out.println("====================");
        }
    }
}
