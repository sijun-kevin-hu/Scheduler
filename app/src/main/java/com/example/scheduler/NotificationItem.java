package com.example.scheduler;

import java.util.Comparator;

public interface NotificationItem {
    String getName();
    String getType();
    String getDueDate();
    Comparable<?> getFormattedDate();
}
