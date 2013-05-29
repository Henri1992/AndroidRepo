<?

$databasehost = "localhost";
$databasename = "android";
$databaseusername ="root";
$databasepassword = "";

$con = mysql_connect($databasehost,$databaseusername,$databasepassword) or die(mysql_error());
mysql_select_db($databasename) or die(mysql_error());
$query = "SELECT * FROM User WHERE id >'".mysql_real_escape_string($_REQUEST['year'])."'"; 
$statementHandle = mysql_query($query);

if (mysql_errno()) { 
    header("HTTP/1.1 500 Internal Server Error");
    echo $query."\n";
    echo mysql_error(); 
}
else
{
    $rows = array();
    while($r = mysql_fetch_assoc($statementHandle))
    {
        $rows[] = $r;
    }
    print json_encode($rows);
    
    mysql_close();
}
?>