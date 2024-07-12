package Model;

/* ***************************************************************
* Autor............: Adryellen Alves de Souza
* Matricula........: 202110189
* Inicio...........: 01/06/2024
* Ultima alteracao.: 05/06/2024
* Nome.............: Controller
* Funcao...........: Essa classe eh responsavel por fazer o metodo que vetor de distancia
*************************************************************** */

import java.util.List;
import java.util.Map;
import Controller.Controller;

public class DistanceVectorAlgorithm {
    private List<Host> hosts;
    private Boolean converged = false;
    // private final int T; //

    public DistanceVectorAlgorithm(List<Host> hosts) {
        this.hosts = hosts;

    }

    public void initializeRoutingTables() {
        for (Host host : hosts) {
            host.initializeRoutingTable();
        }
    }

    public void updateRoutingTables() {
        // boolean updated;
        // updated = false;
        for (Host host : hosts) {
            for (Host neighbor : host.getAdjacentes()) {
                RoutingTable neighborTable = neighbor.routingTable;
                for (Map.Entry<String, RoutingTable.Route> entry : neighborTable.getTable().entrySet()) {
                    String destination = entry.getKey();
                    int newDelay = entry.getValue().delay
                            + Controller.polylineMap.obterPolyline(host.idHost, neighbor.idHost).cost;
                    RoutingTable.Route currentRoute = host.routingTable.getRoute(destination);
                    if (currentRoute == null || newDelay < currentRoute.delay) {
                        host.routingTable.addRoute(destination, String.valueOf(neighbor.idHost), newDelay);
                        // updated = true;
                    }
                }
            }
        }
        converged = true;
    }

    public boolean isConverged() {
        return converged;
    }
}
