<?php
include ("conn.php");//connect the database
$eventID = str_replace(" ","",$_POST['event_id']);
$mailID=str_replace(" ","",$_POST['mail_as_id']);//receive the username from the Android client；
$pLatitude= str_replace(" ","",$_POST['latitude']);
$pLongtitude= str_replace(" ","",$_POST['longtitude']);
//$sql1 = "select * from location_info where event_id = 'eventID' and mail_id = 'mailID'";
//$sql2 = "insert into location_info(event_id,mail_id,latitude,longtitude) values('$eventID','$mailID','$pLatitude','$pLongtitude')";
$sql3 ="update location_info set latitude = '$pLatitude', longtitude = '$pLongtitude' where event_id = '$eventID' and mail_id='$mailID'";
$query=mysql_query($sql3);
// $rs = mysql_fetch_array($query);
// if(is_array($rs)){
	// $query=mysql_query($sql3);
// }
// else {
	// $query=mysql_query($sql2);
// }
	
$sql="select event_id,mail_id,latitude,longtitude from location_info where event_id='$eventID'";
$result=mysql_query($sql);
            $res=Array();
            while($rows=mysql_fetch_assoc($result))
            {
                $res[]=$rows;
            }
        echo json_encode($res);	
?>