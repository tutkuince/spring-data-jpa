package com.incetutku.auditing.auditor;

import org.springframework.data.domain.AuditorAware;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(Arrays.asList("john21", "jane22", "joe23")
                .get(new Random().nextInt(3)));
    }
}
