package com.example.mr.yihuanhuishou.jsonbean.siji;

import java.util.List;

/**
 * Created by Mr赵 on 2018/8/8.
 */

public class DataList {
        public String name;
        public String sort;
        public List<String> number;

        public DataList(String name, String sort, List<String> number) {
            this.name = name;
            this.sort = sort;
            this.number = number;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public List<String> getNumber() {
            return number;
        }

        public void setNumber(List<String> number) {
            this.number = number;
        }


    @Override
    public String toString() {
        return "DataList{" +
                "name='" + name + '\'' +
                ",sort='" + sort + '\'' +
                ",number="+ number +
                '}';
    }
}
