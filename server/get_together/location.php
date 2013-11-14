<?php
$testMail = "g@g.com";
$eventID = str_replace(" ","",$_POST['event_id']);
$mailID=str_replace(" ","",$_POST['mail_as_id']);//receive the username from the Android client；
$pLatitude= str_replace(" ","",$_POST['latitude']);
$pLongtitude= str_replace(" ","",$_POST['longtitude']);
$client_info = array($eventID,$mailID,$pLititude,$pLongtitude);
	if($client_info[1]==$testMail){
		echo $pLatitude;
	}
	else{
		echo "error";
	}
?>