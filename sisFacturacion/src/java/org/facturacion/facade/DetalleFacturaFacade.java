/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.facturacion.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.facturacion.model.DetalleFactura;

/**
 *
 * @author cesar
 */
@Stateless
public class DetalleFacturaFacade extends AbstractFacade<DetalleFactura> {
    @PersistenceContext(unitName = "sisFacturacionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleFacturaFacade() {
        super(DetalleFactura.class);
    }
    
}
