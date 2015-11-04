package cuatroenlinea;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Iniciar implements EventHandler<ActionEvent> {

	private Aplicacion aplicacion;
	
	public Iniciar(Aplicacion aplicacion) {

		this.aplicacion = aplicacion;
	}

	@Override
	public void handle(ActionEvent event) {

		aplicacion.iniciar();
	}

}
