package com.suncorp.cashman.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.suncorp.cashman.command.CashmanCommand;
import com.suncorp.cashman.command.CashmanCommandRunner;
import com.suncorp.cashman.exceptions.CashmanExecutionException;

public class CashmanRunnerCommandCache {
	private final Map<CashmanCommand, Class<? extends CashmanCommandRunner>> annotationMap;
	@Autowired
	ApplicationContext context;
	
	public CashmanRunnerCommandCache(){
		this.annotationMap = scan();
	}

	@SuppressWarnings("unchecked")
	private Map<CashmanCommand, Class<? extends CashmanCommandRunner>> scan() {
		Reflections reflection = new Reflections("com.suncorp");
		Set<Class<?>> classes = reflection.getTypesAnnotatedWith(CashmanCommand.class);
		Map<CashmanCommand, Class<? extends CashmanCommandRunner>> map = new HashMap<CashmanCommand, Class<? extends CashmanCommandRunner>>();
		for (Class<?> clazz : classes) {
			CashmanCommand annotation = clazz.getAnnotation(CashmanCommand.class);
			map.put(annotation, (Class<? extends CashmanCommandRunner>) clazz);
		}
		return map;
	}
	
	public CashmanCommandRunner getRunnerCommand(String command) {
		for (Entry<CashmanCommand, Class<? extends CashmanCommandRunner>> entry : this.annotationMap.entrySet()) {
			if (command.equalsIgnoreCase(((CashmanCommand)entry.getKey()).name())) {
				Class<? extends CashmanCommandRunner> clazz =  (Class<? extends CashmanCommandRunner>) entry.getValue();
				if (clazz == null) { 
					throw new RuntimeException("Command runner not found : " + command);
				}
				try { 
					return context.getBean(clazz);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					return clazz.newInstance();
				} catch (Throwable e) {
					e.printStackTrace();
					throw new CashmanExecutionException("Not able to instantiate for class = " + clazz);
				}
			}
		}
		
		return null;
	}
}
