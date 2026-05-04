package edu.ankara.audiometer.serial;

import edu.ankara.audiometer.model.enums.ConnectionStatus;
import java.util.List;
import java.util.function.Consumer;

public interface SerialService {
    List<String> listAvailablePorts();
    void connect(String portName, int baudRate) throws Exception;
    void disconnect();
    boolean isConnected();
    void sendCommand(String command);
    void addOnResponseReceived(Consumer<String> callback);
    void addOnConnectionStatusChanged(Consumer<ConnectionStatus> callback);
}
