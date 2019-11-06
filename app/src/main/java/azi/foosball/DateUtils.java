package azi.foosball;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.threeten.bp.DateTimeUtils;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.temporal.ChronoUnit;


public class DateUtils {

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final long WEEK = 24 * 60 * 60 * 7;
	private static final long DAY = 24 * 60 * 60;
	private static final long HOUR = 60 * 60;
	private static final long MINUTE = 60;
	static final long MONTH = 30 * DAY;

	public static String format(long date) {

		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date(date);
		return sdfDate.format(now);
	}

	public static String date(long date) {

		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date(date);
		return sdfDate.format(now);
	}

	public static String hour(long date) {

		SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm:ss");
		Date now = new Date(date);
		return sdfDate.format(now);
	}

	public static String formatRelative(LocalDateTime date) {

		ZonedDateTime zdt = date.atZone(ZoneId.systemDefault());
		Date output =  DateTimeUtils.toDate(zdt.toInstant());

		if (minutesSince(date) < MINUTE) {
			return android.text.format.DateUtils.getRelativeTimeSpanString(output.getTime(), new Date().getTime(), android.text.format.DateUtils.SECOND_IN_MILLIS) + "";
		}
		else if (hoursSince(date) < HOUR) {
			return android.text.format.DateUtils.getRelativeTimeSpanString(output.getTime(), new Date().getTime(), android.text.format.DateUtils.MINUTE_IN_MILLIS) + "";
		}
		else if (daysSince(date) < DAY) {
			return android.text.format.DateUtils.getRelativeTimeSpanString(output.getTime(), new Date().getTime(), android.text.format.DateUtils.HOUR_IN_MILLIS) + "";
		}
		else if (weeksSince(date) < WEEK) {
			return android.text.format.DateUtils.getRelativeTimeSpanString(output.getTime(), new Date().getTime(), android.text.format.DateUtils.DAY_IN_MILLIS) + "";
		}
		else {
			return android.text.format.DateUtils.getRelativeTimeSpanString(output.getTime(), new Date().getTime(), android.text.format.DateUtils.YEAR_IN_MILLIS) + "";
		}
	}

	private static long minutesSince(LocalDateTime date) {

		return ChronoUnit.MINUTES.between(LocalDateTime.now(), date);
	}

	private static long hoursSince(LocalDateTime date) {

		return ChronoUnit.HOURS.between(LocalDateTime.now(), date);
	}

	private static long weeksSince(LocalDateTime date) {

		return ChronoUnit.WEEKS.between(LocalDateTime.now(), date);
	}

	private static long daysSince(LocalDateTime date) {

		return ChronoUnit.DAYS.between(LocalDateTime.now(), date);
	}
}