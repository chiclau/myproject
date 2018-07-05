package com.liu.zip;

import java.util.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

public class ZipTestFrame extends JFrame{
	public ZipTestFrame() {
		setTitle("ZipTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		JMenuBar menuBar=new JMenuBar();
		JMenu menu= new JMenu("File");
		JMenuItem openItem=new JMenuItem("open");
		menu.add(openItem);
		openItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
					JFileChooser chooser= new JFileChooser();
					chooser.setCurrentDirectory(new File("."));
					int r=chooser.showOpenDialog(ZipTestFrame.this);
					if(r==JFileChooser.APPROVE_OPTION) {
						zipname=chooser.getSelectedFile().getPath();
						fileCombo.removeAllItems();
						scanZipFile();//第一个压缩文件流:填下拉列表项
					}
					
			}
		});
		JMenuItem exitItem=new JMenuItem("Exit");
		menu.add(exitItem);
		exitItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		
	menuBar.add(menu);
	setJMenuBar(menuBar);
	fileText=new JTextArea();
	fileCombo=new JComboBox();
	fileCombo.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			loadZipFile((String)fileCombo.getSelectedItem());//第二个压缩文件流:填写文本区
		}
	});
	add(fileCombo, BorderLayout.SOUTH);
	add(new JScrollPane(fileText), BorderLayout.CENTER);
	}
	
	
	public void scanZipFile() {
		new SwingWorker<Void, String>() {	//java se6的抽象类
			protected Void doInBackground() throws Exception {//实现抽象方法
				ZipInputStream zin=new ZipInputStream(new FileInputStream(zipname));
				ZipEntry entry;
				while ((entry=zin.getNextEntry()) !=null) {
					publish(entry.getName());	//将压缩文件内的entry登载到List
					zin.closeEntry();		
				}
				zin.close();
				return null;
			}
			
			protected void process(List<String> names) { //将List entry名数组加到下拉列表中
				for(String name:names) fileCombo.addItem(name);
			}
		}.execute();		//执行该工作线程
		
	}
	
	public void loadZipFile(final String name) {
		fileCombo.setEnabled(false);//暂时屏蔽下拉列表
		fileText.setText(" " );
		new SwingWorker<Void, String>() {	//java se6的抽象类
			protected Void doInBackground() throws Exception {
				try {
					ZipInputStream zin=new ZipInputStream(new FileInputStream(zipname));
					ZipEntry entry=new ZipEntry(zipname);
					while((entry=zin.getNextEntry())!=null) {
						Scanner in=new Scanner(zin);
							while(in.hasNext()) {
								fileText.append(in.nextLine());
								fileText.append("\n");
							}
							zin.closeEntry();
					}
					zin.close();
				}catch (Exception e) {
				e.printStackTrace();
				}
			return null;
			}
			protected void done() {
				fileCombo.setEditable(true);//SwingWorkr对象的方法:解释对于下拉列表的屏蔽
			}
		}.execute();//执行该工作线程
		
	}
	public static final int DEFAULT_WIDTH=400;
	public static final int DEFAULT_HEIGHT=300;
	private JComboBox fileCombo;
	private JTextArea fileText;
	private String zipname;
	
}
