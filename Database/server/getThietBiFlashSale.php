<?php
    include "connect.php";

    $arrThietBiMoiNhat = array();
    $query = "SELECT * FROM  thietbi ORDER BY id ASC LIMIT 10";
    $data = mysqli_query($conn, $query);
    while($row = mysqli_fetch_assoc($data)){
        array_push($arrThietBiMoiNhat, ["id" => $row['id'], "tenthietbi" => $row['tenthietbi'], "giagoc" => $row['giagoc'],"giatri" => $row['giatri'], "hinhanhthietbi" => $row['hinhanhthietbi'], "mota" => $row['mota'], "idthietbi" => $row['idthietbi'], "hinhanhchitiet2" => $row['hinhanhchitiet2'], "hinhanhchitiet3" => $row['hinhanhchitiet3']]);
    }

    echo json_encode($arrThietBiMoiNhat);

?>