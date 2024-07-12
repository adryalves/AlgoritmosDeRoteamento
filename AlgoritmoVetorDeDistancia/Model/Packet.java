package Model;

/* ***************************************************************
* Autor............: Adryellen Alves de Souza
* Matricula........: 202110189
* Inicio...........: 01/06/2024
* Ultima alteracao.: 06/06/2024
* Nome.............: Packet
* Funcao...........: Essa classe eh responsavelpor definir o objeto que representa o pacote que ira ser transmitido pela rede
*************************************************************** */

import java.time.Duration;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;
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
    public ImageView imageView;
    public int index;
    // PolylineMap polylineMap = new PolylineMap();

    public void run() {

        enviarMensagemTela(idNoInicial, idNoFinal);

    }

    /*
     * ***************************************************************
     * Metodo: enviarMensagemTela
     * Funcao: simula o pacote passando pelas linhas dos roteadores
     * Parametros: int idNoInicial, int idNoFinal
     * Retorno: void
     */
    public void enviarMensagemTela(int idNoInicial, int idNoFinal) {

        if (Controller.polylineMap.obterPolyline(idNoInicial, idNoFinal) == null) {
            return;
        }
        Polyline polyline = Controller.polylineMap.obterPolyline(idNoInicial, idNoFinal).getPolyline();
        Polyline polylineReverse = Controller.polylineMap.obterPolyline(idNoFinal, idNoInicial).getPolyline();

        try {
            sleep(index * 1500);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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

            // polyline.setStroke(Color.GREEN);

            pathTransition.setOnFinished(event -> {
                pane.getChildren().remove(imageView);
                polyline.setStroke(Color.GREEN);
                polylineReverse.setStroke(Color.GREEN);

            });

            pane.getChildren().addAll(imageView);
            pathTransition.play();
        });

    }

    public Packet(int idNoInicial, int idNoFinal, AnchorPane pane, int index) {
        this.idNoInicial = idNoInicial;
        this.idNoFinal = idNoFinal;
        this.pane = pane;
        this.index = index;

    }

}
