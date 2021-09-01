<?php
    include "connect.php";

    $arrDonHang = array();
    $query = "SELECT * FROM donhang ORDER BY id DESC LIMIT 1";
    $data = mysqli_query($conn, $query);
    while($row = mysqli_fetch_assoc($data)){
        array_push($arrDonHang, ["id" => $row['id'], "tenkhachhang" => $row['tenkhachhang'], "sodienthoai" => $row['sodienthoai'], "diachi" => $row['diachi']]);
    }
    echo json_encode($arrDonHang);

?>