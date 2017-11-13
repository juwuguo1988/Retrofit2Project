package com.jwg.retrofit2test.model;


import java.io.Serializable;
import org.json.JSONObject;

public abstract class BaseBean<T> implements Serializable{

	private static final long serialVersionUID = 5990672132624172041L;

	/**
	 * 将json对象转化为Bean实例
	 * 
	 * @param jsonObj
	 * @return
	 */
	public abstract T parseJSON(JSONObject jsonObj);

	/**
	 * 将Bean实例转化为json对象
	 * 
	 * @return
	 */
	public abstract JSONObject toJSON();
}
