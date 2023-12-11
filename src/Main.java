import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        int qtdCaminhoes = 3;

        List<int[]> rotas = GeradorDeProblemas.geracaoDeRotas(10,10,1.0);

        for (int[] rota : rotas) {
            System.out.println(Arrays.toString(rota));
        }

        int tamanhoT = 5;
        SolucaoGuloso.executarSolucaoGulosa(tamanhoT, qtdCaminhoes);



    }
}