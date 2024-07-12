package Model;

/* ***************************************************************
* Autor............: Adryellen Alves de Souza
* Matricula........: 202110189
* Inicio...........: 01/06/2024
* Ultima alteracao.: 06/06/2024
* Nome.............: ReadFile
* Funcao...........: Essa classe eh responsavel por ler o arquivo backbone.text e salvar dados numa lista que sera usada para construir a rede
*************************************************************** */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFile {

    public ReadFile() {

    }

    /*
     * ***************************************************************
     * Metodo: ReadingFile
     * Funcao: ler um arquivo txt e insere dados numa lista
     * Parametros: void
     * Retorno: lista<String>
     */
    public List<String> ReadingFile() {
        try {

            List<String> conexoes = new ArrayList<String>();
            File arquivo = new File("backbone.txt");
            Scanner scanner = new Scanner(arquivo);
            while (scanner.hasNext()) {
                String item = scanner.next();
                conexoes.add(item);

            }
            scanner.close();

            return conexoes;

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo nao encontrado.");
            e.printStackTrace();
            return new ArrayList<String>();
        }

    }
}
