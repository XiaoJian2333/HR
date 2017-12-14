package com.xiaojian.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.sql.Date;

public class test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(12);
		list.add(26);
		list.add(27);
		list.add(28);
		list.add(29);
		list.add(30);
		list.add(31);
		list.add(32);
		list.add(8);
		list.add(8);
		list.add(8);
		list.add(8);
		list.add(11);
		list.add(11);
		list.add(11);
		list.add(25);
		int a[]=new int[list.size()];
		for (int i = 0; i <list.size(); i++) {
			a[i]=list.get(i);
		}
		Arrays.sort(a);
		String idString = "";
		for(int i = 0;i<a.length;i++){
			if(i==0){
				idString+=a[i];
			}else{
				if(a[i]!=a[i-1]){
					idString+=","+a[i];
				}
			}
		}
		System.out.println(idString);
	}

}
