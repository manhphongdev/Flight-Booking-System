<%-- 
    Document   : booking
    Created on : May 13, 2025, 7:23:52 AM
    Author     : manhphong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Booking Page</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="CSS/booking.css"/>
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>

    <div class="container container-booking mt-5">
        <h2 class="text-center mb-4">Đặt vé máy bay</h2>
        <div class="d-flex justify-content-center mb-4">
            <button class="tab-btn active me-2" onclick="showTab('roundTrip')">Khứ hồi</button>
            <button class="tab-btn me-2" onclick="showTab('oneWay')">Một chiều</button>
            <button class="tab-btn" onclick="showTab('multiCity')">Nhiều thành phố</button>
        </div>

        <div id="roundTrip" class="tab-content">
            <div class="row mb-3">
                <div class="col-md-6"><label class="form-label">Điểm đi</label><div class="input-group"><span class="input-group-text"><i class="bi bi-airplane"></i></span><input type="text" class="form-control" placeholder="Nhập điểm đi"></div></div>
                <div class="col-md-6"><label class="form-label">Điểm đến</label><div class="input-group"><span class="input-group-text"><i class="bi bi-airplane"></i></span><input type="text" class="form-control" placeholder="Nhập điểm đến"></div></div>
            </div>
            <div class="row mb-3">
                <div class="col-md-6"><label class="form-label">Ngày đi</label><input type="date" class="form-control"></div>
                <div class="col-md-6"><label class="form-label">Ngày về</label><input type="date" class="form-control"></div>
            </div>
            <div class="row mb-3">
                <div class="col-md-4"><label class="form-label">Người lớn</label><select class="form-select"><option>1</option><option>2</option><option>3</option><option>4</option></select></div>
                <div class="col-md-4"><label class="form-label">Trẻ em</label><select class="form-select"><option>0</option><option>1</option><option>2</option><option>3</option></select></div>
                <div class="col-md-4"><label class="form-label">Em bé</label><select class="form-select"><option>0</option><option>1</option><option>2</option><option>3</option></select></div>
            </div>
            <div class="row mb-4">
                <div class="col-md-12"><label class="form-label">Hạng vé</label><select class="form-select"><option>Phổ thông</option><option>Thương gia</option><option>Hạng nhất</option></select></div>
            </div>
            <div class="text-center"><button class="btn btn-primary">Tìm chuyến bay</button></div>
        </div>

        <div id="oneWay" class="tab-content" style="display: none;">
            <div class="row mb-3">
                <div class="col-md-6"><label class="form-label">Điểm đi</label><div class="input-group"><span class="input-group-text"><i class="bi bi-airplane"></i></span><input type="text" class="form-control" placeholder="Nhập điểm đi"></div></div>
                <div class="col-md-6"><label class="form-label">Điểm đến</label><div class="input-group"><span class="input-group-text"><i class="bi bi-airplane"></i></span><input type="text" class="form-control" placeholder="Nhập điểm đến"></div></div>
            </div>
            <div class="row mb-3">
                <div class="col-md-12"><label class="form-label">Ngày đi</label><input type="date" class="form-control"></div>
            </div>
            <div class="row mb-3">
                <div class="col-md-4"><label class="form-label">Người lớn</label><select class="form-select"><option>1</option><option>2</option><option>3</option><option>4</option></select></div>
                <div class="col-md-4"><label class="form-label">Trẻ em</label><select class="form-select"><option>0</option><option>1</option><option>2</option><option>3</option></select></div>
                <div class="col-md-4"><label class="form-label">Em bé</label><select class="form-select"><option>0</option><option>1</option><option>2</option><option>3</option></select></div>
            </div>
            <div class="row mb-4">
                <div class="col-md-12"><label class="form-label">Hạng vé</label><select class="form-select"><option>Phổ thông</option><option>Thương gia</option><option>Hạng nhất</option></select></div>
            </div>
            <div class="text-center"><button class="btn btn-primary">Tìm chuyến bay</button></div>
        </div>

        <div id="multiCity" class="tab-content" style="display: none;">
            <div id="flightRows">
                <div class="flight-row">
                    <div class="row mb-3">
                        <div class="col-md-6"><label class="form-label">Điểm đi</label><div class="input-group"><span class="input-group-text"><i class="bi bi-airplane"></i></span><input type="text" class="form-control" placeholder="Nhập điểm đi"></div></div>
                        <div class="col-md-6"><label class="form-label">Điểm đến</label><div class="input-group"><span class="input-group-text"><i class="bi bi-airplane"></i></span><input type="text" class="form-control" placeholder="Nhập điểm đến"></div></div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-12"><label class="form-label">Ngày đi</label><input type="date" class="form-control"></div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-12 text-end"><a href="#" class="remove-flight-btn" onclick="removeFlightRow(event)">Xóa</a></div>
                    </div>
                </div>
            </div>
            <div class="text-end mb-3"><a href="#" class="add-flight-btn" onclick="addFlightRow(event)">+ Thêm chuyến</a></div>
            <div class="row mb-3">
                <div class="col-md-4"><label class="form-label">Người lớn</label><select class="form-select"><option>1</option><option>2</option><option>3</option><option>4</option></select></div>
                <div class="col-md-4"><label class="form-label">Trẻ em</label><select class="form-select"><option>0</option><option>1</option><option>2</option><option>3</option></select></div>
                <div class="col-md-4"><label class="form-label">Em bé</label><select class="form-select"><option>0</option><option>1</option><option>2</option><option>3</option></select></div>
            </div>
            <div class="row mb-4">
                <div class="col-md-12"><label class="form-label">Hạng vé</label><select class="form-select"><option>Phổ thông</option><option>Thương gia</option><option>Hạng nhất</option></select></div>
            </div>
            <div class="text-center"><button class="btn btn-primary">Tìm chuyến bay</button></div>
        </div>
    </div>

    <script>
        // Lưu trạng thái active của tab
        let activeTabId = 'roundTrip'; // Mặc định tab khứ hồi được active
        
        function showTab(tabId) {
            // Ẩn tất cả các tab
            document.querySelectorAll('.tab-content').forEach(tab => tab.style.display = 'none');
            
            // Hiển thị tab được chọn
            document.getElementById(tabId).style.display = 'block';
            
            // Xóa class active khỏi tất cả các nút tab
            document.querySelectorAll('.tab-btn').forEach(btn => btn.classList.remove('active'));
            
            // Thêm class active vào nút tab vừa được click
            document.querySelector(`button[onclick="showTab('${tabId}')"]`).classList.add('active');
            
            // Lưu trạng thái tab active hiện tại
            activeTabId = tabId;
        }

        function addFlightRow(event) {
            event.preventDefault();
            const flightRows = document.getElementById('flightRows');
            const newRow = document.createElement('div');
            newRow.className = 'flight-row';
            newRow.innerHTML = `
                <div class="row mb-3">
                    <div class="col-md-6"><label class="form-label">Điểm đi</label><div class="input-group"><span class="input-group-text"><i class="bi bi-airplane"></i></span><input type="text" class="form-control" placeholder="Nhập điểm đi"></div></div>
                    <div class="col-md-6"><label class="form-label">Điểm đến</label><div class="input-group"><span class="input-group-text"><i class="bi bi-airplane"></i></span><input type="text" class="form-control" placeholder="Nhập điểm đến"></div></div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-12"><label class="form-label">Ngày đi</label><input type="date" class="form-control"></div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-12 text-end"><a href="#" class="remove-flight-btn" onclick="removeFlightRow(event)">Xóa</a></div>
                </div>
            `;
            flightRows.appendChild(newRow);
        }

        function removeFlightRow(event) {
            event.preventDefault();
            event.target.closest('.flight-row').remove();
        }
        
        // Đảm bảo rằng trạng thái active được lưu sau khi tải trang
        window.addEventListener('load', function() {
            // Đặt tab mặc định là active
            showTab(activeTabId);
        });
    </script>

    <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>