package ctrl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class VeterinarioController {

    @FXML
    private Button btnAniadir;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnModificar;

    @FXML
    private TableColumn<?, ?> colEdad;

    @FXML
    private TableColumn<?, ?> colEspecie;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colNombre;

    @FXML
    private TableColumn<?, ?> colPeso;

    @FXML
    private TableColumn<?, ?> colRaza;

    @FXML
    private TableColumn<?, ?> colSexo;

    @FXML
    private TableView<?> tblAnimales;

    @FXML
    private TextField txtFiltro;

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
