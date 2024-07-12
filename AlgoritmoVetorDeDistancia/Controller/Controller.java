package Controller;

/* ***************************************************************
* Autor............: Adryellen Alves de Souza
* Matricula........: 202110189
* Inicio...........: 01/06/2024
* Ultima alteracao.: 05/06/2024
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
import Model.DistanceVectorAlgorithm;
import Model.Host;
import Model.Packet;
import Model.PolylineComplete;
import Model.PolylineMap;
import Model.PolylineWithCost;
import Model.ReadFile;
import Model.RoutingTable;
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
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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

    public static boolean estaExecutando = false;

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

    @FXML
    private Button buttonReiniciar;

    private List<PolylineComplete> polylinesRemovidas = new ArrayList<PolylineComplete>();

    private EventHandler<MouseEvent> mouseEnteredHandler;
    private EventHandler<MouseEvent> mouseExitedHandler;
    private EventHandler<MouseEvent> mouseClickedHandler;

    Paint corAntiga;

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

        DistanceVectorAlgorithm dvAlgorithm = new DistanceVectorAlgorithm(NosCriados);

        dvAlgorithm.initializeRoutingTables();

        new Thread(() -> {
            try {
                dvAlgorithm.updateRoutingTables();
                while (!dvAlgorithm.isConverged()) {
                    Thread.sleep(100);
                }
                // Apos a convergencia, habilite o envio de pacotes
                Platform.runLater(this::enablePacketSending);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Platform.runLater(() -> {
            avisos("1. Assim que se inicia, a rede eh criada e as tabelas de roteamento sao inicialiazadas junto\n2. Para desabilitar uma aresta, "
                    +
                    "passe por cima dela e quando ela estiver da cor vermelha clique exatamente em cima, e assim ela sera removida, "
                    +
                    "\n3. Sempre que apertar o botao de iniciar, primeiramente eh feita a atualizacao das tabelas caso alguma aresta tenha sido removida, "
                    +
                    "\n4. O botao de reiniciar vai fazer a rede voltar a ser como no inicio, ou seja reabilita todas as arestas que foram removidas "
                    +
                    "a funcao dele eh apenas essa, para executar novamente apenas aperte o botao de iniciar que ira executar de acordo o estado da rede "
                    +
                    "\n5. Quando o pacote esta sendo enviado nao eh possivel desabilitar a aresta pelo qual o pacote esta passando ou passara, as outras sim "
                    +
                    "\n6. Por envolver muitas acoes, pode haver delays quando passa por cima da aresta, por isso se passar e nao ficar direto "
                    +
                    "vermelho apenas mexa mais um pouco \n7. Se voce excluir todas as arestas que levam a origem ao destino, nao sera possivel "
                    +
                    " o pacote transitar, por isso se isso ocorrer ou pode ocorrer um erro ou pode ser que o programa so nao consiga avancar"
                    +
                    "\n8. O caminho percorrido pelo pacote eh sinalizado pela cor verde");

        });
    }

    private void enablePacketSending() {
        // Habilite os elementos da GUI para permitir o envio de pacotes
        // sendPacketButton.setDisable(false);
    }

    private void sendPacket(int origemId, int destinoId) {
        Host origem = getHostById(origemId);
        Host destino = getHostById(destinoId);
        if (origem != null && destino != null) {
            new Thread(() -> {
                enviarPacote(origem, destino);
            }).start();
        }
    }

    private void enviarPacote(Host origem, Host destino) {
        // int count = 0;
        Host current = origem;
        while (!current.equals(destino)) {

            RoutingTable.Route route = current.routingTable.getRoute(String.valueOf(destino.idHost));
            if (route == null)
                break; // Nenhuma rota encontrada

            Host nextHop = getHostById(Integer.parseInt(route.nextHop));
            if (nextHop == null)
                break; // Proximo salto nao encontrado

            Host finalCurrent = current;

            // Simule o envio do pacote

            Platform.runLater(() -> {
                Packet packet = new Packet(finalCurrent.idHost, nextHop.idHost, pane, 0);
                packet.run();
            });

            current = nextHop;
            try {
                Thread.sleep(1500); // Simule o atraso
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        estaExecutando = false;
        List<PolylineWithCost> todasPolylines = polylineMap.obterTodasPolylines();

        for (int i = 0; i < todasPolylines.size(); i = i + 2) {
            atualizarEstadoEventos(todasPolylines.get(i).getPolyline());
        }
    }

    private Host getHostById(int id) {
        for (Host host : NosCriados) {
            if (host.idHost == id) {
                return host;
            }
        }
        return null;
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

        // Loop para criar e adicionar os circulos ao AnchorPane

        for (int i = 0; i < quantidadeCirculos; i++) {
            ConstruirCirculo(i);
        }

        for (int i = 1; i < info.size(); i++) {

            String[] partes = info.get(i).split(";");
            int noInicial = Integer.parseInt(partes[0]);
            int noFinal = Integer.parseInt(partes[1]);
            int peso = Integer.parseInt(partes[2]);
            int pesoContrario = Integer.parseInt(partes[3]);

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
                    " com custo: " + peso + " e custo: " + pesoContrario + " na volta");

            Polyline polyline = new Polyline();
            polyline.setStrokeWidth(3); // Defina a largura da linha conforme necessario
            polyline.setStroke(Color.MEDIUMPURPLE); // Define a cor da linha

            polyline.getPoints().addAll(startX, startY, endX, endY);

            Polyline polyline2 = new Polyline();
            polyline2.setStrokeWidth(3); // Defina a largura da linha conforme necessario

            polylineMap.adicionarPolyline(noInicial, noFinal, polyline, peso);

            polyline2.getPoints().addAll(endX, endY, startX, startY);

            polylineMap.adicionarPolyline(noFinal, noInicial, polyline2, pesoContrario);

            pane.getChildren().add(polyline);

            PolylineComplete polylineInfo = new PolylineComplete(startX, startY, endX, endY, noInicial, noFinal,
                    polyline, peso, polyline2, pesoContrario);

            configurandoEventos(polyline, polyline2, polylineInfo);

            habilitarEventosDeMouse(polyline);
            // habilitarEventosDeMouse(polyline2);

            Label labelPeso = new Label();

            labelPeso.setText(peso + " : " + pesoContrario);

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

    private void habilitarEventosDeMouse(Polyline polyline) {
        polyline.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEnteredHandler);
        polyline.addEventHandler(MouseEvent.MOUSE_EXITED, mouseExitedHandler);
        polyline.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseClickedHandler);

        // polyline2.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEnteredHandler);
        // polyline2.addEventHandler(MouseEvent.MOUSE_EXITED, mouseExitedHandler);
        // polyline2.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseClickedHandler);
    }

    private void desabilitarEventosDeMouse(Polyline polyline) {
        polyline.removeEventHandler(MouseEvent.MOUSE_ENTERED, mouseEnteredHandler);
        polyline.removeEventHandler(MouseEvent.MOUSE_EXITED, mouseExitedHandler);
        polyline.removeEventHandler(MouseEvent.MOUSE_CLICKED, mouseClickedHandler);

        // polyline2.removeEventHandler(MouseEvent.MOUSE_ENTERED, mouseEnteredHandler);
        // polyline2.removeEventHandler(MouseEvent.MOUSE_EXITED, mouseExitedHandler);
        // polyline2.removeEventHandler(MouseEvent.MOUSE_CLICKED, mouseClickedHandler);
    }

    private void atualizarEstadoEventos(Polyline polyline) {
        // PolylineWithCost polylineReserva = polylineMap.obterPolyline(idHost,
        // idHost2);
        if (estaExecutando) {
            desabilitarEventosDeMouse(polyline);
        } else {
            habilitarEventosDeMouse(polyline);
        }
    }

    public void configurandoEventos(Polyline polyline, Polyline polyline2, PolylineComplete polylineInfo) {
        Paint corAntiga = polyline.getStroke();
        mouseEnteredHandler = event -> {
            polyline.setStroke(Color.RED);
            // polyline2.setStroke(Color.RED);
        };

        mouseExitedHandler = event -> {
            polyline.setStroke(Color.MEDIUMPURPLE);
            // polyline2.setStroke(Color.MEDIUMPURPLE);
        };

        mouseClickedHandler = event -> {
            desabilitarAresta(polyline, polyline2, polylineInfo);

        };
    }

    private void desabilitarAresta(Polyline polyline, Polyline polyline2, PolylineComplete polylineInfo) {
        // Remova a polyline do AnchorPane
        pane.getChildren().remove(polyline);

        pane.getChildren().remove(polyline2);

        // Atualize o PolylineMap para remover a polyline
        Controller.polylineMap.removerPolyline(polyline);
        Controller.polylineMap.removerPolyline(polyline2);

        polylinesRemovidas.add(polylineInfo);
        // Recalcule as tabelas de roteamento
        recalcularTabelasDeRoteamento();
    }

    private void recalcularTabelasDeRoteamento() {
        for (Host host : NosCriados) {
            host.routingTable = new RoutingTable();
        }

        inicializarTabelasDeRoteamento();
        realizarTrocasDeTabelasDeRoteamento();
    }

    // Metodo para inicializar as tabelas de roteamento
    private void inicializarTabelasDeRoteamento() {
        for (Host host : NosCriados) {
            for (Host vizinho : host.getAdjacentes()) {
                if (Controller.polylineMap.obterPolyline(host.idHost, vizinho.idHost) != null) {
                    int custo = Controller.polylineMap.obterPolyline(host.idHost, vizinho.idHost).getCost();
                    host.routingTable.updateRoute(String.valueOf(vizinho.idHost),
                            new RoutingTable.Route(String.valueOf(vizinho.idHost), custo));
                }
            }
        }
    }

    // Metodo para realizar trocas de tabelas de roteamento ate que todas estejam
    // atualizadas
    private void realizarTrocasDeTabelasDeRoteamento() {
        boolean tabelasAtualizadas;
        do {
            tabelasAtualizadas = false;
            for (Host host : NosCriados) {
                for (Host vizinho : host.getAdjacentes()) {
                    boolean atualizada = host.routingTable.updateRoutes(vizinho.routingTable, vizinho.idHost);
                    if (atualizada) {
                        tabelasAtualizadas = true;
                    }
                }
            }
        } while (tabelasAtualizadas);
    }

    int index = 0;

    public void ConstruirCirculo(int i) {

        /*
         * Circle circle = new Circle();
         * circle.setRadius(24); // Define o raio do circulo
         * circle.setFill(cor); // Define a cor de preenchimento do circulo
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
        // Adicione o circulo ao AnchorPane

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
    void reiniciar(ActionEvent event) {
        for (PolylineComplete polylineRemovida : polylinesRemovidas) {

            Polyline polyline = new Polyline();
            polyline.setStrokeWidth(3); // Defina a largura da linha conforme necessario
            polyline.setStroke(Color.MEDIUMPURPLE); // Define a cor da linha

            polyline.getPoints().addAll(polylineRemovida.getStartX(), polylineRemovida.getStartY(),
                    polylineRemovida.getEndX(), polylineRemovida.getEndY());

            polylineMap.adicionarPolyline(polylineRemovida.getNoInicial(), polylineRemovida.getNoFinal(),
                    polylineRemovida.getPolyline(), polylineRemovida.getPeso());

            Polyline polyline2 = new Polyline();
            polyline2.setStrokeWidth(3); // Defina a largura da linha conforme necessario
            // polyline2.setStroke(Color.MEDIUMPURPLE); // Define a cor da linha

            polyline2.getPoints().addAll(polylineRemovida.getEndX(), polylineRemovida.getEndY(),
                    polylineRemovida.getStartX(), polylineRemovida.getStartY());

            polylineMap.adicionarPolyline(polylineRemovida.getNoFinal(), polylineRemovida.getNoInicial(),
                    polylineRemovida.getPolyline2(), polylineRemovida.getPesoContrario());

            if (!pane.getChildren().contains(polylineRemovida.getPolyline())) {
                pane.getChildren().add(polylineRemovida.getPolyline());
            }

            PolylineComplete polylineInfo = new PolylineComplete(polylineRemovida.getStartX(),
                    polylineRemovida.getStartY(),
                    polylineRemovida.getEndX(), polylineRemovida.getEndY(), polylineRemovida.getNoInicial(),
                    polylineRemovida.getNoFinal(), polylineRemovida.getPolyline(), polylineRemovida.getPeso(),
                    polylineRemovida.getPolyline2(), polylineRemovida.getPesoContrario());

            configurandoEventos(polyline, polyline2, polylineInfo);
            habilitarEventosDeMouse(polyline);
            // habilitarEventosDeMouse(polyline2);

            List<PolylineWithCost> todasPolylines = polylineMap.obterTodasPolylines();

            recalcularTabelasDeRoteamento();
        }
        polylinesRemovidas.clear();
    }

    @FXML
    void iniciar(ActionEvent event) {
        // int valorSpinner = spinner.getValue();
        // contPacote.setText(0 + "");
        estaExecutando = true;
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

        for (int i = 0; i < todasPolylines.size(); i = i + 2) {
            desabilitarEventosDeMouse(todasPolylines.get(i).getPolyline());
        }

        sendPacket(origem.idHost, destinoHost.idHost);

    }

    public void avisos(String conteudo) {
        Alert errorAlert = new Alert(AlertType.INFORMATION); // Instancia o objeto errorAlert
        errorAlert.setHeaderText("Informacoes"); // Usando um metodo proprio desse objeto, ele passa como
        // parametro o que sera exibido no titulo da mensagem de erro
        errorAlert.setContentText(conteudo);
        errorAlert.showAndWait(); // chama o metodo erroAlert para mostrar essa mensagem de erro
    }
}
