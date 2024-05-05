package com.game.bean;

import lombok.Data;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageBean<T> {
    private Integer currentPage;
    private Integer totalPage;
    private Integer pageSize;
    private Integer totalSize;
    private Integer begin;
    private Integer end;
    private List<Integer> optionalPages=new ArrayList<>();
    @Setter
    private List<T> listPage = new ArrayList<>();

    public PageBean() {
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        this.totalPage = (int) Math.ceil((double)this.totalSize/pageSize);
        this.currentPage=1;
    }

    public PageBean(Integer totalSize) {
        this.totalSize = totalSize;
        this.pageSize = 16;
        this.totalPage = (int) Math.ceil((double)totalSize/pageSize);
        this.currentPage=1;
    }


    public void setTotalSize(Integer totalSize) {
        this.pageSize=16;
        this.totalSize = totalSize;
        this.totalPage = (int) Math.ceil((double)totalSize/pageSize);
        this.currentPage=1;
    }

    public void setCurrentPage(Integer currentPage) {
        Integer current=currentPage;
        if (current > this.totalPage){
            current = this.totalPage;
        } else if (current<1) {
            current=1;
        }
        this.currentPage = current;
        this.begin = (current-1)*this.pageSize;
        this.end =this.pageSize;
        this.optionalPages.add(currentPage);
        for(int turn=1 ,diff=1;turn<=5;diff++){
            if (current-diff>=1){
                this.optionalPages.add(0,current-diff);
                turn++;
            }
            if (current+diff<=this.totalPage){
                this.optionalPages.add(current+diff);
                turn++;
            }
        }
    }

}
