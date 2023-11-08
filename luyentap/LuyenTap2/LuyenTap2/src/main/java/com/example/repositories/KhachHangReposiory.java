package com.example.repositories;

import com.example.entities.HangKhachHang;
import com.example.entities.KhachHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KhachHangReposiory extends JpaRepository<KhachHang, Long> {

    @Query("select kh from KhachHang kh where kh.hangKhachHang.id = ?1 and (kh.tenKhachHang like %?2% or kh.id like %?2% or kh.soDienThoai like %?2%)")
    Page<KhachHang> findByHangKhachHangIdAndSearch(HangKhachHang hangKhachHang, String search, Pageable pageable);

    @Query("select kh from KhachHang kh where kh.hangKhachHang.id = ?1")
    Page<KhachHang> findByHangKhachHangId(HangKhachHang hangKhachHang, Pageable pageable);

    @Query("select kh from KhachHang kh where kh.tenKhachHang like %?1% or kh.id like %?1% or kh.soDienThoai like %?1%")
    Page<KhachHang> findByTenKhachHangContainingOrIdContainingOrSoDienThoaiContaining(String search, String search1, String search2, Pageable pageable);
}
