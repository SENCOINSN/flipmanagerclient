package io.github.seyeadamaUASZ.flip.dto;

import java.util.Date;
import java.util.List;

public record FeatureContextDTO(String uuid,
                                String nameFeature,
                                String targetUser,
                                String targetGroup,
                                List<String> userGroups,
                                Date createAt) {
}
