package com.example.controllers;

import com.example.entities.HangKhachHang;
import com.example.entities.KhachHang;
import com.example.repositories.HangKhachHangRepository;
import com.example.repositories.KhachHangReposiory;
import jakarta.servlet.http.HttpSession;
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
@RequestMapping("khach-hang")
public class KhachHangController {

    @Autowired
    private KhachHangReposiory khachHangReposiory;

    @Autowired
    private HangKhachHangRepository hangKhachHangRepository;

    @Autowired
    private KhachHang khachHang;

    @Autowired
    private HttpSession session;

    @GetMapping("hien-thi")
    public String hienThi(Model model, @RequestParam(defaultValue = "0", name = "page") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<KhachHang> list = khachHangReposiory.findAll(pageable);
        model.addAttribute("list", list);
        model.addAttribute("listHangKhachHang", hangKhachHangRepository.findAll());
        model.addAttribute("khachHang", khachHang);
        return "hien-thi";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        khachHangReposiory.deleteById(id);
        session.setAttribute("thongBao", "Xoa phan tu: " + id + " thanh cong");
        return "redirect:/khach-hang/hien-thi";
    }

    @PostMapping("add")
    public String add(@Valid @ModelAttribute("khachHang") KhachHang khachHang, BindingResult result, @RequestParam(defaultValue = "0", name = "page") int page, Model model) {
        if (result.hasErrors()) {
            Pageable pageable = PageRequest.of(page, 5);
            Page<KhachHang> list = khachHangReposiory.findAll(pageable);
            model.addAttribute("list", list);
            model.addAttribute("listHangKhachHang", hangKhachHangRepository.findAll());
            model.addAttribute("khachHang", khachHang);
            return "hien-thi";
        }


        khachHangReposiory.save(khachHang);
        session.setAttribute("thongBao", "them thanh cong");
        return "redirect:/khach-hang/hien-thi";
    }

    @PostMapping("/search")
    public String search(Model model, @RequestParam(defaultValue = "0", name = "page") int page,
                         @RequestParam(name = "search", required = false) String search,
                         @RequestParam(name = "maHang", required = false) Integer maHang) {
        model.addAttribute("link", "/khach-hang/search");
        Pageable pageable = PageRequest.of(page, 5);
        Page<KhachHang> list;

        HangKhachHang hangKhachHang = hangKhachHangRepository.getById(maHang);

        if (maHang != null && search != null && !search.isEmpty()) {
            // Nếu có mã hạng khách hàng và từ khóa tìm kiếm, thực hiện tìm kiếm theo mã hạng khách hàng và từ khóa
            list = khachHangReposiory.findByHangKhachHangIdAndSearch(hangKhachHang, search, pageable);
        } else if (search != null && !search.isEmpty()) {
            // Nếu chỉ có từ khóa tìm kiếm, thực hiện tìm kiếm theo tên, mã tài khoản, hoặc số điện thoại
            list = khachHangReposiory.findByTenKhachHangContainingOrIdContainingOrSoDienThoaiContaining(search, search, search, pageable);
        } else if (maHang != null) {
            // Nếu chỉ có mã hạng khách hàng, thực hiện tìm kiếm theo mã hạng khách hàng
            list = khachHangReposiory.findByHangKhachHangId(hangKhachHang, pageable);
        } else {
            // Nếu không có tiêu chí tìm kiếm, lấy tất cả các khách hàng
            list = khachHangReposiory.findAll(pageable);
        }

        model.addAttribute("listHangKhachHang", hangKhachHangRepository.findAll());
        model.addAttribute("list", list);
        return "hien-thi";
    }


}
