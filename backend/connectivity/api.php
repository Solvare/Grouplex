<?php
	error_reporting(-1);
	ini_set('display_errors', 'On');
	try{
				$handler = new PDO('mysql:host=localhost;dbname=grouplex','root','');
				$handler->setAttribute(PDO::ATTR_ERRMODE,PDO::ERRMODE_EXCEPTION);	
			}
			catch(PDOException $e)
			{
				echo $e->getMessage();
				die();
			}
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
	
	require '../../backend/libs/Slim/Slim.php';
	\Slim\Slim::registerAutoloader();
	$app = new \Slim\Slim();
	
	$app->post('user/register',function() use ($app){
		echo 1;
		$email=$app->request->post('email');
		$password=$app->request->post('password');
		$gcm_reg_id=$app->request->post('gcm_reg_id');
		//$a=new api();
		//$response=$a->createUser($email,$password,$gcm_reg_id);
		createUser($email,$password,$gcm_reg_id);
		echoRsponse(200,$response);
	});
	
    function createUser($email,$password,$gcm_reg_id){
			/*$email='abc';
			$password='abc';
			$gcm_reg_id='abc';*/
			$response=array();
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
		function isUserExists($email){
			$sql="SELECT user_id from users where email=?";
			$query=$handler->prepare($sql);
			$query->execute(array($email));
			return $query->rowCount();
		} 
	
	function echoRsponse($status_code,$response){
		$app = \Slim\Slim::getInstance();
		$app->status($status_code);	
		$app->contentType('application/json');
		echo json_encode($response);
	}
	$app->run();
?>	