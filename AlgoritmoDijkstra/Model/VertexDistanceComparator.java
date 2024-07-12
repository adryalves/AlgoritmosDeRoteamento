package Model;

/* ***************************************************************
* Autor............: Adryellen Alves de Souza
* Matricula........: 202110189
* Inicio...........: 06/05/2024
* Ultima alteracao.: 10/05/2024
* Nome.............: VertexdistanceComparator
* Funcao...........: Essa classe eh responsavel por implementar o metodo que retorna o menor elemento da priority queue baseado na distancia de cada um
*************************************************************** */
import java.util.Comparator;

class VertexDistanceComparator implements Comparator<VertexDistance> {
    public int compare(VertexDistance vd1, VertexDistance vd2) {
        return Integer.compare(vd1.distance, vd2.distance);
    }
}
