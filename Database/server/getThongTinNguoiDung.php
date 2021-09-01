<?php
    include "connect.php";
    
    $page = $_GET['page'];
    $email = $_POST['email'];
    $space = 5;
    $limit = ($page - 1) * $space;

    $arrNguoiDung = array();
    $query = "SELECT * FROM nguoidung WHERE email = $email LIMIT $limit, $space";
    $data = mysqli_query($conn, $query);
    while($row = mysqli_fetch_assoc($data)){
        array_push($arrNguoiDung, ["id" => $row['id'], "email" => $row['email'], "matkhau" => $row['matkhau'], "hoten" => $row['hoten'], "diachi" => $row['diachi'], "avatar" => $row['avatar']]);
    }
    echo json_encode($arrNguoiDung);
    

?>
