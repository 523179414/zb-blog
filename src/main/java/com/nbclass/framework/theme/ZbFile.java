package com.nbclass.framework.theme;

import lombok.Data;

import java.util.Comparator;
import java.util.List;

/**
 * ZbFile 文件
 *
 * @version V1.0
 * @date 2019/10/11
 * @author nbclass
 */
@Data
public class ZbFile implements Comparator<ZbFile> {

    private String name;

    private String path;

    private Boolean isFile;

    private Boolean isEdit;

    private List<ZbFile> node;

    @Override
    public int compare(ZbFile leftFile, ZbFile rightFile) {
        if (leftFile.isFile && !rightFile.isFile) {
            return 1;
        }

        if (!leftFile.isFile && rightFile.isFile) {
            return -1;
        }

        return leftFile.getName().compareTo(rightFile.getName());
    }
}
