package com.sgfm.base.util;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.jsp.PageContext;

/**
 * 
 * 实现对文件的相关操作
 * 
 * <p>
 * 包括列出、拷贝、删除文件，创建、删除目录，对象持久化等操作
 * 
 * @author maluming 2011-4-14
 * @see
 * @since 1.0
 */

public class FileUtil {

	protected ServletContext m_application;

	private boolean m_denyPhysicalPath;

	/**
	 * 功能描述：列出某文件夹及其子文件夹下面的文件，并可根据扩展名过滤
	 * 
	 * @param path
	 *            文件夹
	 */
	public static void list(File path) {
		if (!path.exists()) {
			System.out.println("文件名称不存在!");
		} else {
			if (path.isFile()) {
				if (path.getName().toLowerCase().endsWith(".pdf") || path.getName().toLowerCase().endsWith(".doc") || path.getName().toLowerCase().endsWith(".chm") || path.getName().toLowerCase().endsWith(".html")
						|| path.getName().toLowerCase().endsWith(".htm")) {
					// 文件格式
					System.out.println(path);
					System.out.println(path.getName());
				}
			} else {
				File[] files = path.listFiles();
				for (int i = 0; i < files.length; i++) {
					FileUtil.list(files[i]);
				}
			}
		}
	}

	/**
	 * 功能描述：拷贝一个目录或者文件到指定路径下，即把源文件拷贝到目标文件路径下
	 * 
	 * @param source
	 *            源文件
	 * @param target
	 *            目标文件路径
	 * @return void
	 */
	public static void copy(File source, File target) {
		File tarpath = new File(target, source.getName());
		if (source.isDirectory()) {
			tarpath.mkdir();
			File[] dir = source.listFiles();
			for (int i = 0; i < dir.length; i++) {
				FileUtil.copy(dir[i], tarpath);
			}
		} else {
			try {
				InputStream is = new FileInputStream(source); // 用于读取文件的原始字节流
				OutputStream os = new FileOutputStream(tarpath); // 用于写入文件的原始字节的流
				byte[] buf = new byte[1024];// 存储读取数据的缓冲区大小
				int len = 0;
				while ((len = is.read(buf)) != -1) {
					os.write(buf, 0, len);
				}
				is.close();
				os.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 创建目录（可以一次创建多级）
	 * 
	 * @param directory
	 *            指定目录（如果不存在则创建）
	 * @param subDirectory
	 *            指定目录下的多级子目录（可以为空，为空则只创建directory参数指定的目录）
	 */
	public static void createDirectory(String directory, String subDirectory) {

		String dir[];

		File fl = new File(directory);

		try {

			if (subDirectory == "" && fl.exists() != true) {

				fl.mkdir();

			} else if (!"".equals(subDirectory)) {

				dir = subDirectory.replace('\\', '/').split("/");

				for (int i = 0; i < dir.length; i++) {

					File subFile = new File(directory + File.separator + dir[i]);

					if (subFile.exists() == false) {
						subFile.mkdir();
					}

					directory += File.separator + dir[i];

				}

			}

		} catch (Exception ex) {

			System.out.println(ex.getMessage());

		}

	}

	/**
	 * 
	 * 拷贝文件夹中的所有文件到另外一个文件夹
	 * 
	 * @param srcDirector
	 *            源文件夹
	 * 
	 * @param desDirector
	 *            目标文件夹
	 */

	public static void copyFileWithDirector(String srcDirector, String desDirector) throws IOException {

		(new File(desDirector)).mkdirs();

		File[] file = (new File(srcDirector)).listFiles();

		for (int i = 0; i < file.length; i++) {

			if (file[i].isFile()) {

				FileInputStream input = new FileInputStream(file[i]);

				FileOutputStream output = new FileOutputStream(desDirector + "/" + file[i].getName());

				byte[] b = new byte[1024 * 5];

				int len;

				while ((len = input.read(b)) != -1) {

					output.write(b, 0, len);

				}

				output.flush();

				output.close();

				input.close();

			}

			if (file[i].isDirectory()) {

				FileUtil.copyFileWithDirector(srcDirector + "/" + file[i].getName(), desDirector + "/" + file[i].getName());

			}

		}

	}

	/**
	 * 
	 * 删除文件夹
	 * 
	 * @param folderPath
	 *            folderPath 文件夹完整绝对路径
	 */

	public static void delFolder(String folderPath) throws Exception {

		// 删除完里面所有内容

		FileUtil.delAllFile(folderPath);

		String filePath = folderPath;

		filePath = filePath.toString();

		File myFilePath = new File(filePath);

		// 删除空文件夹

		myFilePath.delete();

	}

	/**
	 * 
	 * 删除指定文件夹下所有文件
	 * 
	 * @param path
	 *            文件夹完整绝对路径
	 */

	public static boolean delAllFile(String path) throws Exception {
		boolean flag = false;

		File file = new File(path);

		if (!file.exists()) {
			return flag;
		}

		if (!file.isDirectory()) {
			return flag;
		}

		String[] tempList = file.list();

		File temp = null;

		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}

			if (temp.isFile()) {
				temp.delete();
			}

			if (temp.isDirectory()) {

				// 先删除文件夹里面的文件
				FileUtil.delAllFile(path + "/" + tempList[i]);

				// 再删除空文件夹
				FileUtil.delFolder(path + "/" + tempList[i]);

				flag = true;

			}

		}

		return flag;

	}

	/**
	 * 把java的可序列化的对象(实现Serializable接口)序列化保存到XML文件里面,如果想一次保存多个可序列化对象请用集合进行封装
	 * 保存时将会用现在的对象原来的XML文件内容
	 * 
	 * @param obj
	 *            要序列化的可序列化的对象
	 * @param fileName
	 *            带完全的保存路径的文件名
	 * @throws FileNotFoundException
	 *             指定位置的文件不存在
	 * @throws IOException
	 *             输出时发生异常
	 * @throws Exception
	 *             其他运行时异常
	 */
	public static void objectXmlEncoder(Object obj, String fileName) throws FileNotFoundException, IOException, Exception {
		// 创建输出文件
		File fo = new File(fileName);
		// 文件不存在,就创建该文件
		if (!fo.exists()) {
			// 先创建文件的目录
			String path = fileName.substring(0, fileName.lastIndexOf('.'));
			File pFile = new File(path);
			pFile.mkdirs();
		}
		// 创建文件输出流
		FileOutputStream fos = new FileOutputStream(fo);
		// 创建XML文件对象输出类实例
		XMLEncoder encoder = new XMLEncoder(fos);
		// 对象序列化输出到XML文件
		encoder.writeObject(obj);
		encoder.flush();
		// 关闭序列化工具
		encoder.close();
		// 关闭输出流
		fos.close();
	}

	/**
	 * 读取由objSource指定的XML文件中的序列化保存的对象,返回的结果经过了List封装
	 * 
	 * @param objSource
	 *            带全部文件路径的文件全名
	 * @return 由XML文件里面保存的对象构成的List列表(可能是一个或者多个的序列化保存的对象)
	 * @throws FileNotFoundException
	 *             指定的对象读取资源不存在
	 * @throws IOException
	 *             读取发生错误
	 * @throws Exception
	 *             其他运行时异常发生
	 */
	public static List objectXmlDecoder(String objSource) throws FileNotFoundException, IOException, Exception {
		List objList = new ArrayList();
		File fin = new File(objSource);
		FileInputStream fis = new FileInputStream(fin);
		XMLDecoder decoder = new XMLDecoder(fis);
		Object obj = null;
		try {
			while ((obj = decoder.readObject()) != null) {
				objList.add(obj);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		fis.close();
		decoder.close();
		return objList;
	}

	public final void initialize(PageContext pageContext) throws ServletException {
		m_application = pageContext.getServletContext();

	}

	public String getPhysicalPath(String filePathName, int option) throws IOException {
		String path = new String();
		String fileName = new String();
		String fileSeparator = new String();
		boolean isPhysical = false;
		fileSeparator = System.getProperty("file.separator");
		if (filePathName == null) {
			throw new IllegalArgumentException("There is no specified destination file (1140).");
		}
		if (filePathName.equals("")) {
			throw new IllegalArgumentException("There is no specified destination file (1140).");
		}
		if (filePathName.lastIndexOf("\\") >= 0) {
			path = filePathName.substring(0, filePathName.lastIndexOf("\\"));
			fileName = filePathName.substring(filePathName.lastIndexOf("\\") + 1);
		}
		if (filePathName.lastIndexOf("/") >= 0) {
			path = filePathName.substring(0, filePathName.lastIndexOf("/"));
			fileName = filePathName.substring(filePathName.lastIndexOf("/") + 1);
		}
		path = path.length() != 0 ? path : "/";
		java.io.File physicalPath = new java.io.File(path);
		if (physicalPath.exists()) {
			isPhysical = true;
		}
		if (option == 0) {
			if (isVirtual(path)) {
				path = m_application.getRealPath(path);
				if (path.endsWith(fileSeparator)) {
					path = path + fileName;
				} else {
					path = String.valueOf((new StringBuffer(String.valueOf(path))).append(fileSeparator).append(fileName));
				}
				return path;
			}
			if (isPhysical) {
				if (m_denyPhysicalPath) {
					throw new IllegalArgumentException("Physical path is denied (1125).");
				} else {
					return filePathName;
				}
			} else {
				throw new IllegalArgumentException("This path does not exist (1135).");
			}
		}
		if (option == 1) {
			if (isVirtual(path)) {
				path = m_application.getRealPath(path);
				if (path.endsWith(fileSeparator)) {
					path = path + fileName;
				} else {
					path = String.valueOf((new StringBuffer(String.valueOf(path))).append(fileSeparator).append(fileName));
				}
				return path;
			}
			if (isPhysical) {
				throw new IllegalArgumentException("The path is not a virtual path.");
			} else {
				throw new IllegalArgumentException("This path does not exist (1135).");
			}
		}
		if (option == 2) {
			if (isPhysical) {
				if (m_denyPhysicalPath) {
					throw new IllegalArgumentException("Physical path is denied (1125).");
				} else {
					return filePathName;
				}
			}
			if (isVirtual(path)) {
				throw new IllegalArgumentException("The path is not a physical path.");
			} else {
				throw new IllegalArgumentException("This path does not exist (1135).");
			}
		}

		else {
			return null;
		}

	}

	// 判断是否是虚拟路径
	private boolean isVirtual(String pathName) {
		if (m_application.getRealPath(pathName) != null) {
			java.io.File virtualFile = new java.io.File(m_application.getRealPath(pathName));
			return virtualFile.exists();
		}

		else {
			return false;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("F:\\Software");
		FileUtil.list(file);
		// Date myDate = new Date();
		// DateFormat df = DateFormat.getDateInstance();
		// System.out.println(df.format(myDate));
	}

	/**
	 * 取得此文件名的文件夹下所有子文件 以字符串数组返回 注意，返回的是文件夹下的子文件夹
	 */
	public static final String[] getFolderFromFolder(String s) {
		ArrayList arraylist = new ArrayList();
		File file1 = new File(s);
		File afile[] = file1.listFiles();
		for (int i = 0; i < afile.length; i++)
			if (afile[i].isDirectory())
				arraylist.add(afile[i].getName());
		return (String[]) (String[]) arraylist.toArray(new String[0]);
	}

	/**
	 * 取得此文件名的文件夹下所有文件 （这里只是文件 不包括子文件夹） 以字符串数组返回 注意，返回的是文件夹下的子文件夹
	 */
	public static final String[] getFileFromFolder(String s) {
		ArrayList arraylist = new ArrayList();
		File file1 = new File(s);
		File afile[] = file1.listFiles();
		for (int i = 0; i < afile.length; i++)
			if (afile[i].isFile())
				arraylist.add(afile[i].getName());

		return (String[]) (String[]) arraylist.toArray(new String[0]);
	}

}
