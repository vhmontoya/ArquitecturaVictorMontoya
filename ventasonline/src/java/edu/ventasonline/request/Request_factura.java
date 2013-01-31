
package edu.ventasonline.request;

import edu.ventasonline.modelo.Factura;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="request_factura")
@RequestScoped
public class Request_factura {
    Factura factura;

    public Request_factura() {
        factura = new Factura();
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }
    
}
