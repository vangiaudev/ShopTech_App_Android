<?php
    include "connect.php";

    $arrChiTietDonHang = array();
    $query = "SELECT * FROM  chitietdonhang ORDER BY madonhang DESC LIMIT 1";
    $data = mysqli_query($conn, $query);
    while($row = mysqli_fetch_assoc($data)){
        array_push($arrChiTietDonHang, ["id" => $row['id'], "madonhang" => $row['madonhang'], "tenthietbi" => $row['tenthietbi'], "giathietbi" => $row['giathietbi'], "soluongthietbi" => $row['soluongthietbi']]);
    }

    echo json_encode($arrChiTietDonHang);

?>