import java.util.ArrayList;

public class Resultado {

    public int colecao;
    public double score;

    Resultado(int i, double v){
        this.colecao = i;
        this.score = v;
    }

    public static void quickSort(ArrayList<Resultado> resultados) {

        int n = resultados.size();
        qSortInterno(resultados, 0, n-1);

    }

    public static void qSortInterno(ArrayList<Resultado> resultados, int inicio, int fim) {
        Resultado pivot;
        Resultado temp;
        int i,j;

        if(fim - inicio > 0) {
            i = inicio;
            j = fim;
            pivot = resultados.get((i+j)/2);

            do {
                while(resultados.get(i).score < pivot.score) i++; /* procura por algum item do lado errado  >= pivot */
                while(resultados.get(j).score > pivot.score) j--; /* procura por algum item do lado errado <= pivot */
                if(i<= j) { /* deixa o igual para garantir que ao final i<j */
                    temp = resultados.get(i);
                    resultados.set(i,resultados.get(j));
                    resultados.set(j, temp);
                    i++; j--;
                }
            } while (i<=j);

            if(inicio < j) qSortInterno(resultados,inicio, j);
            if(i < fim) qSortInterno(resultados, i,fim);
        }
    }

    public static void imprimeResultados(ArrayList<Resultado> resultados){

        for(int i = 0; i < resultados.size(); i++){
            if(resultados.get(i).score > 0){
                System.out.printf("     >>>coleção = %d\n", resultados.get(i).colecao);
                System.out.printf("     >>>score = %f\n", resultados.get(i).score);
            }
        }
    }
}
