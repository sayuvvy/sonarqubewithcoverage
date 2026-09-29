package com.example.demo.context;

import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Binds an incoming (or generated) correlation id to {@link RequestContext#CORRELATION_ID}
 * for the duration of the request, using ScopedValue.where(...).run(...).
 */
@Component
public class CorrelationIdFilter extends OncePerRequestFilter {

    private static final String HEADER = "X-Correlation-Id";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String correlationId = request.getHeader(HEADER);
        if (correlationId == null || correlationId.isBlank()) {
            correlationId = UUID.randomUUID().toString();
        }
        response.setHeader(HEADER, correlationId);

        try {
            ScopedValue.where(RequestContext.CORRELATION_ID, correlationId)
                    .run(() -> doFilter(request, response, chain));
        } catch (WrappedServletException e) {
            throw e.servletException();
        } catch (WrappedIOException e) {
            throw e.ioException();
        }
    }

    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
        try {
            chain.doFilter(request, response);
        } catch (ServletException e) {
            throw new WrappedServletException(e);
        } catch (IOException e) {
            throw new WrappedIOException(e);
        }
    }

    private static final class WrappedServletException extends RuntimeException {
        WrappedServletException(ServletException cause) {
            super(cause);
        }

        ServletException servletException() {
            return (ServletException) getCause();
        }
    }

    private static final class WrappedIOException extends RuntimeException {
        WrappedIOException(IOException cause) {
            super(cause);
        }

        IOException ioException() {
            return (IOException) getCause();
        }
    }
}
