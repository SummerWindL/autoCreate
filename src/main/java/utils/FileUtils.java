package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * 用来创建文件
 */
public class FileUtils {
	/**
	 * 
	 * @param filePath
	 *            文件路径
	 * @param fileName
	 *            文件名称(不含后缀)
	 * @param fileType
	 *            文件后缀,如.txt或者.sql
	 * @param fileContent
	 *            要写入文件的字符串
	 */
	public static boolean createFile(String filePath, String fileName, String fileType, String fileContent) {
		String filenameTemp = filePath + fileName + fileType;
		Boolean bool = false;
		File file = new File(filenameTemp);
		try {
			if (file.exists()) {
				// 如果文件已存在,删除
				System.out.println("文件" + fileName + fileType + "之前存在,现删除后重新生成");
				boolean delSuccess = delFile(filePath, fileName, fileType);
				if (delSuccess) {
					System.out.println(fileName + fileType + "删除成功!");
				} else {
					System.out.println(fileName + fileType + "删除失败!请核对文件...");
					return false;
				}
			}
			// 如果文件不存在，则创建新的文件
			if (!file.exists()) {
				file.createNewFile();
				bool = true;
				// 创建文件成功后，写入内容到文件里
				boolean createSuccess = writeFileContent(filenameTemp, fileContent);
				if (createSuccess) {
					System.out.println(fileName + fileType + "创建成功!");
				} else {
					System.out.println(fileName + fileType + "生成失败!请核对文件...");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}

	/**
	 * 向文件中写入内容
	 * 
	 * @param filepath
	 *            文件路径与名称
	 * @param filein
	 *            写入的内容
	 * @return
	 * @throws IOException
	 */
	public static boolean writeFileContent(String filepath, String filein) throws IOException {
		Boolean bool = false;
		String temp = "";

		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		FileOutputStream fos = null;
		PrintWriter pw = null;
		try {
			File file = new File(filepath);// 文件路径(包括文件名称)
			// 将文件读入输入流
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			StringBuffer buffer = new StringBuffer();

			// 文件原有内容
			for (int i = 0; (temp = br.readLine()) != null; i++) {
				buffer.append(temp);
				// 行与行之间的分隔符 相当于“\n”
				buffer = buffer.append(System.getProperty("line.separator"));
			}
			buffer.append(filein);

			fos = new FileOutputStream(file);
			pw = new PrintWriter(fos);
			pw.write(buffer.toString().toCharArray());
			pw.flush();
			bool = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 不要忘记关闭
			if (pw != null) {
				pw.close();
			}
			if (fos != null) {
				fos.close();
			}
			if (br != null) {
				br.close();
			}
			if (isr != null) {
				isr.close();
			}
			if (fis != null) {
				fis.close();
			}
		}
		return bool;
	}

	/**
	 * 删除文件
	 * 
	 * @param filePath
	 *            文件路径
	 * @param fileName
	 *            文件名称(不含后缀)
	 * @param fileType
	 *            文件后缀,如.txt或者.sql
	 * @return
	 */
	public static boolean delFile(String filePath, String fileName, String fileType) {
		Boolean bool = false;
		String filenameTemp = filePath + fileName + fileType;
		File file = new File(filenameTemp);
		try {
			if (file.exists()) {
				file.delete();
				bool = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bool;
	}

}
