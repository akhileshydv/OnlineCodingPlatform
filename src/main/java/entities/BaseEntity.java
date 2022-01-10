package entities;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BaseEntity {
    private Long id;
    private Date createdOn;
    private String createdBy;
    private Date lastModifiedBy;
    private String lastModifiedOn;
}
