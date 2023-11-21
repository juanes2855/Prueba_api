package certificacion.enums;

public enum DatosGenerales {
    RUTA_BODY("src/test/resources/Data/crearBody.json"),
    BASE("base "),
    BODY("body"),
    USER_NAME_TEXT("UserName"),
    ID_TRANSACTION_TEXT("IdTransaccionCanal"),

    IDENTIFICATION_NUMBER("identificationNumber"),
    RUTA_BODY_REGISTRO("src/test/resources/Data/registro.json"),
    RUTA_BODY_CREAR_LOG("src/test/resources/Data/crearLog.json"),
    RUTA_BODY_INICIO("src/test/resources/Data/inicioSesion.json"),
    RUTA_BODY_ACTUALIZAR("src/test/resources/Data/actualizar.json"),
    RUTA_BODY_ACTUALIZAR_CON_PERFIL("src/test/resources/Data/actualizarConPerfil.json"),
    RUTA_BODY_ENVIAR_EMAIL("src/test/resources/Data/envioEmail.json"),
    RUTA_BODY_CAMBIAR_PASSWORD("src/test/resources/Data/cambiarContrasenia.json"),
    TRANSACTION_ID("\\$\\{" + "transactionId" + "}"),
    TRANSACTION_ID_VALOR("transactionId"),
    USER_ID("\""+"${user_id}"+"\""),
    EMAIL("${email}"),
    PASSWORD("${password}"),
    USERNAME("${username}"),
    IS_CONTACT_INFO_PUBLIC("${isContactInfoPublic}"),
    CORRESPONDENCE_ADDRESS("${correspondenceAddress}"),
    BIO("${bio}"),
    ORGANIZATION("${organization}"),
    COUNTRY("${country}"),
    FACEBOOK_LINK("${faceBookLink}"),
    NEW_PASSWORD("${new_password}"),
    EMAIL_VALOR("email"),
    PASSWORD_VALOR("password"),
    APLICATION("${application}"),
    TIPO_LOG("${tipoLog}"),
    MODULO("${modulo}"),
    FECHA_HORA_GENERACION("${fechaHoraGeneracion}"),
    RESUMEN("${resumen}"),
    DESCRIPCION("${descripcion}"),
    INICIAR_SESION("iniciar"),
    USERNAME_VALOR("username"),
    USER_ID_VALOR("user_id"),
    DATA_TRANSACTION_ID("transactionId"),
    ENDPOINT("EndPoint"),
    FORMATO_FECHA_HORA("HHmmssS"),
    REQUEST_BODY("\"Request body: {0} \"\n"),
    RESPONSE("Response "),
    CREAR("crear"),
    EMAILS("emails"),
    TOKEN("message");
    String msj;
    DatosGenerales(String msj) {
        this.msj = msj;
    }
    public String getMsj() {
        return msj;
    }
}