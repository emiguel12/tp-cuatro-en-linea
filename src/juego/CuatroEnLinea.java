package juego;

/**
 * Juego Cuatro en Lí­nea
 * 
 * Reglas:
 * 
 * 		...
 *
 */
public class CuatroEnLinea {
	
	private Casillero[][] tablero;
	private String jugadorRojo;
	private String jugadorAmarillo;

	/**
	 * pre : 'filas' y 'columnas' son mayores o iguales a 4.
	 * post: empieza el juego entre el jugador que tiene fichas rojas, identificado como 
	 * 		 'jugadorRojo' y el jugador que tiene fichas amarillas, identificado como
	 * 		 'jugadorAmarillo'. 
	 * 		 Todo el tablero está vacío.
	 * 
	 * @param filas : cantidad de filas que tiene el tablero.
	 * @param columnas : cantidad de columnas que tiene el tablero.
	 * @param jugadorRojo : nombre del jugador con fichas rojas.
	 * @param jugadorAmarillo : nombre del jugador con fichas amarillas.
	 */
	public CuatroEnLinea(int filas, int columnas, String jugadorRojo, String jugadorAmarillo) {

		this.crearTablero(filas, columnas);
		
		this.jugadorAmarillo = jugadorAmarillo;
		this.jugadorRojo = jugadorRojo;
	}

	/**
	 * post: devuelve la cantidad máxima de fichas que se pueden apilar en el tablero.
	 */
	public int contarFilas() {
		
		return this.tablero.length;
	}

	/**
	 * post: devuelve la cantidad máxima de fichas que se pueden alinear en el tablero.
	 */
	public int contarColumnas() {
		
		return this.tablero[0].length;
	}

	/**
	 * pre : fila está en el intervalo [1, contarFilas()],
	 * 		 columnas está en el intervalo [1, contarColumnas()].
	 * post: indica qué ocupa el casillero en la posición dada por fila y columna.
	 * 
	 * @param fila
	 * @param columna
	 */
	public Casillero obtenerCasillero(int fila, int columna) {
		
		return tablero[fila-1][columna-1];
	}
	
	/**
	 * pre : el juego no terminó, columna está en el intervalo [1, contarColumnas()]
	 * 		 y aún queda un Casillero.VACIO en la columna indicada. 
	 * post: deja caer una ficha en la columna indicada.
	 * 
	 * @param columna
	 */
	public void soltarFichaEnColumna(int columna) {
		
	}
	
	/**
	 * post: indica si el juego terminó porque uno de los jugadores
	 * 		 ganó o no existen casilleros vacíos.
	 */
	public boolean termino() {
		
		return false;
	}

	/**
	 * post: indica si el juego terminó y tiene un ganador.
	 */
	public boolean hayGanador() {
		
		return false;
	}

	/**
	 * pre : el juego terminó.
	 * post: devuelve el nombre del jugador que ganó el juego.
	 */
	public String obtenerGanador() {
		
		return null;
	}
	
	private void crearTablero(int filas, int columnas){
		
		if(filas < 4 || filas > 10 ){
			throw new Error("La cantidad de filas debe estar entre 4 y 10");
		}
		
		if(columnas < 4 || columnas > 10){
			throw new Error("La cantidad de columnas debe estar entre 4 y 10");
		}
		
		this.tablero = new Casillero[filas][columnas];  
		this.inicializarTablero();
	}
	
	private void inicializarTablero(){
		
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
		      this.tablero[i][j] = Casillero.VACIO;	
			}
		}
	}
	
}
