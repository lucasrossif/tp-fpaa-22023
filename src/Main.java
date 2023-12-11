import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        int qtdCaminhoes = 3;

        Backtraking.executarSolucaoBacktracking(qtdCaminhoes);

        // Tamanho que vem do backtracking
        int tamanhoT = 25;

        SolucaoGuloso.executarSolucaoGulosa(tamanhoT, qtdCaminhoes);
        ProgDinamica.executarSolucaoProgDinamica(qtdCaminhoes);


    }
}