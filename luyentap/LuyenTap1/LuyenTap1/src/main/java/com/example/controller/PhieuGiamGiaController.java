package com.example.controller;

import com.example.entities.KhachHang;
import com.example.entities.PhieuGiamGia;
import com.example.model.PhieuGiamGiaViewModel;
import com.example.repositories.KhachHangRepository;
import com.example.repositories.PhieuGiamGiaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("phieu-giam-gia")
public class PhieuGiamGiaController {
    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private PhieuGiamGiaRepository phieuGiamGiaRepository;

    @Autowired
    private PhieuGiamGiaViewModel phieuGiamGiaViewModel;

    @GetMapping("hien-thi")
    public String hienThi(Model model, @RequestParam(defaultValue = "0", name = "page") int page) {
        model.addAttribute("link", "/phieu-giam-gia/hien-thi");
        Pageable pageable = PageRequest.of(page, 5);
        Page<PhieuGiamGia> list = phieuGiamGiaRepository.findAll(pageable);
        model.addAttribute("listKhachHang", khachHangRepository.findAll());
        model.addAttribute("phieuGiamGia", phieuGiamGiaViewModel);
        model.addAttribute("list", list);
        return "hien-thi";
    }

    @GetMapping("edit/{maPhieu}")
    public String edit(Model model, @PathVariable("maPhieu") String maPhieu) {
        model.addAttribute("listKhachHang", khachHangRepository.findAll());
        model.addAttribute("phieuGiamGia", phieuGiamGiaRepository.findByMaPhieu(maPhieu));
        return "update";
    }

    @GetMapping("delete/{maPhieu}")
    public String delete(@PathVariable("maPhieu") String maPhieu) {
        phieuGiamGiaRepository.deleteById(maPhieu);
        return "redirect:/phieu-giam-gia/hien-thi";
    }

    @PostMapping("update/{maPhieu}")
    public String update(@Valid @PathVariable("maPhieu") String maPhieu, @ModelAttribute("phieuGiamGia") PhieuGiamGiaViewModel phieuGiamGia, BindingResult result) {
        if (result.hasErrors()) {
            return "update";
        }
        Optional<PhieuGiamGia> optional = phieuGiamGiaRepository.findById(maPhieu);
        if (optional.isPresent()) {
            KhachHang khachHang = new KhachHang();
            khachHang.setMaKhachHang(phieuGiamGia.getMaKhanhHang());

            PhieuGiamGia phieuGiamGia1 = optional.get();
            phieuGiamGia1.setMaPhieu(maPhieu);
            phieuGiamGia1.setTenPhieu(phieuGiamGia.getTenPhieu());
            phieuGiamGia1.setGiaTriGiam(phieuGiamGia.getGiaTriGiam());
            phieuGiamGia1.setGiaTriGiamToiDa(phieuGiamGia.getGiaTriGiamToiDa());
            phieuGiamGia1.setNgayBatDau(phieuGiamGia.getNgayBatDau());
            phieuGiamGia1.setNgayKetThuc(phieuGiamGia.getNgayKetThuc());
            phieuGiamGia1.setNguoiSoHuu(khachHang);

            phieuGiamGiaRepository.save(phieuGiamGia1);
        }
        return "redirect:/phieu-giam-gia/hien-thi";
    }

    @PostMapping("search")
    public String search(Model model, @RequestParam(defaultValue = "0", name = "page") int page, @ModelAttribute("phieuGiamGia") PhieuGiamGiaViewModel phieuGiamGia) {
        model.addAttribute("link", "/phieu-giam-gia/search");
        Pageable pageable = PageRequest.of(page, 5);
        Page<PhieuGiamGia> list = phieuGiamGiaRepository.getList(phieuGiamGia.getMaKhanhHang(), phieuGiamGia.getNgayBatDau(), phieuGiamGia.getNgayKetThuc(), pageable);
        model.addAttribute("listKhachHang", khachHangRepository.findAll());
        model.addAttribute("phieuGiamGia", phieuGiamGiaViewModel);
        model.addAttribute("list", list);
        return "hien-thi";
    }
}
