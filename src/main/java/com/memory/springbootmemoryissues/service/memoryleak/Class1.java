package com.memory.springbootmemoryissues.service.memoryleak;

public class Class1 {
	Class2 class2 = new Class2();
	
	public void grow() {
		class2.grow();
	}
}
