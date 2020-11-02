package com.zdd.jvm;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.ListenerNotFoundException;
import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.*;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MemoryUtil {
    private static final Set<String> heapRegions;

    static {
        heapRegions = ManagementFactory.getMemoryPoolMXBeans().stream()
                .filter(b -> b.getType() == MemoryType.HEAP)
                .map(MemoryPoolMXBean::getName)
                .collect(Collectors.toSet());
    }

    private static NotificationListener gcHandler = (notification, handback) -> {
        if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
            GarbageCollectionNotificationInfo gcInfo = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
            Map<String, MemoryUsage> memBefore = gcInfo.getGcInfo().getMemoryUsageBeforeGc();
            Map<String, MemoryUsage> memAfter = gcInfo.getGcInfo().getMemoryUsageAfterGc();
            StringBuilder sb = new StringBuilder(250);
            sb.append("[").append(gcInfo.getGcAction()).append(" / ").append(gcInfo.getGcCause())
                    .append(" / ").append(gcInfo.getGcName()).append(" / (");
            appendMemUsage(sb, memBefore);
            sb.append(") -> (");
            appendMemUsage(sb, memAfter);
            sb.append("), ").append(gcInfo.getGcInfo().getDuration()).append(" ms]");
            System.out.println(sb.toString());
        }
    };

    public static void startGCMonitor() {
        for (GarbageCollectorMXBean mBean : ManagementFactory.getGarbageCollectorMXBeans()) {
            ((NotificationEmitter) mBean).addNotificationListener(gcHandler, null, null);
        }
    }

    public static void stopGCMonitor() {
        for (GarbageCollectorMXBean mBean : ManagementFactory.getGarbageCollectorMXBeans()) {
            try {
                ((NotificationEmitter) mBean).removeNotificationListener(gcHandler);
            } catch (ListenerNotFoundException e) {
                // Do nothing
            }
        }
    }

    private static void appendMemUsage(StringBuilder sb, Map<String, MemoryUsage> memUsage) {
        memUsage.entrySet().forEach((entry) -> {
            if (heapRegions.contains(entry.getKey())) {
                sb.append(entry.getKey()).append(" used=").append(entry.getValue().getUsed() >> 10).append("K; ");
            }
        });
    }
}
