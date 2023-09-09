package certificacion.exceptions;

public class ExcepcionGeneral extends AssertionError {
    private static final long serialVersionUID = 1L;
    public ExcepcionGeneral(String message, Throwable cause) {
        super(message, cause);
    }
}
