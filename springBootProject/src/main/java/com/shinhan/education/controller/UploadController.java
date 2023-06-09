package com.shinhan.education.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shinhan.education.UploadFileUtils;

 

@Controller
public class UploadController {
	
	//application.properties에 설정된 경로를 가져옴. 
	@Value("${spring.servlet.multipart.location}")
	String uploadPath ;

	@GetMapping("/shop/uploadTest") //UploadTest.html 불러옴
	public void upload() {
		
	}
	
	@PostMapping("/shop/register")
	//files: 파일 여러개 
	public String register(@RequestParam MultipartFile[] files, HttpServletRequest req) throws IOException, Exception {
		String imgUploadPath = uploadPath + File.separator + "upload"; 
	
		//upload 폴더가 없으면 upload폴더를 생성해줘라
//		File f2 = new File(imgUploadPath);
//		if(!f2.exists()) {
//			f2.mkdir();
//		}
		
		String ymdPath = UploadFileUtils.calcPath(imgUploadPath); //upload폴더가 생김. (년월일 폴더도 생성)
		String fileName= null;
		for(MultipartFile f:files) {
			String originFileName = f.getOriginalFilename();
			System.out.println("originFileName:" + originFileName);
			if(originFileName!=null && !originFileName.equals("")) {
				fileName = UploadFileUtils.fileUpload(imgUploadPath, originFileName,
						f.getBytes(), ymdPath); //실제 업로드. 
				
			}else {
				fileName = File.separator + "images" + File.separator + "hide.png"; //잘못오면 없는 이미지 하나 만들어서 넣는다. 
			}
			System.out.println(fileName);
		}
		return "redirect:uploadTest";
	}
}
