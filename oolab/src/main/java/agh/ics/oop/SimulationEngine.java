package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine {
    private List<Thread> threads;
    private final List<Simulation> simulations;
    public SimulationEngine(List<Simulation> simulations) {
        this.simulations = simulations;
        this.threads = new ArrayList<>();
    }
    public void runSync(){
        simulations.forEach(Simulation::run);
    }
    public void runAsync(){
        simulations.forEach(simulation -> {
            var thread = new Thread(simulation::run);
            threads.add(thread);
            thread.start();
        });
        awaitSimulationsEnd();
    }
    public void awaitSimulationsEnd(){
            threads.forEach(thread -> {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
            });
    }
}
