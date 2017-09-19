import java.io.IOException;

public class main {

    public static void main(String args[]) throws IOException {
        Vocabulario vocabulario = new Vocabulario();

        Parser.lerArquivo(vocabulario);

        Vocabulario.imprimeVocabulario(vocabulario);


    }
    
}
