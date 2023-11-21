package certificacion.enums;


public enum ConfiguracionAmbiente {

    BASE_URL("http://localhost:8080"),
    BASE_URL_LOGS("http://localhost:8081"),
    BASE_URL_GATEWAY("http://localhost:8083"),
    CONTENT_TYPE("Content-Type"),
    CONTENT_TYPE_JSON("application/json"),
    AUTHORIZATION("Authorization");
    String msj;
    ConfiguracionAmbiente(String msj) {
        this.msj = msj;
    }
    public String getMsj() {
        return msj;
    }
}