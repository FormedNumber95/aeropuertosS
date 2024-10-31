package ctrl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import models.ModeloObservacion;

public class ObservacionController {

    @FXML
    private Button btnAniadir;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnModificar;

    @FXML
    private TableColumn<ModeloObservacion,String> colObservacion;

    @FXML
    private TableView<ModeloObservacion> tblAnimales;

    @FXML
    private TextField txtFiltro;
    
    @FXML
    private ImageView imgAnimal;

    @FXML
    void aniadirAnimal(ActionEvent event) {

    }

    @FXML
    void eliminarAnimal(ActionEvent event) {

    }

    @FXML
    void modificarAnimal(ActionEvent event) {

    }

}
