package com.hb.sys.manage.controlstation.dictionary.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.hb.sys.manage.controlstation.dictionary.dao.DictionaryDao;
import com.hb.sys.manage.controlstation.dictionary.entity.Dictionary;
import com.hb.sys.tools.gen.CrudService;

/**
 * 字典管理Serivice
 * @author hanbin
 * @date 2018年1月8日
 */
@Service
public class DictionaryService extends CrudService<DictionaryDao,Dictionary>{

    @CacheEvict(value="sysCache",key="#dictionary.type+'-'+#dictionary.value")
    public void update(Dictionary dictionary) {
         dao.update(dictionary);
    }
}
