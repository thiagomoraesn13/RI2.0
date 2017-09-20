import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.*;

import static java.lang.Integer.parseInt;

public class Parser {

    public static int qtdDoc = 0;

    public static void lerArquivo(Vocabulario vocabulario) throws IOException{
        int k = 0;
        for(k=74; k<75; k++) {
            String nome = "Base/cf"+k;

            String linha[] = new String[2];
            String linha2[] = new String[2];
            String linha3[] = new String[2];
            ArrayList<String> titulo = null;
            ArrayList<String> autor = null;
            ArrayList<String> MJ = null;
            ArrayList<String> MN = null;
            String aux;
            int flag;

            BufferedReader arquivo = new BufferedReader((new FileReader((nome))));

            while (arquivo.ready()) {
                String line = arquivo.readLine();

                if (line.trim().length() == 0) {
                    //cria o novo documento
                    Documento docAux = new Documento(parseInt(linha[1]), parseInt(linha[1]), titulo, autor, MJ, MN);

                    Documento.imprime(docAux);
                    Parser.processaTermo(vocabulario, docAux);
                    Parser.qtdDoc++;

                    continue;
                }
                if (line.charAt(0) == 'P') {
                    linha = line.split(" ");
                }
                if (line.charAt(0) == 'R' && line.charAt(1) == 'N') {
                    linha2 = line.split(" ");
                }
                if (line.charAt(0) == 'A' && line.charAt(1) == 'U') {
                    aux = " ";
                    autor = new ArrayList<String>();
                    while (line.charAt(0) != 'T' && line.charAt(1) != 'I') {
                        aux = aux + line;
                        line = arquivo.readLine();
                    }
                    String aux2[];
                    aux2 = aux.split(" ");
                    int j;
                    for (j = 0; j < aux2.length; j++) {
                        if (aux2[j].length() != 0) {
                            aux2[j] = aux2[j].replace('.', '#');
                            String aux6[] = aux2[j].split("#");
                            autor.add(aux6[0]);
                        }
                    }
                }
                if (line.charAt(0) == 'T') {
                    aux = " ";
                    titulo = new ArrayList<String>();


                    while (line.charAt(0) != 'S' && line.charAt(1) != 'O') {
                        aux = aux + line;
                        line = arquivo.readLine();
                    }
                    String aux3[];
                    aux3 = aux.split(" ");

                    int l;
                    for (l = 0; l < aux3.length; l++) {
                        if (aux3[l].length() != 0) {
                            aux3[l] = aux3[l].replace('.', '#');
                            aux3[l] = aux3[l].replace(':', '#');
                            aux3[l] = aux3[l].replace(',', '#');
                            String aux6[] = aux3[l].split("#");
                            titulo.add(aux6[0]);
                        }
                    }
                }
                if (line.charAt(0) == 'M' && line.charAt(1) == 'J') {
                    MJ = new ArrayList<String>();
                    aux = "";
                    while (line.charAt(1) != 'N') {
                        aux = aux + line;
                        line = arquivo.readLine();
                    }
                    String aux4[];
                    aux4 = aux.split(" ");
                    int p;
                    for (p = 0; p < aux4.length; p++) {
                        if (aux4[p].length() != 0) {
                            aux4[p] = aux4[p].replace('.', '#');
                            aux4[p] = aux4[p].replace(':', '#');
                            aux4[p] = aux4[p].replace(',', '#');
                            String aux6[] = aux4[p].split("#");

                            MJ.add(aux6[0]);
                            //System.out.println(aux4[p]);
                        }
                    }
                }
                if (line.charAt(0) == 'M' && line.charAt(1) == 'N') {
                    MN = new ArrayList<String>();
                    aux = "";
                    while (line.charAt(0) != 'A'&& line.charAt(0) != 'E') {
                        aux = aux + line;
                        line = arquivo.readLine();
                    }
                    String aux5[];
                    aux5 = aux.split(" ");
                    int q;
                    for (q = 0; q < aux5.length; q++) {
                        if (aux5[q].length() != 0) {
                            aux5[q] = aux5[q].replace('.', '#');
                            aux5[q] = aux5[q].replace(':', '#');
                            aux5[q] = aux5[q].replace(',', '#');

                            String aux6[] = aux5[q].split("#");

                            MN.add(aux6[0]);
                        }
                    }

                }
            }
            arquivo.close();
        }
    }
    public static void processaTermo(Vocabulario vocabulario, Documento doc){
        String termo;
        //conjunto de iteradores para caminhar dentro do documento
        Iterator<String> titulo = doc.titulo.iterator();
        Iterator<String> autor = doc.autor.iterator();
        Iterator<String> mj = doc.principaisAssuntos.iterator();
        Iterator<String> mn = doc.assuntosMN.iterator();

        while(titulo.hasNext()){
            termo = titulo.next();
            //verifica se o termo já está no vocabulário
            if(!Vocabulario.verificaTermo(termo, vocabulario)){
                ListaInvertida aux = new ListaInvertida(termo, doc);
                vocabulario.listas.add(aux);    //adiciona a lista invertida no vocabulário
            }
        }

        while(autor.hasNext()){
            termo = autor.next();
            if(!Vocabulario.verificaTermo(termo, vocabulario)){
                ListaInvertida aux = new ListaInvertida(termo, doc);
                vocabulario.listas.add(aux);    //adiciona a lista invertida no vocabulário
            }
        }

        while(mj.hasNext()){
            termo = mj.next();
            if(!Vocabulario.verificaTermo(termo, vocabulario)){
                ListaInvertida aux = new ListaInvertida(termo, doc);
                vocabulario.listas.add(aux);    //adiciona a lista invertida no vocabulário
            }
        }

        while(mn.hasNext()){
            termo = mn.next();
            if(!Vocabulario.verificaTermo(termo, vocabulario)){
                ListaInvertida aux = new ListaInvertida(termo, doc);
                vocabulario.listas.add(aux);    //adiciona a lista invertida no vocabulário
            }
        }
    }

    public static int calculaFrequenciaNoDocumento(String termo, Documento documento){
        Iterator<String> titulo = documento.titulo.iterator();
        Iterator<String> autor = documento.autor.iterator();
        Iterator<String> mj = documento.principaisAssuntos.iterator();
        Iterator<String> mn = documento.assuntosMN.iterator();

        int freqDoc = 0;

        while(titulo.hasNext()){
            if(termo.toLowerCase().equals(titulo.next().toLowerCase())){
                freqDoc++;
            }
        }

        while(autor.hasNext()){
            String aux = autor.next().toLowerCase();
            if(termo.toLowerCase().equals(aux)){
                freqDoc++;
            }
        }
        while(mj.hasNext()){
            if(termo.toLowerCase().equals(mj.next().toLowerCase())){
                freqDoc++;
            }
        }
        while(mn.hasNext()) {
            String aux = mn.next().toLowerCase();
            if (termo.toLowerCase().equals(aux)) {
                freqDoc++;
            }
        }
        return freqDoc;
    }

}


