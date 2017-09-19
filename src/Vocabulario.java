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


}
