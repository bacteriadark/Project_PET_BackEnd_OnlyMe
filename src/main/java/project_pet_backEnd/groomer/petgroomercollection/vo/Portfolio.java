package project_pet_backEnd.groomer.petgroomercollection.vo;

import lombok.Data;

import javax.persistence.Table;

@Table(name = "PORTFOLIO")
@Data
public class Portfolio {
    // 作品
    private Integer porId;
    private Integer pgId;
    private String porTitle;
    private String porText;
    private java.util.Date porUpload;//util.date

    // 此處省略建構子、Getter 和 Setter 方法
}
