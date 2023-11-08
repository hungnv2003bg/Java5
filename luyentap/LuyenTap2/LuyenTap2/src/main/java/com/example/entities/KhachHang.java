package com.example.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Getter
@Setter
@Entity
@Table
@Component
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaKhachHang", nullable = false)
    @NotNull(message = "Khong duoc de trong")
    private Long id;

    @Size(max = 50, message = "khong qua 50 ki tu")
    @Nationalized
    @Column(name = "TenKhachHang", length = 50)
    @NotBlank(message = "Khong duoc de trong")
    private String tenKhachHang;

    @Column(name = "SinhNhat")
    private Date sinhNhat;

    @Nationalized
    @Lob
    @Column(name = "DiaChi")
    private String diaChi;

    @Size(max = 15, message = "khong qua 15 ki tu")
    @Column(name = "SoDienThoai", length = 15)
    @NotBlank(message = "Khong duoc de trong")
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
    @JoinColumn(name = "HangKhachHang")
    private HangKhachHang hangKhachHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NguoiGioiThieu")
    private KhachHang nguoiGioiThieu;

}