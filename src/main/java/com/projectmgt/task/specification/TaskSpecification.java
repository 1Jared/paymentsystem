package com.projectmgt.task.specification;

import com.projectmgt.task.entity.Status;
import com.projectmgt.task.entity.Task;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskSpecification {

    public static Specification<Task> filterByStatusAndDueDate(Status status, Date dueDate) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (status != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), status));
            }

            if (dueDate != null) {
                predicates.add(criteriaBuilder.equal(root.get("dueDate"), dueDate));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
