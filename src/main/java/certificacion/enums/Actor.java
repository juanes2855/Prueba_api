package certificacion.enums;

public enum Actor {
    VALIDADOR_SERVICIOS("API Gestion Usuarios");
    String nombre;
    Actor(String msj) {
        this.nombre = msj;
    }
    public String getNombre() {
        return nombre;
    }
}
