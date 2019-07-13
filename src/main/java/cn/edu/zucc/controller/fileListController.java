package cn.edu.zucc.controller;


//import org.apache.tomcat.util.http.fileupload.IOUtils;
//import org.apache.tomcat.jni.Buffer;
import cn.edu.zucc.common.R;
import cn.edu.zucc.domain.entity.AnotherProject;
import org.apache.tomcat.util.http.fileupload.IOUtils;
//import org.apache.tomcat.util.http.parser.MediaType;
//import org.apache.tomcat.util.http.parser.MediaType;
import org.hibernate.MultiIdentifierLoadAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
//import sun.misc.IOUtils;

//import org.apache.commons.io.IOUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value="/uploads")
public class fileListController {
    @Autowired
    ResourceLoader loader;
    private static final Logger log = LoggerFactory.getLogger(fileListController.class);


    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/uploadfile")
    @ResponseBody
    public String upload(@RequestParam("file")MultipartFile file) throws IOException {
        String fileName=file.getOriginalFilename();
        String path="C:\\Users\\mitty\\Desktop\\信息系统设计与开发\\uploadtest\\"+file.getOriginalFilename();

        log.info("[文件类型] - [0]",file.getContentType());
        log.info("[文件名称] - [0]",file.getOriginalFilename());
        log.info("[文件大小] - [0]",file.getSize());
        file.transferTo(new File("C:\\Users\\mitty\\Desktop\\信息系统设计与开发\\uploadtest\\"+file.getOriginalFilename()));
        Map<String,String> result = new HashMap<>(16);
        result.put("ContentType",file.getContentType());
        result.put("fileName",file.getOriginalFilename());
        result.put("fileSize",file.getSize()+"");
        return path;
    }

    @PostMapping("/getFileList")
    @ResponseBody
    public ArrayList<String> getFileList() throws IOException {

        File file = new File("C:\\Users\\mitty\\Desktop\\信息系统设计与开发\\uploadtest");
        ArrayList<String> arrayList = new ArrayList<String>();
        for(int i = 0;i < file.list().length;i++){
            arrayList.add(file.list()[i]);
        }
        return arrayList;
    }

    @PostMapping("/deleteFileByName")
    @ResponseBody
    public String deleteFileByName(@RequestBody String name) throws IOException {
        File file = new File("C:\\Users\\mitty\\Desktop\\信息系统设计与开发\\uploadtest\\"+name.substring(5));
        if(!file.exists()){
            return "文件不存在";
        }else{
            file.delete();
            return "文件删除成功";
        }
    }

    @PostMapping("/downloadFileByName")
    @ResponseBody
    public ResponseEntity downloadFileByName(@RequestBody String name) throws IOException {
        String filePath = "C:\\Users\\mitty\\Desktop\\信息系统设计与开发\\uploadtest\\"+name.substring(5);
        File file = new File(filePath);
        HttpHeaders headers = new HttpHeaders();

        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");

        headers.add("Content-Disposition", "attachment; filename=" + System.currentTimeMillis() + ".xls");

        headers.add("Pragma", "no-cache");

        headers.add("Expires", "0");

        headers.add("Last-Modified", new Date().toString());

        headers.add("ETag", String.valueOf(System.currentTimeMillis()));
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new FileSystemResource(file));
    }

    //@UserLoginToken
    @ResponseBody
    @RequestMapping(value = "/downloadfile/{prodocument}",method = RequestMethod.POST)
    public R<String> downloadfile(@PathVariable String prodocument) throws IOException {
        String url1 = "C:\\Users\\mitty\\Desktop\\信息系统设计与开发\\uploadtest\\"+prodocument;// 源文件路径
        String url2 = "C:\\Users\\mitty\\Desktop\\信息系统设计与开发\\downloadtest\\"+prodocument;          // 复制到目标路径
        FileInputStream in = new FileInputStream(new File(url1));
        FileOutputStream out = new FileOutputStream(new File(url2));
        byte[] buff = new byte[512];
        int n = 0;
        System.out.println("复制文件：" + "\n" + "源路径：" + url1 + "\n" + "目标路径："
                + url2);
        while ((n = in.read(buff)) != -1) {
            out.write(buff, 0, n);
        }
        out.flush();
        in.close();
        out.close();
        return R.success("已成功下载"+prodocument);
    }
}
