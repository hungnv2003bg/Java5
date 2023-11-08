package com.example.repositories;

import com.example.entities.PhieuGiamGia;
import com.example.model.PhieuGiamGiaViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface PhieuGiamGiaRepository extends JpaRepository<PhieuGiamGia, String> {

    @Query("SELECT new com.example.model.PhieuGiamGiaViewModel(p.maPhieu, p.tenPhieu, p.giaTriGiam, p.giaTriGiamToiDa, p.ngayBatDau, p.ngayKetThuc, p.nguoiSoHuu.maKhachHang, p.nguoiSoHuu.tenKhachHang) FROM PhieuGiamGia p WHERE p.maPhieu=:maPhieu")
    public PhieuGiamGiaViewModel findByMaPhieu(@Param("maPhieu") String maPhieu);

    @Query("SELECT p FROM PhieuGiamGia p WHERE p.nguoiSoHuu.maKhachHang=:maKhachHang and p.ngayBatDau between :ngayBatDau and :ngayKetThuc and p.ngayKetThuc between :ngayBatDau and :ngayKetThuc")
    public Page<PhieuGiamGia> getList(@Param("maKhachHang") Long maKhachHang, @Param("ngayBatDau") Date ngayBatDau, @Param("ngayKetThuc") Date ngayKetThuc, Pageable pageable);
}
