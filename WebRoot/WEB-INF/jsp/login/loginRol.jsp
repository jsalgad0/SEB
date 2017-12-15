<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Login Page</title>
<style>
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}

#login-box {
	width: 300px;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
}
</style>

<OBJECT classid='CLSID:592B9D7E-51C9-401F-A03C-4DE61FF7008D' name="Autentia" id='Autentia'><embed></embed></OBJECT>
<script type="application/javascript" type="text/javascript" src="${pageContext.request.contextPath}/resources/js/getinfo_autentia.js"></script>
</head>
<body>

	<div id="login-box">

		<h3>Login with RFC</h3>
		
		<script type="text/javascript">
	        function TParams(){
	            this.Dato = '';
	            this.TipoDato  = '';
	            this.Empresa  = '';
	            this.Lugar = '';
	        }
	        
	        function Iniciar(){
				var autenticacionHuella = $( "#autenticacionHuella" ).val();
				if(autenticacionHuella == "true"){
		            var Params   = new TParams;
		            Params.Dato  			= $("#rfc").val();
		            Params.TipoDato			= "RFC" ;
		            Params.Empresa			= "PFA120717716";
		            Params.Erc				= "";
		            Params.NroAudit			= "";
		            Params.ErcDesc			= "";
		            Params.FormaString		= "";
		            Params.Cant				= 0;
		            Params.Acceder			= "si";
		            erc						= 200;

					erc = Autentia.IniciarSesion("611330-2",21);
					erc = Autentia.Transaccion ("verifica",Params);
					
					var idUsuario = $( "#idUsuario" ).val();
					var ctx = "${pageContext.request.contextPath}";
					$.getJSON(ctx+"/auditoria/agregarAuditoria", {idUsuario:idUsuario, idAfiliado:"0", idAgenda:"0", tipoDato:Params.TipoDato, dato:Params.Dato, tipoAudit:"1", nroAudit:Params.NroAudit, ercDesc:Params.ErcDesc, erc:Params.Erc} ,function(response){		        		
					
		        	});
		        	
		            if(erc == 0){
	                    if(Params.Erc == 0){
	                        if(typeof(_params)!="undefined" && Params.hasOwnProperty("NroAudit")) document.getElementById("NroAudit").value = Params.NroAudit;
	                        else if(typeof(_params)!="undefined") document.getElementById("NroAudit").value = "";
	                        else{
	                            document.getElementById("NroAudit").value = (Params.hasOwnProperty("NroAudit"))?Params.NroAudit:"";
	                        }

	                        if(typeof(_params)!="undefined" && Params.hasOwnProperty("ErcDesc")) document.getElementById("ErcDesc").value = Params.ErcDesc;
	                        else if(typeof(_params)!="undefined") document.getElementById("ErcDesc").value = "";
	                        else{
	                            document.getElementById("ErcDesc").value = (Params.hasOwnProperty("ErcDesc"))?Params.ErcDesc:"";
	                        }
	                        
	                        Params.Acceder = "si";
	                        GetInfoSensorInterno();
	                        return true;
	                    }else if(Params.Erc == 1){
	                    	alert("Error El RFC ingresado no existe en la base de huellas");
	                    	return false;
	                    }else if(Params.Erc == 2){
	                        alert("ERROR\nLa verificacion fue rechazada.");
	                        return false;
	                    }else if(Params.Erc == 5){
	                        alert("ERROR\nNo se enrolo correctamente.");
	                        return false;
	                    }else if(Params.Erc == 201){
	                        alert("ERROR\nUsuario cancelo operacion.");
	                        return false;
	                    }else{
	                        alert("Erc: " +Params.Erc+ ", ErcDesc: " +Params.ErcDesc+ ", NroAudit: " +Params.NroAudit);
	                        return false;
	                    }
	                }
				}else{
					return true;
				}
	        }
	        
	        $( document ).ready(function() {
	        	$.ajaxSetup({ cache: false });
	        	
	        	var autenticacionHuella = $( "#autenticacionHuella" ).val();
	        	var muestraRoles = $( "#muestraRoles" ).val();
	        	if(autenticacionHuella == "true" && muestraRoles == "false"){
	        		var enviar = Iniciar();
	        		if(enviar){
	        			$( "#loginForm" ).submit();
	        		}
	        	}
	        	var form = document.getElementById('loginForm');
	        	form.addEventListener('submit', function(event) {
	        		if(!Iniciar()){
	        			event.preventDefault();	
	        		}
	        	});
        	});
	        
		</script>

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>

		<form:form name='loginForm' action="loginSecurity" method='POST' commandName="loginForm" id="loginForm"  >
			<form:hidden path="autenticacionHuella" id="autenticacionHuella"/>
			<form:hidden path="muestraRoles" id="muestraRoles"/>
			<form:hidden path="nroAudit" id="NroAudit"/>
			<form:hidden path="ercDesc" id="ErcDesc"/>
			<form:hidden path="tx_Marca" id="tx_Marca"/>
			<form:hidden path="tx_Serie" id="tx_Serie"/>
			<form:hidden path="tx_Modelo" id="tx_Modelo"/>
			<form:hidden path="tx_Fabric" id="tx_Fabric"/>
			<form:hidden path="usuarios.usuarioId" id="idUsuario"/>
			
			<input type='hidden' id="la_Ver" name="la_Ver">
            <input type="radio" name="rd_32" value="UAREU" id="rd_32" style="display:none">
            <input type="radio" name="rd_32" value="UAREU-Gold" id="rd_64" checked="checked"  style="display:none">
			
			
			
			<span id="la_Msg"></span>
			
			
			<c:if test="${loginForm.muestraRoles}">
				<table>
					<tr>
						<td>RFC:</td>
						<td><form:input path="rfc" id="rfc" readonly="true" /></td>
					</tr>
					<tr>
						<td>Rol:</td>
						<td>
							<form:select path="rol" >
							    <c:forEach var="rol" items="${loginForm.usuarios.usuariorols}">
							        <form:option value="${rol.roles.rolId}" label="${rol.roles.rol}" />
							    </c:forEach>
							</form:select>
						</td>
					</tr>					
					<tr>
						<td colspan='2'><input type="submit" value="enviar" id="enviar" /></td>
					</tr>
				</table>
			</c:if>
			<c:if test="${!loginForm.muestraRoles}">
				<table>
					<tr>
						<td>RFC:</td>
						<td>
							<form:input path="rfc" id="rfc" />							
						</td>
					</tr>
					<tr>
						<td colspan='2'><input type="submit" value="enviar" id="enviar" /></td>
					</tr>
				</table>			
			</c:if>
			
		</form:form>
	</div>

</body>
</html>