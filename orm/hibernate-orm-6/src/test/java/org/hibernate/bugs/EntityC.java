package org.hibernate.bugs;

import jakarta.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class EntityC {

    private LocalDateTime created;

    private Long intValue;
}
