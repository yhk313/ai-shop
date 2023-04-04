package com.shop.util;

import java.io.FileOutputStream;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {//파일저장
	public static void saveFile(MultipartFile mf, String custdir) {
		byte [] data;
		String imgname = mf.getOriginalFilename();
		try {
			data = mf.getBytes();
			FileOutputStream fo2 = 
					new FileOutputStream(custdir+imgname);
			fo2.write(data);
			fo2.close();
		}catch(Exception e) {
			
		}
		
	}
	
}




