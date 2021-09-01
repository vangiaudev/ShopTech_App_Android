<?php
    include "connect.php";
    $page = $_GET['page'];
    $idsp = $_POST['idthietbi'];
    $space = 5;
    $limit = ($page - 1) * $space;


    $arrThietBi = array();
    $query = "SELECT * FROM thietbi WHERE idthietbi = $idsp ORDER BY giagoc ASC LIMIT $limit, $space";
    $data = mysqli_query($conn, $query);
    while($row = mysqli_fetch_assoc($data)){
        array_push($arrThietBi, ["id" => $row['id'], "tenthietbi" => $row['tenthietbi'], "giagoc" => $row['giagoc'],"giatri" => $row['giatri'], "hinhanhthietbi" => $row['hinhanhthietbi'], "mota" => $row['mota'], "idthietbi" => $row['idthietbi'], "hinhanhchitiet2" => $row['hinhanhchitiet2'], "hinhanhchitiet3" => $row['hinhanhchitiet3']]);
    }

    echo json_encode($arrThietBi);

?>