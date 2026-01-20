//package com.velox.bookseller.controller;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import jakarta.servlet.http.HttpServletResponse;
//
//@RestController
//@RequestMapping("/images")
//@CrossOrigin(origins = "http://localhost:3000")
//public class ImageController {
//	
//	@GetMapping("/{filename:.+}")
//	public void getImage(@PathVariable String filename, HttpServletResponse response) throws IOException {
//		Path imagePath = Paths.get("images",filename);
//		
//		if(Files.exists(imagePath)) {
//		 	String contentType = Files.probeContentType(imagePath);
//		 	Files.copy(imagePath, response.getOutputStream());
//		 	response.getOutputStream().flush();
//		}else {
//			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//			response.getWriter().write("Image Not Found:"+ filename);
//		}
//	}
//}
//
//
//
//
//
//
//
//
//
//
//
//



