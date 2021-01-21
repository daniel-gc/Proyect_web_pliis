package mx.pliis.afiliacion.utils.copyproperties;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CopyProperties {

	public Object copyProperties(Object oldObject, Object newObject) {
		try {
			BeanUtils.copyProperties(newObject, oldObject);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return newObject;
	}
	
}