package org.lushen.zhuifeng.commons.beans.database;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 以UUID为主键
 * 
 * @author hlm
 */
@MappedSuperclass
@SuppressWarnings("serial")
public abstract class DbJpaUuidEntity implements DbEntity{
	
	@Id
	@Column(length=32)
	protected String id;
	
	@Column(length=20)
	protected long createtime;
	
	@Column(length=20)
	protected long updatetime;
	
	@Column
	protected boolean isDelete;
	
	@Column(length=20)
	protected long deletetime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getCreatetime() {
		return createtime;
	}

	public void setCreatetime(long createtime) {
		this.createtime = createtime;
	}

	public long getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(long updatetime) {
		this.updatetime = updatetime;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public long getDeletetime() {
		return deletetime;
	}

	public void setDeletetime(long deletetime) {
		this.deletetime = deletetime;
	}

}
