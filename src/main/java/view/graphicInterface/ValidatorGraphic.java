package view.graphicInterface;

import controller.GraphicController;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.TuringException;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

/**
 * ValidatorGraphic represents a graphical component displaying information about a validator in the Turing game.
 * It includes the validator's type, image, corresponding card image, and a button to verify the validator.
 * Implements PropertyChangeListener to handle changes in the validator's state.
 *
 * @author Your Name
 * @version 1.0
 */
public class ValidatorGraphic extends GridPane implements PropertyChangeListener {

    /**
     * Constructs a ValidatorGraphic with the specified parameters.
     *
     * @param controller     The controller witch make the connection between the view and model.
     * @param validatorId    The ID of the validator.
     * @param validatorType  The type (character) of the validator.
     * @param validatorIndex The index of the validator.
     */
    public ValidatorGraphic(GraphicController controller, int validatorId, char validatorType, int validatorIndex) {


        Label witchValidator = new Label(String.valueOf(validatorType));
        GridPane.setHalignment(witchValidator, HPos.CENTER);


        Image image = new Image("/turingMachineImages/robot" + validatorType + ".png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        GridPane.setHalignment(imageView, HPos.CENTER);


        Image validatorImage = new Image("/turingMachineImages/card" + validatorId + ".png");
        ImageView validatorImageView = new ImageView(validatorImage);
        validatorImageView.setFitHeight(270);
        validatorImageView.setFitWidth(270);
        GridPane.setHalignment(validatorImageView, HPos.CENTER);


        Button chooseValidator = new Button("VERIFY");
        GridPane.setHalignment(chooseValidator, HPos.CENTER);


        chooseValidator.setOnAction(event -> {
            try {
                controller.verifyCommand(validatorIndex);
            } catch (TuringException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("ERROR");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        });


        this.add(witchValidator, 0, 0);
        this.add(imageView, 0, 1);
        this.add(validatorImageView, 0, 2);
        this.add(chooseValidator, 0, 3);


        this.setVgap(10);
        this.setAlignment(Pos.TOP_CENTER);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Handles property change events related to the validator's state and updates the UI accordingly.
     * </p>
     *
     * @param evt The property change event.
     * @see PropertyChangeListener#propertyChange(PropertyChangeEvent)
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (Objects.equals(evt.getPropertyName(), "NEW STATE FOR MATCHING AND ASKED")) {
            boolean[] newValues = (boolean[]) evt.getNewValue();
            if (newValues[0]) {
                if (newValues[1]) {
                    ValidatorGraphic.this.setStyle("-fx-background-color: rgb(144, 238, 144);"); // green
                } else {
                    ValidatorGraphic.this.setStyle("-fx-background-color: rgb(250, 30, 0);"); // red
                }

            } else {
                ValidatorGraphic.this.setStyle("-fx-background-color: rgb(250, 250, 250);"); // white
            }
        }
    }
}
