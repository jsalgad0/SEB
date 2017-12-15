<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page session="true"%>
<html>
<OBJECT classid='CLSID:592B9D7E-51C9-401F-A03C-4DE61FF7008D' name="Autentia" id='Autentia'>
	<embed></embed>
</OBJECT>
<head>
<script type="text/javascript">
	function editar(){
		$("#usuarioForm").attr("action", "editar");
		$("#usuarioForm").submit();
	}
	var cont = true;
	$(document).ready(function(){
		var form = document.getElementById('usuarioForm');
		$("#adminInstitucion").autocomplete({
		    serviceUrl: "adminInstitucion",
	 	    paramName: "busqueda",
		    transformResult: function(response) {
		        return {
		            suggestions: $.map(JSON.parse(response), function(dataItem) {
		                return { value: dataItem.value, data: dataItem.data };
		            })
		        };
		    },
		    onSelect: function (suggestion) {
		    	$("#adminInstitucion").val(suggestion.data);
		    	
				$.getJSON("aseguradorById", {busqueda:suggestion.data} ,function(response){ 
		            $.each(response, function(index, item) {
		            	$("#adminInstitucion").val(item.nombreRazonSocial);
		            });
				});		    	    	
		    },
		    onInvalidateSelection:function() {
		    	$("#adminInstitucion").val("");
	        }
		});
		
		form.addEventListener('submit', function(event) {
			if($("#nombreUsuario")==null || $("#nombreUsuario").val()=='' || $("#nombreUsuario").val().length<1){
				alert("Favor de ingresar el nombre");
				event.preventDefault();
			}else if($("#apellidoPaterno")==null || $("#apellidoPaterno").val()=='' || $("#apellidoPaterno").val().length<1){
				alert("Favor de ingresar el apellido paterno");
				event.preventDefault();
			}else if($("#apellidoMaterno")==null || $("#apellidoMaterno").val()=='' || $("#apellidoMaterno").val().length<1){
				alert("Favor de ingresar el apellido materno");
				event.preventDefault();
			}else if($("#fechaDeNacimiento")==null || $("#fechaDeNacimiento").val()=='' || $("#fechaDeNacimiento").val().length<1){
				alert("Favor de ingresar la fecha de nacimiento");
				event.preventDefault();
			}else if($("#idSexo")==null || $("#idSexo").val()=="undefined" || $("#idSexo").val()==-1){
				alert("Favor de seleccionar un sexo");
				event.preventDefault();
			}else if($("#idEstado")==null || $("#idEstado").val()=="undefined" || $("#idEstado").val()==-1){
				alert("Favor de seleccionar un estado");
				event.preventDefault();
			}else if($("#rfc")==null || $("#rfc")=='' || $("#rfc").val().length<13){
				alert("El formato del RFC no es correcto por lo que el sistema generara uno");			
				var nombre = $("#nombreUsuario").val();
				var apellidoP = $("#apellidoPaterno").val();
				var apellidoM = $("#apellidoMaterno").val();
				var fecha = $("#fechaDeNacimiento").val();
				$.getJSON("/sab/generic/rfc", {nombre:nombre, apellidoP:apellidoP, apellidoM:apellidoM, fecha:fecha} ,function(response){
					$("#rfc").val(response);
			    });
				event.preventDefault();
			}else{
				var esp = $("#idEstado");
				var espValor = $("#idEstado").val();
				var texto = esp[0][espValor].innerText;
				if(!IniciarUsuario(0,0,0, $("#rfc").val(), "RFC", $("#nombreUsuario").val(), $("#apellidoPaterno").val(), $("#apellidoMaterno").val(), $("#idSexo").val(),
						$("#fechaDeNacimiento").val(),texto)){
					event.preventDefault();
				}	
			}
		});
		
		if($("#idUsuario").val()!= undefined && $("#idUsuario").val() != null && $("#idUsuario").val()!=''){
			if($("#idModulo").val() == 1){
				$("#administradorSab").show();
			}else if($("#idModulo").val() == 2){
				$("#asegurador").show();
			}else if($("#idModulo").val() == 3){
				$("#lugarAtencion").show();
			}else if($("#idModulo").val() == 4){
				$("#farmacia").show();
			}else if($("#idModulo").val() == 5){
				$("#prestador").show();
			}
			$("#idModulo").val(form.idModulo.value);
			var idModulo = $("#idModulo").val();
			var usuario = $("#idUsuario").val();
		    $.getJSON("/sab/usuarios/rolesSeleccionado", {id:idModulo, idUsuario:usuario} ,function(response){
	            var options = '';
	            $("#idRoles"+idModulo).html(options); 
	            $.each(response, function(index, item) {
	            	if(item.rolesId == 14){
	            		if(item.seleccionado == '1'){
	            			options += item.rol+'<input id="roles" name="roles" type="checkbox" checked="checked" value="'+item.rolesId+'" onclick="getEspecialidades('+item.rolesId+')"; /> ';
	                		options += '<input type="hidden" id="rolMedico" = value="0" />';	
	            		}else{
	            			options += item.rol+'<input id="roles" name="roles" type="checkbox" value="'+item.rolesId+'" onclick="getEspecialidades('+item.rolesId+')"; /> ';
	                		options += '<input type="hidden" id="rolMedico" = value="0" />';
	            		}            		
	            	}else{
	            		if(item.seleccionado == '1'){
	            			options += item.rol+'<input id="roles" name="roles" type="checkbox" checked="checked" value="'+item.rolesId+'" /> ';
	            		}else{
	            			options += item.rol+'<input id="roles" name="roles" type="checkbox" value="'+item.rolesId+'" /> ';
	            		}
	            	}
	            });
	            $("#idRoles"+idModulo).html(options);
		    });	 
		}
	});
	function getUsuarios(){
		if($("#idModulo").val() == 1){
			$("#administradorSab").show();
			$("#asegurador").hide();
			$("#lugarAtencion").hide();
			$("#farmacia").hide();
			$("#prestador").hide();
		}else if($("#idModulo").val() == 2){
			$("#administradorSab").hide();
			$("#asegurador").show();
			$("#lugarAtencion").hide();
			$("#farmacia").hide();
			$("#prestador").hide();
		}else if($("#idModulo").val() == 3){
			$("#administradorSab").hide();
			$("#asegurador").hide();
			$("#lugarAtencion").show();
			$("#farmacia").hide();
			$("#prestador").hide();
		}else if($("#idModulo").val() == 4){
			$("#administradorSab").hide();
			$("#asegurador").hide();
			$("#lugarAtencion").hide();
			$("#farmacia").show();
			$("#prestador").hide();
		}else if($("#idModulo").val() == 5){
			$("#administradorSab").hide();
			$("#asegurador").hide();
			$("#lugarAtencion").hide();
			$("#farmacia").hide();
			$("#prestador").show();
		}else{
			$("#administradorSab").hide();
			$("#asegurador").hide();
			$("#lugarAtencion").hide();
			$("#farmacia").hide();
			$("#prestador").hide();
		}
		
		var idModulo = $("#idModulo").val();
	    $.getJSON("/sab/generic/roles", {id:idModulo} ,function(response){
            var options = '';
            $("#idRoles"+idModulo).html(options); 
            $.each(response, function(index, item) {
            	if(item.rolesId == 14){
            		options += item.rol+'<input id="roles" name="roles" type="checkbox" value="'+item.rolesId+'" onclick="getEspecialidades('+item.rolesId+')"; /> ';
            		options += '<input type="hidden" id="rolMedico" = value="0" />';
            	}else{
            		options += item.rol+'<input id="roles" name="roles" type="checkbox" value="'+item.rolesId+'" /> ';
            	}
            });
            $("#idRoles"+idModulo).html(options);
	    });	    
	}
	function especialidad(){
		var arrHidden = $("#idMatriculas input[type=hidden]");
		cont = true;
		arrHidden.each(function (i) {
			var val = $(this).attr('value');
			if(val == $("#idEspecialidades").val()){
				cont = false;	
			}
		});
		var esp = $("#idEspecialidades");
		var espValor = $("#idEspecialidades").val();
		var texto = esp[0][espValor].innerText;
		if(cont){
			var matricula = '<div id="divMatricula'+$("#idEspecialidades").val()+'"> '+texto+' <input id="especialiadesVo.matricula" name="especialiadesVo.matricula" type="text" />'; 
				matricula += '<input type="hidden" id="especialiadesVo.id" name="especialiadesVo.id" value="'+$("#idEspecialidades").val()+'"/>';
				matricula += '<input type="button" value="borrar" onclick="borraEspecialidad('+$("#idEspecialidades").val()+')"><br></div>';
			$("#idMatriculas").append(matricula);
		}
		$("#idMatriculas").show();
	}
	function borraEspecialidad(id){	
		$("#divMatricula"+id).remove();
		$("#divMatricula"+id).empty();
	}
	function tipoIdentificador(){
		var arrHidden = $("#idIdentificador input[type=hidden]");
		cont = true;
		arrHidden.each(function (i) {
			var val = $(this).attr('value');
			if(val == $("#idTipoIdentificador").val()){
				cont = false;	
			}
		});
		if(cont){
			var esp = $("#idTipoIdentificador");
			var espValor = $("#idTipoIdentificador").val();
			var texto = esp[0][espValor].innerText;
			var matricula = '<div id="divIdentificador'+$("#idTipoIdentificador").val()+'"> '+texto+' <input id="tipoIdentificadorVo.valor" name="tipoIdentificadorVo.valor" type="text" />'; 
				matricula += '<input type="hidden" id="tipoIdentificadorVo.id" name="tipoIdentificadorVo.id" value="'+$("#idTipoIdentificador").val()+'"/>';
				matricula += '<input type="button" value="borrar" onclick="borraTipoIdentificador('+$("#idTipoIdentificador").val()+')"><br></div>';
			$("#idIdentificador").append(matricula);
		}
		$("#idIdentificador").show();
	}
	function borraTipoIdentificador(id){
		$("#divIdentificador"+id).remove();
		$("#divIdentificador"+id).empty();
	}
	function getEspecialidades(id){
		if(id == 14){
			if($("#rolMedico").val() == "1"){
				$("#rolMedico").val(0);
				$("#selectEspecialidades").hide();
				$("#selectTipoIdentificador").hide();
			}else{
				$("#rolMedico").val(1);
				$("#selectEspecialidades").show();
				$("#selectTipoIdentificador").show();
			}
		}
	}	
</script>
</head>
<body>
	<form:form method="post" action="${pageContext.request.contextPath}/usuarios/nuevo"
				commandName="usuarioForm" align="center">
			<form:hidden path="idUsuario" value="${idUsuario }" />
			<span>
				<form:errors path="error"></form:errors>
			</span>
			<table align="center" border="1">
			<tr>
				<td>RFC:</td>
				<td><form:input path="usuario.rfc" id="rfc" /></td>
			</tr>
			<tr>
				<td>Nombre:</td>
				<td><form:input path="usuario.nombre" id="nombreUsuario" /></td>
			</tr>
			<tr>
				<td>Apellido Paterno:</td>
				<td><form:input path="usuario.apellidoPaterno" id="apellidoPaterno" /></td>
			</tr>
			<tr>
				<td>Apellido Materno:</td>
				<td><form:input path="usuario.apellidoMaterno" id="apellidoMaterno" /></td>
			</tr>
			<tr>
				<td>Correo Electronico:</td>
				<td><form:input path="usuario.mail" id="mail" /></td>
			</tr>	
			<tr>
				<td>Sexo:</td>
				<td>Hombre <form:radiobutton path="idSexo" id="idSexo" value="1" /></td>
				<td>Mujer <form:radiobutton path="idSexo" id="idSexo" value="2" /></td>
			</tr>	
			<tr>
				<td>Curp:</td>
				<td><form:input path="usuario.curp" id="curp" /></td>
			</tr>
			<tr>
				<td>Fecha Nacimiento:</td>
				<c:choose>
					<c:when test="${not empty usuarioForm.fechaDeNacimiento }">
						<td><form:input path="fechaDeNacimiento" id="fechaDeNacimiento" value="${usuarioForm.fechaDeNacimiento }" /></td>
					</c:when>
					<c:otherwise>
						<td><form:input path="fechaDeNacimiento" id="fechaDeNacimiento" /></td>	
					</c:otherwise>
				</c:choose>
			</tr>	
			<tr>
				<td>Estado:</td>
				<td>
					<form:select path="idEstado" id="idEstado" onchange="municipios();">
						<form:option value="-1" label="-Selecciona un Estado-" />
						<form:options items="${catEstados}" itemLabel="estado" itemValue="estadoId" ></form:options>
					</form:select>
				</td>
			</tr>
			<tr>
				<td>Tipo Usuario:</td>
				<td>
					<form:select path="idModulo" id="idModulo" onchange="getUsuarios();" >
						<form:option value="-1" label="-Selecciona un Tipo de Usuario-" />
						<form:options items="${catModulos}" itemLabel="modulo" itemValue="moduloId" ></form:options>
					</form:select>
				</td>
			</tr>	
		</table>
		<div id="administradorSab" style="display: none;">
			<table>
				<tr>
					<td><div id="idRoles1"></div> </td>
				</tr>
			</table>
		</div>
		<div id="asegurador" style="display: none;">
			<table>
				<tr>
					<td>Institucion:<form:input path="adminInstitucion" id="adminInstitucion" placeholder="Institucion" autocomplete="on" /></td>
				</tr>
				<tr>
					<td><div id="idRoles2"></div> </td>
				</tr>
			</table>
		</div>
		<div id="lugarAtencion" style="display: none" >
			<table align="center">
				<tr>
					<td>Selecciona uno o varios lugar de atención:</td>
				</tr>
				<tr>
					<td>
						<form:select style="width = 300px;" path="idLugarAtencion" id="idLugarAtencion" multiple="true"  >
							<form:options items="${lugarAtencion}" itemLabel="descripcion" itemValue="lugarDeAtencionId" ></form:options>
						</form:select>
	              	</td>
				</tr>
				<tr>
					<td><div id="idRoles3">
					</div> </td>
				</tr>
				<tr>
					<td>
						<c:choose>
							<c:when test="${not empty usuarioForm.especialiadesVo.id }">
								<div id="selectEspecialidades" >
							</c:when>
							<c:otherwise>
								<div id="selectEspecialidades" style="display: none" >
								</c:otherwise>
							</c:choose>
									<form:select style="width = 300px;" path="idEspecialidades" id="idEspecialidades" multiple="false" onchange="especialidad();" >
										<form:option value="-1" label="-Selecciona una Especialidad-" />
										<form:options items="${catEspecialidades}" itemLabel="especialidadMedica" itemValue="especialidadMedicaId" ></form:options>
									</form:select>
								</div>						
	              	</td>
				</tr>
				<tr>
					<td>
						<c:choose>
							<c:when test="${not empty usuarioForm.especialiadesVo.id }">
								<div id="idMatriculas" >
									<c:forEach var="esp" items="${usuarioForm.especialiadesVo.id }" varStatus="status">
										<c:forEach var="especialidades" items="${catEspecialidades}">
											<c:if test="${esp eq especialidades.especialidadMedicaId }">
												<div id="divMatricula${esp }"> ${especialidades.especialidadMedica } <input id="especialiadesVo.matricula" name="especialiadesVo.matricula" type="text" value="${usuarioForm.especialiadesVo.matricula[status.count-1] }" />
												<input type="hidden" id="especialiadesVo.id" name="especialiadesVo.id" value="${esp }" />
												<input type="button" value="borrar" onclick="borraEspecialidad('${esp }')"><br></div>
											</c:if>
										</c:forEach>									
									</c:forEach>							
								</div>	
							</c:when>
							<c:otherwise>
								<div id="idMatriculas" style="display: none" ></div>
							</c:otherwise>
						</c:choose>						 
					</td>
				</tr>
				<tr>
					<td>
						<c:choose>
							<c:when test="${not empty usuarioForm.tipoIdentificadorVo.id }">
								<div id="selectTipoIdentificador" >	
							</c:when>
							<c:otherwise>
								<div id="selectTipoIdentificador" style="display: none" >
							</c:otherwise>
						</c:choose>						
							<form:select style="width = 300px;" path="idTipoIdentificador" id="idTipoIdentificador" multiple="false" onchange="tipoIdentificador();" >
								<form:option value="-1" label="-Selecciona un Tipo de Identificador-" />
								<form:options items="${tipoIdentificador}" itemLabel="tipoIdentificador" itemValue="tipoIdentificadorId" ></form:options>
							</form:select>
						</div>
	              	</td>
				</tr>				
				<tr>
					<td>
					<c:choose>
						<c:when test="${not empty usuarioForm.tipoIdentificadorVo.id }">
							<div id="idIdentificador" >
								<c:forEach var="identificador" items="${usuarioForm.tipoIdentificadorVo.id }" varStatus="status">
										<c:forEach var="tipoIdentificador" items="${tipoIdentificador}">
											<c:if test="${identificador eq tipoIdentificador.tipoIdentificadorId }">
												<div id="divIdentificador${identificador }"> ${tipoIdentificador.tipoIdentificador } <input id="tipoIdentificadorVo.valor" name="tipoIdentificadorVo.valor" type="text" value="${usuarioForm.tipoIdentificadorVo.valor[status.count-1] }" />
												<input type="hidden" id="tipoIdentificadorVo.id" name="tipoIdentificadorVo.id" value="${identificador }" />
												<input type="button" value="borrar" onclick="borraTipoIdentificador('${identificador }')"><br></div>
											</c:if>
										</c:forEach>									
									</c:forEach>
							</div>
						</c:when>
						<c:otherwise>
							<div id="idIdentificador" style="display: none"></div>
						</c:otherwise>
					</c:choose>
					</td>
				</tr>
			</table>
		</div>
		<div id="farmacia" style="display: none;">
			<table>
				<tr>
					<td>Selecciona uno o varios lugar de atención:</td>
				</tr>
				<tr>
					<td>
						<form:select style="width = 300px;" path="idLugarAtencion" id="idLugarAtencion" multiple="true" >
							<form:options items="${lugarAtencion}" itemLabel="descripcion" itemValue="lugarDeAtencionId" ></form:options>
						</form:select>
	              	</td>
				</tr>
				<tr>
					<td><div id="idRoles4"></div> </td>
				</tr>
			</table>
		</div>
		<c:choose>
			<c:when test="${usuarioForm.editar}">
				<input type="button" value="Editar" onclick="editar();" />
			</c:when>
			<c:otherwise>
				<input type="submit" value="guardar"/>
			</c:otherwise>
		</c:choose>	
	</form:form>
	<a href="/SAB/menu" align="center">Regresar al Menu</a>
</body>
</html>