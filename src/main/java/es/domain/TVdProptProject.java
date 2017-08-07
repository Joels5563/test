package es.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_vd_propt_project")
public class TVdProptProject {
    /**
     * project_id
     */
    @Id
    @Column(name = "project_id")
    private Long projectId;

    /**
     * vendor_id
     */
    @Column(name = "vendor_id")
    private Long vendorId;

    /**
     * 编号规则：‘J’+自动增长
     */
    @Column(name = "project_no")
    private String projectNo;

    /**
     * 项目名称
     */
    private String title;

    /**
     * 项目类型:1.公寓,2.独栋别墅3.联排别墅,4其他
     */
    @Column(name = "project_type")
    private Byte projectType;

    @Column(name = "detail_addr")
    private String detailAddr;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "region_first_code")
    private String regionFirstCode;

    /**
     * 二级行政区code
     */
    @Column(name = "region_second_code")
    private String regionSecondCode;

    /**
     * 三级行政区code
     */
    @Column(name = "region_third_code")
    private String regionThirdCode;

    /**
     * 四级行政区code
     */
    @Column(name = "region_forth_code")
    private String regionForthCode;

    /**
     * 0:未发布,1: 等待审核,2:审核通过,3:审核失败,4:暂停销售
            
     */
    @Column(name = "project_state")
    private Byte projectState;

    @Column(name = "min_price")
    private BigDecimal minPrice;

    @Column(name = "max_price")
    private BigDecimal maxPrice;

    /**
     * 封面图
     */
    @Column(name = "front_image")
    private String frontImage;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 距目标距离
     */
    @Column(name = "target_distance")
    private BigDecimal targetDistance;

    @Column(name = "target_reference")
    private Byte targetReference;

    @Column(name = "reservation_fee")
    private BigDecimal reservationFee;

    /**
     * 币种code
     */
    @Column(name = "currency_type")
    private String currencyType;

    /**
     * 项目联系人姓名.平台使用
     */
    @Column(name = "contacts_name")
    private String contactsName;

    /**
     * 项目联系人电话.平台使用
     */
    @Column(name = "contacts_phone")
    private String contactsPhone;

    /**
     * 项目联系人email.平台使用
     */
    @Column(name = "contacts_email")
    private String contactsEmail;

    /**
     * 卖方律师/产权公司名称.澳洲只有律师
            
            美国律师和产权公司都有
     */
    @Column(name = "lawyer_name")
    private String lawyerName;

    /**
     * 卖方律师/产权公司电话.澳洲只有律师
            
            美国律师和产权公司都有
     */
    @Column(name = "lawyer_phone")
    private String lawyerPhone;

    /**
     * 卖方律师/产权公司email.澳洲只有律师
            
            美国律师和产权公司都有
     */
    @Column(name = "lawyer_email")
    private String lawyerEmail;

    /**
     * 0：海外人士不可购,1：海外人士可购
     */
    @Column(name = "is_abroad")
    private Boolean isAbroad;

    @Column(name = "abroad_percent")
    private BigDecimal abroadPercent;

    /**
     * 可海外销售的不动产数量
     */
    @Column(name = "abroad_number")
    private Short abroadNumber;

    @Column(name = "is_under")
    private Boolean isUnder;

    @Column(name = "data_complete")
    private String dataComplete;

    /**
     * 是否删除.0:未删除,1:已删除
     */
    @Column(name = "del_flag")
    private Boolean delFlag;

    @Column(name = "creator_id")
    private Long creatorId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    private Integer version;

    /**
     * 定金说明
     */
    @Column(name = "reservation_detail")
    private String reservationDetail;

    /**
     * 不动产总数
     */
    @Column(name = "property_count")
    private Integer propertyCount;

    /**
     * 已售不动产数量
     */
    @Column(name = "sold_count")
    private Integer soldCount;

    /**
     * 质量保证年限.独栋别墅、联排别墅、公寓需要，范围0-100的小数不包括0
     */
    @Column(name = "quality_year")
    private BigDecimal qualityYear;

    /**
     * 1:购房合同;2:土地合同、建房合同
     */
    @Column(name = "term_number")
    private Byte termNumber;

    /**
     * 最后更新时间
     */
    @Column(name = "last_time")
    private Date lastTime;

    /**
     * 项目描述
     */
    private String description;

    /**
     * 获取project_id
     *
     * @return project_id - project_id
     */
    public Long getProjectId() {
        return projectId;
    }

    /**
     * 设置project_id
     *
     * @param projectId project_id
     */
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    /**
     * 获取vendor_id
     *
     * @return vendor_id - vendor_id
     */
    public Long getVendorId() {
        return vendorId;
    }

    /**
     * 设置vendor_id
     *
     * @param vendorId vendor_id
     */
    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    /**
     * 获取编号规则：‘J’+自动增长
     *
     * @return project_no - 编号规则：‘J’+自动增长
     */
    public String getProjectNo() {
        return projectNo;
    }

    /**
     * 设置编号规则：‘J’+自动增长
     *
     * @param projectNo 编号规则：‘J’+自动增长
     */
    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    /**
     * 获取项目名称
     *
     * @return title - 项目名称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置项目名称
     *
     * @param title 项目名称
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取项目类型:1.公寓,2.独栋别墅3.联排别墅,4其他
     *
     * @return project_type - 项目类型:1.公寓,2.独栋别墅3.联排别墅,4其他
     */
    public Byte getProjectType() {
        return projectType;
    }

    /**
     * 设置项目类型:1.公寓,2.独栋别墅3.联排别墅,4其他
     *
     * @param projectType 项目类型:1.公寓,2.独栋别墅3.联排别墅,4其他
     */
    public void setProjectType(Byte projectType) {
        this.projectType = projectType;
    }

    /**
     * @return detail_addr
     */
    public String getDetailAddr() {
        return detailAddr;
    }

    /**
     * @param detailAddr
     */
    public void setDetailAddr(String detailAddr) {
        this.detailAddr = detailAddr;
    }

    /**
     * @return zip_code
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * @return country_code
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * @param countryCode
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * @return region_first_code
     */
    public String getRegionFirstCode() {
        return regionFirstCode;
    }

    /**
     * @param regionFirstCode
     */
    public void setRegionFirstCode(String regionFirstCode) {
        this.regionFirstCode = regionFirstCode;
    }

    /**
     * 获取二级行政区code
     *
     * @return region_second_code - 二级行政区code
     */
    public String getRegionSecondCode() {
        return regionSecondCode;
    }

    /**
     * 设置二级行政区code
     *
     * @param regionSecondCode 二级行政区code
     */
    public void setRegionSecondCode(String regionSecondCode) {
        this.regionSecondCode = regionSecondCode;
    }

    /**
     * 获取三级行政区code
     *
     * @return region_third_code - 三级行政区code
     */
    public String getRegionThirdCode() {
        return regionThirdCode;
    }

    /**
     * 设置三级行政区code
     *
     * @param regionThirdCode 三级行政区code
     */
    public void setRegionThirdCode(String regionThirdCode) {
        this.regionThirdCode = regionThirdCode;
    }

    /**
     * 获取四级行政区code
     *
     * @return region_forth_code - 四级行政区code
     */
    public String getRegionForthCode() {
        return regionForthCode;
    }

    /**
     * 设置四级行政区code
     *
     * @param regionForthCode 四级行政区code
     */
    public void setRegionForthCode(String regionForthCode) {
        this.regionForthCode = regionForthCode;
    }

    /**
     * 获取0:未发布,1: 等待审核,2:审核通过,3:审核失败,4:暂停销售
            
     *
     * @return project_state - 0:未发布,1: 等待审核,2:审核通过,3:审核失败,4:暂停销售
            
     */
    public Byte getProjectState() {
        return projectState;
    }

    /**
     * 设置0:未发布,1: 等待审核,2:审核通过,3:审核失败,4:暂停销售
            
     *
     * @param projectState 0:未发布,1: 等待审核,2:审核通过,3:审核失败,4:暂停销售
            
     */
    public void setProjectState(Byte projectState) {
        this.projectState = projectState;
    }

    /**
     * @return min_price
     */
    public BigDecimal getMinPrice() {
        return minPrice;
    }

    /**
     * @param minPrice
     */
    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    /**
     * @return max_price
     */
    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    /**
     * @param maxPrice
     */
    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    /**
     * 获取封面图
     *
     * @return front_image - 封面图
     */
    public String getFrontImage() {
        return frontImage;
    }

    /**
     * 设置封面图
     *
     * @param frontImage 封面图
     */
    public void setFrontImage(String frontImage) {
        this.frontImage = frontImage;
    }

    /**
     * 获取经度
     *
     * @return longitude - 经度
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * 设置经度
     *
     * @param longitude 经度
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * 获取纬度
     *
     * @return latitude - 纬度
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * 设置纬度
     *
     * @param latitude 纬度
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * 获取距目标距离
     *
     * @return target_distance - 距目标距离
     */
    public BigDecimal getTargetDistance() {
        return targetDistance;
    }

    /**
     * 设置距目标距离
     *
     * @param targetDistance 距目标距离
     */
    public void setTargetDistance(BigDecimal targetDistance) {
        this.targetDistance = targetDistance;
    }

    /**
     * @return target_reference
     */
    public Byte getTargetReference() {
        return targetReference;
    }

    /**
     * @param targetReference
     */
    public void setTargetReference(Byte targetReference) {
        this.targetReference = targetReference;
    }

    /**
     * @return reservation_fee
     */
    public BigDecimal getReservationFee() {
        return reservationFee;
    }

    /**
     * @param reservationFee
     */
    public void setReservationFee(BigDecimal reservationFee) {
        this.reservationFee = reservationFee;
    }

    /**
     * 获取币种code
     *
     * @return currency_type - 币种code
     */
    public String getCurrencyType() {
        return currencyType;
    }

    /**
     * 设置币种code
     *
     * @param currencyType 币种code
     */
    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    /**
     * 获取项目联系人姓名.平台使用
     *
     * @return contacts_name - 项目联系人姓名.平台使用
     */
    public String getContactsName() {
        return contactsName;
    }

    /**
     * 设置项目联系人姓名.平台使用
     *
     * @param contactsName 项目联系人姓名.平台使用
     */
    public void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }

    /**
     * 获取项目联系人电话.平台使用
     *
     * @return contacts_phone - 项目联系人电话.平台使用
     */
    public String getContactsPhone() {
        return contactsPhone;
    }

    /**
     * 设置项目联系人电话.平台使用
     *
     * @param contactsPhone 项目联系人电话.平台使用
     */
    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
    }

    /**
     * 获取项目联系人email.平台使用
     *
     * @return contacts_email - 项目联系人email.平台使用
     */
    public String getContactsEmail() {
        return contactsEmail;
    }

    /**
     * 设置项目联系人email.平台使用
     *
     * @param contactsEmail 项目联系人email.平台使用
     */
    public void setContactsEmail(String contactsEmail) {
        this.contactsEmail = contactsEmail;
    }

    /**
     * 获取卖方律师/产权公司名称.澳洲只有律师
            
            美国律师和产权公司都有
     *
     * @return lawyer_name - 卖方律师/产权公司名称.澳洲只有律师
            
            美国律师和产权公司都有
     */
    public String getLawyerName() {
        return lawyerName;
    }

    /**
     * 设置卖方律师/产权公司名称.澳洲只有律师
            
            美国律师和产权公司都有
     *
     * @param lawyerName 卖方律师/产权公司名称.澳洲只有律师
            
            美国律师和产权公司都有
     */
    public void setLawyerName(String lawyerName) {
        this.lawyerName = lawyerName;
    }

    /**
     * 获取卖方律师/产权公司电话.澳洲只有律师
            
            美国律师和产权公司都有
     *
     * @return lawyer_phone - 卖方律师/产权公司电话.澳洲只有律师
            
            美国律师和产权公司都有
     */
    public String getLawyerPhone() {
        return lawyerPhone;
    }

    /**
     * 设置卖方律师/产权公司电话.澳洲只有律师
            
            美国律师和产权公司都有
     *
     * @param lawyerPhone 卖方律师/产权公司电话.澳洲只有律师
            
            美国律师和产权公司都有
     */
    public void setLawyerPhone(String lawyerPhone) {
        this.lawyerPhone = lawyerPhone;
    }

    /**
     * 获取卖方律师/产权公司email.澳洲只有律师
            
            美国律师和产权公司都有
     *
     * @return lawyer_email - 卖方律师/产权公司email.澳洲只有律师
            
            美国律师和产权公司都有
     */
    public String getLawyerEmail() {
        return lawyerEmail;
    }

    /**
     * 设置卖方律师/产权公司email.澳洲只有律师
            
            美国律师和产权公司都有
     *
     * @param lawyerEmail 卖方律师/产权公司email.澳洲只有律师
            
            美国律师和产权公司都有
     */
    public void setLawyerEmail(String lawyerEmail) {
        this.lawyerEmail = lawyerEmail;
    }

    /**
     * 获取0：海外人士不可购,1：海外人士可购
     *
     * @return is_abroad - 0：海外人士不可购,1：海外人士可购
     */
    public Boolean getIsAbroad() {
        return isAbroad;
    }

    /**
     * 设置0：海外人士不可购,1：海外人士可购
     *
     * @param isAbroad 0：海外人士不可购,1：海外人士可购
     */
    public void setIsAbroad(Boolean isAbroad) {
        this.isAbroad = isAbroad;
    }

    /**
     * @return abroad_percent
     */
    public BigDecimal getAbroadPercent() {
        return abroadPercent;
    }

    /**
     * @param abroadPercent
     */
    public void setAbroadPercent(BigDecimal abroadPercent) {
        this.abroadPercent = abroadPercent;
    }

    /**
     * 获取可海外销售的不动产数量
     *
     * @return abroad_number - 可海外销售的不动产数量
     */
    public Short getAbroadNumber() {
        return abroadNumber;
    }

    /**
     * 设置可海外销售的不动产数量
     *
     * @param abroadNumber 可海外销售的不动产数量
     */
    public void setAbroadNumber(Short abroadNumber) {
        this.abroadNumber = abroadNumber;
    }

    /**
     * @return is_under
     */
    public Boolean getIsUnder() {
        return isUnder;
    }

    /**
     * @param isUnder
     */
    public void setIsUnder(Boolean isUnder) {
        this.isUnder = isUnder;
    }

    /**
     * @return data_complete
     */
    public String getDataComplete() {
        return dataComplete;
    }

    /**
     * @param dataComplete
     */
    public void setDataComplete(String dataComplete) {
        this.dataComplete = dataComplete;
    }

    /**
     * 获取是否删除.0:未删除,1:已删除
     *
     * @return del_flag - 是否删除.0:未删除,1:已删除
     */
    public Boolean getDelFlag() {
        return delFlag;
    }

    /**
     * 设置是否删除.0:未删除,1:已删除
     *
     * @param delFlag 是否删除.0:未删除,1:已删除
     */
    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * @return creator_id
     */
    public Long getCreatorId() {
        return creatorId;
    }

    /**
     * @param creatorId
     */
    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return version
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * 获取定金说明
     *
     * @return reservation_detail - 定金说明
     */
    public String getReservationDetail() {
        return reservationDetail;
    }

    /**
     * 设置定金说明
     *
     * @param reservationDetail 定金说明
     */
    public void setReservationDetail(String reservationDetail) {
        this.reservationDetail = reservationDetail;
    }

    /**
     * 获取不动产总数
     *
     * @return property_count - 不动产总数
     */
    public Integer getPropertyCount() {
        return propertyCount;
    }

    /**
     * 设置不动产总数
     *
     * @param propertyCount 不动产总数
     */
    public void setPropertyCount(Integer propertyCount) {
        this.propertyCount = propertyCount;
    }

    /**
     * 获取已售不动产数量
     *
     * @return sold_count - 已售不动产数量
     */
    public Integer getSoldCount() {
        return soldCount;
    }

    /**
     * 设置已售不动产数量
     *
     * @param soldCount 已售不动产数量
     */
    public void setSoldCount(Integer soldCount) {
        this.soldCount = soldCount;
    }

    /**
     * 获取质量保证年限.独栋别墅、联排别墅、公寓需要，范围0-100的小数不包括0
     *
     * @return quality_year - 质量保证年限.独栋别墅、联排别墅、公寓需要，范围0-100的小数不包括0
     */
    public BigDecimal getQualityYear() {
        return qualityYear;
    }

    /**
     * 设置质量保证年限.独栋别墅、联排别墅、公寓需要，范围0-100的小数不包括0
     *
     * @param qualityYear 质量保证年限.独栋别墅、联排别墅、公寓需要，范围0-100的小数不包括0
     */
    public void setQualityYear(BigDecimal qualityYear) {
        this.qualityYear = qualityYear;
    }

    /**
     * 获取1:购房合同;2:土地合同、建房合同
     *
     * @return term_number - 1:购房合同;2:土地合同、建房合同
     */
    public Byte getTermNumber() {
        return termNumber;
    }

    /**
     * 设置1:购房合同;2:土地合同、建房合同
     *
     * @param termNumber 1:购房合同;2:土地合同、建房合同
     */
    public void setTermNumber(Byte termNumber) {
        this.termNumber = termNumber;
    }

    /**
     * 获取最后更新时间
     *
     * @return last_time - 最后更新时间
     */
    public Date getLastTime() {
        return lastTime;
    }

    /**
     * 设置最后更新时间
     *
     * @param lastTime 最后更新时间
     */
    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    /**
     * 获取项目描述
     *
     * @return description - 项目描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置项目描述
     *
     * @param description 项目描述
     */
    public void setDescription(String description) {
        this.description = description;
    }
}