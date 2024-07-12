package Model;

/* ***************************************************************
* Autor............: Adryellen Alves de Souza
* Matricula........: 202110189
* Inicio...........: 20/04/2024
* Ultima alteracao.: 28/04/2024
* Nome.............: HostThirdProtocol
* Funcao...........: Essa classe eh responsavel or implementar o metodo enviar mensagem da opcao 3 (com TTL)
*************************************************************** */

import java.util.ArrayList;
import java.util.List;
import Controller.Controller;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polyline;

public class HostThirdProtocol implements InterfaceProtocol {

    /*
     * ***************************************************************
     * Metodo: enviarMensagem
     * Funcao: enviar a mensagem checando TTL
     * Parametros: Host host, AnchorPane pane, Controller controller
     * Retorno: void
     */
    @Override
    public void enviarMensagem(Host host, AnchorPane pane, Controller controller) {
        Platform.runLater(() -> {
            for (Host vizinho : host.Adjacentes) { // envia msm para aquele que chegou
                if (host.TTL > 0) {
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
                            vizinho.TTL = p.TTL;
                            vizinho.ReceberMensagem();
                        }).start();
                    }
                }
            }
        });
    }

}
