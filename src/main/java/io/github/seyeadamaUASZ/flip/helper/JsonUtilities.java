package io.github.seyeadamaUASZ.flip.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.seyeadamaUASZ.flip.exception.FlipMessageError;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtilities {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static FlipMessageError getMessage(final String jsonMessage) {
        try {
            return objectMapper.readValue(
                    jsonMessage,
                    objectMapper.getTypeFactory().constructType(FlipMessageError.class)
            );
        } catch (Exception e) {
            log.error("Error while parsing json message : {}", jsonMessage);
            log.error(e.getMessage());
            return null;
        }

    }
}
