import java.util.ArrayList;
import java.util.Iterator;

public class Vocabulario {

    ArrayList<ListaInvertida> listas;

    public Vocabulario(){
        this.listas = new ArrayList<ListaInvertida>();
    }
    public static void imprimeVocabulario(Vocabulario vocabulario){
        Iterator<ListaInvertida> listainvertida = vocabulario.listas.iterator();
        while(listainvertida.hasNext()){
            ListaInvertida atual = listainvertida.next();
            System.out.printf("Frequencia   = %s\n", atual.frequenciaCol);
            System.out.printf("Termo        = %s\n", atual.termo);
            System.out.printf("Idf          = %f\n ", atual.idf);
            ListaInvertida.imprimeListaInvertida(atual);
        }
    }
    public static boolean verificaTermo(String palavra,Vocabulario vocabulario){

        //caminhando no vocabulário
        Iterator<ListaInvertida> aux = vocabulario.listas.iterator();


        while(aux.hasNext()){
            if(palavra.equals(aux.next().termo)){
                return true;
            }
        }
        return false;
    }

    //calcula o idf e a quantidade de vezes que um termo aparece na coleção
    public static void frequenciaColecao(Vocabulario vocabulario){

        int i = 0;
        Iterator<ListaInvertida> iteratorListas = vocabulario.listas.iterator();

        ListaInvertida atual;

        while (iteratorListas.hasNext()){

            //pega cada termo do vocabulário
            atual = iteratorListas.next();

            //calcula a frequencia do termo na coleção
            atual.frequenciaCol = frequenciaTermoColecao(atual.termo, vocabulario);

            //caclcula o idf do termo
            atual.idf = ProcessadorBuscas.Idf(Parser.qtdDoc, atual.frequenciaCol);

            //remove o elemento antigo na posição i
            vocabulario.listas.set(i, atual);
            i++;
        }
    }

    public static int frequenciaTermoColecao(String termo, Vocabulario vocabulario){

        int freqCol = 0;

        Iterator<ListaInvertida> iteradorListas = vocabulario.listas.iterator();


        //Arraylist de int documentos
        ArrayList<Integer> idDoc = new ArrayList<Integer>();

        //caminhando no vocabulário
        while (iteradorListas.hasNext()){

            Iterator<Documento> documento = iteradorListas.next().documentos.iterator();

            //caminhando nas listas invertidas
            while (documento.hasNext()) {
                //recupera o documento atual da lista invertida
                Documento docAtual = documento.next();

                //se o documento não estiver no arraylist de documentos, então o documento não foi verificado
                if (!idDoc.contains(docAtual.colecao)){

                    //guarda o documento, para que não seja calculado duas vezes
                    idDoc.add(docAtual.colecao);

                    //calcula a frequencia do termo em cada documento
                    freqCol = freqCol + Parser.calculaFrequenciaNoDocumento(termo, docAtual);
                }
            }
        }
        return freqCol;
    }

}
