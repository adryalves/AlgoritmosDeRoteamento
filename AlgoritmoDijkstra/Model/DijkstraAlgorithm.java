package Model;

/* ***************************************************************
* Autor............: Adryellen Alves de Souza
* Matricula........: 202110189
* Inicio...........: 06/05/2024
* Ultima alteracao.: 10/05/2024
* Nome.............: DijstraAlgorithm
* Funcao...........: Essa classe eh responsavel por realizar o algoritmo de dijkstra e escolhe o caminho mais curto para o destino
*************************************************************** */

import java.util.*;

import Controller.Controller;
import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;

public class DijkstraAlgorithm {

    Host hostOrigem;
    Host hostDestino;
    List<Host> nosCriados;
    AnchorPane pane;
    Controller c;

    int[] distancias = new int[12]; // dps mudar pra valor da quantidade de circulos
    Host[] previous = new Host[16];
    PriorityQueue<VertexDistance> priorityQueue = new PriorityQueue<>(new VertexDistanceComparator());
    List<Integer> custosTotais = new ArrayList<Integer>();

    public DijkstraAlgorithm(Host hostOrigem, List<Host> nosCriados, Host hostDestino, AnchorPane pane, Controller c) {
        this.hostOrigem = hostOrigem;
        this.nosCriados = nosCriados;
        this.hostDestino = hostDestino;
        this.pane = pane;
        this.c = c;

    }

    /*
     * ***************************************************************
     * Metodo: dijkstraRun
     * Funcao: ele executa o metodo que escolhe o menor caminho possivel
     * Parametros: void
     * Retorno: void
     */

    public void dijkstraRun() {

        for (Host host : nosCriados) {
            if (host == hostOrigem) {
                distancias[hostOrigem.idHost - 1] = 0;
                VertexDistance vd = new VertexDistance(hostOrigem, 0);
                priorityQueue.add(vd);
            } else {
                distancias[host.idHost - 1] = 99;
            }
            previous[host.idHost - 1] = null;

        }

        while (!priorityQueue.isEmpty()) {

            VertexDistance minElement = priorityQueue.poll();

            Host u = minElement.vertex;
            for (Host v : u.getAdjacentes()) {
                int alt = distancias[u.idHost - 1] + Controller.polylineMap.obterPolyline(u.idHost, v.idHost).cost;
                if (alt < distancias[v.idHost - 1]) {
                    distancias[v.idHost - 1] = alt;
                    previous[v.idHost - 1] = u;
                    VertexDistance vd = new VertexDistance(v, alt);
                    priorityQueue.add(vd);
                    // custosTotais.add(Controller.polylineMap.obterPolyline(u.idHost,
                    // v.idHost).cost);
                }

            }
        }

        List<Integer> Path = new ArrayList<Integer>();
        int step = hostDestino.idHost - 1;
        while (previous[step] != null) {
            Path.add(step + 1);
            step = previous[step].idHost - 1;
        }
        Path.add(hostOrigem.idHost);

        Collections.reverse(Path);

        for (int i = 0; i < Path.size() - 1; i++) {
            final int index = i;
            custosTotais.add(Controller.polylineMap.obterPolyline(Path.get(index), Path.get(index + 1)).cost);
            Platform.runLater(() -> {
                new Thread(() -> {
                    Packet p = new Packet(Path.get(index), Path.get(index + 1), pane, index);
                    p.start();
                    try {
                        Thread.sleep(1500);
                        // Controller.NosCriados.get(index + 1).pacoteRebecido = true;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();

            });
        }
        int soma = 0;
        for (Integer custo : custosTotais) {
            c.SetarCustoNaTela(custo);
            soma += custo;
        }
        c.SetarCustoTotalNaTela(soma);

        for (Integer host : Path) {

            c.SetarCaminhoTela(Controller.NosCriados.get(host - 1).idTela);
        }
        /*
         * for (int i = 0; i < Path.size() - 1; i++) {
         * Polyline line = Controller.polylineMap.obterPolyline(Path.get(i), Path.get(i
         * + 1)).getPolyline();
         * Polyline line2 = Controller.polylineMap.obterPolyline(Path.get(i + 1),
         * Path.get(i)).getPolyline();
         * 
         * line.setStroke(Color.GREEN);
         * line2.setStroke(Color.GREEN);
         * }
         */

    }

}