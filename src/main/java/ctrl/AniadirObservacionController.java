package ctrl;

import dao.DaoObservacion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import models.ModeloObservacion;

/**
 * The Class AniadirObservacionController.
 */
public class AniadirObservacionController {

    /** The btn cancelar. */
    @FXML
    private Button btnCancelar;

    /** The btn guardar. */
    @FXML
    private Button btnGuardar;

    /** The txt observacion. */
    @FXML
    private TextField txtObservacion;
    
    /** The tbl observaciones. */
    private TableView<ModeloObservacion> tblObservaciones;

    /**
     * Accion cancelar.
     *
     * @param event the event
     */
    @FXML
    void accionCancelar(ActionEvent event) {
    	ObservacionController.getS().close();
    }

    /**
     * Accion guardar.
     *
     * @param event the event
     */
    @FXML
    void accionGuardar(ActionEvent event) {
    	String error="";
    	String observacion=txtObservacion.getText();
    	boolean existe=validarExistencia(observacion,ObservacionController.getId_animal());
    	Alert al=new Alert(AlertType.INFORMATION);
    	al.setHeaderText(null);
    	if(txtObservacion.getText().isEmpty()) {
    		error+="El campo obsercavion es obligatorio\n";
    	}
    	if(ObservacionController.isEsAniadir()) {
    		if(error.equals("")&&!existe) {
    			DaoObservacion.insertar(observacion, ObservacionController.getId_animal());
    			error="Observacion añadida correctamente";
    		}else {
    			if(error.equals("")) {
    				al.setAlertType(AlertType.WARNING);
    				error="La observacion ya estaba en la lista";
    			}else {
    				al.setAlertType(AlertType.ERROR);
    			}
    		}
    	}else {
    		if(error.equals("")&&!existe) {
    			DaoObservacion.modificar(observacion,tblObservaciones.getSelectionModel().getSelectedItem().getId());
    			error="Observacion modificada correctamente";
    		}else {
    			if(error.equals("")) {
    				al.setAlertType(AlertType.WARNING);
    				error="La observacion ya estaba en la lista";
    			}else {
    				al.setAlertType(AlertType.ERROR);
    			}
    		}
    	}
    	al.setContentText(error);
    	al.showAndWait();
    	tblObservaciones.getSelectionModel().clearSelection();
    	ObservacionController.setListaTodas(DaoObservacion.conseguirListaTodas(ObservacionController.getId_animal()));
    	tblObservaciones.refresh();
    	ObservacionController.getS().close();
    }
    
    /**
     * Validar existencia.
     *
     * @param observacion the observacion
     * @param id_animal the id animal
     * @return true, if successful
     */
    private boolean validarExistencia(String observacion, int id_animal) {
    	ModeloObservacion obser=new ModeloObservacion(observacion, id_animal);
		for(ModeloObservacion ob:ObservacionController.getListaTodas()) {
			if(ob.equals(obser)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Sets the tbl observaciones.
	 *
	 * @param tblObservaciones the new tbl observaciones
	 */
	public void setTblObservaciones(TableView<ModeloObservacion> tblObservaciones) {
		this.tblObservaciones = tblObservaciones;
	}
	
	/**
	 * Gets the txt observacion.
	 *
	 * @return the txt observacion
	 */
	public TextField getTxtObservacion() {
		return txtObservacion;
	}

}
