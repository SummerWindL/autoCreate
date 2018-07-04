package loadEntity;

public class DetailEntity {
	/**
	 * 父节点名称
	 */
	private String parentName;
	/**
	 * 父节点名称2:PATH中父节点区分标识(父节点名称2,当父节点重名时候区分父节点的字段)
	 */
	private String parentName2;
	/**
	 * 本字段名称
	 */
	private String selfName;
	/**
	 * 本字段描述
	 */
	private String selfDesc;
	/**
	 * KEY 对应数据库的KEY
	 */
	private String key;
	/**
	 * 自身是否父节点
	 */
	private String isParentNode;
	/**
	 * 字段长度
	 */
	private String attrLength;
	/**
	 * 小数位数
	 */
	private String attrScale;
	/**
	 * 字段类型string
	 */
	private String attrType;
	/**
	 * TYPE(simple/complex)
	 */			
	private String type;
	/**
	 * 是否需要parent的id
	 */
	private String needParentId;
	/**
	 * 数据库中的路径
	 */
	private String path;
	/**
	 * 对应RTTP_BIND_MAP表的id
	 */
	private String bindMapId;
	
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getParentName2() {
		return parentName2;
	}
	public void setParentName2(String parentName2) {
		this.parentName2 = parentName2;
	}
	public String getSelfName() {
		return selfName;
	}
	public void setSelfName(String selfName) {
		this.selfName = selfName;
	}
	public String getSelfDesc() {
		return selfDesc;
	}
	public void setSelfDesc(String selfDesc) {
		this.selfDesc = selfDesc;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getIsParentNode() {
		return isParentNode;
	}
	public void setIsParentNode(String isParentNode) {
		this.isParentNode = isParentNode;
	}
	public String getAttrLength() {
		return attrLength;
	}
	public void setAttrLength(String attrLength) {
		this.attrLength = attrLength;
	}
	public String getAttrScale() {
		return attrScale;
	}
	public void setAttrScale(String attrScale) {
		this.attrScale = attrScale;
	}
	public String getAttrType() {
		return attrType;
	}
	public void setAttrType(String attrType) {
		this.attrType = attrType;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNeedParentId() {
		return needParentId;
	}
	public void setNeedParentId(String needParentId) {
		this.needParentId = needParentId;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getBindMapId() {
		return bindMapId;
	}
	public void setBindMapId(String bindMapId) {
		this.bindMapId = bindMapId;
	}
	@Override
	public String toString() {
		return "DetailEntity [parentName=" + parentName + ", parentName2=" + parentName2 + ", selfName=" + selfName
				+ ", selfDesc=" + selfDesc + ", key=" + key + ", isParentNode=" + isParentNode + ", attrLength="
				+ attrLength + ", attrScale=" + attrScale + ", attrType=" + attrType + ", type=" + type
				+ ", needParentId=" + needParentId + ", path=" + path + ", bindMapId=" + bindMapId + "]";
	}



}
