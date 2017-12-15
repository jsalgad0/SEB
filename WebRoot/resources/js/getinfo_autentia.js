function isIE(){

   return /msie/i.test(navigator.userAgent) && !/opera/i.test(navigator.userAgent);

}

var autentiaPathTrx = "../AUTEMEXICO/";



function SerieAutentia(){
	var objNSuareu = document.getElementById('NSuareu');
	try {
		var Serie = objNSuareu.GetNroSerie(1);
		return Serie;
	}catch ( err ) {
		var oMsg = document.getElementById('la_Msg');
		oMsg.innerHTML = "Verifique que la componente nsuareu32.dll este registrada.";
		return ".";
	}
}



function TParamsInfoLector(){
	this.CSensor = '';
	this.CUbic	 = '';
	this.Ins	 = '';
	this.Sistema = '';
}

var MSensor = "";
var MUbic   = "";
var MIns	= "";
var sistema  = '';

function Graba(){
	var Params   = new TParamsInfoLector;
	erc				= 200;
	Params.CSensor	= "";
	Params.CUbic	= "";
	Params.Ins		= "";
	Params.Sistema	= sistema;
	erc = Autentia.Transaccion ("$SYS/_SensorCodeT64",Params);
	// Ahora que tenemos la respuesta de autentia, en Params, nos enviamos por POST el numero de auditoria
	MSensor = Params.CSensor;
	MUbic	= Params.CUbic;
	MIns	= Params.Ins;
	return; //Params.CSensor;
}	

function GetInfoSensorInterno(){
	var CodInterno;
	var oMsg = document.getElementById('la_Msg');
	CodInterno = "";
	oMsg.innerHTML = "";
	document.getElementById('lectorConectado').value = true;
	document.getElementById('la_Msg').innerHTML = "";
	document.getElementById('tx_Serie').value = ""; //Codigo interno lector
	document.getElementById('tx_Modelo').value = ""; //Institucion
	document.getElementById('tx_Marca').value = ""; //Lugar
	document.getElementById('tx_Fabric').value = ""; //Ambiente
	/*if( isIE() == false ) {

		oMsg.innerHTML = "Esta p\u00E1gina s\u00F3lo funciona con Internet Explorer 10 o superior.";

		return;

	}*/

	if (document.getElementById('rd_32').checked==true){
		sistema =  document.getElementById('rd_32').value;
		oSistema= "32 bits";
	}
	if (document.getElementById('rd_64').checked==true){
	sistema =  document.getElementById('rd_64').value;
	oSistema = "64 bits";
	}
	Graba();
	CodInterno = MSensor;
	if( CodInterno == "." )
		;
	else if( CodInterno == "" ){
		oMsg.innerHTML = "Debe conectar un lector para leer su c\u00F3digo interno.";
		document.getElementById('lectorConectado').value = false;
	}else{
		document.getElementById('tx_Serie').value = MSensor; // CodInterno;
		try {
			document.getElementById('tx_Modelo').value = MUbic; //Fnc.GetSannerInfo( CodInterno );
			document.getElementById('tx_Marca').value = MIns; //Fnc.strRc();
			document.getElementById('la_Ver').innerHTML = Fnc.Version();
		}catch ( err ) {
			//alert(err.description + ".<ul><li>- Para obtener informaci\u00F3n adicional verifique que la componente AutenLib09.dll est\u00E9 correctamente registrado.<li>- Verifique en la pesta\u00F1a de Seguridad que este sitio est\u00E9 entre los Sitios de Confianza.<li>- Debe activar la opción [Controles y Complementos ActiveX] / [Inicializar y activar la secuencia de comandos de los controles de ActiveX no marcados como seguros].<li>- Cierre el explorador e intente nuevamente.</ul>");
		}
	}
}
function IniciarUsuario(idUsuario, idAfiliado, idAgenda, dato, tipoDato, nombre,paterno,materno,sexo,fecha,estado){
	var _params = "undefined";
	var Params   = new TParams;
	Params.Dato  			= dato;
	Params.TipoDato			= tipoDato; 
	Params.Empresa	= "PFA120717716";

	Params.ApPaterno12	= paterno;
	Params.ApMaterno12	= materno;
	Params.Nombres12	= nombre;
	Params.Sexo12 = sexo;
	Params.FecAMD12 = fecha;
	Params.EntidadM12	= estado;
	
	Params.Erc				= "";
	Params.NroAudit			= "";
	Params.ErcDesc			= "";
	Params.FormaString		= "";
	Params.Cant				= 0;		
	erc						= 200;
	erc = Autentia.IniciarSesion("611330-2",21);
	erc = Autentia.Transaccion (autentiaPathTrx+"verifica",Params);
	
	$.getJSON("http://localhost:8080/SAB/auditoria/agregarAuditoria", {idUsuario:idUsuario, idAfiliado:idAfiliado, idAgenda:idAgenda, tipoDato:Params.TipoDato, dato:Params.Dato, tipoAudit:"0", nroAudit:Params.NroAudit, ercDesc:Params.ErcDesc, erc:Params.Erc} ,function(response){
		
	});
	
	if(document.getElementById('codigoAutentia') != undefined){
		document.getElementById('codigoAutentia').value = Params.ErcDesc;
	}
	
	if(document.getElementById('mensajeAutentia') != undefined){
		document.getElementById('mensajeAutentia').value = Params.Erc;
	}
	
	if(erc == 0){
		if(Params.Erc == 201){
				//alert("Usuario cancelo operacion");
			return false;
		}else if(Params.Erc == 2){
			//alert("La verificacion fue rechazada");
            return false;
		}else if(Params.Erc == 0){
			if(typeof(_params)!="undefined" && Params.hasOwnProperty("NroAudit")) _params.NroAudit = Params.NroAudit;
			else if(typeof(_params)!="undefined") _params.NroAudit = "";
			else{
				_params = new Object();
				_params.NroAudit = (Params.hasOwnProperty("NroAudit"))?Params.NroAudit:"";
			}
			
			if(document.getElementById('respuestaAutentia') != undefined){
				document.getElementById('respuestaAutentia').value = Params.NroAudit; //Fnc.strRc();
			}
			
            return true;
		}else if(Params.Erc == 1){
			alert("No se puede generar el patron de huella");
            return false;
		}
	}else if(erc == 2){
		//alert("La verificacion fue rechazada.");
        return false;
	 }else if(erc == 5){
		alert("No se enrolo correctamente.");
        return false;
	 }else if(erc == 201){
		//alert("Usuario cancelo operacion.");
        return false;
	 }else{
		alert("La verificacion fue rechazada");
        return false;
	 }

		 
}

function IniciarUsuarioLogin(idUsuario, idAfiliado, idAgenda, dato, tipoDato, nombre,paterno,materno,sexo,fecha,estado){
	var _params = "undefined";
	var Params   = new TParams;
	Params.Dato  			= dato;
	Params.TipoDato			= tipoDato; 
	Params.Empresa	= "PFA120717716";
	
	Params.Erc				= 0;
	Params.NroAudit			= "xxxx";
	Params.ErcDesc			= "joel";
	Params.FormaString		= "";
	Params.Cant				= 0;		
	erc						= 0;
	//erc = Autentia.IniciarSesion("611330-2",21);
	//erc = Autentia.Transaccion (autentiaPathTrx+"verifica",Params);
	console.log("idUsuario: " + idUsuario);
	console.log("idAfiliado: " + idAfiliado);
	console.log("idAgenda: " + idAgenda);
	console.log("dato: " + dato);
	console.log("tipoDato: " + tipoDato);
	console.log("nombre: " + nombre);
	
	$.getJSON("http://localhost:8080/SAB/auditoria/agregarAuditoria", {idUsuario:idUsuario, idAfiliado:idAfiliado, idAgenda:idAgenda, tipoDato:Params.TipoDato, dato:Params.Dato, tipoAudit:"0", nroAudit:Params.NroAudit, ercDesc:Params.ErcDesc, erc:Params.Erc} ,function(response){
		
	});
	
	if(document.getElementById('codigoAutentia') != undefined){
		document.getElementById('codigoAutentia').value = Params.ErcDesc;
	}
	
	if(document.getElementById('mensajeAutentia') != undefined){
		document.getElementById('mensajeAutentia').value = Params.Erc;
	}
	
	if(erc == 0){
		if(Params.Erc == 201){
				//alert("Usuario cancelo operacion");
			return false;
		}else if(Params.Erc == 2){
			//alert("La verificacion fue rechazada");
            return false;
		}else if(Params.Erc == 0){
			if(typeof(_params)!="undefined" && Params.hasOwnProperty("NroAudit")) _params.NroAudit = Params.NroAudit;
			else if(typeof(_params)!="undefined") _params.NroAudit = "";
			else{
				_params = new Object();
				_params.NroAudit = (Params.hasOwnProperty("NroAudit"))?Params.NroAudit:"";
			}
			
			if(document.getElementById('respuestaAutentia') != undefined){
				document.getElementById('respuestaAutentia').value = Params.NroAudit; //Fnc.strRc();
			}
			
            return true;
		}else if(Params.Erc == 1){
			//alert("No se puede generar el patron de huella");
            return false;
		}
	}else if(erc == 2){
		//alert("La verificacion fue rechazada.");
        return false;
	 }else if(erc == 5){
		alert("No se enrolo correctamente.");
        return false;
	 }else if(erc == 201){
		//alert("Usuario cancelo operacion.");
        return false;
	 }else{
		alert("La verificacion fue rechazada");
        return false;
	 }

		 
}

function TParams(){
	this.Dato = '';
	this.TipoDato  = '';
	this.Empresa  = '';
	this.Lugar = '';
}