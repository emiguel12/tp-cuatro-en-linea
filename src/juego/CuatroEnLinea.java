package juego;

/**
 * Juego Cuatro en Lí­nea
 * 
 * Reglas:
 * 
 * El objetivo de este juego consiste en colocar cuatro fichas en una fila
 * contínua vertical, horizontal o diagonalmente. Se juega sobre un tablero de
 * nXm casillas que al empezar está vacío. Cómo colocar las fichas
 * 
 * Ambos jugadores sitúan sus fichas (una por movimiento) en el tablero. La
 * regla para colocarlas consiste en que estas siempre "caen hasta abajo". Es
 * decir una ficha puede ser colocada bien en la parte inferior de una columna o
 * bien sobre otra de alguna otra columna.
 * 
 * La partida termina si una de las siguientes condiciones se cumple: Uno de los
 * jugadores coloca cuatro o más fichas en una línea contínua vertical,
 * horizontal o diagonalmente. Este jugador gana la partida.
 *
 * Todas las casillas del tablero están ocupadas y ningún jugador cumple la
 * condición anterior para ganar. En este caso la partida finaliza en empate.
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
	public CuatroEnLinea(int filas, int columnas, String jugadorRojo,
			String jugadorAmarillo) {

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
	 *            : numero de fila
	 * @param columna
	 *            : numero de columna
	 */
	public Casillero obtenerCasillero(int fila, int columna) {
		validarFila(fila);
		validarColumna(columna);
		return tablero[fila - 1][columna - 1];
	}

	/**
	 * pre : el juego no terminó, columna está en el intervalo [1,
	 * contarColumnas()] y aún queda un Casillero.VACIO en la columna indicada.
	 * post: deja caer una ficha en la columna indicada y cambia el turno del
	 * jugador.
	 * 
	 * @param columna
	 */
	public void soltarFichaEnColumna(int columna) {

		if (!this.termino() && !this.esColumnaLlena(columna)) {

			int i = 1;
			while ((i != this.contarFilas())
					&& (this.obtenerCasillero(i + 1, columna) == Casillero.VACIO)) {
				i++;
			}

			this.tablero[i - 1][columna - 1] = this.esTurnoRojo ? Casillero.ROJO
					: Casillero.AMARILLO;

			if (this.comprobar(i, columna, this.tablero[i - 1][columna - 1])) {
				this.ganador = this.esTurnoRojo ? this.jugadorRojo
						: this.jugadorAmarillo;
			}
			this.cambiarTurno();

		}

	}

	/**
	 * post: indica si el juego terminó porque uno de los jugadores ganó o
	 * existe empate.
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
		if (this.ganador == null) {
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

	/**
	 * pre : 'filas' y 'columnas' son mayores o iguales a 4 y menores iguales a
	 * 10. post: se crea el tablero vacio con tantas filas y columnas indicadas
	 * 
	 * @param filas
	 *            : cantidad de filas que tiene el tablero.
	 * @param columnas
	 *            : cantidad de columnas que tiene el tablero.
	 */
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

	/**
	 * pre : existe un tablero post: se inicializa cada casillero del tablero
	 * con el Casillero VACIO
	 * 
	 */
	private void inicializarTablero() {

		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				this.tablero[i][j] = Casillero.VACIO;
			}
		}
	}

	/**
	 * 
	 * post: Cambia el turno del jugador Rojo a el jugador Amarillo y viceversa
	 * 
	 */
	private void cambiarTurno() {

		this.esTurnoRojo = !this.esTurnoRojo;

	}

	/**
	 * pre : existe la columna en el tablero post: indica si la columna esta sin
	 * casilleros disponibles
	 * 
	 */
	private boolean esColumnaLlena(int columna) {

		if (this.obtenerCasillero(1, columna) != Casillero.VACIO) {
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

	/**
	 * pre : 'filas' y 'columnas' pertenecen al tablero. post: indica si el
	 * jugador alineo 4 Fichas en forma vertical
	 * 
	 * @param fila
	 *            : numero de fila de la ultima ficha ingresada
	 * @param columna
	 *            : numero de columna de la ultima ficha ingresada
	 * @param casillero
	 *            : Casillero del color correspondiente al jugador
	 */
	private boolean lineaVertical(int fila, int columna, Casillero casillero) {

		int filaMin = (fila - 3) >= 1 ? fila - 3 : 1;
		int filaMax = (fila + 3) <= this.contarFilas() ? fila + 3 : this
				.contarFilas();
		int cantFichasJuntas = 0;

		for (int i = filaMin; i <= filaMax; i++) {

			if (this.obtenerCasillero(i, columna) == casillero) {
				cantFichasJuntas++;
			} else {
				cantFichasJuntas = cantFichasJuntas == 4 ? 4 : 0;
			}

		}

		return cantFichasJuntas >= 4;

	}

	/**
	 * pre : 'filas' y 'columnas' pertenecen al tablero. post: indica si el
	 * jugador alineo 4 Fichas en forma horizontal
	 * 
	 * @param fila
	 *            : numero de fila de la ultima ficha ingresada
	 * @param columna
	 *            : numero de columna de la ultima ficha ingresada
	 * @param casillero
	 *            : Casillero del color correspondiente al jugador
	 */
	private boolean lineaHorizontal(int fila, int columna, Casillero casillero) {

		int columnaMin = (columna - 3) >= 1 ? columna - 3 : 1;
		int columnaMax = (columna + 3) <= this.contarColumnas() ? columna + 3
				: this.contarColumnas();
		int cantFichasJuntas = 0;

		for (int i = columnaMin; i <= columnaMax; i++) {

			if (this.obtenerCasillero(fila, i) == casillero) {
				cantFichasJuntas++;
			} else {
				cantFichasJuntas = (cantFichasJuntas == 4) ? 4 : 0;
			}

		}

		return cantFichasJuntas >= 4;

	}

	/**
	 * pre : 'filas' y 'columnas' pertenecen al tablero. post: indica si el
	 * jugador alineo 4 Fichas en forma diagonal descendente
	 * 
	 * @param fila
	 *            : numero de fila de la ultima ficha ingresada
	 * @param columna
	 *            : numero de columna de la ultima ficha ingresada
	 * @param casillero
	 *            : Casillero del color correspondiente al jugador
	 */
	private boolean lineaDiagonalDescendente(int fila, int columna,
			Casillero casillero) {

		int filaMax = this.contarFilas();
		int columnaMax = this.contarColumnas();

		// Contador de fichas
		int cantFichasJuntas = 1;
		boolean seguirContandoIzq = true;
		boolean seguirContandoDer = true;
		int i = 1;
		// Verificador por filas
		while ((cantFichasJuntas < 4)
				&& (seguirContandoDer || seguirContandoIzq)) {

			if ((seguirContandoIzq)
					&& (fila - i >= 1 && columna - i >= 1)
					&& (this.obtenerCasillero(fila - i, columna - i) == casillero)) {
				cantFichasJuntas++;
			} else {
				seguirContandoIzq = false;
			}
			if ((fila + i <= filaMax && columna + i <= columnaMax)
					&& (this.obtenerCasillero(fila + i, columna + i) == casillero)) {
				cantFichasJuntas++;
			} else {
				seguirContandoDer = false;
			}
			i++;
		}

		return cantFichasJuntas >= 4;
	}

	/**
	 * pre : 'filas' y 'columnas' pertenecen al tablero. post: indica si el
	 * jugador alineo 4 Fichas en forma diagonal ascendente
	 * 
	 * @param fila
	 *            : numero de fila de la ultima ficha ingresada
	 * @param columna
	 *            : numero de columna de la ultima ficha ingresada
	 * @param casillero
	 *            : Casillero del color correspondiente al jugador
	 */
	private boolean lineaDiagonalAscendente(int fila, int columna,
			Casillero casillero) {

		int filaMax = this.contarFilas();
		int columnaMax = this.contarColumnas();

		// Contador de fichas
		int cantFichasJuntas = 1;
		boolean seguirContandoIzq = true;
		boolean seguirContandoDer = true;
		int i = 1;
		// Verificador por filas
		while ((cantFichasJuntas < 4)
				&& (seguirContandoDer || seguirContandoIzq)) {

			if ((seguirContandoIzq)
					&& (fila + i <= filaMax && columna - i >= 1)
					&& (this.obtenerCasillero(fila + i, columna - i) == casillero)) {
				cantFichasJuntas++;
			} else {
				seguirContandoIzq = false;
			}
			if ((fila - i >= 1 && columna + i <= columnaMax)
					&& (this.obtenerCasillero(fila - i, columna + i) == casillero)) {
				cantFichasJuntas++;
			} else {
				seguirContandoDer = false;
			}
			i++;
		}

		return cantFichasJuntas >= 4;
	}

	/**
	 * pre : 'fila' y 'columna' pertenecen al tablero. post: indica si el
	 * jugador alineo 4 Fichas en cualquier direccion
	 * 
	 * @param fila
	 *            : numero de fila de la ultima ficha ingresada
	 * @param columna
	 *            : numero de columna de la ultima ficha ingresada
	 * @param casillero
	 *            : Casillero del color correspondiente al jugador
	 */
	private boolean comprobar(int fila, int columna, Casillero casillero) {

		return (this.lineaHorizontal(fila, columna, casillero)
				|| this.lineaVertical(fila, columna, casillero)
				|| lineaDiagonalAscendente(fila, columna, casillero) || lineaDiagonalDescendente(
					fila, columna, casillero));

	}
}
