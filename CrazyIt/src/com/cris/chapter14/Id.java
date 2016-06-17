package com.cris.chapter14;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface Id {

	String column();
	String type();
	String generator();
	
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
@interface Property{
	
	String column();
	String type();
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
@interface Persistence{
	String table();
}