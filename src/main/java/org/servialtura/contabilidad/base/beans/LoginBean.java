package org.servialtura.contabilidad.base.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.servialtura.contabilidad.base.beans.viewUtils.PropertyUtils;
import org.servialtura.contabilidad.base.utils.SessionUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

 
@ManagedBean(name = "loginBean")
@ViewScoped
/**
 *
 * @author User
 */
public class LoginBean extends BaseBean implements Serializable {
 
    private static final long serialVersionUID = 1L;
    private String password;
    private String uname;
    final static Logger logger = Logger.getLogger(LoginBean.class);
     
    @ManagedProperty(value="#{authenticationManager}")
    private AuthenticationManager authenticationManager;
    
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public String getUname() {
        return uname;
    }
 
    public void setUname(String uname) {
        this.uname = uname;
    }
 
    @PostConstruct
    public void init(){
    }
    public String loginProject() {
        try {
        	Authentication request = new UsernamePasswordAuthenticationToken(this.uname, this.password);
            Authentication result = authenticationManager.authenticate(request);
            logger.debug("entramos en el inicio");
            SecurityContextHolder.getContext().setAuthentication(result);
        } catch (AuthenticationException e) {
        	logger.error(e);
        	PropertyUtils.addMessage(FacesContext.getCurrentInstance(),"messagesLogin","message.loginFailed", null);
			return "/login";
        }
        return "xhtml/solicitudes/solicitudes.xhtml";
    }
    
     
    public void logout() {
      HttpSession session = SessionUtils.getSession();
      session.invalidate();
   }

	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
    
}