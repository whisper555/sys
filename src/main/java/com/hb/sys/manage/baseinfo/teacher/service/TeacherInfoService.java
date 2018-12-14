/**
 * 
 */
package com.hb.sys.manage.baseinfo.teacher.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hb.sys.global.dao.GlobalDao;
import com.hb.sys.manage.baseinfo.department.dao.DepartmentInfoDao;
import com.hb.sys.manage.baseinfo.student.dao.StudentInfoDao;
import com.hb.sys.manage.baseinfo.student.entity.StudentInfo;
import com.hb.sys.manage.baseinfo.teacher.dao.TeacherInfoDao;
import com.hb.sys.manage.baseinfo.teacher.entity.TeacherInfo;
import com.hb.sys.tools.excel.ExcelUtil;
import com.hb.sys.tools.gen.CrudService;
import com.hb.sys.tools.tlds.dict.dao.DictDao;;

/**
 * @author hanbin
 * @version 2017年10月24日
 */
@Service
public class TeacherInfoService extends CrudService<TeacherInfoDao,TeacherInfo>{
	@Autowired
	DictDao dictDao;
	@Autowired
	StudentInfoDao studentInfoDao;
	@Autowired
	DepartmentInfoDao departmentInfoDao;
	@Autowired
	GlobalDao globalDao;
	
	/*
	 * 获取教师所在院系
	 */
	public  List<Map<String,String>> getdepartmentlist(){
		return dao.getdepartmentList();
	};
	
	//学生端选择志愿找出wish中没有的教师列表
	public List<TeacherInfo> findListForWish(StudentInfo s){
		return dao.findListForWish(s);
	};
	
	//管理员端 分配教师 获取当前可以使用的教师
	public List<Map<String,String>> findAllocationTeacherList(){
		return dao.findAllocationTeacherList();
	}
	/**
	 * 导出
	 * @param list
	 * @return
	 */
    public HSSFWorkbook export(List<TeacherInfo> list) {    
    	String[] excelHeader = { "工号","姓名","密码","所在部门","联系电话","邮箱","是否管理","最大带人数量","职称"};  
    	int[] excelHeaderWidth = {150,150,150,150,150,150,150,150,150 };  
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
            TeacherInfo teacher = list.get(i);    
            row.createCell(0).setCellValue(teacher.getT_id());
            row.createCell(1).setCellValue(teacher.getT_name()); 
            row.createCell(2).setCellValue(teacher.getT_pw());  
            row.createCell(3).setCellValue(teacher.getDepartment().getD_name());    
            row.createCell(4).setCellValue(teacher.getT_phone());   
            row.createCell(5).setCellValue(teacher.getT_mail());   
            row.createCell(6).setCellValue(dictDao.getDictLabel(teacher.getT_manager(), "t_manager"));   
            row.createCell(7).setCellValue(teacher.getT_max());    
            row.createCell(8).setCellValue(dictDao.getDictLabel(teacher.getT_pro(), "t_pro"));    
        }    
        return wb;    
    }    
    public void importExcelInfo(InputStream in, MultipartFile file) throws Exception{  
        List<List<Object>> listob = ExcelUtil.getBankListByExcel(in,file.getOriginalFilename());  
        List<TeacherInfo> teacherList = new ArrayList<TeacherInfo>();  
        //遍历listob数据，把数据放到List中  
        for (int i = 0; i < listob.size(); i++) {  
            List<Object> ob = listob.get(i);  
            TeacherInfo teacher = new TeacherInfo();  
            //设置编号  
            //salarymanage.setSerial(SerialUtil.salarySerial());  
            //通过遍历实现把每一列封装成一个model中，再把所有的model用List集合装载  
           teacher.setT_id(String.valueOf(ob.get(0)));
           teacher.setT_name(String.valueOf(ob.get(1))); 
           teacher.setT_pw(String.valueOf(ob.get(2)));  
           teacher.setDepartment(departmentInfoDao.findDepartmentByName(String.valueOf(ob.get(3))));    
           teacher.setT_phone(String.valueOf(ob.get(4)));    
           teacher.setT_mail(String.valueOf(ob.get(5)));    
           teacher.setT_manager(dictDao.getDictValue(String.valueOf(ob.get(6)), "t_manager"));   
           teacher.setT_max(String.valueOf(ob.get(7)));    
           teacher.setT_pro(dictDao.getDictValue(String.valueOf(ob.get(8)), "t_pro")); 
           teacherList.add(teacher);
        }  
        //批量插入  
        for(TeacherInfo t:teacherList){
        	if(!(dao.exists(t.getT_id()).size()>0)){
        		dao.insert(t);
        	} 
        }
        
    }

    /**
     * 管理员：没提交论文的教师列表
     * @return
     */
    public List<TeacherInfo> findNoPagerList(){
        return dao.findNoPagerList();
    }
    /**
     * 管理员：没有学生的教师列表
     */
    public List<TeacherInfo> findNoStudentList(){
        return dao.findNoStudentList();
    }
}