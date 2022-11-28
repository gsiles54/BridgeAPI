package com.spacex.challenge.task.worker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spacex.challenge.task.model.Task;

import jakarta.annotation.PostConstruct;

@Service
public class WorkerFactory<T extends Task> {
	 @Autowired
	    private List<TaskWorker<T>> workers;

	    private  final Map<String, TaskWorker<T>> myWorkersCache = new HashMap<>();

	    @PostConstruct
	    public void initMyServiceCache() {
	        for(TaskWorker<T> worker : workers) {
	        	myWorkersCache.put(worker.getType(), worker);
	        }
	    }

	    public  TaskWorker<T> getWorker(String type) {
	    	TaskWorker<T> worker =myWorkersCache.get(type);
	        
	        if(worker == null) throw new IllegalArgumentException("Declared task doesn't have any functionality on the server: " + type);
	        return worker;
	    }


}
