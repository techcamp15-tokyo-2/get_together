<?php
$dbhost = "localhost:3306";
$dbuser = "root"; // user name
$dbpass = ""; // password
$dbname = "get_together"; //database name
$cn = mysql_connect($dbhost,$dbuser,$dbpass) or die("connect error");
@mysql_select_db($dbname)or die("db error");
mysql_query("set names 'UTF-8'");
?>