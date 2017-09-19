import java.util.ArrayList;
import java.util.Iterator;
public class Documento {

    int colecao;
    int idDoc;
    public ArrayList<String> titulo;
    public ArrayList<String> autor;
    public ArrayList<String> principaisAssuntos;
    public ArrayList<String> assuntosMN;

    public Documento(int colecao, int idDoc, ArrayList<String> titulo, ArrayList<String> autor, ArrayList<String> mj, ArrayList<String> mn){
        this.colecao = colecao;
        this.idDoc = idDoc;
        this.titulo = titulo;
        this.autor = autor;
        this.principaisAssuntos = mj;
        this.assuntosMN = mn;
    }
    public static void imprime(Documento doc){
        System.out.printf(" >>>colecao =  %d\n", doc.colecao);
        System.out.printf(" >>>ID =  %d\n", doc.idDoc);

        Iterator<String> aux = doc.titulo.iterator();
        Iterator<String> aux2 = doc.autor.iterator();
        Iterator<String> aux3 = doc.principaisAssuntos.iterator();
        Iterator<String> aux4 = doc.assuntosMN.iterator();

        while(aux.hasNext()){
            System.out.printf(" >>>titulo   = %s\n", aux.next());
        }
        while(aux2.hasNext()){
            System.out.printf(" >>>autor    = %s\n", aux2.next());
        }
        while(aux3.hasNext()){
            System.out.printf(" >>>MJ       = %s\n", aux3.next());
        }
        while(aux4.hasNext()){
            System.out.printf(" >>>MN       = %S\n", aux4.next());
        }

    }

}
