package com.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.*;

import com.db.DBManager;

/*
 * 锟斤拷锟斤拷servlet锟斤拷锟斤拷锟斤拷锟捷匡拷木锟斤拷锟斤拷锟斤拷
 */
public class Service {

	public String login(String username, String password) {

		// 锟斤拷取Sql锟斤拷询锟斤拷锟�
		String logSql = "select * from hy_user where username ='" + username
				+ "' and password ='" + password + "'";

		// 锟斤拷取DB锟斤拷锟斤拷
		DBManager sql = DBManager.createInstance();
		sql.connectDB();
		JSONObject jsonObject = new JSONObject();
		int code = 1;
		String msg = "FAIL";
		String data = null;
		JSONArray receipt_address_array = new JSONArray();
		// 锟斤拷锟斤拷DB锟斤拷锟斤拷
		try {
			ResultSet rs = sql.executeQuery(logSql);
			if (rs.next()) {
				code = 0;
				msg = "OK";
				String account = rs.getString("username");
				String card_code = rs.getString("card_code");
				card_code = nullToString(card_code);
				String register_time = rs.getString("register_time");
				register_time = nullToString(register_time);
				String nickname = rs.getString("nickname");
				nickname = nullToString(nickname);
				String address = rs.getString("address");
				address = nullToString(address);
				String sex = rs.getString("sex");
				sex = nullToString(sex);
				String birthday = rs.getString("birthday");
				birthday = nullToString(birthday);
				String introduce = rs.getString("introduce");
				introduce = nullToString(introduce);
				String relationship = rs.getString("relationship");
				relationship = nullToString(relationship);
				jsonObject.put("account", account);
				jsonObject.put("card_code", card_code);
				jsonObject.put("register_time", register_time);
				jsonObject.put("nickname", nickname);
				jsonObject.put("address", address);
				jsonObject.put("sex", sex);
				jsonObject.put("birthday", birthday);
				jsonObject.put("introduce", introduce);
				jsonObject.put("relationship", relationship);
				if (!card_code.equals("null")) {
					String getEffectTime = "select push_start_time,push_end_time from user_cardcode where card_code='"
							+ card_code + "'";
					ResultSet resultSet = sql.executeQuery(getEffectTime);
					if (resultSet.next()) {
						String pay_starttime = resultSet
								.getString("push_start_time");
						pay_starttime = nullToString(pay_starttime);
						String pay_endtime = resultSet
								.getString("push_end_time");
						pay_endtime = nullToString(pay_endtime);
						jsonObject.put("pay_starttime", pay_starttime);
						jsonObject.put("pay_endtime", pay_endtime);
					}
				}

				String kindergartenSql = "select * from card_info where card_code='"
						+ card_code + "'";
				ResultSet rs2 = sql.executeQuery(kindergartenSql);
				if (rs2.next()) {
					jsonObject.put("success", "true");
					String cardholder_name = rs2.getString("cardholder_name");
					jsonObject.put("cardholder_name", cardholder_name);
					String kindergarten_code = rs2
							.getString("kindergarten_code");
					jsonObject.put("kindergarten_code", kindergarten_code);
					String class_code = rs2.getString("class_code");
					jsonObject.put("class_code", class_code);
					String kindergarten_name = rs2
							.getString("kindergarten_name");
					String cardholder_birthday = rs2
							.getString("cardholder_birthday");
					String class_name = rs2.getString("class_name");
					jsonObject.put("kindergarten_name", kindergarten_name);
					jsonObject.put("cardholder_birthday", cardholder_birthday);
					jsonObject.put("class_name", class_name);

				} else {
					jsonObject.put("success", "false");
				}
				data = jsonObject.toString();
				String receipt_address_sql = "select * from receipt_address where username='"+username+"'";
				ResultSet rs_ra = sql.executeQuery(receipt_address_sql);
				if (rs_ra.next()) {
					rs_ra.previous();
					while (rs_ra.next()) {
						String receipt_address = rs_ra
								.getString("receipt_address");
						JSONObject jsonObject_ra = new JSONObject();
						jsonObject_ra.put("receipt_address", receipt_address);
						receipt_address_array.add(jsonObject_ra);
					}

				}
			}

		} catch (SQLException e) {
			code = 1;
			msg = "FAIL";
		}
		sql.closeDB();
		jsonObject.clear();
		jsonObject.put("code", code);
		jsonObject.put("msg", msg);
		jsonObject.put("data", data);
		jsonObject.put("receipt_address", receipt_address_array);
		return jsonObject.toString();

	}

	/*
	 * 锟剿猴拷注锟斤拷
	 */
	public String register(String username, String password, String user_phone) {
		String isExistUsername = "select * from hy_user where username='"
				+ username + "'";
		long times = System.currentTimeMillis();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormat.format(times);
		// 锟斤拷取sql锟斤拷询锟斤拷锟�
		String regSql = "insert into hy_user(username,password,user_phone,register_time) "
				+ "values('"
				+ username
				+ "','"
				+ password
				+ "','"
				+ user_phone
				+ "','" + date + "') ";

		// 锟斤拷取DB锟斤拷锟斤拷
		DBManager sql = DBManager.createInstance();
		sql.connectDB();
		ResultSet rs = sql.executeQuery(isExistUsername);
		try {
			if (rs.next()) {
				sql.closeDB();
				return "0"; // 锟斤拷锟斤拷锟矫伙拷锟斤拷锟窖撅拷锟斤拷锟节ｏ拷锟斤拷锟斤拷 0
			} else {
				int ret = sql.executeUpdate(regSql);
				if (ret != 0) {
					sql.closeDB();
					return "1";// 锟矫伙拷锟斤拷锟斤拷冢锟斤拷锟斤拷锟�
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sql.closeDB();

		return "-1";// 锟斤拷锟斤拷锟届常 锟斤拷锟斤拷锟斤拷-1

	}

	/*
	 * 锟斤拷取小锟斤拷锟窖斤拷锟秸的猴拷水锟斤拷息锟斤拷锟斤拷锟剿拷锟狡拷锟�
	 */
	public String getWaterInfo(String cardId, int type, int count) {
		long times = System.currentTimeMillis();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormat.format(times);
		String waterInfoSql = null;
		int pages_start = (count - 1) * 10;
		int pages_end = count * 10;
		// 锟斤拷取sql锟斤拷询锟斤拷锟�
		if (type == 0) {
			waterInfoSql = "select * from drink_info where card_code='"
					+ cardId + "' and drink_day='" + date + "'order by id desc";
		} else if (count == 1) {
			waterInfoSql = "select top 10 * from drink_info where card_code='"
					+ cardId + "'order by id desc";
		} else {
			// waterInfoSql="select top 5 * from UserInfo where UserId not in"+
			// (select top (n-1)*5 UserID from UserInfo order by UserID asc)
			// order by UserID asc"
			waterInfoSql = "select top 10 * from drink_info where id not in"
					+ "(select top " + pages_start
					+ " id from drink_info where card_code='" + cardId
					+ "'order by id desc)" + " and card_code='" + cardId
					+ "' order  by id desc";
		}

		// 锟斤拷取DB锟斤拷锟斤拷
		DBManager sql = DBManager.createInstance();
		sql.connectDB();
		ResultSet rs = sql.executeQuery(waterInfoSql);
		Map<String, String> map = new HashMap<String, String>();
		int i = 1;
		try {
			if (rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {

					String drink_time = rs.getString("drink_time");
					String water_temp = rs.getString("water_temp");
					String water_quality = rs.getString("water_quality");
					String drink_picture = rs.getString("drink_pic");

					map.put("drink_time" + i, drink_time);
					map.put("water_temp" + i, water_temp);
					map.put("water_quality" + i, water_quality);
					map.put("drink_picture" + i, drink_picture);
					i++;
				}
				JSONArray json = JSONArray.fromObject(map);
				String jsonString = json.toString();
				sql.closeDB();
				System.out.println(jsonString);
				return jsonString;

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sql.closeDB();
		System.out.println("jsonString为锟斤拷");
		return null;

	}

	/*
	 * 锟斤拷取锟斤拷锟斤拷小锟斤拷锟斤拷锟斤拷锟节班级锟斤拷锟斤拷小锟斤拷锟窖的猴拷水锟斤拷息
	 */
	public String getDrinkListInfo(String cardId, String type) {

		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		String class_code_info = "select * from card_info where card_code='"
				+ cardId + "'";
		DBManager sql = DBManager.createInstance();
		sql.connectDB();
		ResultSet rs = sql.executeQuery(class_code_info);
		try {
			if (rs.next()) {
				long times = System.currentTimeMillis();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
				String date = dateFormat.format(times);
				System.out.println(date);
				java.util.Date currentDateTime = new java.util.Date();
				java.sql.Date currentDate = new java.sql.Date(
						currentDateTime.getTime());
				String class_code = rs.getString("class_code");// 锟斤拷取锟矫伙拷锟侥班级锟斤拷锟�
				String kindergarten_code = rs.getString("kindergarten_code");// 锟斤拷取锟矫伙拷锟斤拷园锟斤拷锟斤拷
				String DrinkListInfoSql = null;
				if (Integer.parseInt(type) == 0) {
					DrinkListInfoSql = "select * from drink_info_v where class_code='"
							+ class_code
							+ "' and kindergarten_code='"
							+ kindergarten_code
							+ "' and drink_day='"
							+ currentDate.toString() + "'";
				} else {
					DrinkListInfoSql = "select * from drink_info_v where class_code='"
							+ class_code
							+ "' and kindergarten_code='"
							+ kindergarten_code
							+ "' and drink_day  like'%"
							+ date + "%'";
				}
				// DBManager sql_1 = DBManager.createInstance();
				// sql_1.connectDB();
				rs = sql.executeQuery(DrinkListInfoSql);
				int i = 0;
				while (rs.next()) {
					jsonObject = new JSONObject();
					String cardholder_name = rs.getString("cardholder_name");
					String drink_count = rs.getString("drink_count");
					jsonObject.put("cardholder_name", cardholder_name);
					jsonObject.put("drink_count", drink_count);
					jsonArray.add(jsonObject);
					i++;
				}
				sql.closeDB();
				if (i == 0) {
					return null;
				}
				return jsonArray.toString();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sql.closeDB();
		return null;

	}

	/*
	 * 锟斤拷取锟斤拷锟斤拷园锟斤拷锟斤拷锟较拷锟斤拷锟斤拷锟皆帮拷锟斤拷椋帮拷锟斤拷锟狡�
	 */
	public String getKindegartenInfo(String cardId) {
		String kindergartenInfoString = "select * from kindergarten_v where "
				+ "card_code ='" + cardId + "'";
		String kindergarten_name = null;
		String kindergarten_info = null;
		DBManager sql = DBManager.createInstance();
		sql.connectDB();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonAll = new JSONObject();
		ResultSet rs = sql.executeQuery(kindergartenInfoString);
		try {
			if (rs.next()) {
				JSONObject jsonObject = new JSONObject();
				kindergarten_name = rs.getString("kindergarten_name");
				kindergarten_info = rs.getString("kindergarten_info");
				int i = 1;
				while (i <= 5) {
					String temp = "kindergarten_pic_" + i;
					String kindergarten_pic = rs.getString(temp);
					kindergarten_pic = nullToString(kindergarten_pic);
					jsonObject.put("kindergarten_pic", kindergarten_pic);
					jsonArray.add(jsonObject);
					i++;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block

		}
		sql.closeDB();
		jsonAll.put("kindergarten_name", kindergarten_name);
		jsonAll.put("kindergarten_info", kindergarten_info);
		jsonAll.put("kindergarten_pic_all", jsonArray.toString());
		return jsonAll.toString();
	}

	// public String sendMessage(String phoneString){
	// String indentifyCode= (Math.random()*9000+1000)+"";
	// String indenSqlString="insert into indentifycode values('"+ phoneString+
	// "','"+ indentifyCode+ "') ";
	// return null;
	//
	// }
	/*
	 * 锟斤拷水锟斤拷锟斤拷
	 */
	public String getBindInfo(String cupId, String account, String studentName) {
		String checkCupSql = "select * from card_info where card_code='"
				+ cupId + "'";
		DBManager sql = DBManager.createInstance();
		sql.connectDB();
		ResultSet rs = sql.executeQuery(checkCupSql);
		try {
			if (rs.next()) {
				String cardholder_name = rs.getString("cardholder_name");
				if (!cardholder_name.equals(studentName)) {
					return "fail";
				}
			} else {
				return "fail";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String bindInfoSql = "update hy_user set card_code='" + cupId
				+ "' where username='" + account + "'";
		int ln = sql.executeUpdate(bindInfoSql);
		sql.closeDB();
		if (ln > 0) {
			return "success";
		}
		return "fail";

	}

	public String saveData(String account, String nickname, String sex,
			String birthday, String address, String introduce) {
		String sql = "update hy_user set nickname='" + nickname + "', sex='"
				+ sex + "',birthday='" + birthday + "',address='" + address
				+ "',introduce='" + introduce + "' where username='" + account
				+ "'";
		DBManager db = DBManager.createInstance();
		db.connectDB();
		int ret = db.executeUpdate(sql);
		if (ret != 0) {
			db.closeDB();
			return "1";
		}
		db.closeDB();
		return "-1";

	}

	public String getUserInfo(String username) {
		// 锟斤拷取Sql锟斤拷询锟斤拷锟�
		String logSql = "select * from hy_user where username ='" + username
				+ "'";

		// 锟斤拷取DB锟斤拷锟斤拷
		DBManager sql = DBManager.createInstance();
		sql.connectDB();
		Map<String, String> map = new HashMap<String, String>();

		// 锟斤拷锟斤拷DB锟斤拷锟斤拷
		try {
			ResultSet rs = sql.executeQuery(logSql);
			if (rs.next()) {
				String account = rs.getString("username");
				String card_code = rs.getString("card_code");
				card_code = nullToString(card_code);
				String register_time = rs.getString("register_time");
				register_time = nullToString(register_time);
				String nickname = rs.getString("nickname");
				nickname = nullToString(nickname);
				String address = rs.getString("address");
				address = nullToString(address);
				String sex = rs.getString("sex");
				sex = nullToString(sex);
				String birthday = rs.getString("birthday");
				birthday = nullToString(birthday);
				String introduce = rs.getString("introduce");
				introduce = nullToString(introduce);
				String relationship = rs.getString("relationship");
				relationship = nullToString(relationship);
				map.put("account", account);
				map.put("card_code", card_code);
				map.put("register_time", register_time);
				map.put("nickname", nickname);
				map.put("address", address);
				map.put("sex", sex);
				map.put("birthday", birthday);
				map.put("introduce", introduce);
				map.put("relationship", relationship);
				if (!card_code.equals("notEdit")) {
					String getEffectTime = "select push_start_time,push_end_time from user_cardcode where card_code='"
							+ card_code + "'";
					ResultSet resultSet = sql.executeQuery(getEffectTime);
					if (resultSet.next()) {
						String pay_starttime = resultSet
								.getString("push_start_time");
						pay_starttime = nullToString(pay_starttime);
						String pay_endtime = resultSet
								.getString("push_end_time");
						pay_endtime = nullToString(pay_endtime);
						map.put("pay_starttime", pay_starttime);
						map.put("pay_endtime", pay_endtime);
					}
				}
				String kindergartenSql = "select * from card_info where card_code='"
						+ card_code + "'";
				ResultSet rs2 = sql.executeQuery(kindergartenSql);
				if (rs2.next()) {
					map.put("success", "true");
					String cardholder_name = rs2.getString("cardholder_name");
					map.put("cardholder_name", cardholder_name);
					String kindergarten_code = rs2
							.getString("kindergarten_code");
					map.put("kindergarten_code", kindergarten_code);
					String class_code = rs2.getString("class_code");
					map.put("class_code", class_code);
					String kindergarten_name = rs2
							.getString("kindergarten_name");
					String cardholder_birthday = rs2
							.getString("cardholder_birthday");
					String class_name = rs2.getString("class_name");
					map.put("kindergarten_name", kindergarten_name);
					map.put("cardholder_birthday", cardholder_birthday);
					map.put("class_name", class_name);

				} else {
					map.put("success", "false");
				}
				sql.closeDB();
				JSONArray array = JSONArray.fromObject(map);
				String jsonString = array.toString();
				return jsonString;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		sql.closeDB();
		return null;
	}

	public String getKGNews(String kindergarten_code, int count) {
		String sql;
		int pages_end = count * 10;
		if (count == 1) {
			sql = "select top 10 * from kindergarten_notice where  kindergarten_code='"
					+ kindergarten_code + "'order by id desc";
		} else {
			sql = "select top 10 * from kindergarten_notice where id<(select Min(id) from (select top "
					+ pages_end
					+ " id from kindergarten_notice where  kindergarten_code='"
					+ kindergarten_code
					+ "' order by id desc)AS T) order by id asc";
		}
		DBManager db = DBManager.createInstance();
		db.connectDB();
		ResultSet rs = db.executeQuery(sql);
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject;
		try {
			if (rs.next()) {
				rs.previous();
				while (rs.next()) {
					String news_title = rs.getString("news_title");
					String news_content = rs.getString("news_content");
					String news_time = rs.getString("news_time");
					jsonObject = new JSONObject();
					jsonObject.put("news_title", news_title);
					jsonObject.put("news_content", news_content);
					jsonObject.put("news_time", news_time);
					jsonArray.add(jsonObject);
				}
				db.closeDB();
				return jsonArray.toString();
			} else {
				db.closeDB();
				return "0";
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.closeDB();
		return "-1";

	}

	public String getFeeModel() {
		String sql = "select * from fee_model";
		DBManager db = DBManager.createInstance();
		db.connectDB();
		ResultSet rs = db.executeQuery(sql);
		JSONArray array = new JSONArray();
		JSONObject jsonObject;
		try {
			while (rs.next()) {
				jsonObject = new JSONObject();
				String model_name = rs.getString("model_name");
				String model_price = rs.getString("model_price");
				String model_times = rs.getString("model_times");
				jsonObject.put("model_name", model_name);
				jsonObject.put("model_price", model_price);
				jsonObject.put("model_times", model_times);
				array.add(jsonObject);
			}
			db.closeDB();
			return array.toString();
		} catch (SQLException e) { // 锟斤拷锟矫伙拷锟斤拷锟斤拷
			db.closeDB();
			return "-1";
		}
	}

	// 锟斤拷取取值为null时锟斤拷锟斤拷锟街凤拷值notEdit
	private String nullToString(String string) {
		if (string == null) {
			return "null";
		} else {
			return string;
		}
	}

	public String getApkInfo() {
		DBManager db = DBManager.createInstance();
		db.connectDB();
		String sql = "select * from apkInfo order by version_code";
		ResultSet rs = db.executeQuery(sql);
		String updateMessage = "";
		String url = "";
		int versionCode = 0;
		try {
			if (rs.next()) {
				updateMessage = rs.getString("updateMessage");
				url = rs.getString("url");
				versionCode = rs.getInt("version_code");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("updateMessage", updateMessage);
		jsonObject.put("url", url);
		jsonObject.put("version_code", versionCode);
		db.closeDB();
		// TODO Auto-generated method stub
		return jsonObject.toString();
	}

	public String getProductList() {
		// TODO Auto-generated method stub
		int code = 0;
		String msg = "OK";
		DBManager db = DBManager.createInstance();
		db.connectDB();
		String sql_pro_list = "select * from Product";
		ResultSet rs = db.executeQuery(sql_pro_list);
		JSONArray data_jsonArray = new JSONArray();
		try {
			while (rs.next()) {
				JSONObject jsonObject = new JSONObject();
				String product_id = rs.getString("Product_Id");
				String pro_spec = rs.getString("BigClassName");
				String product_name = rs.getString("Title");
				String stock = rs.getString("stock");
				String pro_spec_code = rs.getString("Spec");
				String pro_price = rs.getString("Price");
				String pro_img = rs.getString("DefaultPicUrl");
				String pro_points = rs.getString("PriceBli");// 商品积分

				jsonObject.put("pro_id", product_id);
				jsonObject.put("pro_spec", pro_spec);
				jsonObject.put("pro_name", product_name);
				jsonObject.put("pro_stock", stock);
				jsonObject.put("pro_spec_code", pro_spec_code);
				jsonObject.put("pro_price", pro_price);
				jsonObject.put("pro_img", pro_img);
				jsonObject.put("pro_points", pro_points);
				data_jsonArray.add(jsonObject);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			code = 301;
			msg = e.getMessage();

		}
		db.closeDB();
		JSONObject return_object = new JSONObject();
		return_object.put("code", code);
		return_object.put("msg", msg);
		return_object.put("data", data_jsonArray);
		System.out.println(return_object.toString());
		return return_object.toString();
	}

	public String addReceiptAddress(String jsonData) {
		// TODO Auto-generated method stub
		int code=0;
		String msg ="OK";
		String data="";
		JSONObject jsonObject= JSONObject.fromObject(jsonData);
		String username  = jsonObject.getString("username");
		String address= jsonObject.getString("address");
		String sql = "insert into receipt_address(username,receipt_address)values('"
				+username+"','"+address+"')";
		DBManager db = DBManager.createInstance();
		db.connectDB();
		int ret =db.executeUpdate(sql);
		if(ret==0){
			code=301;
			msg="数据库存储出现异常";
		}else{
			JSONArray address_array = new JSONArray();
			String sql_address= "select * from receipt_address where username='"+username+"'";
			ResultSet rs= db.executeQuery(sql_address);
			try {
				while(rs.next()){
					JSONObject jsonObject2 =  new JSONObject();
					String receipt_address=rs.getString("receipt_address");
					jsonObject2.put("address", receipt_address);
					address_array.add(jsonObject2);
					
				}
				data=address_array.toString();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				code=301;
				msg=e.getMessage();
			}
		}
		db.closeDB();
		JSONObject return_object = new JSONObject();
		return_object.put("code",code);
		return_object.put("msg",msg);
		return_object.put("data", data);
		return return_object.toString();
	}

}
