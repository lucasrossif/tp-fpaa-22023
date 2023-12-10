import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<int[]> rotas = GeradorDeProblemas.geracaoDeRotas(20,10,1.0);

        for (int[] rota : rotas) {
            System.out.println(Arrays.toString(rota));
        }

        SolucaoGuloso.executarGuloso(rotas);

    }
}