package com.cwh.springbootMybatis.util;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
/**
 * @since File upload tool class
 * @author HuWang
 *
 */
public class FileUtils {
    private static final Logger logger = Logger.getLogger(FileUtils.class);

  /**
   * 
   * @param multipartFile
   * @param dirParam
   * @return
   */
    public static String upload(MultipartFile multipartFile, String dirParam) {
        String saveDir = "";
        if (dirParam.contains("\\")) {
            saveDir = dirParam.substring(0, dirParam.lastIndexOf("\\")) + File.separator + "upload" + File.separator;
        } else {
            saveDir = dirParam.substring(0, dirParam.lastIndexOf("/")) + File.separator + "upload" + File.separator;
        }
        if (multipartFile == null || multipartFile.isEmpty()) {
            return null;
        }
        String relativePath = "";
        String originalName = multipartFile.getOriginalFilename();
        String fileType = originalName.substring(originalName.lastIndexOf("."), originalName.length());
        String saveFileName = RDateUtils.formatDate(new Date(), RDateUtils.TIME_STAMP_PATTERN);
        File dir = new File(saveDir.toString());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        relativePath = saveDir + saveFileName + fileType;
        try {
            multipartFile.transferTo(new File(relativePath));
        } catch (IOException e) {
            logger.error("保存文件失败", e);
            e.printStackTrace();
        }
        String res = relativePath.substring(relativePath.indexOf("upload"), relativePath.length()).replaceAll("\\\\", "/");
        logger.info("文件" + originalName + "保存的相对路径" + res);
        return res;
    }

    /**
     * 
     * @param multipartFile
     * @param dirParam
     * @return
     */
    public static String uploads(MultipartFile multipartFile, String dirParam) {
        String saveDir = "";
        if (dirParam.contains("\\")) {
            saveDir = dirParam.substring(0, dirParam.lastIndexOf("\\")) + File.separator + "uploada" + File.separator;
        } else {
            saveDir = dirParam.substring(0, dirParam.lastIndexOf("/")) + File.separator + "uploada" + File.separator;
        }

        if (multipartFile == null || multipartFile.isEmpty()) {
            return null;
        }
        String relativePath = "";
        String originalName = multipartFile.getOriginalFilename();
        String fileType = originalName.substring(originalName.lastIndexOf("."), originalName.length());
        String saveFileName = RDateUtils.formatDate(new Date(), RDateUtils.TIME_STAMP_PATTERN);
        File dir = new File(saveDir.toString());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        relativePath = saveDir + saveFileName + fileType;
        try {
            multipartFile.transferTo(new File(relativePath));
        } catch (IOException e) {
            logger.error("保存文件失败", e);
            e.printStackTrace();
        }
        String res = relativePath.substring(relativePath.indexOf("uploada"), relativePath.length()).replaceAll("\\\\", "/");
        logger.info("文件" + originalName + "保存的相对路径" + res);
        return res;
    }

    /**
     * 
     * @param multipartFiles
     * @param dirParam
     * @return
     */
    public static String uploadArrs(MultipartFile[] multipartFiles, String dirParam) {
        StringBuffer saveDir = new StringBuffer();
        saveDir.append("E:").append(File.separator)
                .append("upload").append(File.separator)
                .append(dirParam).append(File.separator);

        if (multipartFiles == null || multipartFiles.length < 1) {
            return "error";
        }
        String relativePath = "";
        StringBuffer res = new StringBuffer();
        File dir = new File(saveDir.toString());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        for (MultipartFile multipartFile : multipartFiles) {
            String originalName = multipartFile.getOriginalFilename();
            String fileType = originalName.substring(originalName.lastIndexOf("."), originalName.length());
            String saveFileName = RDateUtils.formatDate(new Date(), RDateUtils.TIME_STAMP_PATTERN);
            relativePath = saveDir + saveFileName + fileType;
            try {
                multipartFile.transferTo(new File(relativePath));
            } catch (IOException e) {
                logger.error("保存文件失败", e);
                e.printStackTrace();
            }
            res.append(relativePath.substring(relativePath.indexOf("upload"), relativePath.length()).replaceAll("\\\\", "/")).append(",");
            logger.info("文件" + originalName + "保存的相对路径" + res.toString());
        }

        return res.toString().substring(0, res.length() - 1);
    }

    /**
     * 
     * @param filePath
     * @param response
     * @throws IOException
     */
    public static void download(String filePath, HttpServletResponse response) throws IOException {
        File file = new File(filePath);
        String fileName = file.getName().replaceAll(" ", "");
        InputStream fis = null;
        byte[] bytes = null;
        try {
            fis = new BufferedInputStream(new FileInputStream(file));
            bytes = new byte[fis.available()];
            fis.read(bytes);
        } catch (FileNotFoundException e) {
            logger.error("文件流创建失败", e);
            e.printStackTrace();
        } finally {
            fis.close();
        }
        response.reset();
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("utf-8"), "iso8859-1"));
        response.addHeader("Content-Length", String.valueOf(file.length()));
        OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/octet-stream");
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
    }
}
