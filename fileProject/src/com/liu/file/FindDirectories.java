package com.liu.file;

import java.io.File;
import java.io.IOException;
/**
 * 打印出用命令行参数指定路径下的所有目录及子目录，主方法的嵌套调用是为了遍历该路径下的下一级目录。
 * 默认条件下显示文件所在的当前目录
 * @author Administrator
 *
 */
public class FindDirectories {
	public static void main(String[] args) {
		File pathName=new File("f://txt");
		pathName.mkdirs();		//创建文件夹
		System.out.println(pathName.exists());	//是否存在
		System.out.println(pathName.isFile());		//是否是文件
		System.out.println(pathName.isDirectory());		//是否是文件夹
		if(args.length==0) args=new String[] {".."};
		try {
		File patNam=new File(args[0]);		//建立文件(目录)对象
		String[] fileNames=patNam.list();	//路径文件名数组赋值
		for(int i=0;i<fileNames.length;i++) {
			File f=new File(patNam.getPath(),fileNames[i]);
				if(f.isDirectory()) {//如果是文件夹
						System.out.println(f.getCanonicalPath());//输出规范文件路径
						main(new String[] {f.getPath()});
					} 
				}
		}catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
			}
		}
	}
