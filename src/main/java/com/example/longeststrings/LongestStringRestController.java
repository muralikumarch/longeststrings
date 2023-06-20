package com.example.longeststrings;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/***
 * The Service should take the given text and return top N largest words as per the given sorting order
 * @param String
 * @param k
 * @return
 */
@RestController
public class LongestStringRestController {

	@RequestMapping(method = RequestMethod.GET, path = "/api/longeststrings", produces = "application/json")
	public List<String> getLongStrings(@RequestParam(name = "str", required = false) String str, @RequestParam(name = "k", required = false, defaultValue = "1") Integer k) {
		return topKLongestStings(str, k);
	}

	/***
	 * Used sort the string based on the length and natural ordering. Returns list of k no of strings in a sorted order 
	 * @param words
	 * @param k
	 * @return list 
	 */
	public List<String> topKLongestStings(String words, int k) {

		//Split the string as array by space and expect some special chars, so that we can easily sort the strings.
		String[] wordsArr = words.replaceAll("[^a-zA-Z0-9\\s\\'\\-]", "").split(" ");

		//Comparator for string length
		Comparator<String> lengthComparator = Comparator.<String, Integer>comparing(s -> s.length()).reversed();
		//Comparator for irrespective of the case
		Comparator<String> caseComparator = lengthComparator.thenComparing(String::compareToIgnoreCase);
		//Comparator for natural ordering
		Comparator<String> comparator = caseComparator.thenComparing(Comparator.naturalOrder());
		//Finally apply sorting on array of string with composed custom comparator
		List<String> sortedList = Arrays.asList(wordsArr).stream().sorted(comparator).limit(k)
				.collect(Collectors.toList());
		return sortedList;
	}
}