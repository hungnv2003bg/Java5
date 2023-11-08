package com.example.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "KhachHang")
@Component
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaKhachHang", nullable = false)
    private Long maKhachHang;

    @Size(max = 50)
    @Nationalized
    @Column(name = "TenKhachHang", length = 50)
    private String tenKhachHang;

    @Column(name = "SinhNhat")
    private LocalDate sinhNhat;

    @Nationalized
    @Lob
    @Column(name = "DiaChi")
    private String diaChi;

    @Size(max = 15)
    @Column(name = "SoDienThoai", length = 15)
    private String soDienThoai;

    @Size(max = 50)
    @Column(name = "Email", length = 50)
    private String email;

    @Column(name = "GioiTinh")
    private Boolean gioiTinh;

    @Size(max = 15)
    @Column(name = "ChungMinhThu", length = 15)
    private String chungMinhThu;

    @Size(max = 15)
    @Column(name = "SoCanCuoc", length = 15)
    private String soCanCuoc;

    @Size(max = 50)
    @Column(name = "AnhDaiDien", length = 50)
    private String anhDaiDien;

    @Column(name = "TrangThai")
    private Integer trangThai;

    @Column(name = "DiemTichLuy")
    private Integer diemTichLuy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NguoiGioiThieu")
    private KhachHang nguoiGioiThieu;

}