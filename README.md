# ỨNG DỤNG THƯƠNG MẠI ĐIỆN TỬ (MUA BÁN CÁC SẢN PHẨM CÔNG NGHỆ)
> **NGUYỄN VĂN GIÀU - Sinh Viên Khoa Công Nghệ Thông Tin - Trường Đại Học Sư Phạm TP. Hồ Chí Minh**

## I. Nền tảng triển khai

- Ứng dụng viết trên nền tảng Java và được chạy trên các thiết bị Android
<div>
  <img src="https://user-images.githubusercontent.com/75024999/126862145-419f9049-2fd4-45af-bd6b-d5830f5ba4ff.png?raw=true" height="120px" width="120px">
</div>

- Sử dụng hệ cơ sở dữ liệu MySQL & Firebase
<div>
   <img src="https://user-images.githubusercontent.com/75024999/126862148-de0a8610-352b-4d83-b8ea-6e90a4fbdf28.png?raw=true" height="105px" width="180px">
   <img src="https://user-images.githubusercontent.com/75024999/126863039-1da26608-5360-4702-b630-37ffc2b97097.png?raw=true" height="120px" width="120px">
</div> 

- Ứng dụng được thực thi trên Android Studio
<div>
  <img src="https://user-images.githubusercontent.com/75024999/126862027-d2b66656-2a73-43ec-82d1-41002c66e2fc.png?raw=true" height="120px" width="120px">
</div>
  
## II. Khởi chạy ứng dụng
- Giao diện sẽ xuất hiện như hình bên dưới sau khi mở ứng dụng:
  + ![grab-landing-page](https://github.com/vangiaurecca/ShopTech_App_Android/blob/main/demo_app.gif)
   
- Sau khi đăng nhập tài khoản thành công thì sẽ vào giao diện chính của ứng dụng:
  + ![grab-landing-page](https://github.com/vangiaurecca/ShopTech_App_Android/blob/main/dashboard_app.gif)
   
- Màn hình quản lý người dùng, hiển thị các thông tin của tài khoản đang được đăng nhập vào hệ thống bao gồm họ tên, email, địa chỉ, avatar... tại đây người dùng có thể tự do sửa đổi thông tin của mình:
  + <img src="https://user-images.githubusercontent.com/75024999/126863735-a250a9d8-176f-4d2d-a960-457e06da78df.png?raw=true" height="507px" width="240px">
   
- Tương tự có các giao diện như Điện Thoại, Macbook, Tablet, Đồng Hồ... 
  <div>
      <img src="https://user-images.githubusercontent.com/75024999/126863857-7728417a-d66d-4ac3-af48-51e2fef2bf64.png?raw=true" height="507px" width="240px">
      <img src="https://user-images.githubusercontent.com/75024999/126863865-ca5f2912-b51c-46eb-b17c-243a38ca7e95.png?raw=true" height="507px" width="240px">
  </div>
  <div>
      <img src="https://user-images.githubusercontent.com/75024999/126863869-341f2346-1bf8-486b-8d1a-0a46bf4b251a.png?raw=true" height="507px" width="240px">
      <img src="https://user-images.githubusercontent.com/75024999/126863875-8ad07dc2-a244-453d-b9cc-0a20551b13bd.png?raw=true" height="507px" width="240px">
  </div> 

- Giao diện Thông Tin Ứng Dụng hiển thị các thông tin của nhà phát triển: 
  <div>
      <img src="https://user-images.githubusercontent.com/75024999/126864733-b0bcdadd-d71d-4ba4-9576-93b310e052bb.png?raw=true" height="507px" width="240px">
      <img src="https://user-images.githubusercontent.com/75024999/126864735-bf249100-a46a-4435-9b30-5da9e183014f.png?raw=true" height="507px" width="240px">
  </div> 
- Giao diện Tin Tức Công Nghệ: Dữ liệu được đọc từ file RSS của các trang web báo nổi tiếng, tại đây người dùng có thể xem đầy đủ các tin tức liên quan đến công nghệ như 1 trình duyệt đọc báo bình thường:
  <div>
      <img src="https://user-images.githubusercontent.com/75024999/126903222-e9fc4912-f47a-4685-8a25-a0b9012c6e26.png?raw=true" height="507px" width="240px">
      <img src="https://user-images.githubusercontent.com/75024999/126903224-c7abb6b3-71f0-4c4d-b0e1-800f990d0e48.png?raw=true" height="507px" width="240px">
  </div> 

## III. Các tính năng chính của phần mềm
- #### 1. Chức năng tìm kiếm là 1 chức năng đặc biệt, ứng dụng sẽ đưa ra kết quả nhanh chóng bằng việc sử dụng Filter tích hợp tính năng Realtime Database trên FIREBASE:  
  + ![grab-landing-page](https://github.com/vangiaurecca/ShopTech_App_Android/blob/main/searching_app.gif) 

- #### 2. Bên cạnh đó còn các chức năng cơ bản như xem chi tiết sản phẩm, đặt hàng, thêm giỏ hàng, đánh giá sản phẩm và xử lý thanh toán đơn hàng:
  + ##### 2.1 Xem chi tiết sản phẩm:
    <div align="left" >
       <img src="https://user-images.githubusercontent.com/75024999/126900457-a311da14-0d0e-4fbd-9547-c010aa76bbec.png?raw=true" height="507px" width="240px">
       <img src="https://user-images.githubusercontent.com/75024999/126900351-6b4036fc-8aa8-408a-bd56-0f07c06e9db3.png?raw=true" height="507px" width="240px">
    </div> 
  + ##### 2.2 Thêm giỏ hàng: Sau khi click vào Button THÊM GIỎ HÀNG, ứng dụng sẽ xuất hiện một BottomSheet hiện lên các thông tin của sản phẩm mà khách hàng vừa thêm
    <div>
       <img src="https://user-images.githubusercontent.com/75024999/126900643-e84c7f82-5837-4b38-8579-4c209351c436.png?raw=true" height="507px" width="240px">
    </div>
    
  + ##### 2.3 Xem giỏ hàng: Tại đây người dùng có thể tăng giảm số lượng sản phẩm cần mua, click giữ vào Item sản phẩm để thực hiện xóa sản phẩm khỏi giỏ hàng
    <div>
       <img src="https://user-images.githubusercontent.com/75024999/126900818-26958a75-6a06-4477-b7b3-f8d2f102d51e.png?raw=true" height="507px" width="240px">
       <img src="https://user-images.githubusercontent.com/75024999/126900821-26235ec2-8dbe-468b-806c-2bf7f86786b7.png?raw=true" height="507px" width="240px">
       <img src="https://user-images.githubusercontent.com/75024999/126900824-8de703ec-e46f-47ec-bf15-ddd9dc9e026b.png?raw=true" height="507px" width="240px">
    </div> 

  + ##### 2.4 Đánh giá: Tại màn hình Chi Tiết Sản Phẩm, người dùng Scroll xuống dưới cùng để xem các đánh giá cũng như viết đánh giá của cá nhân:
     <div>
       <img src="https://user-images.githubusercontent.com/75024999/126900948-1be175c9-b6ea-40d3-880d-36899d814238.png?raw=true" height="507px" width="240px">
    </div>
    
  + ##### 2.5 Thanh toán: 
    + Tại màn hình Thanh Toán, người dùng cần nhập đầy đủ các thông tin cá nhân. Có 2 phương thức thanh toán cho người dùng lựa chọn
    <div>
       <img src="https://user-images.githubusercontent.com/75024999/126901123-6f1a55d5-02c6-4cc1-9183-8ae09d640727.png?raw=true" height="507px" width="240px">
       <img src="https://user-images.githubusercontent.com/75024999/126901174-d7f61c20-cd5c-46d7-9184-51345aab5f8c.png?raw=true" height="507px" width="240px">
       <img src="https://user-images.githubusercontent.com/75024999/126901187-fbb24558-c8ce-4033-a77a-873a19879fd4.png?raw=true" height="507px" width="240px">
    </div>  
    
    + Sau khi hoàn thành các thông tin trên người dùng tiến hành đặt hàng (ở đây mình sẽ demo phương thức thanh toán khi nhận hàng)
    <div>
       <img src="https://user-images.githubusercontent.com/75024999/126901426-b36db75b-33ed-456d-ba80-b04d14cf720d.png?raw=true" height="507px" width="240px">
       <img src="https://user-images.githubusercontent.com/75024999/126901432-f7a096ee-a4cf-4ac4-932b-a372a022b650.png?raw=true" height="507px" width="240px">
    </div> 
    
## IV. Nhận xét phần mềm
    
    |  #  | Ưu điểm                                               | Nhược điểm                                                |
    |-----| ----------------------------------------------------- | --------------------------------------------------------- |
    |  1  | Ứng dụng cũng tương đối hoàn thiện                    | Do chưa có nhiều kinh nghiệm nên vẫn còn 1 số thiếu sót   |
    |  2  | Giao diện bắt mắt, dễ sử dụng, thao tác đơn giản      | chúng tôi sẽ cố gắng khắc phục để ứng dụng hoàn thiện hơn |
    |  3  | Tìm kiếm và đưa ra kết quả nhanh chóng cho người dùng | và nhiều chức năng hơn…                                   |

> **Copyright by Nguyễn Văn Giàu**

