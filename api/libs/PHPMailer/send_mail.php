<?php
require 'PHPMailerAutoload.php';

$mail = new PHPMailer;

$mail->SMTPDebug = 3;                               // Enable verbose debug output

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
$mail->addAddress('rishabhahuja279@gmail.com', 'Rishabh');     // Add a recipient
//$mail->addReplyTo('info@example.com', 'Information');
//$mail->addCC('cc@example.com');
//$mail->addBCC('harsh.gupta@ves.ac.in');
//$mail->addAttachment('/var/tmp/file.tar.gz');         // Add attachments
//$mail->addAttachment('/tmp/image.jpg', 'new.jpg');    // Optional name
$mail->isHTML(true);                                  // Set email format to HTML

$mail->Subject = 'OTP';
$mail->Body    = 'Dear Rishabh,<br><br>Your One Time Password for Grouplex is <b>68319</b><br><br>Warm Regards,<br>Team Grouplex';
$mail->AltBody = 'OTP';

if(!$mail->send()) {
    echo 'Message could not be sent.';
    echo 'Mailer Error: ' . $mail->ErrorInfo;
} else {
    echo 'Message has been sent';
}
?>
