package com.tyugen.WebsiteEduAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaiKhoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taiKhoanID;

    @Column
    @Size(max = 50, message = "Tên người dùng không được vượt quá 50 ký tự")
    private String tenNguoiDung;

    @Column
    @Size(max = 50, message = "Tài khoản không được vượt quá 50 ký tự")
    private String taiKhoan;

    @Column
    @Size(max = 50, message = "Mật khẩu không được vượt quá 50 ký tự")
    private String matKhau;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quyenHanID", foreignKey = @ForeignKey(name = "fk_taikhoan_quyenhan"))
    private QuyenHan quyenHan;

    @OneToMany(mappedBy = "taiKhoan", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("taiKhoan")
    private Set<BaiViet> baiViets;

    @OneToMany(mappedBy = "taiKhoan", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("taiKhoan")
    private Set<DangKyHoc> dangKyHocs;

    public Integer getTaiKhoanID() {
        return taiKhoanID;
    }

    public void setTaiKhoanID(Integer taiKhoanID) {
        this.taiKhoanID = taiKhoanID;
    }

    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public QuyenHan getQuyenHan() {
        return quyenHan;
    }

    public void setQuyenHan(QuyenHan quyenHan) {
        this.quyenHan = quyenHan;
    }

    public Set<BaiViet> getBaiViets() {
        return baiViets;
    }

    public void setBaiViets(Set<BaiViet> baiViets) {
        this.baiViets = baiViets;
    }

    public Set<DangKyHoc> getDangKyHocs() {
        return dangKyHocs;
    }

    public void setDangKyHocs(Set<DangKyHoc> dangKyHocs) {
        this.dangKyHocs = dangKyHocs;
    }
}
