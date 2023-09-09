package certificacion.enums;

public enum Mensajes {
    CONST_VALIDAR_USUARIO("F070"),
    CONST_CREAR_USUARIO("200"),
    CONST_CONSULTAR_TRANSACCION("F102"),
    CONST_VALIDAR_ERROR("errors.IdentificationNumber"),
    CONST_CODIGO("code"),
    CONST_MENSAJE("message"),
    CONST_NUMERO_DOCUMENTO("data.identificationNumber"),
    MENSAJE_ERROR("Se produjo un error comparando los mensajes"),
    MENSAJE_ERROR_CODIGO("Se produjo un error comparando el estado del código"),
    MENSAJE_ERROR_DOCUMENTO("Se produjo un error comparando el numero de identificación"),
    MENSAJE_USUARIO_BD("usuario conexion bd azure "),
    MENSAJE_CONEXION_BD("conexion exitosa base de datos solicitudes");
    String msj;
    Mensajes(String msj) {
        this.msj = msj;
    }
    public String getMsj() {
        return msj;
    }
}