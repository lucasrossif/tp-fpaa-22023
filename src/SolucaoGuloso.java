import java.util.*;

public class SolucaoGuloso {

    public static void executarSolucaoGulosa (int tamanhoT, int qtdCaminhoes) {
        long tempoTotalGulosoE1 = 0;
        long tempoTotalGulosoE2 = 0;
        long tempoTotalGulosoE3 = 0;

        for (int i = tamanhoT; i <= tamanhoT*10; i += tamanhoT ){
            System.out.println("---Testando algoritmo guloso com conjuntos de tamanho " + i + "---");

            for (int j = 0; j < 10; j++){
                List<int[]> rotasGuloso = GeradorDeProblemas.geracaoDeRotas(i,10,1.0);

                // Estratégia 1: ordenar as rotas em ordem crescente
                long inicioGuloso = System.nanoTime();;
                SolucaoGuloso.executarGulosoCrescente(rotasGuloso, qtdCaminhoes);
                long fimGuloso = System.nanoTime();;
                tempoTotalGulosoE1 += (fimGuloso - inicioGuloso);

                // Estratégia 2: ordenar as rotas em ordem decrescente
                inicioGuloso = System.nanoTime();;
                SolucaoGuloso.executarGulosoDecrescente(rotasGuloso, qtdCaminhoes);
                fimGuloso = System.nanoTime();;
                tempoTotalGulosoE2 += (fimGuloso - inicioGuloso);

                // Estratégia 3: ordenar as rotas em ordem crescente e ir distribuindo até chegar na média
                inicioGuloso = System.nanoTime();;
                SolucaoGuloso.executarGulosoMedia(rotasGuloso, qtdCaminhoes);
                fimGuloso = System.nanoTime();;
                tempoTotalGulosoE3 += (fimGuloso - inicioGuloso);

                //System.out.println("Tempo de execução " + (0+j) + " da rota de tamanho " + i + ": " + (fimGuloso - inicioGuloso) + " ms");
            }
            System.out.println("Média de tempo de execução da rota de tamanho " + i + " com estratégia 1: " + (tempoTotalGulosoE1/10)/1000000.0 + " ms");
            System.out.println("Média de tempo de execução da rota de tamanho " + i + " com estratégia 2: " + (tempoTotalGulosoE2/10)/1000000.0 + " ms");
            System.out.println("Média de tempo de execução da rota de tamanho " + i + " com estratégia 3: " + (tempoTotalGulosoE3/10)/1000000.0 + " ms");

        }
    }

    public static void executarSolucaoGulosaApresentacao (List<int[]> rotasGuloso, int qtdCaminhoes) {
        long tempoTotalGulosoE1 = 0;
        long tempoTotalGulosoE2 = 0;
        long tempoTotalGulosoE3 = 0;

            for (int j = 0; j < 10; j++){

                // Estratégia 1: ordenar as rotas em ordem crescente
                long inicioGuloso = System.nanoTime();;
                SolucaoGuloso.executarGulosoCrescente(rotasGuloso, qtdCaminhoes);
                long fimGuloso = System.nanoTime();;
                tempoTotalGulosoE1 += (fimGuloso - inicioGuloso);

                // Estratégia 2: ordenar as rotas em ordem decrescente
                inicioGuloso = System.nanoTime();;
                SolucaoGuloso.executarGulosoDecrescente(rotasGuloso, qtdCaminhoes);
                fimGuloso = System.nanoTime();;
                tempoTotalGulosoE2 += (fimGuloso - inicioGuloso);

                // Estratégia 3: ordenar as rotas em ordem crescente e ir distribuindo até chegar na média
                inicioGuloso = System.nanoTime();;
                SolucaoGuloso.executarGulosoMedia(rotasGuloso, qtdCaminhoes);
                fimGuloso = System.nanoTime();;
                tempoTotalGulosoE3 += (fimGuloso - inicioGuloso);

                //System.out.println("Tempo de execução " + (0+j) + " da rota de tamanho " + i + ": " + (fimGuloso - inicioGuloso) + " ms");
            }
            System.out.println("Média de tempo de execução da rota de tamanho com estratégia 1: " + (tempoTotalGulosoE1/10)/1000000.0 + " ms");
            System.out.println("Média de tempo de execução da rota de tamanho com estratégia 2: " + (tempoTotalGulosoE2/10)/1000000.0 + " ms");
            System.out.println("Média de tempo de execução da rota de tamanho com estratégia 3: " + (tempoTotalGulosoE3/10)/1000000.0 + " ms");


    }

    public static void executarGulosoCrescente(List<int[]> rotas, int qtdCaminhoes) {
        // Estratégia 1 - Quilometragens em ordem crescente
        for (int[] rota : rotas) {
            System.out.println("Rota: " + Arrays.toString(rota));
            int[] caminhoes = new int[qtdCaminhoes];
            int somaKmsRota = Arrays.stream(rota).sum();

            //Ordena o array em ordem crescente
            Arrays.sort(rota);


            for (int km : rota) {
                int caminhaoMenosKm = encontrarCaminhaoMenosKm(caminhoes);
                caminhoes[caminhaoMenosKm] += km;
            }

            System.out.println("-----Resultado Estratégia 1----");

           System.out.println("Soma de kms rota: " + somaKmsRota);
              System.out.println("Soma de kms caminhões: " + Arrays.stream(caminhoes).sum());
              System.out.println("Desvio padrão rota " + Util.calcularDesvioPadrao(caminhoes));
            System.out.println("Amplitude rota " + Util.calcularAmplitude(caminhoes));
            for (int i = 0; i < caminhoes.length; i++) {
                System.out.println("Qtd kms caminhão " + (i+1) + ": " + caminhoes[i]);
            }
        }

    }

    public static void executarGulosoDecrescente (List<int[]> rotas, int qtdCaminhoes) {
        // Estratégia 2 - Quilometragens em ordem decrescente
        for (int[] rota : rotas) {
            System.out.println("Rota: " + Arrays.toString(rota));
            int[] caminhoes = new int[qtdCaminhoes];
            int somaKmsRota = Arrays.stream(rota).sum();

            //Ordena o array em ordem decrescente
            int[] rotaOrdenada = inverterArray(rota);


            for (int km : rotaOrdenada) {
                int caminhaoMenosKm = encontrarCaminhaoMenosKm(caminhoes);
                caminhoes[caminhaoMenosKm] += km;
            }

            System.out.println("-----Resultado Estratégia 2----");
            System.out.println("Soma de kms rota: " + somaKmsRota);
           System.out.println("Soma de kms caminhões: " + Arrays.stream(caminhoes).sum());
            System.out.println("Desvio padrão rota " + Util.calcularDesvioPadrao(caminhoes));
            System.out.println("Amplitude rota " + Util.calcularAmplitude(caminhoes));
            for (int i = 0; i < caminhoes.length; i++) {
                System.out.println("Qtd kms caminhão " + (i+1) + ": " + caminhoes[i]);
            }
        }
    }

    public static void executarGulosoMedia (List<int[]> rotas, int qtdCaminhoes) {
        // Estratégia 3 - Quilometragens em ordem crescente
        for (int[] rota : rotas) {
            System.out.println("Rota: " + Arrays.toString(rota));
            int[] caminhoes = new int[qtdCaminhoes];
            int somaKmsRota = Arrays.stream(rota).sum();
            int mediaKmsRota = somaKmsRota / qtdCaminhoes;

            //Ordena o array em ordem crescente
            //Arrays.sort(rota);

            //Ordena o array em ordem decrescente
            int[] rotaOrdenada = inverterArray(rota);

            int caminhaoAtual = 0;
            for (int km : rotaOrdenada) {
                if (caminhoes[caminhaoAtual] + km <= mediaKmsRota) {
                    caminhoes[caminhaoAtual] += km;
                } else {
                    if (caminhaoAtual != caminhoes.length - 1) caminhaoAtual++;
                    caminhoes[caminhaoAtual] += km;
                }

            }

            System.out.println("-----Resultado Estratégia 3----");
            System.out.println("Soma de kms rota: " + somaKmsRota);
            System.out.println("Média de kms rota: " + mediaKmsRota);
            System.out.println("Soma de kms caminhões: " + Arrays.stream(caminhoes).sum());
            System.out.println("Desvio padrão rota " + Util.calcularDesvioPadrao(caminhoes));
            System.out.println("Amplitude rota " + Util.calcularAmplitude(caminhoes));
            for (int i = 0; i < caminhoes.length; i++) {
                System.out.println("Qtd kms caminhão " + (i+1) + ": " + caminhoes[i]);
            }
        }
    }


    private static int encontrarCaminhaoMenosKm(int[] caminhoes) {
        int indiceCaminhaoComMenosKm = 0;
        int qtdKmsMenorCaminhao = Integer.MAX_VALUE;

        for (int i = 0; i < caminhoes.length; i++) {
            if (caminhoes[i] < qtdKmsMenorCaminhao) {
                indiceCaminhaoComMenosKm = i;
                qtdKmsMenorCaminhao = caminhoes[i];
            }
            //System.out.printf("Qtd caminhão %d: %d\n", i+1, caminhoes[i]);
        }
        //System.out.printf("Caminhão mais vazio com %d km em rotas: \n", caminhoes[indiceCaminhaoComMenosKm], indiceCaminhaoComMenosKm+1);
        return indiceCaminhaoComMenosKm;
    }

    private static int[] inverterArray(int[] array) {
        int[] sortedArray = Arrays.stream(array)
                .boxed()
                .sorted(Comparator.reverseOrder()) // just use 'sorted()' for ascending order
                .mapToInt(Integer::intValue)
                .toArray();

        return sortedArray;
    }
}
