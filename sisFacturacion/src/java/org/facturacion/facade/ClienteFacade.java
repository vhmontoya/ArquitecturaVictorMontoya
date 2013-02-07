/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.facturacion.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.facturacion.model.Cliente;

/**
 *
 * @author cesar
 */
@Stateless
public class ClienteFacade extends AbstractFacade<Cliente> {
    @PersistenceContext(unitName = "sisFacturacionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }
    
    public Cliente findForId(Long id){
        return this.find(id);        
    }
}
