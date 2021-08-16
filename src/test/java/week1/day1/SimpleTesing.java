package week1.day1;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import com.google.common.base.CharMatcher;
import com.google.common.collect.Ordering;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SimpleTesing {


	public static void main(String[] args) throws InterruptedException {
		
		List<String> numbersInString= new ArrayList<String>();
		numbersInString.add("00000111");
		numbersInString.add("00000110");
		numbersInString.add("00000112");
		numbersInString.add("00000109");
		
		List<Integer> numbersInInt= new ArrayList<Integer>();
		
		for (String string : numbersInString) {
			numbersInInt.add(Integer.parseInt(string));
		}
		
		System.out.println(numbersInString);
		
		System.out.println(numbersInInt);
		
		System.out.println(Ordering.natural().isOrdered(numbersInString));
		
		System.out.println(Ordering.natural().isOrdered(numbersInInt));
	}
	
}

