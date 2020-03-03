package io.kubernetes.client.extended.event.legacy;

import io.kubernetes.client.custom.V1Patch;
import io.kubernetes.client.openapi.models.V1Event;
import java.util.Optional;
import java.util.function.Predicate;
import org.apache.commons.lang3.tuple.MutablePair;

public class EventCorrelator {

  protected EventAggregator aggregator;
  protected Predicate<V1Event> filter;
  protected EventLogger logger;

  public EventCorrelator() {
    final int maxLRUCacheEntries = 4096;

    this.filter = new EventSpamFilter(maxLRUCacheEntries, EventUtils::getSpamKey)::filter;
    this.aggregator =
        new EventAggregator(
            maxLRUCacheEntries,
            EventUtils::getAggregatedAndLocalKeyByReason,
            EventUtils::getAggregatedMessageByReason);
    this.logger = new EventLogger(maxLRUCacheEntries, EventUtils::getEventKey);
  }

  public Optional<MutablePair<V1Event, V1Patch>> correlate(V1Event event) {
    MutablePair<V1Event, String> aggregatedResult = this.aggregator.aggregate(event);
    V1Event aggregatedEvent = aggregatedResult.getLeft();
    String cacheKey = aggregatedResult.getRight();
    MutablePair<V1Event, V1Patch> observeResult = this.logger.observe(aggregatedEvent, cacheKey);
    if (!this.filter.test(event)) {
      return Optional.empty();
    }
    return Optional.of(observeResult);
  }

  public void updateState(V1Event event) {
    this.logger.updateState(event);
  }
}
