package ctrl;

import java.io.IOException;

import dao.DaoObservacion;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.ModeloAnimal;
import models.ModeloObservacion;

/**
 * The Class ObservacionController.
 */
public class ObservacionController {

    /** The btn aniadir. */
    @FXML
    private Button btnAniadir;

    /** The btn eliminar. */
    @FXML
    private Button btnEliminar;

    /** The btn modificar. */
    @FXML
    private Button btnModificar;

    /** The col observacion. */
    @FXML
    private TableColumn<ModeloObservacion,String> colObservacion;

    /** The tbl observaciones. */
    @FXML
    private TableView<ModeloObservacion> tblObservaciones;

    /** The txt filtro. */
    @FXML
    private TextField txtFiltro;
    
    /** The img animal. */
    @FXML
    private ImageView imgAnimal;
    
    /** The tbl animales. */
    private static TableView<ModeloAnimal> tblAnimales;
    
    /** The es aniadir. */
    private static boolean esAniadir;
    
    /** The filtro. */
    private FilteredList<ModeloObservacion> filtro;
    
    /** The lista todas. */
    private static ObservableList<ModeloObservacion> listaTodas;
    
    /** The s. */
    private static Stage s;
    
    /** The id animal. */
    private static int id_animal;
    
    /**
     * Accion filtrar.
     *
     * @param event the event
     */
    @FXML
    void accionFiltrar(ActionEvent event) {
    	tblObservaciones.setItems(filtro);
    	if(txtFiltro.getText().isEmpty()){
    		tblObservaciones.setItems(listaTodas);
    	}else {
    		filtro.setPredicate(observacion -> observacion.getObservacion().contains(txtFiltro.getText()));
    	}
    }

    /**
     * Aniadir animal.
     *
     * @param event the event
     */
    @FXML
    void aniadirAnimal(ActionEvent event) {
    	esAniadir=true;
    	s=new Stage();
    	Scene scene;
    	try {
			 FXMLLoader controlador = new FXMLLoader(es.aketzagonzalez.aeropuertosS.MainApp.class.getResource("/fxml/aniadirObservacion.fxml"));
			scene = new Scene(controlador.load());
			s.setTitle("Nueva Observacion");
			s.setScene(scene);
			AniadirObservacionController controller = controlador.getController();
			controller.setTblObservaciones(tblObservaciones);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	 s.setResizable(false);
    	 s.initOwner(VeterinarioController.getS());
    	 s.initModality(javafx.stage.Modality.WINDOW_MODAL);
         s.showAndWait();
         accionFiltrar(event);
         tblObservaciones.refresh();
    }

    /**
     * Eliminar animal.
     *
     * @param event the event
     */
    @FXML
    void eliminarAnimal(ActionEvent event) {
    	Alert al=new Alert(AlertType.CONFIRMATION);
    	al.setHeaderText(null);
    	if(tblObservaciones.getSelectionModel().getSelectedItem()!=null) {
    		al.setContentText("Estas seguro que deseas eliminar la observacion?");
    		al.showAndWait();
	    	if(al.getResult().getButtonData().name().equals("OK_DONE")) {
	    		DaoObservacion.eliminar(tblObservaciones.getSelectionModel().getSelectedItem().getId());
	    		listaTodas=DaoObservacion.conseguirListaTodas(id_animal);
	    		accionFiltrar(event);
	    		tblObservaciones.refresh();
	    	}
    	}else {
    		al.setAlertType(AlertType.ERROR);
    		al.setContentText("No hay nadie seleccionado, asi que no se puede borrar a nadie");
        	al.showAndWait();
    	}
    	tblObservaciones.getSelectionModel().clearSelection();
    }

    /**
     * Modificar animal.
     *
     * @param event the event
     */
    @FXML
    void modificarAnimal(ActionEvent event) {
    	esAniadir=false;
    	s=new Stage();
    	Scene scene;
    	try {
			 FXMLLoader controlador = new FXMLLoader(es.aketzagonzalez.aeropuertosS.MainApp.class.getResource("/fxml/aniadirObservacion.fxml"));
			scene = new Scene(controlador.load());
			s.setTitle("Editar Observacion");
			s.setScene(scene);
			AniadirObservacionController controller = controlador.getController();
			controller.setTblObservaciones(tblObservaciones);
			controller.getTxtObservacion().setText(tblObservaciones.getSelectionModel().getSelectedItem().getObservacion());
		} catch (IOException e) {
			e.printStackTrace();
		}
    	 s.setResizable(false);
    	 s.initOwner(VeterinarioController.getS());
    	 s.initModality(javafx.stage.Modality.WINDOW_MODAL);
         s.showAndWait();
         accionFiltrar(event);
         tblObservaciones.refresh();
    }
    
    /**
     * Initialize.
     */
    @FXML
    private void initialize() {
    	colObservacion.setCellValueFactory(new PropertyValueFactory<>("observacion"));
    	id_animal=tblAnimales.getSelectionModel().getSelectedItem().getId();
    	imgAnimal.setImage(new Image(tblAnimales.getSelectionModel().getSelectedItem().getFoto()));
    	listaTodas=DaoObservacion.conseguirListaTodas(id_animal);
    	filtro = new FilteredList<ModeloObservacion>(listaTodas);
    	tblObservaciones.setItems(listaTodas);
    }
    
    /**
     * Sets the tbl animales.
     *
     * @param tblAnimales the new tbl animales
     */
    public static void setTblAnimales(TableView<ModeloAnimal> tblAnimales) {
		ObservacionController.tblAnimales = tblAnimales;
	}
    
    /**
     * Gets the s.
     *
     * @return the s
     */
    public static Stage getS() {
		return s;
	}
    
    /**
     * Checks if is es aniadir.
     *
     * @return true, if is es aniadir
     */
    public static boolean isEsAniadir() {
		return esAniadir;
	}
    
    /**
     * Gets the id animal.
     *
     * @return the id animal
     */
    public static int getId_animal() {
		return id_animal;
	}
    
    /**
     * Gets the lista todas.
     *
     * @return the lista todas
     */
    public static ObservableList<ModeloObservacion> getListaTodas() {
		return listaTodas;
	}
    
    /**
     * Sets the lista todas.
     *
     * @param listaTodas the new lista todas
     */
    public static void setListaTodas(ObservableList<ModeloObservacion> listaTodas) {
		ObservacionController.listaTodas = listaTodas;
	}

}
