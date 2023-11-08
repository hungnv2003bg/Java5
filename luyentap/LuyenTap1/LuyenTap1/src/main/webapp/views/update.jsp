<%--
  Created by IntelliJ IDEA.
  User: mrbom
  Date: 12/06/2023
  Time: 11:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Update</title>
</head>
<body>
<%--@elvariable id="phieuGiamGia" type=""--%>
<form:form action="/phieu-giam-gia/update/${phieuGiamGia.maPhieu}" method="post" modelAttribute="phieuGiamGia">
    <div>
        <label>Ten Phieu:</label>
        <form:input path="tenPhieu" type="text"/>
        <label>Gia Tri Giam:</label>
        <form:input path="giaTriGiam" type="text"/>
        <label>Gia Tri Toi Da:</label>
        <form:input path="giaTriGiamToiDa" type="text"/>
    </div>
    <div>
        <label>Form:</label>
        <form:input type="date" path="ngayBatDau"/>
        <label>To:</label>
        <form:input type="date" path="ngayKetThuc"/>
    </div>
    <div>
        <label>Khach Hang:</label>
        <form:select path="maKhanhHang" onchange="updateTenKhachHang(this)">
            <c:forEach items="${listKhachHang}" var="khachHang">
                <option value="${khachHang.maKhachHang}" ${khachHang.maKhachHang == phieuGiamGia.maKhanhHang ? "selected" : ""}>${khachHang.maKhachHang}</option>
            </c:forEach>
        </form:select>
        <label>Ten Khach Hang: <span id="tenKhachHang">${phieuGiamGia.tenKhachHang}</span></label>
    </div>
    <button type="submit">Update</button>
</form:form>
<script>
    function updateTenKhachHang(selectElement) {
        var selectedMaKhachHang = selectElement.value;
        var tenKhachHangLabel = document.getElementById("tenKhachHang");

        <c:forEach items="${listKhachHang}" var="khachHang">
        if ("${khachHang.maKhachHang}" === selectedMaKhachHang) {
            tenKhachHangLabel.innerText = "${khachHang.tenKhachHang}";
        }
        </c:forEach>
    }
</script>
</body>
</html>
