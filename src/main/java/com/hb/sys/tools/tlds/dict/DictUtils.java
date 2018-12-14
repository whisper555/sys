package com.hb.sys.tools.tlds.dict;

import java.util.List;

import com.hb.sys.tools.SpringContextHolder;
import com.hb.sys.tools.tlds.dict.dao.DictDao;
import com.hb.sys.tools.tlds.dict.entity.Dict;
import com.hb.sys.tools.tlds.dict.service.DictService;
import org.springframework.cache.annotation.Cacheable;

/**
 * 字典label和value获取工具，用在下拉选择框选择字典信息
 * @author hanbin
 *
 */
public class DictUtils {


	private static DictService dictService = SpringContextHolder.getBean(DictService.class);
	//根据字典类型获取字典类型列表
	public static List<Dict> getDictList (String type){
		
		List<Dict> dictList = dictService.getDictList(type);
		return dictList;
		
	}
	//根据字典类型和value获取label

	public static String getDictLabel(String value ,String type,String str){
		String label = dictService.getDictLabel(value,type);
		if(label==null){
			label = str;
		}
		return label;
	}
}
