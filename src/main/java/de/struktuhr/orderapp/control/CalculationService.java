package de.struktuhr.orderapp.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CalculationService {

    private final static Logger log = LoggerFactory.getLogger(CalculationService.class);

    private final ConcurrentHashMap<String, LocalDateTime> map = new ConcurrentHashMap<>();


    @Async
    public void runCalculation(String id) {
        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        map.put(id, LocalDateTime.now());
        log.info("Calculation finished for {}", id);
    }

    public Map<String, LocalDateTime> getResults() {
        Map<String, LocalDateTime> result = new LinkedHashMap<>();
        result.putAll(map);
        map.clear();
        return result;
    }
}
