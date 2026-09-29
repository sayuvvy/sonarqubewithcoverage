package com.example.demo.context;

/**
 * Holds the per-request correlation id using a JDK 25 {@link ScopedValue} (JEP 506)
 * instead of a ThreadLocal. ScopedValues are immutable for the dynamic scope in
 * which they are bound, which makes them safe to read from virtual threads spawned
 * while handling a request.
 */
public final class RequestContext {

    public static final ScopedValue<String> CORRELATION_ID = ScopedValue.newInstance();

    private RequestContext() {
    }

    public static String currentCorrelationId() {
        return CORRELATION_ID.isBound() ? CORRELATION_ID.get() : "unbound";
    }
}
