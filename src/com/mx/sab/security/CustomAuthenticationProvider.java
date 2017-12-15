package com.mx.sab.security;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.mx.sab.model.Menu;
import com.mx.sab.model.Usuariorol;
import com.mx.sab.model.Usuarios;
import com.mx.sab.service.ILoginService;
import com.mx.sab.service.ILugarAtencionService;
import com.mx.sab.service.IUsuarioService;
import com.mx.sab.vo.UserInfo;

@Component
@Log4j2
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private ILoginService loginService;
	
	@Autowired
	private IUsuarioService usuarioService;	
	
	@Autowired ILugarAtencionService lugarAtencionService;
	
	@Autowired 
	private HttpServletRequest request;
	 
	@Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = "identisa";
        //log.info("request.getParameter(autenticacionHuella) = "+request.getParameter("autenticacionHuella"));
        //log.info("request.getParameter(muestraRoles) = "+request.getParameter("muestraRoles"));
        //log.info("request.getParameter(nroAudit) = "+request.getParameter("nroAudit"));
        //log.info("request.getParameter(ercDesc) = "+request.getParameter("ercDesc"));
        //log.info("request.getParameter(rol) = "+request.getParameter("rol"));
        //log.info("request.getParameter(tx_Marca) = "+request.getParameter("tx_Marca"));
        //log.info("request.getParameter(tx_Serie) = "+request.getParameter("tx_Serie"));
        //log.info("request.getParameter(tx_Modelo) = "+request.getParameter("tx_Modelo"));
        //log.info("request.getParameter(tx_Fabric) = "+request.getParameter("tx_Fabric"));
        //log.info("request.getParameter(idUsuario) = "+request.getParameter("idUsuario"));
        String autenticacionHuella = request.getParameter("autenticacionHuella");
        String muestraRoles = request.getParameter("muestraRoles");
        String nroAudit = request.getParameter("nroAudit");
        String ercDesc = request.getParameter("ercDesc");
        String rol = request.getParameter("rol");
        int tx_Marca = Integer.parseInt(request.getParameter("tx_Marca"));
        String tx_Serie = request.getParameter("tx_Serie");
        String tx_Modelo = request.getParameter("tx_Modelo");
        String tx_Fabric = request.getParameter("tx_Fabric");
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        if (!name.equals("") && !rol.equals("")) {
        	Usuarios usuarios = usuarioService.getUsuarioById(idUsuario);
        	UserInfo userInfo = new UserInfo();
        	userInfo.setAutenticacionHuella(autenticacionHuella);
        	userInfo.setErcDesc(ercDesc);
        	userInfo.setMuestraRoles(muestraRoles);
        	userInfo.setName(name);
        	userInfo.setNroAudit(nroAudit);
        	userInfo.setTx_Marca(tx_Marca);
        	userInfo.setTx_Serie(tx_Serie);
        	userInfo.setTx_Modelo(tx_Modelo);
        	userInfo.setTx_Fabric(tx_Fabric);
        	userInfo.setLugaresdeatencion(lugarAtencionService.getLugaresAtencionByClave(tx_Marca));
        	userInfo.setUsuarios(usuarios);
        	if (muestraRoles.equals("false")) {
				Iterator<Usuariorol> usuarioRolIterator = usuarios.getUsuariorols().iterator();
				while (usuarioRolIterator.hasNext()) {
					Usuariorol usuariorol = (Usuariorol) usuarioRolIterator.next();
					rol = ""+usuariorol.getRoles().getRolId();
				}
			}
        	userInfo.setRol(rol);
        	Iterator<Usuariorol> usuarioRolIterator = usuarios.getUsuariorols().iterator();
        	while (usuarioRolIterator.hasNext()) {
				Usuariorol usuariorol = (Usuariorol) usuarioRolIterator.next();
				if (Integer.parseInt(rol) == usuariorol.getRoles().getRolId()) {
					userInfo.setRolNombre(usuariorol.getRoles().getRol());
				}
			}
        	List<Menu> menus = loginService.getRoles(rol);
        	userInfo.setMenus(menus);
            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            for (Menu menu : menus) {
            	grantedAuths.add(new SimpleGrantedAuthority(menu.getRol()));	
			}
            Authentication auth = new UsernamePasswordAuthenticationToken(userInfo, password, grantedAuths);
            return auth;
        } else {
            return null;
        }
    }
 
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    
    
}
