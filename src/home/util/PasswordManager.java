package home.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PasswordManager {
	public static String tempPassword() {
		List<Character> list = new ArrayList<>();
		for(char i='A'; i<='Z'; i++) list.add(i);
		for(char i='a'; i<='z'; i++) list.add(i);
		for(char i='0'; i<='9'; i++) list.add(i);
		
		Collections.shuffle(list);
		
		StringBuffer buffer = new StringBuffer();
		for(int i=0; i < 10; i++){
			buffer.append(list.get(i));
		}
		return buffer.toString();
	}
}
