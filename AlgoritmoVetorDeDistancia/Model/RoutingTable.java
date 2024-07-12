package Model;

/* ***************************************************************
* Autor............: Adryellen Alves de Souza
* Matricula........: 202110189
* Inicio...........: 01/06/2024
* Ultima alteracao.: 05/06/2024
* Nome.............: Controller
* Funcao...........: Essa classe eh responsavel por criar a tabela de roteamento
*************************************************************** */

import java.util.HashMap;
import java.util.Map;

public class RoutingTable {
    private Map<String, Route> table;

    public RoutingTable() {
        this.table = new HashMap<>();
    }

    public void addRoute(String destination, String nextHop, int delay) {
        table.put(destination, new Route(nextHop, delay));
    }

    public void updateRoute(String destination, Route route) {
        table.put(destination, route);
    }

    public Route getRoute(String destination) {
        return table.get(destination);
    }

    public Map<String, Route> getTable() {
        return table;
    }

    public boolean updateRoutes(RoutingTable neighborTable, int neighborId) {
        boolean updated = false;
        Route neighborRouteToSelf = getRoute(String.valueOf(neighborId));

        if (neighborRouteToSelf == null) {
            // Se nao existe rota para o neighborId, ignore este vizinho
            return false;
        }

        int neighborDelay = neighborRouteToSelf.delay;

        for (Map.Entry<String, Route> entry : neighborTable.table.entrySet()) {
            String destination = entry.getKey();
            Route neighborRoute = entry.getValue();
            int newCost = neighborRoute.delay + neighborDelay;

            if (!table.containsKey(destination) || table.get(destination).delay > newCost) {
                table.put(destination, new Route(String.valueOf(neighborId), newCost));
                updated = true;
            }
        }

        return updated;
    }

    public static class Route {
        public String nextHop;
        public int delay;

        public Route(String nextHop, int delay) {
            this.nextHop = nextHop;
            this.delay = delay;
        }
    }
}
