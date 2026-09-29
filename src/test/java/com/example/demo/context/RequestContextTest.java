package com.example.demo.context;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RequestContextTest {

    @Test
    void returnsUnboundWhenNoValueIsBound() {
        assertThat(RequestContext.currentCorrelationId()).isEqualTo("unbound");
    }

    @Test
    void returnsBoundValueWhenScopedValueIsSet() {
        String result = ScopedValue.where(RequestContext.CORRELATION_ID, "test-id")
                .call(RequestContext::currentCorrelationId);

        assertThat(result).isEqualTo("test-id");
    }

    @Test
    void privateConstructorExistsOnlyToBlockInstantiation() throws Exception {
        var constructor = RequestContext.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        assertThat(constructor.newInstance()).isNotNull();
    }
}
