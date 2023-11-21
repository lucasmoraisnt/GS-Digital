package fiap.gs.digital.model;

public record RestValidationError(
        Integer code,
        String field,
        String message) {

}
