package view.graphicInterface;

import controller.GraphicController;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.Code;
import model.TuringException;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

/**
 * CodeInput is a graphical user interface component for entering and validating codes in the Turing game.
 * It extends GridPane and implements PropertyChangeListener to listen for changes in the input code.
 * The component includes buttons for selecting code digits, displaying the current input code, and
 * buttons for validating and checking the code.
 * <p>
 * The class uses JavaFX controls, including ToggleButtons, TextFields, and Buttons, to create the UI.
 * </p>
 *
 * @author JAWAD EL HARRASI
 * @version 1.0
 */
public class CodeInput extends GridPane implements PropertyChangeListener {

    private final TextField codeInput;
    private final GraphicController controller;

    /**
     * Constructs a CodeInput component for entering and validating codes in the Turing game.
     *
     * @param controller the controller witch make the connexion with the model
     */
    public CodeInput(GraphicController controller){
        this.controller = controller;
        ToggleButton one = new ToggleButton("1");
        ToggleButton two = new ToggleButton("2");
        ToggleButton three = new ToggleButton("3");
        ToggleButton four = new ToggleButton("4");
        ToggleButton five = new ToggleButton("5");

        ToggleButton one1 = new ToggleButton("1");
        ToggleButton two1 = new ToggleButton("2");
        ToggleButton three1 = new ToggleButton("3");
        ToggleButton four1 = new ToggleButton("4");
        ToggleButton five1 = new ToggleButton("5");

        ToggleButton one2 = new ToggleButton("1");
        ToggleButton two2 = new ToggleButton("2");
        ToggleButton three2 = new ToggleButton("3");
        ToggleButton four2 = new ToggleButton("4");
        ToggleButton five2 = new ToggleButton("5");

        ToggleGroup firstColumn = new ToggleGroup();
        one.setToggleGroup(firstColumn);
        two.setToggleGroup(firstColumn);
        three.setToggleGroup(firstColumn);
        four.setToggleGroup(firstColumn);
        five.setToggleGroup(firstColumn);

        ToggleGroup secondColumn = new ToggleGroup();
        one1.setToggleGroup(secondColumn);
        two1.setToggleGroup(secondColumn);
        three1.setToggleGroup(secondColumn);
        four1.setToggleGroup(secondColumn);
        five1.setToggleGroup(secondColumn);

        ToggleGroup thirdColumn = new ToggleGroup();
        one2.setToggleGroup(thirdColumn);
        two2.setToggleGroup(thirdColumn);
        three2.setToggleGroup(thirdColumn);
        four2.setToggleGroup(thirdColumn);
        five2.setToggleGroup(thirdColumn);


        setButtonSize(one);
        setButtonSize(two);
        setButtonSize(three);
        setButtonSize(four);
        setButtonSize(five);

        setButtonSize(one1);
        setButtonSize(two1);
        setButtonSize(three1);
        setButtonSize(four1);
        setButtonSize(five1);

        setButtonSize(one2);
        setButtonSize(two2);
        setButtonSize(three2);
        setButtonSize(four2);
        setButtonSize(five2);


        Image triangleBleu = new Image("/turingMachineImages/triangleBleu.png");
        ImageView imageViewTriangle = new ImageView(triangleBleu);
        imageViewTriangle.setFitHeight(45);
        imageViewTriangle.setFitWidth(45);
        setMargin(imageViewTriangle, new Insets(0, 0, 0, 8));
        this.add(imageViewTriangle, 0, 0);

        Image yellowSquare = new Image("/turingMachineImages/carréJaune.png");
        ImageView imageViewSquare = new ImageView(yellowSquare);
        imageViewSquare.setFitHeight(50);
        imageViewSquare.setFitWidth(60);
        this.add(imageViewSquare, 1, 0);

        Image purpleCircle = new Image("/turingMachineImages/rondMauve.jpg");
        ImageView imageViewCircle = new ImageView(purpleCircle);
        imageViewCircle.setFitHeight(50);
        imageViewCircle.setFitWidth(50);
        setMargin(imageViewCircle, new Insets(0, 0, 0, 8));
        this.add(imageViewCircle, 2, 0);

        this.add(one, 0, 1);
        this.add(two, 0, 2);
        this.add(three, 0, 3);
        this.add(four, 0, 4);
        this.add(five, 0, 5);

        this.add(one1, 1, 1);
        this.add(two1, 1, 2);
        this.add(three1, 1, 3);
        this.add(four1, 1, 4);
        this.add(five1, 1, 5);

        this.add(one2, 2, 1);
        this.add(two2, 2, 2);
        this.add(three2, 2, 3);
        this.add(four2, 2, 4);
        this.add(five2, 2, 5);

        codeInput = new TextField();
        codeInput.setPromptText("YOUR CODE");
        codeInput.setEditable(false);
        this.add(codeInput, 3, 3);

        Button codeValidation = new Button("VALIDATE CODE");
        this.add(codeValidation, 4, 3);

        codeValidation.setOnAction(event -> {
            try {
                if (controller.confirmACode()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("YOU WIN !!!!!!");
                    alert.setContentText("THANK YOU AND GOOD BYE ");
                    alert.showAndWait();
                    controller.end();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("YOU LOSE !!!!!!");
                    alert.setContentText("THANK YOU AND GOOD BYE ");
                    alert.showAndWait();
                    controller.end();
                }
            } catch (TuringException e) {
                throw new RuntimeException(e);
            }
        });

        Button codeForVerification = new Button("INPUT THE CODE");
        this.add(codeForVerification, 4, 2);

        codeForVerification.setOnAction(event -> {
            try {
                addCode();
            } catch (TuringException e) {
                throw new RuntimeException(e);
            }
        });


        setButtonAction(one, 0, "1");
        setButtonAction(two, 0, "2");
        setButtonAction(three, 0, "3");
        setButtonAction(four, 0, "4");
        setButtonAction(five, 0, "5");

        setButtonAction(one1, 1, "1");
        setButtonAction(two1, 1, "2");
        setButtonAction(three1, 1, "3");
        setButtonAction(four1, 1, "4");
        setButtonAction(five1, 1, "5");

        setButtonAction(one2, 2, "1");
        setButtonAction(two2, 2, "2");
        setButtonAction(three2, 2, "3");
        setButtonAction(four2, 2, "4");
        setButtonAction(five2, 2, "5");
    }

    /**
     * Sets the action for a ToggleButton, updating the input code based on the button's position and text.
     *
     * @param button     The ToggleButton for which to set the action.
     * @param pos        The position of the button (0, 1, or 2).
     * @param buttonText The text of the button.
     */
    private void setButtonAction(ToggleButton button, int pos, String buttonText) {
        button.setOnAction(e -> {
            String textToAdd = codeInput.getText();
            if (!(textToAdd.length() < 3)) {
                textToAdd = switch (pos) {
                    case 0 -> buttonText + textToAdd.substring(1);
                    case 1 -> textToAdd.charAt(0) + buttonText + textToAdd.charAt(2);
                    case 2 -> textToAdd.substring(0, 2) + buttonText;
                    default -> textToAdd;
                };
                codeInput.setText(textToAdd);
            } else {
                textToAdd += buttonText;
                codeInput.setText(textToAdd);
            }
        });
    }


    /**
     * Sets the size preferences for a ToggleButton.
     *
     * @param button The ToggleButton for which to set the size preferences.
     */
    private void setButtonSize(ToggleButton button) {
        button.setPrefWidth(60);
        button.setPrefHeight(40);
    }

    /**
     * Clears the input code in the TextField.
     */
    public void inputCodeClear() {
        this.codeInput.clear();
    }


    /**
     * Adds the entered code to the game, triggering the inputCodeCommand.
     *
     * @throws TuringException If there is an issue related to Turing machine operations.
     */
    private void addCode() throws TuringException {
        this.controller.inputCodeCommand(Integer.parseInt(this.codeInput.getText()));
    }


    /**
     * {@inheritDoc}
     * <p>
     * Handles property change events related to the input code and updates the UI accordingly.
     * </p>
     *
     * @param evt The property change event.
     * @see PropertyChangeListener#propertyChange(PropertyChangeEvent)
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (Objects.equals(evt.getPropertyName(), "INPUT CODE MODIFICATION")) {
            if (evt.getNewValue() != null) {
                Code newCode = (Code) evt.getNewValue();
                codeInput.setText(String.valueOf(newCode.getCode()));
            } else {
                codeInput.setText("");
            }
        }
    }
}
