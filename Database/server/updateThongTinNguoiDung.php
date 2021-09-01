<?php
    include"connect.php";
    
    $email = $_POST['email'];
    $matkhau = $_POST['matkhau'];
    $hoten = $_POST['hoten'];
    $diachi = $_POST['diachi'];
    $avatar = $_POST['avatar'];
    
    if(strlen($hoten) > 0 || strlen($diachi) > 0 || strlen($matkhau) > 0){
        $query = "UPDATE nguoidung SET matkhau = '$matkhau', hoten = '$hoten', diachi = '$diachi', avatar = '$avatar' WHERE email = '$email'";
        if(mysqli_query($conn, $query)){
            echo "success";
        }
        else{
            echo "error";
        }
    }
    else{
        echo "checkdata";
    }
?>