package io.github.seyeadamaUASZ.flip.client;

import io.github.seyeadamaUASZ.flip.caller.FlipCaller;
import io.github.seyeadamaUASZ.flip.dto.FeatureContextDTO;
import io.github.seyeadamaUASZ.flip.dto.FeatureDTO;
import io.github.seyeadamaUASZ.flip.exception.FlipException;
import io.github.seyeadamaUASZ.flip.helper.UriParametersBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class FlipManagerClient {
    private final FlipCaller flipCaller;

    public FlipManagerClient(FlipCaller flipCaller) {
        this.flipCaller = flipCaller;
    }

    public List<FeatureDTO> getAllFeatures(String uri) throws FlipException {
        return flipCaller.getCall(buildUri(uri, null), new ParameterizedTypeReference<List<FeatureDTO>>() {
        });
    }

    public FeatureDTO getFeature(String uri, String uuid) throws FlipException {
        return flipCaller.getCall(buildUri(uri, Map.of("uuid", uuid)), new ParameterizedTypeReference<FeatureDTO>() {
        });
    }

    public FeatureDTO getFeatureByName(String uri, String nameFeature) throws FlipException {
        return flipCaller.getCall(buildUri(uri, Map.of("name", nameFeature)), new ParameterizedTypeReference<FeatureDTO>() {
        });
    }

    public String testConnect(String uri) throws FlipException {
        return flipCaller.getCall(buildUri(uri, null), new ParameterizedTypeReference<String>() {
        });
    }

    public FeatureContextDTO getFeatureContextByName(String uri,String name) throws FlipException {
        return flipCaller.getCall(buildUri(uri, Map.of("name", name)), new ParameterizedTypeReference<FeatureContextDTO>() {
        });
    }

    public List<FeatureContextDTO> allContext(String uri) throws FlipException {
        return flipCaller.getCall(buildUri(uri, null), new ParameterizedTypeReference<List<FeatureContextDTO>>() {
        });
    }

    private UriComponentsBuilder buildUri(String url, Map<String, String> params) {
        UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(url);
        if (params != null && !params.isEmpty()) {
            UriParametersBuilder.build(uri, params);
        }
        return uri;
    }
}
