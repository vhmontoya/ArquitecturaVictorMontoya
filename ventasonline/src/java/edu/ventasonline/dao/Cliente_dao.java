
package edu.ventasonline.dao;

import edu.ventasonline.comun.DaoAbstract;
import edu.ventasonline.modelo.Cliente;
import java.util.List;

public class Cliente_dao extends DaoAbstract<Cliente> {
    
      public Cliente_dao(Cliente cliente) {
        super(cliente);
    }   
    public List<Cliente> buscar_criterio(String criterio_busqueda){
        try {
            String binteligente = "Select personal from Personal  pcxvdszgzfggfdgdgdgdgdsgdsgdsgersonal  where " + "personal .nombres like concat(:criterio_busqueda,'%')or" + " personal .apellidos like concat(:criterio_busqueda,'%') or" + " personal.numero_documento_identificacion like concat(:criterio_busqueda,'%') " + "  order by personal.apellidos";
            javax.persistence.Query consulta = this.getEntityManager().createQuery(binteligente);
            consulta.setParameter("criterio_busqueda", criterio_busqueda);

            return consulta.getResultList();
        } catch (Exception e) {
        }
        return null;
    }

     public Cliente buscarIdentificacion(String ci_ruc){      
        List<Cliente> clientes;
        try {
            String bnombre="Select cliente from Cliente cliente where "+"(cliente.ci_ruc like:ci_ruc) ";
        javax.persistence.Query consulta=this.getEntityManager().createQuery(bnombre);
        consulta.setParameter("ci_ruc",ci_ruc);
           clientes= consulta.getResultList();
           return !clientes.isEmpty()? clientes.get(0): null;
        } catch (Exception e) {
        }
        return null;

  }
    @Override
    public List<Cliente> buscarTodo() {
        javax.persistence.Query consulta = this.getEntityManager().createQuery("Select cliente from Cliente cliente");
        return consulta.getResultList();
    }
}
