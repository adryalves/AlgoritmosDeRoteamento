package Model;

/* ***************************************************************
* Autor............: Adryellen Alves de Souza
* Matricula........: 202110189
* Inicio...........: 01/06/2024
* Ultima alteracao.: 06/06/2024
* Nome.............: PolylineMap
* Funcao...........: Essa classe eh responsavel por definir o objeto que sera usado para o hash, um atributo eh o caminho entre eles e o outro eh o custo
*************************************************************** */
import javafx.scene.shape.Polyline;

public class PolylineWithCost {
    public Polyline polyline;
    public int cost;

    public PolylineWithCost(Polyline polyline, int cost) {
        this.polyline = polyline;
        this.cost = cost;
    }

    public Polyline getPolyline() {
        return polyline;
    }

    public void setPolyline(Polyline polyline) {
        this.polyline = polyline;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

}