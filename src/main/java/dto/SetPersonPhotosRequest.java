package dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class SetPersonPhotosRequest {
    @NotNull
    private Set<UUID> personPhotosIds;


}

