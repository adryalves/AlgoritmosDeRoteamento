package Model;
/* ***************************************************************
* Autor............: Adryellen Alves de Souza
* Matricula........: 202110189
* Inicio...........: 06/05/2024
* Ultima alteracao.: 10/05/2024
* Nome.............: VertexDistance
* Funcao...........: Essa classe eh responsavel por definir um objeto que sera da priorityqueue, um eh o vertice e o outro eh o custo
*************************************************************** */

public class VertexDistance {
    Host vertex;
    int distance;

    public VertexDistance(Host vertex, int distance) {
        this.vertex = vertex;
        this.distance = distance;
    }
}
