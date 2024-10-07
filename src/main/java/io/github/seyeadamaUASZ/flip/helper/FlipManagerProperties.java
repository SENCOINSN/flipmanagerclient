package io.github.seyeadamaUASZ.flip.helper;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Component
@ConfigurationProperties(prefix = "flip")
public class FlipManagerProperties {
    private String urlServer;

    public void setUrlServer(String urlServer) {
        this.urlServer = urlServer;
    }
}
