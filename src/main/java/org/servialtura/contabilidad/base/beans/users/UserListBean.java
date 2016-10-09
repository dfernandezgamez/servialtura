package org.servialtura.contabilidad.base.beans.users;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.transaction.SystemException;

import org.primefaces.event.RowEditEvent;
import org.servialtura.contabilidad.base.beans.BaseBean;
import org.servialtura.contabilidad.base.model.Usuario;
import org.servialtura.contabilidad.base.service.UsersService;


@ManagedBean(name = "userList")
@ViewScoped
public class UserListBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7766171432336654234L;

	/**
	 * 
	 */
	private List<Usuario> users;
    @ManagedProperty(value="#{UsersService}")
   private  UsersService usuarisService;
    
    private Usuario newUser = new Usuario();
    
    
    @PostConstruct
    public void init(){
    	try {
			users = (List<Usuario>) usuarisService.listUsuarios();
		} catch (SystemException e) {
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error getting users"));
		}
    }
    
    
    public void addUser(){
    	try {
			usuarisService.createUsuario(newUser);
		} catch (SystemException e) {
			   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error creating user"));
		}
    }
    
    public void onRowEdit(RowEditEvent event) {
    	Usuario user = (Usuario) event.getObject();
    	
    	try {
			usuarisService.updateUsuario(user);
			//users = (List<Usuario>) usuarisService.getUsuarios();
		} catch (SystemException e) {
			   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error updating user"));
		}
    	 FacesMessage msg = new FacesMessage("Edit Cancelled", ((Usuario) event.getObject()).getUsuario());
         FacesContext.getCurrentInstance().addMessage(null, msg);
    }

	public List<Usuario> getUsers() {
		return users;
	}

	public void setUsers(List<Usuario> users) {
		this.users = users;
	}

	public UsersService getUsuarisService() {
		return usuarisService;
	}

	public void setUsuarisService(UsersService usuarisService) {
		this.usuarisService = usuarisService;
	}


	public Usuario getNewUser() {
		return newUser;
	}


	public void setNewUser(Usuario newUser) {
		this.newUser = newUser;
	}
    
}
