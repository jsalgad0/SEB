<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>

	<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Expires" content="0">


<!-- Add mousewheel plugin (this is optional) -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/fancyBox/fancyapps-fancyBox/lib/jquery.mousewheel-3.0.6.pack.js"></script>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/popup_generales.css">
<!-- Add fancyBox -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/fancyBox/fancyapps-fancyBox/source/jquery.fancybox.css" type="text/css" media="screen" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/fancyBox/fancyapps-fancyBox/source/jquery.fancybox.pack.js"></script>

<!-- Optionally add helpers - button, thumbnail and/or media -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/fancyBox/fancyapps-fancyBox/source/helpers/jquery.fancybox-buttons.css" type="text/css" media="screen" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/fancyBox/fancyapps-fancyBox/source/helpers/jquery.fancybox-buttons.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/fancyBox/fancyapps-fancyBox/source/helpers/jquery.fancybox-media.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/fancyBox/fancyapps-fancyBox/source/helpers/jquery.fancybox-thumbs.css" type="text/css" media="screen" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/fancyBox/fancyapps-fancyBox/source/helpers/jquery.fancybox-thumbs.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/estilos_login.css" type="text/css" media="screen" />
<title>::SAB::</title>
</head>
<body>
	<form:form name='loginForm' action="loginVerificacion" method='POST' commandName="loginForm" id="loginForm" >
		<form:hidden path="error"/>
		<form:hidden path="tx_Marca"/>
		<form:hidden path="tx_Serie"/>
		<form:hidden path="tx_Modelo"/>
		<form:hidden path="tx_Fabric"/>
		<form:hidden path="la_Msg"/>
		<form:hidden path="la_Ver"/>
		<form:hidden path="lectorConectado"/>
		<form:hidden path="validarHuella"/>
		<form:hidden path="validarClave"/>
		<form:hidden path="ingresarClave"/>
		<input type="radio" name="rd_32" value="UAREU" id="rd_32" style="display:none">
        <input type="radio" name="rd_32" value="UAREU-Gold" id="rd_64" checked="checked"  style="display:none">
        <!-- <form:hidden path="idUsuario"/> -->
        <form:hidden path="nombre"/>
        <form:hidden path="apellidoPaterno"/>
        <form:hidden path="apellidoMaterno"/>
        <form:hidden path="sexo"/>
        <form:hidden path="fechaNacimiento"/>
        <form:hidden path="muestraRoles"/>
		<form:hidden path="autenticacionHuella" id="autenticacionHuella"/>
		<form:hidden path="nroAudit" id="NroAudit"/>
		<form:hidden path="ercDesc" id="ErcDesc"/>
		<form:hidden path="errorClave" id="errorClave"/>
		<form:hidden path="usuarios.usuarioId" id="idUsuario"/>
		<form:hidden path="intentosClave" id="intentosClave"/>
		<form:hidden path="irLogin" id="irLogin"/>
		<form:hidden path="claveExitosa" id="claveExitosa"/> 
	
		<div class="flotante">
	    	<div id="logo_central">
	        </div>
	    </div>    
	    <div class="flotante2">
	    	 <div class="contenedor_central">
	    	 	<form:input path="rfc" id="rfc" cssClass="campo_text2" placeholder="RFC" onkeypress="return keyEnviar(event)" /><!--
	            --><div class="caja_arrow" onclick="enviar();">
	            		<div class="flecha">
	                    </div>
	               </div>
	         </div>
	    </div>	
	    
	    <div class="flotante2">
	    	<div class="contenedor_3">
	    		<div class="aviso">
	            </div>
	        </div>
	    </div>
	    
	    <div id="trClave" style="display: none;">
			<div class="flotante2">
		    	 <div class="contenedor_central">
		    	 	<form:password path="clave" id="clave" cssClass="campo_text2" onkeypress="return keyEnviar2(event)" /><!--
		            --><div class="caja_arrow" onclick="enviar2();">
		            		<div class="flecha">
		                    </div>
		               </div>
		         </div>
		    </div> 	  
		    
		    <div class="flotante2">
		    	<div class="contenedor_3">
		
		            <a class="aviso" href="#" onclick="olvidoClave()">¿Olvidó su clave?
		            </a>
		
		            
		        </div>
		    </div>	    
	    </div>
	    
	
	    
	    <div class="flotante2" id="roles" style="display: none;">
	    	 <div class="contenedor_2">
	         	<div class="campo_text">
					<form:select path="rol" id="rol" cssClass="selectt cssmenu" onchange="validarRol();" >
						<form:option value="-1">Seleccione un Rol</form:option>					
						<c:forEach var="rol" items="${usuarioRols}">
							<form:option value="${rol.roles.rolId}" label="${rol.roles.rol}" />
						</c:forEach>
					</form:select>							
	            </div><!--
	            --><div class="caja_arrow">
	            		<div id="flecha_abajo">
	                    </div>
	               </div>
	         </div>
	    </div>	          		
				
	</form:form>
</body>

<script type="text/javascript">
	function enviar(){
		var ctx = "${pageContext.request.contextPath}";
		var lectorConectado = $("#lectorConectado").val();
		if (lectorConectado == "true") {
			$("#loginForm").attr("action", "loginVerificacion");
			$("#loginForm").submit();	
		}else{
			$.fancybox.open({
				content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup huella\"></div><h1>No hay lector de huella conectado al equipo, el sistema no puede operar</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
				openEffect : 'elastic',
				openSpeed  : 150,
				closeEffect : 'elastic',
				closeSpeed  : 150,								
 				autoSize: false,
 				width: 470,
 				height: 185,
 				afterClose : function(){
 					var irLogin = $("#irLogin").val();
 					if (irLogin == "true") {
	 					var ctx = "${pageContext.request.contextPath}";
	 					window.location.href = ctx+"/login";								
					}
 				}
			});			
		}
	}
	
	function enviar2(){
		$("#loginForm").submit();	
	}
	
	function keyEnviar (event) {
	    if (event.which == 13 || event.keyCode == 13) {
	        enviar();
	        return false;
	    }
	    return true;
	};
	
	function keyEnviar2 (event) {
	    if (event.which == 13 || event.keyCode == 13) {
	        enviar2();
	        return false;
	    }
	    return true;
	};	
	
	function validarRol(){
		var valor = $("#rol").val();
		if(valor!=-1){
			$("#loginForm").submit();	
		}
	}
	
	function olvidoClave(){
		var idUsuario = $("#idUsuario").val();
		$.fancybox.open({
			href : '${pageContext.request.contextPath}/olvidoClave?idUsuario='+idUsuario,
			type : 'iframe',
			openEffect : 'elastic',
			openSpeed  : 150,
			closeEffect : 'elastic',
			closeSpeed  : 150,
			closeClick : true,
			autoSize : false,
			width : 735,
			height : 435
		});
	}
	
	$( document ).ready(function() {
		
		$(".fancybox").fancybox();
		
		var lectorConectado = $("#lectorConectado").val();
		if (lectorConectado == "false") {
			GetInfoSensorInterno();	
		}
		
		var ctx = "${pageContext.request.contextPath}";
		var error = $("#error").val();
		if (error!="") {
			$.fancybox.open({
				content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>"+error+"</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
				openEffect : 'elastic',
				openSpeed  : 150,
				closeEffect : 'elastic',
				closeSpeed  : 150,								
 				autoSize: false,
 				width: 470,
 				height: 185,
 				afterClose : function(){
 					var irLogin = $("#irLogin").val();
 					if (irLogin == "true") {
	 					var ctx = "${pageContext.request.contextPath}";
	 					window.location.href = ctx+"/login";								
					}
 				}
			});				
		}else{
			var validarHuella = $("#validarHuella").val();
			var validarClave = $("#validarClave").val();
			var errorClave = $("#errorClave").val();
			var claveExitosa = $("#claveExitosa").val();
			if (validarHuella == "true") {
				if (IniciarUsuarioLogin($("#idUsuario").val(), 0, 0, $("#rfc").val(), "RFC",  $("#nombre").val(), $("#apellidoPaterno").val(), $("#apellidoMaterno").val(), $("#sexo").val(), $("#fechaNacimiento").val(), "9")) {
					var muestraRoles = $("#muestraRoles").val();
					if (muestraRoles == "true") {
						$("#roles").show();
						$("#loginForm").attr("action", "loginSecurity");
					}else{
						$("#loginForm").attr("action", "loginSecurity");
						$("#loginForm").submit();
					}
				}else{
					$.fancybox.open({
						content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Verificación de identidad no fue exitosa, consulte con el Administrador del Sistema en su Lugar de Atención</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
						openEffect : 'elastic',
						openSpeed  : 150,
						closeEffect : 'elastic',
						closeSpeed  : 150,								
		 				autoSize: false,
		 				width: 470,
		 				height: 185,
		 				afterClose : function(){
		 					var irLogin = $("#irLogin").val();
		 					if (irLogin == "true") {
			 					var ctx = "${pageContext.request.contextPath}";
			 					window.location.href = ctx+"/login";								
							}
		 				}
					});						
				}
			}else if(validarClave == "true"){
				$("#trClave").show();
				$("#loginForm").attr("action", "loginVerificacionClave");
				var ingresarClave = $("#ingresarClave").val();
				if(ingresarClave == "true"){
					$.fancybox.open({
						href : '${pageContext.request.contextPath}/ingresarClave',
						type : 'iframe',
						openEffect : 'elastic',
						openSpeed  : 150,
						closeEffect : 'elastic',
						closeSpeed  : 150,
						closeClick : true,
						autoSize : false,
						width : 580,
						height : 400
					});
				}else if (errorClave != "") {
					$.fancybox.open({
						content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>"+errorClave+"</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
						openEffect : 'elastic',
						openSpeed  : 150,
						closeEffect : 'elastic',
						closeSpeed  : 150,								
		 				autoSize: false,
		 				width: 470,
		 				height: 185,
		 				afterClose : function(){
		 					var irLogin = $("#irLogin").val();
		 					if (irLogin == "true") {
			 					var ctx = "${pageContext.request.contextPath}";
			 					window.location.href = ctx+"/login";								
							}
		 				}
					});	
				}else if (claveExitosa == "true") {
					var muestraRoles = $("#muestraRoles").val();
					if (muestraRoles == "true") {
						$("#roles").show();
						$("#loginForm").attr("action", "loginSecurity");
					}else{
						$("#loginForm").attr("action", "loginSecurity");
						$("#loginForm").submit();
					}
				}
			}
		}
		
	});	
</script>
</html>