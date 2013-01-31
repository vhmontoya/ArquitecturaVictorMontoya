
package edu.ventasonline.controlador;

import edu.ventasonline.dao.Factura_dao;
import edu.ventasonline.modelo.Factura;
import edu.ventasonline.request.Request_factura;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

@ManagedBean(name = "administra_factura")
public class FacturaC {
    @ManagedProperty(value="#reques_factura")
    Request_factura request_factura;
    private String criterio_busqueda;
    private boolean guardar;

    public FacturaC() {
    }
    
     public String crear() {
        return "Crear";
    }

    public String cancelar() {
        return "Cancelar";
    }

    public String editar(Factura factura) {
        request_factura.setFactura(factura);
        return "Crear";
    }
    
     public boolean mostrar_panel_error() {
        return guardar;

    }

    public List<Factura> buscar_criterio(String criterio_busqueda) {
        Factura_dao  factura_dao = new Factura_dao(new Factura());
        return factura_dao.buscar_criterio(criterio_busqueda);
    }

    public Request_factura getRequest_factura() {
        return request_factura;
    }

    public void setRequest_factura(Request_factura request_factura) {
        this.request_factura = request_factura;
    }
   
    public String getCriterio_busqueda() {
        return criterio_busqueda;
    }

    public void setCriterio_busqueda(String criterio_busqueda) {
        this.criterio_busqueda = criterio_busqueda;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public void setGuardar(boolean guardar) {
        this.guardar = guardar;
    }
    
    public String guardar() {
        Factura_dao factura_dao = new Factura_dao(request_factura.getFactura());        
                
        try {                           
                if (request_factura.getFactura().getCodigo_factura() == 0) 
                {                            
                     factura_dao.persist();
                     guardar = false;
                     return "Guardar";
                }
                else 
                {                            
                      factura_dao.update();
                      guardar = false;
                      return "Guardar";                            
                }                                             
        } 
        catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar el encabezado de la factura", "Error");
            FacesContext.getCurrentInstance().addMessage(null, message);
            guardar = true;
            return null;
        }

    }
}
