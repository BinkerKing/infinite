package net.binker.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public class PageQuery {

    private static final long serialVersionUID = -7460526310221003824L;
    private Integer pindex = 0; // 页数
    private Integer offset = 0; //从第几条开始
    private Integer pcount = 10;// 每页条数
    private String sort; // like: id_asc|name_desc

    public Integer getPindex() {
        return pindex;
    }

    public void setPindex(Integer pindex) {
        this.pindex = pindex;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
        this.pindex = offset / pcount;
    }

    public Integer getPcount() {
        return pcount;
    }

    public void setPcount(Integer pcount) {
        this.pcount = pcount;
        this.pindex = offset / pcount;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    @Transient
    @JsonIgnore
    public Sort getSortObj() {
        if (StringUtils.isNotEmpty(sort)) {
            String[] arr = sort.split("\\|");
            List<Sort.Order> orders = new ArrayList<>();
            for (String item : arr) {
                if (StringUtils.isNotEmpty(item)) {
                    String[] its = item.split("_");
                    orders.add(new Sort.Order(
                            Sort.Direction.valueOf(its[1].toUpperCase()), its[0]));
                }
            }
            return new Sort(orders);
        }
        return null;
    }
}
