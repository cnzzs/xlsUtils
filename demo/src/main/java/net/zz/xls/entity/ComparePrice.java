package net.zz.xls.entity;

import net.zz.xls.infrastructure.annotation.Cell;
import net.zz.xls.infrastructure.annotation.Cells;

import java.math.BigInteger;

/**
 * Created by ZaoSheng on 2015/7/21.
 */
public class ComparePrice {

    private BigInteger id;
    @Cell(value = "0")
    private String companyName;
    @Cell(value = "1")
    private String companyPname;
    @Cell(value = "2")
    private String phone;
    @Cell(value = "3")
    private String qq;
    @Cell(value = "4")
    private String startCityName;
    @Cell(value = "5")
    private String startCityPname;
    @Cell(value = "6")
    private String endCityName;
    @Cell(value = "7")
    private String endCityPname;
    @Cell(value = "8")
    private String type;
    @Cell(value = "9")
    private String timeline;
    @Cells(value = {"10"})
    private String fees1;
    @Cell(value = "11")
    private String fees2;
    @Cell(value = "12")
    private String fees3;
    @Cell(value = "13")
    private String fees4;
    @Cell(value = "14")
    private String fees5;
    @Cell(value = "15")
    private String fees6;
    @Cell(value = "16")
    private String fees7;
    @Cell(value = "17")
    private String fees8;
    @Cell(value = "18")
    private String fees9;
    @Cell(value = "19")
    private String fees10;
    @Cell(value = "20")
    private String fees11;
    @Cell(value = "21")
    private String createTime;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyPname() {
        return companyPname;
    }

    public void setCompanyPname(String companyPname) {
        this.companyPname = companyPname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getStartCityName() {
        return startCityName;
    }

    public void setStartCityName(String startCityName) {
        this.startCityName = startCityName;
    }

    public String getStartCityPname() {
        return startCityPname;
    }

    public void setStartCityPname(String startCityPname) {
        this.startCityPname = startCityPname;
    }

    public String getEndCityName() {
        return endCityName;
    }

    public void setEndCityName(String endCityName) {
        this.endCityName = endCityName;
    }

    public String getEndCityPname() {
        return endCityPname;
    }

    public void setEndCityPname(String endCityPname) {
        this.endCityPname = endCityPname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTimeline() {
        return timeline;
    }

    public void setTimeline(String timeline) {
        this.timeline = timeline;
    }

    public String getFees1() {
        return fees1;
    }

    public void setFees1(String fees1) {
        this.fees1 = fees1;
    }

    public String getFees2() {
        return fees2;
    }

    public void setFees2(String fees2) {
        this.fees2 = fees2;
    }

    public String getFees3() {
        return fees3;
    }

    public void setFees3(String fees3) {
        this.fees3 = fees3;
    }

    public String getFees4() {
        return fees4;
    }

    public void setFees4(String fees4) {
        this.fees4 = fees4;
    }

    public String getFees5() {
        return fees5;
    }

    public void setFees5(String fees5) {
        this.fees5 = fees5;
    }

    public String getFees6() {
        return fees6;
    }

    public void setFees6(String fees6) {
        this.fees6 = fees6;
    }

    public String getFees7() {
        return fees7;
    }

    public void setFees7(String fees7) {
        this.fees7 = fees7;
    }

    public String getFees8() {
        return fees8;
    }

    public void setFees8(String fees8) {
        this.fees8 = fees8;
    }

    public String getFees9() {
        return fees9;
    }

    public void setFees9(String fees9) {
        this.fees9 = fees9;
    }

    public String getFees10() {
        return fees10;
    }

    public void setFees10(String fees10) {
        this.fees10 = fees10;
    }

    public String getFees11() {
        return fees11;
    }

    public void setFees11(String fees11) {
        this.fees11 = fees11;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
