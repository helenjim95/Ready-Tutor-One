package de.tum.in.ase;

@FunctionalInterface
public interface Formatter {
    default String formatExam(Exam exam) {
        return exam.getName() + "\n";
    }
}
