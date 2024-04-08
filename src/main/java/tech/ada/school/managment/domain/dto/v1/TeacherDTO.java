package tech.ada.school.managment.domain.dto.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class TeacherDTO {
    private UUID id;

    @NotEmpty(message = "O nome não pode ser vazio")
    @NotBlank(message = "O nome não pode estar em branco")
    private String name;
}
