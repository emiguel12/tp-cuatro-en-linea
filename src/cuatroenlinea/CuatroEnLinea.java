package cuatroenlinea;

import java.util.Random;

public class CuatroEnLinea {

	private Posicion[][] posiciones;
	
	private String jugadorRojo;
	
	private String jugadorAmarillo;

	public CuatroEnLinea(int filas, int columnas, String jugadorRojo, String jugadorAmarillo) {

		posiciones = new Posicion[filas][columnas];
		for (int fila = 0; fila < filas; fila++) {

			for (int columna = 0; columna < columnas; columna++) {
				posiciones[fila][columna] = Posicion.VACIO;
			}
		}
		
		this.jugadorRojo = jugadorRojo;
		this.jugadorAmarillo = jugadorAmarillo;
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
		
		return (new Random()).nextBoolean() ? jugadorAmarillo : jugadorRojo;
	}

	public Posicion obtenerPosicion(int fila, int columna) {

		return posiciones[fila][columna];
	}
}
