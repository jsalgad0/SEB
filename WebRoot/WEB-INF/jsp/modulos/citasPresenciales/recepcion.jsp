<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<OBJECT classid='CLSID:592B9D7E-51C9-401F-A03C-4DE61FF7008D'
	name="Autentia" id='Autentia'>
	<embed></embed>
</OBJECT>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>
<body>
<div id="contenedor_gral">

	<div id="pre-load" style="display: none;"><div id="imagen-load"><img src="${pageContext.request.contextPath}/resources/img/load_flor.gif" alt="" /></div>
	</div>
<div id="admin_contenido">
	<form:form name='citasPresencialesForm' action="nuevoAtencionMedica" method='POST' commandName="citasPresencialesForm" id="citasPresencialesForm">
		<form:hidden path="idPersona"/>
		<form:hidden path="idAtencion"/>
		<form:hidden path="afiliadotipoidentificador.cattipoidentificador.tipoIdentificadorId" id="tipoIdentificadorId"/>
		<form:hidden path="codEmpresa"/>
		<form:hidden path="codProducto"/>
		<form:hidden path="codAfiliado"/>
		<form:hidden path="correlativo"/>
		<form:hidden path="idAfiliado"/>
		<form:hidden path="idMedico"/>
		<form:hidden path="envio"/>
		<form:hidden path="numIssste"/>
		<form:hidden path="idAfiliadoSinAsegurador"/>
		<form:hidden path="idLugarAtencion"/>
		<form:hidden path="idAgenda"/>
		<form:hidden path="autorizacionEspecial"/>
		<form:hidden path="mostrarBotonPacienteNoEnLista"/>
		<form:hidden path="menorDeEdad"/>
		<form:hidden path="idSexo"/>
		<form:hidden path="idEstado"/>
		<form:hidden path="vigencia"/>
		<form:hidden path="autentia"/>
		<input type="hidden" id="seleccionPorOrdenServicio" />
		<input type="hidden" id="prestacionesPorOrdenServicio" />
		<input type="hidden" id="banderaGuardar" />
		
		<div class="admin_titulo">Folio de Atención
    		<form:input path="folio" cssClass="campo_supervisor tam_6" placeholder="Folio" readonly="true" />
		</div>
		
	    <div class="admin_pleca"></div>
	                    
	    <div class="linea_supervisor">
	        
	
	    </div>
	    
		<div class="contenedor_recepcion">
		
	    	<div class="linea_supervisor margen4">
				<form:select path="idAsegurador" id="idAsegurador" cssClass="selectt campo_supervisor tam_2" onchange="prestadores(); identificadores();" >
					<form:option value="-1" label="Asegurador" />
					<form:options items="${aseguradores}" itemLabel="nombreRazonSocial" itemValue="aseguradorId" ></form:options>
				</form:select>

				<form:select path="idPrestador" id="idPrestador" cssClass="selectt campo_supervisor tam_2" onchange="convenios();">
					<form:option value="-1" label="Prestador"/>
					<form:options items="${prestadores}" itemLabel="nombreRazonSocial" itemValue="prestadorId" ></form:options>
				</form:select>
				
				<form:select path="idConvenio" id="idConvenio" cssClass="selectt campo_supervisor tam_11" >
					<form:option value="-1" label="Convenio" />
					<form:options items="${convenios}" itemLabel="convenio" itemValue="convenioId" ></form:options>
				</form:select>					            
	    	</div>		
		
			<div class="linea_supervisor margen4"> 
				<div class="error tam_2" id="errorAsegurador"><form:errors path="idAsegurador" /></div>
	            
	            <div class="error tam_2" id="errorPrestador"><form:errors path="idPrestador" /></div>
	            
	            <div class="error margen5 tam_9" id="errorConvenio"><form:errors path="idConvenio" /></div>
			</div>		 		
			
	        <div class="linea_supervisor margen4">
				<form:select path="idIdentificador" id="idIdentificador" cssClass="selectt campo_supervisor tam_4">
					<form:option value="-1" label="Identificador" />
					<form:options items="${catTipoIdentificador}" itemLabel="tipoIdentificador" itemValue="tipoIdentificadorId" ></form:options>
				</form:select>        
				
				<form:input path="afiliadotipoidentificador.tipoIdValor" id="tipoIdValor" cssClass="campo_supervisor tam_7" placeholder="ID Paciente" maxlength="100" />			
				
				
	            <div class="texto_supervisor gris tam_11">
	            	<div id="derechohabienteDiv"><form:radiobutton path="tipoAfiliado" value="D" label="DERECHOHABIENTE" /><form:radiobutton path="tipoAfiliado" value="B" label="BENEFICIARIO"/></div>
	            	<div id="sexoAfiliadoDiv" style="display: none;"><form:radiobutton path="sexoAfiliado" value="1" label="MASCULINO" /><form:radiobutton path="sexoAfiliado" value="2" label="FEMENINO" /></div>
	         	</div>
	            
	            <div class="texto_supervisor gris tam_7" style="font-family:quicksand-bold">
	             		Asiste el paciente?
	                    <form:checkbox path="personaConfianza" id="personaConfianza"/>
	            </div>
	
	    	</div>	
    	
	        <div class="linea_supervisor margen4">
	        	<div class="error tam_4"><form:errors path="idIdentificador" /></div>
	        	
	        	<div class="error tam_7"><form:errors path="afiliadotipoidentificador.tipoIdValor" /></div>
	            
	            <div class="error tam_9"><form:errors path="tipoAfiliado" /></div>
	        </div>     		
			
	     	<div class="linea_supervisor margen4">
	     		<form:input path="agenda.afiliado.apellidoPaterno" id="apellidoPaterno" cssClass="campo_supervisor tam_9 mayusculas" placeholder="Apellido Paterno" maxlength="60"/>
	     		<form:input path="agenda.afiliado.apellidoMaterno" id="apellidoMaterno" cssClass="campo_supervisor tam_9 mayusculas" placeholder="Apellido Materno" maxlength="60"/>
	     		<form:input path="agenda.afiliado.nombre" id="nombre" cssClass="campo_supervisor tam_5 mayusculas" placeholder="Nombre" maxlength="60"/>
	            <input type="button" class="btn_buscar" id="buscar" onclick="buscarAfiliado();" />
	            <input type="button" id="nuevo" value="nuevo" onclick="nuevoAfiliado();" style="display: none;"/>
	    	</div>	
	    	
	        <div class="linea_supervisor margen4"> 
	            <div class="error tam_9" id="errorApellidoPaterno"><form:errors path="agenda.afiliado.apellidoPaterno" /></div>
	            <div class="error margen5 tam_5" id="errorApellidoMaterno"><form:errors path="agenda.afiliado.apellidoMaterno" /></div>
	            <div class="error tam_9" id="errorNombre"><form:errors path="agenda.afiliado.nombre" /></div>
	        </div>
	    		
	    	<div id="datosExtras" style="display: none;" >
		        <div class="linea_supervisor margen4">
		            <form:input path="fechaDeNacimiento" cssClass="campo_supervisor gris tam_4 datepicker" placeholder="Fecha de Nac" readonly="true" />
		            <form:input path="agenda.afiliado.telefono1" id="telefono1" cssClass="campo_supervisor tam_12" placeholder="Teléfono" maxlength="10"/>
		            <form:input path="agenda.afiliado.mail" id="mail" cssClass="campo_supervisor tam_11" placeholder="Correo Electrónico" maxlength="50"/>
		            <form:input path="ordenServicio" cssClass="campo_supervisor tam_5" cssStyle="margin-right:0px !important;" placeholder="No. Orden de Servicio"/>
		          	<input id="buscarOrden" class="btn_solo buscar_icono" onclick="verificarOrdenServicio()" />
		    	</div> 
		    	
		    	
				<div class="linea_supervisor margen4"> 
					<div class="error tam_4"><form:errors path="fechaDeNacimiento" /></div>
		            <div class="error tam_12"><form:errors path="agenda.afiliado.telefono1" /></div>
		            <div class="error margen5 tam_11"><form:errors path="agenda.afiliado.mail" /></div>
		            <div class="error margen5 tam_5"></div>
				</div>
			</div>
		
			<div class="tabla margen4" id="divAfiliados" style="display: none;">
				<div class="tabla">   
					<div class="titulo_tabla alto">
						<div class="celda fondo_v texto_supervisor blanco tam_10">Paciente</div>
	            		<div class="celda fondo_v texto_supervisor blanco tam_4">Fecha de Nac</div>
	            		<div class="celda fondo_v texto_supervisor blanco tam_3">RFC</div>
					</div> 
				</div>
			
				<div class="tabla_contenedor desplazar tam_14">
					<div class="tabla tam_14" id="divAfiliadosBody">  
					</div>	 
				</div> 
			</div>
			
	        <div class="linea_supervisor margen4" id="divPrestaciones" style="display: none;">
	            <input class="campo_supervisor tam_11 mayusculas" placeholder="Descripción" id="prestacion" />
	            <input type="hidden" id="idPrestacion">
	            <form:input path="cantidad" cssClass="campo_supervisor tam_8" placeholder="Cant" maxlength="2"/>
	            <form:input path="medico" cssClass="campo_supervisor tam_5" cssStyle="margin-right:0px !important; display:none;" placeholder="Médico" readonly="true"/>
	            <input class="btn_solo buscar_icono" onclick="seleccionarMedico()" id="botonSeleccionarMedico" style="display: none;"/>
	           
	            <form:input path="idTiempo" class="campo_supervisor tam_8 margen5" readonly="true" cssStyle="display:none;"/>
	          	<input class="btn_solo agregar_icono" id="agregar" onclick="agregarPrestacion()" disabled />
	          
	    	</div>
	    	
			<div class="tabla2 margen4 margen_arriba" id="tablaPrincipalPrestaciones" style="display: none;">
      
				<div class="tabla2">  
					<div class="titulo_tabla alto">
	        			<div class="celda2 fondo_v texto_supervisor blanco tam_19">Código-Descripción</div>
	            		<div class="celda2 fondo_v texto_supervisor blanco tam_6 centrado">Cant</div>
						<div class="celda2 fondo_v texto_supervisor blanco tam_6 centrado">Valor</div>
	            		<div class="celda2 fondo_v texto_supervisor blanco tam_6 centrado">Aporte</div>
	            		<div class="celda2 fondo_v texto_supervisor blanco tam_8 centrado">Copago</div>
						<div class="celda2 fondo_v texto_supervisor bote blanco tam_1 centrado"></div>
	    			</div>
	      		</div>
	      
				<div class="tabla_contenedor desplazar tam_14 centrado">
					<div class="tabla2 tam_14" id="tablaPrestaciones"> 
						<c:forEach items="${citasPresencialesForm.autocompleteVos}" var="autocompleteVo" varStatus="varStatus" > 
							<div class="linea_tabla" id="divAutocompleteVos${autocompleteVo.data}">
								<input type="hidden" id="autocompleteVos${autocompleteVo.data}" value="x"/>
								<input type="hidden" name="autocompleteVos[${varStatus.index}].value" value="autocompleteVo.value"/>
								<input type="hidden" name="autocompleteVos[${varStatus.index}].data" value="autocompleteVo.data" />
								<div class="celda fondo_g  blanco tam_2"><input class="sin_borde_bg texto_supervisor blanco tam_13" value="${autocompleteVo.value}" /></div>
								<div class="celda fondo_g  blanco tam_1"><input class="sin_borde_bg texto_supervisor blanco tam_13" /></div>
		            			<div class="celda fondo_g  blanco tam_1"><input class="sin_borde_bg texto_supervisor blanco tam_13" /></div>
		            			<div class="celda fondo_g  blanco tam_1"><input class="sin_borde_bg texto_supervisor blanco tam_13" /></div>
		            			<div class="celda fondo_g  blanco tam_1"><input class="sin_borde_bg texto_supervisor blanco tam_13" /></div>
		            			<div class="celda fondo_g bote tam_15 link3" onclick="borrarPrestacion(${autocompleteVo.data})"></div>
		    				</div>
		    			</c:forEach>
					</div>
	     		</div>
			</div> 			 	
					
			<div id="dialog-form" title="Acompañantes o Personas de Confianza Registradas"> 
			</div>
			
			<div id="dialog-form-medicos" title="Seleccion de medicos"> 
			</div>
			
			<div id="divMedico" style="display: none;">
				<table id="tablaMedicos">
				</table>
				<input type="button" value="Guardar" onclick="enviarForma();"/>
			</div>
			
			<div class="linea_supervisor margen4">
				<div class="btn btn_cerrar" onclick="cerrar();"></div>
				<div class="btn btn_certificar" id="botonCertificar" style="display: none;" onclick="certificar();"></div>
				<div class="btn_grande btn_nolista" id="botonPacienteNoEnLista" style="display: none;" onclick="pacienteNoEnLista();"></div>
				<div class="btn btn_valorizar" id="botonValorizar" style="display: none;" onclick="valorizar();"></div>
				<div class="btn btn_guardar" id="botonGuardar" style="display: none;" onclick="guardar();"></div>
			</div>
		</div>				
	</form:form>

</div>
</div>
	
	<script type="text/javascript">
	function nuevoAfiliado(){
		var citasPresencialesForm = $("#citasPresencialesForm").serialize();
	    $.getJSON("nuevoAfiliado", citasPresencialesForm ,function(response){
	    	if(response.errores.length == 0){
	    		vigenteSinAsegurador(response.idAfiliado);
	    	}else{
    			$("#errores").empty();
    			$.each(response.errores, function(index, item) {
    				$("#errores").append(item+"</br>"); 
                });	
    		}
	    });
	}
	
	function pacienteNoEnLista(){
		$.fancybox.open({
			content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Paciente no registrado en el Asegurador, requiere Autorización especial del Asegurador</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
			openEffect : 'elastic',
			openSpeed  : 150,
			closeEffect : 'elastic',
			closeSpeed  : 150,								
			autoSize: false,
			width: 470,
			height: 185,
			afterClose : function(){
				$("#divAfiliados").hide();
				$("#botonCertificar").hide();
				$("#botonPacienteNoEnLista").hide();
			}
		});
	}
	
	function enviarForma(){
		$("#citasPresencialesForm").submit();
	}
	
	function seleccionarMedico(){
		abrirDialogMedico();
	} 
	
	function prestadores(){
		var idAsegurador = $("#idAsegurador").val();
	    $.getJSON("prestadores", {id:idAsegurador} ,function(response){ 
	        $("#idPrestador option").remove(); 
	            var options = '<option value="-1">Prestador</option>';
	            var optionsConvenios = '<option value="-1">Convenio</option>';
	            var i = 0;
	            var idPrestador;
	            $("#idPrestador").html(options);
	            $("#idConvenio").html(optionsConvenios); 
	            $.each(response, function(index, item) {
	                options += '<option value="' + item.prestadorId + '">' + item.nombreRazonSocial + '</option>';
	                $("#idPrestador").html(options);
	                idPrestador = item.prestadorId;
	                i++;
	            });
	            
	            if (i==1) {
	            	$("#idPrestador").val(idPrestador);
	            	convenios();
				}
	    });
	    
	    
	    if(idAsegurador==18){
	    	$("#derechohabienteDiv").hide();
	    	$("#sexoAfiliadoDiv").show();
	    	$("#nuevo").show();
	    }else{
	    	$("#sexoAfiliadoDiv").hide();
	    	$("#derechohabienteDiv").show();
	    	$("#nuevo").hide();
	    }
	}
	
	function convenios(){
		var idAsegurador = $("#idAsegurador").val();
		var idPrestador = $("#idPrestador").val();
	    $.getJSON("convenios", {idAsegurador:idAsegurador, idPrestador:idPrestador} ,function(response){ 
	        $("#idConvenio option").remove(); 
	            var options = '<option value="-1">Convenio</option>';
	            $("#idConvenio").html(options);
	            var i = 0;
	            var idConvenio;
	            $.each(response, function(index, item) {
	                options += '<option value="' + item.convenioId + '">' + item.convenio + '</option>';
	                $("#idConvenio").html(options); 
	                idConvenio = item.convenioId;
	                i++;
	            });
	            
	            if (i==1) {
	            	$("#idConvenio").val(idConvenio);
				}	            
	    });
	}
	
	function identificadores(){
		var idAsegurador = $("#idAsegurador").val();
	    $.getJSON("identificadores", {idAsegurador:idAsegurador} ,function(response){ 
	        $("#idIdentificador option").remove(); 
	            var options = '<option value="-1">Identificador</option>';
	            $("#idIdentificador").html(options);
	            $.each(response, function(index, item) {
	                options += '<option value="' + item.tipoIdentificadorId + '">' + item.tipoIdentificador + '</option>';
	                $("#idIdentificador").html(options); 
	            });
	    });
	}
	
	function buscarAfiliado(){
		$("#divPrestaciones").hide();
		$("#divMedicos").hide();
		$("#tablaMedicos").empty();
		var citasPresencialesForm = $("#citasPresencialesForm").serialize();
		$("#pre-load").show();
	    $.getJSON("buscar", citasPresencialesForm ,function(response){
	    	$("#tablaAfiliados > tbody").empty(); 
    		var filas = "";
    		if( response.banderaError == false){
    			var indice = 0;    			
    			var idAsegurador = $("#idAsegurador").val();
    			$("#mostrarBotonPacienteNoEnLista").val(response.mostrarBotonPacienteNoEnLista);
    			if (response.mostrarBotonPacienteNoEnLista == 1) {
    				$("#botonPacienteNoEnLista").show();	
				}else{
					$("#botonPacienteNoEnLista").hide();
				}
    			
    			if(idAsegurador=="10"){
    				$.each(response.afiliadoIsssteVos, function(index, item) {
        				indice++;
        			});
        			
                	if(indice==1){
                		$.each(response.afiliadoIsssteVos, function(index, item) {
                			vigenteI(item.numIssste); 
                			$("#divAfiliados").hide();
                        });
                	}else if(indice==0){
        				$.fancybox.open({
        					content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Paciente no registrado en el Asegurador, requiere Autorización especial del Asegurador</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
        					openEffect : 'elastic',
        					openSpeed  : 150,
        					closeEffect : 'elastic',
        					closeSpeed  : 150,								
        	 				autoSize: false,
        	 				width: 470,
        	 				height: 185
        				});	   			
                	}else{
                		$("#divAfiliadosBody").empty();
                		$.each(response.afiliadoIsssteVos, function(index, item) {
                        	filas += '<div class="linea_tabla alto" onclick="vigenteI('+item.numIssste+');"><div class="celda link2 texto_supervisor blanco tam_10">';
                        	filas += item.nombre+' '+item.apellidoPaterno+' '+item.apellidoMaterno+'</div>';
                        	filas += '<div class="celda texto_supervisor blanco tam_4">'+item.fechaNacimiento+'</div>';
                        	filas += '<div class="celda texto_supervisor blanco tam_3"></div></div>';
                            $("#divAfiliadosBody").append(filas); 
                            filas = "";   
                        });	
                		$("#divAfiliados").show();
                	}
    			}else if(idAsegurador=="12"){
    				$.each(response.afiliadoCtoVos, function(index, item) {
        				indice++;
        			});
        			
                	if(indice==1){
                		$.each(response.afiliadoCtoVos, function(index, item) {
                    		vigenteM(item.codEmpresa,item.codAfiliado,item.correlativo);  
                        });	
                	}else{
                		$.each(response.afiliadoCtoVos, function(index, item) {
                        	filas += '<div class="linea_tabla alto" onclick="vigenteM('+item.codEmpresa+',\''+item.codAfiliado+'\','+item.correlativo+'); false;"><div class="celda link2 texto_supervisor blanco tam_10">';
                        	filas += item.nombre+' '+item.apellidoPaterno+' '+item.apellidoMaterno+'</div>';
                        	filas += '<div class="celda texto_supervisor blanco tam_4">'+item.fecha+'</div>';
                        	filas += '<div class="celda texto_supervisor blanco tam_3"></div></div>';
                            $("#divAfiliadosBody").append(filas); 
                            filas = "";                              
                        });
                		$("#divAfiliados").show();
                	}
    			}	
/*    			}else if(idAsegurador=="18"){
    				$.each(response.afiliadoSinAseguradorVos, function(index, item) {
        				indice++;
        			});
        			
                	if(indice==1){
                		alert("vigente sin Asegurador");
                		$.each(response.afiliadoSinAseguradorVos, function(index, item) {
                    		alert(item.idAfiliado);
                    		vigenteSinAsegurador(item.idAfiliado);
                        });	
                	}else{
                		$.each(response.afiliadoSinAseguradorVos, function(index, item) {
                        	filas += '<tr><td><a href="#" onclick="vigenteSinAsegurador('+item.idAfiliado+'); false;">'+item.nombre+' '+item.apellidoPaterno+' '+item.apellidoMaterno+'</a></td>';
                        	filas += '<td>'+item.fecha+'</td><td></td><td></td></tr>';
                        	alert(filas);
                            $("#tablaAfiliados tbody").append(filas); 
                            filas = "";  
                        });
                		$("#divAfiliados").show();
                	}
        			
    			}	 */
    		}else{	
				$.fancybox.open({
					content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>"+response.error+"</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
					openEffect : 'elastic',
					openSpeed  : 150,
					closeEffect : 'elastic',
					closeSpeed  : 150,								
	 				autoSize: false,
	 				width: 470,
	 				height: 185
				});	   			
    		}
	    }).complete(function(){$("#pre-load").hide();});
	}
	
	function vigenteSinAsegurador(idAfiliado){
		alert("Sin asegurador");
		$("#idAfiliadoSinAsegurador").val(idAfiliado);
		var citasPresencialesForm = $("#citasPresencialesForm").serialize();
	    $.getJSON("vigente", citasPresencialesForm ,function(response){ 
    		if( response.errores.length == 0){
    			$("#errores").empty();
    			$("#idIdentificador").val(response.idIdentificador);
    			$("#tipoIdentificadorId").val(response.idIdentificador);
    			$("#tipoIdValor").val(response.afiliadotipoidentificador.tipoIdValor);
    			$("#tipoAfiliado").val(response.tipoAfiliado);
    			$("#fechaDeNacimiento").val(response.fechaDeNacimiento);
    			$("#nombre").val(response.agenda.afiliado.nombre);
    			$("#apellidoPaterno").val(response.agenda.afiliado.apellidoPaterno);
    			$("#apellidoMaterno").val(response.agenda.afiliado.apellidoMaterno);
    			$("#telefono1").val(response.agenda.afiliado.telefono1);
    			$("#mail").val(response.agenda.afiliado.mail);
    			$("#idAfiliado").val(response.idAfiliado);
    			$("input[value='"+response.tipoAfiliado+"']").prop('checked',true);
    			$("#divAfiliados").hide();
    			$("#divPrestaciones").show();
    			var r = confirm("Asiste el paciente");
    			if (r == true) {
     				if (IniciarUsuario(0, response.idAfiliado, 0, response.idAfiliado, "AfiliadoId", $("#nombre").val(), $("#apellidoPaterno").val(), $("#apellidoMaterno").val(), response.sexo, $("#fechaDeNacimiento").val(),response.estado)) {
     					inicializaAutocomplete();
     				}else{
     					alert("no paso la autenticacion de huella");	
     				}
    			} else {
    				abrirDialog(response.idAfiliado);
    			}    			
    		}else{
    			$("#errores").empty();
    			$.each(response.errores, function(index, item) {
    				$("#errores").append(item+"</br>"); 
                });	
    		}
	    });
	}
	
	function vigenteM(codEmpresa,codAfiliado,correlativo){
		$("#codEmpresa").val(codEmpresa);
		$("#codAfiliado").val(codAfiliado);
		$("#correlativo").val(correlativo);
		var citasPresencialesForm = $("#citasPresencialesForm").serialize();
	    $.getJSON("vigente", citasPresencialesForm ,function(response){ 
	    	if( response.errores.length == 0){
	   			$("#errores").empty();
	   			$("#idIdentificador").val(response.idIdentificador);
	   			$("#tipoIdentificadorId").val(response.idIdentificador);
	   			$("#tipoIdValor").val(response.afiliadotipoidentificador.tipoIdValor);
	   			$("#tipoAfiliado").val(response.tipoAfiliado);
	   			$("#fechaDeNacimiento").val(response.fechaDeNacimiento);
	   			$("#nombre").val(response.agenda.afiliado.nombre);
	   			$("#apellidoPaterno").val(response.agenda.afiliado.apellidoPaterno);
	   			$("#apellidoMaterno").val(response.agenda.afiliado.apellidoMaterno);
	   			$("#telefono1").val(response.agenda.afiliado.telefono1);
	   			$("#mail").val(response.agenda.afiliado.mail);
	  			$("#idAfiliado").val(response.idAfiliado);
	  			$("#menorDeEdad").val(response.menorDeEdad);
	  			$("#idSexo").val(response.sexo);
	  			$("#idEstado").val(response.estado);
	  			$("input[value='"+response.tipoAfiliado+"']").prop('checked',true);
	  			$("#vigencia").val(response.vigencia);
	  			$("#codProducto").val(response.codProducto);
	  			$("#botonCertificar").show();
	  			$("#divAfiliados").hide();	    		
	    	}else{
    			$.each(response.errores, function(index, item) { 				
    				$.fancybox.open({
    					content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>"+item+"</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
    					openEffect : 'elastic',
    					openSpeed  : 150,
    					closeEffect : 'elastic',
    					closeSpeed  : 150,								
    	 				autoSize: false,
    	 				width: 470,
    	 				height: 185
    				});    				
                });
	 
	    	}

   	    }).complete(function(){$("#pre-load").hide();});
	}
	
	function vigenteI(numIssste){
		$("#numIssste").val(numIssste);
		var citasPresencialesForm = $("#citasPresencialesForm").serialize();
		$("#pre-load").show();
	    $.getJSON("vigente", citasPresencialesForm ,function(response){ 
			$("#errores").empty();
   			$("#idIdentificador").val(response.idIdentificador);
   			$("#tipoIdentificadorId").val(response.idIdentificador);
   			$("#tipoIdValor").val(response.afiliadotipoidentificador.tipoIdValor);
   			$("#tipoAfiliado").val(response.tipoAfiliado);
   			$("#fechaDeNacimiento").val(response.fechaDeNacimiento);
   			$("#nombre").val(response.agenda.afiliado.nombre);
   			$("#apellidoPaterno").val(response.agenda.afiliado.apellidoPaterno);
   			$("#apellidoMaterno").val(response.agenda.afiliado.apellidoMaterno);
   			$("#telefono1").val(response.agenda.afiliado.telefono1);
   			$("#mail").val(response.agenda.afiliado.mail);
   			$("#idAfiliado").val(response.idAfiliado);
   			$("#menorDeEdad").val(response.menorDeEdad);
   			$("#idSexo").val(response.sexo);
   			$("#idEstado").val(response.estado);
   			$("input[value='"+response.tipoAfiliado+"']").prop('checked',true);
   			$("#vigencia").val(response.vigencia);
   			$("#botonCertificar").show();
   			$("#divAfiliados").hide();
	    }).complete(function(){$("#pre-load").hide();});
	}
	
	function certificar(){
		var r = $("#personaConfianza").is(":checked");
		var menorDeEdad= $("#menorDeEdad").val();
		var idAfiliado= $("#idAfiliado").val();
		if (r == true) {
			if (menorDeEdad != "true") {
				$("#divAfiliados").hide();
				if (IniciarUsuario(0, $("#idAfiliado").val(), 0,  $("#idAfiliado").val(), "AfiliadoId", $("#nombre").val(), $("#apellidoPaterno").val(), $("#apellidoMaterno").val(),  $("#idSexo").val(), $("#fechaDeNacimiento").val(),  $("#idEstado").val())) {
					$("#autentia").val(true);
					$("#buscar").hide();
					deshabilitar();
				}else{
					$("#autentia").val(false);
					$("#buscar").hide();
					deshabilitar();
				}
				verificarAgenda();
				$("#divPrestaciones").show();
				$("#datosExtras").show();
				$("#botonCertificar").hide();
				$("#botonPacienteNoEnLista").hide();
				

			} else {
				abrirDialog(idAfiliado);
       			$("#divAfiliados").hide();	
   			}
		}else{
			abrirDialog(idAfiliado);
   			$("#divAfiliados").hide();
		}
	}
	
	function valorizar(){
		var rows = $("#tablaPrestaciones .linea_tabla").length;
		if(rows!=0){
			var citasPresencialesForm = $("#citasPresencialesForm").serialize();
//			var idAtencion = $("#idAtencion").val();
//			alert(idAtencion);
//			if (idAtencion==0) {
				$("#pre-load").show();
			    $.getJSON("valorizar", citasPresencialesForm ,function(response){
//			    	$("#idAtencion").val(response.idAtencion);
			    	var autocompleteVos = response.autocompleteVos;
			    	for (var i = 0; i < autocompleteVos.length; i++) {
			    		 $("#valor"+autocompleteVos[i].value).append(autocompleteVos[i].valor);
			    		 $("#aporte"+autocompleteVos[i].value).append(autocompleteVos[i].aporte);
			    		 $("#copago"+autocompleteVos[i].value).append(autocompleteVos[i].copago);
			    		 $("#valorHidden"+autocompleteVos[i].value).val(autocompleteVos[i].valor);
			    		 $("#aporteHidden"+autocompleteVos[i].value).val(autocompleteVos[i].aporte);
			    		 $("#copagoHidden"+autocompleteVos[i].value).val(autocompleteVos[i].copago);
					}
			    	$("#banderaGuardar").val(true);
//			    	$("#folio").val(response.folio);
//			    	if($("#botonGuardar").is(":visible")){
//						$.fancybox.open({
//							content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Paciente no vigente  y/o Paciente no identificado correctamente. El supervisor debe autorizar la atención</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
//							openEffect : 'elastic',
//							openSpeed  : 150,
//							closeEffect : 'elastic',
//							closeSpeed  : 150,								
//			 				autoSize: false,
//			 				width: 470,
//			 				height: 185
//						});			    		
//			    	}
			    }).complete(function(){$("#pre-load").hide();});	
//			}else{
//			    $.getJSON("revalorizar", citasPresencialesForm ,function(response){
//			    	var autocompleteVos = response.autocompleteVos;
//			    	for (var i = 0; i < autocompleteVos.length; i++) {
//			    		 $("#valor"+autocompleteVos[i].value).append(autocompleteVos[i].valor);
//			    		 $("#aporte"+autocompleteVos[i].value).append(autocompleteVos[i].aporte);
//			    		 $("#copago"+autocompleteVos[i].value).append(autocompleteVos[i].copago);
//			    		 $("#valorHidden"+autocompleteVos[i].value).val(autocompleteVos[i].valor);
//			    		 $("#aporteHidden"+autocompleteVos[i].value).val(autocompleteVos[i].aporte);
//			    		 $("#copagoHidden"+autocompleteVos[i].value).val(autocompleteVos[i].copago);
//					}
//			    	if($("#botonGuardar").is(":visible")){
//						$.fancybox.open({
//							content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Paciente no vigente  y/o Paciente no identificado correctamente. El supervisor debe autorizar la atención</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
//							openEffect : 'elastic',
//							openSpeed  : 150,
//							closeEffect : 'elastic',
//							closeSpeed  : 150,								
//			 				autoSize: false,
//			 				width: 470,
//			 				height: 185
//						});			    		
//			    	}
//			    });
//			}
		}else{
			$.fancybox.open({
				content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Debe de agregar una prestacion</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
				openEffect : 'elastic',
				openSpeed  : 150,
				closeEffect : 'elastic',
				closeSpeed  : 150,								
 				autoSize: false,
 				width: 470,
 				height: 185
			});	 
		}
	}
	
	function guardar(){
		var rows = $("#tablaPrestaciones .linea_tabla").length;
		if(rows!=0){
			var verificaTelefono = false;
			var verificaCorreo = false;
			var telefono = $("#telefono1").val();
			var mail = $("#mail").val();
			if(telefono!=""){
				if (telefono.length!=10 || isNaN(telefono)){
					verificaTelefono = false;
					$.fancybox.open({
						content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Verifique el Telefono</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
						openEffect : 'elastic',
						openSpeed  : 150,
						closeEffect : 'elastic',
						closeSpeed  : 150,								
		 				autoSize: false,
		 				width: 470,
		 				height: 185
					});
				}else{
					verificaTelefono = true;	
				} 
			}else{
				verificaTelefono = true;
			}
			
			if(mail!=""){
				if (!validateEmail(mail)){
					verificaCorreo = false;
					$.fancybox.open({
						content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Verifique el Correo Electronico</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
						openEffect : 'elastic',
						openSpeed  : 150,
						closeEffect : 'elastic',
						closeSpeed  : 150,								
		 				autoSize: false,
		 				width: 470,
		 				height: 185
					});
				}else{
					verificaCorreo = true;	
				} 
			}else{
				verificaCorreo = true;
			}			
			
			if (verificaTelefono && verificaCorreo) {
				var citasPresencialesForm = $("#citasPresencialesForm").serialize();
				var banderaGuardar = $("#banderaGuardar").val();
		    	var vigencia = $("#vigencia").val();
				var autentia = $("#autentia").val();
				if(banderaGuardar == "true"){
					$("#pre-load").show();
				    $.getJSON("guardar", citasPresencialesForm ,function(response){
				    	if(response.error != undefined && response.error != ""){
							$.fancybox.open({
								content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>"+response.error+"</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
								openEffect : 'elastic',
								openSpeed  : 150,
								closeEffect : 'elastic',
								closeSpeed  : 150,								
				 				autoSize: false,
				 				width: 480,
				 				height: 200
							});				    		
				    	}else{
				    		$("#folio").val(response.folio);

							if(vigencia == "true" && autentia == "true"){
								mensaje = "Atencion Medica guardada con el folio: "+response.folio;
							}else if(autentia == "false"){
								mensaje = "Atencion Medica guardada con el folio: "+response.folio+" Verificación de Identidad no se pudo realizar, requiere Autorización especial del Supervisor del Lugar de Atención";
							}else if (vigencia == "false"){
								mensaje = "Atencion Medica guardada con el folio: "+response.folio+" Verificación de Vigencia no se pudo realizar, requiere Autorización especial del Supervisor del Lugar de Atención";
							}else{
								mensaje = "Atencion Medica guardada con el folio: "+response.folio+" Verificación de Identidad y Vigencia no se pudo realizar, requiere Autorización especial del Supervisor del Lugar de Atención";
							}
							
							$.fancybox.open({
								content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario1\"></div><h1>"+mensaje+"</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
								openEffect : 'elastic',
								openSpeed  : 150,
								closeEffect : 'elastic',
								closeSpeed  : 150,								
				 				autoSize: false,
				 				width: 480,
				 				height: 200,
				 				afterClose : function(){
				 					var ctx = "${pageContext.request.contextPath}";
				 					window.location.href = ctx+"/menu";
				 				}
							});	
				    	}
				    }).complete(function(){$("#pre-load").hide();});	
				}else{
					$.fancybox.open({
						content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Debe de valorizar antes de guardar</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
						openEffect : 'elastic',
						openSpeed  : 150,
						closeEffect : 'elastic',
						closeSpeed  : 150,								
		 				autoSize: false,
		 				width: 480,
		 				height: 200
					});	
				}
			}
		}else{
			$.fancybox.open({
				content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Debe de agregar una prestacion</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
				openEffect : 'elastic',
				openSpeed  : 150,
				closeEffect : 'elastic',
				closeSpeed  : 150,								
 				autoSize: false,
 				width: 470,
 				height: 185
			});	 
		}
	}	
	
	function abrirDialog(idAfiliado){
		var ctx = "${pageContext.request.contextPath}";
		$.fancybox.open({
			href : ctx + '/recepcion/inicioPersonaConfianza?idAfiliado='+idAfiliado,
			type : 'iframe',
			padding : 5,
			autoSize: false,
			width: 860,
			height: 465,
			afterClose : function(){
				var idPersona = $("#idPersona").val();
				if (idPersona!=0) {
					$("#autentia").val(true);
					verificarAgenda();
					$("#divPrestaciones").show();
					$("#datosExtras").show();
					$("#botonCertificar").hide();
					$("#botonPacienteNoEnLista").hide();
					
					$("#buscar").hide();
					deshabilitar();
				}else{
					setTimeout(noSeleccionoPersona, 50);
				}
			}
		});
	}
	
	function noSeleccionoPersona(){
		$.fancybox.open({
			content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Debe de seleccionar una persona de confianza para continuar</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
			openEffect : 'elastic',
			openSpeed  : 150,
			closeEffect : 'elastic',
			closeSpeed  : 150,								
			autoSize: false,
			width: 470,
			height: 185
		});	 
	}
	
	function abrirDialogMedico(){
		var ctx = "${pageContext.request.contextPath}";
		$.fancybox.open({
			href : ctx + '/recepcion/seleccionarMedico',
			type : 'iframe',
			padding : 5,
			autoSize: false,
			width: 860,
			height: 440,
			afterClose : function(){
				var idPersona = $("#idPersona").val();
			}
		});		
	}
		
	function verificarAgenda(){
		var citasPresencialesForm = $("#citasPresencialesForm").serialize();
	    $.getJSON("verificaAgenda", citasPresencialesForm ,function(response){
	    	if(response.idAgenda!=0){
	    		$("#idMedico").val(response.idMedico);
	    		$("#idTiempo").val(response.idTiempo);
	    		$("#idPrestacion").val(response.autocompleteVo.value);
	    		$("#prestacion").val(response.autocompleteVo.label);
	    		$("#cantidad").val(response.autocompleteVo.cantidad);
	    		$("#idAgenda").val(response.idAgenda);
	    		agregarPrestacion();
	    		inicializaAutocomplete();
	    	}else{
	    		inicializaAutocomplete();
	    	}
	    });
		var rows = $("#tablaPrestaciones .linea_tabla").length;
		if(rows!=0){
			$("#tablaPrincipalPrestaciones").show();	
		}
	}
	
	function verificarOrdenServicio(){
		var citasPresencialesForm = $("#citasPresencialesForm").serialize();
		var ordenServicio = $("#ordenServicio").val();
	    $.getJSON("verificaOrdenServicio", citasPresencialesForm ,function(response){
	    	if(response.prestacionesPorTomarVos.length != 0){
	    		var ctx = "${pageContext.request.contextPath}";
	    		$.fancybox.open({
	    			href : ctx + '/recepcion/ordenServicio?ordenServicio='+ordenServicio,
	    			type : 'iframe',
	    			padding : 5,
	    			autoSize: false,
	    			width: 860,
	    			height: 465,
	    			afterClose : function(){
	    				var prestacionesPorOrdenServicio = $("#prestacionesPorOrdenServicio").val();
	    				if (prestacionesPorOrdenServicio.length!=0) {
	    					var prestaciones = prestacionesPorOrdenServicio.split(",");
	    					for (var i = 0; i < prestaciones.length; i++) {
								var prestacion = prestaciones[i].split("|");
					    		$("#idPrestacion").val(prestacion[0]);
					    		$("#prestacion").val(prestacion[1]);
					    		$("#cantidad").val(prestacion[2]);
					    		$("#seleccionPorOrdenServicio").val("X");
					    		agregarPrestacion();		
							}
						}
	 				}
	    		});
	    	}else{
				$.fancybox.open({
					content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>No se encontro la Orden de Servicio*</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
					openEffect : 'elastic',
					openSpeed  : 150,
					closeEffect : 'elastic',
					closeSpeed  : 150,								
	 				autoSize: false,
	 				width: 470,
	 				height: 185
				});	 	    		
	    	}
	    });
	}	
	
	function inicializaAutocomplete(){
 		$("#prestacion").autocomplete({
			source: "prestacionSAB?idConvenio="+$("#idConvenio").val(),
	 	   	minLength: 1,
            select: function( event, ui ){
            	if (ui.item.value!="-1") {
                	var label = ui.item.label;
           	    	$("#prestacion").val(ui.item.label);
           	    	$("#idPrestacion").val(ui.item.value);
           	    	$("#cantidad").val(1);
           	    	var vigencia = $("#vigencia").val();
           	    	var autentia = $("#autentia").val();
           	    	if (label.indexOf("CONSULTA") != -1 && vigencia == "true" && autentia == "true") {
           	    		var medicoPrestaciones = $("#medicoPrestaciones").val();
        				if(medicoPrestaciones == undefined){
               	    		$("#medico").show();
               	    		$("#botonSeleccionarMedico").show();
               	    		$("#idTiempo").show();    		
               	    		$("#medico").val("");
               	    		$("#idTiempo").val("");
        				}
    				}else{
    					$("#medico").hide();
    					$("#botonSeleccionarMedico").hide();
    					$("#idTiempo").hide();
    				}
           	    	$("#agregar").prop( "disabled", false );
				}else{
           	    	$("#prestacion").val("");
           	    	$("#idPrestacion").val("");
				}
                return false;
			},
			focus: function (event, ui) {
				if (ui.item.value!="-1") {
					$("#prestacion").val(ui.item.label);
				}else{
           	    	$("#prestacion").val("");
           	    	$("#idPrestacion").val("");
				}
				return false;
            }

		});  		
	}
	
	function agregarPrestacion(){
		var idPrestacion = $("#idPrestacion").val();
		var prestacion = $("#prestacion").val();
		var cantidad = $("#cantidad").val();
		var agregar = false;
		if (idPrestacion != "") {
			if(cantidad != ""){
				if(prestacion.indexOf("CONSULTA") != -1 && $("#tablaPrestaciones .linea_tabla").length>0){
					$.fancybox.open({
						content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>No se puede agregar la prestación</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
						openEffect : 'elastic',
						openSpeed  : 150,
						closeEffect : 'elastic',
						closeSpeed  : 150,								
		 				autoSize: false,
		 				width: 470,
		 				height: 185
					});	
				}else{
					agregar = true;
				}	
				if(agregar){
					if($("#autocompleteVos"+idPrestacion).val() === undefined){
						var rows = $("#tablaPrestaciones .linea_tabla").length;
						if(rows!=0){
							var myElem = document.getElementById(rows);
							while(myElem!=null){
								rows++;
								myElem = document.getElementById(rows);
							}	
						}
						
						if(prestacion.indexOf("CONSULTA") != -1 && $("#tablaPrestaciones .linea_tabla").length>0){
							$.fancybox.open({
								content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>No se puede agregar la prestación</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
								openEffect : 'elastic',
								openSpeed  : 150,
								closeEffect : 'elastic',
								closeSpeed  : 150,								
				 				autoSize: false,
				 				width: 470,
				 				height: 185
							});	
						}else{
							agregar = true;
						}					
					
						if(agregar){
							var filas = '<div class="linea_tabla alto" id="'+rows+'">'+
					    				'<input type="hidden" id="autocompleteVos'+idPrestacion+'" value="x"/>'+
					    				'<input type="hidden" name="autocompleteVos['+rows+'].label" id="label'+rows+'" value="'+prestacion+'"/>'+
					    				'<input type="hidden" name="autocompleteVos['+rows+'].value" value="'+idPrestacion+'"/>'+
					    				'<input type="hidden" name="autocompleteVos['+rows+'].valor" id="valorHidden'+idPrestacion+'"/>'+
					    				'<input type="hidden" name="autocompleteVos['+rows+'].aporte" id="aporteHidden'+idPrestacion+'"/>'+
					    				'<input type="hidden" name="autocompleteVos['+rows+'].copago" id="copagoHidden'+idPrestacion+'"/>'+
					    				'<input type="hidden" name="autocompleteVos['+rows+'].cantidad" value="'+cantidad+'"/>';
					    				
					    	var idTiempo = $("#idTiempo").val();
					    	
					    	if (idTiempo != "") {
					    		filas = filas + '<input type="hidden" id="medicoPrestaciones" value="x"/>';
							}
					    	
					    	var seleccionPorOrdenServicio = $("#seleccionPorOrdenServicio").val();
					    	
					    	if (seleccionPorOrdenServicio == "X") {
					    		var ordenServicio = $("#ordenServicio").val();
					    		filas = filas + '<input type="hidden" name="autocompleteVos['+rows+'].ordenServicio" value="'+ordenServicio+'"/>';
							}
					    				
							filas = filas + '<div class="celda2 texto_supervisor blanco tam_19">'+prestacion+'</div>'+			
					    			'<div class="celda2 texto_supervisor blanco tam_6 centrado">'+cantidad+'</div>'+
					        		'<div class="celda2 texto_supervisor blanco tam_6 centrado" id="valor'+idPrestacion+'"></div>'+
					        		'<div class="celda2 texto_supervisor blanco tam_6" id="aporte'+idPrestacion+'"></div>'+
					        		'<div class="celda2 texto_supervisor blanco tam_8 centrado" id="copago'+idPrestacion+'"></div>'+
					        		'<div class="celda2 texto_supervisor blanco tam_1 centrado bote link3" onclick="borrarPrestacion('+rows+')"></div></div>';  				
		
					        deshabilitarAgregarPrestacion(prestacion);
							$("#tablaPrestaciones").append(filas);
							$("#prestacion").val("");
							$("#cantidad").val("");
							$("#tablaPrincipalPrestaciones").show();
							$("#medico").hide();
							$("#botonSeleccionarMedico").hide();
							$("#idTiempo").hide();
							var vigencia = $("#vigencia").val();
							var autentia = $("#autentia").val();
							
							if(vigencia == "true" && autentia == "true"){
								$("#botonValorizar").show();
								$("#botonGuardar").show();
								$("#banderaGuardar").val(false);
							}else{
								$("#botonGuardar").show();
								$("#banderaGuardar").val(true);
							}
						}
					}else{
							$.fancybox.open({
								content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Ya existe la prestacion</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
								openEffect : 'elastic',
								openSpeed  : 150,
								closeEffect : 'elastic',
								closeSpeed  : 150,								
				 				autoSize: false,
				 				width: 470,
				 				height: 185
							});	 
							$("#agregar").prop( "disabled", true );
							$("#prestacion").val("");
							$("#cantidad").val("");					
						}
					}
				}else{
					$.fancybox.open({
						content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Debe de agregar la Cantidad</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
						openEffect : 'elastic',
						openSpeed  : 150,
						closeEffect : 'elastic',
						closeSpeed  : 150,								
		 				autoSize: false,
		 				width: 470,
		 				height: 185
					});
				}
			}else{
				$.fancybox.open({
					content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Debe de agregar la Prestacion</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
					openEffect : 'elastic',
					openSpeed  : 150,
					closeEffect : 'elastic',
					closeSpeed  : 150,								
	 				autoSize: false,
	 				width: 470,
	 				height: 185
				});
			}
			$("#seleccionPorOrdenServicio").val("");

	}

	function habilitarAgregarPrestacion(id){
		var labelPrestacion = $("#label"+id).val(); 
		if (labelPrestacion .indexOf("CONSULTA") != -1) {
			$("#prestacion").attr('disabled',false);
			$("#agregar").show();
			$("#ordenServicio").show();
			$("#buscarOrden").show();
		}
	}
	
	function deshabilitarAgregarPrestacion(prestacion){
		if (prestacion.indexOf("CONSULTA") != -1) {
			$("#prestacion").attr('disabled',true);
			$("#agregar").hide();
			$("#ordenServicio").hide();
			$("#buscarOrden").hide();
		}
	}
	
	function borrarPrestacion(id){
		habilitarAgregarPrestacion(id);
		$("#tablaPrestaciones div#"+id).remove();
		var rows = $("#tablaPrestaciones .linea_tabla").length;
		if(rows==0){
			$("#tablaPrincipalPrestaciones").hide();			
			$("#botonGuardar").hide();
			$("#botonValorizar").hide();
		}
	}	
	
	function cerrar(){
		var ctx = "${pageContext.request.contextPath}";
		window.location.href = ctx+"/menu";
	}
	
	$(document).ready(function(){
		
		$.ajaxSetup({ cache: false });
		
		$("#tipoIdValor").alphanum({
	        allowSpace: true,
	        disallow: '´çÇ¿¨¡·'
	    });
		
		$("#apellidoPaterno").alphanum({
	        allowSpace: true,
	        disallow: '´çÇ¿¨¡·'
	    });
		
		$("#apellidoMaterno").alphanum({
	        allowSpace: true,
	        disallow: '´çÇ¿¨¡·'
	    });
		
		$("#nombre").alphanum({
	        allowSpace: true,
	        disallow: '´çÇ¿¨¡·'
	    });
		
		$("#telefono1").alphanum({
	        allowSpace: true,
	        disallow: '´çÇ¿¨¡·'
	    });
		
		$("#mail").alphanum({
	        allowSpace: true,
	        allow: '_@-.',
	        disallow: '´çÇ¿¨¡·'
	    });
		
		$("#ordenServicio").alphanum({
	        allowSpace: true,
	        disallow: '´çÇ¿¨¡·'
	    });	

		$("#cantidad").alphanum({
	        allowSpace: true,
	        disallow: '´çÇ¿¨¡·'
	    });	
		
		var idAsegurador = $("#idAsegurador").val();
		if (idAsegurador!=-1) {
			prestadores();
			identificadores();
		}
	});

	function deshabilitar(){
		$("#tipoIdValor").prop("readonly", true);
		$("#apellidoPaterno").prop("readonly", true);
		$("#apellidoMaterno").prop("readonly", true);
		$("#nombre").prop("readonly", true);
	}



	</script>
</body>
</html>