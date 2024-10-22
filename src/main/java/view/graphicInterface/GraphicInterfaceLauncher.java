package view.graphicInterface;

import controller.GraphicController;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.Problems;
import model.TuringException;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

/**
 * GraphicInterfaceLauncher initializes and manages the graphical user interface for the Turing game.
 * It sets up the main menu for choosing the game level and launches the second window for playing the game.
 * Implements PropertyChangeListener to handle changes in game properties such as input code, score, and turns.
 * <p>
 * The class uses JavaFX controls and layouts to create a visually appealing and interactive user interface.
 * </p>
 *
 * @author JAWAD EL HARRASI
 * @version 1.0
 */
public class GraphicInterfaceLauncher implements PropertyChangeListener {

    private final TextField score = new TextField("0");
    private final TextField turn = new TextField("1");
    private final ChoiceBox<String> levelChoiceBox;
    private boolean random = false;
    private CodeInput codeInput;
    private GraphicController controller;

    /**
     * Constructs the GraphicInterfaceLauncher and sets up the main menu for choosing the game level.
     * Initializes JavaFX controls and layouts for the main menu.
     *
     * @param primaryStage The primary stage for displaying the main menu.
     */
    public GraphicInterfaceLauncher(Stage primaryStage) {
        StackPane root = new StackPane();
        root.setAlignment(Pos.CENTER);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);

        Problems problems = new Problems();
        levelChoiceBox = new ChoiceBox<>();
        levelChoiceBox.setPrefWidth(300);
        levelChoiceBox.setValue(problems.toString().split("\n")[0]);
        for (int i = 0; i < 16; i++) {
            levelChoiceBox.getItems().add(problems.toString().split("\n")[i]);
        }

        gridPane.add(levelChoiceBox, 0, 0);

        Button start = new Button("START");
        Button startRandom = new Button("START WITH A RANDOM LEVEL");
        start.setPrefWidth(300);
        startRandom.setPrefWidth(300);
        start.setOnAction(e -> {
            try {
                secondWindowLauncher();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("ERROR");
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }
        });
        startRandom.setOnAction(e -> {
            try {
                this.random = true;
                secondWindowLauncher();
                primaryStage.close();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("ERROR");
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }
        });
        gridPane.add(start, 0, 1);
        gridPane.add(startRandom, 0, 2);

        Image image = new Image("/turingMachineImages/imageComplète.png");
        ImageView imageView = new ImageView(image);

        // Cover the entire scene
        imageView.fitWidthProperty().bind(primaryStage.widthProperty());
        imageView.fitHeightProperty().bind(primaryStage.heightProperty());

        root.getChildren().add(imageView);
        root.getChildren().add(gridPane);

        Scene scene = new Scene(root, 1000, 650);
        primaryStage.setScene(scene);
        primaryStage.setTitle("CHOOSING MODE");
        primaryStage.show();
    }

    /**
     * Launches the second window for playing the Turing game.
     * Initializes the game, sets up the game board, and handles key events for undo and redo actions.
     *
     * @throws TuringException If there is an issue related to Turing machine operations.
     */
    private void secondWindowLauncher() throws TuringException {
        int level;

        if (!random) {
            level = Integer.parseInt(levelChoiceBox.getValue().split(" ")[2]);
        } else {
            Random randomInt = new Random();
            level = randomInt.nextInt(16) + 1;
        }

        this.controller = new GraphicController(level);
        this.controller.lunchTheGame();
        controller.addObserver(this);

        Stage secondStage = new Stage();
        VBox vBox = new VBox();
        HBox hBox = new HBox();


        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        secondStage.setX(primaryScreenBounds.getMinX());
        secondStage.setY(primaryScreenBounds.getMinY());
        secondStage.setWidth(primaryScreenBounds.getWidth());
        secondStage.setHeight(primaryScreenBounds.getHeight());


        hBox.setPrefHeight(primaryScreenBounds.getHeight() - primaryScreenBounds.getHeight() / 2.3);



        for (int i = 0; i < controller.getIndexesValidators().size(); i++) {
            int asciiCode = 65 + i;
            char letter = (char) asciiCode;
            System.out.println(controller.getIndexesValidators().get(i));
            ValidatorGraphic validator = new ValidatorGraphic(this.controller, controller.getIndexesValidators().get(i), letter, i);
            validator.setPrefWidth(primaryScreenBounds.getWidth() / controller.getIndexesValidators().size());

            controller.addObserverForValidator(i,validator);
            hBox.getChildren().add(validator);
        }
        this.codeInput = new CodeInput(this.controller);
        this.codeInput.setAlignment(Pos.BASELINE_CENTER);
        controller.addObserver(this.codeInput);

        GridPane bottomPage = new GridPane();
        Button nextTurn = new Button("NEXT TURN");
        nextTurn.setOnAction(actionEvent -> {
            try {
                controller.nextTurnCommand();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("ERROR");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        });
        bottomPage.setAlignment(Pos.CENTER);
        bottomPage.add(codeInput, 1, 0);
        bottomPage.add(nextTurn, 9, 1);

        bottomPage.add(score, 6, 1);
        Label scoreLabel = new Label("SCORE");
        bottomPage.add(scoreLabel, 5, 1);

        bottomPage.add(turn, 6, 2);
        Label turnLabel = new Label("TURN");
        bottomPage.add(turnLabel, 5, 2);


        Button quit = new Button("QUIT     ");
        quit.setOnAction(event -> controller.end());
        quit.setTranslateX(50);
        bottomPage.add(quit, 8, 2);


        Label textForUndo = new Label("for undo press : ctrl + Z \n" +
                "for redo press : ctrl + Y");
        bottomPage.add(textForUndo, 8, 0);


        vBox.setOnKeyPressed(event -> {
            if (event.isControlDown() && event.getCode() == KeyCode.Z) {
                try {
                    controller.undo();
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("ERROR");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
            } else if (event.isControlDown() && event.getCode() == KeyCode.Y) {
                try {
                    controller.redo();
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("ERROR");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
            }
        });

        vBox.getChildren().add(hBox);
        vBox.getChildren().add(bottomPage);

        Scene scene = new Scene(vBox);
        secondStage.setTitle("TURNING MACHINE");
        secondStage.setScene(scene);
        secondStage.show();
    }

    /**
     * {@inheritDoc}
     * <p>
     * Handles property change events related to the game and updates the UI accordingly.
     * </p>
     *
     * @param evt The property change event.
     * @see PropertyChangeListener#propertyChange(PropertyChangeEvent)
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "INPUT CODE MODIFICATION" -> this.codeInput.inputCodeClear();
            case "SCORE MODIFICATION" -> this.score.setText(evt.getNewValue().toString());
            case "TURN MODIFICATION" -> this.turn.setText(evt.getNewValue().toString());
        }
    }
}



