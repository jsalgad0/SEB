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
		var formElements = document.getElementById("mantenimientoModificarUsuarioForm");
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
	
	function actualizar(){
		if (IniciarUsuario($("#idUsuarioAdministrador").val(), 0, 0, $("#rfcAdministrador").val(), "RFC",  $("#nombreAdministrador").val(), $("#apellidoPaternoAdministrador").val(), $("#apellidoMaternoAdministrador").val(), $("#sexoAdministrador").val(), $("#fechaNacimientoAdministrador").val(), "9")) {
			$("#mantenimientoModificarUsuarioForm").submit();	
		}else{
			$.fancybox.open({
				content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>FallÛ la verificaciÛn de identidad del Administrador de Usuarios, los cambios no ser·n registrados</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
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
	        allow: '@.-',
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
		
		verificarMedicoCheck();
		var vigenciaAux = $("#vigenciaAux").val();
		$("#rfc").prop("readonly", true);
		if(vigenciaAux==1){
			$("#nombre").prop("readonly", false);
			$("#apellidoPaterno").prop("readonly", false);
			$("#apellidoMaterno").prop("readonly", false);
			$("#fechaNacimiento").datepicker( "option", "disabled", false );
			$("#curp").prop("readonly", false);
			$("#telefono").prop("readonly", false);
			$("#mail").prop("readonly", false);	
			$("#claveInterna").prop("readonly", false);
			$("#regSs").prop("readonly", false);
			$("#idEspecialidad1").prop("disabled", false);
			$("#cedulaProfesional1").prop("readonly", false);
			$("#idInstitucion1").prop("disabled", false);
			$("#idEspecialidad2").prop("disabled", false);
			$("#cedulaProfesional2").prop("readonly", false);
			$("#idInstitucion2").prop("disabled", false);
			$("#idEspecialidad3").prop("disabled", false);
			$("#cedulaProfesional3").prop("readonly", false);
			$("#idInstitucion3").prop("disabled", false);	
			
			$("#fechaNacimiento").datepicker({
				changeMonth: true,
				changeYear: true,
				maxDate: 0
			});
		}else{
			$("#nombre").prop("readonly", true);
			$("#apellidoPaterno").prop("readonly", true);
			$("#apellidoMaterno").prop("readonly", true);
			$("#fechaNacimiento").datepicker( "option", "disabled", true );
			$("#curp").prop("readonly", true);
			$("#telefono").prop("readonly", true);
			$("#mail").prop("readonly", true);	
			$("#claveInterna").prop("readonly", true);
			$("#regSs").prop("readonly", true);
			$("#idEspecialidad1").prop("disabled", true);
			$("#cedulaProfesional1").prop("readonly", true);
			$("#idInstitucion1").prop("disabled", true);
			$("#idEspecialidad2").prop("disabled", true);
			$("#cedulaProfesional2").prop("readonly", true);
			$("#idInstitucion2").prop("disabled", true);
			$("#idEspecialidad3").prop("disabled", true);
			$("#cedulaProfesional3").prop("readonly", true);
			$("#idInstitucion3").prop("disabled", true);	
			
			var formElements = document.getElementById("mantenimientoModificarUsuarioForm");
		    for (var i = 0; i < formElements.length; i++){
		        if (formElements[i].type == "checkbox"){
		        	formElements[i].disabled = true;
				}
			}
		    
		    $("#modificarVigencia").val(true);

		}
	});	

</script>
<body>	
	<div id="contenido">
		<form:form method="post" action="updateModificarUsuarios" commandName="mantenimientoModificarUsuarioForm" id="mantenimientoModificarUsuarioForm">
			<form:hidden path="idUsuario"/>
			<form:hidden path="modificarVigencia"/>
			<form:hidden path="vigenciaAux" id="vigenciaAux"/>
			<form:hidden path="idUsuarioAdministrador" id="idUsuarioAdministrador"/>
			<form:hidden path="rfcAdministrador" id="rfcAdministrador"/>
			<form:hidden path="nombreAdministrador" id="nombreAdministrador"/>
			<form:hidden path="apellidoPaternoAdministrador" id="apellidoPaternoAdministrador"/>
			<form:hidden path="apellidoMaternoAdministrador" id="apellidoMaternoAdministrador"/>
			<form:hidden path="sexoAdministrador" id="sexoAdministrador"/>
			<form:hidden path="fechaNacimientoAdministrador" id="fechaNacimientoAdministrador"/>
			
			<form:errors path="error"></form:errors><div class="error size1"><span id="errores"></span></div>
			
			<div class="linea1">
	      		<form:input path="rfc" id="rfc" cssClass="campo size1" placeholder="RFC" />
	      		<form:input path="nombre" cssClass="campo size4" placeholder="Nombre" id="nombre" maxlength="45" />
	      		<form:input path="apellidoPaterno" cssClass="campo size1" placeholder="Apellido Paterno" id="apellidoPaterno" maxlength="45" />
	      		<form:input path="apellidoMaterno" cssClass="campo size1" placeholder="Apellido Materno" id="apellidoMaterno" maxlength="45" />
			</div>
	        <div class="linea5">
				<div class="error size1"><form:errors path="rfc" /></div>
				<div class="error size4"><form:errors path="nombre" /></div>
				<div class="error size1"><form:errors path="apellidoPaterno" /></div>
				<div class="error size1"><form:errors path="apellidoMaterno" /></div>
			</div>	
            <div class="linea2">
            	<form:input path="fechaNacimiento" cssClass="campo size2" placeholder="Fecha de Nac" id="fechaNacimiento" readonly="true" />
            	<form:input path="curp" cssClass="campo size7" placeholder="CURP" id="curp" maxlength="20"/>
            	<form:input path="telefono" cssClass="campo size6" placeholder="TelÈfono" id="telefono" maxlength="20"/>
            	<form:input path="mail" cssClass="campo size1" placeholder="Correo ElectrÛnico" id="mail" maxlength="100"/>
                <div class="genero">
                   	<h4>Sexo
                   		<form:radiobutton path="sexo" value="1" cssClass="radiob"/> H
                   		<form:radiobutton path="sexo" value="2" cssClass="radiob"/> M
                	</h4>
                </div>
			</div>
            <div class="linea5">
	  			<div class="error size2"><form:errors path="fechaNacimiento" /></div>
                <div class="error size7"><form:errors path="curp" /></div>
                <div class="error size5"><form:errors path="telefono" /></div>
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
                    
                <div class="linea5">
           			<div class="error size3"><form:errors path="claveInterna" /></div>
					<form:hidden path="id1"/>
					<form:hidden path="id2"/>
					<form:hidden path="id3"/>
				</div>			
				<div class="linea2">
					<input type="text" value="Medicina General" class="campo size1" readonly="true">
					<form:hidden path="idEspecialidad1" />
					<form:input path="regSs" cssClass="campo size2" placeholder="No. Reg. S.S." id="regSs"/>
					<form:input path="cedulaProfesional1" cssClass="campo size4" placeholder="CÈd. Profesional" id="cedulaProfesional1"/>
					<form:input path="idInstitucion1" cssClass="campo size7" placeholder="Institucion Educativa" maxlength="100"/>							
				</div>
				
                <div class="linea5">
                	<div class="error size1"><form:errors path="idEspecialidad1" /></div>
                    <div class="error size2"><form:errors path="regSs" /></div>
                    <div class="error size4"><form:errors path="cedulaProfesional1" /></div>
                	<div class="error size7"><form:errors path="idInstitucion1" /></div>
                </div>
                
                <div class="linea2">
					<form:select path="idEspecialidad2" cssClass="campo selectt size5">
						<form:option value="-1" label="Seleccione una Especialidad" />
						<form:options items="${especialidades}" itemLabel="especialidadMedica" itemValue="especialidadMedicaId" />
					</form:select>
					<form:input path="cedulaProfesional2" cssClass="campo size4" placeholder="CÈd. Profesional" id="cedulaProfesional2"/>
					<form:input path="idInstitucion2" cssClass="campo size7" placeholder="Institucion Educativa" maxlength="100"/>
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
					<form:input path="cedulaProfesional3" cssClass="campo size4" placeholder="CÈd. Profesional" id="cedulaProfesional3"/>
					<form:input path="idInstitucion3" cssClass="campo size7" placeholder="Institucion Educativa" maxlength="100"/>								
                </div>
                <div class="linea5">
                    <div class="error size5"><form:errors path="idEspecialidad3" /></div>
                    <div class="error size4"><form:errors path="cedulaProfesional3" /></div>
                	<div class="error size7"><form:errors path="idInstitucion3" /></div>
                </div> 
			</div>
			<div class="linea1">
				<input type="button" value="" class="btn btn_guardar" onclick="actualizar();" />
				<input type="button" class="btn btn_cerrar" onclick="cerrar();">
			</div>			
		</form:form>
	</div>
</body>
</html>