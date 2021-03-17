package uz.pdp.task1.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.task1.entity.Attachment;

@Projection(types = Attachment.class)
public class CustomAttachment {
    Integer getId;
    String getName;
    long getSize;
    String getContentType;
}
