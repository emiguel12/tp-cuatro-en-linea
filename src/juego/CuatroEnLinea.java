package juego;

/**
 * Juego Cuatro en L�nea
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
	private boolean esTurnoRojo = true;

	/**
	 * pre : 'filas' y 'columnas' son mayores o iguales a 4.
	 * post: empieza el juego entre el jugador que tiene fichas rojas, identificado como 
	 * 		 'jugadorRojo' y el jugador que tiene fichas amarillas, identificado como
	 * 		 'jugadorAmarillo'. 
	 * 		 Todo el tablero est� vac�o.
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
	 * post: devuelve la cantidad m�xima de fichas que se pueden apilar en el tablero.
	 */
	public int contarFilas() {
		
		return this.tablero.length;
	}

	/**
	 * post: devuelve la cantidad m�xima de fichas que se pueden alinear en el tablero.
	 */
	public int contarColumnas() {
		
		return this.tablero[0].length;
	}

	/**
	 * pre : fila est� en el intervalo [1, contarFilas()],
	 * 		 columnas est� en el intervalo [1, contarColumnas()].
	 * post: indica qu� ocupa el casillero en la posici�n dada por fila y columna.
	 * 
	 * @param fila
	 * @param columna
	 */
	public Casillero obtenerCasillero(int fila, int columna) {
		validarFila(fila);
		validarColumna(columna);
		return tablero[fila-1][columna-1];
	}
	
	/**
	 * pre : el juego no termin�, columna est� en el intervalo [1, contarColumnas()]
	 * 		 y a�n queda un Casillero.VACIO en la columna indicada. 
	 * post: deja caer una ficha en la columna indicada.
	 * 
	 * @param columna
	 */
	public void soltarFichaEnColumna(int columna) {
		validarColumna(columna);
		
		int col = columna - 1;
		int fila = this.contarFilas() -1;
		boolean soltoFicha = false;
		
		if(!termino()){
			
			while(fila >= 0 && soltoFicha == false){
				
				if(this.tablero[fila][col] != Casillero.VACIO){
					fila --;
				}else{
					
					this.tablero[fila][col] = (this.esTurnoRojo ) ? Casillero.ROJO : Casillero.AMARILLO; 
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
	 * post: indica si el juego termin� porque uno de los jugadores
	 * 		 gan� o no existen casilleros vac�os.
	 */
	public boolean termino() {
		int casillerosOcupados = 0;
		
		for(int i = 0; i < this.tablero.length; i++){
			for(int j = 0; j < this.tablero[i].length; j++){
				if(this.tablero[i][j] != Casillero.VACIO){
					casillerosOcupados++;
				}
			}
		}
		
		if(casillerosOcupados == casillerosTotales()){
			return true;
		}
		
		return false;
	}

	/**
	 * post: indica si el juego termin� y tiene un ganador.
	 */
	public boolean hayGanador() {
		
		return false;
	}

	/**
	 * pre : el juego termin�.
	 * post: devuelve el nombre del jugador que gan� el juego.
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
	
	
	private void cambiarTurno(){
		
		this.esTurnoRojo = !this.esTurnoRojo;
	}
	
	/*
	 * POST: Analiza si la columna ingresada es v�lida
	 */
	private void validarColumna(int columna){
		if(columna < 1 || columna > contarColumnas()){
			throw new Error("La columna ingresada ("+columna +") es inv�lida.");
			
		}
	}
	/*
	 *POST: Analiza si la fila ingresada es v�lida
	 */
	private void validarFila(int fila){
		if(fila < 1 || fila > contarFilas()){
			throw new Error("La fila ingresada (" + fila + ") es inv�lida");
		}
	}
	
	/*
	 * POST: Devuelve el total de casilleros del tablero
	 */
	
	private int casillerosTotales(){
		return (contarFilas() * contarColumnas());
	}
}
