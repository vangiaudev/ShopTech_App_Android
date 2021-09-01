<?php
    include"connect.php";
    $tenkh = $_POST['tenkhanhhang'];
    $sodt = $_POST['sodienthoai'];
    $diachi = $_POST['diachi'];
    if(strlen($tenkh) > 0 && strlen($sodt) > 0 && strlen($diachi) > 0){
        $query = "INSERT INTO donhang(id, tenkhachhang, sodienthoai, diachi) values (null, '$tenkh', '$sodt', '$diachi')";
        if(mysqli_query($conn, $query)){
            $iddonhang = $conn->insert_id;
            echo $iddonhang;
        }
        else{
            echo "error";
        }
    }
    else{
        echo "checkdata";
    }
?>