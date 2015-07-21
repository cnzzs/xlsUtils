package net.zz.xls.demo;

import net.zz.validator.constraints.SingleFileUpload;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by ZaoSheng on 15-5-27.
 */
public class ExcelRequestBody {

    @SingleFileUpload(
            message = "上传的文件错误，请检查文件类型是否正确，文件大小是否超出等",
            useConfig = "excel"
    )
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }


}
