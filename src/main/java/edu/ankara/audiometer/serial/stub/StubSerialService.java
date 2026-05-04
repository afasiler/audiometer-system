package edu.ankara.audiometer.serial.stub;

import edu.ankara.audiometer.model.enums.ConnectionStatus;
import edu.ankara.audiometer.serial.SerialCommand;
import edu.ankara.audiometer.serial.SerialService;
import javafx.application.Platform;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class StubSerialService implements SerialService {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final Random random = new Random();
    
    private boolean connected = false;
    private final List<Consumer<String>> onResponseReceived = new CopyOnWriteArrayList<>();
    private final List<Consumer<ConnectionStatus>> onConnectionStatusChanged = new CopyOnWriteArrayList<>();

    private void fireConnectionStatusChanged(ConnectionStatus status) {
        runOnFxThread(() -> onConnectionStatusChanged.forEach(c -> c.accept(status)));
    }

    private void fireResponseReceived(String response) {
        runOnFxThread(() -> onResponseReceived.forEach(c -> c.accept(response)));
    }

    @Override
    public List<String> listAvailablePorts() {
        return Arrays.asList("COM1", "COM2", "COM3");
    }

    @Override
    public void connect(String portName, int baudRate) throws Exception {
        if (connected) return;
        
        fireConnectionStatusChanged(ConnectionStatus.CONNECTING);

        scheduler.schedule(() -> {
            connected = true;
            fireConnectionStatusChanged(ConnectionStatus.CONNECTED);
        }, 1500, TimeUnit.MILLISECONDS);
    }

    @Override
    public void disconnect() {
        connected = false;
        fireConnectionStatusChanged(ConnectionStatus.DISCONNECTED);
    }

    @Override
    public boolean isConnected() {
        return connected;
    }

    @Override
    public void sendCommand(String command) {
        if (!connected) return;
        
        if (command.startsWith(SerialCommand.PLAY_PREFIX)) {
            int delayMs = 2000 + random.nextInt(3000); // 2 to 5 seconds
            scheduler.schedule(() -> {
                if (connected) {
                    fireResponseReceived("RESPONSE");
                }
            }, delayMs, TimeUnit.MILLISECONDS);
        }
    }

    @Override
    public void addOnResponseReceived(Consumer<String> callback) {
        this.onResponseReceived.add(callback);
    }

    @Override
    public void addOnConnectionStatusChanged(Consumer<ConnectionStatus> callback) {
        this.onConnectionStatusChanged.add(callback);
    }
    
    private void runOnFxThread(Runnable action) {
        try {
            Platform.runLater(action);
        } catch (IllegalStateException e) {
            // Toolkit not initialized (e.g. in Unit Tests), just run directly
            action.run();
        }
    }
    
    public void shutdown() {
        scheduler.shutdownNow();
    }
}
