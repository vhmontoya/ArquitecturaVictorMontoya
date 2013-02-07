package org.facturacion.controller;

import org.facturacion.model.Cliente;
import org.facturacion.controller.util.JsfUtil;
import org.facturacion.controller.util.PaginationHelper;
import org.facturacion.facade.ClienteFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ConversationScoped
public class ClienteController implements Serializable {

    private Cliente current;
    //private DataModel items = null;
    private List<Cliente> resultList;
    @EJB
    private org.facturacion.facade.ClienteFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    @Inject
    Conversation conversation;
    private Long clienteId;

    public ClienteController() {
        resultList = new ArrayList<Cliente>();
        current = new Cliente();
    }

    public Cliente getCurrent() {
        return current;
    }

    public void setCurrent(Cliente current) {
        this.beginConversation();
        this.current = current;
    }

    public List<Cliente> getResultList() {
        return resultList;
    }

    public void setResultList(List<Cliente> resultList) {
        this.resultList = resultList;
    }

    public Long getClienteId() {
        if (this.current != null) {
            return this.current.getId();
        } else {
            return null;
        }
    }

    public void setClienteId(Long clienteId) {

        this.beginConversation();
        if (clienteId != null && clienteId.longValue() > 0) {
//            this.current = ejbFacade.buscarPorId(estudianteId);
            this.current = ejbFacade.findForId(clienteId);

        } else {
            System.out.println("========> INGRESO a Crear Estudiante: ");
            this.current = new Cliente();
        }
    }

    public String delete() {
//        System.out.println("========> INGRESO a Eliminar Estudiante: " + current.getNombres());
        ejbFacade.remove(current);
        this.endConversation();
        String summary = "Cliente Eliminado Correctamente!";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null));
        
        return "/cliente/List?faces-redirect=true";

    }

    public String findAll() {
        System.out.println("Ingreso a buscar Todos Estudiantes");
        resultList = ejbFacade.findAll();
//        for (Estudiante estudiante : resultList) {
//            System.out.println(estudiante);
//        }
        String summary = "Encontrados Correctamente!";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null));
        //puedo hacer retornar a la pagina q se quiera
        return "/cliente/List";
    }

    public String createInstance() {
        //return "/vehicle/Edit?faces-redirect=true";
//        System.out.println("========> INGRESO a Crear Instance estudiante: " + current.getNombres());
        this.current = new Cliente();
        return "/cliente/Edit?faces-redirect=true";
        //return "/vehicle/BrandEdit";
    }

    public String persist() {
        System.out.println("========> INGRESO a Grabar nuevo Estudiante: " + current.getNombres());
        
        ejbFacade.create(current);
        this.endConversation();
        System.out.println("guardo correctamente");
        String summary = ResourceBundle.getBundle("/Bundle").getString("ClienteCreated");
        JsfUtil.addSuccessMessage(summary);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

        return "/cliente/List?faces-redirect=true";
        //return "/vehicle/BrandList";

    }

    public String saveInstance() {
        if (clienteId != null) {
            return update();
        } else {
            return persist();
        }
    }

    public String update() {

        System.out.println("========> INGRESO a Actualizar al Estudiante: " + current.getNombres());
        ejbFacade.edit(current);
        this.endConversation();

        String summary = ResourceBundle.getBundle("/Bundle").getString("ClienteUpdated");
        FacesContext.getCurrentInstance().addMessage("successInfo", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, summary));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

        return "/cliente/List?faces-redirect=true";

    }

    public String cancelEdit() {
        System.out.println("me acaban de llamar: canceledit()");
        this.endConversation();
        return "/cliente/List?faces-redirect=true";
    }

    public Cliente getSelected() {
        if (current == null) {
            current = new Cliente();
            selectedItemIndex = -1;
        }
        return current;
    }

    private ClienteFacade getFacade() {
        return ejbFacade;
    }

    public void beginConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
            System.out.println("========> INICIANDO CONVERSACION: ");
        }
    }

    public void endConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
//            System.out.println("========> FINALIZANDO CONVERSACION: ");
        }
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {
                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }
    /*
     public String prepareList() {
     recreateModel();
     return "List";
     }

     public String prepareView() {
     current = (Cliente) getItems().getRowData();
     selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
     return "View";
     }*/

    public String prepareCreate() {
        current = new Cliente();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ClienteCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }
    /*
     public String prepareEdit() {
     current = (Cliente) getItems().getRowData();
     selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
     return "Edit";
     }
     */

//    public String update() {
//        try {
//            getFacade().edit(current);
//            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ClienteUpdated"));
//            return "View";
//        } catch (Exception e) {
//            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
//            return null;
//        }
//    }
//    public String destroy() {
//        current = (Cliente) getItems().getRowData();
//        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
//        performDestroy();
//        recreatePagination();
//        recreateModel();
//        return "List";
//    }
//
//    public String destroyAndView() {
//        performDestroy();
//        recreateModel();
//        updateCurrentItem();
//        if (selectedItemIndex >= 0) {
//            return "View";
//        } else {
//            // all items were removed - go back to list
//            recreateModel();
//            return "List";
//        }
//    }
    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ClienteDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }
    /*
     public DataModel getItems() {
     if (items == null) {
     items = getPagination().createPageDataModel();
     }
     return items;
     }

     private void recreateModel() {
     items = null;
     }*/

    private void recreatePagination() {
        pagination = null;
    }

//    public String next() {
//        getPagination().nextPage();
//        recreateModel();
//        return "List";
//    }
//
//    public String previous() {
//        getPagination().previousPage();
//        recreateModel();
//        return "List";
//    }
    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    @FacesConverter(forClass = Cliente.class)
    public static class ClienteControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ClienteController controller = (ClienteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "clienteController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuffer sb = new StringBuffer();
            sb.append(value);
            return sb.toString();
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Cliente) {
                Cliente o = (Cliente) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Cliente.class.getName());
            }
        }
    }
}
