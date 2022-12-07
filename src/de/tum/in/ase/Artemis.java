package de.tum.in.ase;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Artemis {
    // calculate the average grade of all valid exams
    public static double averageGrade(Stream<Exam> exams) {
        return exams.map(Exam::getGrade)
                .filter(grade -> grade.getStatus().equals((Status.VALID)))
                .collect(Collectors.averagingDouble(Grade::getValue));
    }

    // sort all exams by exam date in ascending order
    public static List<Exam> sortExamsByExamDate(Stream<Exam> exams) {
        return exams.sorted(Comparator.comparing(Exam::getExamDate))
                .toList();
    }

    // return the date of the first exam written, if none -> return null
    public static LocalDate dateOfFirstExam(Stream<Exam> exams) {
        Optional<LocalDate> date =  exams.map(exam -> exam.getExamDate())
                .min(LocalDate::compareTo);
        return date.orElse(null);
    }

    // count how many exams are online and onsite
    public static Map<Boolean, Integer> countByOnline(Stream<Exam> exams) {
        return exams.filter(exam -> exam.getGrade().getStatus().equals(Status.VALID))
                .collect(Collectors.groupingBy(Exam::isOnline, Collectors.summingInt(exam -> 1)));
    }

    // TODO: calculate the students performance index according to the exercise
    public static double calculatePerformanceIndex(Stream<Exam> exams) {
        return 0;
    }


	// create a report using the passed formatter
	public static String createFormattedReport(Stream<Exam> exams, Formatter formatter) {
		return exams.map(exam -> formatter.formatExam(exam))
                .collect(Collectors.joining(System.lineSeparator()));
	}

    // create a simple report string
    public static String createSimpleReport(Stream<Exam> exams) {
        return exams.map(exam -> "[" + exam.getGrade().getStatus() + "] Exam \"" + exam.getName() + "\": " + exam.getGrade().getValue())
                .collect(Collectors.joining(System.lineSeparator()));
    }

}
