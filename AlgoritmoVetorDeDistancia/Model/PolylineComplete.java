package Model;

/* ***************************************************************
* Autor............: Adryellen Alves de Souza
* Matricula........: 202110189
* Inicio...........: 01/06/2024
* Ultima alteracao.: 06/06/2024
* Nome.............: Packet
* Funcao...........: Essa classe eh responsavel por definir todos os atributos da polyline
*************************************************************** */

import javafx.scene.shape.Polyline;

public class PolylineComplete {
    double startX;
    double startY;
    double endX;
    double endY;
    int noInicial;
    int noFinal;
    Polyline polyline;
    int peso;
    Polyline polyline2;
    int pesoContrario;

    public PolylineComplete(double startX, double startY, double endX, double endY, int noInicial, int noFinal,
            Polyline polyline, int peso, Polyline polyline2, int pesoContrario) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.noInicial = noInicial;
        this.noFinal = noFinal;
        this.polyline = polyline;
        this.peso = peso;
        this.polyline2 = polyline2;
        this.pesoContrario = pesoContrario;

    }

    public double getStartX() {
        return startX;
    }

    public double getStartY() {
        return startY;
    }

    public double getEndX() {
        return endX;
    }

    public double getEndY() {
        return endY;
    }

    public int getNoInicial() {
        return noInicial;
    }

    public int getNoFinal() {
        return noFinal;
    }

    public Polyline getPolyline() {
        return polyline;
    }

    public int getPeso() {
        return peso;
    }

    public Polyline getPolyline2() {
        return polyline2;
    }

    public int getPesoContrario() {
        return pesoContrario;
    }

}
