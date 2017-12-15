<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/menu_admin.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/estilos_1.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/supervisor.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/botones.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/popup_generales.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/tam_fijos.css">


<html>
<OBJECT classid='CLSID:592B9D7E-51C9-401F-A03C-4DE61FF7008D'
	name="Autentia" id='Autentia'>
	<embed></embed>
</OBJECT>
<body style="overflow:hidden;">
<form:form name='registroLectoresForm' action="borrarRegistroLector" method='POST' commandName="registroLectoresForm" id="registroLectoresForm">
	<form:hidden path="error"/>
	<form:hidden path="exito"/>
	<form:hidden path="idLugarAtencion"/>
	<form:hidden path="idPropietarioLector"/>
	<form:hidden path="codigoLugarAtencionAux"/>
	<form:hidden path="noDeSerieAux"/>
<div id="admin_contenido2">

                    
    <div class="linea_supervisor margen_arriba3" style="height:72px;">
    	
   		<div class="linea_supervisor margen_arriba4">     
	
		</div>
    </div>  
 
  <div class="admin_contenidoFrame">   
  
  <div class="linea_corta2">

  
	<div class="linea_supervisor margen">
   		<form:input cssClass="campo_supervisor tam_28 mayusculas" cssStyle="margin-right:0px !important;" placeholder="No. Serie externo*" path="noDeSerie"/><!--
          --><input class="btn_solo buscar_icono" onclick="consulta()" />
   		<form:input cssClass="campo_supervisor tam_10" placeholder="No. de serie interno" path="noDeSerieInterno" readonly="true" />
    </div>
    
    <div class="linea3 margen">
			<div class="error tam_2"><form:errors path="noDeSerie"/></div>
	 </div>
    
    <div class="linea_supervisor margen">
   		<form:input cssClass="campo_supervisor tam_2" placeholder="Código lugar de atención *" path="codigoLugarAtencion" readonly="true"/>
   		<form:input cssClass="campo_supervisor tam_10 mayusculas" placeholder="Lugar de atención *" path="lugarAtencion" readonly="true"/>
    </div>
    
     <div class="linea3 margen">
			<div class="error tam_2"><form:errors path="codigoLugarAtencion"/></div>
			<div class="error tam_10"><form:errors path="lugarAtencion"/></div>
	 </div>    
    
    <div class="linea_supervisor margen">
   		<form:input cssClass="campo_supervisor tam_2 mayusculas" placeholder="RFC Propietario *" path="rfcPropietarioLector" readonly="true"/>
   		<form:input cssClass="campo_supervisor tam_10" placeholder="Nombre propietario" path="propietarioLector" readonly="true" />
    </div> 
    
	<div class="linea3 margen">
		<div class="error tam_2"><form:errors path="rfcPropietarioLector"/></div>
	</div> 

     <div class="linea_supervisor margen margen_arriba">
     		<div class="btn btn_baja_lector" id="btnGuardar" onclick="guardar()"></div>
            <div class="btn btn_cerrar" onclick="cerrar()"></div>
     </div>
  </div>  
 </div>    
</div>

</form:form>
<script type="text/javascript">

	function cerrar(){
		var ctx = "${pageContext.request.contextPath}";
		window.location.href = ctx+"/administrador/registroLectores";
	}

	$( document ).ready(function() {
		$.ajaxSetup({ cache: false });
		
		var error = $("#error").val();
		var exito = $("#exito").val();
		if (error!="") {
			$.fancybox.open({
				content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>"+error+"</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
				openEffect : 'elastic',
				openSpeed  : 150,
				closeEffect : 'elastic',
				closeSpeed  : 150,								
				autoSize: false,
				width: 470,
				height: 185
			});	
		}
		if (exito!="") {
			registra(exito);
		}		
	});	
	
	function guardar(){
		$("#registroLectoresForm").submit();
	}
	
    function TParams(){
        this.CodExt = '';
        this.CSensor  = '';
        this.Ins  = '';
        this.CSite = '';
    }
	
	function consulta(){
		var Params   = new TParams; 
		erc				= 200;
		Params.CodExt   = $("#noDeSerie").val();
		Params.CSensor	= "";
		Params.Ins		= "";
		Params.CSite	= "";

		if ($("#noDeSerie").val() == "" || $("#noDeSerie").val() == "Numero de Serie Externo"){
			$.fancybox.open({
				content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Debe Ingresar Código Externo</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
				openEffect : 'elastic',
				openSpeed  : 150,
				closeEffect : 'elastic',
				closeSpeed  : 150,								
				autoSize: false,
				width: 470,
				height: 185
			});
			return;
		}
		
		erc = Autentia.Transaccion ("$SYS/_conslector",Params);  //enrola_10dedos_C
		if (Params.CSensor != ""){
			$("#codigoLugarAtencionAux").val(Params.Ins);
			$("#codigoLugarAtencion").val(Params.Ins);
			$("#propietarioLector").val(Params.CSite);
			$("#noDeSerieInterno").val(Params.CSensor);
			$("#noDeSerieAux").val($("#noDeSerie").val());
			$("#btnGuardar").show();
			
			var lugarAtencion;
			$.getJSON("propietarioLectorByPropietario", {busqueda:Params.CSite} ,function(response){ 
	            $.each(response, function(index, item) {
	            	$("#rfcPropietarioLector").val(item.rfc);
	            	$("#idPropietarioLector").val(item.idPropietarioLector);
	            });
			});
			
			$.getJSON("lugarAtencionByCodigo", {busqueda:Params.Ins} ,function(response){ 
	            $.each(response, function(index, item) {
	            	lugarAtencion = item.descripcion;
	            	$("#lugarAtencion").val(item.descripcion);
	            	$("#idLugarAtencion").val(item.lugarDeAtencionId);
	            });
			});	
		}
		else{
            var Params2 = new TParams;
            Params2.CSensor = "";
            Params2.Ins     = "";
            Params2.CSite   = "";         
			$("#codigoLugarAtencion").val(Params2.Ins);
			$("#propietarioLector").val(Params2.CSite);
			$("#noDeSerieInterno").val(Params2.CSensor);
			$("#noDeSerieAux").val($("#noDeSerie").val());
        	$("#rfcPropietarioLector").val("");
        	$("#idPropietarioLector").val(0);
        	$("#lugarAtencion").val("");
        	$("#idLugarAtencion").val("");
        	$("#btnGuardar").hide();
            res = Autentia.Transaccion("$SYS/_CodSensorMX",Params2);
            $("#noDeSerieInterno").val(Params2.CSensor);
			$.fancybox.open({
				content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Lector no ha sido dado de alta</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
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
		
	function registra(exito){
		var Params = new TParams;
			Params.CodInternoBD	= $("#noDeSerieInterno").val();
			Params.CodExterno	= $("#noDeSerieAux").val();
			Params.Institucion	= "SAB";
			Params.Ubicacion	= "";
			Params.Descripcion	= $("#propietarioLector").val();
		

	   	res = parent.Autentia.Transaccion("$SYS/_RegSensor64",Params);
		if (Params.resul == "S" || res==0){
			$.fancybox.open({
				content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario1\"></div><h1>"+exito+"</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
				openEffect : 'elastic',
				openSpeed  : 150,
				closeEffect : 'elastic',
				closeSpeed  : 150,								
				autoSize: false,
				width: 470,
				height: 185
			});	
		}else{
			test.Resp.value ="";
			$.fancybox.open({
				content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Hubo un error con Autentia</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
				openEffect : 'elastic',
				openSpeed  : 150,
				closeEffect : 'elastic',
				closeSpeed  : 150,								
				autoSize: false,
				width: 470,
				height: 185
			});	
			return false;
		}
	}
	
</script>


</body>
</html>