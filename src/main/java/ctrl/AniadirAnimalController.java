package ctrl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import models.ModeloAnimal;

public class AniadirAnimalController {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private ComboBox<String> cmbSexo;

    @FXML
    private TextField txtEdad;

    @FXML
    private TextField txtEspecie;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPeso;

    @FXML
    private TextField txtPrimeraConsulta;

    @FXML
    private TextField txtRaza;
    
    private TableView<ModeloAnimal> tblAnimales;

    @FXML
    void accionAniadir(ActionEvent event) {

    }

    @FXML
    void accionCancelar(ActionEvent event) {
    	VeterinarioController.getS().close();
    }
    
    @FXML
    private void initialize() {
    	cmbSexo.getItems().addAll("M", "F");
    }
    
    public void setTblAnimales(TableView<ModeloAnimal> tblAnimales) {
		this.tblAnimales = tblAnimales;
	}

	public TextField getTxtEdad() {
		return txtEdad;
	}

	public TextField getTxtEspecie() {
		return txtEspecie;
	}

	public TextField getTxtNombre() {
		return txtNombre;
	}

	public TextField getTxtPeso() {
		return txtPeso;
	}

	public TextField getTxtPrimeraConsulta() {
		return txtPrimeraConsulta;
	}

	public TextField getTxtRaza() {
		return txtRaza;
	}

}
