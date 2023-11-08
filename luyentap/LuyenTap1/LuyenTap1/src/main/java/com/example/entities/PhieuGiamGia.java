package com.example.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "PhieuGiamGia")
@Component
public class PhieuGiamGia {
    @Id
    @Size(max = 10)
    @Column(name = "MaPhieu", nullable = false, length = 10)
    private String maPhieu;

    @Size(max = 20, message = "Tên phiếu không được quá 20 ký tự")
    @Nationalized
    @Column(name = "TenPhieu", length = 20)
    private String tenPhieu;

    @Column(name = "NgayBatDau")
    private Date ngayBatDau;

    @Column(name = "NgayKetThuc")
    private Date ngayKetThuc;

    @Column(name = "GiaTriGiam")
    private BigDecimal giaTriGiam;

    @Column(name = "GiaTriGiamToiDa")
    private BigDecimal giaTriGiamToiDa;

    @Column(name = "HinhThucGiam")
    private Boolean hinhThucGiam;

    @Column(name = "TrangThai")
    private Integer trangThai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NguoiSoHuu")
    private KhachHang nguoiSoHuu;

}