package io.github.seyeadamaUASZ.flip.helper;

import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

public class UriParametersBuilder {
    private UriParametersBuilder() {
    }

    public static void build(UriComponentsBuilder uri, Map<String, String> params) {
        if (!params.isEmpty()) {
            params.forEach(uri::queryParam);
        }

    }
}
