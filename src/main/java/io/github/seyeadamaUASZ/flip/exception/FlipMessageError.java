package io.github.seyeadamaUASZ.flip.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlipMessageError {
    private String exceptionCode;
    private String code;
    private String message;
    private String timestamp;
    private Integer status;

}
