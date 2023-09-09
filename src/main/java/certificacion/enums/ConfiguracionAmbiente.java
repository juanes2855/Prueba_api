package certificacion.enums;


public enum ConfiguracionAmbiente {
    CONSTANT_ENV("Env"),
    CONSTANT_ENV_PRUEBAS("prub"),
    CONSTANT_ENV_CERTI("certi"),
    HOST("Host"),
    HOST_CERTI("certvalidacionidentidad.tuyacloud.corp"),
    HOST_PRUB("prubvalidacionidentidad.tuyacloud.corp"),
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