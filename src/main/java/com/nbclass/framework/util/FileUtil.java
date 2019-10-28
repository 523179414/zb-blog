package com.nbclass.framework.util;

import com.nbclass.framework.theme.ZbFile;
import com.nbclass.framework.theme.ZbTheme;
import com.nbclass.framework.exception.ZbException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Stream;


/**
 * FolderFileScanner
 *
 * @version V1.0
 * @date 2019/10/11
 * @author nbclass
 */
public class FileUtil {

    private static final String[] CAN_EDIT_SUFFIX = {".html", ".css", ".js", ".yaml", ".yml", ".properties"};


    public static void copyFolder(Path source, Path target) throws IOException {
        Files.walkFileTree(source, new SimpleFileVisitor<Path>() {

            private Path current;

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                current = target.resolve(source.relativize(dir).toString());
                Files.createDirectories(current);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.copy(file, target.resolve(source.relativize(file).toString()), StandardCopyOption.REPLACE_EXISTING);
                return FileVisitResult.CONTINUE;
            }
        });
    }


    public static List<ZbFile> listFiles(Path topPath, boolean recursion){
        List<ZbFile> files = listFiles(new ArrayList<>(), topPath, recursion);
        if (files != null) {
            files.sort(new ZbFile());
        }
        return  files;
    }

    public static List<ZbFile> listFileTree(Path topPath) {
        if (!Files.isDirectory(topPath)) {
            return null;
        }
        try (Stream<Path> pathStream = Files.list(topPath)) {
            List<ZbFile> themeFiles = new LinkedList<>();
            pathStream.forEach(path -> {
                ZbFile themeFile = new ZbFile();
                themeFile.setName(path.getFileName().toString());
                themeFile.setPath(path.toString());
                themeFile.setIsFile(Files.isRegularFile(path));
                themeFile.setIsEdit(isEditable(path));
                if (Files.isDirectory(path)) {
                    themeFile.setNode(listFileTree(path));
                }
                themeFiles.add(themeFile);
            });
            themeFiles.sort(new ZbFile());
            return themeFiles;
        } catch (IOException e) {
            throw new ZbException("Failed to list tree files");
        }
    }

    public static Map<String, ZbTheme> scanThemeFolder(Path topPath) {
        if (!Files.isDirectory(topPath)) {
            return null;
        }
        try (Stream<Path> pathStream = Files.list(topPath)) {
            Map<String,ZbTheme> resMap = new LinkedHashMap<>();
            pathStream.forEach(path -> {
                if (Files.isDirectory(path)) {
                    String s = readFile(Paths.get(path.toString()+"/setting.json"));
                    if(StringUtils.isNotEmpty(s)){
                        ZbTheme zbTheme=GsonUtil.fromJson(s,ZbTheme.class);
                        String id=path.getFileName().toString();
                        zbTheme.setId(id);
                        resMap.put(id,zbTheme);
                    }

                }
            });
            return resMap;
        } catch (IOException e) {
            throw new ZbException("Failed to scan system theme");
        }
    }

    private static List<ZbFile> listFiles(List<ZbFile> list, Path topPath, boolean recursion){
        if (!Files.isDirectory(topPath)) {
            return null;
        }
        try (Stream<Path> pathStream = Files.list(topPath)) {
            pathStream.forEach(path -> {
                if (Files.isDirectory(path)) {
                    if(recursion){
                        listFiles(list,path,true);
                    }
                }else{
                    ZbFile themeFile = new ZbFile();
                    themeFile.setName(path.getFileName().toString());
                    themeFile.setPath(path.toString());
                    themeFile.setIsFile(Files.isRegularFile(path));
                    themeFile.setIsEdit(isEditable(path));
                    list.add(themeFile);
                }

            });
            return list;
        } catch (IOException e) {
            throw new ZbException("Failed to list files");
        }
    }


    private static boolean isEditable(Path path) {
        boolean isEditable = Files.isReadable(path) && Files.isWritable(path);
        if (!isEditable) {
            return false;
        }
        for (String suffix : CAN_EDIT_SUFFIX) {
            if (path.toString().endsWith(suffix)) {
                return true;
            }
        }
        return false;
    }

    private static String readFile(Path path){
        try {
            return new String(Files.readAllBytes(path));
        } catch (IOException e) {
            return null;
        }
    }
    public static void main(String[] args) {
        try {
             String filePath = ResourceUtils.getFile("classpath:templates/theme/zblog/").getPath();
            List<ZbFile> files = FileUtil.listFiles(Paths.get(filePath), true);
            List<ZbFile> files1  = FileUtil.listFiles(Paths.get(filePath), false);
            List<ZbFile> files2 = FileUtil.listFileTree(Paths.get(filePath));
            System.out.println(GsonUtil.toJson(files2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}