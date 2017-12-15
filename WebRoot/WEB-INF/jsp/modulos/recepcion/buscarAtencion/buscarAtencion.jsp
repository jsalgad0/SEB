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
<div id="admin_contenido">

	<form:form action="actualizarAtencionPendiente" method='POST' commandName="buscarAtencionForm">
		
		
		
		<form:hidden path="idMedico"/>
		<form:hidden path="idAtencionMedica"/>
		<form:hidden path="idAgenda"/>
		<form:hidden path="idAfiliado"/>
		<form:hidden path="tipoBusqueda"/>
		
		
		
		<div class="linea_supervisor margen4">
		<div class="titulo_supervisor">BUSCAR ATENCIÓN:</div>
	     <form:input path="folio" cssClass="campo_supervisor tam_4 mayusculas" maxlength="10" cssStyle="margin-right:0px !important;" placeholder="Folio Atención"/><!--
          --><input class="btn_solo buscar_icono" onclick="buscar(1)" id="btnBuscarFolio"  readonly="true"/>
      		<div class="margen_izq2">
      			<form:input path="fechaAtencion"  cssClass="campo_supervisor tam_4 datepicker" maxlength="10" cssStyle="margin-right:0px !important;" placeholder="Fecha atención" /><!--
          --><input class="btn_solo buscar_icono" onclick="buscar(2)" id="btnBuscarFecha" readonly="true"/>
      		</div>	
    	</div>
		
	    <div class="admin_pleca"></div>
	                    
	    <div class="linea_supervisor">
	        
	
	    </div>
	    
		<div class="contenedor_recepcion">
		
	    	<div class="linea_supervisor margen4">
				<form:select path="idAsegurador"  cssClass="selectt campo_supervisor tam_2" onchange="prestadores(); identificadores();" >
				<form:option value="-1">Asegurador </form:option>
				</form:select>

				<form:select path="idPrestador" cssClass="selectt campo_supervisor tam_2" onchange="convenios();">
				<form:option value="-1">Prestador </form:option>
				</form:select>
				
				<form:select path="idConvenio"  cssClass="selectt campo_supervisor tam_11" >
				<form:option value="-1">Convenio </form:option>
				</form:select>					            
	    	</div>		
		
	   		<div class="linea_supervisor margen4">
	   		 <div class="error tam_9" id="errorApellidoPaterno"></div>
	            <div class="error margen5 tam_5" id="errorApellidoMaterno"></div>
	            <div class="error tam_9" id="errorNombre"></div> 
			</div>

	        <div class="linea_supervisor margen4">
				<form:select path="idIdentificador" cssClass="selectt campo_supervisor tam_4">
				<form:option value="-1">Identificador</form:option>
				</form:select>        
				
				<form:input path="idPaciente" cssClass="campo_supervisor tam_7" placeholder="ID Paciente" readonly="true"/>			
				
				
	            <div class="texto_supervisor gris tam_11">
	            	<div class="margen2" id="derechohabienteDiv">DERECHOHABIENTE<input type="radio" disabled="true" id="tipoAfiliado" value="D" label="DERECHOHABIENTE" /> BENEFICIARIO<input type="radio" path="tipoAfiliado" disabled="true" value="B" label="BENEFICIARIO"/></div>
	            	<div id="sexoAfiliadoDiv" style="display: none;"><input type="radio" disabled="true" id="sexoAfiliado" value="1" label="MASCULINO" /><input type="radio" id="sexoAfiliado" value="2" label="FEMENINO" /></div>
	         	</div>
	            
	            <div class="texto_supervisor gris tam_7" style="font-family:quicksand-bold">
	             		Asiste el paciente?
	             		
	             		 <form:checkbox path="personaConfianza" disabled="true" id="personaConfianza"/>
	             		
	            </div>
	
	    	</div>	
    	
	        
	   		<div class="linea_supervisor margen4">
	   		 <div class="error tam_9" id="errorApellidoPaterno"></div>
	            <div class="error margen5 tam_5" id="errorApellidoMaterno"></div>
	            <div class="error tam_9" id="errorNombre"></div> 
			</div>
			 
	     	<div class="linea_supervisor margen4">
	     		<form:input path="apellidoPaterno" cssClass="campo_supervisor tam_9 mayusculas"  maxlength="30" disabled="disabled" placeholder="Apellido Paterno" />
	     		<form:input path="apellidoMaterno" cssClass="campo_supervisor tam_9 mayusculas" maxlength="30" disabled="disabled" placeholder="Apellido Materno" />
	     		<form:input path="nombre" cssClass="campo_supervisor tam_5 mayusculas" maxlength="30" placeholder="Nombre"/>
	     		<input class="btn_buscar" id="btnBuscarNombre" onclick="buscar(2)" />
	            
	            <input type="button" id="nuevo" value="nuevo" onclick="nuevoAfiliado();" style="display: none;"/>
	    	</div>	
	    	
	    	   		<div class="linea_supervisor margen4">
	   		 <div class="error tam_9" id="errorApellidoPaterno"></div>
	            <div class="error margen5 tam_5" id="errorApellidoMaterno"></div>
	            <div class="error tam_9" id="errorNombre"></div> 
			</div>
	    		
	        <div class="linea_supervisor margen4">
	            <form:input path="fechaDeNac" cssClass="campo_supervisor gris tam_4 datepicker" placeholder="Fecha de Nac" disabled="true"/>
	            <form:input path="telefono"  cssClass="campo_supervisor tam_12" placeholder="Teléfono" maxlength="10" disabled="true" />
	            <form:input path="mail" cssClass="campo_supervisor tam_11" placeholder="Correo Electrónico" maxlength="50" disabled="true"/>
	    	</div> 
	    	
	   		<div class="linea_supervisor margen4">
	   		 <div class="error tam_9" id="errorApellidoPaterno"></div>
	            <div class="error margen5 tam_5" id="errorApellidoMaterno"></div>
	            <div class="error tam_9" id="errorNombre"></div> 
			</div>
			
		
			
			
	        <div class="linea_supervisor margen4" id="divPrestaciones">
	            <input class="campo_supervisor tam_11 mayusculas" placeholder="Descripción" id="prestacion" disabled="true"/>
	            <input type="hidden" id="idPrestacion">
	            <form:input path="cantidad" cssClass="campo_supervisor tam_8" maxlength="2" placeholder="Cant" disabled="true"/>
	            <form:input path="medico" cssClass="campo_supervisor tam_5" cssStyle="margin-right:0px !important; " placeholder="Médico" disabled="true"/>
	           
	           
	           <form:input path="idTiempo" cssClass="campo_supervisor tam_8 margen5" disabled="true" placeholder="Hora" />
	          	
	          
	    	</div>
	   		<div class="linea_supervisor margen4">
	   		 <div class="error tam_9" id="errorApellidoPaterno"></div>
	            <div class="error margen5 tam_5" id="errorApellidoMaterno"></div>
	            <div class="error tam_9" id="errorNombre"></div> 
			</div>
	    	
	    	
			<div class="tabla2 margen4 margen_arriba" id="tablaPrincipalPrestaciones" style="display:none!important" >
      
				<div class="tabla2">  
					<div class="titulo_tabla alto">
			        	<div class="celda2 fondo_v texto_supervisor blanco tam_19">Código-Descripción
			            </div>
			            <div class="celda2 fondo_v texto_supervisor blanco tam_6 centrado">Cant
			            </div>
			            <div class="celda2 fondo_v texto_supervisor blanco tam_6 centrado">Valor
			            </div>
			            <div class="celda2 fondo_v texto_supervisor blanco tam_6 centrado">Aporte
			            </div>
			            <div class="celda2 fondo_v texto_supervisor blanco tam_8 centrado">Copago
			            </div>
			            <div class="celda2 fondo_v texto_supervisor bote blanco tam_1 centrado">
			            </div>
			    	</div>
	      		</div>
	      
				<div class="tabla_contenedor desplazar tam_14 centrado">
					<div class="tabla2 tam_14" id="tablaPrestaciones" > 
						
		    			
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
				
				<div class="btn btn_cerrar" onclick="cerrar()"></div>
			</div> 		
		</div>				
	</form:form>

</div>
</div>
	
	<script type="text/javascript">
	
	$(function() {
	    $(".datepicker").datepicker({
	     
	      changeMonth: true,
	      changeYear: true,
	      maxDate:0
	     
	    });
	});
	
	
	$(document).ready(function() {
	
	$.ajaxSetup({ cache: false });
	
	var d = new Date();
	var mes = d.getMonth()+1;
	if(mes<10){
		mes = "0"+mes;
	}
	var fechaAtencion= d.getDate() + "/" + mes + "/" + d.getFullYear();
	$("#fechaAtencion").val(fechaAtencion);
	});
	
function validaCampos(idBuscar){
			var flag = false;

			if(idBuscar == 1){
			
				if($("#folio").val() == ""){
				$.fancybox.open({
						content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Verifique el Folio de atención</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
						openEffect : 'elastic',
						openSpeed  : 150,
						closeEffect : 'elastic',
						closeSpeed  : 150,								
		 				autoSize: false,
		 				width: 470,
		 				height: 185
					});
				}else{
				flag = true;
				}
				
	
			}else{
			
			var nombre = $("#nombre").val();
			var aPaterno = $("#apellidoPaterno").val();
			var aMaterno = $("#apellidoMaterno").val();
			
				if(nombre == "" && aPaterno == "" && aMaterno == ""){
					$.fancybox.open({
						content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Verifique el nombre del paciente</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
						openEffect : 'elastic',
						openSpeed  : 150,
						closeEffect : 'elastic',
						closeSpeed  : 150,								
		 				autoSize: false,
		 				width: 470,
		 				height: 185
					});
				}else{
					flag = true;
				}
			}
			
			return flag;
}

	function buscar(idBuscar){
	
	$("#tipoBusqueda").val(idBuscar);
	var flag = validaCampos(idBuscar);
	if(flag == true){
				var buscarAtencionForm = $("#buscarAtencionForm").serialize();
			    $.getJSON("buscarAtencion", buscarAtencionForm ,function(response){
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
		    			var atenciones = response.atenciones;			    		
			    		if(atenciones.length == 1 ){
			    		
			    		 $("#idAsegurador option").remove(); 
			    		 var optionAsegurador = '<option value="'+response.idAsegurador+'">'+response.descripcionAsegurador+'</option>';
			    		 $("#idAsegurador").html(optionAsegurador);
			    		 
			    		 $("#idPrestador option").remove(); 
			    		 var optionPrestador = '<option value="'+response.idPrestador+'">'+response.descripcionPrestador+'</option>';
			    		 $("#idPrestador").html(optionAsegurador);
			    		 
			    		 $("#idConvenio option").remove(); 
			    		 var optionConvenio = '<option value="'+response.idConvenio+'">'+response.descripcionConvenio+'</option>';
			    		 $("#idConvenio").html(optionConvenio);
						 
						 $("#idIdentificador option").remove(); 
						 var optionIdentificador = '<option value="'+response.idIdentificador+'">'+response.descripcionIdentificador+'</option>';
			    		 $("#idIdentificador").html(optionIdentificador);
			    		
			    		
			    		$("#idPaciente").val(response.idPaciente);
			    		$("#personaConfianza").attr('checked',response.personaConfianza);
			    		$("#apellidoPaterno").val(response.apellidoPaterno);
			    		$("#apellidoMaterno").val(response.apellidoMaterno);
			    		$("#nombre").val(response.nombre);
			    		$("#fechaDeNac").val(response.fechaDeNac);
			    		$("#telefono").val(response.telefono);
			    		$("#mail").val(response.mail);
			    		
			    		if(response.nombreMedico != undefined){
				    		$("#medico").val(response.nombreMedico);
				    		$("#idTiempo").val(response.idTiempo);
			    		}else{
			    			$("#medico").hide();
			    			$("#idTiempo").hide();
			    		}
			    		
			    		$("#tablaPrincipalPrestaciones").show();
			    		$("#tablaPrestaciones").empty();
			    		$("#fechaAtencion").val(response.fechaAtencion);
			    		var filas = "";
			    		var prestaciones = response.autocompleteVos;
			    		for (var i = 0; i < prestaciones.length; i++) {
			    			filas = '<div class="linea_tabla alto" id="'+i+'">'+	
			    			'<div class="celda2 texto_supervisor blanco tam_19"> '+prestaciones[i].codigo+'-'+prestaciones[i].label+' </div>'+			
			    			'<div class="celda2 texto_supervisor blanco tam_6 centrado">'+prestaciones[i].cantidad+'</div>'+
			        		'<div class="celda2 texto_supervisor blanco tam_6 centrado" id="valor">'+prestaciones[i].valor+'</div>'+
			        		'<div class="celda2 texto_supervisor blanco tam_6 centrado" id="aporte">'+prestaciones[i].aporte+'</div>'+
			        		'<div class="celda2 texto_supervisor blanco tam_8 centrado" id="copago">'+prestaciones[i].copago+'</div>'+
			        		'<div class="celda2 texto_supervisor blanco tam_1 centrado bote link3" ></div></div>';  			
			        		
						

			    			}	
			    		$("#tablaPrestaciones").append(filas);
			    			
			    			$("#btnBuscarFecha").hide();
			    			$("#btnBuscarFolio").hide();
			    			$("#btnBuscarNombre").hide();
			    			$("#nombre").attr("disabled",true);
			    			$("#apellidoPaterno").attr("disabled",true);
			    			$("#apellidoMaterno").attr("disabled",true);
			    			$("#fechaAtencion").attr("disabled",true);
			    			$("#folio").attr("disabled",true);
			    			
			    					    		
			    		}else{
			    		    $("#folio").val("");
			    			abrirListaAtenciones();
			    		}
			    	
			    	}
			    });	
		}
	}
	
	function abrirListaAtenciones(){
		var ctx = "${pageContext.request.contextPath}";
		$.fancybox.open({
			href : ctx + '/recepcion/recepcionListaAtenciones',
			type : 'iframe',
			padding : 5,
			autoSize: false,
			width: 820,
			height: 360,
			afterClose : function(){
			if($("#folio").val() != ""){
				buscar(1);
				}		
			}
		});
	}
		
		
	function cerrar(){
		var ctx = "${pageContext.request.contextPath}";
		window.location.href = ctx+"/menu";
	}
	</script>
</body>
</html>