<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/menu_recepcion.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/estilos_1.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/supervisor.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/botones.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/popup_generales.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/tam_fijos.css">

<script>
cambiarBgCol = function(num){

$(".fondo_v2").removeClass("fondo_g2");
$("#celda_"+num).addClass("fondo_g2");

} 
</script>

<html>
<body>
<form:form>

<div id="contenedor4_popup">




	<div class="icon_popup usuario1"></div>
	<div class="linea_popup"><h1>SELECCIONAR ACOMPAÑANTE</h1></div>
    
    <div class="tabla_popup">   
        <div class="fila_popup">
        	<div class="celda_popup fondo_v texto_popup1 blanco tam_fijo7">Nombre
            </div>
            <div class="celda_popup fondo_v texto_popup1 blanco tam_fijo4">Fecha Nac
            </div>
            <div class="celda_popup fondo_v texto_popup1 blanco tam_fijo4">ID Interno
            </div>
            <div class="celda_popup fondo_v texto_popup1 blanco tam_fijo2">RFC
            </div>
            <div class="celda_popup fondo_v texto_popup1 blanco tam_fijo5">CURP
            </div>
    	</div> 
	</div>
	
 <div class="tabla_contenedor_popup desplazar tam_14">	
	<div class="tabla_popup">

          <div class="fila_popup">
        	<div class="celda_popup fondo_g texto_popup1 blanco tam_fijo7">Adriana de la Cruz León
            </div>
            <div class="celda_popup fondo_g texto_popup1 blanco tam_fijo4">25/03/1980
            </div>
            <div class="celda_popup fondo_g texto_popup1 blanco tam_fijo4">IFE
            </div>
            <div class="celda_popup fondo_g texto_popup1 blanco tam_fijo2">CRAL800325E3F
            </div>
            <div class="celda_popup fondo_g texto_popup1 blanco tam_fijo5">CRAL800325E3FMFGH6
            </div>
    	 </div> 
    	 
    	  <div class="fila_popup">
        	<div class="celda_popup fondo_g texto_popup1 blanco tam_fijo7">Adriana de la Cruz León
            </div>
            <div class="celda_popup fondo_g texto_popup1 blanco tam_fijo4">25/03/1980
            </div>
            <div class="celda_popup fondo_g texto_popup1 blanco tam_fijo4">IFE
            </div>
            <div class="celda_popup fondo_g texto_popup1 blanco tam_fijo2">CRAL800325E3F
            </div>
            <div class="celda_popup fondo_g texto_popup1 blanco tam_fijo5">
            </div>
    	 </div>
    	 
    	 <div class="fila_popup">
        	<div class="celda_popup fondo_g texto_popup1 blanco tam_fijo7">Adriana de la Cruz León
            </div>
            <div class="celda_popup fondo_g texto_popup1 blanco tam_fijo4">25/03/1980
            </div>
            <div class="celda_popup fondo_g texto_popup1 blanco tam_fijo4">IFE
            </div>
            <div class="celda_popup fondo_g texto_popup1 blanco tam_fijo2">CRAL800325E3F
            </div>
            <div class="celda_popup fondo_g texto_popup1 blanco tam_fijo5">
            </div>
    	 </div>
    	 
    	 <div class="fila_popup">
        	<div class="celda_popup fondo_g texto_popup1 blanco tam_fijo7">Adriana de la Cruz León
            </div>
            <div class="celda_popup fondo_g texto_popup1 blanco tam_fijo4">25/03/1980
            </div>
            <div class="celda_popup fondo_g texto_popup1 blanco tam_fijo4">IFE
            </div>
            <div class="celda_popup fondo_g texto_popup1 blanco tam_fijo2">CRAL800325E3F
            </div>
            <div class="celda_popup fondo_g texto_popup1 blanco tam_fijo5">
            </div>
    	 </div>
    	 
    	  <div class="fila_popup">
        	<div class="celda_popup fondo_g texto_popup1 blanco tam_fijo7">Adriana de la Cruz León
            </div>
            <div class="celda_popup fondo_g texto_popup1 blanco tam_fijo4">25/03/1980
            </div>
            <div class="celda_popup fondo_g texto_popup1 blanco tam_fijo4">IFE
            </div>
            <div class="celda_popup fondo_g texto_popup1 blanco tam_fijo2">CRAL800325E3F
            </div>
            <div class="celda_popup fondo_g texto_popup1 blanco tam_fijo5">
            </div>
    	 </div>

     </div>
  </div>

   	 <div class="linea_supervisor">
        
        	<select class="selectt campo_popup ancho1" name="Temporal">
            	<option class="ancho1">Tipo ID</option>
            	<option class="ancho1">2</option>
                <option class="ancho1">3</option>
            </select>
            
            <input class="campo_popup ancho2" placeholder="RFC/Acompañante" />
    </div>
    	
        <div class="linea_supervisor">
        	<div class="error_popup ancho1">
            	Indique tipo de ID
            </div>
            
            <div class="error_popup ancho2">
            	Indique RFC del Acompañante
            </div>
 
        </div>
        
        <div class="linea_supervisor">
			<input class="campo_popup ancho3" placeholder="Nombre" />
            <input class="campo_popup ancho3" placeholder="Apellido Paterno" />
            <input class="campo_popup ancho3" placeholder="Apellido Materno" />
    	</div>
        
        <div class="linea_supervisor"> 
            <div class="error_popup ancho3">
            	Introduzca Nombre
            </div>
            <div class="error_popup ancho3">
            	Introduzca Apellido paterno
            </div>
            
            <div class="error_popup ancho3">
            	Introduzca Apellido Materno
            </div>
        </div>
        
         <div class="linea_supervisor">
  
            <input id="datepicker" class="campo_popup ancho2" placeholder="Fecha de Nac" />

            <div class="text_popup gris alto_campo ancho3">
            	
          		<input type="radio" name="genero2" />Masculino
            	<input type="radio" name="genero2" />Femenino
         	</div>
    	</div>
        
        <div class="linea_supervisor">
        	<div class="error_popup ancho2">
            	Indique Fecha de Nac
            </div>
            
            <div class="error_popup ancho3">
            	Indique Género
            </div>
 
        </div>
   
   <div class="btn_popup">
   			<div class="btn_certificar_popup"></div>
   		    <div class="btn_cerrar_popup"></div>
   </div>
    
   
</div>

<!-- nononononoonononnonononononoonononononoononononono POPUP DE ACOMPAÑANTE ARRIBA -->

        <div class="linea_supervisor tam_14">
 
        </div>

<!-- nononononoonononnonononononoonononononoononononono ORDEN DE SERVICIOS ABAJO-->

<div id="contenedor4_popup">

	<div class="icon_popup reportem"></div>
	
	<div class="linea_popup"><h1>Orden de Servicios Médicos</h1>
    	<div class="campo_datos_popup tam_8">AB12345</div>
    	<h1>de fecha</h1>
    	<div class="campo_datos_popup tam_6">
    		 <script>
				 var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1; //January is 0!

    var yyyy = today.getFullYear();
    if(dd<10){
        dd='0'+dd
    } 
    if(mm<10){
        mm='0'+mm
    } 
    var today = dd+'/'+mm+'/'+yyyy;

				document.write(today);
			
		</script>
	
    	</div>
    </div>
    
<div  class="tabla_popup2 margen10">   
     <div class="tabla_popup2">   
        <div class="fila_popup">
        	<div class="celda_popup fondo_v texto_popup1 blanco tam_fijo6">Prestaciones Solicitadas
            </div>
            <div class="celda_popup fondo_v texto_popup1 blanco tam_fijo7">Fecha de Atención
            </div>
            <div class="celda_popup fondo_v texto_popup1 blanco tam_fijo9">
            </div>
    	</div> 
	</div>
	
  <div class="tabla_contenedor_popup2 desplazar tam_14">  	
	<div class="tabla_popup2">

          <div class="fila_popup">
        	<div class="celda_popup fondo_g texto_popup1 blanco tam_fijo6">Exámen de orina completa
            </div>
            <div class="celda_popup fondo_g texto_popup1 blanco tam_fijo7">25/03/1980
            </div>
            <div class="celda_popup fondo_g texto_popup1 blanco tam_fijo9"><input type="checkbox" name="ok">
            </div>
    	 </div> 
    	 
    	 <div class="fila_popup">
        	<div class="celda_popup fondo_g texto_popup1 blanco tam_fijo6">Química sanguínea
            </div>
            <div class="celda_popup fondo_g texto_popup1 blanco tam_fijo7">25/03/1980
            </div>
            <div class="celda_popup fondo_g texto_popup1 blanco tam_fijo9"><input type="checkbox" name="ok">
            </div>
    	 </div> 
    	 
    	 <div class="fila_popup">
        	<div class="celda_popup fondo_g texto_popup1 blanco tam_fijo6">EGO
            </div>
            <div class="celda_popup fondo_g texto_popup1 blanco tam_fijo7">25/03/1980
            </div>
            <div class="celda_popup fondo_g texto_popup1 blanco tam_fijo9"><input type="checkbox" name="ok">
            </div>
    	 </div> 
    	 
    	 <div class="fila_popup">
        	<div class="celda_popup fondo_g texto_popup1 blanco tam_fijo6">EGO
            </div>
            <div class="celda_popup fondo_g texto_popup1 blanco tam_fijo7">25/03/1980
            </div>
            <div class="celda_popup fondo_g texto_popup1 blanco tam_fijo9"><input type="checkbox" name="ok">
            </div>
    	 </div> 
     </div>
   </div> 
 </div>
 
   <div class="btn_popup">
   			<div class="btn_aceptar_popup"></div>
   		    <div class="btn_cerrar_popup"></div>
   </div>
    
   
</div>

<!-- nononononononononoon Popup Busqueda de medicos ABAJO -->

<div id="contenedor4_popup">

	<div class="icon_popup b_medico"></div>
	<div class="linea_popup"><h1>BÚSQUEDA DE MÉDICOS</h1></div>
   

   
 
   <div class="tabla_popup3 margen10">
    <div class="tabla_popup3">   
        <div class="fila_popup">
        	<div class="celda_popup fondo_v texto_popup2 blanco tam_13">Médico y Horarios disponibles
            </div>

           
    	</div> 
	</div>
	
  <div class="tabla_contenedor_popup3 desplazar_2 alto_1">  		
	<div class="tabla_popup3 alto_1">

          <div class="fila_popup">
        	<div class="celda_popup fondo_g texto_popup1 blanco tam_fijo1">Roberto Gómez Bolaños
            </div>
            <div id="celda_1" onclick="cambiarBgCol('1')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">7:15
            </div>
            <div id="celda_2" onclick="cambiarBgCol('2')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">8:10
            </div>
            <div id="celda_3" onclick="cambiarBgCol('3')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:12
            </div>
            <div id="celda_4" onclick="cambiarBgCol('4')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:30
            </div>
             <div id="celda_5" onclick="cambiarBgCol('5')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:10
            </div>
            <div id="celda_6" onclick="cambiarBgCol('6')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:25
            </div>
            <div id="celda_7" onclick="cambiarBgCol('7')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:12
            </div>
            <div id="celda_8" onclick="cambiarBgCol('8')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:30
            </div>
             <div id="celda_9" onclick="cambiarBgCol('9')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:10
            </div>
            <div id="celda_10" onclick="cambiarBgCol('10')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:25
            </div>

    	 </div> 
    	 
    	<div class="fila_popup">
        	<div class="celda_popup fondo_g texto_popup1 blanco tam_fijo1">Rodrigo Verona Ibarra
            </div>
            <div id="celda_11" onclick="cambiarBgCol('11')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">7:15
            </div>
            <div id="celda_12" onclick="cambiarBgCol('12')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">8:10
            </div>
            <div id="celda_13" onclick="cambiarBgCol('13')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:12
            </div>
            <div id="celda_14" onclick="cambiarBgCol('14')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">
            </div>
             <div id="celda_15" onclick="cambiarBgCol('15')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:10
            </div>
            <div id="celda_16" onclick="cambiarBgCol('16')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:25
            </div>
            <div id="celda_17" onclick="cambiarBgCol('17')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:12
            </div>
            <div id="celda_18" onclick="cambiarBgCol('18')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">
            </div>
             <div id="celda_19" onclick="cambiarBgCol('19')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:10
            </div>
            <div id="celda_20" onclick="cambiarBgCol('20')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:25
            </div>

    	 </div> 
    	 
              <div class="fila_popup">
        	<div class="celda_popup fondo_g texto_popup1 blanco tam_fijo1">Saul Camarena Ortiz
            </div>
            <div id="celda_21" onclick="cambiarBgCol('21')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">7:15
            </div>
            <div id="celda_22" onclick="cambiarBgCol('22')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">8:10
            </div>
            <div id="celda_23" onclick="cambiarBgCol('23')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:12
            </div>
            <div id="celda_24" onclick="cambiarBgCol('24')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">
            </div>
             <div id="celda_25" onclick="cambiarBgCol('25')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:10
            </div>
            <div id="celda_26" onclick="cambiarBgCol('26')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:25
            </div>
            <div id="celda_27" onclick="cambiarBgCol('27')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:12
            </div>
            <div id="celda_28" onclick="cambiarBgCol('28')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">
            </div>
             <div id="celda_29" onclick="cambiarBgCol('29')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:10
            </div>
            <div id="celda_30" onclick="cambiarBgCol('30')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:25
            </div>

    	 </div> 
    	 
          <div class="fila_popup">
        	<div class="celda_popup fondo_g texto_popup1 blanco tam_fijo1">Angelica Beltrán Osorio
            </div>
            <div id="celda_31" onclick="cambiarBgCol('1')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">7:15
            </div>
            <div id="celda_32" onclick="cambiarBgCol('32')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">8:10
            </div>
            <div id="celda_33" onclick="cambiarBgCol('33')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:12
            </div>
            <div id="celda_34" onclick="cambiarBgCol('34')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">
            </div>
             <div id="celda_35" onclick="cambiarBgCol('35')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:10
            </div>
            <div id="celda_36" onclick="cambiarBgCol('36')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:25
            </div>
            <div id="celda_37" onclick="cambiarBgCol('37')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:12
            </div>
            <div id="celda_38" onclick="cambiarBgCol('38')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">
            </div>
             <div id="celda_39" onclick="cambiarBgCol('39')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:10
            </div>
            <div id="celda_40" onclick="cambiarBgCol('40')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:25
            </div>

    	 </div> 
    	 
    	 <div class="fila_popup">
        	<div class="celda_popup fondo_g texto_popup1 blanco tam_fijo1">Marinano Escobedo Tellez
            </div>
            <div id="celda_41" onclick="cambiarBgCol('41')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">7:15
            </div>
            <div id="celda_42" onclick="cambiarBgCol('42')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">8:10
            </div>
            <div id="celda_43" onclick="cambiarBgCol('43')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:12
            </div>
            <div id="celda_44" onclick="cambiarBgCol('44')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">
            </div>
             <div id="celda_45" onclick="cambiarBgCol('45')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:10
            </div>
            <div id="celda_46" onclick="cambiarBgCol('46')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:25
            </div>
            <div id="celda_47" onclick="cambiarBgCol('47')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:12
            </div>
            <div id="celda_48" onclick="cambiarBgCol('48')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">
            </div>
             <div id="celda_49" onclick="cambiarBgCol('49')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:10
            </div>
            <div id="celda_50" onclick="cambiarBgCol('50')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:25
            </div>

    	 </div> 
    	 
    	 <div class="fila_popup">
        	<div class="celda_popup fondo_g texto_popup1 blanco tam_fijo1">Lilia Lopez Basurto
            </div>
            <div id="celda_51" onclick="cambiarBgCol('51')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">7:15
            </div>
            <div id="celda_52" onclick="cambiarBgCol('52')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">8:10
            </div>
            <div id="celda_53" onclick="cambiarBgCol('53')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:12
            </div>
            <div id="celda_54" onclick="cambiarBgCol('54')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">
            </div>
             <div id="celda_55" onclick="cambiarBgCol('55')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:10
            </div>
            <div id="celda_56" onclick="cambiarBgCol('56')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:25
            </div>
            <div id="celda_57" onclick="cambiarBgCol('57')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:12
            </div>
            <div id="celda_58" onclick="cambiarBgCol('58')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">
            </div>
             <div id="celda_59" onclick="cambiarBgCol('59')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:10
            </div>
            <div id="celda_60" onclick="cambiarBgCol('60')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:25
            </div>

    	 </div> 
    	 
    	 <div class="fila_popup">
        	<div class="celda_popup fondo_g texto_popup1 blanco tam_fijo1">Cristopher Mendoza Vital
            </div>
            <div id="celda_61" onclick="cambiarBgCol('61')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">7:15
            </div>
            <div id="celda_62" onclick="cambiarBgCol('62')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">8:10
            </div>
            <div id="celda_63" onclick="cambiarBgCol('63')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:12
            </div>
            <div id="celda_64" onclick="cambiarBgCol('64')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">
            </div>
             <div id="celda_65" onclick="cambiarBgCol('65')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:10
            </div>
            <div id="celda_66" onclick="cambiarBgCol('66')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:25
            </div>
            <div id="celda_67" onclick="cambiarBgCol('67')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:12
            </div>
            <div id="celda_68" onclick="cambiarBgCol('68')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">
            </div>
             <div id="celda_69" onclick="cambiarBgCol('69')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:10
            </div>
            <div id="celda_70" onclick="cambiarBgCol('70')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:25
            </div>

    	 </div> 
    	 
    	 <div class="fila_popup">
        	<div class="celda_popup fondo_g texto_popup1 blanco tam_fijo1">Daniel Adonay Martinez
            </div>
            <div id="celda_71" onclick="cambiarBgCol('71')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">7:15
            </div>
            <div id="celda_72" onclick="cambiarBgCol('72')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">8:10
            </div>
            <div id="celda_73" onclick="cambiarBgCol('73')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:12
            </div>
            <div id="celda_74" onclick="cambiarBgCol('74')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">
            </div>
             <div id="celda_75" onclick="cambiarBgCol('75')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:10
            </div>
            <div id="celda_76" onclick="cambiarBgCol('76')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:25
            </div>
            <div id="celda_77" onclick="cambiarBgCol('77')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:12
            </div>
            <div id="celda_78" onclick="cambiarBgCol('78')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">
            </div>
             <div id="celda_79" onclick="cambiarBgCol('79')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:10
            </div>
            <div id="celda_80" onclick="cambiarBgCol('80')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:25
            </div>

    	 </div> 
    	 
    	 <div class="fila_popup">
        	<div class="celda_popup fondo_g texto_popup1 blanco tam_fijo1">Valente Escobar Flores
            </div>
            <div id="celda_81" onclick="cambiarBgCol('81')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">7:15
            </div>
            <div id="celda_82" onclick="cambiarBgCol('82')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">8:10
            </div>
            <div id="celda_83" onclick="cambiarBgCol('83')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:12
            </div>
            <div id="celda_84" onclick="cambiarBgCol('84')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">
            </div>
             <div id="celda_85" onclick="cambiarBgCol('85')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:10
            </div>
            <div id="celda_86" onclick="cambiarBgCol('86')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:25
            </div>
            <div id="celda_87" onclick="cambiarBgCol('87')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:12
            </div>
            <div id="celda_88" onclick="cambiarBgCol('88')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">
            </div>
             <div id="celda_89" onclick="cambiarBgCol('89')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:10
            </div>
            <div id="celda_90" onclick="cambiarBgCol('90')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">9:25
            </div>

    	 </div> 
     </div>
   </div>
 </div>
 
   <div class="btn_popup">
   			<div class="btn_guardar_popup"></div>
   		    <div class="btn_cerrar_popup"></div>
   </div>
    
   
</div>


</form:form>


</body>
</html>