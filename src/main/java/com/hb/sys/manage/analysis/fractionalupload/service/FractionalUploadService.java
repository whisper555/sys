package com.hb.sys.manage.analysis.fractionalupload.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hb.sys.global.dao.GlobalDao;
import com.hb.sys.manage.baseinfo.classs.dao.ClasssInfoDao;
import com.hb.sys.manage.baseinfo.student.dao.StudentInfoDao;
import com.hb.sys.manage.baseinfo.student.entity.StudentInfo;
import com.hb.sys.tools.excel.ExcelUtil;
import com.hb.sys.tools.tlds.dict.dao.DictDao;

/**
 * 分数上传service
 * @author hanbin
 * @date 2018年4月9日
 */
@Service
public class FractionalUploadService {

	@Autowired
	private StudentInfoDao studentInfoDao;
	@Autowired
	private GlobalDao globalDao;
	@Autowired
	private DictDao dictDao;
	@Autowired
	ClasssInfoDao classsInfoDao;
	/**
	 * 导出
	 * @param list
	 * @return
	 */
   /* public HSSFWorkbook export(List<StudentInfo> list) {    
    	String[] excelHeader = { "学号","姓名","密码","性别","联系电话","班级","分数"};  
    	int[] excelHeaderWidth = {150,150,150,150,150,150,150 };  
        HSSFWorkbook wb = new HSSFWorkbook();    
        HSSFSheet sheet = wb.createSheet("分数");    
        HSSFRow row = sheet.createRow((int) 0);    
        HSSFCellStyle style = wb.createCellStyle();    
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);    
    
        for (int i = 0; i < excelHeader.length; i++) {    
            HSSFCell cell = row.createCell(i);    
            cell.setCellValue(excelHeader[i]);    
            cell.setCellStyle(style);    
            sheet.autoSizeColumn(i);    
        }    
     // 设置列宽度（像素）  
        for (int i = 0; i < excelHeaderWidth.length; i++) {  
            sheet.setColumnWidth(i, 32 * excelHeaderWidth[i]);  
        }  
    
        for (int i = 0; i < list.size(); i++) {    
            row = sheet.createRow(i + 1);    
            StudentInfo student = list.get(i);    
            row.createCell(0).setCellValue(student.getS_id());
            row.createCell(1).setCellValue(student.getS_name()); 
            row.createCell(2).setCellValue(student.getS_pw());  
            row.createCell(3).setCellValue(dictDao.getDictLabel(student.getS_sex(), "s_sex"));    
            row.createCell(4).setCellValue(student.getS_phone());
            row.createCell(5).setCellValue(student.getClasss().getC_name());
            row.createCell(6).setCellValue(student.getS_score());
            
        }    
        return wb;    
    }    
	*/
	  /**
		 * 导出论文选择结果
		 * @param list
		 * @return
		 */
	    public HSSFWorkbook export(List<StudentInfo> list) {    
	    	String[] excelHeader = { "学号","姓名","论文题目","指导老师","总分"};  
	    	int[] excelHeaderWidth = {150,100,300,150,100};  
	        HSSFWorkbook wb = new HSSFWorkbook();    
	        HSSFSheet sheet = wb.createSheet("基本信息");    
	        HSSFRow row = sheet.createRow((int) 0);    
	        HSSFCellStyle style = wb.createCellStyle();    
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);    
	    
	        for (int i = 0; i < excelHeader.length; i++) {    
	            HSSFCell cell = row.createCell(i);    
	            cell.setCellValue(excelHeader[i]);    
	            cell.setCellStyle(style);    
	            sheet.autoSizeColumn(i);    
	        }    
	     // 设置列宽度（像素）  
	        for (int i = 0; i < excelHeaderWidth.length; i++) {  
	            sheet.setColumnWidth(i, 32 * excelHeaderWidth[i]);  
	        }  
	    
	        for (int i = 0; i < list.size(); i++) {    
	            row = sheet.createRow(i + 1);    
	            StudentInfo student = list.get(i);    
	            row.createCell(0).setCellValue(student.getS_id());
	            row.createCell(1).setCellValue(student.getS_name()); 
	            if(student.getPager()!=null){
	            	row.createCell(2).setCellValue(student.getPager().getP_name());
	            }else{
	            	row.createCell(2).setCellValue("");
	            }
	            if(student.getTeacher()!=null){
	            	row.createCell(3).setCellValue(student.getTeacher().getT_name());
	            }else{
	            	row.createCell(3).setCellValue("");
	            }
	           
	            row.createCell(7).setCellValue(""); 
	     
	        }    
	        return wb;    
	    }  
   
   /* public void importExcelInfo(InputStream in, MultipartFile file) throws Exception{  
        List<List<Object>> listob = ExcelUtil.getBankListByExcel(in,file.getOriginalFilename());  
        List<StudentInfo> studentList = new ArrayList<StudentInfo>();  
        //遍历listob数据，把数据放到List中  
        for (int i = 0; i < listob.size(); i++) {  
            List<Object> ob = listob.get(i);  
            StudentInfo student = studentInfoDao.get(globalDao.sid2id(String.valueOf(ob.get(0))));  
            //设置编号  
            //salarymanage.setSerial(SerialUtil.salarySerial());  
            //通过遍历实现把每一列封装成一个model中，再把所有的model用List集合装载  
            student.setS_id(String.valueOf(ob.get(0)));
            student.setS_name(String.valueOf(ob.get(1))); 
            student.setS_pw(String.valueOf(ob.get(2)));  
            student.setS_sex(dictDao.getDictValue(String.valueOf(ob.get(3)), "s_sex"));    
            student.setS_phone(String.valueOf(ob.get(4)));    
            student.setClasss(classsInfoDao.findClasssByName(String.valueOf(ob.get(5))));    
            student.setS_score(String.valueOf(ob.get(6)));
        
           studentList.add(student);
        }  
        //批量更新
        for(StudentInfo s:studentList)
        {		s.setId(globalDao.sid2id(s.getS_id()));
        		studentInfoDao.update(s);
        	
        }
        
    } */
	    public void importExcelInfo(InputStream in, MultipartFile file) throws Exception{  
	        List<List<Object>> listob = ExcelUtil.getBankListByExcel(in,file.getOriginalFilename());  
	        List<StudentInfo> studentList = new ArrayList<StudentInfo>();  
	        //遍历listob数据，把数据放到List中  
	        for (int i = 0; i < listob.size(); i++) {  
	            List<Object> ob = listob.get(i);  
	            StudentInfo student = studentInfoDao.get(globalDao.sid2id(String.valueOf(ob.get(0))));  
	            //设置编号  
	            //salarymanage.setSerial(SerialUtil.salarySerial());  
	            //通过遍历实现把每一列封装成一个model中，再把所有的model用List集合装载  
	           
	            student.setS_score(String.valueOf(ob.get(2)));
	        
	           studentList.add(student);
	        }  
	        //批量更新
	        for(StudentInfo s:studentList)
	        {		s.setId(globalDao.sid2id(s.getS_id()));
	        		studentInfoDao.update(s);
	        	
	        }
	        
	    }
	    /**
	  		 * 导出成绩导入模版
	  		 * @param list
	  		 * @return
	  		 */
	  	    public HSSFWorkbook templete(List<StudentInfo> list) {    
	  	    	String[] excelHeader = { "学号","姓名","总分"};  
	  	    	int[] excelHeaderWidth = {150,100,100};  
	  	        HSSFWorkbook wb = new HSSFWorkbook();    
	  	        HSSFSheet sheet = wb.createSheet("基本信息");    
	  	        HSSFRow row = sheet.createRow((int) 0);    
	  	        HSSFCellStyle style = wb.createCellStyle();    
	  	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);    
	  	    
	  	        for (int i = 0; i < excelHeader.length; i++) {    
	  	            HSSFCell cell = row.createCell(i);    
	  	            cell.setCellValue(excelHeader[i]);    
	  	            cell.setCellStyle(style);    
	  	            sheet.autoSizeColumn(i);    
	  	        }    
	  	     // 设置列宽度（像素）  
	  	        for (int i = 0; i < excelHeaderWidth.length; i++) {  
	  	            sheet.setColumnWidth(i, 32 * excelHeaderWidth[i]);  
	  	        }  
	  	    
	  	        for (int i = 0; i < list.size(); i++) {    
	  	            row = sheet.createRow(i + 1);    
	  	            StudentInfo student = list.get(i);    
	  	            row.createCell(0).setCellValue(student.getS_id());
	  	            row.createCell(1).setCellValue(student.getS_name()); 
	  	            row.createCell(2).setCellValue(""); 
	  	     
	  	        }    
	  	        return wb;    
	  	    }  
}
