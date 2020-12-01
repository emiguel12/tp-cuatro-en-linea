package juego;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Aplicación del juego Cuatro en Lí­nea.
 * 
 * Punto de entrada del programa.
 * 
 */
public class Aplicacion extends Application {

	public static final String TITULO = "Cuatro en Lí­nea";
	
	private GridPane grilla;
	
	private TextField campoNombreJugadorUno;
	private TextField campoNombreJugadorDos;

	private TextField campoColumnas;
	private TextField campoFilas;
	
	private Button colorJugadorUno;
	private Button colorJugadorDos;
	private Button botonIniciar;
	
	public static String colorUno = "Rojo";
	public  static String colorDos = "Amarillo";
	public String nombreJugadorRojo;
	public String nombreJugadorAmarillo;

	@Override
	public void start(Stage escenarioPrincipal) {

		crearGrilla();

		Scene escena = new Scene(grilla, 400, 300);
		escenarioPrincipal.setScene(escena);
		escenarioPrincipal.setTitle(TITULO);
		escenarioPrincipal.show();
	}

	private void crearGrilla() {

		grilla = new GridPane();
		grilla.setAlignment(Pos.CENTER);
		grilla.setHgap(20);
		grilla.setVgap(20);
		
		Text textoTitulo = new Text(TITULO);
		textoTitulo.setFont(new Font(16));
		

		
		crearControles();

		grilla.add(textoTitulo, 0, 0, 2, 1);
		grilla.add(new Label("Color del Jugador"), 2, 0);
		grilla.add(new Label("Jugador Uno"), 0, 1);
		grilla.add(campoNombreJugadorUno, 1, 1);
		grilla.add(colorJugadorUno, 2, 1);
		grilla.add(new Label("Jugador Dos"), 0, 2);
		grilla.add(campoNombreJugadorDos, 1, 2);
		grilla.add(colorJugadorDos, 2, 2);
		grilla.add(new Label("Filas"), 0, 3);
		grilla.add(campoFilas, 1, 3);
		grilla.add(new Label("Columnas"), 0, 4);
		grilla.add(campoColumnas, 1, 4);
		grilla.add(botonIniciar, 0, 5, 2, 1);
		
		GridPane.setHalignment(botonIniciar, HPos.CENTER);
		GridPane.setHalignment(textoTitulo, HPos.CENTER);
	}

	private void crearControles() {

		campoNombreJugadorUno = new TextField("Uno");
		campoNombreJugadorDos = new TextField("Dos");
		
		campoColumnas = new TextField("7");
		campoFilas = new TextField("7");
		
		botonIniciar = new Button("Iniciar");
		botonIniciar.setOnAction(new IniciarJuego(this));
		
		colorJugadorUno = new Button(colorUno);
		colorJugadorUno.setOnAction(new CambiarColores(this));
		colorJugadorDos = new Button(colorDos);
		colorJugadorDos.setOnAction(new CambiarColores(this));
	}
	
	/**
	 * post: crea un juego CuatroEnLinea, lo asocia a una Tablero 
	 * 		 y comienza su ejecución.
	 * 
	 */
	public void iniciar(String nombreJugadorRojo, String nombreJugadorAmarillo) {
		
		obtenerNombres();
		int filas = Integer.parseInt(campoFilas.getText());
		int columnas = Integer.parseInt(campoColumnas.getText());
		
		CuatroEnLinea juego = new CuatroEnLinea(filas, columnas, 
												nombreJugadorRojo, nombreJugadorAmarillo);
		
		Tablero tablero = new Tablero(juego);
		tablero.mostrar();
	}
	
	public static void main(String[] args) {
		
		Thread.setDefaultUncaughtExceptionHandler(new MostrarError());
		
		launch(args);
	}
	
	public void AlternarColores(){
		if(colorUno == "Rojo"){
			colorJugadorUno.setText("Amarillo");
			colorUno = "Amarillo";
			colorJugadorDos.setText("Rojo"); 
			colorDos = "Rojo";
		}else if (colorUno == "Amarillo"){
			colorJugadorUno.setText("Rojo");
			colorUno = "Rojo";
			colorJugadorDos.setText("Amarillo"); 
			colorDos = "Amarillo";
			
		}	
	}
	
	private void obtenerNombres(){
		nombreJugadorRojo = (colorUno == "Rojo") ? campoNombreJugadorUno.getText() : campoNombreJugadorDos.getText();
		nombreJugadorAmarillo = (colorDos == "Rojo") ? campoNombreJugadorDos.getText() : campoNombreJugadorUno.getText();
	}
}
