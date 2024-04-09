package com.game.bean;

import com.game.domain.Student;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Data
public class PageBean<T> {
    private Integer currentPage;
    private Integer totalPage;
    private Integer numPage;
    private Integer totalNum;
    private Integer start;
    private Integer end;
    private List<Integer> optionalPages;
    @Setter
    private List<T> listPage;

    public PageBean() {
    }

    public void setNumPage(Integer numPage) {
        this.numPage = numPage;
        this.totalPage = (int) Math.ceil((double)this.totalNum/numPage);
        this.currentPage=1;
    }

    public PageBean(Integer totalNum) {
        this.totalNum = totalNum;
        this.numPage = 16;
        this.totalPage = (int) Math.ceil((double)totalNum/numPage);
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
        this.start = (current-1)*this.numPage;
        this.end =(current*this.numPage<this.totalNum)? current*this.numPage:this.totalNum;
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
