package kr.co.comes.projectA.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author mins : validator
 * @Object : object
 *
 */

public class InputValidator implements Validator {
	private String m_key = "";

	@Override
	public boolean supports(Class<?> clazz) {
		// if(m_type.equals("login")) {
		// return LoginVO.class.isAssignableFrom(clazz);
		// }
		return true; // LoginVO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// if(m_type.equals("login")) {
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id",
		// "id.required");
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pwd",
		// "pwd.required");
		// } else if(m_type.equals("projectmodify")) {
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "", "");
		// } else if(m_type.equals("userchange")){
		// UserVO vo = (UserVO)target;
		// if(isemail(vo.getEmail())) {
		// // email ���� true
		// }
		// } else {
		//
		// }

		// Iterator<Object> it = prop.keySet().iterator();
		//
		// while(it.hasNext()) {
		// String key = (String) it.next();
		// String value = "";
		// if(key.equals(m_key)) {
		// value = (String) prop.getProperty(key);
		// System.out.println(key + " : " + value);
		// }
		// }

		Properties prop = new Properties();
		try {
			String filename = "inputdata.properties";
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filename);
			prop.load(inputStream);
			String value = (String) prop.getProperty(m_key);
			JSONObject jsonObject = new JSONObject(value);
			for (Object key : jsonObject.keySet()) {
				String keyStr = (String) key;
				String keyValue = jsonObject.get(keyStr).toString();
				JSONArray jsonArray = new JSONArray(keyValue);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject obj = (JSONObject) jsonArray.get(i);
					for (Object subkey : obj.keySet()) {
						String subkeyStr = (String) subkey;
						String subkeyValue = obj.get(subkeyStr).toString();
						System.out.println(target.toString());
						discriminant(keyStr, subkeyStr, subkeyValue, target.toString(), errors);
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errors.reject("FileNotFound");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errors.reject("IOEcxception");
		}
	}

	public void validate(Object target, Errors errors, String key) {
		m_key = key;
		validate(target, errors);
	}

	public void discriminant(String key, String subkey, String value, String target, Errors errors) {
		JSONArray jsonArray = new JSONArray(target);
		String plainValue = "true";
		String result = "";

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = (JSONObject) jsonArray.get(i);
			for (Object plainkey : jsonObject.keySet()) {
				String strplainkey = (String) plainkey;
				if (key.equals(strplainkey)) {
					plainValue = jsonObject.get(strplainkey).toString().trim();
					break;
				}
			}
		}

		switch (subkey) {
		case "max":
			if (plainValue.length() > Integer.valueOf(value)) {
				result = (key + ":���ڿ� ���� �ִ밪�� " + value + " �Դϴ�");
			}
			break;
		case "min":
			if (plainValue.length() < Integer.valueOf(value)) {
				result = (key + ":���ڿ� ���� �ּҰ��� " + value + " �Դϴ�");
			}
			break;
		case "type":
			switch (value) {
			case "test":
				if (plainValue.isEmpty()) {
					result = "�׽�Ʈ�� �������ּ���.";
				}
				break;
			case "email":
				if (!plainValue.isEmpty()) {
					if (!isemail(plainValue)) {
						result = key + ":�̸��� ���Ŀ� �����ʽ��ϴ�";
					}
				}
				break;
			case "num":
				if (!isnumber(plainValue)) {
					result = key + ":���ڸ� �Է� �����մϴ�";
				}
				break;
			case "password":
				if (!ispassword(plainValue)) {
					result = key + ":��й�ȣ ���Ŀ� �����ʽ��ϴٹݵ�� ���� �ҹ���, �빮��, ����, Ư�����ڰ� �� 1�ڸ� �̻� ���ԵǾ�� �մϴ�";
				}
				break;
			case "notempty":
				if (plainValue.isEmpty()) {
					result = key + ":�Է¶��� ������ϴ�";
				}
				break;
			case "date":
				if (!isdate(plainValue)) {
					result = key + ":��¥ ���Ŀ� �����ʽ��ϴ�";
				}
				break;
			}
			break;
		case "user":
			if (!isuser(plainValue)) {
				result = key + ":���� ��ȣ�� �ƴմϴ�";
			}
			break;
		default:
			break;
		}
		if (!result.isEmpty()) {
			errors.reject(result);
		}
	}

	private boolean isemail(String email) {
		return (Pattern.matches("^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", email));
	}

	private boolean isnumber(String num) {
		String str = num.replaceAll("[^0-9]", "");
		return (str.equals(num));
	}

	private boolean isuser(String user) {
		return (Integer.valueOf(user) < 3 && Integer.valueOf(user) >= 0);
	}

	private boolean ispassword(String pwd) {
		if (!pwd.matches("(.*[A-Z].*)"))
			return false;
		if (!pwd.matches("(.*[a-z].*)"))
			return false;
		if (!pwd.matches("(.*[0-9].*)"))
			return false;
		if (!pwd.matches("(.*[,~,!,@,#,$,%,^,&,*,(,),-,_,=,+,[,{,],},|,;,:,<,>,/,?].*$)"))
			return false;
		return true;
	}

	private boolean isdate(String date) {
		try {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			formatter.setLenient(false);
			Date da = formatter.parse(date);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}
}