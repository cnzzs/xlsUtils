package net.zz.validator.constraintvalidators;

import net.zz.validator.constraints.SingleFileUpload;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by ZaoSheng on 2014/10/22.
 *
 * @author ZaoSheng
 *         文件合法验证
 */
public class SingleFileUploadValidate implements ConstraintValidator<SingleFileUpload, MultipartFile> {


    private final static Map<String, Object> map = new HashMap<String, Object>();
    private final static String configPath = "upload-config.properties";
    private static String _contentTypeKey = "_allow_content_type";
    private static String _sizeKey = "_allow_size_kb";

    private long size;
    private String[] contentTypes;
    private String useConfig;
    private boolean allowEmpty;

    static {
        try {
            Configuration config = new PropertiesConfiguration(configPath);
            Iterator<String> keys = config.getKeys();
            String key = null;
            while (keys.hasNext()) {
                key = keys.next();
                if (key.endsWith(_contentTypeKey)) {
                    map.put(key, config.getStringArray(key));
                } else if (key.endsWith(_sizeKey)) {
                    map.put(key, config.getString(key));
                }
            }
        } catch (ConfigurationException e) {

            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SingleFileUploadValidate p = new SingleFileUploadValidate();

    }

    @Override
    public void initialize(SingleFileUpload uploadFileValidate) {
        this.allowEmpty = uploadFileValidate.allowEmpty();
        this.size = uploadFileValidate.size();
        this.contentTypes = uploadFileValidate.contentTypes();
        this.useConfig = uploadFileValidate.useConfig();
        if (useConfig != null && !"".equals(useConfig.trim())) {
            String[] _contentTypes = (String[]) map.get(useConfig + _contentTypeKey);
            String sizeStr = (String) map.get(useConfig + _sizeKey);
            if (_contentTypes != null) {
                contentTypes = _contentTypes;
            }
            if (sizeStr != null) {
                size = Long.valueOf(sizeStr);
            }
        }
        this.size = size * 1024;
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {

        System.out.println(multipartFile.getContentType());
        if (multipartFile == null || multipartFile.getSize() == 0) {
            return allowEmpty;
        }

        return (allowSize(multipartFile) && allowContentType(multipartFile));
       /* if (size == 0 || multipartFile.getSize() <= size) {
            if (contentTypes.length > 0) {
                for (int i = 0; i < contentTypes.length; i++) {
                    if (contentTypes[i].equals(multipartFile.getContentType())) {
                        return true;
                    }
                }
                return false;
            }
            return true;
        }*/
    }

    private boolean allowSize(MultipartFile multipartFile) {

        return (size == 0 || multipartFile.getSize() <= size);
    }

    private boolean allowContentType(MultipartFile multipartFile) {
        // 不限制
        if (contentTypes.length == 0)
            return true;
        for (int i = 0; i < contentTypes.length; i++) {
            if (contentTypes[i].equals(multipartFile.getContentType())) {
                return true;
            }
        }
        return false;
    }


}
