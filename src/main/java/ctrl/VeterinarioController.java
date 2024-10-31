package ctrl;

import java.io.IOException;

import dao.DaoAnimal;
import es.aketzagonzalez.aeropuertosS.MainApp;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.ModeloAnimal;

/**
 * The Class VeterinarioController.
 */
public class VeterinarioController {

    /** The btn aniadir. */
    @FXML
    private Button btnAniadir;

    /** The btn eliminar. */
    @FXML
    private Button btnEliminar;

    /** The btn modificar. */
    @FXML
    private Button btnModificar;

    /** The col edad. */
    @FXML
    private TableColumn<ModeloAnimal, Integer> colEdad;

    /** The col especie. */
    @FXML
    private TableColumn<ModeloAnimal, String> colEspecie;
    
    /** The col fecha. */
    @FXML
    private TableColumn<ModeloAnimal, String> colFecha;

    /** The col id. */
    @FXML
    private TableColumn<ModeloAnimal, Integer> colId;

    /** The col nombre. */
    @FXML
    private TableColumn<ModeloAnimal, String> colNombre;

    /** The col peso. */
    @FXML
    private TableColumn<ModeloAnimal, Float> colPeso;

    /** The col raza. */
    @FXML
    private TableColumn<ModeloAnimal, String> colRaza;

    /** The col sexo. */
    @FXML
    private TableColumn<ModeloAnimal, String> colSexo;

    /** The tbl animales. */
    @FXML
    private TableView<ModeloAnimal> tblAnimales;

    /** The txt filtro. */
    @FXML
    private TextField txtFiltro;
    
    /** The filtro. */
    private FilteredList<ModeloAnimal> filtro;
    
    /** The es aniadir. */
    private static boolean esAniadir=false;
    
    /** The s. */
    private static Stage s;
    
    /** The lista todas. */
    private static ObservableList<ModeloAnimal> listaTodas;

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
			 FXMLLoader controlador = new FXMLLoader(es.aketzagonzalez.aeropuertosS.MainApp.class.getResource("/fxml/aniadirAnimal.fxml"));
			scene = new Scene(controlador.load());
			s.setTitle("Nuevo Animal");
			s.setScene(scene);
			AniadirAnimalController controller = controlador.getController();
			controller.setTblAnimales(tblAnimales);
			controller.getCmbSexo().getSelectionModel().select(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
        s.setResizable(false);
        s.initOwner(es.aketzagonzalez.aeropuertosS.MainApp.getStage());
        s.initModality(javafx.stage.Modality.WINDOW_MODAL);
        s.showAndWait();
        accionFiltrar(event);
        tblAnimales.refresh();
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
    	if(tblAnimales.getSelectionModel().getSelectedItem()!=null) {
    		al.setContentText("Estas seguro que deseas eliminar a "+tblAnimales.getSelectionModel().getSelectedItem().getNombre()+"?");
    		al.showAndWait();
	    	if(al.getResult().getButtonData().name().equals("OK_DONE")) {
	    		DaoAnimal.eliminar(tblAnimales.getSelectionModel().getSelectedItem().getId());
	    		listaTodas=DaoAnimal.conseguirListaTodos();
	    		accionFiltrar(event);
	    		tblAnimales.refresh();
	    	}
    	}else {
    		al.setAlertType(AlertType.ERROR);
    		al.setContentText("No hay nadie seleccionado, asi que no se puede borrar a nadie");
        	al.showAndWait();
    	}
    	tblAnimales.getSelectionModel().clearSelection();
    }

    /**
     * Modificar animal.
     *
     * @param event the event
     */
    @FXML
    void modificarAnimal(ActionEvent event) {
    	esAniadir=false;
    	if(tblAnimales.getSelectionModel().getSelectedItem()!=null) {
	    	s=new Stage();
	    	Scene scene;
			try {
				 FXMLLoader controlador = new FXMLLoader(MainApp.class.getResource("/fxml/aniadirAnimal.fxml"));
				scene = new Scene(controlador.load());
				s.setTitle("Modificar Animal");
				s.setScene(scene);
				AniadirAnimalController controller = controlador.getController();
				controller.getTxtNombre().setText(tblAnimales.getSelectionModel().getSelectedItem().getNombre());
				controller.getTxtEdad().setText(tblAnimales.getSelectionModel().getSelectedItem().getEdad()+"");
				controller.getTxtEspecie().setText(tblAnimales.getSelectionModel().getSelectedItem().getEspecie());
				controller.getTxtPeso().setText(tblAnimales.getSelectionModel().getSelectedItem().getPeso()+"");
				controller.getTxtRaza().setText(tblAnimales.getSelectionModel().getSelectedItem().getRaza());
				controller.setTblAnimales(tblAnimales);
				controller.getImagenAnimal().setImage(new Image(tblAnimales.getSelectionModel().getSelectedItem().getFoto()));
				if(tblAnimales.getSelectionModel().getSelectedItem().getSexo()=="F") {
					controller.getCmbSexo().getSelectionModel().select(1);
				}else {
					controller.getCmbSexo().getSelectionModel().select(0);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	        s.setResizable(false);
	        s.initOwner(MainApp.getStage());
	        s.initModality(javafx.stage.Modality.WINDOW_MODAL);
	        s.showAndWait();
	        accionFiltrar(event);
	        tblAnimales.refresh();
    	}
    	else {
    		Alert al=new Alert(AlertType.ERROR);
        	al.setHeaderText(null);
        	al.setContentText("No hay nadie seleccionado, asi que no se puede modificar a nadie");
        	al.showAndWait();
    	}
    }

    /**
     * Mostrar informacion animal.
     *
     * @param event the event
     */
    @FXML
    void mostrarInformacionAnimal(MouseEvent event) {
    	if(event.getClickCount()==2&&tblAnimales.getSelectionModel().getSelectedItem()!=null) {
    		s=new Stage();
	    	Scene scene;
			try {
				ObservacionController.setTblAnimales(tblAnimales);
				 FXMLLoader controlador = new FXMLLoader(MainApp.class.getResource("/fxml/observaciones.fxml"));
				scene = new Scene(controlador.load());
				s.setTitle("Observaciones de "+tblAnimales.getSelectionModel().getSelectedItem().getNombre());
				s.setScene(scene);
			}catch (IOException e) {
				e.printStackTrace();
			}
			 s.setResizable(false);
		        s.initOwner(MainApp.getStage());
		        s.initModality(javafx.stage.Modality.WINDOW_MODAL);
		        s.showAndWait();
			
    	}
    }
    
    /**
     * Accion filtrar.
     *
     * @param event the event
     */
    @FXML
    void accionFiltrar(ActionEvent event) {
    	tblAnimales.setItems(filtro);
    	if(txtFiltro.getText().isEmpty()){
    		tblAnimales.setItems(listaTodas);
    	}else {
    		filtro.setPredicate(animal -> animal.getNombre().contains(txtFiltro.getText()));
    	}
    }
    
    /**
     * Initialize.
     */
    @FXML
    private void initialize() {
    	colId.setCellValueFactory(new PropertyValueFactory<>("id"));
    	colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    	colEspecie.setCellValueFactory(new PropertyValueFactory<>("especie"));
    	colRaza.setCellValueFactory(new PropertyValueFactory<>("raza"));
    	colPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
    	colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
    	colSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
    	colFecha.setCellValueFactory(new PropertyValueFactory<>("primeraConsulta"));
    	listaTodas=DaoAnimal.conseguirListaTodos();
    	filtro = new FilteredList<ModeloAnimal>(listaTodas);
    	tblAnimales.setItems(listaTodas);
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
     * Gets the lista todas.
     *
     * @return the lista todas
     */
    public static ObservableList<ModeloAnimal> getListaTodas() {
		return listaTodas;
	}
    
    /**
     * Sets the lista todas.
     *
     * @param listaTodas the new lista todas
     */
    public static void setListaTodas(ObservableList<ModeloAnimal> listaTodas) {
		VeterinarioController.listaTodas = listaTodas;
	}
    
}
