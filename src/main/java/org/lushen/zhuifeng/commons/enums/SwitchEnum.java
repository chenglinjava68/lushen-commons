package org.lushen.zhuifeng.commons.enums;

/**
 * 确认枚举（注意：必须正反出现）
 * 
 * @author hlm
 */
public enum SwitchEnum {

	YES(1), NO(0),
	
	ON(1), OFF(0),
	
	USING(1), UNUSED(0),
	
	ENABLED(1), DISABLED(0),
	
	ACTIVE(1), INACTIVE(0),
	
	SUCCESS(1), UNSUCCESS(0), FAILT(0), ERROR(0);

	private int value;
	
	private SwitchEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean equalsTo(int value) {
		return this.value == value;
	}
	
	public boolean equalsTo(SwitchEnum arg) {
		return this.value == arg.value;
	}

	@Override
	public String toString() {
		return Integer.toString(this.value);
	}

}
