package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationApp;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.List;

public class SimulationPresenter implements MapChangeListener {
    private WorldMap worldMap;
    @FXML
    public GridPane mapGrid;
    @FXML
    private TextField listOfMovesTextFiled;
    @FXML
    private Button startButton;
    @FXML
    private Label movementDescriptionLabel;
    @FXML
    private Label infoLabel;
    @FXML
    private HBox basicHBox;
    private final Integer maxSizeOfMap = 400;

    public void setWorldMap(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public void drawMap() {
//        clearGrid();
//        var bounds = worldMap.getCurrentBounds();
//        var boundsSubtracted = bounds.rightUpperCorner().subtract(bounds.leftLowerCorner());
//        int minX = bounds.leftLowerCorner().getX();
//        int minY = bounds.leftLowerCorner().getY();
//        int maxX = bounds.rightUpperCorner().getX();
//        int maxY = bounds.rightUpperCorner().getY();
//        int columns = boundsSubtracted.getX();
//        int rows = boundsSubtracted.getY();
//        int cellWidth = maxSizeOfMap / boundsSubtracted.getX();
//        int cellHeight = maxSizeOfMap / boundsSubtracted.getY();
//        createGrid(minX, minY, columns, rows, cellWidth, cellHeight);
//        addElementsToGrid(minX, minY, columns, rows);


    }

    private void createGrid(int minX, int minY, int columns, int rows, int cellWidth, int cellHeight) {
        mapGrid.getColumnConstraints().add(new ColumnConstraints(cellWidth));
        //x column
        for (int i = 0; i < columns; i++) {
            var label = new Label(Integer.toString(i + minX));
            GridPane.setHalignment(label, HPos.CENTER);
            mapGrid.getColumnConstraints().add(new ColumnConstraints(cellWidth));
            mapGrid.add(label, i + 1, 0);
        }
        //y column
        mapGrid.getRowConstraints().add(new RowConstraints(cellHeight));
        for (int i = 0; i < rows; i++) {
            var label = new Label(Integer.toString(rows - i + minY));
            GridPane.setHalignment(label, HPos.CENTER);
            mapGrid.getRowConstraints().add(new RowConstraints(cellHeight));
            mapGrid.add(label, 0, i + 1);
        }
        //x/y label
        var label = new Label("y/x");
        GridPane.setHalignment(label, HPos.CENTER);
        mapGrid.add(label, 0, 0);

    }

    private void addElementsToGrid(int minX, int minY, int columns, int rows) {
        for (int x = 1; x < columns + 2; x++) {
            for (int y = rows + 1; y >= 0; y--) {
                var worldElement = worldMap.objectAt(new Vector2d(x - 1 + minX, rows - y + minY));
                if (worldElement != null) {
                    Label mapElement = new Label(worldElement.toString());
                    mapGrid.add(mapElement, x, y + 1);
                    GridPane.setHalignment(mapElement, HPos.CENTER);
                }
            }
        }

    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        setWorldMap(worldMap);
        Platform.runLater(() -> {
            drawMap();
            //infoLabel.setText(worldMap.toString());
            startButton.setText("Start simulation");
            movementDescriptionLabel.setText(message);
            GridPane.setHalignment(listOfMovesTextFiled, HPos.CENTER);
            GridPane.setHalignment(startButton, HPos.CENTER);
            // GridPane.setHalignment(infoLabel, HPos.CENTER);
        });
    }

    @FXML
    public void onSimulationStartClicked() {
        String[] args = listOfMovesTextFiled.getText().split(" ");

        List<MoveDirection> directions = OptionsParser.convertToMoveDirections(args);
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(8, 10));
        var map = new GrassField(12);
        map.subscribeMapChangeListener(this);

        var simulation = new Simulation(positions, directions, map);
        var simulationEngine = new SimulationEngine(List.of(simulation));
        simulationEngine.runSync();
    }

    private void clearGrid() {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().getFirst()); // hack to retain visible grid lines
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }
    @FXML
    private void newGame(){
        SimulationApp simulationApp = new SimulationApp();
        simulationApp.start(new Stage());
    }
}
