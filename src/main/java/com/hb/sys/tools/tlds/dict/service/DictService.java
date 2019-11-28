package com.hb.sys.tools.tlds.dict.service;

import com.hb.sys.tools.tlds.dict.dao.DictDao;
import com.hb.sys.tools.tlds.dict.entity.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述：
 * 作者： hanbin
 * 时间： 2018-12-12 12:23
 **/
@Service
public class DictService {
    @Autowired
    private DictDao dictDao;
    public List<Dict> getDictList(String type){
        return dictDao.getDictList(type);
    }

    //@Cacheable(value="sysCache",key="#type+'-'+#value")
    public String getDictLabel(String value,String type){
        return dictDao.getDictLabel(value,type);
    }
}
