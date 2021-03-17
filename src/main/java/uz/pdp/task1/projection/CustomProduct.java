package uz.pdp.task1.projection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.rest.core.config.Projection;
import uz.pdp.task1.entity.Attachment;
import uz.pdp.task1.entity.Category;
import uz.pdp.task1.entity.Measurement;
import uz.pdp.task1.entity.Product;
import uz.pdp.task1.entity.template.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Projection(types = Product.class)
public class CustomProduct extends AbstractEntity {
     Integer getId;
     String getName;
     boolean getActive;
     Category getCategory;
     Attachment getPhoto;
     String getCode;
     Measurement getMeasurement;
}
