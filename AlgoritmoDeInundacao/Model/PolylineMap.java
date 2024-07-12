package Model;

/* ***************************************************************
* Autor............: Adryellen Alves de Souza
* Matricula........: 202110189
* Inicio...........: 20/04/2024
* Ultima alteracao.: 28/04/2024
* Nome.............: PolylineMap
* Funcao...........: Essa classe eh responsavel por definir um hash que liga dois ids que repsentam os host a uma poluline que eh justamente o caminho entre esses hoss
*************************************************************** */

import java.util.HashMap;
import java.util.Map;

import javafx.scene.shape.Polyline;

public class PolylineMap {
    private Map<String, Polyline> polylineMap;

    public PolylineMap() {
        polylineMap = new HashMap<>();
    }

    /*
     * ***************************************************************
     * Metodo: adicionarPolyline
     * Funcao: adiciona uma chave referentes aos host que representa a polyline
     * Parametros: int idHost1, int idHost2, Polyline polyline
     * Retorno: void
     */
    public void adicionarPolyline(int idHost1, int idHost2, Polyline polyline) {
        String chave = gerarChave(idHost1, idHost2);
        polylineMap.put(chave, polyline);
    }

    /*
     * ***************************************************************
     * Metodo: obterPolyline
     * Funcao: resgata a polyline referentes ao hosts
     * Parametros: int idHost1, int idHost2, Polyline polyline
     * Retorno: void
     */

    public Polyline obterPolyline(int idHost1, int idHost2) {
        String chave = gerarChave(idHost1, idHost2);
        return polylineMap.get(chave);
    }

    private String gerarChave(int idHost1, int idHost2) {
        return idHost1 + "-" + idHost2;
    }
}
