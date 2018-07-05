package com.liu.zip;

import java.awt.EventQueue;

import javax.swing.JFrame;
/**
 *用下拉列表来检索用户选中的压缩文件的内部文件项，在带有滚动机制的文本区中显示出来。
 *这里的时间处理中涉及两个压缩输入流:open菜单项的时间处理负责天下下拉列表项，下拉列表事件处理负责将
 *选中的文件加载到文本区中。两个事件处理都应用了JDK1.6的javax.swing .SwingWorker类。该类是使用后台
 *线程处理长时间GUI交互任务的抽象类。压缩流本质上是字节流。
 *程序主方法将含有JFrame的主方法体置于迟发线程中，是为了求得swing组件的线程同步。压缩输入流获得
 *压缩输入项的方法是getEntry(),Scanner阅读文本的方法是nextLine();
 *	Scanner(
 *					ZipInputStream(
 *													FileInputStream(
 *																						Java.lang.zip)
 *												)
 *					);
 * @author Administrator
 *该例可作为在不必解压情况下的压缩文件阅读器。
 */
public class ZipTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {		//将含有JFrame的主体方法体置于迟发线程体重
			ZipTestFrame frame=new ZipTestFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			}
		});
	}

}
