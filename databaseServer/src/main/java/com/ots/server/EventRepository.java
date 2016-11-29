package com.ots.server;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "event", path = "events")
public interface EventRepository extends CrudRepository<Event, Long> {
    List<Event> findByCarId(@Param("carId") long carId);

    List<Event> findByCarIdAndEventNameIn(@Param("carId") long carId, @Param("eventName") Collection<String> eventName);

    List<Event> findByCarIdAndTimeBetween(@Param("carId") long carId, @Param("startTime") long startTime, @Param("endTime") long endTime);

    List<Event> findByCarIdAndTimeBetweenAndEventNameIn(@Param("carId") long carId, @Param("startTime") long startTime, @Param("endTime") long endTime, @Param("eventName") Collection<String> eventName);
    List<Event> findByTimeBetween(@Param("startTime") long startTime, @Param("endTime") long endTime);

    List<Event> findByTimeBetweenAndEventNameIn(@Param("startTime") long startTime, @Param("endTime") long endTime,
            @Param("eventName") Collection<String> eventName);

    List<Event> findByEventNameIn(@Param("eventName") Collection<String> eventName);
}
