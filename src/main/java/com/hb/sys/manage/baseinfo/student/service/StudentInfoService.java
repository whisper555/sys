/**
 * 
 */
package com.hb.sys.manage.baseinfo.student.service;

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
import com.hb.sys.manage.baseinfo.pager.dao.PagerInfoDao;
import com.hb.sys.manage.baseinfo.student.dao.StudentInfoDao;
import com.hb.sys.manage.baseinfo.student.entity.StudentInfo;
import com.hb.sys.manage.baseinfo.teacher.dao.TeacherInfoDao;
import com.hb.sys.tools.Utils.StrUtils;
import com.hb.sys.tools.excel.ExcelUtil;
import com.hb.sys.tools.gen.CrudService;
import com.hb.sys.tools.tlds.dict.dao.DictDao;;

/**
 * 学生基本信息查询service
 * 
 * @author hanbin
 * @version 2017年10月27日
 */
@Service
public class StudentInfoService extends CrudService<StudentInfoDao, StudentInfo> {
	
	@Autowired
	PagerInfoDao pagerInfoDao;
	@Autowired
	TeacherInfoDao teacherInfoDao;
	@Autowired
	ClasssInfoDao classsInfoDao;
	@Autowired
	DictDao dictDao; 
	@Autowired
	GlobalDao globalDao;

	// 用于教师端教师选择学生
	public void updateTeacher(String sid, String tid,String pid) {
		dao.updateTeacher(sid, globalDao.tid2id(tid), pid);
	};
	// 用于教师端教师更新评分
		public void updateScore(String sid, String score) {
			dao.updateScore(sid, score);//sid为id
		};

	// 用于教师端教师删除学生
	public void updateTeacherNull(String sid) {
		dao.updateTeacherNull(sid);
	};

	// 用于教师端教师查询我的学生
	public List<StudentInfo> findMyStudentList(String tid) {
		return dao.findMyStudentList(globalDao.tid2id(tid));
	};

	// 用于教师端查询第一志愿列表（第一志愿是此教师，并且teacherid为空）
	public List<StudentInfo> findFirstList(String tid) {
		return dao.findFirstList(globalDao.tid2id(tid));
	};

	// 用于教师端查询第二志愿列表（第二志愿是此教师，并且teacherid为空）
	public List<StudentInfo> findSecondList(String tid) {
		return dao.findSecondList(globalDao.tid2id(tid));
	};

	// 用于教师端查询第三志愿列表（第三志愿是此教师，并且teacherid为空）
	public List<StudentInfo> findThirdList(String tid) {
		return dao.findThirdList(globalDao.tid2id(tid));
	};

	// 学生端设置第一志愿
	public void setFirstOrder(String sid, String pid) {
		dao.setFirstOrder(globalDao.sid2id(sid), pid);
	}

	// 学生端设置第二志愿
	public void setSecondOrder(String sid, String pid) {
		dao.setSecondOrder(globalDao.sid2id(sid), pid);
	}

	// 学生端设置第三志愿
	public void setThirdOrder(String sid, String pid) {
		dao.setThirdOrder(globalDao.sid2id(sid), pid);
	}
	//教师端查询没有教师的学生列表
	public List<StudentInfo> findLackStuList(){
		return dao.findLackStuList();
	}
	//教师端查询没有教师的学生列表 带参数(班级id)
	public List<StudentInfo> findLackStuListParam(String cid){
		return dao.findLackStuListParam(cid);
	}
	//教师端选择学生
	public void selectstu(String id,String tid){
		dao.selectstu(id,tid);
	}
	
	//教师端选择学生ajax 判断已经选择学生人数是否超
	public boolean countlimit(String id){
		if(!(dao.countlimit(id)<StrUtils.SringToInt(teacherInfoDao.get(id).getT_max()))){
			return false;
		}else {
			return true;
		}
	}
	
	//管理员：分配教师学生列表
	public List<StudentInfo> findAllocationList(){
		return dao.findAllocationList();
	}
	//管理员：没有论文的学生列表
	public List<StudentInfo> findNoAllocationList(){
		return dao.findNoAllocationList();
	}
	/**
	 * 导出
	 * @param list
	 * @return
	 */
    public HSSFWorkbook export(List<StudentInfo> list) {    
    	String[] excelHeader = { "学号","姓名","密码","性别","联系电话","班级"};  
    	int[] excelHeaderWidth = {150,150,150,150,150,150 };  
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
            row.createCell(2).setCellValue(student.getS_pw());  
            row.createCell(3).setCellValue(dictDao.getDictLabel(student.getS_sex(), "s_sex"));    
            row.createCell(4).setCellValue(student.getS_phone());
            row.createCell(5).setCellValue(student.getClasss().getC_name());
               
            
        }    
        return wb;    
    }  
    /**
	 * 导出论文选择结果
	 * @param list
	 * @return
	 */
    public HSSFWorkbook exportResult(List<StudentInfo> list) {    
    	String[] excelHeader = { "学号","姓名","论文题目","指导老师","指导教师分数","评阅教师分数","评阅小组分数","总分"};  
    	int[] excelHeaderWidth = {150,100,300,150,100,100,100,100};  
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
            row.createCell(4).setCellValue(student.getS_score_t()); 
            row.createCell(5).setCellValue(""); 
            row.createCell(6).setCellValue(""); 
            row.createCell(7).setCellValue(""); 
     
        }    
        return wb;    
    }  
    public void importExcelInfo(InputStream in, MultipartFile file) throws Exception{  
        List<List<Object>> listob = ExcelUtil.getBankListByExcel(in,file.getOriginalFilename());  
        List<StudentInfo> studentList = new ArrayList<StudentInfo>();  
        //遍历listob数据，把数据放到List中  
        for (int i = 0; i < listob.size(); i++) {  
            List<Object> ob = listob.get(i);  
            StudentInfo student = new StudentInfo();  
            //设置编号  
            //salarymanage.setSerial(SerialUtil.salarySerial());  
            //通过遍历实现把每一列封装成一个model中，再把所有的model用List集合装载  
           student.setS_id(String.valueOf(ob.get(0)));
           student.setS_name(String.valueOf(ob.get(1))); 
           student.setS_pw(String.valueOf(ob.get(2)));  
           student.setS_sex(dictDao.getDictValue(String.valueOf(ob.get(3)), "s_sex"));    
           student.setS_phone(String.valueOf(ob.get(4)));    
           student.setClasss(classsInfoDao.findClasssByName(String.valueOf(ob.get(5))));    
           student.setS_flag("0");
        
           studentList.add(student);
        }  
        //批量插入  
        for(StudentInfo s:studentList){
        	if(!(dao.exists(s.getS_id()).size()>0)){
        		dao.insert(s);
        	}
        }
        
    }  
}
