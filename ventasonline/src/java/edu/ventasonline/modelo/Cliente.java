
package edu.ventasonline.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
public class Cliente implements Serializable{
    @Id @GeneratedValue(strategy= GenerationType.AUTO)
    private int codigo_cliente;		
    @Column(columnDefinition = "varchar(20)", nullable = false)
    private String ci_ruc;		
    @Column(columnDefinition = "varchar(60)", nullable = false)
    private String nombre;		
    @Column(columnDefinition = "varchar(60)", nullable = false)
    private String apellido;		
    @Column(columnDefinition = "varchar(200)", nullable = false)
    private String direccion;		
    @Column(columnDefinition = "varchar(60)", nullable = false)
    private String telefono;		
    @Temporal(TemporalType.TIMESTAMP)
    private Date  fecha_ingreso;		
    @OneToMany(mappedBy = "Cliente")
    List<Factura> facturas;

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }
    
    public Cliente() {
         facturas = new ArrayList<Factura>();
    }

    public int getCodigo_cliente() {
        return codigo_cliente;
    }

    public void setCodigo_cliente(int codigo_cliente) {
        this.codigo_cliente = codigo_cliente;
    }

    public String getCi_ruc() {
        return ci_ruc;
    }

    public void setCi_ruc(String ci_ruc) {
        this.ci_ruc = ci_ruc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }
    
}
