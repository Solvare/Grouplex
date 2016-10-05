<?php
	//require_once dirname(__FILE__) . '/connection.php';
	try{
		$handler = new PDO('mysql:host=localhost;dbname=grouplex','root','');//here handler variable act act an open connection to mysql
		$handler->setAttribute(PDO::ATTR_ERRMODE,PDO::ERRMODE_EXCEPTION);	
	}
	catch(PDOException $e)
	{
		echo $e->getMessage();
		die();
	}
	$email='abc';
	$password='abc';
	$gcm_reg_id='abc';
	$sql="Insert into users(email,password,gcm_reg_id) VALUES (?,?,?)";
	$query=$handler->prepare($sql);
	
	$result=$query->execute(array($email,$password,$gcm_reg_id));
	echo $query->rowCount();
	//$user_id=1;
	/*$email='abc';
	$password='abc';
	$gcm_reg_id='abc';
	$created_at='1.1.2007'
	$sql="INSERT into users(email,password,gcm_reg_id) values(?,?,?)";
	//$handler->query($sql);
	$query1=$handler->prepare($sql);
	$query1->execute(array($email,$password,$gcm_reg_id,$created_at));
	
	
	
		/*class api{
		function __construct(){
			try{
				$handler = new PDO('mysql:host=localhost;dbname=grouplex','root','');
				$handler->setAttribute(PDO::ATTR_ERRMODE,PDO::ERRMODE_EXCEPTION);	
			}
			catch(PDOException $e)
			{
				echo $e->getMessage();
				die();
			}
		}
		public function createUser($email,$password,$gcm_reg_id){
			/*$email='abc';
			$password='abc';
			$gcm_reg_id='abc';*/
			/*$response=array();
			if($this->isUserExists($email)!=1){
				$sql="Insert into users(email,password,gcm_reg_id) VALUES (?,?,?)";
				$query=$handler->prepare($sql);
				$result=$query->execute(array($email,$password,$gcm_reg_id));
				if($result==1){
					$response['error']=false;
					$response['user']='hello';
				}else{
					$response['error']=true;
					$response['message']='Registeration error';
				}
			}else{
				$response['error']=false;
				$response['user']='hello';
			}
			return $response;	
		}
		public function isUserExists($email){
			$sql="SELECT user_id from users where email=?";
			$query=$handler->prepare($sql);
			$query->execute(array($email));
			echo $query->rowCount();
			return $query->rowCount();
		} 	
			
	}*/
	/*public function getUserByEmail($email){
		$sql="Select email from users where email=?";
		$query=$handler->prepare($sql);
		$query->execute(array($email));
	}*/

	*/
?>