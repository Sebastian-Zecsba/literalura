package com.kcire.literalura.service;

public interface IConvierteDatos {
    //generico
    <T> T obtenerDatos(String json, Class<T> clase);
}
