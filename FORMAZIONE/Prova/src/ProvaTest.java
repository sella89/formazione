import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

public class ProvaTest {

	private static final Set<String> metadata = Set.of("Author","creator","title","pdf:docinfo:title","dc:title","meta:author","pdf:docinfo:creator","dc:creator");
	public static void main(String[] args) {
		
		String date = "D:20210720060254-04'00'";
		String dataf = date.substring(date.indexOf(':')+1, date.indexOf('-'));
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH);
		try {
			Date data = format.parse(dataf);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");  
			String str = dateFormat.format(data);  
			System.out.println(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		CustomInfo customInfo = new CustomInfo();
//		customInfo.setAuthor("Riccardo Sellitto");
//		customInfo.setTitle("Alstom");
//		Map<String, Object> contentMap = new HashMap<>();
//		Field[] fields = CustomInfo.class.getDeclaredFields();
//		for (Field field : fields) {
//			String key = field.getName();
//			String value = getProperty(customInfo,key , "");			
//			List<String> subList =
//					metadata.stream()
//					.filter(f-> f.contains(key)||f.contains(setFirstCharaterToUpperCase(key)))
//					.collect(Collectors.toList());
//			subList.forEach( ris -> contentMap.put(ris, value));
//			if(key.equalsIgnoreCase("Author")) {
//				String key2=  "creator";
//				List<String> subList2 = 
//						metadata.stream().filter(f-> f.contains(key2)||f.contains(setFirstCharaterToUpperCase(key2)))
//						.collect(Collectors.toList());
//				subList2.forEach( ris -> contentMap.put(ris, value));
//			}
//		}
//		contentMap.forEach((k,v)->System.out.println(k+":"+v));
	}
	
	 public static <T> T getProperty(Object obj, String property, T defaultValue) {
	        T returnValue = (T) getProperty(obj, property);
	        if (returnValue == null) {
	            returnValue = defaultValue;
	        }
	        return returnValue;
	    }
	
	public static Object getProperty(Object obj, String property) {
        Object returnValue = null;
        try {
            String methodName = "get" + setFirstCharaterToUpperCase(property);
            Class clazz = obj.getClass();
            Method method = clazz.getMethod(methodName, null);
            returnValue = method.invoke(obj, null);
        }
        catch (Exception e) {
            // Do nothing, we'll return the default value
        }
        return returnValue;
    }
	
	public static String setFirstCharaterToUpperCase(String word) {
		return word.substring(0, 1).toUpperCase() + word.substring(1, word.length());
	}
}
