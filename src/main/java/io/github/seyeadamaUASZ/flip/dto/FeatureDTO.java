package io.github.seyeadamaUASZ.flip.dto;

import java.util.Date;

public record FeatureDTO(String uuid,
                         String nameFeature,
                         boolean activate,
                         String descriptionFeature,
                         Date createAt) {
}
