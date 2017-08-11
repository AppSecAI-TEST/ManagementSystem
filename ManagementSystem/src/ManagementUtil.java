import java.text.SimpleDateFormat;
import java.util.Date;

public class ManagementUtil {

	/**
	 * 날짜 변환
	 * @param from
	 * @return
	 */
	public String data2string(Date from){
		from = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String to = transFormat.format(from);
		return to;
	}
	
	public Date string2date(String from) throws Exception{
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date to = transFormat.parse(from);
		return to;
	}
}
