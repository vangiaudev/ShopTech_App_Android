<?php
    include "connect.php";

    $email = $_POST['email'];
    $idsanpham = $_POST['idsanpham'];
    
    $arrDanhGia = array();
    $query = "SELECT * FROM danhgia WHERE email = '$email' and idsanpham = '$idsanpham'";
    $data = mysqli_query($conn, $query);
    while($row = mysqli_fetch_assoc($data)){
        array_push($arrDanhGia, ["id" => $row['id'], "noidung" => $row['noidung'], "rating" => $row['rating'], "ngaydanhgia" => $row['ngaydanhgia'],"email" => $row['email'], "hoten" => $row['hoten'], "avatar" => $row['avatar'], "idsanpham" => $row['idsanpham']]);
    }

    echo json_encode($arrDanhGia);

?>