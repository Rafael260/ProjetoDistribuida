package application;
	
import java.io.IOException;
import java.net.URL;

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
			URL resource = getClass().getClassLoader().getResource("fxml/"+carregadorTelaLogin.coletarNomeTelaLogin());
			System.out.println("Resource: " + resource);
			root = FXMLLoader.load(resource);
			Scene scene = new Scene(root);
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
