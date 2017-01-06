<?php

error_reporting(-1);
ini_set('display_errors', 'On');

require '.././libs/Slim/Slim.php';

\Slim\Slim::registerAutoloader();

$app = new \Slim\Slim();


// 1. fetching all groups of a particular user

$app->get('/:user_id/groups', function($user_id) {
    $response = array();
    require_once dirname(__FILE__) . '../../include/db_connect.php';
    $db = new DbConnect();
    $conn = $db->connect();

    //check if user exists or not
    $exist = $conn->query("SELECT user_id FROM users WHERE user_id = $user_id");
    if($exist->rowCount()==0)
    {
        $response["error"] = true;
        $response["message"] = "No such user exists";
    }
    else
    {

        $stmt = $conn->query("SELECT group_name FROM master,groups WHERE master.user_id = $user_id AND groups.group_id = master.group_id");
        if($stmt->rowCount()==0)
        {
            $response["error"] = true;
            $response["message"] = "No associated groups";
        }
        else
        {
            $response["error"] = false;
            $response["groups"] = array();
            foreach($stmt as $row)
            {
                $tmp = array();
                $tmp["group_name"] = $row["group_name"];
                array_push($response["groups"], $tmp);
            }
        }  
    }
    $exist = null;
    $stmt = null;
    $conn = null;

    echoRespnse(200, $response);
});


// 2. fetching all messages of a particular group

$app->get('/:group_id/messages', function($group_id) {
    $response = array();
    require_once dirname(__FILE__) . '../../include/db_connect.php';
    $db = new DbConnect();
    $conn = $db->connect();

    //check if group exists or not
    $exist = $conn->query("SELECT group_id FROM groups WHERE group_id = $group_id");
    if($exist->rowCount()==0)
    {
        $response["error"] = true;
        $response["message"] = "No such group exists";
    }
    else
    {

        $stmt = $conn->query("SELECT chat FROM messages WHERE group_id = $group_id");
        if($stmt->rowCount()==0)
        {
            $response["error"] = true;
            $response["message"] = "No associated messages";
        }
        else
        {
            $response["error"] = false;
            $response["messages"] = array();
            foreach($stmt as $row)
            {
                $tmp = array();
                $tmp["chat"] = $row["chat"];
                array_push($response["messages"], $tmp);
            }
        }  
    }
    $exist = null;
    $stmt = null;
    $conn = null;

    echoRespnse(200, $response);
});


// 3. fetching all members of a particular group

$app->get('/:group_id/members', function($group_id) {
    $response = array();
    require_once dirname(__FILE__) . '../../include/db_connect.php';
    $db = new DbConnect();
    $conn = $db->connect();

    //check if group exists or not
    $exist = $conn->query("SELECT group_id FROM groups WHERE group_id = $group_id");
    if($exist->rowCount()==0)
    {
        $response["error"] = true;
        $response["message"] = "No such group exists";
    }
    else
    {

        $stmt = $conn->query("SELECT full_name FROM master,users WHERE master.group_id = $group_id AND master.user_id = users.user_id");
        $response["error"] = false;
        $response["members"] = array();
        foreach($stmt as $row)
        {
            $tmp = array();
            $tmp["name"] = $row["full_name"];
            array_push($response["members"], $tmp);
        }
        
    }
    $exist = null;
    $stmt = null;
    $conn = null;

    echoRespnse(200, $response);
});


// 4. posting message to a particular group

$app->post('/:group_id/:user_id/post', function($group_id,$user_id) use ($app) {
   
    global $app;
    $response = array();
    require_once dirname(__FILE__) . '../../include/db_connect.php';
    $db = new DbConnect();
    $conn = $db->connect();

    //check if group exists or not
    $exist_group = $conn->query("SELECT group_id FROM groups WHERE group_id = $group_id");
    $exist_user = $conn->query("SELECT user_id FROM users WHERE user_id = $user_id");
    if($exist_group->rowCount()==0 || $exist_user->rowCount()==0)
    {
        $response["error"] = true;
        $response["message"] = "The group or user does not exists";
    }
    else
    {

        $stmt = $conn->query("SELECT admin_id FROM groups WHERE admin_id = $user_id AND group_id=$group_id");
        if($stmt->rowCount()==0)
        {
            $response["error"] = true;
            $response["message"] = "The user is not authorised to post in this group";
        }
        else if($stmt->rowCount()==1)
        {
            $chat = $app->request->get('chat');
            $insrt = $conn->exec("INSERT INTO messages(group_id,chat) VALUES('$group_id','$chat')");
			
            if($insrt==1)
            {
                $response["error"] = false;
                $response["chat"] = $chat;
            }
            else
            {
                $response["error"] = true;
                $response["message"] = "Insertion Failed";
            }
            
        }
    }
    $exist_group = null;
    $exist_user = null;
    $insrt = null;
    $stmt = null;
    $conn = null;

    echoRespnse(200, $response);
});


// 5. searching for a group

$app->get('/search/:query', function($query) {
	global $app;
    $response = array();
    require_once dirname(__FILE__) . '../../include/db_connect.php';
    $db = new DbConnect();
    $conn = $db->connect();

    $stmt = $conn->query("SELECT group_name FROM groups WHERE group_name LIKE '%$query%'");
    if($stmt->rowCount()==0)
    {
        $response["error"] = true;
        $response["message"] = "No groups found";
    }
    else
    {
        $response["error"] = false;
        $response["groups"] = array();
        foreach($stmt as $row)
        {
            $tmp = array();
            $tmp["group_name"] = $row["group_name"];
            array_push($response["groups"], $tmp);
        }
     
    }
    
    $stmt = null;
    $conn = null;
	
    echoRespnse(200, $response);
});

//following link is used for registration
$app->post('/user/register',function() use ($app){
	global $app;
	$response=array();
	require_once dirname(__FILE__) . '../../include/db_connect.php';
	$db = new DbConnect();
    $conn = $db->connect();
	
	$email=$app->request->post('email');
	$password=$app->request->post('password');
	$full_name=$app->request->get('full_name');
	$user_exist=$conn->query("select user_id from users where email='$email'");
	if($user_exist->rowCount()>0){
		$response["error"]=true;
		$response["message"]="The user is already registered";
	}else{
		
		if($conn->query("INSERT INTO users(email,password,full_name,gcm_reg_id) VALUES( '$email','$password','$full_name','')")==TRUE)
		{
			$response["error"]=false;
			$response["message"]="New user created";
		}
		else{
			$response["error"]=true;
			$respomse["message"]="An error occurred while registration";
		}
			
	}
    $conn = null;
	$user_exist=null;
	echoRespnse(200,$response);
});


//following link is used for user login
$app->post('/user/login',function() use ($app){
	$response=array();
	require_once dirname(__FILE__) . '../../include/db_connect.php';
	$db = new DbConnect();
    $conn = $db->connect();
	
	$email=$app->request->post('email');
	$password=$app->request->post('password');
	$user_exist=$conn->query("select user_id from users where email='$email' AND password='$password'");
	if($user_exist->rowCount()==1){
		$response["error"]=false;
		$response["message"]="user successfully logged in";
	}else{
		$response["error"]=true;
		$response["message"]="user does not exist";
	}
    $conn = null;
	echoRespnse(200,$response);
});


//link to create new group
$app->post('/newgroup/:user_id',function($user_id) use ($app){
	$response=array();
	require_once dirname(__FILE__) . '../../include/db_connect.php';
	$db = new DbConnect();
    $conn = $db->connect();
	
	$user_exist=$conn->query("select user_id from users where user_id=$user_id");
	if($user_exist->rowCount()>0){
		$group_name=$app->request->get('group_name');
		
		$query=$conn->query("select group_id from groups where group_name='$group_name'");
		if($query->rowCount()>0){
			$response["error"]=true;
			$response["message"]="group name already exist";
		}else{
			if($conn->query("Insert into groups(group_name,admin_id) VALUES('$group_name','$user_id')")==TRUE){			
				$response["error"]=false;
				$response["message"]="new group created";
			}else{
				$response["error"]=true;
				$response["message"]="error occurred while creating group";
			}
		}
	}
	else{
		$response["error"]=true;
		$response["message"]="user does not exist";
	}
	$conn=null;
	echoRespnse(200,$response);
});


//link through which user can join the group
$app->post('/joingroup/:user_id',function($user_id) use ($app){
	$response=array();
	require_once dirname(__FILE__) . '../../include/db_connect.php';
	$db = new DbConnect();
    $conn = $db->connect();
	
	$user_exist=$conn->query("select user_id from users where user_id=$user_id");
	if($user_exist->rowCount()>0){
		$group_name=$app->request->get('group_name');
		$query=$conn->query("select * from groups where group_name='$group_name'");
		
		if($query->rowCount()>0){
			while($param=$query->fetch(PDO::FETCH_OBJ))
			{
				$group_id=$param->group_id;
			}
			$userjoin=$conn->query("select id from master where group_id=$group_id AND user_id=$user_id");
			if($userjoin->rowCount()>0){
				$response["error"]=true;
				$response["message"]="user already joined the group";
			}else{
				if($conn->query("Insert into master(group_id,user_id) VALUES('$group_id','$user_id')")==TRUE){
					$response["error"]=false;
					$response["message"]="group joined";
				}
				else{
					$response["error"]=true;
					$response["message"]="error occurred while joining group";
				}
			}
		}else{
			$response["error"]=true;
			$response["message"]="group does not exist";
		}
	}else{
		$response["error"]=true;
		$response["message"]="user does not exist";
	}
	$conn=null;
	echoRespnse(200,$response);
});


/**
 * Echoing json response to client
 * @param String $status_code Http response code
 * @param Int $response Json response
 */
function echoRespnse($status_code, $response) {
    $app = \Slim\Slim::getInstance();
    // Http response code
    $app->status($status_code);

    // setting response content type to json
    $app->contentType('application/json');

    echo json_encode($response);
}

$app->run();
?>
