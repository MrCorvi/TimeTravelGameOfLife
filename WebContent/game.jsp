<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://unpkg.com/purecss@2.0.3/build/pure-min.css" integrity="sha384-cg6SkqEOCV1NbJoCu11+bm0NvBRc8IYLRGXkmNrqUBfTjmMYwNKPWBTIKyw9mHNJ" crossorigin="anonymous">
	<link rel="stylesheet" href="css/font-awesome-4.7.0/css/font-awesome.min.css">

	
	<title>Time travel game of life</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	
	
	
	<style type="text/css">
	
		body{
			background-color: #446677;
			color: #EEE;
			font-family: 'Raleway',sans-serif;
		}
		
		h1 { font-family: 'Raleway',sans-serif; font-size: 40px; font-weight: 800; line-height: 72px; margin: 0 0 24px; text-align: center; text-transform: uppercase; }
		
		table{
			border: solid 2px black;
		   	margin:auto;
		   	border-radius: 5px;
		   	box-shadow: 0px 10px 15px black;
		}
		
		button, input{
			box-shadow: 0px 10px 10px black;
		}
		
		.cell{
			width: 13px;
			height: 13px;
			margin: 1px 1px 0px 0px;
			background-color: #AAA;
		}
		
		.center {
		  justify-content: center;
		}
		
		.header{
		  text-align: center;
		}
		
		.centerButtons{
			display: flex; 
			justify-content: center;
		}
		
		
        .button-error{
            color: white;
            border-radius: 4px;
            text-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
        }

        .button-error {
            background: rgb(202, 60, 60);
            /* this is a maroon */
        }
		
		#backStep{
			margin-top: 20px;
		}
		
		#clear{
			margin-top: 10px;
		}
		
		#buttons{
			margin-top: 30px;
		}
		
		.arrow{
			padding-top: 3px;
			padding-bottom: 3px;
		}
		
		#dataIndex{
			width:80px;
			margin: 2px 5px;
		}
		
	</style>
</head>

<body>
	<div class="pure-g">
	    <div class="pure-u-3-24"></div>
	    <div class="pure-u-18-24">
			<h1 class="header">
				Inverse Game of life Predictor
			</h1>
		</div>
	    <div class="pure-u-3-24"></div>
	</div>
	
	<div class="pure-g">
	    <div class="pure-u-4-24"></div>
	    <div class="pure-u-7-24">	    
			<table id="nextGrid"></table>	    
	    </div>
	    <div class="pure-u-2-24 centerButtons">
			<button id="nextStep" class="pure-button pure-button-primary"> <i class="fa fa-arrow-right"></i></button>		
	    </div>
	    <div class="pure-u-7-24">
			<table id="grid"></table>
	    </div>
	    <div class="pure-u-4-24"></div>
	</div>
	
	<div class="pure-g">
	    <div class="pure-u-4-24"></div>
	    <div class="pure-u-7-24">	   
	    </div>
	    <div class="pure-u-2-24"></div>
	    <div class="pure-u-7-24 centerButtons">	    
			<button id="backStep" class="pure-button pure-button-primary">Predict</button>
	    </div>
	    <div class="pure-u-4-24"></div>
	</div>
	
	
	<div class="pure-g">
	    <div class="pure-u-2-24"></div>
	    <div class="pure-u-20-24 centerButtons">
		    <br>
			<div id="buttons">
				
				<!-- 
					<button class="arrow pure-button pure-button-primary"><i class="fa fa-arrow-left"></i></button>
					<input id="dataIndex">
				-->				
				
				<button id="dataset" class="arrow pure-button pure-button-primary">
					<!-- 
				 		<i class="fa fa-arrow-right"></i>
					 -->
					 Test Set 
				</button>
			</div>
		</div>
	    <div class="pure-u-2-24"></div>
	</div>

	<div class="pure-g">
	    <div class="pure-u-2-24"></div>
	    <div class="pure-u-20-24 centerButtons">
		    <div>
				<button id="clear" class="pure-button button-error">clear</button>
		    </div>
	    </div>
	    <div class="pure-u-2-24"></div>
	</div>
	
	<!-- 	
	 <button id="build">build</button>
	 -->
	<script type="text/javascript"
		src="http://localhost:8080${pageContext.request.contextPath}/js/grid.js"></script>
</body>
</html>
