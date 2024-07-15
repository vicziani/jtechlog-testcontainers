package employees;

import jakarta.validation.constraints.NotBlank;

public record EmployeeResource(Long id, @NotBlank String name) {

    public EmployeeResource(String name) {
        this(null, name);
    }
}
