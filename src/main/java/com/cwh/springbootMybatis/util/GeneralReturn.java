package com.cwh.springbootMybatis.util;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 数据返回工具类
 * @author wanghu
 * 自定义响应结构
 */
public class GeneralReturn {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();
    // 响应业务状态
    private Integer result;
    
    private Integer code;
    
    private Integer count;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

	public static String data(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
    //常用的格式
    public static GeneralReturn build(Integer result, String msg, Object data) {
        return new GeneralReturn(result, msg, data);
    }
    public static GeneralReturn build(Integer code, String msg, Integer count,Object data) {
        return new GeneralReturn(code, msg, count, data);
    }
    public static GeneralReturn ok(Object data) {
        return new GeneralReturn(data);
    }
    
    public static GeneralReturn ok(Integer result,Object data) {
    	return new GeneralReturn(data);
    }
    

    public static GeneralReturn ok() {
        return new GeneralReturn(null);
    }

    public GeneralReturn() {

    }
    
    
	public static GeneralReturn build(Integer result, String msg) {
        return new GeneralReturn(result, msg, null);
    }

    public GeneralReturn(Integer result, String msg, Object data) {
        this.result = result;
        this.msg = msg;
        this.data = data;
    }

    public GeneralReturn( Integer code,
			String msg, Integer count, Object data) {
		
		this.code = code;
		this.count = count;
		this.msg = msg;
		this.data = data;
	}

	public GeneralReturn(Object data) {
        this.result = 0;
        this.msg = "OK";
        this.data = data;
    }


    public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getMsg() {
        return msg;
    }

    public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 将json结果集转化为TaotaoResult对象
     * 
     * @param jsonData json数据
     * @param clazz Result中的object类型
     * @return
     */
    public static GeneralReturn formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, GeneralReturn.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("result").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     * 
     * @param json
     * @return
     */
    public static GeneralReturn format(String json) {
        try {
            return MAPPER.readValue(json, GeneralReturn.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
	public String toString() {
		return "GeneralReturn [result=" + result + ", code=" + code
				+ ", count=" + count + ", msg=" + msg + ", data=" + data + "]";
	}
	/**
     * Object是集合转化
     * 
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return
     */
    public static GeneralReturn formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("result").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    
    
}
