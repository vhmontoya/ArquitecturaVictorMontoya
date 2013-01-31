
package edu.ventasonline.comun;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FuenteDatos {
  
private  static  EntityManager cm=null;
public static  EntityManager getEntityManager(){
    if(cm==null||!cm.isOpen()){
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("ventasonlinecvgdfgdgdgfdgdggsdsdgsgdsfgsdgsdgsdPU");
        cm=emf.createEntityManager();
    }
    return cm;
}
}
