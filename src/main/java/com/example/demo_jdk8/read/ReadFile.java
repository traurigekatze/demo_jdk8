package com.example.demo_jdk8.read;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

public class ReadFile {

	public static void main(String[] args) {
		List<String> orderNos = getOrderNo();
        BufferedReader br = null;
        BufferedReader br2 = null;
        try {
        	br = new BufferedReader(new FileReader("C:\\Users\\traur\\Desktop\\123\\csv-export.csv"));
	        // 第一种读取文件方式
	        String contentLine ;
	        List<String> arr1 = new ArrayList<>();
	        while ((contentLine = br.readLine()) != null) {
	//            contentLine = br.readLine();
	            //读取每一行，并输出
//	            System.out.println(contentLine);
	            //将每一行追加到arr1
	            arr1.add(contentLine);
	        }
	        br2 = new BufferedReader(new FileReader("C:\\Users\\traur\\Desktop\\123\\ali-export.csv"));
	        // 第一种读取文件方式
	        String contentLine2 ;
	        while ((contentLine2 = br2.readLine()) != null) {
	//            contentLine = br.readLine();
	            //读取每一行，并输出
//	            System.out.println(contentLine);
	            //将每一行追加到arr1
	            arr1.add(contentLine2);
	        }
	        //输出数组
	        List<String> result = new ArrayList<>(arr1.size());
	        List<String> result2 = new ArrayList<>(arr1.size());
	        List<String> result3 = new ArrayList<>(arr1.size());
	        if (arr1.size() > 0) {
	        	for (int i = 0; i < arr1.size(); i++) {
	        		if (arr1.get(i).indexOf("{") > 0) {
	        			String str = arr1.get(i).substring(arr1.get(i).indexOf("{"));
	        			
	        			String newstr = str.substring(0, str.length()-1);
	        			newstr = newstr.replace("\"\"", "\"");
	        			result3.add(newstr);
	        			int begin = str.indexOf("citizenResult");
	        			int end = str.indexOf("idNoResult");
	        			if (begin > 0 && end > 0) {
	        				String ss = str.substring(begin, end);	
	        				ss = ss.replace("\\\"\"", "");
	        				String orderNo = getStr(str, "orderNo(.*?)orderTime");
	        				orderNo = orderNo.replace("\"\"", "");
	        				orderNo = orderNo.replace(":", "").replace(",", "");
//	        				ss += orderNo;
	        				if (orderNos.contains(orderNo)) {
								ss += "超时";
							}
	        				result.add(ss);
						} else {
							String ss = getStr(str, "errormesage(.*?)errormesage");
							String orderNo = getStr(str, "orderNo(.*?)orderTime");
	        				orderNo = orderNo.replace("\"\"", "");
	        				orderNo = orderNo.replace(":", "").replace(",", "");
//	        				ss += orderNo;
	        				if (orderNos.contains(orderNo)) {
								ss += "超时";
							}
							result2.add(ss);
						}
					}
				}
	        	for (int i = 0; i < 10; i++) {
					System.out.println(arr1.get(i));
				}
				for (int i = 0; i < 10; i++) {
					System.out.println(result.get(i));
				}
				System.out.println("arr1.size=" + arr1.size());
				System.out.println("result.size=" + result.size());
				System.out.println("result2.size=" + result2.size());
				for (int i = 0; i < 10; i++) {
					System.out.println(result3.get(i));
				}
	        }
	        String filePath="C:\\Users\\traur\\Desktop\\123\\result.txt";
	        FileOutputStream fos = new FileOutputStream(filePath);
	        for (int i = 0; i < result.size(); i++) {
	        	String ss = result.get(i) + "\r\n";
				fos.write(ss.getBytes());
			}
	        fos.close();
	        
	        String filePath2="C:\\Users\\traur\\Desktop\\123\\result2.txt";
	        FileOutputStream fos2 = new FileOutputStream(filePath2);
	        for (int i = 0; i < result2.size(); i++) {
	        	String ss = result2.get(i) + "\r\n";
	        	fos2.write(ss.getBytes());
			}
	        fos2.close();
        } catch (FileNotFoundException e) { 
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        } finally {
        	try {
        		if (br != null) {
        			br.close();
        		}
        		if (br2 != null) {
        			br2.close();
        		}
        	} catch (IOException e) {
        		System.out.println("Error in closing the BufferedReader");
        	}  
        }    
	}
	
	public static String getStr(String str, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher(str);
		if (m.find()) {
			return !StringUtils.isEmpty(m.group(1)) ? m.group(1).trim() : m.group(1);	
		}
		return "";
	}
	
	public static List<String> getOrderNo() {
		List<String> orderNos = new ArrayList<>();
		orderNos.add("1382033135353184256");
		orderNos.add("1384552604289974272");
		orderNos.add("1386770222111510528");
		orderNos.add("1389208264189399040");
		orderNos.add("1390929642613624832");
		orderNos.add("1394561309597224960");
		orderNos.add("1397486478275895296");
		orderNos.add("1397527654504841216");
		orderNos.add("1397993457331200000");
		orderNos.add("1398204414020009984");
		orderNos.add("1398312275236610048");
		orderNos.add("1400199742814404608");
		orderNos.add("1400535647794221056");
		orderNos.add("1400536629898891264");
		orderNos.add("1400536221935714304");
		orderNos.add("1400536274834280448");
		orderNos.add("1400537060469362688");
		orderNos.add("1400536487737147392");
		orderNos.add("1400536439888535552");
		orderNos.add("1400536282644070400");
		orderNos.add("1400536495630835712");
		orderNos.add("1400537289855852544");
		orderNos.add("1400537434399956992");
		orderNos.add("1400537753661988864");
		orderNos.add("1400537880086700032");
		orderNos.add("1400537984097050624");
		orderNos.add("1400538534976937984");
		orderNos.add("1400538663440080896");
		orderNos.add("1400536995180822528");
		orderNos.add("1400537995144839168");
		orderNos.add("1400539117054050304");
		orderNos.add("1400539298147319808");
		orderNos.add("1400537886227152896");
		orderNos.add("1400537390678528000");
		orderNos.add("1400537802575958016");
		orderNos.add("1400537795286257664");
		orderNos.add("1400540343720198144");
		orderNos.add("1400537913062313984");
		orderNos.add("1400541065039822848");
		orderNos.add("1400540381955473408");
		orderNos.add("1400541070836350976");
		orderNos.add("1400539585599754240");
		orderNos.add("1400541678389673984");
		orderNos.add("1400538769891512320");
		orderNos.add("1400540640836308992");
		orderNos.add("1400540973905989632");
		orderNos.add("1400542918938968064");
		orderNos.add("1400541276541800448");
		orderNos.add("1400543596679774208");
		orderNos.add("1400537976765403136");
		orderNos.add("1400541922900824064");
		orderNos.add("1400541982820651008");
		orderNos.add("1400542739103993856");
		orderNos.add("1400542922000814080");
		orderNos.add("1400543039986585600");
		orderNos.add("1400543071494197248");
		orderNos.add("1400543791253540864");
		orderNos.add("1400539984637444096");
		orderNos.add("1400539589592735744");
		orderNos.add("1400539715572846592");
		orderNos.add("1400540966641459200");
		orderNos.add("1400541121235116032");
		orderNos.add("1400542658162311168");
		orderNos.add("1400541230152802304");
		orderNos.add("1400541571778863104");
		orderNos.add("1400541660916211712");
		orderNos.add("1400542566357393408");
		orderNos.add("1400542696900911104");
		orderNos.add("1400542765989486592");
		orderNos.add("1400543239333470208");
		orderNos.add("1400539165741535232");
		orderNos.add("1400540177743208448");
		orderNos.add("1400540792099684352");
		orderNos.add("1400537255294787584");
		orderNos.add("1400811186723471360");
		orderNos.add("1400880324925972480");
		orderNos.add("1401100640684064768");
		orderNos.add("1401151634763669504");
		orderNos.add("1401498812145197056");
		orderNos.add("1404799419434016768");
		orderNos.add("1407169178494558208");
		orderNos.add("1408884382727020544");
		orderNos.add("1409146935235567616");
		orderNos.add("1409147755708420096");
		orderNos.add("1409147702390546432");
		orderNos.add("1409147538854633472");
		orderNos.add("1409148213667700736");
		orderNos.add("1409248982140063744");
		orderNos.add("1409351704185016320");
		orderNos.add("1410177347298660352");
		orderNos.add("1410957979423477760");
		orderNos.add("1411940196417150976");
		orderNos.add("1415785415155781632");
		return orderNos;
	}
	
}
