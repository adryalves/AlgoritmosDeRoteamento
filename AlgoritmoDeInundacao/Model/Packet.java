package Model;

/* ***************************************************************
* Autor............: Adryellen Alves de Souza
* Matricula........: 202110189
* Inicio...........: 20/04/2024
* Ultima alteracao.: 28/04/2024
* Nome.............: Packet
* Funcao...........: Essa classe eh responsavelpor definir o objeto que representa o pacote que ira ser transmitido pela rede
*************************************************************** */

import java.time.Duration;

import Controller.Controller;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polyline;

public class Packet extends Thread {

    public int idNoInicial;
    public int idNoFinal;
    public Polyline caminho;
    public AnchorPane pane;
    public int TTL;
    public ImageView imageView;
    // PolylineMap polylineMap = new PolylineMap();

    public void run() {

        if (TTL > 0) {
            TTL--;
            enviarMensagemTela(idNoInicial, idNoFinal);
        }

    }

    /*
     * ***************************************************************
     * Metodo: enviarMensagemTela
     * Funcao: simula o pacote passando pelas linhas dos roteadores
     * Parametros: int idNoInicial, int idNoFinal
     * Retorno: void
     */
    public void enviarMensagemTela(int idNoInicial, int idNoFinal) {

        Polyline polyline = Controller.polylineMap.obterPolyline(idNoInicial, idNoFinal);

        Platform.runLater(() -> {

            PathTransition pathTransition = new PathTransition();

            pathTransition.setPath(polyline);
            pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathTransition.setCycleCount(0);
            pathTransition.setAutoReverse(false);
            pathTransition.setDuration(new javafx.util.Duration(1500)); // Define a duracao da animacao
            ImageView imageView = new ImageView(new Image("View/carta.png"));
            imageView.setFitWidth(40);
            imageView.setFitHeight(40);
            pathTransition.setNode(imageView);

            pathTransition.setOnFinished(event -> {
                pane.getChildren().remove(imageView); // Remove a ImageView do AnchorPane apos a transicao terminar
            });

            pane.getChildren().addAll(imageView);
            pathTransition.play();
        });

    }

    public Packet(int idNoInicial, int idNoFinal, AnchorPane pane, int TTL) {
        this.idNoInicial = idNoInicial;
        this.idNoFinal = idNoFinal;
        this.pane = pane;
        this.TTL = TTL;
    }

}
