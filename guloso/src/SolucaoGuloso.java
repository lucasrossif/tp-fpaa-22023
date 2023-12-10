import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

public class SolucaoGuloso {

    public static void executarGuloso(List<int[]> rotas) {

        for (int[] rota : rotas){
            int soma = Arrays.stream(rota).sum();
            double media = Arrays.stream(rota).average().getAsDouble();
            System.out.println("Rota " + Arrays.toString(rota) + ":\nSoma: " + soma + "\nMédia: " + media);

        }
    }
}
