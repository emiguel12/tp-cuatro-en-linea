package cuatroenlinea;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Aplicacion extends Application {

	private TextField campoNombreJugadorRojo;
	private TextField campoNombreJugadorAmarillo;

	@Override
	public void start(Stage escenarioPrincipal) {

		GridPane grilla = new GridPane();
		grilla.setAlignment(Pos.CENTER);
		grilla.setHgap(20);
		grilla.setVgap(20);
		
		campoNombreJugadorRojo = new TextField();
		campoNombreJugadorAmarillo = new TextField();
		Button botonIniciar = new Button("Iniciar");
		botonIniciar.setOnAction(new Iniciar(this));
		
		grilla.add(new Label("Jugador Rojo"), 0, 1);
		grilla.add(campoNombreJugadorRojo, 1, 1);
		grilla.add(new Label("Jugador Amarillo"), 0, 2);
		grilla.add(campoNombreJugadorAmarillo, 1, 2);
		grilla.add(botonIniciar, 0, 6, 2, 1);
		GridPane.setHalignment(botonIniciar, HPos.CENTER);

		Scene escena = new Scene(grilla, 400, 300);
		escenarioPrincipal.setScene(escena);
		escenarioPrincipal.show();
	}
	
	public void iniciar() {
		
		String nombreJugadorRojo = campoNombreJugadorRojo.getText();
		String nombreJugadorAmarillo = campoNombreJugadorAmarillo.getText();
		
		CuatroEnLinea juego = new CuatroEnLinea(7, 7, 
												nombreJugadorRojo,
												nombreJugadorAmarillo);
		
		Tablero tablero = new Tablero(juego);
		tablero.mostrar();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
