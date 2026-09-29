package com.example.demo.sample;

/**
 * Demonstrates JDK 25's flexible constructor bodies (JEP 513): a subclass may
 * now run statements - including argument validation - before the super()
 * call, as long as it does not touch the instance being constructed.
 */
public final class Bicycle extends Vehicle {

    private final int wheelSizeInInches;

    public Bicycle(int wheelSizeInInches) {
        if (wheelSizeInInches <= 0) {
            throw new IllegalArgumentException("wheelSizeInInches must be positive");
        }
        super(2);
        this.wheelSizeInInches = wheelSizeInInches;
    }

    public int wheels() {
        return wheels;
    }

    public int wheelSizeInInches() {
        return wheelSizeInInches;
    }
}
