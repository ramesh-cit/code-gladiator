package com.leet;

import java.util.LinkedList;
import java.util.Queue;

public class LongestSubstring {
	public static void main(String[] args) {

	}

	public int lengthOfLongestSubstring(String s) {
		char[] chars = s.toCharArray();
		Queue q = new LinkedList<Character>();
		for (char c : chars) {
			if (!q.contains(c)) {
				q.add(c);
			}
			
			
		}
		return 0;
	}
}
