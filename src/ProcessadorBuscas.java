import java.util.ArrayList;
import java.util.Iterator;

public class ProcessadorBuscas {


    //calcula o idf
    public static double Idf(int qtdDocumentos, int ocorrenciaCol){
        double aux = (double)qtdDocumentos/(double)ocorrenciaCol;
        return Math.log(aux);
    }

    //caclucla o idf*tf
    public static double tfIdf(double idf, int tf){
        return idf*tf;
    }


    // função que calcula a similaridade parcial de dois documentos
    public static double simParcial(Documento doc, Documento consulta){
        double prodInterno = 0;

        for(int i = 0; i < doc.vetDoc.length; i++){
            prodInterno = prodInterno + doc.vetDoc[i]*consulta.vetDoc[i];
        }

        return prodInterno;
    }

    public static ArrayList<Resultado> sim(Vocabulario vocabulario, Documento consulta){

        double acumuladores[] = new double[vocabulario.listas.size()];
        int j = 0;
        //caminhando nas listas invertidas
        for(int i = 0; i < vocabulario.listas.size(); i++){

            //caminhando nos documentos
            while(j < vocabulario.listas.get(i).documentos.size()){
                acumuladores[i] = simParcial(vocabulario.listas.get(i).documentos.get(j), consulta);
                j++;
            }
            j = 0;
        }

        ArrayList<Integer> idDoc = new ArrayList<Integer>();

        ArrayList<Resultado> resultados = new ArrayList<Resultado>();
        Resultado temp = null;
        for(j = 0; j < vocabulario.listas.size(); j++){
            for(int k = 0; k < vocabulario.listas.get(j).documentos.size(); k++){

                //se o documento não estiver no arraylist de documentos, então o documento não foi verificado
                if (!idDoc.contains(vocabulario.listas.get(j).documentos.get(k).colecao)) {

                    //guarda o documento, para que não seja calculado duas vezes
                    idDoc.add(vocabulario.listas.get(j).documentos.get(k).colecao);
                    temp = new Resultado(vocabulario.listas.get(j).documentos.get(k).colecao, acumuladores[j]/(vocabulario.listas.get(j).documentos.get(k).normaVet * consulta.normaVet));
                }
            }
            resultados.add(temp);
        }
        return resultados;
    }


    public static void criaVetores(Vocabulario vocabulario){
        Iterator<ListaInvertida> listas =  vocabulario.listas.iterator();

        //caminhando no vocabulario e recuperando as listas invertidas
        while (listas.hasNext()){

            Iterator<Documento> documentos = listas.next().documentos.iterator();

            //recupera os documentos
            while (documentos.hasNext()){

                //cria os vetores de busca
                criaVetorDeBuscas(vocabulario, documentos.next());
            }
        }

    }

    public static void criaVetorDeBuscas(Vocabulario vocabulario, Documento documento) {

        int tamVoc = vocabulario.listas.size();
        int i = 0;

        double vetDoc[] = new double[tamVoc]; // cria vetor do processamento de buscas

        //caminhando no vocabulário
        while (i < tamVoc){
            int freqAux = Parser.calculaFrequenciaNoDocumento(vocabulario.listas.get(i).termo, documento);
            vetDoc[i] = ProcessadorBuscas.tfIdf(vocabulario.listas.get(i).idf, freqAux);
            i++;
        }


        //norma do vetor
        double norma = 0.0;
        for(i = 0; i < vetDoc.length; i++){
            norma += Math.pow(vetDoc[i], 2.0);
        }

        documento.setNormaVet(Math.sqrt(norma));
        documento.setVetDoc(vetDoc);
    }




    public static void criaVetorConsulta(Vocabulario vocabulario, Documento consulta) {

        int tamVoc = vocabulario.listas.size();
        int i = 0;

        double vetDoc[] = new double[tamVoc];

        while (i < tamVoc) {
            if (consulta.consulta.toLowerCase().equals(vocabulario.listas.get(i).termo.toLowerCase())) {
                int freqAux = 1;
                vetDoc[i] = ProcessadorBuscas.tfIdf(vocabulario.listas.get(i).idf, freqAux);
                break;
            }
            i++;
        }

        consulta.setNormaVet(1.0);
        consulta.setVetDoc(vetDoc);
    }

}