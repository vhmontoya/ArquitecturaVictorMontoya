/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.facturacion.controller;

import javax.inject.Named;
import org.facturacion.model.TipoGenero;

/**
 *
 * @author edison
 */
@Named(value = "typeListController")
public class TypeListController {

    /**
     * Creates a new instance of TypeListController
     */
    public TypeListController() {
    }

    public TipoGenero[] getFuelTypeList() {
        return TipoGenero.values();
    }
  
   
}
