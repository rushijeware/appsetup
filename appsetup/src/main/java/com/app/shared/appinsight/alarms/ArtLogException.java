package com.app.shared.appinsight.alarms;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Cache;

@Entity
@Table(name = "art_log_exception_m")
@Cache(alwaysRefresh = true)
public class ArtLogException implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "exceptionId")
	private Integer exceptionId;

	@Column(name = "exception")
	private String exception;

	@Column(name = "exceptionName")
	private String exceptionName;

	@Column(name = "rootException")
	private String rootException;

	public ArtLogException() {
		super();
	}

	public ArtLogException(Integer exceptionId, String exception, String exceptionName, String rootException) {
		super();
		this.exceptionId = exceptionId;
		this.exception = exception;
		this.exceptionName = exceptionName;
		this.rootException = rootException;
	}

	public Integer getExceptionId() {
		return exceptionId;
	}

	public void setExceptionId(final Integer exceptionId) {
		this.exceptionId = exceptionId;
	}

	public String getException() {
		return exception;
	}

	public void setException(final String exception) {
		this.exception = exception;
	}

	public String getRootException() {
		return rootException;
	}

	public void setRootException(final String rootException) {
		this.rootException = rootException;
	}

	public String getExceptionName() {
		return exceptionName;
	}

	public void setExceptionName(final String exceptionName) {
		this.exceptionName = exceptionName;
	}

	@Override
	public String toString() {
		return "ArtLogException [exceptionId=" + exceptionId + ", exception=" + exception + ", exceptionName=" + exceptionName + ", rootException=" + rootException + "]";
	}

	public String toJSON() {
		StringBuilder sb = new StringBuilder();
		sb.append("{").append("\"exceptionId\":" + "\"" + getExceptionId() + "\"").append(",\"exceptionName\":" + "\"" + getExceptionName() + "\"")
				.append(",\"exception\":" + "\"" + getException() + "\"").append(",\"rootException\":" + "\"" + getRootException() + "\"").append("}");
		return sb.toString();
	}

}
