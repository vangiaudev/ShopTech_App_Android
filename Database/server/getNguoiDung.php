<?php
    include "connect.php";

    $arrNguoiDung = array();
    $query = "SELECT * FROM nguoidung";
    $data = mysqli_query($conn, $query);
    while($row = mysqli_fetch_assoc($data)){
        array_push($arrNguoiDung, ["id" => $row['id'], "email" => $row['email'], "matkhau" => $row['matkhau'], "hoten" => $row['hoten'], "diachi" => $row['diachi'], "avatar" => $row['avatar']]);
    }
    echo json_encode($arrNguoiDung);

?>