package error;

import java.util.UUID;

public class PersonsApiBaseException extends RuntimeException {
    private final UUID errorid;
    public PersonsApiBaseException(String massage){
        super(massage);
        this.errorid = UUID.randomUUID();


    }

    public UUID getErrorId() {
        return errorid;
    }
}
