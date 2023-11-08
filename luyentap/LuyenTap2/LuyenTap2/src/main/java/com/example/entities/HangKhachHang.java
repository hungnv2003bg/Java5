package com.example.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@Entity
@Table
public class HangKhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaHang", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Nationalized
    @Column(name = "TenHang", length = 50)
    private String tenHang;

    @Nationalized
    @Lob
    @Column(name = "MoTa")
    private String moTa;

    @NotNull
    @Column(name = "DiemToiThieu", nullable = false)
    private Integer diemToiThieu;

    @Column(name = "TrangThai")
    private Integer trangThai;

}