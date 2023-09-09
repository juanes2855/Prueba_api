package certificacion.enums;


public enum ConfiguracionAmbiente {

    CONTENT_TYPE("Content-Type"),
    CONTENT_TYPE_JSON("application/json");
    String msj;
    ConfiguracionAmbiente(String msj) {
        this.msj = msj;
    }
    public String getMsj() {
        return msj;
    }
}