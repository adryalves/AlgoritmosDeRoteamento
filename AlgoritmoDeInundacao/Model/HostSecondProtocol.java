package Model;

/* ***************************************************************
* Autor............: Adryellen Alves de Souza
* Matricula........: 202110189
* Inicio...........: 20/04/2024
* Ultima alteracao.: 28/04/2024
* Nome.............: HostSecondProtocol
* Funcao...........: Essa classe eh responsavel por implementar o metodo enviar mensagem da opcao e em que envia para todos nos adjacentes
*************************************************************** */

import java.util.ArrayList;
import java.util.List;
import Controller.Controller;
import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polyline;

public class HostSecondProtocol implements InterfaceProtocol {

    /*
     * ***************************************************************
     * Metodo: enviarMensagem
     * Funcao: enviar a mensagem para todos nos exceto pelo que chegou
     * Parametros: Host host, AnchorPane pane, Controller controller
     * Retorno: void
     */
    @Override
    public void enviarMensagem(Host host, AnchorPane pane, Controller controller) {
        Platform.runLater(() -> {
            for (Host vizinho : host.Adjacentes) {
                if (vizinho != host.Pai) {
                    new Thread(() -> {
                        Packet p = new Packet(host.idHost, vizinho.idHost, pane, host.TTL);
                        p.start();
                        if (!Controller.chegouDestino) {
                            Controller.contPacoteint++;
                            controller.SetarValorNatela(Controller.contPacoteint);
                        }
                        vizinho.Pai = host;
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        vizinho.ReceberMensagem();
                    }).start();
                }
            }
        });
    }

}
