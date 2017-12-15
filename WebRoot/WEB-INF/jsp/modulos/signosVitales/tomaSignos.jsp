<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.maskedinput.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/menu_recepcion.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/estilos_1.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/supervisor.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/botones.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/popup_generales.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/tam_fijos.css">


<html>
<body>
<form:form name="signosVitalesForm" id="signosVitalesForm" commandName="signosVitalesForm" method="POST">
<form:hidden path="idAtencion" id="idAtencion" value="${infoSignos.idAtencion}"/>
<div id="admin_contenido2">

                    
    <div class="linea_supervisor back_paciente margen_arriba3" style="height:72px;">
    	<div class="linea_supervisor margen_arriba4 derecho">
    		<div class="texto_supervisor4 verde margen5">
	          PACIENTE
	         </div>
	         <div class="texto_supervisor4 gris margen5 pad_derecho">
	          ${infoSignos.nombre}
	         </div>
    	</div> 
   		<div class="linea_supervisor margen_arriba4">     
	         <div class="texto_supervisor verde margen2 tam_16">
	          Edad:
	         </div>
	         
	         <div class="texto_supervisor gris tam_6">
	          ${infoSignos.edad} aÒos
	         </div>
	         
	         <div class="texto_supervisor verde margen5 tam_16">
	          Peso:
	         </div>
	         
	         <div class="texto_supervisor gris tam_6">
	          ${infoSignos.peso} kg
	         </div>
			
	        <div class="texto_supervisor verde margen5 tam_1">
	          Altura:
	         </div>
	         
	         <div class="texto_supervisor gris tam_6">
	          ${infoSignos.altura} cm
	         </div>
	         
	         <div class="texto_supervisor verde margen5 tam_15">
	          T.A.:
	         </div>
	         
	         <div class="texto_supervisor gris margen5 tam_6">
	          ${infoSignos.tensionArterial}
	         </div>
	         
	         <div class="texto_supervisor verde tam_3">
	          ⁄ltima SomatometrÌa:
	         </div>
	         
	         <div class="texto_supervisor gris tam_4">
	          ${infoSignos.fechaUltimaSomatometria}
	         </div>
		</div>
    </div>  
    <div id="admin_contenido3">
      <div class="linea_corta_cont">
       <div class="linea_corta">
   		<div class="texto_supervisor2 verde margen2">
	          SIGNOS VITALES
	    </div>
	   </div>
			
		<div class="linea_corta margen_arriba2 margen2">

			<form:radiobutton path="primeraVez" name="primeraVez" value="0" />
	    	<div class="texto_supervisor5 blanco">1a. vez</div>
	        <form:radiobutton class="margen2" path="primeraVez" name="primeraVez" value="1" />
	    	<div class="texto_supervisor5 blanco">Subsecuente</div>
			
	    </div>	
	    
	     <div class="linea_corta margen_arriba2 margen2">
	    	<form:input class="campo_supervisor2 tam_8" maxlength="3" placeholder="Peso*" path="peso" id="peso" onblur="calcImc();" /><!--
	    	--><input class="campo_1 tam_16" placeholder="Kg." disabled />
	    	
	    	<form:input class="campo_supervisor2 tam_8 margen6" maxlength="3" placeholder="Altura*" path="altura" id="altura" onblur="calcImc();" /><!--
	    	--><input class="campo_1 tam_16" placeholder="cm." disabled />
	    	
	    	<form:input class="campo_supervisor2 tam_8 margen6" placeholder="T.A.*" path="tensionArterial" id="tensionArterial" onblur="calcTA();" /><!--
	    	--><input class="campo_1 tam_8" placeholder="mmHG" disabled />
	    	
	    	<form:input class="campo_supervisor2 tam_17 margen6" maxlength="4" placeholder="Temperatura*" path="temperatura" id="temperatura" onblur="calcTemp();" /><!--
	    	--><input class="campo_1 tam_16" placeholder="∫C" disabled />
	    	
	    	<form:input class="campo_supervisor2 tam_8 margen6" maxlength="5" placeholder="IMC" path="indiceMasaCoporal" id="indiceMasaCoporal" /><!--
	    	--><input class="campo_1 tam_1" placeholder="kg/m2 &sup2;" disabled />
	    
	    </div>
	    
	     <div class="linea_corta margen2">
	    	<div class="error2 tam_8"><form:errors path="peso"/></div>

	    	<div class="error2 tam_8 margen2"><form:errors path="altura"/></div>

	    	<div class="error2 tam_8 margen"><form:errors path="tensionArterial"/></div>
	    	
	    	<div class="error2 tam_12 margen12"><form:errors path="temperatura"/></div>
	    	
	    </div>
	    
	    
	     <div class="linea_corta margen_arriba2 margen2">
	     	    	
	    	<form:input class="campo_supervisor2 tam_8" maxlength="4" placeholder="Frec. Resp." path="frecuenciaRespiratoria" id="frecuenciaRespiratoria" onblur="calcFrecResp();" /><!--
	    	--><input class="campo_1 tam_8" placeholder="resp/min" disabled />
	    
	    	<form:input class="campo_supervisor2 tam_17 margen6" maxlength="4" placeholder="Frec. Cardiaca" path="frecuenciaCardiaca" id="frecuenciaCardiaca" onblur="calcFrecCardiaca();" /><!--
	    	--><input class="campo_1 tam_8" placeholder="lat/min" disabled />
	    	
	    	<form:input class="campo_supervisor2 tam_6 margen6" maxlength="5" placeholder="Glucosa" path="glucosa" id="glucosa" onblur="calcGlucosa();" /><!--
	    	--><input class="campo_1 tam_8" placeholder="mg/dl" disabled />
	    	
	    	<form:input class="campo_supervisor2 tam_8 margen6" maxlength="3" placeholder="Cintura" path="cintura" id="cintura" /><!--
	    	--><input class="campo_1 tam_16" placeholder="cm." disabled />
	 
	    </div>
	    
	    <div class="linea_corta margen2">
	    	<div class="error2 tam_8"></div>
	    </div>
	    
	   <div class="linea_corta margen_arriba2 margen2">
	    	
	    	<form:input class="campo_supervisor2 tam_12" maxlength="5" style="height:1.8em;" placeholder="Sat. OxÌgeno" path="saturacionOxigeno" id="saturacionOxigeno" /><!--
	    	--><input class="campo_1 tam_16" style="height:1.83em;" placeholder="%." disabled />
	    </div>
	    
	    <div class="linea_mini margen2">

				<div class="texto_supervisor4 blanco margen_arriba5">
		          OBSERVACIONES
		        </div>
		        <form:textarea class="campo_supervisor2 comment izquierdo" path="observaciones" id="observaciones" rows="2" maxlength="250"/>
     	</div>   
	    
	     <div class="linea_corta margen2 margen_arriba">
	     	<div class="btn btn_guardar" onclick="guardar()"></div>
            <div class="btn btn_cerrar" onclick="cerrar()"></div>
     	</div> 
	 
     </div>
     
         <div class="linea_mini">
     		<div class="texto_supervisor5 blanco margen_arriba"></div>
     		
     		<div class="linea_corta margen">
     			
				<div class="texto_supervisor4 blanco margen_arriba5">
		          ALERTAS
		         </div>
		         
		         <div class="texto_chico" id="alertIMC">
		          
		         </div>
		         
		          <div class="texto_chico" id="alertPeso">
		          
		         </div>
		         
		         <div class="texto_chico" id="alertTemperatura">
		          
		         </div>
		         
		         <div class="texto_chico" id="alertTensionArterial">
		          
		         </div>
	         
	         </div>
     	</div>	

    
    </div>
    
     
</div>

</form:form>
<script type="text/javascript">
	var guardado = ${infoSignos.guardado};
	function calcImc(){	
		var peso = $("#peso").val();  
		if(peso!=""){			      		   		
			if (peso>250 || peso < 0.5){
				$.fancybox.open({
					content : "<div id=\"contenedor2_popup\"><div class=\"icon_popup alert\"></div><h1>Su Peso es de "+peso+"<br /> Favor de ingresar un valor entre 0.5kg y 250kg</h1><div class=\"btn_popup\"><div class=\"btn_aceptar_popup\" onclick=\"parent.$.fancybox.close();\"></div></div></div>",
					openEffect : 'elastic',
					openSpeed  : 150,
					closeEffect : 'elastic',
					closeSpeed  : 150,
					autoSize: false,
	 				width: 400,
	 				height: 180
				});
				$("#peso").val("");  
			}
		};
		    
		var altura = $("#altura").val();
		if(altura!=""){
			if (altura>250 || altura < 10){
			$.fancybox.open({
					content : "<div id=\"contenedor2_popup\"><div class=\"icon_popup alert\"></div><h1>Su Altura es de "+altura+"<br />Favor de ingresar un valor entre 10cm y 250cm</h1><div class=\"btn_popup\"><div class=\"btn_aceptar_popup\" onclick=\"parent.$.fancybox.close();\"></div></div></div>",
					openEffect : 'elastic',
					openSpeed  : 150,
					closeEffect : 'elastic',
					closeSpeed  : 150,
					autoSize: false,
	 				width: 400,
	 				height: 180
				});
				$("#altura_sig").val("");  
			}
		};
		
		if(peso!=""&&altura!=""){
			var result=((peso/([altura]*[altura]))*10000);
			var flotante = parseFloat(result);
			var resultado = Math.round(flotante*100)/100;   
			$("#indiceMasaCoporal").val(resultado);
			
		if (resultado<=16.00){
			$("#alertIMC").html("Su IMC es de "+resultado+"\nDelgadez Severa - 16,0");
		}else if (resultado>16.01 && resultado<=16.99){
			$("#alertIMC").html("Su IMC es de "+resultado+"\nDelgadez moderada, entre 16,1 y 16,9");
		}else if (resultado>17.00 && resultado<=18.49){
			$("#alertIMC").html("Su IMC es de "+resultado+"\nDelgadez aceptable, entre 17,0 y 18,49");
		}else if (resultado>18.50 && resultado<=24.99 ){
			$("#alertIMC").html("Su IMC es de "+resultado+"\nPeso Normal, entre 18,5 y 24,9");
		}else if (resultado>25.00 && resultado<=29.99 ){
			$("#alertIMC").html("Su IMC es de "+resultado+"\nSobrepeso, entre 25,0 y 29,9");
		}else if (resultado>30.00 && resultado<=34.99 ){
			$("#alertIMC").html("Su IMC es de "+resultado+"\nObeso: Tipo I, entre 30,0 y 34,9");
		}else if (resultado>35.00 && resultado<=39.99 ){
			$("#alertIMC").html("Su IMC es de "+resultado+"\nObeso: Tipo II, entre 35,0 y 39,9");
		}else if (resultado>40.00 && resultado<=100.00 ){
			$("#alertIMC").html("Su IMC es de "+resultado+"\nObeso: Tipo III, entre 30,0 y 34,9");
		};            

	   }
	};

	function calcTemp(){
       	var temperatura = $("#temperatura").val();
       	if(temperatura!=""){
       		if (temperatura<30 || temperatura>50){
       			$.fancybox.open({
					content : "<div id=\"contenedor2_popup\"><div class=\"icon_popup alert\"></div><h1>Su Temperatura es de "+temperatura+"<br />Favor de ingresar un valor entre 30 y 50</h1><div class=\"btn_popup\"><div class=\"btn_aceptar_popup\" onclick=\"parent.$.fancybox.close();\"></div></div></div>",
					openEffect : 'elastic',
					openSpeed  : 150,
					closeEffect : 'elastic',
					closeSpeed  : 150,
					autoSize: false,
	 				width: 400,
	 				height: 180
				});
            	$("#temperatura").val("");
       		}else{
       			if (temperatura<35.00){
            	$("#alertTemperatura").html("Su Temperatura es de "+temperatura+"\nTipo Hipotermia, inferior de 35");
            }else if (temperatura<=36.9){
            	$("#alertTemperatura").html("Su Temperatura es de "+temperatura+"\n , dentro de lo normal");
            }else if (temperatura<=37.9 ){
            	$("#alertTemperatura").html("Su Temperatura es de "+temperatura+"\nTipo FebrÌcula, entre 37,1 y 37,9");
            }else if (temperatura<=41.00){
            	$("#alertTemperatura").html("Su Temperatura es de "+temperatura+"\nTipo Hipertermia o fiebre, mayor a 38");
            }else if (temperatura>50.00){
            	$.fancybox.open({
					content : "<div id=\"contenedor2_popup\"><div class=\"icon_popup alert\"></div><h1>Su Temperatura es de "+temperatura+"<br />Favor de ingresar un valor valido</h1><div class=\"btn_popup\"><div class=\"btn_aceptar_popup\" onclick=\"parent.$.fancybox.close();\"></div></div></div>",
					openEffect : 'elastic',
					openSpeed  : 150,
					closeEffect : 'elastic',
					closeSpeed  : 150,
					autoSize: false,
	 				width: 400,
	 				height: 180
				});
            	$("#temperatura").val("");
            }
       		}
            
       	}
   	};
   	
   	function calcGlucosa(){
        var glu	= $('#glucosa').val();
        if(glu!=""){
            if (glu < 70){
                $("#alertGlucosa").html("Glucosa: Por debajo de lo Normal");
            }
            if (glu >= 70 && glu < 100){
                $("#alertGlucosa").html("Glucosa: Normal");
            }
            if (glu >= 100 && glu < 125){
                $("#alertGlucosa").html("Glucosa: Pre-diabetes");
            }
            if (glu >= 126){
                $("#alertGlucosa").html("Tension Arterial: Diabetes");
            }
        }
    };
    
    function calcFrecResp(){
        var frecResp	= $('#frecuenciaRespiratoria').val();
        if(frecResp!=""){
            if (frecResp<5 || frecResp>100){
                $.fancybox.open({
					content : "<div id=\"contenedor2_popup\"><div class=\"icon_popup alert\"></div><h1>La frecuencia respiratoria debe ser entre 5 y 100</h1><div class=\"btn_popup\"><div class=\"btn_aceptar_popup\" onclick=\"parent.$.fancybox.close();\"></div></div></div>",
					openEffect : 'elastic',
					openSpeed  : 150,
					closeEffect : 'elastic',
					closeSpeed  : 150,
					autoSize: false,
	 				width: 400,
	 				height: 180
				});
                $('#frecuenciaRespiratoria').val("");
            }
        }
    };
    
    function calcFrecCardiaca(){
        var frecCardiaca	= $('#frecuenciaCardiaca').val();
        if(frecCardiaca!=""){
            if (frecCardiaca<30 || frecCardiaca>200){
                $.fancybox.open({
					content : "<div id=\"contenedor2_popup\"><div class=\"icon_popup alert\"></div><h1>La frecuencia cardiaca debe ser entre 30 y 200</h1><div class=\"btn_popup\"><div class=\"btn_aceptar_popup\" onclick=\"parent.$.fancybox.close();\"></div></div></div>",
					openEffect : 'elastic',
					openSpeed  : 150,
					closeEffect : 'elastic',
					closeSpeed  : 150,
					autoSize: false,
	 				width: 400,
	 				height: 180
				});
                $('#frecuenciaCardiaca').val("");
            }
        }
    };
    
    function calcTA(){
        var ta 	= $('#tensionArterial').val();
        ta = ta.replace(/_/g,"");
        var n = ta.indexOf("/");
        if(ta!="" && (n!=-1) ){
            ta = ta.split("/");
            var sistolica = ta[0];
            var diastolica= ta[1];
            if((sistolica>=50 && sistolica<=350) && (diastolica>=40 && diastolica<=200)){
                if (sistolica<120 && diastolica<80){
                    $("#alertTensionArterial").html("Tension Arterial: Optima");
                }else if (sistolica<130 && diastolica<85){
                    $("#alertTensionArterial").html("Tension Arterial: Normal");
                }else if ((sistolica>=130 && sistolica<=139)||(diastolica>=85 && diastolica<=90)){
                    $("#alertTensionArterial").html("Tension Arterial: Normal alta");
                }else if ((sistolica>=140 && sistolica<=159)||(diastolica>=90 && diastolica<=99)){
                    $("#alertTensionArterial").html("Tension Arterial: HipertensiÛn leve");
                }else if ((sistolica>=160 && sistolica<=179)||(diastolica>=100 && diastolica<=109)){
                    $("#alertTensionArterial").html("Tension Arterial: HipertensiÛn moderada");
                }else if (sistolica>=180||diastolica>=110){
                    $("#alertTensionArterial").html("Tension Arterial: HipertensiÛn grave");
                }
            }else if (n==0) {
				
			}
            else{
                $.fancybox.open({
					content : "<div id=\"contenedor2_popup\"><div class=\"icon_popup alert\"></div><h1>Favor de ingresar una TensiÛn Arterial valida</h1><div class=\"btn_popup\"><div class=\"btn_aceptar_popup\" onclick=\"parent.$.fancybox.close();\"></div></div></div>",
					openEffect : 'elastic',
					openSpeed  : 150,
					closeEffect : 'elastic',
					closeSpeed  : 150,
					autoSize: false,
	 				width: 400,
	 				height: 180
				});
				$('#tensionArterial').val("");
			}
    	}
        
    };
	
	function guardar(){
		/*var peso = $("#peso").val();
		var altura = $("#altura").val();
		var temperatura = $("#temperatura").val();
		var tensionArterial = $("#tensionArterial").val();*/
		var min = true;
		/*$("#msgPeso").hide();
		$("#msgAltura").hide();
		$("#msgTemperatura").hide();
		$("#msgTensionArterial").hide();
		if(peso==""){
			$("#msgPeso").show();
			min=false;
		}
		if(altura==""){
			$("#msgAltura").show();
			min=false;
		}
		if(temperatura==""){
			$("#msgTemperatura").show();
			min = false;
		}
		if(tensionArterial==""){
			$("#msgTensionArterial").show();
			min = false;
		}*/
		if(min){
			$("#signosVitalesForm").attr("action", "nuevo");
			$("#signosVitalesForm").submit();
		}
	};
	
	function cerrar(){
		window.location.href = '/sab/signos/listaPacientes';
	};
	
$( document ).ready(function() {
	$("#peso").numeric({
		allowLatin : false,
		disallow: '¥Á«ø®°∑',
		allowOtherCharSets : false
	});
	
	$("#altura").numeric({
		allowLatin : false,
		disallow: '¥Á«ø®°∑',
		allowOtherCharSets : false
	});
	
	$("#temperatura").numeric({
		allowLatin : false,
		disallow: '¥Á«ø®°∑',
		allowOtherCharSets : false
	});
	
	$("#indiceMasaCoporal").numeric({
		allowLatin : false,
		disallow: '¥Á«ø®°∑',
		allowOtherCharSets : false
	});
	
	$("#frecuenciaRespiratoria").numeric({
		allowLatin : false,
		disallow: '¥Á«ø®°∑',
		allowOtherCharSets : false
	});
	
	$("#frecuenciaCardiaca").numeric({
		allowLatin : false,
		disallow: '¥Á«ø®°∑',
		allowOtherCharSets : false
	});
	
	$("#glucosa").numeric({
		allowLatin : false,
		disallow: '¥Á«ø®°∑',
		allowOtherCharSets : false
	});
	
	$("#cintura").numeric({
		allowLatin : false,
		disallow: '¥Á«ø®°∑',
		allowOtherCharSets : false
	});
	
	$("#saturacionOxigeno").numeric({
		allowLatin : false,
		disallow: '¥Á«ø®°∑',
		allowOtherCharSets : false
	});
	
	$("#observaciones").alphanum({
       	allowSpace: true,
       	disallow: '¥Á«ø®°∑'
   	});
   	
   	$.mask.definitions['~'] = '[0-9 _]';
   	$("#tensionArterial").mask("99~/99?9");
   	
   	calcImc();
   	calcTemp();
   	calcTA();
   	calcFrecCardiaca();
   	calcFrecResp();
   	calcGlucosa();
   	calcImc();
   	
   	if(guardado){
		$.fancybox.open({
			content : "<div id=\"contenedor2_popup\"><div class=\"icon_popup alert\"></div><h1>Se han guardado correctamente los signos vitales</h1><div class=\"btn_popup\"><div class=\"btn_aceptar_popup\" onclick=\"parent.$.fancybox.close();\"></div></div></div>",
			openEffect : 'elastic',
			openSpeed  : 150,
			closeEffect : 'elastic',
			closeSpeed  : 150,
			autoSize: false,
				width: 400,
				height: 180
		}); 
	};
   	
});
</script>

</body>
</html>