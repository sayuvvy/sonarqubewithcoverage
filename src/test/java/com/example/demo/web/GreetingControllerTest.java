package com.example.demo.web;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GreetingControllerTest {

    private final GreetingController controller = new GreetingController();

    @Test
    void greetsByName() {
        Greeting greeting = controller.greet("Claude");

        assertThat(greeting.message()).isEqualTo("Hello, Claude!");
        assertThat(greeting.correlationId()).isEqualTo("unbound");
        assertThat(greeting.javaVersion()).isEqualTo(Runtime.version().toString());
        assertThat(greeting).isEqualTo(new Greeting(greeting.message(), greeting.correlationId(), greeting.javaVersion()));
        assertThat(greeting.toString()).contains("Claude");
    }
}
