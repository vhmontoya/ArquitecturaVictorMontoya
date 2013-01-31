
package edu.ventasonline.controlador;

import edu.ventasonline.dao.Cliente_dao;
import edu.ventasonline.dao.Factura_dao;
import edu.ventasonline.modelo.Cliente;
import edu.ventasonline.modelo.Factura;
import edu.ventasonline.request.Request_cliente;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;


@ManagedBean(name="administra_cliente")
public class ClienteC {
    @ManagedProperty(value="#request_cliente")
    Request_cliente request_cliente;
    private String criterio_busqueda;
    private boolean guardar;

    public ClienteC() {
    }
     public String crear() {
        return "Crear";
    }

    public String cancelar() {
        return "Cancelar";
    }

    public String editar(Cliente cliente) {
        request_cliente.setCliente(cliente);
        return "Crear";
    }
    
    public List<Factura> get_facturas() {
        Factura_dao factura_dao = new Factura_dao(new Factura());
        return factura_dao.buscarTodo();
    }

    public String guardar() {
        Cliente_dao cliente_dao = new Cliente_dao(request_cliente.getCliente());        
                
        try {                                                

                if (request_cliente.getCliente().getCi_ruc() == null) 
                {
                    if (cliente_dao.buscarIdentificacion(request_cliente.getCliente().getCi_ruc()) == null) {
                        cliente_dao.persist();
                        guardar = false;
                        return "Guardar";
                    } 
                    else 
                    {
                        FacesMessage  message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Número de Documento ya Existe", "Error");
                        FacesContext.getCurrentInstance().addMessage(null, message);
                        guardar = true;
                        return null;
                    }
                } 
                else 
                {
                    if (cliente_dao.buscarIdentificacion(request_cliente.getCliente().getCi_ruc()) == null || cliente_dao.FindBy((long)request_cliente.getCliente().getCodigo_cliente()).equals(cliente_dao.buscarIdentificacion(request_cliente.getCliente().getCi_ruc()))) {
                        cliente_dao.update();
                        guardar = false;
                        return "Guardar";
                    } else {
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Número de Documento ya Existe", "Error");
                        FacesContext.getCurrentInstance().addMessage(null, message);
                        guardar = true;
                        return null;
                    }
                }                    
                
            

        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Número de Identificación ya existe", "Error");
            FacesContext.getCurrentInstance().addMessage(null, message);
            guardar = true;
            return null;
        }

    }

    public boolean mostrar_panel_error() {
        return guardar;

    }

    public List<Cliente> buscar_criterio(String criterio_busqueda) {
        Cliente_dao cliente_dao = new Cliente_dao(new Cliente());
        return cliente_dao.buscar_criterio(criterio_busqueda);
    }

    public Request_cliente getRequest_cliente() {
        return request_cliente;
    }

    public void setRequest_cliente(Request_cliente request_cliente) {
        this.request_cliente = request_cliente;
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
}
