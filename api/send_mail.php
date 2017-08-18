<?php

//http://alpha2zeta.tech/php-email-tutorial.php

require 'libs/PHPMailer/PHPMailerAutoload.php';

$mail = new PHPMailer;

$mail->SMTPDebug = 0;                               // Enable verbose debug output

$mail->isSMTP();
$mail->Host = 'smtp.gmail.com';    // Set mailer to use SMTP // Specify main and backup SMTP servers
$mail->SMTPAuth = true;                               // Enable SMTP authentication
$mail->Username = 'solvesoftware@gmail.com';                 // SMTP username
$mail->Password = 'solvare3536';                           // SMTP password
$mail->SMTPSecure = 'tls';                            // Enable TLS encryption, `ssl` also accepted
//$mail->SMTPSecure = false; if not encription, uncomment this and the line below
//$mail->SMTPAutoTLS = false;
$mail->Port = 587;                                    // TCP port to connect to

$mail->setFrom('solvesoftware@gmail.com', 'Grouplex');
$mail->addAddress($_POST["email"], $_POST["name"]);     // Add a recipient

//$mail->addReplyTo('info@example.com', 'Information');
//$mail->addCC('cc@example.com');
//$mail->addBCC('harsh.gupta@ves.ac.in');
//$mail->addAttachment('/var/tmp/file.tar.gz');         // Add attachments
//$mail->addAttachment('/tmp/image.jpg', 'new.jpg');    // Optional name

$mail->isHTML(true);                                  // Set email format to HTML

$mail->Subject = 'OTP';
$mail->Body    = 'Dear '.$_POST["name"].',<br><br>Your One Time Password for Grouplex is <b>68319</b>.<br>Please do not reply to this mail.<br><br>Warm Regards,<br>Team Grouplex';
$mail->AltBody = 'OTP';

$response = array();
if(!$mail->send())
{
	$response["error"] = true;
	$response["message"] = "Email could not be sent";	    
	//echo 'Mailer Error: ' . $mail->ErrorInfo;
}
else
{
 	$response["error"] = false;
	$response["message"] = "Email sent";
}
echo json_encode($response);
?>
