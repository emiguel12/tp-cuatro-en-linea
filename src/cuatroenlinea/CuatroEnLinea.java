package cuatroenlinea;

import java.util.Random;

public class CuatroEnLinea {

	private Casillero[][] casilleros;
	
	private String jugadorRojo;
	
	private String jugadorAmarillo;

	public CuatroEnLinea(int filas, int columnas, String jugadorRojo, String jugadorAmarillo) {

		casilleros = new Casillero[filas][columnas];
		for (int fila = 0; fila < filas; fila++) {

			for (int columna = 0; columna < columnas; columna++) {
				casilleros[fila][columna] = Casillero.VACIO;
			}
		}
		
		this.jugadorRojo = jugadorRojo;
		this.jugadorAmarillo = jugadorAmarillo;
	}

	public void soltarFicha(int columna) {

		casilleros[2][columna] = columna % 2 == 0 ? Casillero.ROJO : Casillero.AMARILLO;
	}

	public int contarFilas() {
		
		return casilleros.length;
	}

	public int contarColumnas() {
		
		return casilleros[0].length;
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

	public Casillero obtenerCasillero(int fila, int columna) {

		return casilleros[fila][columna];
	}
}
