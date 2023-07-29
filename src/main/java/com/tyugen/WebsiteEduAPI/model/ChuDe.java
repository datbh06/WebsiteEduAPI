package com.tyugen.WebsiteEduAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
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
public class ChuDe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chuDeID;

    @Column
    @Size(max = 50, message = "Tên chủ đề không được vượt quá 50 ký tự")
    private String tenChuDe;

    @Column
    private String noiDung;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "loaiBaiVietID", foreignKey = @ForeignKey(name = "fk_chude_loaibaiviet"))
    private LoaiBaiViet loaiBaiViet;

    @OneToMany(mappedBy = "chuDes", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("chuDes")
    private Set<BaiViet> baiViets;

}
