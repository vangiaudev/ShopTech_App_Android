<?php
    include"connect.php";
    $json = $_POST['json'];

    $data = json_decode($json, true);
    foreach ($data as $value) {
        $madonhang = $value['madonhang'];
        $mathietbi= $value['mathietbi'];
        $tenthietbi = $value['tenthietbi'];
        $giathietbi = $value['giathietbi'];
        $soluongthietbi = $value['soluongthietbi'];
        $ngaydat = $value['ngaydat'];
        $emaildat = $value['emaildat'];

        $query = "INSERT INTO chitietdonhang (id, madonhang, mathietbi, tenthietbi, giathietbi, soluongthietbi, ngaydat, emaildat) VALUES (null, '$madonhang', '$mathietbi', '$tenthietbi', '$giathietbi', '$soluongthietbi', '$ngaydat', '$emaildat')";
        $db = mysqli_query($conn, $query);
    
    }
    if($db){
        echo "1";
    }
    else{
        echo "0";
    }
?>