package cuatroenlinea;

import java.util.Random;

public class CuatroEnLinea {

	Posicion[][] posiciones;

	public CuatroEnLinea(int filas, int columnas) {

		posiciones = new Posicion[filas][columnas];
		for (int fila = 0; fila < filas; fila++) {

			for (int columna = 0; columna < columnas; columna++) {
				posiciones[fila][columna] = Posicion.VACIO;
			}
		}

	}

	public void soltarFicha(int columna) {

		posiciones[2][columna] = columna % 2 == 0 ? Posicion.ROJO : Posicion.AMARILLO;
	}

	public int contarFilas() {
		
		return posiciones.length;
	}

	public int contarColumnas() {
		
		return posiciones[0].length;
	}

	public boolean termino() {
		
		return (new Random()).nextBoolean();
	}

	public boolean hayGanador() {
		
		return (new Random()).nextBoolean();
	}

	public String obtenerGanador() {
		
		return "jugador 1";
	}

	public Posicion obtenerPosicion(int fila, int columna) {

		return posiciones[fila][columna];
	}
}
