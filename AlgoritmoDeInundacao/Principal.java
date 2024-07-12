/* ***************************************************************
* Autor............: Adryellen Alves de Souza
* Matricula........: 202110189
* Inicio...........: 20/04/2024
* Ultima alteracao.: 28/04/2024
* Nome.............: Principa
* Funcao...........: eh a classe que executa o programa
*************************************************************** */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import Controller.Controller;

public class Principal extends Application {
    Controller c = new Controller();

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("View/TelaInundacao.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false); // para nao ser possivel aumentar de tamanho
        stage.setTitle("Flooding Algorithm"); // para declarar o titulo do
        // programa
        scene.getStylesheets().add("View/style.css"); // Aqui eu adiciono o arquivo
        // css no meu projeto
        stage.getIcons().add(new Image("View/icon.png"));

        // Adicionando o Pane à cena

        stage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });

        stage.show(); // rodar e iniciar a tela

    }

    public static void main(String[] args) {
        launch(args);

    }
}
