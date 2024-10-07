package io.github.seyeadamaUASZ.flip.caller;

import io.github.seyeadamaUASZ.flip.exception.FlipException;
import io.github.seyeadamaUASZ.flip.exception.FlipMessageError;
import io.github.seyeadamaUASZ.flip.helper.JsonUtilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@Slf4j
public class FlipCaller {

    private final RestTemplate restTemplate;


    protected FlipCaller(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public <T> T getCall(UriComponentsBuilder builder, ParameterizedTypeReference<T> type) throws FlipException {
        long start = System.currentTimeMillis();
        try {
            log.info("Start calling  sencoin Api url : {} in {} ms", builder.toUriString(),start);
            return restTemplate.exchange(builder.build().toString(), HttpMethod.GET, null, type).getBody();

        } catch (Exception e) {
            return handleException(e);
        } finally {
            log.info("End calling  sencoin Api url : {} in {} ms", builder.toUriString(), System.currentTimeMillis() - start);
        }
    }


    public <T> T postCall(UriComponentsBuilder builder, Object body, ParameterizedTypeReference<T> type) throws  FlipException {
        long start = System.currentTimeMillis();
        try {
            log.info("Start calling  sencoin Api url : {} in {} ms", builder.toUriString(),start);
            return restTemplate.exchange(builder.build().toString(), HttpMethod.POST, createRequestEntity(body), type).getBody();
        } catch (Exception e) {
            return handleException(e);
        } finally {
            log.info("End calling  sencoin Api url : {} in {} ms", builder.toUriString(), System.currentTimeMillis() - start);
        }
    }


    public <T> T putCall(UriComponentsBuilder builder, Object body, ParameterizedTypeReference<T> type) throws FlipException {
        long start = System.currentTimeMillis();
        try {
            log.info("Start calling  sencoin Api url : {} in {} ms", builder.toUriString(),start);
            if(body == null){
                return restTemplate.exchange(builder.build().toString(), HttpMethod.PUT, null, type).getBody();
            }else{
                return restTemplate.exchange(builder.build().toString(), HttpMethod.PUT, createRequestEntity(body), type).getBody();
            }

        } catch (Exception e) {
            return handleException(e);
        } finally {
            log.info("End calling  sencoin Api url : {} in {} ms", builder.toUriString(), System.currentTimeMillis() - start);
        }
    }

    public ResponseEntity deleteCall(UriComponentsBuilder builder) throws FlipException {
        long start = System.currentTimeMillis();
        try {
            log.info("Start calling  sencoin Api url : {} in {} ms", builder.toUriString(),start);
            return restTemplate.exchange(builder.build().toString(), HttpMethod.DELETE, null, ResponseEntity.class);
        } catch (Exception e) {
            return handleException(e);
        } finally {
            log.info("End calling  sencoin Api url : {} in {} ms", builder.toUriString(), System.currentTimeMillis() - start);
        }
    }


    private <T> T handleException(Exception e) throws FlipException {
        if (e instanceof HttpClientErrorException && HttpStatus.NOT_FOUND.equals(((HttpClientErrorException) e).getStatusCode())) {
            HttpClientErrorException notFoundException = (HttpClientErrorException) e;

            log.error("Http error while calling  sencoin Api : Status code : {}, error : {}",
                    notFoundException.getStatusCode(),
                    notFoundException.getResponseBodyAsString());
            FlipMessageError message = JsonUtilities.getMessage(notFoundException.getResponseBodyAsString());
            if (message != null) {
                throw new FlipException(message.getMessage());
            }

        }
        log.error("Error while calling  sencoin Api : ", e);
        throw new FlipException(e.getMessage());
    }

    private HttpEntity createRequestEntity(Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(body, headers);
    }
}
