package io.github.seyeadamaUASZ.flip.helper;

import io.github.seyeadamaUASZ.flip.client.FlipManagerClient;
import io.github.seyeadamaUASZ.flip.dto.FeatureContextDTO;
import io.github.seyeadamaUASZ.flip.dto.FeatureDTO;
import io.github.seyeadamaUASZ.flip.exception.FlipException;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class FlipManagerDialect {
    @Autowired
    private  FlipManagerClient flipManagerClient;

    private static final String CONNECT="connect";
    private static final String FEATURE_BY_NAME="feature-by-name";
    private static final String LIST_FEATURE="list";
    private static final String FEATURE_CONTEXT_BY_NAME="feature-context-by-name";
    private static final String ALL_CONTEXT="all-context";

    @Setter
    @Getter
    private  String uri;

    public FlipManagerDialect(FlipManagerProperties properties){
        this.uri = properties.getUrlServer();
    }

    @PostConstruct
    public void init() throws FlipException {
        log.info("try to connect on server url flip");
        try{
            String urlConnect = this.uri+CONNECT;
            String status = flipManagerClient.testConnect(urlConnect);
            log.info("connect is sucessfully on server Status {} ",status);
        }catch (FlipException e){
            log.error("Error to connect on server !");
            throw new FlipException("Error to connect on server ");
        }
    }

    public  String getConnectInstance(String urlServer) throws FlipException {
        this.uri = urlServer;
        String urlConnect = this.uri+CONNECT;
        return flipManagerClient.testConnect(urlConnect);
    }

    public boolean flipCheck(String feature) throws FlipException {
        String url = this.uri+FEATURE_BY_NAME;
        log.info("url called for flip {} ",url);
        FeatureDTO featureDTO = flipManagerClient.getFeatureByName(url,feature);
        return featureDTO.activate();
    }

    public List<FeatureDTO> allFeatures() throws FlipException {
        String url = this.uri+LIST_FEATURE;
        log.info("url called for all list  flip {} ",url);
        return flipManagerClient.getAllFeatures(url);
    }

    public FeatureContextDTO getFeatureContext(String nameFeature) throws FlipException {
        String url = this.uri+FEATURE_CONTEXT_BY_NAME;
        log.info("url called for get feature context {} ",url);
        return flipManagerClient.getFeatureContextByName(url,nameFeature);
    }

    public List<FeatureContextDTO> allContexts() throws FlipException {
        String url = this.uri+ALL_CONTEXT;
        log.info("url called for get all feature context {} ",url);
        return flipManagerClient.allContext(url);
    }

}
