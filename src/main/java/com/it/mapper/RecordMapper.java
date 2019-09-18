package com.it.mapper;

import com.it.entity.Record;

import java.util.List;

public interface RecordMapper {
    /**
     *  添加操作
     */
    public int addOperation(Record record);
    /**
     * 查看操作
     */
    public List<Record> findRecord();
    /**
     * 通过管理员id查询
     */
    public List<Record> findRecordById();
}
