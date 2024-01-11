package com.veterinaria_back.util;

public class MessagesConstant {

    public MessagesConstant() {
    }

    // Mensajes de éxito
    public static final int SUCCESS_CODE = 0;
    public static final String SUCCESS_MESSAGE = "Operación exitosa";

    // Mensajes de error genérico
    public static final int ERROR_DEFAULT_CODE = 1;
    public static final String ERROR_DEFAULT_MESSAGE = "Error realizando la operación";
    public static final String ERROR_CUSTOM_MESSAGE = "Error: %s";

    // Mensajes de validación de campos
    public static final int ERROR_FIELD_VALIDATION_CODE = 2;
    public static final String MANDATORY_FIELD = "El campo %s es obligatorio";

    // Mensajes de entidad
    public static final int ENTITY_ALREADY_EXISTS_CODE = 3;
    public static final String ENTITY_ALREADY_EXISTS = "Ya existe un %s con ese id";

    public static final int ENTITY_NOT_EXISTS_CODE = 4;
    public static final String ENTITY_NOT_EXISTS = "No existe un %s con ese id";

    // Mensajes de proceso
    public static final int PROCESS_ALREADY_EXISTS_CODE = 5;
    public static final String PROCESS_ALREADY_EXISTS = "El proyecto ya tiene ese proceso";

    // Mensajes de permisos
    public static final int INSUFFICIENT_PERMISSIONS_CODE = 6;
    public static final String INSUFFICIENT_PERMISSIONS = "No tienes permisos suficientes para realizar esta acción";

    // Mensajes de conexión
    public static final int CONNECTION_ERROR_CODE = 7;
    public static final String CONNECTION_ERROR = "Error de conexión con el servidor";
}
