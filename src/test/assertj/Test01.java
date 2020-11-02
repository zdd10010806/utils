package assertj;

import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class Test01 {
    private static final long MAX_METRIC_LIVE_TIME_MS = 1000 * 60 * 5;

    @Test
    public void a_few_simple_assertions() {
        assertThat("The Lord of the Rings").isNotNull()
                .startsWith("The")
                .contains("Lord")
                .endsWith("Ringsz");
    }


    @Test
    public void ttt() {

        new ConcurrentLinkedHashMap.Builder<Long, String>()
                .maximumWeightedCapacity(MAX_METRIC_LIVE_TIME_MS).weigher((key, value) -> {
            // Metric older than {@link #MAX_METRIC_LIVE_TIME_MS} will be removed.
            int weight = (int) (System.currentTimeMillis() - key);
            // weight must be a number greater than or equal to one
            return Math.max(weight, 1);
        }).build();
    }


}
