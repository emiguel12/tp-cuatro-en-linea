package juego;

/**
 * Juego Cuatro en Lí­nea
 * 
 * Reglas:
 * 
 * ...
 *
 */
public class CuatroEnLinea {

	private Casillero[][] tablero;
	private String jugadorRojo;
	private String jugadorAmarillo;
	private String ganador;
	private boolean esTurnoRojo = true;


	/**
	 * pre : 'filas' y 'columnas' son mayores o iguales a 4. post: empieza el
	 * juego entre el jugador que tiene fichas rojas, identificado como
	 * 'jugadorRojo' y el jugador que tiene fichas amarillas, identificado como
	 * 'jugadorAmarillo'. Todo el tablero está vacío.
	 * 
	 * @param filas
	 *            : cantidad de filas que tiene el tablero.
	 * @param columnas
	 *            : cantidad de columnas que tiene el tablero.
	 * @param jugadorRojo
	 *            : nombre del jugador con fichas rojas.
	 * @param jugadorAmarillo
	 *            : nombre del jugador con fichas amarillas.
	 */
	public CuatroEnLinea(int filas, int columnas, String jugadorRojo,String jugadorAmarillo) {

		this.crearTablero(filas, columnas);

		this.jugadorAmarillo = jugadorAmarillo;
		this.jugadorRojo = jugadorRojo;

	}

	/**
	 * post: devuelve la cantidad máxima de fichas que se pueden apilar en el
	 * tablero.
	 */
	public int contarFilas() {

		return this.tablero.length;
	}

	/**
	 * post: devuelve la cantidad máxima de fichas que se pueden alinear en el
	 * tablero.
	 */
	public int contarColumnas() {

		return this.tablero[0].length;
	}

	/**
	 * pre : fila está en el intervalo [1, contarFilas()], columnas está en el
	 * intervalo [1, contarColumnas()]. post: indica qué ocupa el casillero en
	 * la posición dada por fila y columna.
	 * 
	 * @param fila
	 * @param columna
	 */
	public Casillero obtenerCasillero(int fila, int columna) {
		validarFila(fila);
		validarColumna(columna);
		return tablero[fila - 1][columna - 1];
	}

	/**
	 * pre : el juego no terminó, columna está en el intervalo [1,
	 * contarColumnas()] y aún queda un Casillero.VACIO en la columna indicada.
	 * post: deja caer una ficha en la columna indicada.
	 * 
	 * @param columna
	 */
	public void soltarFichaEnColumna(int columna) {
		
		if(!this.termino()&& !this.esColumnaLlena(columna)){
			
			
			int i = 1;
			while((i!= this.contarFilas()) && (this.obtenerCasillero(i+1,columna) == Casillero.VACIO)){
				 i++;
			}

			this.tablero[i-1][columna-1] = this.esTurnoRojo ? Casillero.ROJO : Casillero.AMARILLO;
			
		    if (this.comprobar(i, columna, this.tablero[i-1][columna-1]))
	    	{
		    	this.ganador = this.esTurnoRojo ? this.jugadorRojo : this.jugadorAmarillo;
	    	}
			this.cambiarTurno();				

		}

	}

	/**
	 * post: indica si el juego terminó porque uno de los jugadores ganó o no
	 * existen casilleros vacíos.
	 */
	public boolean termino() {
		if (hayGanador()) {
			return true;
		}
		int casillerosOcupados = 0;

		for (int i = 0; i < this.tablero.length; i++) {
			for (int j = 0; j < this.tablero[i].length; j++) {
				if (this.tablero[i][j] != Casillero.VACIO) {
					casillerosOcupados++;
				}
			}
		}

		if (casillerosOcupados == casillerosTotales()) {
			return true;
		}

		return false;
	}

	/**
	 * post: indica si el juego terminó y tiene un ganador.
	 */
	public boolean hayGanador() {
		if(this.ganador == null)
		{
			return false;
		}
		return true;
	}

	/**
	 * pre : el juego terminó. post: devuelve el nombre del jugador que ganó el
	 * juego.
	 */
	public String obtenerGanador() {

		return ganador;
	}

	private void crearTablero(int filas, int columnas) {

		if (filas < 4 || filas > 10) {
			throw new Error("La cantidad de filas debe estar entre 4 y 10");
		}

		if (columnas < 4 || columnas > 10) {
			throw new Error("La cantidad de columnas debe estar entre 4 y 10");
		}

		this.tablero = new Casillero[filas][columnas];
		this.inicializarTablero();
	}

	private void inicializarTablero() {

		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				this.tablero[i][j] = Casillero.VACIO;
			}
		}
	}

	private void cambiarTurno(){
		
		this.esTurnoRojo = !this.esTurnoRojo;

	}
	
	private boolean esColumnaLlena(int columna){
		
		if(this.obtenerCasillero(1, columna) != Casillero.VACIO){
			return true;
		}
		return false;
	}

	/*
	 * POST: Analiza si la columna ingresada es válida
	 */
	private void validarColumna(int columna) {
		if (columna < 1 || columna > contarColumnas()) {
			throw new Error("La columna ingresada (" + columna
					+ ") es inválida.");

		}
	}

	/*
	 * POST: Analiza si la fila ingresada es válida
	 */
	private void validarFila(int fila) {
		if (fila < 1 || fila > contarFilas()) {
			throw new Error("La fila ingresada (" + fila + ") es inválida");
		}
	}

	/*
	 * POST: Devuelve el total de casilleros del tablero
	 */

	private int casillerosTotales() {
		return (contarFilas() * contarColumnas());
	}


	/*private boolean ganadorVertical() {
		int casillerosRojosSeguidos = 0;
		int casillerosAmarillosSeguidos = 0;
		
		for (int i = 1; i < this.tablero.length; i++) {
			for (int j = 0; j < this.tablero[i].length; j++) {
				if(analizarCasillerosRojosVerticalesSeguidos(i,j))  {
					casillerosRojosSeguidos++;
				}
				if (analizarCasillerosAmarillosVerticalesSeguidos(i,j)){
					casillerosAmarillosSeguidos++;
				}
			}
		}
		if(casillerosRojosSeguidos >= 3){
			ganador = this.jugadorRojo;
			return true;
		}else if(casillerosAmarillosSeguidos >= 3){
			ganador = this.jugadorAmarillo;
			return true;
		}
		return false;
	}
	
	private boolean analizarCasillerosRojosVerticalesSeguidos(int i, int j){
		if(this.tablero[i][j] == this.tablero[i - 1][j]
				&& this.tablero[i][j] != Casillero.VACIO && this.tablero[i][j] == Casillero.ROJO){
					return true;
	
		}
		return false;
	}
	
	private boolean analizarCasillerosAmarillosVerticalesSeguidos(int i, int j){
		if(this.tablero[i][j] == this.tablero[i - 1][j]
				&& this.tablero[i][j] != Casillero.VACIO && this.tablero[i][j] == Casillero.AMARILLO){
					return true;
		}
		return false;
		}
	*/
	private boolean lineaVertical(int fila, int columna, Casillero casillero){
	
		int filaMin = (fila - 3) >= 1 ? fila-3 : 1;
		int filaMax = (fila + 3) <= this.contarFilas() ? fila+3:this.contarFilas();
		int cantFichasJuntas = 0;
		
		for (int i = filaMin; i <= filaMax; i++) {
			
			if(this.obtenerCasillero(i, columna) == casillero){
				cantFichasJuntas++;
			}else{
				cantFichasJuntas = cantFichasJuntas == 4 ? 4:0;
			}
			
		}
		
		if(cantFichasJuntas==4 ){
			return true;
		}
		return false;
		
	}
	
	private boolean lineaHorizontal(int fila, int columna, Casillero casillero){
		
		int columnaMin = (columna - 3) >= 1 ? columna-3 : 1;
		int columnaMax = (columna + 3) <= this.contarColumnas() ? columna+3 : this.contarColumnas();
		int cantFichasJuntas = 0;
		
		for (int i = columnaMin; i <= columnaMax; i++) {
			
			if(this.obtenerCasillero(fila, i) == casillero){
				cantFichasJuntas++;
			}else{
				cantFichasJuntas = (cantFichasJuntas == 4) ?  4:0 ;
			}
			
		}
		
		if(cantFichasJuntas == 4){
			return true;
		}
		return false;
		
	}
	// CUIDADO, ROMPE TODO 
private boolean lineaDiagonal(int fila, int columna, Casillero casillero){
		
		//Variables para columnas
		int columnaMin = (columna - 3) >= 1 ? columna-3 : 1;
		int columnaMax = (columna + 3) <= this.contarColumnas() ? columna+3 : this.contarColumnas();
		
		//Variables para filas
		int filaMin = (fila - 3) >= 1 ? fila-3 : 1;
		int filaMax = (fila + 3) <= this.contarFilas() ? fila+3 : this.contarFilas();
		
		//Contador de fichas
		int cantFichasJuntas = 0; 
		
		//Verificador por filas
		for(int i = 0; i <= 3; i++){
				int columnaMenosI = (columna - i) >= columnaMin ? (columna-i) : filaMin;
				int columnaMasI = (columna + i) <= columnaMax ? (columna + i) : columnaMax;
				int filaMenosI = (fila - i) >= filaMin ? (fila - i) : columnaMin;
				int filaMasI = (fila + i) <= filaMax ? (fila + i) : filaMax;
				
					//Comprobador de que es la misma ficha diagonal izquierda para abajo
				if(this.obtenerCasillero(filaMasI, columnaMenosI) == casillero ||
						//Comprobador de que es la misma ficha diagonal izquierda para arriba
						this.obtenerCasillero(filaMenosI, columnaMenosI) == casillero ||
						//Comprobador de que es la misma ficha diagonal derecha para abajo
						this.obtenerCasillero(filaMasI, columnaMasI) == casillero ||
						//Comprobador de que es la misma ficha diagonal derecha para arriba
						this.obtenerCasillero(filaMenosI, columnaMenosI) == casillero){
					cantFichasJuntas++;
					}else {
					cantFichasJuntas = (cantFichasJuntas == 4) ? 4:0;
				}
			
	//	}
		}
			
		
		if(cantFichasJuntas == 4){
			return true;
		} else{

		return false;
	}
}
 
	private boolean comprobar(int fila,int columna, Casillero casillero){
		
		return (this.lineaHorizontal(fila, columna, casillero) || this.lineaVertical(fila, columna, casillero) || 
				lineaDiagonal(fila, columna, casillero));
		
		
	}
	

}
