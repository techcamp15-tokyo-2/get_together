<?php
include ("conn.php");//connect the database
$usermail=str_replace(" ","",$_POST['mail']);//receive the username from the Android client；
$sql="select * from user_for_login where mail='$usermail'";
$query=mysql_query($sql);
$rs = mysql_fetch_array($query);
	if(is_array($rs)){
		if($_POST['pwd']==$rs['password']){
			echo $usermail;
		}
		else{
			echo "error";
		}
	}
?>