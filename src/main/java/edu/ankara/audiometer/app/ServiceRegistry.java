package edu.ankara.audiometer.app;

import edu.ankara.audiometer.audiometry.HughsonWestlakeService;
import edu.ankara.audiometer.audiometry.stub.StubHughsonWestlakeService;
import edu.ankara.audiometer.serial.SerialService;
import edu.ankara.audiometer.serial.stub.StubSerialService;

public class ServiceRegistry {
    private ServiceRegistry() {
    }

    public static final SerialService serialService = new StubSerialService();
    public static final HughsonWestlakeService hwService = new StubHughsonWestlakeService();
}
