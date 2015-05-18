package org.myapp.playground;

public interface Packer<T> {
	public String dumps(T obj);
	public T loads(String str);
}
