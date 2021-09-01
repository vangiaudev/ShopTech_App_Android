<?php
    include"connect.php";
    
    $noidung = $_POST['noidung'];
    $rating = $_POST['rating'];
    $ngaydanhgia = $_POST['ngaydanhgia'];
    $email = $_POST['email'];
    $hoten = $_POST['hoten'];
    $avatar = $_POST['avatar'];
    $idsanpham = $_POST['idsanpham'];


    
    if(strlen($noidung) > 0 && strlen($email) > 0 && strlen($hoten) > 0){
        $query = "INSERT INTO danhgia(id, noidung, rating, ngaydanhgia, email, hoten, avatar, idsanpham) values (null, '$noidung', '$rating', '$ngaydanhgia', '$email', '$hoten', '$avatar', '$idsanpham')";
        if(mysqli_query($conn, $query)){
            $iddanhgia = $conn->insert_id;
            echo $iddanhgia;
        }
        else{
            echo "error";
        }
    }
    else{
        echo "checkdata";
    }
?>