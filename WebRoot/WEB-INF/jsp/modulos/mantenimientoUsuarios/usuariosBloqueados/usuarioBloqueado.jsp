<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<OBJECT classid='CLSID:592B9D7E-51C9-401F-A03C-4DE61FF7008D'
	name="Autentia" id='Autentia'>
	<embed></embed>
</OBJECT>
<script type="text/javascript">
	function verificarMedicoCheck(){
		var formElements = document.getElementById("mantenimientoUsuarioBloqueadoForm");
		var banderaEspecialidades = false;
	    for (var i = 0; i < formElements.length; i++){
	        if (formElements[i].type == "checkbox"){
	    		if(formElements[i].value=="14" || formElements[i].value=="20"){
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
	
	function actualizar(){
		if (IniciarUsuario($("#idUsuarioAdministrador").val(), 0, 0, $("#rfcAdministrador").val(), "RFC",  $("#nombreAdministrador").val(), $("#apellidoPaternoAdministrador").val(), $("#apellidoMaternoAdministrador").val(), $("#sexoAdministrador").val(), $("#fechaNacimientoAdministrador").val(), "9")) {
			$("#mantenimientoUsuarioBloqueadoForm").submit();	
		}else{
			$.fancybox.open({
				content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Falló la verificación de identidad del Administrador de Usuarios, los cambios no serán registrados</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
				openEffect : 'elastic',
				openSpeed  : 150,
				closeEffect : 'elastic',
				closeSpeed  : 150,								
 				autoSize: false,
 				width: 470,
 				height: 185,
 				afterClose : function(){
 					var ctx = "${pageContext.request.contextPath}";
 					window.location.href = ctx+"/mantenimientoUsuarios/inicio";
 				}
			});	
		}
	}
	
	function cerrar(){
		var ctx = "${pageContext.request.contextPath}";
		window.location.href = ctx+"/mantenimientoUsuarios/inicio";
	}		
	
	$( document ).ready(function() {
		$(".fancybox").fancybox();
		
		verificarMedicoCheck();
		$("#rfc").prop("readonly", true);
		$("#nombre").prop("readonly", true);
		$("#apellidoPaterno").prop("readonly", true);
		$("#apellidoMaterno").prop("readonly", true);
		$("#curp").prop("readonly", true);
		$("#telefono").prop("readonly", true);
		$("#mail").prop("readonly", false);	
		$("#claveInterna").prop("readonly", true);
		$("#idEspecialidad1").prop("readonly", true);
		$("#cedulaProfesional1").prop("readonly", true);
		$("#idInstitucion1").prop("readonly", true);
		$("#idEspecialidad2").prop("readonly", true);
		$("#cedulaProfesional2").prop("readonly", true);
		$("#idInstitucion2").prop("readonly", true);
		$("#idEspecialidad3").prop("readonly", true);
		$("#cedulaProfesional3").prop("readonly", true);
		$("#idInstitucion3").prop("readonly", true);
		var termino = $("#termino").val();
		
		if (termino == "true") {
			$.fancybox.open({
				content : "<div id=\"contenedor2_popup\"><div class=\"icon_popup usuario1\"></div><h1>Usuario actualizado exitosamente</h1><div class=\"btn_popup\"><div class=\"btn_aceptar_popup\" onclick=\"parent.$.fancybox.close();\"></div></div></div>",
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
		}
	});	
</script>
<body>	
	<div id="contenido">
		<form:form method="post" action="actualizarUsuarioBloqueado" commandName="mantenimientoUsuarioBloqueadoForm" id="mantenimientoUsuarioBloqueadoForm">
			<form:hidden path="idUsuario"/>
			<form:hidden path="idUsuarioAdministrador" id="idUsuarioAdministrador"/>
			<form:hidden path="rfcAdministrador" id="rfcAdministrador"/>
			<form:hidden path="nombreAdministrador" id="nombreAdministrador"/>
			<form:hidden path="apellidoPaternoAdministrador" id="apellidoPaternoAdministrador"/>
			<form:hidden path="apellidoMaternoAdministrador" id="apellidoMaternoAdministrador"/>
			<form:hidden path="sexoAdministrador" id="sexoAdministrador"/>
			<form:hidden path="fechaNacimientoAdministrador" id="fechaNacimientoAdministrador"/>	
			<form:hidden path="termino"/>	
			
			<form:errors path="error"></form:errors><div class="error size1"><span id="errores"></span></div>
			
			
			<div class="linea1">
	      		<form:input path="rfc" id="rfc" cssClass="campo size1" placeholder="RFC" />
	      		<form:input path="nombre" cssClass="campo size4" placeholder="Nombre" id="nombre" />
	      		<form:input path="apellidoPaterno" cssClass="campo size1" placeholder="Apellido Paterno" id="apellidoPaterno" />
	      		<form:input path="apellidoMaterno" cssClass="campo size1" placeholder="Apellido Materno" id="apellidoMaterno" />
			</div>
			
            <div class="linea2">
            	<form:input path="fechaNacimiento" cssClass="campo size2" placeholder="Fecha de Nac" id="fechaNacimiento" />
            	<form:input path="curp" cssClass="campo size7" placeholder="CURP" id="curp" />
            	<form:input path="telefono" cssClass="campo size6" placeholder="Teléfono" id="telefono" />
            	<form:input path="mail" cssClass="campo size1" placeholder="Correo Electrónico" id="mail" />
                <div class="genero">
                   	<h4>Sexo
                   		<form:radiobutton path="sexo" value="1" cssClass="radiob" disabled="true"/> H
                   		<form:radiobutton path="sexo" value="2" cssClass="radiob" disabled="true"/> M
                	</h4>
                </div>
			</div>	
            <div class="linea5">
	  			<div class="error size2"></div>
                <div class="error size4"></div>
                <div class="error size6"></div>
                <div class="error size1"><form:errors path="mail" /></div>
			</div>		
			
            <div class="linea3">
            	<h4>Estado:</h4>
                <h5><form:radiobutton path="vigencia" value="1"/>Vigente <form:radiobutton path="vigencia" value="2"/>No Vigente <form:radiobutton path="vigencia" value="3"/>Bloqueado</h5> 
                <div class="error size3"><form:errors path="vigencia" /></div>                   
            </div>							

			<div class="linea3">
				<h4>Roles:</h4>
				<h5><form:checkboxes items="${catRoles}" element="span class='check_box'" path="roles" itemLabel="rol" itemValue="rolId" onclick="verificarMedicoCheck();" /></h5>
			</div>
            <div class="linea5">
				<div class="error size3"><form:errors path="roles" /></div>
			</div>	
			<div id="especialidades" style="display: none;">
				<div class="linea3">
					<form:input path="claveInterna" cssClass="campo size1" placeholder="Clave interna" id="claveInterna"/>
				</div>
				<div class="linea2">
					<input type="text" value="Medicina General" class="campo size1" readonly="true">
					<form:hidden path="idEspecialidad1" />
					<form:input path="regSs" cssClass="campo size2" placeholder="No. Reg. S.S." id="regSs"/>
					<form:input path="cedulaProfesional1" cssClass="campo size4" placeholder="Céd. Profesional"/>
					<form:input path="idInstitucion1" cssClass="campo size7" placeholder="Institucion Educativa" maxlength="100"/>
				</div>				

                <div class="linea2">
					<form:select path="idEspecialidad2" cssClass="campo selectt size5">
						<form:option value="-1" label="Seleccione una Especialidad" />
						<form:options items="${especialidades}" itemLabel="especialidadMedica" itemValue="especialidadMedicaId" />
					</form:select>
					<form:input path="cedulaProfesional2" cssClass="campo size4" placeholder="Céd. Profesional"/>
					<form:input path="idInstitucion2" cssClass="campo size7" placeholder="Institucion Educativa" maxlength="100"/>
                </div> 

                <div class="linea2">
	                <form:select path="idEspecialidad3" cssClass="campo selectt size5">
						<form:option value="-1" label="Seleccione una Especialidad" />
						<form:options items="${especialidades}" itemLabel="especialidadMedica" itemValue="especialidadMedicaId" />
					</form:select>
					<form:input path="cedulaProfesional3" cssClass="campo size4" placeholder="Céd. Profesional"/>
					<form:input path="idInstitucion3" cssClass="campo size7" placeholder="Institucion Educativa" maxlength="100"/>
                </div>
			</div>
			<div class="linea1">					
				<input type="button" value="" class="btn_grande btn_desbloquear" onclick="actualizar();"/>
				<input type="button" class="btn btn_cerrar" onclick="cerrar();">
			</div>					 
		</form:form>
	</div>

</body>
</html>