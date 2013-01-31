
package edu.ventasonline.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.*;

@Entity
public class Factura implements Serializable{

    @Id @GeneratedValue (strategy= GenerationType.AUTO)
    private long codigo_factura;				
    @Column(columnDefinition = "double(12,2)", nullable = false)
    private Double  suman;		
    @Column(columnDefinition = "double(12,2)", nullable = false)
    private Double descuento;		
    @Column(columnDefinition = "double(12,2)", nullable = false)
    private Double subtotal;		
    @Column(columnDefinition = "double(12,2)", nullable = false)
    private Double tarifa_iva;
    @Column(columnDefinition = "double(12,2)", nullable = false)
    private Double total;		
    @Column(columnDefinition = "double(12,2)", nullable = false)
    private Double tarifacero;		
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date  fecha;		
    @Column(columnDefinition = "varchar(10)", nullable = false)
    private String aut_sri_empresa;		
    @Column(columnDefinition = "int(11)", nullable = false)
    private int id_usu_fk;		
    @Column(columnDefinition = "varchar(30)", nullable = false)
    private String estado;		
    @ManyToOne
    private Cliente cliente;

    public Factura() {
    }

    public long getCodigo_factura() {
        return codigo_factura;
    }

    public void setCodigo_factura(long codigo_factura) {
        this.codigo_factura = codigo_factura;
    }

    public Double getSuman() {
        return suman;
    }

    public void setSuman(Double suman) {
        this.suman = suman;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getTarifa_iva() {
        return tarifa_iva;
    }

    public void setTarifa_iva(Double tarifa_iva) {
        this.tarifa_iva = tarifa_iva;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getTarifacero() {
        return tarifacero;
    }

    public void setTarifacero(Double tarifacero) {
        this.tarifacero = tarifacero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getAut_sri_empresa() {
        return aut_sri_empresa;
    }

    public void setAut_sri_empresa(String aut_sri_empresa) {
        this.aut_sri_empresa = aut_sri_empresa;
    }

    public int getId_usu_fk() {
        return id_usu_fk;
    }

    public void setId_usu_fk(int id_usu_fk) {
        this.id_usu_fk = id_usu_fk;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
