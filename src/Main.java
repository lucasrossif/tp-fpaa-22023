import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        int qtdCaminhoes = 3;

        List<int[]> rotas = GeradorDeProblemas.geracaoDeRotas(10,10,1.0);

        for (int[] rota : rotas) {
            System.out.println(Arrays.toString(rota));
        }

        Backtraking.executarSolucaoBacktracking(qtdCaminhoes);

        // Tamanho que vem do backtracking
        int tamanhoT = 5;

        SolucaoGuloso.executarSolucaoGulosa(tamanhoT, qtdCaminhoes);
        ProgDinamica.executarSolucaoProgDinamica(qtdCaminhoes);


    }
}