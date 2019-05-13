package bbt.com.notecarrier.helper

import android.text.format.DateUtils

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object DateFormatterHelper {

    val DAY = 1
    val WEEK = 2
    val MONTH = 3

    val hourMinuteFormat = SimpleDateFormat("hh:mm", Locale.US) // 01:55

    val dateObjectFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)

    val fullHourMinuteFormat = SimpleDateFormat("HH:mm", Locale.US) // 13:55

    val hourMinuteSecFormat = SimpleDateFormat("HH:mm:ss", Locale.US) // 13:55:22

    val ampmFormat = SimpleDateFormat("a", Locale.US) // PM

    val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.US) // 06-03-2018

    val fullDateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.US) // 06-03-2018 13:55

    val ymdFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US) // 2018-03-06

    val dmyhmaFormat = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.US) // 06 Mar 2018, 01:55 PM

    val ymdhmsFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US) // 2018-03-06 01:55:22

    val sdf_day = SimpleDateFormat("EEE dd-MMM-yyyy", Locale.US) // Tue 06-Mar-2018

    val dayDate = SimpleDateFormat("EEE dd", Locale.US) // Tue 06

    val sdf_month = SimpleDateFormat("MMMM yyyy", Locale.US) // March 2018

    val zoneFormat = SimpleDateFormat("Z", Locale.US) // +0530

    val zoneShortFormat = SimpleDateFormat("z", Locale.US) // GMT+05:30

    val zoneLongFormat = SimpleDateFormat("zzzz", Locale.US) // India Standard Time

    val MMMFormat = SimpleDateFormat("MMM", Locale.US) // Mar

    val MMFormat = SimpleDateFormat("MM", Locale.US) // 03

    val ddFormat = SimpleDateFormat("dd", Locale.US) // 06

    val dayFormat = SimpleDateFormat("EEEE", Locale.US) // Tuesday

    val weekFormat = SimpleDateFormat("MMM dd", Locale.US) // Mar 06

    val bookingDateFormat = SimpleDateFormat("EEEE dd MMM, yyyy", Locale.US) // Tuesday 06 Mar, 2018

    val fullbookingDateFormat = SimpleDateFormat("EEEE dd MMMM, yyyy", Locale.US) // Tuesday 06 March, 2018

    val ddMMMMyyyy = SimpleDateFormat("dd MMMM, yyyy", Locale.US) // 06 March, 2018

    val bookingTimeFormat = SimpleDateFormat("hh:mm a", Locale.US) // 01:55 PM

    val dateFormatBooking = SimpleDateFormat("MM/dd/yyyy", Locale.US) // 03/06/2018

    val bookingReqFormat = SimpleDateFormat("dd-MM-yyyy", Locale.US) // 06-3-2018

    val ddMMyyyy = SimpleDateFormat("dd/MM/yyyy", Locale.US) // 2018-03-06

    val EEEEddMMMMyyyy = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.US) // MONDAY, 21 April 2018
    val MMMMddyyyy = SimpleDateFormat("MMMM dd, yyyy", Locale.US) //  April 13, 2018
    val fileSaveDateFormate = SimpleDateFormat("dd_MM_yyyy_hh_mm_a", Locale.US) //  April 13, 2018


    fun parseDate(
        inputDateString: String,
        inputDateFormat: SimpleDateFormat,
        outputDateFormat: SimpleDateFormat
    ): String? {
        var date: Date? = null
        var outputDateString: String? = null
        try {
            date = inputDateFormat.parse(inputDateString)
            outputDateString = outputDateFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return outputDateString
    }

    fun parseDate(date: Date, outputFormat: String): String {
        val spf = SimpleDateFormat(outputFormat, Locale.US)
        return spf.format(date)
    }

    fun parseDate(calendar: Calendar, outputFormat: String): String {

        val spf = SimpleDateFormat(outputFormat, Locale.US)
        return spf.format(calendar.time)
    }

    fun parseDate(calendar: Calendar, outputFormat: SimpleDateFormat): String {
        return outputFormat.format(calendar.time)
    }

    fun setCalendarDateTitle(date: Date, isWeek: Boolean): String {
        val spf: SimpleDateFormat
        val outputFormat: String

        if (!isWeek) {
            outputFormat = "EEEE MMMM dd"
            if (DateUtils.isToday(date.time)) {
                spf = SimpleDateFormat(outputFormat, Locale.US)
                return "Today - " + spf.format(date)
            } else {
                spf = SimpleDateFormat(outputFormat, Locale.US)
                return spf.format(date)
            }
        } else {
            outputFormat = "MMMM dd"

            spf = SimpleDateFormat(outputFormat, Locale.US)
            val selectedMonthDate = spf.format(date)

            val endWeekDate = toCalendar(date)
            endWeekDate.add(Calendar.DAY_OF_MONTH, 6)

            return selectedMonthDate + " - " + parseDate(endWeekDate.time, "dd")
        }
    }

    private fun toCalendar(date: Date): Calendar {
        val cal = Calendar.getInstance()
        cal.time = date
        return cal
    }

    fun StringToDate(dateString: String, inputFormat: SimpleDateFormat): Date {

        //        String dateString = "03/26/2012 11:49:00 AM";
        var convertedDate = Date()
        try {
            convertedDate = inputFormat.parse(dateString)
        } catch (e: ParseException) {

            e.printStackTrace()
        }

        println(convertedDate)
        return convertedDate
    }

    fun isSameDay(cal1: Calendar, cal2: Calendar): Boolean {
        return (cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH)
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
                && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR))
    }

    fun isPast(givenDate: Calendar): Boolean {
        return if (isSameDay(givenDate, Calendar.getInstance())) {
            false
        } else {
            getJustDate(givenDate).before(Calendar.getInstance())
        }
    }

    fun isCurrentMonth(visibleMonth: Calendar, givenDate: Calendar): Boolean {
        return visibleMonth.get(Calendar.MONTH) == givenDate.get(Calendar.MONTH) && visibleMonth.get(Calendar.YEAR) == givenDate.get(
            Calendar.YEAR
        )
    }

    fun isSameDay(date1: Date, date2: Date): Boolean {
        val cal1 = toCalendar(date1)
        val cal2 = toCalendar(date2)
        return (cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH)
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
                && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR))
    }

    @Throws(ParseException::class)
    fun changeDateType(inputDate: String, inputType: String, outPutType: String): String {
        val originalFormat = SimpleDateFormat(inputType, Locale.ENGLISH)
        val targetFormat = SimpleDateFormat(outPutType, Locale.ENGLISH)

        val date = originalFormat.parse(inputDate)
        return targetFormat.format(date)
    }

    @Throws(ParseException::class)
    fun changeDateType(inputDate: String, inputType: DateFormat, outPutType: DateFormat): String {
        val date = inputType.parse(inputDate)
        return outPutType.format(date)
    }

    @Throws(ParseException::class)
    fun parseStringToCalendar(inputDate: String, inputType: DateFormat): Calendar {
        val currentDate = Calendar.getInstance()
        currentDate.time = inputType.parse(inputDate)
        return currentDate
    }

    @Throws(ParseException::class)
    fun parseStringToDate(inputDate: String, inputType: DateFormat): Date {
        val currentDate = Calendar.getInstance()

        val calendar = toCalendar(inputType.parse(inputDate))
        calendar.set(Calendar.YEAR, currentDate.get(Calendar.YEAR))
        calendar.set(Calendar.MONTH, currentDate.get(Calendar.MONTH))
        calendar.set(Calendar.DAY_OF_MONTH, currentDate.get(Calendar.DAY_OF_MONTH))
        return calendar.time
    }

    fun setTimeToCurrentDate(dateForTime: Date): Date {
        val calForTime = toCalendar(dateForTime)
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        calendar.set(Calendar.HOUR_OF_DAY, calForTime.get(Calendar.HOUR_OF_DAY))
        calendar.set(Calendar.MINUTE, calForTime.get(Calendar.MINUTE))
        calendar.set(Calendar.SECOND, calForTime.get(Calendar.SECOND))
        calendar.set(Calendar.MILLISECOND, calForTime.get(Calendar.MILLISECOND))
        return calendar.time
    }

    fun addMinutes(startTime: Calendar, min: Int): Calendar {
        val totalDuration = startTime.clone() as Calendar
        totalDuration.add(Calendar.MINUTE, min)
        return totalDuration
    }

    @Throws(ParseException::class)
    fun getTimeSlots(
        startTime: String,
        mins: IntArray,
        inputTimeFormat: SimpleDateFormat,
        outputFormat: SimpleDateFormat
    ): String {
        var totalMin = 0
        for (i in mins.indices) {
            totalMin = totalMin + mins[i]
        }
        val formattedStartTime = parseDate(startTime, inputTimeFormat, outputFormat)
        val endTime = addMinutes(parseStringToCalendar(startTime, inputTimeFormat), totalMin)
        val formattedEndTime = parseDate(endTime, outputFormat)
        return String.format(Locale.getDefault(), "%s to %s", formattedStartTime, formattedEndTime)

    }

    @Throws(ParseException::class)
    fun getEndTime(
        startTime: String,
        mins: IntArray,
        inputTimeFormat: SimpleDateFormat,
        outputFormat: SimpleDateFormat
    ): String {
        var totalMin = 0
        for (i in mins.indices) {
            totalMin = totalMin + mins[i]
        }
        val endTime = addMinutes(parseStringToCalendar(startTime, inputTimeFormat), totalMin)
        return parseDate(endTime, outputFormat)

    }

    private fun getJustDate(calendar: Calendar): Calendar {
        val jstDate = calendar.clone() as Calendar
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return jstDate
    }

    fun getDifferenceInSecond(biggerTime: Long, smallerTime: Long): Long {
        val diff = biggerTime - smallerTime
//        long minutes = seconds / 60;
        return diff / 1000
    }
}