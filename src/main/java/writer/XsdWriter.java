package writer;
/**
 * 定义发出xsd和接收xsd文件共用的属性和方法
 */
public class XsdWriter {
	public String xmlDeclare = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\r\n";
	/**
	 * xsSchema部分
	 */
	public String xsSchema1="<xs:schema xmlns=\"";
	public String xsSchema2="\"\r\n" + 
			"	elementFormDefault=\"qualified\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\"\r\n" + 
			"	targetNamespace=\"";
	public String xsSchema3="\">\r\n";
	
	/**
	 * 组装xsd文件头部xsHead
	 */
	public String xsTopCreater(String xsdName) {
		if(xsdName==null || "".equals(xsdName.trim())) {
			System.out.println("xsdName不能为空!");
			return null;
		}
		String xsTop=xmlDeclare+xsSchema1+xsdName+xsSchema2+xsdName+xsSchema3;
		return xsTop;
	}
	/**
	 * xsd文件结束
	 */
	public String xsSchema4="</xs:schema>";
	
}
