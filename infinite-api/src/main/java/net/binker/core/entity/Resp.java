package net.binker.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Resp {
	protected String _data;
	protected Short code; // 状态码
	protected String codeDesc; // 状态码描述
	protected Integer runtime = 0; // 运行时长

	public Resp() {
		super();
	}

	/**
	 * default 200
	 *
	 * @param _data
	 */
	public Resp(Object _data) {
		this(_data, (short) 200);
	}

	public Resp(Object _data, Short code) {
		this(_data, code, "");
	}

	public Resp(Object _data, Short code, String codeDesc) {
		this.setData(_data);
		this.code = code;
		this.codeDesc = codeDesc;
	}

	@JsonIgnore
	public <T> T getData(Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
		return new ObjectMapper().readValue(_data, clazz);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getListData(Class<?> class1) throws IOException {
		List<T> list = new ArrayList<T>();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(this._data);
		if (!node.isArray()) {
			throw new IOException("data is not array");
		}
		Iterator<JsonNode> iter = node.iterator();
		while (iter.hasNext()) {
			String str = iter.next().toString();
			list.add((T) mapper.readValue(str, class1));
		}
		return list;
	}

	public void setData(Object data) {
		try {
			if (data != null) {
				this._data = new ObjectMapper().writeValueAsString(data);
			}
		} catch (JsonProcessingException e) {
			//ExceptUtils.printStackTrace(e);
		}
	}

	public String get_data() {
		return _data;
	}

	public void set_data(String _data) {
		this._data = _data;
	}

	public Short getCode() {
		return code;
	}

	public Resp setCode(Short code) {
		this.code = code;
		return this;
	}

	public String getCodeDesc() {
		return codeDesc;
	}

	public Resp setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
		return this;
	}

	public Integer getRuntime() {
		return runtime;
	}

	public void setRuntime(Integer runtime) {
		this.runtime = runtime;
	}

	public String toJSON() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(this);
	}

}
