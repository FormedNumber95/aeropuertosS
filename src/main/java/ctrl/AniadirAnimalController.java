package ctrl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

import javax.imageio.ImageIO;

import dao.DaoAnimal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritablePixelFormat;
import javafx.stage.FileChooser;
import models.ModeloAnimal;

/**
 * The Class AniadirAnimalController.
 */
public class AniadirAnimalController {

    /** The btn cancelar. */
    @FXML
    private Button btnCancelar;

    /** The btn guardar. */
    @FXML
    private Button btnGuardar;

    /** The cmb sexo. */
    @FXML
    private ComboBox<String> cmbSexo;

    /** The txt edad. */
    @FXML
    private TextField txtEdad;

    /** The txt especie. */
    @FXML
    private TextField txtEspecie;

    /** The txt nombre. */
    @FXML
    private TextField txtNombre;

    /** The txt peso. */
    @FXML
    private TextField txtPeso;

    /** The txt raza. */
    @FXML
    private TextField txtRaza;
    
    /** The btn seleccionar imagen. */
    @FXML
    private Button btnSeleccionarImagen;
    
    /** The imagen animal. */
    @FXML
    private ImageView imagenAnimal;
    
    /** The tbl animales. */
    private TableView<ModeloAnimal> tblAnimales;

    /**
     * Accion aniadir.
     *
     * @param event the event
     */
    @FXML
    void accionAniadir(ActionEvent event) {
    	String error="";
    	String nombre=txtNombre.getText();
    	String especie=txtEspecie.getText();
    	String raza=txtRaza.getText();
    	int edad=-1;
    	float peso=-1f;
    	InputStream imagen=null;
    	boolean existe=false;
    	if(txtNombre.getText().isEmpty()) {
    		error+="El campo nombre es obligatorio\n";
    	}
    	if(txtEspecie.getText().isEmpty()) {
    		error+="El campo especie es obligatorio\n";
    	}
    	if(txtRaza.getText().isEmpty()) {
    		error+="El campo raza es obligatorio\n";
    	}
    	if(txtEdad.getText().isEmpty()) {
    		error+="El campo edad es obligatorio\n";
    	}else {
    		try {
    			edad=Integer.parseInt(txtEdad.getText());
    			if(edad<0) {
    				throw new Exception();
    			}
    		}catch(NumberFormatException e) {
    			error+="La edad debe ser un numero\n";
    		} catch (Exception e) {
				error+="La edad no puede ser menor que 0\n";
			}
    	}
    	if(txtPeso.getText().isEmpty()) {
    		error+="El campo peso es obligatorio\n";
    	}else {
    		try {
    			peso=Float.parseFloat(txtPeso.getText());
    			if(peso<=0) {
    				throw new Exception();
    			}
    		}catch(NumberFormatException e) {
    			error+="El peso debe ser un numero\n";
    		} catch (Exception e) {
				error+="El peso no puede ser menor o igul a 0\n";
			}
    	}
    	imagen=getImageInputStream(imagenAnimal);
    	if(imagen==null) {
    		error+="La imagen es obligatoria\n";
    	}
    	Alert al=new Alert(AlertType.INFORMATION);
    	al.setHeaderText(null);
    	if(VeterinarioController.isEsAniadir()) {
    		String primeraConsulta=LocalDateTime.now().getYear()+"/"+LocalDateTime.now().getMonth()+"/"+LocalDateTime.now().getDayOfMonth();
    		existe=validarExistencia(nombre,especie,raza,edad,peso,primeraConsulta,cmbSexo.getSelectionModel().getSelectedItem());
    		if(error.equals("")&&!existe) {
    			DaoAnimal.insertar(nombre, especie, raza,cmbSexo.getSelectionModel().getSelectedItem(), edad, peso, primeraConsulta, imagen);
    			error="Animal añadido correctamente";
    		}else {
    			if(error.equals("")) {
    				al.setAlertType(AlertType.WARNING);
    				error="El animal ya estaba en la lista";
    			}else {
    				al.setAlertType(AlertType.ERROR);
    			}
    		}
    	}else {
    		String primeraConsulta=tblAnimales.getSelectionModel().getSelectedItem().getPrimeraConsulta();
    		existe=validarExistencia(nombre,especie,raza,edad,peso,primeraConsulta,cmbSexo.getSelectionModel().getSelectedItem());
    		if(error.equals("")&&!existe) {
    			ModeloAnimal animal=tblAnimales.getSelectionModel().getSelectedItem();
    			DaoAnimal.modificar(animal.getId(), nombre, especie, raza,cmbSexo.getSelectionModel().getSelectedItem(), edad, peso, primeraConsulta, imagen);
    			error="Animal modificado correctamente";
    		}else {
    			if(error.equals("")) {
    				al.setAlertType(AlertType.WARNING);
    				error="El animal ya estaba en la lista";
    			}else {
    				al.setAlertType(AlertType.ERROR);
    			}
    		}
    	}
    	al.setContentText(error);
    	al.showAndWait();
    	tblAnimales.getSelectionModel().clearSelection();
    	VeterinarioController.setListaTodas(DaoAnimal.conseguirListaTodos());
    	tblAnimales.refresh();
    	VeterinarioController.getS().close();
    }

    /**
     * Validar existencia.
     *
     * @param nombre the nombre
     * @param especie the especie
     * @param raza the raza
     * @param edad the edad
     * @param peso the peso
     * @param primeraConsulta the primera consulta
     * @param sexo the sexo
     * @return true, if successful
     */
    private boolean validarExistencia(String nombre, String especie, String raza, int edad, float peso,
			String primeraConsulta, String sexo) {
    	ModeloAnimal animal=new ModeloAnimal(nombre, especie, raza, sexo, edad, peso, primeraConsulta, null);
    	for(ModeloAnimal a:DaoAnimal.conseguirListaTodos()) {
    		if(a.equals(animal)) {
    			return true;
    		}
    	}
		return false;
	}

	/**
	 * Accion cancelar.
	 *
	 * @param event the event
	 */
	@FXML
    void accionCancelar(ActionEvent event) {
    	VeterinarioController.getS().close();
    }
    
    /**
     * Elegir imagen.
     *
     * @param event the event
     */
    @FXML
    void elegirImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("Archivos JPG (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("Archivos PNG (*.png)", "*.png");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG); 
        File file = fileChooser.showOpenDialog(((Node)event.getSource()).getScene().getWindow());
        if(file!=null) {
	        imagenAnimal.setImage(new Image(file.toURI().toString()));
	        imagenAnimal.setFitWidth(100);
	        imagenAnimal.setFitHeight(100);
        }
    }
    
    /**
     * Initialize.
     */
    @FXML
    private void initialize() {
    	cmbSexo.getItems().addAll("M", "F");
    }
    
    /**
     * Gets the image input stream.
     *
     * @param imageView the image view
     * @return the image input stream
     */
    public static InputStream getImageInputStream(ImageView imageView) {
        Image image = imageView.getImage();
        if (image == null) {
            return null;
        }

        // Obtener las dimensiones de la imagen
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        // Crear un BufferedImage con el mismo tamaño que la imagen de JavaFX
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Obtener el lector de píxeles de la imagen de JavaFX
        PixelReader pixelReader = image.getPixelReader();
        if (pixelReader != null) {
            // Leer píxeles de la imagen y escribirlos en BufferedImage
            int[] pixels = new int[width * height];
            pixelReader.getPixels(0, 0, width, height, WritablePixelFormat.getIntArgbInstance(), pixels, 0, width);
            bufferedImage.setRGB(0, 0, width, height, pixels, 0, width);
        }

        try {
            // Escribir el BufferedImage en un ByteArrayOutputStream
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", outputStream);
            outputStream.flush();

            // Convertir el ByteArrayOutputStream a InputStream
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Sets the tbl animales.
     *
     * @param tblAnimales the new tbl animales
     */
    public void setTblAnimales(TableView<ModeloAnimal> tblAnimales) {
		this.tblAnimales = tblAnimales;
	}

	/**
	 * Gets the txt edad.
	 *
	 * @return the txt edad
	 */
	public TextField getTxtEdad() {
		return txtEdad;
	}

	/**
	 * Gets the txt especie.
	 *
	 * @return the txt especie
	 */
	public TextField getTxtEspecie() {
		return txtEspecie;
	}

	/**
	 * Gets the txt nombre.
	 *
	 * @return the txt nombre
	 */
	public TextField getTxtNombre() {
		return txtNombre;
	}

	/**
	 * Gets the txt peso.
	 *
	 * @return the txt peso
	 */
	public TextField getTxtPeso() {
		return txtPeso;
	}

	/**
	 * Gets the txt raza.
	 *
	 * @return the txt raza
	 */
	public TextField getTxtRaza() {
		return txtRaza;
	}
	
	/**
	 * Gets the cmb sexo.
	 *
	 * @return the cmb sexo
	 */
	public ComboBox<String> getCmbSexo() {
		return cmbSexo;
	}
	
	/**
	 * Gets the imagen animal.
	 *
	 * @return the imagen animal
	 */
	public ImageView getImagenAnimal() {
		return imagenAnimal;
	}

}
