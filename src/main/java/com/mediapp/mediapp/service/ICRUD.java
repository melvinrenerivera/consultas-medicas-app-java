package com.mediapp.mediapp.service;

import java.util.List;

public interface ICRUD<T,ID> {  // el T para tipo y el ID valor de llave primari
    T registrar(T p) throws Exception;
    T modificar(T p) throws Exception;
    List<T> listar() throws  Exception;
    T listarPorId(ID id) throws Exception;
    void eliminar(ID id) throws Exception;

}
