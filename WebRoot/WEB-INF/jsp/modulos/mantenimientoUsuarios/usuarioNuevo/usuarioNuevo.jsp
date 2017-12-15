<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<OBJECT classid='CLSID:592B9D7E-51C9-401F-A03C-4DE61FF7008D'
	name="Autentia" id='Autentia'>
	<embed></embed>
</OBJECT>
<script type="text/javascript">
	function verificarRfc(){
		var ctx = "${pageContext.request.contextPath}";
		var mantenimientoUsuarioNuevoForm = $("#mantenimientoUsuarioNuevoForm").serialize();
	    $.getJSON("verificarRfc", mantenimientoUsuarioNuevoForm ,function(response){
	    	if(response.errores.length == 0){
	    		if (response.error=="") {
	    			$("#statusInput").val(false);
	    			$("#statusSelect").val(false);
	    			$("#validarEspecialidades").val(true);
					$("#nombre").prop("readonly", false);
					$("#apellidoPaterno").prop("readonly", false);
					$("#apellidoMaterno").prop("readonly", false);
					$("#fechaNacimientoAux").datepicker( "option", "disabled", false );
					$("#curp").prop("readonly", false);
					$("#telefono").prop("readonly", false);
					$("#mail").prop("readonly", false);
					$("#sexo1").prop("disabled", false);
					$("#sexo2").prop("disabled", false);		
					
					var formElements = document.getElementById("mantenimientoUsuarioNuevoForm");
				    for (var i = 0; i<formElements.length; i++){
				        if (formElements[i].type == "checkbox"){
				        	for(var j=0; j<response.roles.length; j++){
								//formElements[i].checked = false;
				        	}
						}
					}
				    
					$("#claveInterna").prop("readonly", false);
					$("#regSs").prop("readonly", false);
					
					$("#cedulaProfesional1").prop("readonly", false);
					$("#idInstitucion1").prop("disabled", false);
					
					$("#idEspecialidad2").prop("disabled", false);
					$("#cedulaProfesional2").prop("readonly", false);
					$("#idInstitucion2").prop("disabled", false);
					
					$("#idEspecialidad3").prop("disabled", false);
					$("#cedulaProfesional3").prop("readonly", false);
					$("#idInstitucion3").prop("disabled", false);
				}else{
					if (response.funcionamiento == 1) {
						$.fancybox.open({
							href : ctx + '/mantenimientoUsuarios/mostrarRegistrado',
							type : 'iframe',
							openEffect : 'elastic',
							openSpeed  : 150,
							closeEffect : 'elastic',
							closeSpeed  : 150,
							autoSize: false,
			 				width: 550,
			 				height: 400,
			 				afterClose : function(){
								var respuesta = $("#respuestaRegistrado").val();
								if (respuesta == "true") {
					    			$("#statusInput").val(true);
									$("#validarEspecialidades").val(true);
									$("#idUsuario").val(response.idUsuario);
									$("#nombre").val(response.usuarios.nombre);
									$("#nombre").prop("readonly", true);
									$("#apellidoPaterno").val(response.usuarios.apellidoPaterno);
									$("#apellidoPaterno").prop("readonly", true);
									$("#apellidoMaterno").val(response.usuarios.apellidoMaterno);
									$("#apellidoMaterno").prop("readonly", true);
									$("#fechaNacimientoAux").val(response.fechaNacimiento);
									$("#fechaNacimientoAux").datepicker( "option", "disabled", true );
									$("#curp").val(response.usuarios.curp);
									$("#curp").prop("readonly", true);
									$("#telefono").val(response.usuarios.telefono);
									$("#telefono").prop("readonly", true);
									$("#mail").val(response.usuarios.mail);
									$("#mail").prop("readonly", true);
									$("#sexo"+response.sexo).prop("checked", true);
									
									var especialidadesBandera = false;
									var formElements = document.getElementById("mantenimientoUsuarioNuevoForm");
								    for (var i = 0; i<formElements.length; i++){
								        if (formElements[i].type == "checkbox"){
								        	for(var j=0; j<response.roles.length; j++){
								        		if(formElements[i].value==response.roles[j]){
													formElements[i].checked = false;
													if(formElements[i].value=="14" || formElements[i].value=="20"){
														especialidadesBandera = true;
														$("#validarEspecialidades").val(false);
													}
												}
								        	}
										}
									}
								 
								    if (especialidadesBandera) {
								    	$("#statusSelect").val(true);
								    	$("#claveInterna").val(response.claveInterna);
										$("#claveInterna").prop("readonly", true);
										$("#regSs").prop("readonly", true);
										
										$("#cedulaProfesional1").val(response.cedulaProfesional1);
										$("#cedulaProfesional1").prop("readonly", true);
										$("#idInstitucion1").val(response.idInstitucion1);
										$("#idInstitucion1").prop("disabled", true);
										
										$("#idEspecialidad2").val(response.idEspecialidad2);
										$("#idEspecialidad2").prop("disabled", true);
										$("#cedulaProfesional2").val(response.cedulaProfesional2);
										$("#cedulaProfesional2").prop("readonly", true);
										$("#idInstitucion2").val(response.idInstitucion2);
										$("#idInstitucion2").prop("disabled", true);
										
										$("#idEspecialidad3").val(response.idEspecialidad3);
										$("#idEspecialidad3").prop("disabled", true);
										$("#cedulaProfesional3").val(response.cedulaProfesional3);
										$("#cedulaProfesional3").prop("readonly", true);
										$("#idInstitucion3").val(response.idInstitucion3);
										$("#idInstitucion3").prop("disabled", true);							
									}else{
										$("#regSs").prop("readonly", false);
										$("#statusSelect").val(false);
										$("#claveInterna").val(response.claveInterna);
										$("#claveInterna").prop("readonly", false);
										
										$("#cedulaProfesional1").val("");
										$("#cedulaProfesional1").prop("readonly", false);
										$("#idInstitucion1").val("-1");
										$("#idInstitucion1").prop("disabled", false);
										
										$("#idEspecialidad2").val("-1");
										$("#idEspecialidad2").prop("disabled", false);
										$("#cedulaProfesional2").val("");
										$("#cedulaProfesional2").prop("readonly", false);
										$("#idInstitucion2").val("-1");
										$("#idInstitucion2").prop("disabled", false);
										
										$("#idEspecialidad3").val("-1");
										$("#idEspecialidad3").prop("disabled", false);
										$("#cedulaProfesional3").val("");
										$("#cedulaProfesional3").prop("readonly", false);
										$("#idInstitucion3").val("-1");
										$("#idInstitucion3").prop("disabled", false);
									}
									
								}else{
									$(".fancybox").fancybox();
									setTimeout(nada, 50, response.rfcAux);
								}
			 				}
						});
						

					}else{
						$("#rfc").val("");
						$.fancybox.open({
							content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>"+response.error+"</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
							openEffect : 'elastic',
							openSpeed  : 150,
							closeEffect : 'elastic',
							closeSpeed  : 150,								
			 				autoSize: false,
			 				width: 470,
			 				height: 185,
			 				afterClose : function(){
			 					//var ctx = "${pageContext.request.contextPath}";
			 					//window.location.href = ctx+"/mantenimientoUsuarios/inicio";
			 				}
						});						
					}
				}
	    	}else{
    			$("#errores").empty();
    			$.each(response.errores, function(index, item) {
    				$("#errores").append(item+"</br>"); 
                });	
    		}
	    	verificarMedicoCheck();	
	    });
	}
	
	function siGenerarRfc(){
		$("#respuestaGenerarRfc").val("true");
		parent.$.fancybox.close(true);
	}
	
	function noGenerarRfc(){
		$("#respuestaGenerarRfc").val("false");
		parent.$.fancybox.close(true);
	}
	
	function nada(rfcAux){
		$("#respuestaGenerarRfc").val("false");
		$.fancybox.open({
			content : "<div id=\"contenedor2_popup\"><div class=\"icon_popup ask\"></div><h1>Desea registrar al usuario con un RFC asignado por el sistema?</h1><div class=\"btn_popup\"><div class=\"btn_si_popup\" onclick=\"siGenerarRfc();\"></div><div class=\"btn_no_popup\" onclick=\"noGenerarRfc();\"></div></div></div>",
			type : 'iframe',
			openEffect : 'elastic',
			openSpeed  : 150,
			closeEffect : 'elastic',
			closeSpeed  : 150,
			autoSize: false,
			width: 500,
			height: 190,
			afterClose : function(){
				var respuesta = $("#respuestaGenerarRfc").val();
				if (respuesta=="true") {
	    			$("#statusInput").val(false);
	    			$("#statusSelect").val(false);
					$("#validarEspecialidades").val(true);
					$("#rfc").val(rfcAux);
					$("#rfcAux").val(rfcAux);
					$("#nombre").val("");
					$("#nombre").prop("readonly", false);
					$("#apellidoPaterno").val("");
					$("#apellidoPaterno").prop("readonly", false);
					$("#apellidoMaterno").val("");
					$("#apellidoMaterno").prop("readonly", false);
					$("#fechaNacimientoAux").val("");
					$("#fechaNacimientoAux").datepicker( "option", "disabled", false );
					$("#curp").val("");
					$("#curp").prop("readonly", false);
					$("#telefono").val("");
					$("#telefono").prop("readonly", false);
					$("#mail").val("");
					$("#mail").prop("readonly", false);	
										
					$("#claveInterna").val("");
					$("#claveInterna").prop("readonly", false);
					
					$("#cedulaProfesional1").val("");
					$("#cedulaProfesional1").prop("readonly", false);
					$("#idInstitucion1").val("-1");
					$("#idInstitucion1").prop("disabled", false);
					
					$("#idEspecialidad2").val("-1");
					$("#idEspecialidad2").prop("disabled", false);
					$("#cedulaProfesional2").val("");
					$("#cedulaProfesional2").prop("readonly", false);
					$("#idInstitucion2").val("-1");
					$("#idInstitucion2").prop("disabled", false);
					
					$("#idEspecialidad3").val("-1");
					$("#idEspecialidad3").prop("disabled", false);
					$("#cedulaProfesional3").val("");
					$("#cedulaProfesional3").prop("readonly", false);
					$("#idInstitucion3").val("-1");
					$("#idInstitucion3").prop("disabled", false);
				}else{
					var ctx = "${pageContext.request.contextPath}";
					window.location.href = ctx+"/mantenimientoUsuarios/inicio";
				}
			}
		});
	}
	
	function verificarMedicoCheck(){
		var formElements = document.getElementById("mantenimientoUsuarioNuevoForm");
		var banderaEspecialidades = false;
	    for (var i = 0; i < formElements.length; i++){
	        if (formElements[i].type == "checkbox"){
        		if(formElements[i].value=="14" || formElements[i].value=="20" || formElements[i].value=="15"){
					if(formElements[i].checked){
						$("#especialidades").show();
						banderaEspecialidades = true;
					}else{
						if (banderaEspecialidades == false) {
							$("#especialidades").hide();	
						}
					}
				}	
			}
		}
	}
	
	function cerrar(){
		$.fancybox.open({
			content : "<div id=\"contenedor2_popup\"><div class=\"icon_popup ask\"></div><h1>No se han guardado los datos registrados, desea continuar?</h1><div class=\"btn_popup\"><div class=\"btn_si_popup\" onclick=\"$.fancybox.close();\"></div><div class=\"btn_no_popup\" onclick=\"cerrarN();\"></div></div></div>",
			type : 'iframe',
			openEffect : 'elastic',
			openSpeed  : 150,
			closeEffect : 'elastic',
			closeSpeed  : 150,
			autoSize: false,
			width: 500,
			height: 190,
			afterClose : function(){
				$.fancybox.close();
			}
		});		
	}
	
	function cerrarN(){
		var ctx = "${pageContext.request.contextPath}";
		window.location.href = ctx+"/mantenimientoUsuarios/inicio";
	}
	
	function enviar(){
		var fechaNacimientoAux = $("#fechaNacimientoAux").val();
		$("#fechaNacimiento").val(fechaNacimientoAux);
		$("#mantenimientoUsuarioNuevoForm").submit();
	}	
	
	$( document ).ready(function() {
		$.ajaxSetup({ cache: false });
		
		$("#rfc").alphanum({
	        allowSpace: false,
	        disallow: '¥Á«ø®°∑'
	    });		
		$("#nombre").alpha({
	        allowSpace: true,
	        disallow: '¥Á«ø®°∑'
	    });		
		$("#apellidoPaterno").alpha({
	        allowSpace: true,
	        disallow: '¥Á«ø®°∑'
	    });
		$("#apellidoMaterno").alpha({
	        allowSpace: true,
	        disallow: '¥Á«ø®°∑'
	    });
		$("#curp").alphanum({
	        allowSpace: false,
	        disallow: '¥Á«ø®°∑'
	    });
		$("#telefono").numeric({
	        allowSpace: false,
	        disallow: '¥Á«ø®°∑'
	    });
		$("#mail").alphanum({
	        allowSpace: false,
	        allow: '@.-_',
	        disallow: '¥Á«ø®°∑'
	    });
		$("#claveInterna").alphanum({
	        allowSpace: false,
	        disallow: '¥Á«ø®°∑'
	    });
		$("#regSs").numeric({
	        allowSpace: false,
	        disallow: '¥Á«ø®°∑'
	    });
		$("#cedula").numeric({
	        allowSpace: false,
	        disallow: '¥Á«ø®°∑'
	    });
		$("#cedula2").numeric({
	        allowSpace: false,
	        disallow: '¥Á«ø®°∑'
	    });
		$("#cedula3").numeric({
	        allowSpace: false,
	        disallow: '¥Á«ø®°∑'
	    });
		$("#iEducativa").alphanum({
	        allowSpace: true,
	        disallow: '¥Á«ø®°∑'
	    });
	    $("#iEducativa2").alphanum({
	        allowSpace: true,
	        disallow: '¥Á«ø®°∑'
	    });
	    $("#iEducativa3").alphanum({
	        allowSpace: true,
	        disallow: '¥Á«ø®°∑'
	    });
	    
		$(".fancybox").fancybox();
		
		var fechaNacimiento = $("#fechaNacimiento").val();
		$("#fechaNacimientoAux").val(fechaNacimiento);
		
		var mostrarDeclaracion = $("#mostrarDeclaracion").val();
		var termino = $("#termino").val();
		var banderaError = $("#banderaError").val();
		var statusInput = $("#statusInput").val();
		var statusSelect = $("#statusSelect").val();
		var idUsuario = $("#idUsuario").val();
		var ctx = "${pageContext.request.contextPath}";
		if(mostrarDeclaracion=="true"){
			$("#respuesta").val("false");
			$.fancybox.open({
				href : ctx + '/mantenimientoUsuarios/mostrarDeclaracionPendiente?idUsuario='+idUsuario,
				type : 'iframe',
				padding : 5,
				autoSize: false,
				width: 770,
				height: 590,
				afterClose : function(){
					var respuesta =  $("#respuesta").val();
					if (respuesta=="false") {
						$("#estadoUsuario").val(3);
						var mantenimientoUsuarioNuevoForm = $("#mantenimientoUsuarioNuevoForm").serialize();
						$.getJSON("updateUsuario", mantenimientoUsuarioNuevoForm ,function(response){
							if(response.errores.length == 0){
								$.fancybox.open({
									content : "<div id=\"contenedor2_popup\"><div class=\"icon_popup usuario1\"></div><h1 style=\"margin-left:3.5vw;\">Usuario fue registrado pero debe de Aceptar la Declaracion para quedar vigente en el sistema</h1><div class=\"btn_popup\"><div class=\"btn_aceptar_popup\" onclick=\"parent.$.fancybox.close();\"></div></div></div>",
									openEffect : 'elastic',
									openSpeed  : 150,
									closeEffect : 'elastic',
									closeSpeed  : 150,
									autoSize: false,
									width: 420,
									height: 180,
									afterClose : function(){
										window.location.href = ctx+"/mantenimientoUsuarios/inicio";
									}
								});
								
							}else{
				    			$("#errores").empty();
				    			$.each(response.errores, function(index, item) {
				    				$("#errores").append(item+"</br>"); 
				                });	
				    		}
						});
					}else{
						if (IniciarUsuario($("#idUsuario").val(), 0, 0, $("#rfc").val(), "RFC",  $("#nombre").val(), $("#apellidoPaterno").val(), $("#apellidoMaterno").val(), $("#sexo").val(), $("#fechaNacimientoAux").val(), "9")) {
							$("#estadoUsuario").val(6);
							var mantenimientoUsuarioNuevoForm = $("#mantenimientoUsuarioNuevoForm").serialize();
							$.getJSON("updateUsuario", mantenimientoUsuarioNuevoForm ,function(response){
								if(response.errores.length == 0){
									$.fancybox.open({
										content : "<div id=\"contenedor2_popup\"><div class=\"icon_popup usuario1\"></div><h1 style=\"margin-left:3.5vw;\">Usuario creado exitosamente</h1><div class=\"btn_popup\"><div class=\"btn_aceptar_popup\" onclick=\"parent.$.fancybox.close();\"></div></div></div>",
										openEffect : 'elastic',
										openSpeed  : 150,
										closeEffect : 'elastic',
										closeSpeed  : 150,
										autoSize: false,
						 				width: 370,
						 				height: 150,
						 				afterClose : function(){
						 					var ctx = "${pageContext.request.contextPath}";
						 					window.location.href = ctx+"/mantenimientoUsuarios/inicio";
						 				}
									});
								}else{
					    			$("#errores").empty();
					    			$.each(response.errores, function(index, item) {
					    				$("#errores").append(item+"</br>"); 
					                });	
					    		}
								
							});	
						}else{
							$("#estadoUsuario").val(1);
							var mantenimientoUsuarioNuevoForm = $("#mantenimientoUsuarioNuevoForm").serialize();
							$.getJSON("updateUsuario", mantenimientoUsuarioNuevoForm ,function(response){
								$.fancybox.open({
									content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup alert\"></div><h1>No se puede Enrolar huella del usuario, debe solicitar AutorizaciÛn para uso de Clave</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"parent.$.fancybox.close();\"></div></div></div>",
									openEffect : 'elastic',
									openSpeed  : 150,
									closeEffect : 'elastic',
									closeSpeed  : 150,
					 				autoSize: false,
					 				width: 500,
					 				height: 190,
					 				afterClose : function(){
					 					var ctx = "${pageContext.request.contextPath}";
					 					window.location.href = ctx+"/mantenimientoUsuarios/inicio";
					 				}
								});
							});			
						}
					}
				}
			});				
		}else if (termino=="true") {
			$.fancybox.open({
				content : "<div id=\"contenedor2_popup\"><div class=\"icon_popup usuario1\"></div><h1>Usuario creado exitosamente</h1><div class=\"btn_popup\"><div class=\"btn_aceptar_popup\" onclick=\"parent.$.fancybox.close();\"></div></div></div>",
				openEffect : 'elastic',
				openSpeed  : 150,
				closeEffect : 'elastic',
				closeSpeed  : 150,
				autoSize: false,
 				width: 370,
 				height: 150,
 				afterClose : function(){
 					var ctx = "${pageContext.request.contextPath}";
 					window.location.href = ctx+"/mantenimientoUsuarios/inicio";
 				}
			});
		}else if(banderaError=="true"){
			var error = $("#error").val();
			if(error!=""){
				$.fancybox.open({
					content : "<div id=\"contenedor2_popup\"><div class=\"icon_popup usuario1\"></div><h1>"+error+"</h1><div class=\"btn_popup\"><div class=\"btn_aceptar_popup\" onclick=\"parent.$.fancybox.close();\"></div></div></div>",
					openEffect : 'elastic',
					openSpeed  : 150,
					closeEffect : 'elastic',
					closeSpeed  : 150,
					autoSize: false,
	 				width: 370,
	 				height: 150
				});		
			}
					
		}
		
		if(statusInput=="true"){
			$("#nombre").prop("readonly", true);
			$("#apellidoPaterno").prop("readonly", true);
			$("#apellidoMaterno").prop("readonly", true);
			$("#curp").prop("readonly", true);
			$("#telefono").prop("readonly", true);
			$("#mail").prop("readonly", true);
			$("#fechaNacimientoAux").datepicker( "option", "disabled", true );
		}else{
			$("#nombre").prop("readonly", false);
			$("#apellidoPaterno").prop("readonly", false);
			$("#apellidoMaterno").prop("readonly", false);
			$("#curp").prop("readonly", false);
			$("#telefono").prop("readonly", false);
			$("#mail").prop("readonly", false);
			$("#fechaNacimientoAux").datepicker( "option", "disabled", false );
		}
		
		if(statusSelect=="true"){
			$("#regSs").prop("readonly", true);
			$("#claveInterna").prop("readonly", true);
			$("#cedulaProfesional1").prop("readonly", true);
			$("#idInstitucion1").prop("disabled", true);
			$("#idEspecialidad2").prop("disabled", true);
			$("#cedulaProfesional2").prop("readonly", true);
			$("#idInstitucion2").prop("disabled", true);
			$("#idEspecialidad3").prop("disabled", true);
			$("#cedulaProfesional3").prop("readonly", true);
			$("#idInstitucion3").prop("disabled", true);	
		}else{
			$("#regSs").prop("readonly", false);
			$("#claveInterna").prop("readonly", false);
			$("#cedulaProfesional1").prop("readonly", false);
			$("#idInstitucion1").prop("disabled", false);
			$("#idEspecialidad2").prop("disabled", false);
			$("#cedulaProfesional2").prop("readonly", false);
			$("#idInstitucion2").prop("disabled", false);
			$("#idEspecialidad3").prop("disabled", false);
			$("#cedulaProfesional3").prop("readonly", false);
			$("#idInstitucion3").prop("disabled", false);
		}
		verificarMedicoCheck();
	});
	
	$(function() {
		$("#fechaNacimientoAux").datepicker({
			changeMonth: true,
			changeYear: true,
			maxDate: 0
		});
	});
</script>
<body>	
	<div id="contenido">
		<form:form method="post" action="agregarUsuarioNuevo" commandName="mantenimientoUsuarioNuevoForm" id="mantenimientoUsuarioNuevoForm">
			<form:hidden path="rfcAux" id="rfcAux"/>
			<form:hidden path="idUsuario" id="idUsuario"/>
			<form:hidden path="mostrarDeclaracion" id="mostrarDeclaracion"/>
			<form:hidden path="estadoUsuario" id="estadoUsuario"/>
			<form:hidden path="validarEspecialidades" id="validarEspecialidades"/>
			<form:hidden path="termino" id="termino"/>
			<form:hidden path="statusInput" id="statusInput"/>
			<form:hidden path="statusSelect" id="statusSelect"/>
			<form:hidden path="error" id="error"/>
			<form:hidden path="banderaError" id="banderaError"/>
			<input type="hidden" id="respuestaRegistrado" />
			<input type="hidden" id="respuestaGenerarRfc" />
			<input type="hidden" id="respuesta" />
			
			<form:errors path="error"></form:errors><div class="error size1"><span id="errores"></span></div>
           	
           	<div class="linea1">
           		<form:input path="rfc" id="rfc" onblur="verificarRfc();" cssClass="campo size1 mayusculas" placeholder="RFC" maxlength="15" />
           		<form:input path="usuarios.nombre" cssClass="campo size4 mayusculas" placeholder="Nombre" id="nombre" maxlength="45" />
           		<form:input path="usuarios.apellidoPaterno" cssClass="campo size1 mayusculas" placeholder="Apellido Paterno" id="apellidoPaterno" maxlength="45" />
           		<form:input path="usuarios.apellidoMaterno" cssClass="campo size1 mayusculas" placeholder="Apellido Materno" id="apellidoMaterno" maxlength="45" />
			</div>
            <div class="linea5">
				<div class="error size1"><form:errors path="rfc" /></div>
				<div class="error size4"><form:errors path="usuarios.nombre" /></div>
				<div class="error size1"><form:errors path="usuarios.apellidoPaterno" /></div>
				<div class="error size1"><form:errors path="usuarios.apellidoMaterno" /></div>
			</div>					
            <div class="linea2">
            	<input type="text" class="campo size2" placeholder="Fecha de Nac" id="fechaNacimientoAux" readonly />
            	<form:hidden path="fechaNacimiento" id="fechaNacimiento"/>
            	<form:input path="usuarios.curp" cssClass="campo size7 mayusculas" placeholder="CURP" id="curp" maxlength="20"/>
            	<form:input path="usuarios.telefono" cssClass="campo size6" placeholder="TelÈfono" id="telefono" maxlength="20" />
            	<form:input path="usuarios.mail" cssClass="campo size1" placeholder="Correo ElectrÛnico" id="mail" maxlength="100" />
                <div class="genero">
                   	<h4>Sexo
                   		<form:radiobutton path="sexo" value="1" cssClass="radiob"/> H
                   		<form:radiobutton path="sexo" value="2" cssClass="radiob"/> M
                	</h4>
                </div>
			</div>
            <div class="linea5">
	  			<div class="error size2"><form:errors path="fechaNacimiento" /></div>
                <div class="error size7"><form:errors path="usuarios.curp" /></div>
                <div class="error size6"><form:errors path="usuarios.telefono" /></div>
                <div class="error size1"><form:errors path="usuarios.mail" /></div>
			</div>
			<div class="linea2">
				<h4>Roles:</h4>
				<h5><form:checkboxes items="${catRoles}" element="span class='check_box'" path="roles" itemLabel="rol" itemValue="rolId" onclick="verificarMedicoCheck();" /></h5>
			</div>
            <div class="linea5">
				<div class="error size3"><form:errors path="roles" /></div>
			</div>			
			<div id="especialidades" style="display: none;">
				<div class="linea3">
					<form:input path="claveInterna" cssClass="campo size1" placeholder="Clave interna" id="claveInterna" maxlength="50" />
				</div>
                    
                <div class="linea5">
           			<div class="error size3"><form:errors path="claveInterna" /></div>
				</div>
				
				<div class="linea2">
					<input type="text" value="Medicina General" class="campo size1" readonly="true">
					<form:hidden path="idEspecialidad1" />
					<form:input path="usuarios.regSs" cssClass="campo size2" placeholder="No. Reg. S.S." id="regSs" maxlength="50" />
					<form:input path="cedulaProfesional1" cssClass="campo size4" placeholder="CÈd. Profesional" id="cedula" maxlength="60" />
					<form:input path="idInstitucion1" cssClass="campo size7 mayusculas" placeholder="Institucion Educativa" id="iEducativa" maxlength="100"/>							
				</div>
				
                <div class="linea5">
                	<div class="error size1"><form:errors path="idEspecialidad1" /></div>
                    <div class="error size2"><form:errors path="usuarios.regSs" /></div>
                    <div class="error size4"><form:errors path="cedulaProfesional1" /></div>
                	<div class="error size7"><form:errors path="idInstitucion1" /></div>
                </div>				
                <div class="linea2">
					<form:select path="idEspecialidad2" cssClass="campo selectt size5">
						<form:option value="-1" label="Seleccione una Especialidad" />
						<form:options items="${especialidades}" itemLabel="especialidadMedica" itemValue="especialidadMedicaId" />
					</form:select>
					<form:input path="cedulaProfesional2" cssClass="campo size4" placeholder="CÈd. Profesional" id="cedula2" maxlength="60" />
					<form:input path="idInstitucion2" cssClass="campo size7 mayusculas" placeholder="Institucion Educativa" id="iEducativa2" maxlength="100"/>
                </div>                
                <div class="linea5">
                    <div class="error size5"><form:errors path="idEspecialidad2" /></div>
                    <div class="error size4"><form:errors path="cedulaProfesional2" /></div>
                	<div class="error size7"><form:errors path="idInstitucion2" /></div>
                </div>                
                <div class="linea2">
	                <form:select path="idEspecialidad3" cssClass="campo selectt size5">
						<form:option value="-1" label="Seleccione una Especialidad" />
						<form:options items="${especialidades}" itemLabel="especialidadMedica" itemValue="especialidadMedicaId" />
					</form:select>
					<form:input path="cedulaProfesional3" cssClass="campo size4" placeholder="CÈd. Profesional" id="cedula3" maxlength="60"/>
					<form:input path="idInstitucion3" cssClass="campo size7 mayusculas" placeholder="Institucion Educativa" id="iEducativa3" maxlength="100"/>								
                </div>
                <div class="linea5">
                    <div class="error size5"><form:errors path="idEspecialidad3" /></div>
                    <div class="error size4"><form:errors path="cedulaProfesional3" /></div>
                	<div class="error size7"><form:errors path="idInstitucion3" /></div>
                </div>
			</div>
			<div class="linea1">    
				<input type="button" value="" class="btn btn_guardar" onclick="enviar();"/>
				<input type="button" class="btn btn_cerrar" onclick="cerrar();">			
			</div>

		</form:form>
	</div>
</body>
</html>