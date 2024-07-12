package Model;
/* ***************************************************************
* Autor............: Adryellen Alves de Souza
* Matricula........: 202110189
* Inicio...........: 20/04/2024
* Ultima alteracao.: 28/04/2024
* Nome.............: InterfaceProtocol
* Funcao...........: Essa classe eh responsavel por definir a interface que sera implementada pelas classes de host
*************************************************************** */

import Controller.Controller;
import javafx.scene.layout.AnchorPane;

public interface InterfaceProtocol {

    void enviarMensagem(Host host, AnchorPane pane, Controller controller);

}
