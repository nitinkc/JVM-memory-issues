package com.memory.springbootmemoryissues.service.memoryleak;

public class Class3 {
	MapManager mapManager = new MapManager();

	public void grow() {
		mapManager.grow();
	}		
}
