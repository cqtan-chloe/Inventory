package team5.model;

// in addition to user and datetime,
// annotate decrease in qty due to returns 
public class ReturnRecord extends Annotation {
	private String comment;
	private String status;

	public ReturnRecord() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ReturnRecord(String comment, String status) {
		super();
		this.comment = comment;
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
