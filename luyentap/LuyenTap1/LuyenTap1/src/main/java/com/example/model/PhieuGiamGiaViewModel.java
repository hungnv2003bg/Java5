package com.example.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.ScriptAssert;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
@Component
public class PhieuGiamGiaViewModel {
    private String maPhieu;
    @NotBlank(message = "Tên phiếu không được để trống")
    @Size(max = 20, message = "Tên phiếu không được quá 20 ký tự")
    private String tenPhieu;
    @NotBlank(message = "Giá trị giảm không được để trống")
    private BigDecimal giaTriGiam;
    @NotBlank(message = "Giá trị giảm tối đa không được để trống")
    private BigDecimal giaTriGiamToiDa;
    @NotNull(message = "Ngày bắt đầu không được để trống")
    private Date ngayBatDau;
    @NotNull(message = "Ngày kết thúc không được để trống")
    private Date ngayKetThuc;
    private Long maKhanhHang;
    private String tenKhachHang;
}
