<?php
    include"connect.php";
    
    $email = $_POST['email'];
    $matkhau = $_POST['matkhau'];
    $hoten = $_POST['hoten'];
    $diachi = $_POST['diachi'];
    $avatar = $_POST['avatar'];
    
    if(strlen($email) > 0 && strlen($matkhau) > 0 && strlen($hoten) > 0){
        $query = "INSERT INTO nguoidung(id, email, matkhau, hoten, diachi, avatar) values (null, '$email', '$matkhau', '$hoten', '$diachi', '$avatar')";
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