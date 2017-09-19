import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.Iterator;

public class ListaInvertida {

    String termo;
    int frequenciaCol;
    double idf;
    ArrayList<Documento> documentos;

    public ListaInvertida(String termo, Documento doc){
        this.termo = termo;
        this.documentos = new ArrayList<Documento>();
        this.documentos.add(doc);

    }
    public static void imprimeListaInvertida(ListaInvertida lista){
        Iterator<Documento> iterator = lista.documentos.iterator();
        while(iterator.hasNext()){
            Documento atual = iterator.next();
            Documento.imprime(atual);
        }
    }

}
