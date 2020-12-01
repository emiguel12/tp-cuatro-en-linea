package juego;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class CambiarColores implements EventHandler<ActionEvent> {
	
	private Aplicacion aplicacion;

	public CambiarColores(Aplicacion aplicacion)  {
		this.aplicacion = aplicacion;
	}
	
	
	
	@Override
	public void handle(ActionEvent event) {

		aplicacion.AlternarColores();
	}

}

