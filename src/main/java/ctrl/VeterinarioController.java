package ctrl;

import java.io.IOException;

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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.ModeloAnimal;

public class VeterinarioController {

    @FXML
    private Button btnAniadir;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnModificar;

    @FXML
    private TableColumn<ModeloAnimal, Integer> colEdad;

    @FXML
    private TableColumn<ModeloAnimal, String> colEspecie;
    
    @FXML
    private TableColumn<ModeloAnimal, String> colFecha;

    @FXML
    private TableColumn<ModeloAnimal, Integer> colId;

    @FXML
    private TableColumn<ModeloAnimal, String> colNombre;

    @FXML
    private TableColumn<ModeloAnimal, Float> colPeso;

    @FXML
    private TableColumn<ModeloAnimal, String> colRaza;

    @FXML
    private TableColumn<ModeloAnimal, String> colSexo;

    @FXML
    private TableView<ModeloAnimal> tblAnimales;

    @FXML
    private TextField txtFiltro;
    
    private FilteredList<ModeloAnimal> filtro;
    
    private static boolean esAniadir=false;
    
    private static Stage s;
    
    private static ObservableList<ModeloAnimal> listaTodas;

    @FXML
    void aniadirAnimal(ActionEvent event) {
    	esAniadir=true;
    	s=new Stage();
    	Scene scene;
		try {
			 FXMLLoader controlador = new FXMLLoader(es.aketzagonzalez.aeropuertosS.MainApp.class.getResource("/fxml/aniadirAnimal.fxml"));
			scene = new Scene(controlador.load());
			s.setTitle("Nueva Persona");
			s.setScene(scene);
			AniadirAnimalController controller = controlador.getController();
			controller.setTblAnimales(tblAnimales);
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

    @FXML
    void eliminarAnimal(ActionEvent event) {

    }

    @FXML
    void modificarAnimal(ActionEvent event) {
    	//TODO Acabar el modificar, tenindo en cuenta el sexo y comprobar que funcione
    	esAniadir=false;
    	if(tblAnimales.getSelectionModel().getSelectedItem()!=null) {
	    	s=new Stage();
	    	Scene scene;
			try {
				 FXMLLoader controlador = new FXMLLoader(MainApp.class.getResource("/fxml/aniadirPersona.fxml"));
				scene = new Scene(controlador.load());
				s.setTitle("Modificar ModeloPersona");
				s.setScene(scene);
				AniadirAnimalController controller = controlador.getController();
				controller.getTxtEdad().setText(tblAnimales.getSelectionModel().getSelectedItem().getEdad()+"");
				controller.getTxtEspecie().setText(tblAnimales.getSelectionModel().getSelectedItem().getEspecie());
				controller.getTxtPeso().setText(tblAnimales.getSelectionModel().getSelectedItem().getPeso()+"");
				controller.getTxtPrimeraConsulta().setText(tblAnimales.getSelectionModel().getSelectedItem().getPrimeraConsulta());
				controller.getTxtRaza().setText(tblAnimales.getSelectionModel().getSelectedItem().getRaza());
				controller.setTblAnimales(tblAnimales);
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

    @FXML
    void mostrarInformacionAnimal(MouseEvent event) {

    }
    
    @FXML
    void accionFiltrar(ActionEvent event) {
    	tblAnimales.setItems(filtro);
    	if(txtFiltro.getText().isEmpty()){
    		tblAnimales.setItems(listaTodas);
    	}else {
    		filtro.setPredicate(persona -> persona.getNombre().contains(txtFiltro.getText()));
    	}
    }
    
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
    }
    
    public static Stage getS() {
		return s;
	}
    
}
