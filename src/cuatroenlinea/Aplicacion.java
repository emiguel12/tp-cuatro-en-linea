package cuatroenlinea;

import javafx.application.Application;
import javafx.stage.Stage;

public class Aplicacion extends Application {

	
	@Override
	public void start(Stage escenarioPrincipal) {

		CuatroEnLinea juego = new CuatroEnLinea(7, 7, "Jugador 1", "Jugador 2");
		
		Tablero tablero = new Tablero(juego);
		tablero.mostrar();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
