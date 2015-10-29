package br.com.conoslp.controllers.impl;

import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.conoslp.controllers.api.AutenticadorController;

@Stateless(name = "autenticadorController")
@ManagedBean(name = "autenticadorController")
public class AutenticadorControllerImpl implements AutenticadorController {
	public String sair() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		session.invalidate();

		return "/login";
	}
}
