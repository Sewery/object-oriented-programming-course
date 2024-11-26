package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine {
    private final List<Thread> threads;
    private final List<Simulation> simulations;
    private final ExecutorService executorService;
    public SimulationEngine(List<Simulation> simulations) {
        this.simulations = simulations;
        this.threads = new ArrayList<>();
        this.executorService = Executors.newFixedThreadPool(4);
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
    public void runAsyncInThreadPool(){
        simulations.forEach(simulation ->
            executorService.submit(simulation::run)
        );
        executorService.shutdown();
        awaitSimulationsEnd();
    }
    public void awaitSimulationsEnd() {
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        });
        try {
            if(!executorService.awaitTermination(10, TimeUnit.SECONDS)){
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
}
