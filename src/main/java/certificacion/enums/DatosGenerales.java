package certificacion.enums;

public enum DatosGenerales {
    RUTA_BODY("src/test/resources/Data/crearBody.json"),
    BASE("base "),
    BODY("body"),
    USER_NAME_TEXT("UserName"),
    ID_TRANSACTION_TEXT("IdTransaccionCanal"),

    IDENTIFICATION_NUMBER("identificationNumber"),
    RUTA_BODY_REGISTRO("src/test/resources/Data/registro.json"),
    TRANSACTION_ID("\\$\\{" + "transactionId" + "}"),
    TRANSACTION_ID_VALOR("transactionId"),
    USER_ID("\""+"${user_id}"+"\""),
    EMAIL("${email}"),
    PASSWORD("${password}"),
    USERNAME("${username}"),
    EMAIL_VALOR("email"),
    PASSWORD_VALOR("password"),
    USERNAME_VALOR("username"),
    USER_ID_VALOR("user_id"),
    DATA_TRANSACTION_ID("transactionId"),
    ENDPOINT("EndPoint"),
    FORMATO_FECHA_HORA("HHmmssS"),
    REQUEST_BODY("\"Request body: {0} \"\n"),
    RESPONSE("Response ");
    String msj;
    DatosGenerales(String msj) {
        this.msj = msj;
    }
    public String getMsj() {
        return msj;
    }
}