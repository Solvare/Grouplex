<?php
	require_once dirname(__FILE__) . '/config.php';
	try{
		$handler = new PDO('mysql:host=localhost;dbname=grouplex','root','');//here handler variable act act an open connection to mysql
		$handler->setAttribute(PDO::ATTR_ERRMODE,PDO::ERRMODE_EXCEPTION);	
	}
	catch(PDOException $e)
	{
		echo $e->getMessage();
		die();
	}
?>