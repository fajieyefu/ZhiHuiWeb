//package com.servlet;
//
//import java.util.LinkedList;
//import java.util.List;
//
//import org.apache.http.NameValuePair;
//import org.apache.http.message.BasicNameValuePair;
//
//public class Abc {
//	  private String genProductArgs() { 
//		  StringBuffer xml = new StringBuffer(); 
//		  try { 
//			  String nonceStr = genNonceStr(); 
//			  xml.append("</xml>"); 
//			  List<NameValuePair> packageParams = new LinkedList<NameValuePair>(); 
//			  packageParams .add(new BasicNameValuePair("appid", Constants.APP_ID));
//			  packageParams.add(new BasicNameValuePair("body", "weixin")); 
//			  packageParams .add(new BasicNameValuePair("mch_id", Constants.MCH_ID));
//			  packageParams.add(new BasicNameValuePair("nonce_str", nonceStr)); 
//			  packageParams.add(new BasicNameValuePair("notify_url", "http://121.40.35.3/test"));
//			  packageParams.add(new BasicNameValuePair("out_trade_no", genOutTradNo())); 
//			  packageParams.add(new BasicNameValuePair("spbill_create_ip", "127.0.0.1")); 
//			  packageParams.add(new BasicNameValuePair("total_fee", "1")); 
//			  packageParams.add(new BasicNameValuePair("trade_type", "APP")); 
//			  String sign = genPackageSign(packageParams); 
//			  packageParams.add(new BasicNameValuePair("sign", sign));
//			  String xmlstring = toXml(packageParams);
//			  return xmlstring;
//			  } 
//		  catch (Exception e) { 
//				  Log.e(TAG, "genProductArgs fail, ex = " + e.getMessage());
//				  return null; 
//				  }
//		  }
//	  String url = String.format("https://api.mch.weixin.qq.com/pay/unifiedorder");
//	  String entity = genProductArgs(); 
//	  Log.e("orion", entity); 
//	  byte[] buf = Util.httpPost(url, entity); 
//	  String content = new String(buf); 
//	  Log.e("orion", content);
//	  Map<String, String> xml = decodeXml(content);
//	  private void genPayReq() { 
//		  req.appId = Constants.APP_ID;
//		  req.partnerId = Constants.MCH_ID;
//		  req.prepayId = resultunifiedorder.get("prepay_id");
//		  req.packageValue = "Sign=WXPay";
//		  req.nonceStr = genNonceStr(); 
//		  req.timeStamp = String.valueOf(genTimeStamp()); 
//		  List<NameValuePair> signParams = new LinkedList<NameValuePair>(); 
//		  signParams.add(new BasicNameValuePair("appid", req.appId));
//		  signParams.add(new BasicNameValuePair("noncestr", req.nonceStr)); 
//		  signParams.add(new BasicNameValuePair("package", req.packageValue)); 
//		  signParams.add(new BasicNameValuePair("partnerid", req.partnerId)); 
//		  signParams.add(new BasicNameValuePair("prepayid", req.prepayId)); 
//		  signParams.add(new BasicNameValuePair("timestamp", req.timeStamp));
//		  req.sign = genAppSign(signParams); 
//		  sb.append("sign\n" + req.sign + "\n\n"); 
//		  show.setText(sb.toString()); 
//		  Log.e("orion", signParams.toString()); 
//		  }
//}
