package com.spacex.challenge.task.worker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import jakarta.annotation.PostConstruct;

@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class WorkerFactory {
	 	
		@Autowired
	    private List<TaskWorker> workers;

	    private  final Map<String, TaskWorker> myWorkersCache = new HashMap<>();


		@PostConstruct
	    public <T> void initMyServiceCache() {
	        for(TaskWorker<T> worker : workers) {
	        	myWorkersCache.put(worker.getType(), worker);
	        }
	    }

	    public <T> TaskWorker<T> getWorker(String type) {
	    	TaskWorker<T> worker =myWorkersCache.get(type);
	        
	        if(worker == null) throw new IllegalArgumentException("Declared task doesn't have any functionality on the server: " + type);
	        return worker;
	    }


}
