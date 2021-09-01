<?php

    include "connect.php";
    $query = "SELECT * FROM loaithietbi";
    $data = mysqli_query($conn, $query);

    class LoaiThietBi{
        function LoaiThietBi($id, $ten, $hinhanh){
            $this->id = $id;
            $this->ten = $ten;
            $this->hinhanh = $hinhanh;
        }
    }

    $arrLoaiThietBi = array();

    while($row = mysqli_fetch_assoc($data)){
        array_push($arrLoaiThietBi, ["id" => $row['id'], "ten" => $row['tenloai'], "hinhanh" => $row['hinhanhloai']]);
    }
    echo json_encode($arrLoaiThietBi);
?>