<%--
  Created by IntelliJ IDEA.
  User: mrbom
  Date: 15/06/2023
  Time: 11:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/khach-hang/search" method="post">
    <label>Search</label>
    <input name="search" type="text">
    <select name="maHang">
        <c:forEach var="hangKhacHang" items="${listHangKhachHang}">
            <option value="${hangKhacHang.id}">${hangKhacHang.tenHang}</option>
        </c:forEach>
    </select>
    <button type="submit">Search</button>
</form>
<span>${thongBao}</span>


<%--@elvariable id="khachHang" type="java"--%>
<form:form action="/khach-hang/add" method="post" modelAttribute="khachHang">
    <div>
        <label>Ma Khach Hang</label>
        <form:input path="id" type="text"></form:input>
        <form:errors path="id"></form:errors>
    </div>
    <div>
        <label>Ma Hang</label>
        <form:select path="hangKhachHang" onchange="getTenHang(this)">
            <c:forEach var="hangKhacHang" items="${listHangKhachHang}">
                <option value="${hangKhacHang.id}">${hangKhacHang.id}</option>
            </c:forEach>
        </form:select>

        <label>Ten Hang: <span id="tenHang"></span></label>
    </div>
    <div>
        <label>Ten Khach Hang</label>
        <form:input path="tenKhachHang" type="text"></form:input>
        <form:errors path="tenKhachHang"></form:errors>
    </div>
    <div>
        <label>SDT Khach Hang</label>
        <form:input path="soDienThoai" type="number"></form:input>
        <form:errors path="soDienThoai"></form:errors>

    </div>
    <div>
        <label>Gioi Tinh Khach Hang</label>
        <input type="checkbox" value="true" name="gioiTinh" checked>
        <label>Nam</label>
        <input type="checkbox" value="false" name="gioiTinh">
        <label>Nu</label>
    </div>
    <div>
        <button type="submit">Add</button>
    </div>
</form:form>


<table>
    <thead>
    <tr>
        <th>#</th>
        <th>Ten</th>
        <th>Gioi Tinh</th>
        <th>SDT</th>
        <th>Ten Hang</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="khachHang" items="${list.content}">
        <tr>
            <td>${khachHang.id}</td>
            <td>${khachHang.tenKhachHang}</td>
            <td>${khachHang.gioiTinh == true ? "Nam" : "Nu"}</td>
            <td>${khachHang.soDienThoai}</td>
            <td>${khachHang.hangKhachHang.tenHang}</td>
            <td>
                <a href="/khach-hang/delete/${khachHang.id}"
                   onclick="return confirm('ban co muon xoa khong?')">Remove</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<nav>
    <ul>
        <c:forEach begin="0" end="${list.totalPages - 1}" varStatus="loop">
            <li>
                <a href="/khach-hang/hien-thi?page=${loop.index}">${loop.index + 1}</a>
            </li>
        </c:forEach>
    </ul>
</nav>
<script>
    function getTenHang(selectElement) {
        var maHang = selectElement.value;
        var tenHang = document.getElementById("tenHang");
        <c:forEach var="hangKhacHang" items="${listHangKhachHang}">
        if ("${hangKhacHang.id}" === maHang) {
            tenHang.innerText = "${hangKhacHang.tenHang}";
        }
        </c:forEach>
    }
</script>
</body>
</html>
