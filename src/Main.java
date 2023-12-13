import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        int qtdCaminhoes = 3;

        // Prepara rotas para apresentação
        List<int[]> rotasCaram = new ArrayList<>();
        int[] rota1 = {40, 36, 38, 29, 32, 28, 31, 35, 31, 30, 32, 30, 29, 39, 35, 38, 39, 35, 32, 38, 32, 33, 29, 33, 29, 39, 28};
        int[] rota2 = {32, 51, 32, 43, 42, 30, 42, 51, 43, 51, 29, 25, 27, 32, 29, 55, 43, 29, 32, 44, 55, 29, 53, 30, 24, 27};
        rotasCaram.add(rota1);
        rotasCaram.add(rota2);

        System.out.println("----- RESULTADOS RELATÓRIO ------");
        //Métodos para montar o relatório
        System.out.println("---- Relatório: Backtracking ----");
        Backtracking.executarSolucaoBacktracking(qtdCaminhoes);

        // Tamanho T máximo representa a quantidade de rotas que, no backtracking,
        // não ultrapassou 30 segundos.
        int tamanhoT = 22;

        //Métodos para montar o relatório

        System.out.println("---- Relatório: Guloso ----");
        SolucaoGuloso.executarSolucaoGulosa(tamanhoT, qtdCaminhoes);

        System.out.println("---- Relatório: Divisão e Conquista ----");
        DivisaoEConquista.executarDivisaoEConquista(qtdCaminhoes);

        System.out.println("---- Relatório: Programação Dinâmica ----");
        ProgDinamica.executarSolucaoProgDinamica(qtdCaminhoes);

        //Métodos que usam as rotas do Caram para a apresentação
//        System.out.println("----- RESULTADOS APRESENTAÇÃO ------");

//        System.out.println("---- Backtracking ----");
//        Backtracking.executarSolucaoBacktrackingApresentacao(rotasCaram, qtdCaminhoes);

//        System.out.println("---- Guloso ----");
//        SolucaoGuloso.executarSolucaoGulosaApresentacao(rotasCaram, qtdCaminhoes);
//
//        System.out.println("---- Divisão e Conquista ----");
//        DivisaoEConquista.executarDivisaoEConquistaApresentacao(rotasCaram, qtdCaminhoes);
//
//        System.out.println("---- Programação Dinâmica ----");
//        ProgDinamica.executarSolucaoProgDinamicaApresentacao(rotasCaram, qtdCaminhoes);



    }
}