package Controller;

/* ***************************************************************
* Autor............: Adryellen Alves de Souza
* Matricula........: 202110189
* Inicio...........: 06/05/2024
* Ultima alteracao.: 10/05/2024
* Nome.............: Controller
* Funcao...........: Essa classe eh responsavel por controlar os itens da interface usando dados e classes do modelo
*************************************************************** */

import java.io.IOException;
import java.net.URL;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import Model.DijkstraAlgorithm;
import Model.Host;
import Model.Packet;
import Model.PolylineMap;
import Model.PolylineWithCost;
import Model.ReadFile;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.geometry.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.scene.*;

public class Controller implements Initializable {

    @FXML
    private Circle bolinha;
    Color cor = Color.web("#ff1f87");

    // private String[] opcoes = { "Enviar para todos", "Enviar para todos com
    // exceccao", "Protocolo com TTL" };
    // @FXML
    // private ChoiceBox<String> escolha;
    @FXML
    private ChoiceBox<String> destino;

    @FXML
    private ChoiceBox<String> origem;

    @FXML
    private AnchorPane pane;

    private double valorInicialX = 402;
    private double valorInicialY = 136;
    public static List<Host> NosCriados = new ArrayList<Host>();
    public static List<Circle> circulosList = new ArrayList<Circle>();
    int id = 0;
    int valor = 1;
    @FXML
    private Label custoCaminho;
    String[] letrasNos = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
            "S",
            "T", "U", "V", "W", "Y", "Z" };

    Color[] cores = { Color.DARKTURQUOISE, Color.HOTPINK, Color.ORANGERED, Color.MEDIUMPURPLE, Color.FUCHSIA,
            Color.NAVAJOWHITE };

    // Color.BlueViolet, Color.ALICEBLUE

    List<Polyline> polylines = new ArrayList<Polyline>();
    public static PolylineMap polylineMap = new PolylineMap();

    public static int versaoEscolhida;

    @FXML
    private Button inicio;
    @FXML
    private Label contPacote;

    public static int contPacoteint = 0;

    public static boolean chegouDestino = false;

    public ArrayList<Circle> nosDaTela = new ArrayList<Circle>();

    public ArrayList<Label> labels = new ArrayList<Label>();

    @FXML
    private Circle no1;

    @FXML
    private Circle no10;

    @FXML
    private Circle no11;

    @FXML
    private Circle no12;

    @FXML
    private Circle no2;

    @FXML
    private Circle no3;

    @FXML
    private Circle no4;

    @FXML
    private Circle no5;

    @FXML
    private Circle no6;

    @FXML
    private Circle no7;

    @FXML
    private Circle no8;

    @FXML
    private Circle no9;

    @FXML
    private Label label1;

    @FXML
    private Label label10;

    @FXML
    private Label label11;

    @FXML
    private Label label12;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

    @FXML
    private Label label4;

    @FXML
    private Label label5;

    @FXML
    private Label label6;

    @FXML
    private Label label7;

    @FXML
    private Label label8;

    @FXML
    private Label label9;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // TODO Auto-generated method stub
        Collections.addAll(nosDaTela, no1, no2, no3, no4, no5, no6, no7, no8, no9, no10, no11, no12);

        Collections.addAll(labels, label1, label2, label3, label4, label5, label6, label7, label8, label9, label10,
                label11, label12);

        // spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(5,
        // 20, 5));

        // escolha.getItems().addAll(opcoes);
        // escolha.setValue("Enviar para todos");

        // pane.heightProperty().addListener((obs, oldHeight, newHeight) -> {
        criarCirculosDinamicamente();
        // });

    }

    private void criarCirculosDinamicamente() {

        ReadFile rf = new ReadFile();
        List<String> info = rf.ReadingFile();
        String quantidadeCirculosStr = info.get(0).replace(";", "");
        int quantidadeCirculos = Integer.parseInt(quantidadeCirculosStr);

        for (int i = 0; i < quantidadeCirculos; i++) {
            origem.getItems().add(letrasNos[i]);
            destino.getItems().add(letrasNos[i]);
        }

        origem.setValue("A");
        destino.setValue(letrasNos[quantidadeCirculos - 1]);

        ArrayList<Integer> numeros = new ArrayList<Integer>();

        // Loop para criar e adicionar os círculos ao AnchorPane

        for (int i = 0; i < quantidadeCirculos; i++) {
            ConstruirCirculo(i);
        }

        for (int i = 1; i < info.size(); i++) {

            String[] partes = info.get(i).split(";");
            int noInicial = Integer.parseInt(partes[0]);
            int noFinal = Integer.parseInt(partes[1]);
            int peso = Integer.parseInt(partes[2]);

            Path path = new Path();
            path.setStrokeWidth(2); // Defina a largura da linha conforme necessario
            path.setFill(Color.MEDIUMPURPLE);

            Circle circleInicial = circulosList.get(noInicial - 1);
            Circle circleFinal = circulosList.get(noFinal - 1);

            double startX = circleInicial.getCenterX();
            double startY = circleInicial.getCenterY();

            // double intermediaryY = circulosList.get(noInicial - 1 + 1).getCenterY() + 75;

            double endX = circleFinal.getCenterX();
            double endY = circleFinal.getCenterY();

            NosCriados.get(noInicial - 1).Adjacentes.add(NosCriados.get(noFinal - 1));

            // msm os pais sao adjacentes
            NosCriados.get(noFinal - 1).Adjacentes.add(NosCriados.get(noInicial - 1));

            System.out.println("Host " + (noInicial) + " ( " + NosCriados.get(noInicial - 1).idTela + " )"
                    + " conectado a Host " + (noFinal) + " ( " + NosCriados.get(noFinal - 1).idTela + " )" +
                    " com custo: " + peso);

            Polyline polyline = new Polyline();
            polyline.setStrokeWidth(3); // Defina a largura da linha conforme necessario
            polyline.setStroke(Color.MEDIUMPURPLE); // Define a cor da linha

            polyline.getPoints().addAll(startX, startY, endX, endY);

            Polyline polyline2 = new Polyline();
            polyline.setStrokeWidth(3); // Defina a largura da linha conforme necessario
            // polyline.setStroke(Color.MEDIUMPURPLE);

            polylineMap.adicionarPolyline(noInicial, noFinal, polyline, peso);

            // double intermediaryYInvertido = circulosList.get(noFinal - 2).getCenterY() +
            // 75;

            polyline2.getPoints().addAll(endX, endY, startX, startY);

            polylineMap.adicionarPolyline(noFinal, noInicial, polyline2, peso);

            // polylines.add(polyline);

            pane.getChildren().add(polyline);

            Label labelPeso = new Label();

            labelPeso.setText(peso + "");

            double midX = (startX + endX) / 2;
            double midY = (startY + endY) / 2;

            // Define as coordenadas do Label com base no meio da polyline
            labelPeso.setLayoutX(midX - labelPeso.getWidth() / 2 - 5);
            labelPeso.setLayoutY(midY - labelPeso.getHeight() / 2 - 20);
            labelPeso.setFont(new Font(24));
            labelPeso.setTextFill(cor);
            labelPeso.toFront();
            pane.getChildren().add(labelPeso);

        }

        // Packet p = new Packet(0, 0, polylines.get(0), pane);
        // p.start();
    }

    int index = 0;

    public void ConstruirCirculo(int i) {

        /*
         * Circle circle = new Circle();
         * circle.setRadius(24); // Define o raio do círculo
         * circle.setFill(cor); // Define a cor de preenchimento do círculo
         * double x = valorInicialX;
         * double y = valorInicialY;
         * // valorInicialY += 18;
         * circle.setCenterX(x);
         * circle.setCenterY(y);
         * circle.setLayoutX(0);
         * circle.setLayoutY(0);
         * valorInicialX += 96;
         * valorInicialY += 80;
         */

        // valorInicialY += Math.floor(Math.random() * 50) + 1;
        // Adicione o círculo ao AnchorPane

        circulosList.add(nosDaTela.get(i));
        nosDaTela.get(i).setVisible(true);
        // pane.getChildren().add(circle);
        NosCriados.add(new Host(id + 1, letrasNos[id], valorInicialX,
                valorInicialY, pane, this));

        // Label label = new Label();
        // label.setLayoutX(nosDaTela.get(i).getCenterX() + 5);
        // label.setLayoutY(nosDaTela.get(i).getCenterY() - 50);
        // label.setLayoutY(y - 40);
        labels.get(i).setText(NosCriados.get(index).getIdTela());
        labels.get(i).setFont(new Font(19));
        index++;
        id++;
        labels.get(i).setVisible(true);
        // pane.getChildren().add(label);
    }

    public void SetarValorNatela(int valor) {

        Platform.runLater(() -> {
            contPacote.setText(valor + "");
        });
    }

    public int MapearNos() {
        int res = 0;
        for (int i = 0; i < letrasNos.length; i++) {
            res++;
            if (origem.getValue() == letrasNos[i]) {
                break;
            }

        }
        return res;
    }

    public int MapearNosDestino() {
        String x = destino.getValue();
        int res = 0;
        for (int i = 0; i < letrasNos.length; i++) {
            res++;
            if (destino.getValue() == letrasNos[i]) {
                break;
            }

        }
        return res;
    }

    public void SetarCaminhoTela(String caminhoTela) {
        Platform.runLater(() -> {
            if (contPacote.getText().isEmpty()) {
                contPacote.setText("" + caminhoTela);
            } else {
                contPacote.setText(contPacote.getText() + " -> " + caminhoTela);
            }
        });
    }

    public void SetarCustoNaTela(int custo) {
        Platform.runLater(() -> {
            if (custoCaminho.getText().isEmpty()) {
                custoCaminho.setText("" + custo);
            } else {
                custoCaminho.setText(custoCaminho.getText() + "  +  " + custo);
            }
        });
    }

    public void SetarCustoTotalNaTela(int custo) {
        Platform.runLater(() -> {
            custoCaminho.setText(custoCaminho.getText() + " = " + custo);
        });
    }

    @FXML
    void iniciar(ActionEvent event) {
        // int valorSpinner = spinner.getValue();
        // contPacote.setText(0 + "");
        contPacoteint = 0;
        contPacote.setText("");
        custoCaminho.setText("");
        for (Host no : NosCriados) {
            // no.TTL = valorSpinner;
            no.Pai = null;
        }
        // versaoEscolhida = VersaoEscolhida();

        int idMaquina = MapearNos() - 1;
        Host origem = NosCriados.get(idMaquina);

        int idMaquinaDestino = MapearNosDestino() - 1;
        Host destinoHost = NosCriados.get(idMaquinaDestino);

        List<PolylineWithCost> todasPolylines = polylineMap.obterTodasPolylines();

        for (PolylineWithCost line : todasPolylines) {
            line.polyline.setStroke(Color.MEDIUMPURPLE);
        }

        // Chamar enviarMensagem dentro do Platform.runLater para garantir que ele seja
        // executado na thread da interface do usuario
        Platform.runLater(() -> {
            DijkstraAlgorithm da = new DijkstraAlgorithm(origem, NosCriados, destinoHost, pane, this);
            da.dijkstraRun();
        });

    }
}
