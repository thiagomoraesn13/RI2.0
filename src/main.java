import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class main {

    public static void main(String args[]) throws IOException {
        Vocabulario vocabulario = new Vocabulario();
        ArrayList<Resultado> resultados = new ArrayList<Resultado>();

        System.out.println("Executando o Parser, favor aguarde ...");
        Parser.lerArquivo(vocabulario);
        System.out.println("Calculando o tf e idf dos termos, favor aguarde ...");
        Vocabulario.frequenciaColecao(vocabulario);
        System.out.println("Criando os vetores da busca, favor aguarde ...");
        ProcessadorBuscas.criaVetores(vocabulario);
        System.out.println("concluido!");

        System.out.printf("Digite sua consulta \n");
        Scanner scan = new Scanner(System.in);
        Documento consulta = new Documento(0,0,"Hoiby-N");


        //cria vetor da consulta
        ProcessadorBuscas.criaVetorConsulta(vocabulario, consulta);

        //Calculando a similaridade
        resultados = ProcessadorBuscas.sim(vocabulario, consulta);
        Resultado.quickSort(resultados);
        Resultado.imprimeResultados(resultados);


//        Vocabulario.imprimeVocabulario(vocabulario);
    }
    
}
