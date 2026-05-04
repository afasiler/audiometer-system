package com.audiometer.service;

import com.audiometer.service.stub.StubHughsonWestlakeService;
import com.audiometer.service.stub.StubSerialService;

public class ServiceRegistry {
    // Singleton instances for CP testability
    public static final SerialService serialService = new StubSerialService();
    public static final HughsonWestlakeService hwService = new StubHughsonWestlakeService();
}
