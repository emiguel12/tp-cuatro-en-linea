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
	private int filas;
	private int columnas;
	private String jugadorRojo;
	private String jugadorAmarillo;
	private String turno;

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
		this.filas = filas;
		this.columnas = columnas;
		this.jugadorAmarillo = jugadorAmarillo;
		this.jugadorRojo = jugadorRojo;
		this.turno = "rojo";
		tablero = new Casillero[filas][columnas];
		
		for(int i = 0; i<filas; i++){
			
			for(int j = 0; j<columnas; j++){
				
				tablero[i][j] = Casillero.VACIO;
			}
		}
		
	}

	/**
	 * post: devuelve la cantidad máxima de fichas que se pueden apilar en el tablero.
	 */
	public int contarFilas() {
		
		return this.filas;
	}

	/**
	 * post: devuelve la cantidad máxima de fichas que se pueden alinear en el tablero.
	 */
	public int contarColumnas() {
		
		return this.columnas;
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
		int fil = fila -1;
		int col = columna -1;
		
		return tablero[fil][col];
	}
	
	/**
	 * pre : el juego no terminó, columna está en el intervalo [1, contarColumnas()]
	 * 		 y aún queda un Casillero.VACIO en la columna indicada. 
	 * post: deja caer una ficha en la columna indicada.
	 * 
	 * @param columna
	 */
	public void soltarFichaEnColumna(int columna) {
		
		int col = columna - 1;
		int fila = this.filas -1;
		boolean soltoFicha = false;
		
		if(!termino()){
			
			while(fila >= 0 && soltoFicha == false){
				
				if(this.tablero[fila][col] != Casillero.VACIO){
					fila --;
				}else{
					
					this.tablero[fila][col] = (this.turno == "amarillo") ? Casillero.AMARILLO : Casillero.ROJO; 
					soltoFicha = true;
				}
				
			}
			
			if(fila == -1){
				return;
			}else{
				cambiarTurno();
				return;
			}
			
		}
		
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
	
	private void cambiarTurno(){
		
		this.turno = (this.turno == "rojo") ? "amarillo" : "rojo";
	}
}
