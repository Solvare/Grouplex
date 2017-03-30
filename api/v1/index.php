<?php

error_reporting(E_ALL);

require '.././libs/Slim/Slim.php';
require dirname(__FILE__) . '../../include/salt_pass.php';
require dirname(__FILE__) . '../../include/db_connect.php';

\Slim\Slim::registerAutoloader();

$app = new \Slim\Slim();

/*------------------------------------------------------------------------------------*/
//  1. fetching all groups of a particular user
/*------------------------------------------------------------------------------------*/
 
$app->get('/:user_id/groups', function($user_id)
{
    $response = array();

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
        $stmt = $conn->query("SELECT group_name,admin_id,image FROM master,groups WHERE master.user_id = $user_id AND groups.group_id = master.group_id");
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
                $tmp["name"] = $row["group_name"];
                $tmp["level"] = ($user_id == $row["admin_id"] ? "admin" : "member");
                //$tmp["image"] = "http://"."$_SERVER[HTTP_HOST]"."/grouplex/api/images/".$row["image"];
                array_push($response["groups"], $tmp);
            }
        }  
    }
    $exist = null;
    $stmt = null;
    $conn = null;
    echoRespnse(200, $response);
});

/*------------------------------------------------------------------------------------*/
//  2. fetching all messages of a particular group
/*------------------------------------------------------------------------------------*/

$app->get('/:group_id/messages', function($group_id)
{
    $response = array();
 
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
                array_push($response["messages"], $row["chat"]);
            }
        }  
    }
    $exist = null;
    $stmt = null;
    $conn = null;

    echoRespnse(200, $response);
});

/*------------------------------------------------------------------------------------*/
//  3. fetching all members of a particular group
/*------------------------------------------------------------------------------------*/

$app->get('/:group_id/members', function($group_id)
{
    $response = array();
  
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
            array_push($response["members"], $row["full_name"]);
        }
        
    }
    $exist = null;
    $stmt = null;
    $conn = null;

    echoRespnse(200, $response);
});

/*------------------------------------------------------------------------------------*/
//  4. posting message to a particular group
/*------------------------------------------------------------------------------------*/

$app->post('/message', function() use ($app)
{   
    global $app;
    $response = array();
   
    $db = new DbConnect();
    $conn = $db->connect();

    $group_id = $app->request->post('group_id');
    $user_id = $app->request->post('user_id');

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
            $chat = $app->request->post('chat');
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

/*------------------------------------------------------------------------------------*/
//  5. searching for a group
/*------------------------------------------------------------------------------------*/

$app->get('/search/:query', function($query)
{
    global $app;
    $response = array();
   
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
            array_push($response["groups"], $row["group_name"]);
        }
     
    }
    
    $stmt = null;
    $conn = null;
    
    echoRespnse(200, $response);
});

/*------------------------------------------------------------------------------------*/
//  6. user registration
/*------------------------------------------------------------------------------------*/

$app->post('/user/register',function() use ($app)
{
    global $app;
    $response=array();

    $db = new DbConnect();
    $conn = $db->connect();
    
    $email=$app->request->post('email');
    $password=$app->request->post('password');
    $full_name=$app->request->post('full_name');
    
    if($email == null || $password == 'gow' || $full_name == null)
    {
        $response["error"]=true;
        $response["message"]="Insufficient Info";
    }
    else
    {
        $user_exist=$conn->query("select user_id from users where email='$email'");
        if($user_exist->rowCount()>0)
        {
            $response["error"]=true;
            $response["message"]="The user is already registered";
        }
        else
        {       
            $pass_hash = PassHash::hash($password);
            
            if($conn->query("INSERT INTO users(email,password,full_name,gcm_reg_id) VALUES('$email','$pass_hash','$full_name','')")==TRUE)
            {
                $response["error"]=false;
                $response["message"]="New user created";
            }
            else
            {
                $response["error"]=true;
                $respomse["message"]="An error occurred while registration";
            }
        }
    }
    
    $conn = null;
    $user_exist=null;
    echoRespnse(200,$response);
});

/*------------------------------------------------------------------------------------*/
//  7. user login
/*------------------------------------------------------------------------------------*/

$app->post('/user/login',function() use ($app)
{
    $response=array();

    $db = new DbConnect();
    $conn = $db->connect();
    
    $email=$app->request->post('email');
    $password=$app->request->post('password');
    
    if($email == 'gow' || $password == 'gow')
    {
        $response["error"]=true;
        $response["message"]="Insufficient Info";
    }
    else
    {
        $user_exist=$conn->query("select user_id,email,full_name,password as passhash from users where email='$email'");
        if($user_exist->rowCount()==1)
        {                       
            $row = $user_exist->fetch();        
            $passhash = $row['passhash'];
            if(PassHash::check_password($passhash, $password))
            {
                $response["error"]=false;
                $response["message"]="user successfully logged in";
                $response["user_id"]=$row['user_id'];
                $response["email"]=$row['email'];
                $response["full_name"]=$row['full_name'];
            }
            else
            {
                $response["error"]=true;
                $response["message"]="incorrect password";  
            }
        }
        else
        {
            $response["error"]=true;
            $response["message"]="user does not exist";
        }
    }
    $conn = null;
    $user_exist = null;

    echoRespnse(200,$response);
});

/*------------------------------------------------------------------------------------*/
//  8. creating a new group
/*------------------------------------------------------------------------------------*/

$app->post('/creategroup',function() use ($app)
{
    $response=array();
    
    $db = new DbConnect();
    $conn = $db->connect();
    
    $group_name=$app->request->post('group_name');
    $user_id=$app->request->post('user_id');

    $user_exist=$conn->query("select user_id from users where user_id=$user_id");
    if($user_exist->rowCount()>0){
        
        $query=$conn->query("select group_id from groups where group_name='$group_name'");
        if($query->rowCount()>0){
            $response["error"]=true;
            $response["message"]="group name already exist";
        }else{
            $group_table = $conn->query("Insert into groups(group_name,admin_id) VALUES('$group_name','$user_id')");
            $group_id = $conn->lastInsertId();
            $master_table = $conn->query("Insert into master(group_id,user_id) VALUES('$group_id','$user_id')");
            if($group_table == TRUE && $master_table == TRUE){         
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
    $user_exist=null;
    $query=null;
    $group_table=null;
    $master_table=null;
    echoRespnse(200,$response);
});

/*------------------------------------------------------------------------------------*/
//  9. joining a group
/*------------------------------------------------------------------------------------*/

$app->post('/joingroup',function() use ($app)
{
    $response=array();

    $db = new DbConnect();
    $conn = $db->connect();
    
    $group_id=$app->request->post('group_id');
    $user_id=$app->request->post('user_id');

    $user_exist=$conn->query("select user_id from users where user_id=$user_id");
    if($user_exist->rowCount()>0){
        $query=$conn->query("select * from groups where group_id=$group_id");
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
    $user_exist=null;
    $query=null;
    $userjoin=null;
    echoRespnse(200,$response);
});

/*------------------------------------------------------------------------------------*/
//  10. Upload Group Image
/*------------------------------------------------------------------------------------*/

$app->post('/image', function() use ($app)
{   
    /*
    echo "here";
    global $app;
    $response = array();
   
    $db = new DbConnect();
    $conn = $db->connect();

    $group_id = $app->request->post('group_id');
    $user_id = $app->request->post('user_id');

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
            $response["message"] = "The user is not authorised to upload image of this group";
        }
        else if($stmt->rowCount()==1)
        {

            echo "here";
            
                $target_dir = "http://"."$_SERVER[HTTP_HOST]"."/grouplex/api/images/";
                $target_file = $target_dir . basename($_FILES["fileToUpload"]["name"]);
                echo $target_file;
                $uploadOk = 1;
                $imageFileType = pathinfo($target_file,PATHINFO_EXTENSION);
            
                // Check file size
                if ($_FILES["fileToUpload"]["size"] > 500000) {
                    $response["message1"] = "Sorry, your file is too large";
                    $uploadOk = 0;
                }
                
                // Allow certain file formats
                if($imageFileType != "jpg" && $imageFileType != "png" && $imageFileType != "jpeg"
                && $imageFileType != "gif" ) {
                    $response["message2"] = "Sorry, only JPG, JPEG, PNG & GIF files are allowed";
                    $uploadOk = 0;
                }
                // Check if $uploadOk is set to 0 by an error
                if ($uploadOk == 0) {
                    $response["error"] = true;
                // if everything is ok, try to upload file
                } 
                else {
                    if (move_uploaded_file($_FILES["fileToUpload"]["tmp_name"], $target_file)) {
                        $response["error"] = false;
                        $response["message3"] = "The file ". basename( $_FILES["fileToUpload"]["name"]). " has been uploaded."
                    } else {
                        $response["error"] = true;
                        $response["message3"] = "Sorry, there was an error uploading your file";
                    }
                }
            
            $insrt = $conn->exec("INSERT INTO groups(image) VALUES('image.jpg')");
            
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
    */
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