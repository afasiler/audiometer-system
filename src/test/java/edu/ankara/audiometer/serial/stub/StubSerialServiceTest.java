package edu.ankara.audiometer.serial.stub;

import edu.ankara.audiometer.model.enums.ConnectionStatus;
import edu.ankara.audiometer.serial.SerialCommand;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class StubSerialServiceTest {

    private StubSerialService service;

    @BeforeEach
    void setUp() {
        service = new StubSerialService();
    }

    @AfterEach
    void tearDown() {
        service.shutdown();
    }

    @Test
    void testConnectAndDisconnect() throws Exception {
        CountDownLatch connectLatch = new CountDownLatch(1);
        CountDownLatch disconnectLatch = new CountDownLatch(1);

        service.addOnConnectionStatusChanged(status -> {
            if (status == ConnectionStatus.CONNECTED) {
                connectLatch.countDown();
            } else if (status == ConnectionStatus.DISCONNECTED) {
                disconnectLatch.countDown();
            }
        });

        service.connect("COM1", 9600);
        assertTrue(connectLatch.await(3, TimeUnit.SECONDS), "Timeout waiting for connected status");
        assertTrue(service.isConnected());

        service.disconnect();
        assertTrue(disconnectLatch.await(1, TimeUnit.SECONDS), "Timeout waiting for disconnected status");
        assertFalse(service.isConnected());
    }

    @Test
    void testSendCommand() throws Exception {
        CountDownLatch connectLatch = new CountDownLatch(1);
        service.addOnConnectionStatusChanged(status -> {
            if (status == ConnectionStatus.CONNECTED) connectLatch.countDown();
        });
        service.connect("COM3", 9600);
        connectLatch.await(2000, TimeUnit.MILLISECONDS);

        CountDownLatch responseLatch = new CountDownLatch(1);
        AtomicReference<String> response = new AtomicReference<>();

        service.addOnResponseReceived(resp -> {
            response.set(resp);
            responseLatch.countDown();
        });
        
        service.sendCommand(SerialCommand.playTone(1000, 40, "RIGHT"));
        
        // Should take 2 to 5 seconds. Waiting up to 6 seconds.
        boolean received = responseLatch.await(6000, TimeUnit.MILLISECONDS);
        assertTrue(received, "Should receive RESPONSE callback within 6 seconds");
        assertEquals("RESPONSE", response.get());
    }
}
