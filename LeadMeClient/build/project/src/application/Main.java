package application;
	
import java.io.IOException;

import fabricas.Fabrica;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
		CarregadorTelaLogin carregadorTelaLogin = Fabrica.getInstance().getFactory().createCarregadorTelaLogin();
        Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/fxml/"+carregadorTelaLogin.coletarNomeTelaLogin()));
			Scene scene = new Scene(root);
	        //Teste para criar as tabelas
	        stage.setTitle("Lead Me - Login");
	        stage.setScene(scene);
	        stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
