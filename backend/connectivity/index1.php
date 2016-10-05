<?php
try{
	$handler = new PDO('mysql:host=localhost;dbname=gcm_chat','root','');//here handler variable act act an open connection to mysql
	$handler->setAttribute(PDO::ATTR_ERRMODE,PDO::ERRMODE_EXCEPTION);	
	}
	catch(PDOException $e)
	{
		echo $e->getMessage();
		die();
	}
	$query=$handler->query("Select * from users");
		if($query->rowCount())
		{
			while($r=$query->fetch(PDO::FETCH_OBJ))
			{
				echo $r->name,'<br>';
			}
		}
		else{
			echo 'no results';
		}
?>