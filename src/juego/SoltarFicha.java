package juego;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SoltarFicha implements EventHandler<ActionEvent> {

	private Tablero tablero;
	private CuatroEnLinea juego;
	private int columna;

	public SoltarFicha(Tablero tableroCuatroEnLinea, CuatroEnLinea cuatroEnLinea, int columnaSeleccionada) {

		tablero = tableroCuatroEnLinea;
		juego = cuatroEnLinea; 
		columna = columnaSeleccionada;
	}

	@Override
	public void handle(ActionEvent evento) {

		juego.soltarFicha(columna);
		
		tablero.dibujar();
		
		if (juego.termino()) {
			
			tablero.mostrarResultado();
		}
	}
}
