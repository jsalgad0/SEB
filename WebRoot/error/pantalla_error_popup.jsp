<!doctype html>
<html>
<head>

<style>
body {
	background-color:#00919e;
}

@font-face {
    font-family: "quicksand-bold";
    src:url(../resources/font/Quicksand-Bold.otf) format("truetype");
}

.contenido_error {
	margin:0 auto;
	width:696px;
	height:365px;
	background-image:url(../resources/img/error_pantalla_popup.png);
}

.texto_error {
	width:550px;
	text-align:center;
	margin-left:90px;
	margin-top:180px;
	font-size:26px;
	color:#FFFFFF;
	display:inline-block;
}

.texto_error_volver {
	width:70px;
	margin-left:70px;
	font-size:15px;
	color:#FFFFFF;
	display:block;
	vertical-align:middle;
	font-family:quicksand-bold;
}

.btn_volver {
	display:block;
	text-align:center;
	vertical-align:middle;
}

p {
	font-family:quicksand-bold;
}

.btn_inicio {
	width:150px;
	height:35px;
	display:inline-block;
}

</style>
<meta charset="utf-8">
<title>Untitled Document</title>
</head>

<body>

<div class="contenido_error">
	<div class="texto_error">
    	<p>Lo sentimos, por el momento la información que solicita
no está disponible, inténtelo más tarde.</p>
    </div>
    
    <div class="btn_volver">
    	<a class="btn_inicio" href="#"><img src="../resources/img/btn_cerrar_popup.jpg" alt="Inicio" onclick="parent.$.fancybox.close();" /></a>
    </div>
</div>
</body>
</html>
