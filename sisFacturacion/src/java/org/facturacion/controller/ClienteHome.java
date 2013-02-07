/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.facturacion.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.ejb.TransactionAttribute;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.facturacion.facade.ClienteFacade;
import org.facturacion.model.Cliente;

/**
 *
 * @author cesar
 */
@Named(value = "clienteHome")
@ConversationScoped
public class ClienteHome implements Serializable {

    @Inject
    private ClienteFacade ejbCliente;
    private Long Id;
    private Cliente cliente;

    public ClienteFacade getEjbCliente() {
        return ejbCliente;
    }

    public void setEjbCliente(ClienteFacade ejbCliente) {
        this.ejbCliente = ejbCliente;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @PostConstruct
    public void init() {
        this.cliente = createInstance();
    }

    private Cliente createInstance() {
        if (isDefined()) {
            Cliente e = ejbCliente.find(Id);
            if (e != null) {
                return ejbCliente.find(Id);
            }else{
                return null;
            }
        }else{
                return new Cliente();
        }
    }
    
    @TransactionAttribute
    public String saveInstance(){
        if(isDefined()){
            ejbCliente.create(cliente);
        }else{
            ejbCliente.edit(cliente);
        }
        return "cliente/List";
    }
    
    public boolean isDefined() {
        if (this.Id != null) {
            return true;
        } else {
            return false;
        }
    }
}
