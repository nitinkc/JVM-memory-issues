package com.memory.springbootmemoryissues.service.memoryleak;

public class Class2 {
	Class3 class3 = new Class3();

	public void grow() {
		class3.grow();
	}	
}
