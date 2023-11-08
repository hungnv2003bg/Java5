<%--
  Created by IntelliJ IDEA.
  User: mrbom
  Date: 12/06/2023
  Time: 3:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Index</title>
</head>
<body>
<h1>Home</h1>
<%--@elvariable id="phieuGiamGia" type="java"--%>
<form:form action="/phieu-giam-gia/search" method="post" modelAttribute="phieuGiamGia">
    <label>Form:</label>
    <form:input type="date" path="ngayBatDau"/>
    <label>To:</label>
    <form:input type="date" path="ngayKetThuc"/>
    <label>Khach Hang:</label>
    <form:select path="maKhanhHang" onchange="updateTenKhachHang(this)">
        <c:forEach items="${listKhachHang}" var="khachHang">
            <option value="${khachHang.maKhachHang}">${khachHang.maKhachHang}</option>
        </c:forEach>
    </form:select>
    <label>Ten Khach Hang: <span id="tenKhachHang"></span></label>
    <button type="submit">Search</button>
</form:form>

<table>
    <thead>
    <tr>
        <th>#</th>
        <th>ma</th>
        <th>ten phieu</th>
        <th>ngay bat dau</th>
        <th>ngay ket thuc</th>
        <th>gia tri giam</th>
        <th>trang thai</th>
        <th>ten khach hang</th>
        <th>action</th>
    </tr>

    </thead>
    <tbody>
    <c:forEach items="${list.content}" var="phieuGiamGia" varStatus="status">
        <tr>
            <td>${status.index + 1}</td>
            <td>${phieuGiamGia.maPhieu}</td>
            <td>${phieuGiamGia.tenPhieu}</td>
            <td>${phieuGiamGia.ngayBatDau}</td>
            <td>${phieuGiamGia.ngayKetThuc}</td>
            <td>${phieuGiamGia.giaTriGiam}</td>
            <td>${phieuGiamGia.trangThai == 0 ? "Ket thuc":"Dang hoat dong"}</td>
            <td>${phieuGiamGia.nguoiSoHuu.tenKhachHang}</td>
            <td>
                <a href="/phieu-giam-gia/edit/${phieuGiamGia.maPhieu}">Edit</a>
                <a href="/phieu-giam-gia/delete/${phieuGiamGia.maPhieu}">Delete</a>
        </tr>
    </c:forEach>
    </tbody>
</table>
<nav>
    <ul >
        <c:forEach begin="0" end="${list.totalPages - 1}" varStatus="loop">
            <li>
                <a href="${link}?page=${loop.index}">
                        ${loop.index + 1}
                </a>
            </li>
        </c:forEach>
    </ul>
</nav>
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
