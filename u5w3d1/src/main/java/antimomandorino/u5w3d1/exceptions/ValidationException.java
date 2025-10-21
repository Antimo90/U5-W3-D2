package antimomandorino.u5w3d1.exceptions;

import lombok.Generated;

import java.util.List;

public class ValidationException extends RuntimeException {
    private List<String> errorsMessages;

    public ValidationException(List<String> errorsMessages) {
        super("Ci sono stati errori di validazione");
        this.errorsMessages = errorsMessages;
    }

    @Generated
    public List<String> getErrorsMessages() {
        return this.errorsMessages;
    }
}
