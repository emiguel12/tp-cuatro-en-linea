package cuatroenlinea;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Tablero extends Application {

	private static final int ALTO_FILA = 80;
	private static final int ANCHO_COLUMNA = 80;
	private static final int ALTURA_BOTON = 40;
	private static final double RADIO = Math.min(ALTO_FILA - 1, ANCHO_COLUMNA - 1) / 2;
	
	private CuatroEnLinea juego;
	private GridPane grilla;
	private Stage ventana;

	@Override
	public void start(Stage primaryStage) {

		ventana = primaryStage;
		
		int filas = 7;
		int columnas = 7;
		Tablero tablero = this;
		juego = new CuatroEnLinea(filas, columnas, "Jugador 1", "Jugador 2");

		grilla = new GridPane();
		
		for (int columna = 0; columna < columnas; columna++) {

			Button botonSoltarFicha = new Button("Soltar");
			botonSoltarFicha.setMinHeight(ALTURA_BOTON);

			botonSoltarFicha.setOnAction(new SoltarFicha(tablero, juego, columna));
			botonSoltarFicha.setMinWidth(ANCHO_COLUMNA);
			grilla.add(botonSoltarFicha, columna, 0);
		}

		double ancho = columnas * ANCHO_COLUMNA;
		double alto = (filas * ALTO_FILA) + ALTURA_BOTON;
		
		Scene scene = new Scene(grilla, ancho, alto);

		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Cuatro en línea");
		dibujar();

		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void dibujar() {

		for (int fila = 0; fila < juego.contarFilas(); fila++) {

			for (int columna = 0; columna < juego.contarColumnas(); columna++) {

				Posicion posicion = juego.obtenerPosicion(fila, columna);
				
				Circle dibujoCasillero = new Circle(RADIO, obtenerPintura(posicion));
				
				dibujoCasillero.setStroke(new Color(0.5, 0.5, 0.5, 1.0));
				dibujoCasillero.setScaleX(0.95);
				dibujoCasillero.setScaleY(0.95);
				
				grilla.add(dibujoCasillero, columna, fila + 1);
			}
		}
	}

	private Paint obtenerPintura(Posicion posicion) {

		Paint pintura;

		switch (posicion) {
			case AMARILLO:
				pintura = Color.YELLOW;
				break;
			case ROJO:
				pintura = Color.RED;
				break;
			default:
				pintura = Color.WHITE;
		}

		return pintura;
	}

	public void mostrarResultado() {

		Stage dialogo = new Stage();
		
		BorderPane panelGanador = new BorderPane();
		panelGanador.setPadding(new Insets(10.0));
		Text textoResultado;
		Font fuente = new Font(40.0);
		
		if (juego.hayGanador()) {
		
			textoResultado = new Text("Ganó el jugador " + juego.obtenerGanador());
			
		} else {
			
			textoResultado = new Text("Hubo empate");
		}
		
		textoResultado.setFont(fuente);
		panelGanador.setCenter(textoResultado);
		
		Scene escenaGanador = new Scene(panelGanador);
		
		dialogo.setScene(escenaGanador);
		dialogo.initOwner(ventana);
		dialogo.initModality(Modality.WINDOW_MODAL);
		dialogo.setResizable(false);
		
		dialogo.showAndWait();
	}
}
