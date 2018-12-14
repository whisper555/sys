package com.hb.sys.tools.fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传controller
 * 
 * @author hanbin
 * @date 2018年3月8日
 */

public class FileUtil {
	

	public static String upload(@RequestParam MultipartFile file, @RequestParam String user_id,
			HttpServletRequest request) throws IOException {
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH时mm分ss秒");
		String res = sdf.format(new Date());
*/
		Date date = new Date();
		// uploads文件夹位置
		String rootPath = request.getSession().getServletContext().getRealPath("/files/");
		System.out.println(rootPath);
		// 原始名称
		String originalFileName = file.getOriginalFilename() ;
		// 新文件名
//		 String newFileName = res + originalFileName.substring(originalFileName.lastIndexOf("."));
		String newFileName = originalFileName.substring(0, originalFileName.lastIndexOf(".")) +"_"+date.getTime()+ originalFileName.substring(originalFileName.lastIndexOf("."));
		// 创建年月文件夹
//		Calendar date = Calendar.getInstance();
//		File dateDirs = new File(date.get(Calendar.YEAR) + File.separator + (date.get(Calendar.MONTH) + 1));
//		String url = rootPath + File.separator + dateDirs + File.separator + newFileName;
		String url = rootPath + File.separator + newFileName;
		// 新文件
		File newFile = new File(url);
		// 判断目标文件所在目录是否存在
		if (!newFile.getParentFile().exists()) {
			// 如果目标文件所在的目录不存在，则创建父目录
			newFile.getParentFile().mkdirs();
		}
		//System.out.println(newFile);

		// 将内存中的数据写入磁盘
		file.transferTo(newFile);
		

		return newFile.getPath();
	}

	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}

	// 删除文件夹
	// param folderPath 文件夹完整绝对路径
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void delete(String filename,HttpServletRequest request,HttpServletResponse response) {
		String path = request.getSession().getServletContext().getRealPath("/files/") + "\\"+filename;
		File file = new File(path);
		file.delete();
	}
	

	public static String downloadfile(String file,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		String localPath =  request.getSession().getServletContext().getRealPath("/files/" );//服务器本地保存图片的物理路径
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName=" + file);
		try {

			InputStream inputStream = new FileInputStream(new File(localPath+ File.separator + file));
//			InputStream inputStream = new FileInputStream(file);
			OutputStream os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			os.close();
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
