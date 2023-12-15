import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProgDinamica {
    public static void executarSolucaoProgDinamica(int qtdCaminhoes) {
        final int tamConjunto = 10;

        List<int[]> rotasGeradas = GeradorDeProblemas.geracaoDeRotas(220, tamConjunto, 1);

        List<List<DtoResposta>> respostas = new ArrayList<>();

        for (int[] rotasGerada : rotasGeradas) {
            respostas.add(getRotas(qtdCaminhoes, rotasGerada));
        }

        int numConjunto = 1;
        double tempoTotal = 0.0;
        double desvioPadraoTotal = 0.0;
        int amplitudeTotal = 0;
        // Imprimir Relatório
        for (List<DtoResposta> conjunto : respostas) {
            int[] totaisRotas = conjunto.stream()
                    .mapToInt(resCaminhoes -> resCaminhoes.rota().total())
                    .toArray();

            double tempo = conjunto.stream()
                    .mapToDouble(DtoResposta::timmer)
                    .sum();

            double desvioPadrao = Util.calcularDesvioPadrao(totaisRotas);
            int amplitude = Util.calcularAmplitude(totaisRotas);

            tempoTotal += tempo;
            desvioPadraoTotal += desvioPadrao;
            amplitudeTotal += amplitude;

            System.out.println("Conjunto (" + (numConjunto++) + ")");
            System.out.println("Totais de rota: " + Arrays.toString(totaisRotas));
            System.out.println("Desvio Padrão: " + desvioPadrao);
            System.out.println("Amplitude: " + amplitude);
            System.out.println("Tempo (ms): " + tempo);
            System.out.println();
        }
        System.out.println("===================================");
        System.out.println();
        System.out.println("[Total] Desvio Padrão: " + desvioPadraoTotal / tamConjunto);
        System.out.println("[Total] Amplitude: " + amplitudeTotal / tamConjunto);
        System.out.println("[Total] Media tempo (ms): " + tempoTotal/tamConjunto);
    }

    private static List<DtoResposta> getRotas(int caminhoes, int[] array) {
        List<Integer> set = new ArrayList<>(Arrays.stream(array)
                .boxed()
                .toList());
        List<DtoResposta> respostas = new ArrayList<>();
        int sum = (int) Math.ceil(set.stream().mapToDouble(a -> a).sum() / caminhoes);

        for (int i = 0; i < caminhoes; i++) {
            String label = "Caminhão " + (i + 1);

            double timmer = System.nanoTime();
            boolean[][] subsetSum = GFG.subsetSum(set, sum);
            Rota rota = GFG.getResults(set, subsetSum);
            timmer = System.nanoTime() - timmer;

            respostas.add(new DtoResposta(label, rota, timmer / 1000000));

            // Garantimos que removemos apenas 1
            for (int r : rota.rotas()) {
                set.remove(set.indexOf(r));
            }

            // Garante margem de erro que não foi suprida
            sum += sum - rota.total();
        }
        return respostas;
    }

    public static void executarSolucaoProgDinamicaApresentacao(List<int[]> conjuntoDeTeste, int qtdCaminhoes) {

        final int tamConjunto = 2;

        List<List<DtoResposta>> respostas = new ArrayList<>();

        for (int[] rotasGerada : conjuntoDeTeste) {
            respostas.add(getRotas(qtdCaminhoes, rotasGerada));
        }

        int numConjunto = 1;
        double tempoTotal = 0.0;
        double desvioPadraoTotal = 0.0;
        int amplitudeTotal = 0;
        // Imprimir Relatório
        for (List<DtoResposta> conjunto : respostas) {
            int[] totaisRotas = conjunto.stream()
                    .mapToInt(resCaminhoes -> resCaminhoes.rota().total())
                    .toArray();

            double tempo = conjunto.stream()
                    .mapToDouble(DtoResposta::timmer)
                    .sum();

            double desvioPadrao = Util.calcularDesvioPadrao(totaisRotas);
            int amplitude = Util.calcularAmplitude(totaisRotas);

            tempoTotal += tempo;
            desvioPadraoTotal += desvioPadrao;
            amplitudeTotal += amplitude;

            System.out.println("Conjunto (" + (numConjunto++) + ")");
            System.out.println("Totais de rota: " + Arrays.toString(totaisRotas));
            System.out.println("Desvio Padrão: " + desvioPadrao);
            System.out.println("Amplitude: " + amplitude);
            System.out.println("Tempo (ms): " + tempo);
            System.out.println();
        }
        System.out.println("===================================");
        System.out.println();
        System.out.println("[Total] Desvio Padrão: " + desvioPadraoTotal / tamConjunto);
        System.out.println("[Total] Amplitude: " + amplitudeTotal / tamConjunto);
        System.out.println("[Total] Media tempo (ms): " + tempoTotal/tamConjunto);

    }
}