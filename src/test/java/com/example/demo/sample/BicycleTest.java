package com.example.demo.sample;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BicycleTest {

    @Test
    void buildsBicycleWithTwoWheels() {
        Bicycle bicycle = new Bicycle(26);

        assertThat(bicycle.wheels()).isEqualTo(2);
        assertThat(bicycle.wheelSizeInInches()).isEqualTo(26);
    }

    @Test
    void rejectsNonPositiveWheelSize() {
        assertThatThrownBy(() -> new Bicycle(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("wheelSizeInInches must be positive");
    }
}
