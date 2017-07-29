package org.lushen.zhuifeng.commons.beans.database;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 以int类型为主键
 * 
 * @author hlm
 */
@MappedSuperclass
@SuppressWarnings("serial")
public abstract class DbJpaIntegerEntity implements DbEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length=11)
	protected int id;
	
	@Column(length=20)
	protected long createtime;
	
	@Column(length=20)
	protected long updatetime;
	
	@Column
	protected boolean isDelete;
	
	@Column(length=20)
	protected long deletetime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
