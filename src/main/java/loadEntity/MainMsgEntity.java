package loadEntity;

public class MainMsgEntity {
	/**
	 * 制作人标识
	 */
	private String maker;
	/**
	 * 业务名称
	 */
	private String workName;
	/**
	 * 交易码
	 */
	private String srcTransCode;
	/**
	 * 返回码
	 */
	private String destTransCode;
	/**
	 * 发出xsd名称(NAMESPACES)
	 */
	private String sendXsdName;
	/**
	 * 接收xsd名称(NAMESPACES)
	 */
	private String receiveXsdName;
	/**
	 * channel.xml名称
	 */
	private String channelName;
	/**
	 * WORKFLOW_NAME
	 */
	private String workFlowName;
	
	private String filePath;
	
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	public String getSrcTransCode() {
		return srcTransCode;
	}
	public void setSrcTransCode(String srcTransCode) {
		this.srcTransCode = srcTransCode;
	}
	public String getDestTransCode() {
		return destTransCode;
	}
	public void setDestTransCode(String destTransCode) {
		this.destTransCode = destTransCode;
	}
	public String getSendXsdName() {
		return sendXsdName;
	}
	public void setSendXsdName(String sendXsdName) {
		this.sendXsdName = sendXsdName;
	}
	public String getReceiveXsdName() {
		return receiveXsdName;
	}
	public void setReceiveXsdName(String receiveXsdName) {
		this.receiveXsdName = receiveXsdName;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getWorkFlowName() {
		return workFlowName;
	}
	public void setWorkFlowName(String workFlowName) {
		this.workFlowName = workFlowName;
	}
	@Override
	public String toString() {
		return "MainMsgEntity [maker=" + maker + ", workName=" + workName + ", srcTransCode=" + srcTransCode
				+ ", destTransCode=" + destTransCode + ", sendXsdName=" + sendXsdName + ", receiveXsdName="
				+ receiveXsdName + ", channelName=" + channelName + ", workFlowName=" + workFlowName + "]";
	}
	
	
	
	
	
	
}
