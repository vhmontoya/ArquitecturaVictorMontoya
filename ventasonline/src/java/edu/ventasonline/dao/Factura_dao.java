
package edu.ventasonline.dao;

import edu.ventasonline.comun.DaoAbstract;
import edu.ventasonline.modelo.Factura;
import java.util.List;

public class Factura_dao extends DaoAbstract<Factura> {
    public Factura_dao(Factura factura)
    {
        super(factura);
    }
     public List<Factura> buscar_criterio(String criterio_busqueda) {
        try {
            String binteligente = "Select factura from Factura factura  where " + "factura.codigo_factura like concat('%',:criterio_busqueda,'%')  order by factura.fecha";
            javax.persistence.Query consulta = this.getEntityManager().createQuery(binteligente);
            consulta.setParameter("criterio_busqueda", criterio_busqueda);

            return consulta.getResultList();
        } catch (Exception e) {
        }
        return null;
    }
     
    public Factura buscarEstado(String estado) {
        List<Factura> facturas;
        try {
            String bnombre = "Select factura from Factura factura  where " + "(factura.estado like:estado) ";
            javax.persistence.Query consulta = this.getEntityManager().createQuery(bnombre);
            consulta.setParameter("estado", estado);
            facturas = consulta.getResultList();
            return !facturas.isEmpty() ? facturas.get(0) : null;
        } catch (Exception e) {
        }
        return null;
    }
       
    @Override
    public List<Factura> buscarTodo() {
        javax.persistence.Query consulta = this.getEntityManager().createQuery("Select factura from Factura factura");
        return consulta.getResultList();
    }    
}
