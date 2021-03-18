package com.ecoR.IO;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 * BFS实现目录拷贝
 */
public class CopyDirectoryTest {
    public static void main(String[] args) {
        File srcFile =new File("E:\\test");//源目录
        File destFile =new File("F:\\test(copy)");//目标目录
        copyDirectory(srcFile,destFile);
    }
    public static void copyDirectory(File srcFile, File destFile){
        Queue<File> queue =new LinkedList<>();//盛放源File
        Queue<File> queue1=new LinkedList<>();//盛放目标File
        queue.offer(srcFile);
        queue1.offer(destFile);
        while(!queue.isEmpty()){
            File[] files=queue.poll().listFiles();//取出源File下的所有File
            File dest=queue1.poll();//取出目标File
            int size=files.length;
            for(int i=0;i<size;i++){//遍历源File下的所有File
                File src=files[i];
                if(src.isFile()){//如果是文件，拷到目标目录下
                    copyFile(src,dest);
                }else{//如果是目录，在源目录下新建同名空目录
                    File newDir=new File(dest,src.getName());
                    newDir.mkdir();//建立同名目录
                    //将源目录和新建目录分别加入相应Queue
                    queue.offer(src);
                    queue1.offer(newDir);
                }
            }
        }
    }

    /**
     * 拷贝文件方法
     * @param src 源目录
     * @param dest 目标目录
     */
    public static void copyFile(File src,File dest){
        FileInputStream in=null;
        FileOutputStream out=null;
        try {
            File newFile= new File(dest,src.getName());
            newFile.createNewFile();
            in =new FileInputStream(src);
            out =new FileOutputStream(newFile);
            byte[] bytes =new byte[1024*1024];
            int readCount=0;
            while((readCount=in.read(bytes))!=-1){
                out.write(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
