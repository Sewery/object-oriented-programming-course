package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.RectangularMap;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.presenter.SimulationPresenter;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SimulationApp extends Application {
    private SimulationPresenter presenter;
    public void start(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));
        BorderPane viewRoot;
        try {
            viewRoot = loader.load();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return;
        }
        presenter = loader.getController();
        String[] args =getParameters().getRaw().toArray(new String[0]);
        System.out.println(Arrays.toString(args));
        List<MoveDirection> directions = OptionsParser.convertToMoveDirections(args);
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(8, 10));
        var map =new RectangularMap(12,12);
        presenter.setWorldMap(map);
        map.subscribeMapChangeListener(presenter);
        var simulation = new Simulation(positions, directions, map);
        var simulationEngine = new SimulationEngine(List.of(simulation));
        simulationEngine.runSync();
        configureStage(primaryStage,viewRoot);
        primaryStage.show();
    }
    private void configureStage(Stage primaryStage, BorderPane viewRoot) {
        var scene = new Scene(viewRoot);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulation app");
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }
}
