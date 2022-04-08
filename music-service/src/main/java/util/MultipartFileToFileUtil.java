package util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * MultipartFile 转 File
 * @author ZAY
 */
public class MultipartFileToFileUtil {
    public static File multipartFileToFile(MultipartFile file ) throws Exception {
        File toFile;
        if(file.getSize()<=0){
            toFile = null;
        }else {
            InputStream ins= file.getInputStream();;
            toFile = new File(file.getOriginalFilename());
            toFile = inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }
    private static File inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
