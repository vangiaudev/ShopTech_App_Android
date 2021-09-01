<?php
    include "connect.php";

    $emaildat = $_POST['emaildat'];

    $arrLichSu = array();
    $query = "SELECT * FROM chitietdonhang WHERE emaildat = '$emaildat'";
    $data = mysqli_query($conn, $query);
    while($row = mysqli_fetch_assoc($data)){
        array_push($arrLichSu, ["id" => $row['id'], "madonhang" => $row['madonhang'], "mathietbi" => $row['mathietbi'], "tenthietbi" => $row['tenthietbi'],"giathietbi" => $row['giathietbi'], "soluongthietbi" => $row['soluongthietbi'], "ngaydat" => $row['ngaydat'], "emaildat" => $row['emaildat']]);
    }

    echo json_encode($arrLichSu);

?>