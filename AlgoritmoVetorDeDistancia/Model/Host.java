package Model;

/* ***************************************************************
* Autor............: Adryellen Alves de Souza
* Matricula........: 202110189
* Inicio...........: 01/06/2024
* Ultima alteracao.: 06/06/2024
* Nome.............: Host
* Funcao...........: Essa classe eh responsavel por definir o objeto host seus atributos e metodos
*************************************************************** */
import java.util.ArrayList;
import java.util.List;

import Controller.Controller;
import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polyline;

public class Host {
    public int idHost;
    public String idTela;
    public double layoutX;
    public double layoutY;
    public List<Host> Adjacentes;
    public Host Pai;
    AnchorPane pane;
    public Controller c;
    public RoutingTable routingTable;

    // private InterfaceProtocol mensagemStrategy;

    public int getIdHost() {
        return idHost;
    }

    public void setIdHost(int idHost) {
        this.idHost = idHost;
    }

    public String getIdTela() {
        return idTela;
    }

    public void setIdTela(String idTela) {
        this.idTela = idTela;
    }

    public double getLayoutX() {
        return layoutX;
    }

    public void setLayoutX(double layoutX) {
        this.layoutX = layoutX;
    }

    public double getLayoutY() {
        return layoutY;
    }

    public void setLayoutY(double layoutY) {
        this.layoutY = layoutY;
    }

    public List<Host> getAdjacentes() {
        return Adjacentes;
    }

    public void setAdjacentes(List<Host> adjacentes) {
        Adjacentes = adjacentes;
    }

    public Host(int idHost, String idTela, double layoutX, double layoutY, AnchorPane pane, Controller c) {
        this.idHost = idHost;
        this.idTela = idTela;
        this.layoutX = layoutX;
        this.layoutY = layoutY;
        this.pane = pane;
        this.Adjacentes = new ArrayList<>();
        this.c = c;
        this.routingTable = new RoutingTable();
    }

    public Host(int idHost, String idTela, double layoutX, double layoutY, AnchorPane pane, Controller c,
            RoutingTable routingTable) {
        this.idHost = idHost;
        this.idTela = idTela;
        this.layoutX = layoutX;
        this.layoutY = layoutY;
        this.pane = pane;
        this.Adjacentes = new ArrayList<>();
        this.c = c;
        this.routingTable = new RoutingTable();

    }

    public void addAdjacente(Host adjacente) {
        this.Adjacentes.add(adjacente);
    }

    public Host getPai() {
        return Pai;
    }

    public void setPai(Host pai) {
        Pai = pai;
    }

    public void initializeRoutingTable() {
        for (Host neighbor : Adjacentes) {
            int delay = Controller.polylineMap.obterPolyline(idHost, neighbor.idHost).cost;
            routingTable.addRoute(String.valueOf(neighbor.idHost), String.valueOf(neighbor.idHost), delay);
        }
        routingTable.addRoute(String.valueOf(idHost), String.valueOf(idHost), 0); // Add self route
    }

    // if (this.Pai != null) {
    // Packet p = new Packet(this.idHost, this.Pai.idHost, pane);
    // p.start();
    // Pai.ReceberMensagem();
    // }

}